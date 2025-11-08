package com.cdc.esales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EsalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsalesApplication.class, args);
	}

}
