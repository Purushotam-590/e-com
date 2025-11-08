package com.cdc.esales.api;

import com.cdc.esales.dto.InventoryItem;
import com.cdc.esales.service.InventoryProcess;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryApiImpl implements InventoryApi {

    private final InventoryProcess inventoryProcess;

    public InventoryApiImpl(InventoryProcess inventoryProcess) {
        this.inventoryProcess = inventoryProcess;
    }


    //@PreAuthorize("@securityPermission.checkPermission('INVENTORY_MANAGE')")
    public ResponseEntity<String> updateInventory(@RequestBody InventoryItem newItem, @RequestParam String operation) {

        if (validateInput(operation)) {
            return ResponseEntity.badRequest().body("Operation must be either 'c', 'u', or 'd'.");
        }
        inventoryProcess.processInventory(newItem, operation);

        return ResponseEntity.ok("Simulated CDC event sent for item ID: " + newItem.getId());
    }

    private static boolean validateInput(String operation) {
        return !("c".equals(operation) || "u".equals(operation) || "d".equals(operation));
    }
}

