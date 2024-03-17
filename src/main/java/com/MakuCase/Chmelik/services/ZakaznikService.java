package com.MakuCase.Chmelik.services;

import com.MakuCase.Chmelik.dao.ZakaznikDAO;
import com.MakuCase.Chmelik.models.Zakaznik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/**
 * Zakaznik service managing CRUD tasks.
 */
@Service
public class ZakaznikService {
   /**
    * DAO database handler.
    */
   @Autowired
   ZakaznikDAO zakaznikDAO;
   /**
    * @param emailPattern regex for checking email format correctness
    */
   private String emailPattern = "^(.+)@(\\S+)$";
   /**
    * @param phonePattern regex for checking phone format correctness
    */
   private String phonePattern = "\"(?:\\+\\d{3})?\\d{9}|(?:\\d{3} ){3}\\d{3}|(?:\\d{3} ){2}\\d{3}\"gm";
   
   /**
    * Service for obtaining all customers from database, using zakaznikDAO.
    * @return List of all customers from database, as well as HTTP response code:
    * If everything ok: HTTP code 200
    * If no result is obtained from database: HTTP code 500 and empty List
    */
   public ResponseEntity<List<Zakaznik>> getAllCustomers() {
      try {
         return new ResponseEntity<>(zakaznikDAO.findAll(), HttpStatus.OK);
      }catch (Exception e){
         e.printStackTrace();
      }
      return new ResponseEntity<>(new ArrayList<>(), HttpStatusCode.valueOf(500));
   }
   /**
    * Fetches a customer defined by id, using zakaznikDAO, from database.
    * @param id as Integral
    * @return object Zakaznik queried from db by provided id, as well as HTTP response code:
    * If successful returns Zakaznik and HTTP code 200.
    * If id provided doesn't fit requirements returns NULL and HTTP code 406.
    * If customer defined by id doesn't exist API returns NULL and HTTP code 500.
    */
   public ResponseEntity<Zakaznik> getCustomerById(Integer id) {
      if (id <=0){
         return ResponseEntity.status(HttpStatusCode.valueOf(406)).body(null);
      }
      try{
      
            return new ResponseEntity<>(zakaznikDAO.findById(id).get(), HttpStatus.OK);
         
      }catch (Exception e){e.printStackTrace();}
       return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(null);
   }
   /**
    * Fetches a customer defined by email, using zakaznikDAO, from database.
    * Checks email pattern with PatternMatches method inside.
    * @param email as String
    * @return object Zakaznik queried from db by provided email address, as well as HTTP response code:
    * If successful returns Zakaznik and HTTP code 200.
    * If address pattern provided doesn't fit requirements returns NULL and HTTP code 406.
    * If customer defined by address doesn't exist API returns NULL and HTTP code 500.
    */
   public ResponseEntity<Zakaznik> getCustomerByEmail(String email) {
      if(!PatternMatches(email, emailPattern)){
         return new ResponseEntity<>(null, HttpStatusCode.valueOf(406));
      }
      try {
      return new ResponseEntity<>(zakaznikDAO.findByEmail(email), HttpStatus.OK);
      }catch (Exception e){ e.printStackTrace();}
      return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
   }
   /**
    * Fetches a customer defined by phone number, using zakaznikDAO, from database.
    * Checks phone number pattern with PatternMatches method inside.
    * @param phone as String
    * @return object Zakaznik queried from db by provided phone, as well as HTTP response code:
    * If successful returns Zakaznik and HTTP code 200.
    * If phone number provided doesn't match pattern, service returns NULL and HTTP code 406.
    * If customer defined by phone number doesn't exist, service returns NULL and HTTP code 500.
    */
   public ResponseEntity<Zakaznik> getCustomerByPhone(String phone) {
      if(!PatternMatches(phone, phonePattern)){
         return new ResponseEntity<>(null, HttpStatusCode.valueOf(406));
      }
      try {
         return new ResponseEntity<>(zakaznikDAO.findByPhone(phone), HttpStatus.OK);
      }catch (Exception e){ e.printStackTrace();}
      return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
   }
   
   /**
    * Service inserting new customer in database, using customerDAO.
    * It checks inside for correct email and phone number pattern.
    * @param name required
    * @param email not required
    * @param phone not required
    * @return String answer and HTTP code:
    * If success: "New customer has been added" and HTTP code 200.
    * If no name is provided: "Error: Name must be provided" and HTTP code 406.
    * If email or phone number pattern is not correct: Error: {param} has not correct form", HTTP code 400
    * If other error apears: "Error while adding new customer", HTTP code 400.
    */
   public ResponseEntity<String> createNewCustormer(String name, String email, String phone) {
      if(name.equals("")|| name.isEmpty() || name.isBlank()) {
         return new ResponseEntity<>("Error: Name must be provided", HttpStatusCode.valueOf(406));
      }
      if(!PatternMatches(email, emailPattern)){
         return new ResponseEntity<>("Error: email has not correct form", HttpStatusCode.valueOf(400));
      } else if (!PatternMatches(phone,phonePattern)) {
         return new ResponseEntity<>("Error: phone has not correct form", HttpStatusCode.valueOf(400));
      }
      Zakaznik newZakaznik = new Zakaznik(name, email, phone);
      try {
         zakaznikDAO.save(newZakaznik);
         return  new ResponseEntity<>("New customer has been added", HttpStatus.OK);
      }catch (Exception e){e.printStackTrace();}
      return new ResponseEntity<>("Error while adding new customer", HttpStatusCode.valueOf(500));
   }
   /**
    * Helper methode for correct format of email address and/or phone number.
    * @param stringToCheck param which is sent from calling service for checking.
    * @param regexPattern class property, chosen - in service -  by type of sent string (email or phone number)
    * @return If matches returns TRU, otherwise false.
    */
   public static boolean PatternMatches(String stringToCheck, String regexPattern) {
      
      return Pattern.compile(regexPattern)
            .matcher(stringToCheck)
            .matches();
   }
}

