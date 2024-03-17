package com.MakuCase.Chmelik.services;

import com.MakuCase.Chmelik.dao.ZapujckaDAO;
import com.MakuCase.Chmelik.models.Zapujcka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Zapujcka service managing CRUD tasks.
 */
@Service
public class ZapujckaService {
   /**
    * DAO database handler.
    */
   @Autowired
   ZapujckaDAO zapujckaDao;
   /**
    *  Fetches list of all vozidlo objects from database using DAO.
    * @return List of all objects Zakaznik from database, as well as HTTP response code 200.
    *  If no customers are present in database API returns empty list with HTTP code 500.
    */
   public ResponseEntity<List<Zapujcka>> getAll() {
      try {
         return new ResponseEntity<>(zapujckaDao.findAll(), HttpStatus.OK);
      }catch( Exception e) {e.printStackTrace();}
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
   }
   /**
    * Fetches a Zapujcka object defined by id.
    * @param id of Zapujcka instance as Integral
    * @return object Zapujcka queried from db by provided id or NULL, as well as HTTP response code:
    * If succes: HTTP code 200.
    * If Zapujcka defined by id doesn't exist API returns NULL and HTTP code 500.
    * If id provided doesn't fit requirements returns NULL and HTTP code 406.
    */
   public ResponseEntity<Zapujcka> getById(Integer id) {
      if(id == null || id<=0){
//         return new ResponseEntity<>(null, HttpStatusCode.valueOf(406));
         return ResponseEntity.status(HttpStatusCode.valueOf(406)).body(null);
      }
      try{
         return new ResponseEntity<>(zapujckaDao.findById(id).get(), HttpStatus.OK);
      }catch (Exception e){e.printStackTrace();}
//      return new ResponseEntity<>( null ,HttpStatusCode.valueOf(500));
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
   }
   /**
    * Calls its service to fetch List of Zapujcka defined by Vozidlo id.
    * @param idVozidla of instance of Vozidlo
    * @return List of Zapujcka for specified Vozidla instance and HTTP code:
    * If successfull: List of all Zapujcka for specified Vozidla and HTTP code 200
    * If unsuccessful: Empty list and HTTP code 406.
    */
   public ResponseEntity<List<Zapujcka>> getByVozidlaId(Integer idVozidla) {
      try{
         return new ResponseEntity<>(zapujckaDao.findByIdVozidla(idVozidla).get() , HttpStatus.OK);
      }catch (Exception e){e.printStackTrace();}
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE); //406
   }
   /**
    * Calls its service to fetch List of Zapujcka defined by Vozidlo id.
    * @param idZakaznik of instance of Zakaznik
    * @return List and HTTP code:
    * If successfull: List of Zapujcka for specified Zakaznik and HTTP coe 200.
    * If unsuccessful: Empty list and HTTP code 406.
    */
   public ResponseEntity<List<Zapujcka>> getByZakaznikId(Integer idZakaznik) {
      try{
         return new ResponseEntity<>(zapujckaDao.findByIdZakaznik(idZakaznik).get() , HttpStatus.OK);
      }catch (Exception e){e.printStackTrace();}
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE); //406
   }
   
   /**
    * Calls its service to create new instance of Zapujcka, then insert that to database.
     * @param dateFrom
    * @param dateTo
    * @param idZakaznik
    * @param idVozidla
    * @return Message and HTTP code:
    * If succesfull: "New Zapujcka created" and HTTP code 200.
    * If data missing: "All information must be provided" and HTTP code 406.
    * If other error appears: "Unable to create Zapujcka and insert it to database" and HTTP code 500.
    */
   public ResponseEntity<String> createNewZapujcka(Date dateFrom, Date dateTo, Integer idZakaznik, Integer idVozidla) {
      if(dateFrom==null||dateTo==null||(idVozidla<=0|| idVozidla==null)||(idZakaznik<=0||idZakaznik==null)){
         return new ResponseEntity<>("All information must be provided", HttpStatusCode.valueOf(406));
      }
      Zapujcka newZapujcka = new Zapujcka(dateFrom, dateTo,idVozidla,idZakaznik);
      try{
         zapujckaDao.save(newZapujcka);
         return new ResponseEntity<>("New Zapujcka created", HttpStatus.CREATED);
      }catch (Exception e){ e.printStackTrace();}
      return new ResponseEntity<>("Unable to create Zapujcka and insert it to database", HttpStatus.BAD_REQUEST);
   }
   
 
   
  
   
   
   }


