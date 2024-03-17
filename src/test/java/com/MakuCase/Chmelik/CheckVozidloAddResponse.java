package com.MakuCase.Chmelik;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckVozidloAddResponse {
   @LocalServerPort
   private Integer port;
   @Autowired
   private TestRestTemplate testRestTemplate;
   private String url = "http://localhost:"+port+"/vozidla/add" ;
   @Test
   public void shouldPassIfStringMatches(){
      assertEquals("Vozidlo succesfully added", testRestTemplate.getForObject(url, String.class));
   }
}
