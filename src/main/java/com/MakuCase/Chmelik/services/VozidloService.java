package com.MakuCase.Chmelik.services;

import com.MakuCase.Chmelik.dao.VozidloDAO;
import com.MakuCase.Chmelik.models.Vozidla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Vozidlo service for managing task called by controller.
 */
@Service
public class VozidloService {
   /**
    * DAO database handler.
    */
   @Autowired
   VozidloDAO vozidloDao;
   /**
    *  Fetches list of all vozidlo objects from database using DAO
    * @return List of all objects Zakaznik from database, as well as HTTP response code 200.
    *  If no customers are present in database API returns empty list with HTTP code 500.
    */
   public ResponseEntity<List<Vozidla>> getAll() {
      try{
         return new ResponseEntity<>(vozidloDao.findAll(), HttpStatus.OK);
      }catch (Exception e) {
         e.printStackTrace();
      }
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
   }
   /**
    * Fetches List of all available Vozidla using DAO.
    * @return List of Vozidla which have available=True.
    */
   public ResponseEntity<List<Vozidla>> getAllAvailable() {
      try {
         return new ResponseEntity<>(vozidloDao.findAllByAvailable(), HttpStatus.OK);
      } catch (Exception e){
         e.printStackTrace();
      }
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
   }
   /**
    * Fetches a Vozdilo object defined by id.
    * @param id as Integral
    * @return object Vozidlo queried from db by provided id or NULL, as well as HTTP response code:
    * If succes: HTTP code 200.
    * If vozidlo defined by id doesn't exist API returns NULL and HTTP code 500.
    * If id provided doesn't fit requirements returns NULL and HTTP code 406.
    */
   public ResponseEntity<Vozidla> getById(Integer id) {
       if (vozidloDao.findById(id).isPresent()){
          return new ResponseEntity<>(vozidloDao.findById(id).get(), HttpStatus.OK);
      } else {
          Vozidla errorVozidlo = new Vozidla( );
          errorVozidlo.setBrand("error");
          errorVozidlo.setModel("no id found");
          return new ResponseEntity<>(errorVozidlo, HttpStatus.CONFLICT);
       }
      
   }
   /**
    * Inserts instance of Vozidla into datatabase using DAO.
    * @param vozidlo
    * @return message and HTTP code:
    * If succesfull: "Vozidlo succesfully added" and HTTP code 200.
    * Else: "Vozidlo could not be added" and HTTP code 400.
    */
   public ResponseEntity<String> addVozidlo(Vozidla vozidlo) {
      try{
         vozidloDao.save(vozidlo);
         return new ResponseEntity<>(
               "Vozidlo succesfully added", HttpStatusCode.valueOf(200));
      }catch (Exception e){
         e.printStackTrace();
      }
      return new ResponseEntity<>(
            "Vozidlo could not be added", HttpStatus.BAD_REQUEST);
   }
   /**
    * Create new instance of Vozidla, then insert it to database using DAO.
    * @param brand required string
    * @param model required string
    * @param available required boolean
    * @return Message and HTTP code:
    * If success: "Vozidlo successfully created and inserted to db." and HTTP code 200.
    * If either brand,model or available is NULL: Brand, model and available needs to be provided." and HTTP code 406
    * If any other error appears: "Vozidlo created, ERROR while inserting to db." and HTTP code 500.
    */
   public ResponseEntity<String> createVozidlo(String brand, String model, Boolean available) {
      if((brand.isBlank()||brand.isEmpty())&&(model.isBlank()||model.isEmpty())&&available!=null){
         return new ResponseEntity<>("Brand, model and available needs to be provided.", HttpStatusCode.valueOf(406)); // not accepted
      }else {
         try{
            Vozidla newVozidlo = new Vozidla(brand, model, available);
            vozidloDao.save(newVozidlo);
            return new ResponseEntity<>("Vozidlo successfully created and inserted to db.", HttpStatusCode.valueOf(200));
         }catch( Exception e){e.printStackTrace();}
      }
      return new ResponseEntity<>("Vozidlo created, ERROR while inserting to db.",HttpStatusCode.valueOf(500));
   }
   /**
    * Deletes a Vozidla instance of specified id from database.
    * @param id required
    * @return message and HTTP code:
    * If successful "Successfully deleted" and HTTP code 200.
    * If id provided doesn't fit requirements: "Id provided is not correct " and HTTP code 406.
    * If customer defined by id doesn't exist:"Vozidlo could not be deleted" and HTTP code 500.
    */
   public ResponseEntity<String> deleteById(Integer id) {
      if(id<=0||id== null){
         return new ResponseEntity<>("Id provided is not correct ", HttpStatusCode.valueOf(406));
      }
      try   {
         vozidloDao.deleteById(id);
         return new ResponseEntity<>(
               "Succesfuly deleted", HttpStatus.OK);
      }catch ( Exception e){
         e.printStackTrace();
      }
      return new ResponseEntity<>(
            "Vozidlo could not be deleted", HttpStatus.BAD_REQUEST);
   }
   /**
    * Fetches Vozidla instance by id, then alter it by  Vozidla instance provided.
    * Causes alteration of original, keeps the id.
    * @param id of instance to be changed, required
    * @param alteredVozidlo instance by the change is processed, required
    * @return Message and HTTP code:
    * If successful: "Updated successfully." and HTTP code 200.
    * If id provided is not correct: "Provided id is incorrect" and HTTP code 400.
    * If any other error appears: "Unable to find vozidlo to change." and HTTP code 406.
    */
   public ResponseEntity<String> changeVozidloById(Integer id, Vozidla alteredVozidlo) {
      if(id == null || id<= 0){return new ResponseEntity<>("Provided Id is incorrect", HttpStatusCode.valueOf(400));}
      if (vozidloDao.findById(id).isPresent()){
      Vozidla vozidloToChange = vozidloDao.findById(id).get();
      vozidloToChange.setBrand(alteredVozidlo.getBrand());
       vozidloToChange.setModel(alteredVozidlo.getModel());
       vozidloToChange.setAvailable(alteredVozidlo.getAvailable());
       vozidloDao.save(vozidloToChange);
       return new ResponseEntity<>(
             "Vozidlo"+vozidloToChange.getIdVozidlo()+ "Updated successfully.", HttpStatus.OK);
    } else {
       return new ResponseEntity<>(
             "Unable to find vozidlo to change.", HttpStatus.NOT_ACCEPTABLE);
    }
   }
   
  
   
   
}
