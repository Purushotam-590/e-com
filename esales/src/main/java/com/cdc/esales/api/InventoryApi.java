package com.cdc.esales.api;

import com.cdc.esales.dto.InventoryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



public interface InventoryApi {

    @PostMapping("/update")
    public ResponseEntity<String> updateInventory(@RequestBody InventoryItem newItem, @RequestParam String operation);

}
