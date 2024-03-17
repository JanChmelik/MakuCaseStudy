package com.MakuCase.Chmelik.controllers;

import com.MakuCase.Chmelik.models.Vozidla;
import com.MakuCase.Chmelik.services.VozidloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Vozidlo controller handling CRUD tasks.
 */
@RestController
@RequestMapping("/vozidla")
public class VozidloController {
   /**
    * service for handling all designated tasks
    */
   @Autowired
   VozidloService vozidloService;
   /**
    *  It calls its service for fetching list of all vozidlo objects from database.
    * @return List of all objects Zakaznik from database, as well as HTTP response code 200.
    *  If no customers are present in database API returns empty list with HTTP code 500.
    */
   @GetMapping("/getAll")
   public ResponseEntity<List<Vozidla>> getAll() {
      return vozidloService.getAll();
   }
   /**
    * If no id is provided, this is "safe option", that fetches List of all Vozidlo objects from database.
    * Effect is similar as .getAll service
    * @return
    */
   @GetMapping("/getById")
   public ResponseEntity<List<Vozidla>> getById() {
      return vozidloService.getAll();
   }
   /**
    * Calls its service to fetch List of all available Vozidla.
    * @return List of Vozidla which have available=True.
    */
   @GetMapping("/Available")
   public ResponseEntity<List<Vozidla>> getAllAvailable(){return vozidloService.getAllAvailable();}
   /**
    * Calls service to fetch a Vozdilo object defined by id.
    * @param id as Integral
    * @return object Vozidlo queried from db by provided id or NULL, as well as HTTP response code:
    * If succes: HTTP code 200.
    * If vozidlo defined by id doesn't exist API returns NULL and HTTP code 500.
    * If id provided doesn't fit requirements returns NULL and HTTP code 406.
    */
   @GetMapping("/getById/")
   public ResponseEntity<Vozidla> getById(@RequestParam Integer id) {
      return vozidloService.getById(id);
   }
   /**
    *Calls its service  to insert instance of Vozidla into datatabase.
    * @param vozidlo
    * @return message and HTTP code:
    * If succesfull: "Vozidlo succesfully added" and HTTP code 200.
    * Else: "Vozidlo could not be added" and HTTP code 400.
    */
   @PostMapping("/add")
   public ResponseEntity<String> addVozidlo(@RequestBody Vozidla vozidlo){
     return vozidloService.addVozidlo(vozidlo);
   }
   
   /**
    * Calls service to create new instance of Vozidla, then insert it to database.
    * @param brand required string
    * @param model required string
    * @param available required boolean
    * @return Message and HTTP code:
    * If success: "Vozidlo successfully created and inserted to db." and HTTP code 200.
    * If either brand,model or available is NULL: Brand, model and available needs to be provided." and HTTP code 406
    * If any other error appears: "Vozidlo created, ERROR while inserting to db." and HTTP code 500.
    */
   @PostMapping("/create")
   public ResponseEntity<String> createVozidlo(@RequestParam String brand, @RequestParam String model, @RequestParam Boolean available){
      return vozidloService.createVozidlo(brand, model, available);
   }
   /**
    * Calls its service to delete a Vozidla instance of specified id from database.
    * @param id required
    * @return message and HTTP code:
    * If successful "Successfully deleted" and HTTP code 200.
    * If id provided doesn't fit requirements: "Id provided is not correct " and HTTP code 406.
    * If customer defined by id doesn't exist:"Vozidlo could not be deleted" and HTTP code 500.
    */
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deleteVozidloById(@PathVariable Integer id){
      return vozidloService.deleteById(id);
   }
   
   /**
    * Calls its service to fetch Vozidla instance by id, then alter it by  Vozidla instance provided.
    * Causes alteration of original, keeps the id.
    * @param id of instance to be changed, required
    * @param alteredVozidlo instance by the change is processeed, required
    * @return Message and HTTP code:
    * If successful: "Updated successfully." and HTTP code 200.
    * If id provided is not correct: "Provided Id is incorrect" and HTTP code 400.
    * If any other error appears: "Unable to find vozidlo to change." and HTTP code 406.
    */
   @PutMapping("/change/{id}")
   public ResponseEntity<String> changeVozidloById(@PathVariable Integer id, @RequestBody Vozidla alteredVozidlo){
      return vozidloService.changeVozidloById(id, alteredVozidlo);
   }
}
