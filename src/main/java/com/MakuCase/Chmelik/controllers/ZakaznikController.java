package com.MakuCase.Chmelik.controllers;

import com.MakuCase.Chmelik.models.Zakaznik;
import com.MakuCase.Chmelik.services.ZakaznikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Zakaznik controller handling zakaznik's CRUD tasks.
 *  EXCEPT for updating and removing!
 */
@RestController
@RequestMapping("/zakaznik")
public class ZakaznikController {
   /**
    * service for handling all designated tasks
    */
   @Autowired
   ZakaznikService zakaznikService;
   /**
    *  It calls its service for fetching listo of all customer objects from database.
    * @return List of all objects Zakaznik from database, as well as HTTP response code 200.
    *  If no customers are present in database API returns empty list with HTTP code 500.
    */
   @GetMapping("/getAll")
   public ResponseEntity<List<Zakaznik>> getAllCustomers(){
      return zakaznikService.getAllCustomers();
   }
   /**
    * Calls service to fetch a customer defined by id.
    * @param id as Integral
    * @return object Zakaznik queried from db by provided id or NULL, as well as HTTP response code:
    * If succes: HTTP code 200.
    * If customer defined by id doesn't exist API returns NULL and HTTP code 500.
    * If id provided doesn't fit requirements returns NULL and HTTP code 406.
    */
   @GetMapping("/getById")
   public ResponseEntity<Zakaznik> getCustomerById(@RequestParam Integer id){
      return zakaznikService.getCustomerById(id);
   }
   /**
    * Calls service to fetch a customer defined by e-mail address.
    * E-mail address, pattern checked inside the service method.
    * @param email e-mail address
    * @return object Zakaznik queried from db by provided e-mail address, as well as HTTP response code:
    * Object Zakaznik defined by e-mail address with HTTP code 200.
    * If address provided doesn't fit requirements returns null and HTTP code 406.
    * If customer defined by id doesn't exist API returns null and HTTP code 500.
    */
   @GetMapping("/getByEmail")
   public ResponseEntity<Zakaznik> getCustomerByEmail(@RequestParam String email){
      return zakaznikService.getCustomerByEmail(email);
   }
   
   /**
    * Fetches a customer defined by e-mail address and HTTP code.
    * Phone accepted patterns, checked inside the service method.
    * 159 159 159 159
    * 159159159159
    * 159 159 159
    * 159159159
    * @param phone phone number
    * @return object Zakaznik queried from db by provided phone number, as well as HTTP response code:
    *If success, returns object Zakaznik and HTTP code 200.
    * If address provided doesn't fit requirements returns null and HTTP code 406.
    * If customer defined by id doesn't exist API returns null and HTTP code 500.
    */
   @GetMapping("/getByPhone")
   public ResponseEntity<Zakaznik> getCustomerByPhone(@RequestParam String phone){
      return zakaznikService.getCustomerByPhone(phone);
   }
   
   /**
    *Creation API for new customer.
    * @param name required param
    * @param email not required
    * @param phone not required
    * @return String message which is specific for diferent results, as well as HTTP response code:
    * If everything is ok: "New customer has been added" and HTTP code 200.
    * If name not provided returns: "Error: Name must be provided" and HTTP code 400.
    * If email or phone doesn't strict to pattern API returns: "Error: {param} has not correct form" and HTTP code 400.
    * If other exception emerge:"Error while adding new customer" and HTTP code 500;
    */
   @PostMapping("/create")
   public ResponseEntity<String> createCustormer(@RequestParam String name, @RequestParam String email, @RequestParam String phone){
      return zakaznikService.createNewCustormer(name, email, phone);
   }
   
}
