package com.MakuCase.Chmelik.controllers;

import com.MakuCase.Chmelik.models.Zapujcka;
import com.MakuCase.Chmelik.services.ZapujckaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Zapujcka controller handling CRUD tasks.
 */
@RestController
@RequestMapping("/zapujcka")
public class ZapujckaController {
   /**
    * service for handling all designated tasks
    */
   @Autowired
   ZapujckaService zapujckaService;
   
   /**
    *  It calls its service for fetching list of all vozidlo objects from database.
    * @return List of all objects Zapujcka from database, as well as HTTP response code 200.
    *  If no customers are present in database API returns empty list with HTTP code 500.
    */
   @GetMapping("/getAll")
   public ResponseEntity<List<Zapujcka>> getAll(){ return zapujckaService.getAll();}
   /**
    * Calls service to fetch a Zapujcka object defined by id.
    * @param id of Zapujcka instance as Integral
    * @return object Zapujcka queried from db by provided id or NULL, as well as HTTP response code:
    * If succes: HTTP code 200.
    * If Zapujcka defined by id doesn't exist API returns NULL and HTTP code 500.
    * If id provided doesn't fit requirements returns NULL and HTTP code 406.
    */
   @GetMapping("/getById")
   // /getBy?id="id"
   public ResponseEntity<Zapujcka> getById(@RequestParam Integer id){
     return zapujckaService.getById(id);
   }
   
   /**
    * Calls its service to fetch List of Zapujcka defined by Vozidlo id.
    * @param id of instance of Vozidlo
    * @return List of Zapujcka for specified Vozidla instance and HTTP code:
    * If successfull: List of all Zapujcka for specified Vozidla and HTTP code 200
    * If unsuccessful: Empty list and HTTP code 406.
    */
   @GetMapping("/getForVozidlaId")
   // /getBy?id="id"
   public ResponseEntity<List<Zapujcka>> getByIdVozidla(@RequestParam Integer id){
      return zapujckaService.getByVozidlaId(id);
   }
   
   /**
    * Calls its service to fetch List of Zapujcka defined by Vozidlo id.
    * @param id of instance of Zakaznik
    * @return Message and HTTP code:
    *
    */
   @GetMapping("/getForZakaznikId")
   // /getBy?id="id"
   public ResponseEntity<List<Zapujcka>> getByZakaznikId(@RequestParam Integer id){
      return zapujckaService.getByZakaznikId(id);
   }
   
   /**
    * Calls its service to create new instance of Zapujcka, then insert that to database.
    * @param dateFrom required, start of new Zapujcka
    * @param dateTo required, end of new Zapujcka
    * @param idVozidla required, id of Vozidla provided
    * @param idZakaznik required, id of Zakaznik
    * @return Message and HTTP code:
    * If succesfull: "New Zapujcka created" and HTTP code 200.
    * If data missing: "All information must be provided" and HTTP code 406.
    * If other error appears: "Unable to create Zapujcka and insert it to database" and HTTP code 500.
    */
   @PostMapping("/create")
   // /create?dateFrom=''&dateTo=''&idVozidla=''&idZakaznik=''
   public ResponseEntity<String> createZapujcka(@RequestParam(name = "dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateFrom,
                                                @RequestParam(name = "dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateTo,
                                                @RequestParam Integer idVozidla, @RequestParam Integer idZakaznik){
      return zapujckaService.createNewZapujcka(dateFrom, dateTo, idZakaznik, idVozidla);
   }
}
