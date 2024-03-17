package com.MakuCase.Chmelik.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Zapujcka {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idZapujcka;
   @DateTimeFormat
   private Date dateFrom;
   @DateTimeFormat
   private Date dateTo;
   private Integer idVozidla;
   private Integer idZakaznik;
   
   public Zapujcka() {
   }
   
   public Zapujcka(Date dateFrom, Date dateTo, Integer idVozidlo, Integer idZakaznik) {
      this.dateFrom = dateFrom;
      this.dateTo = dateTo;
      this.idVozidla = idVozidlo;
      this.idZakaznik = idZakaznik;
   }
   
   public Integer getIdZapujcka() {
      return idZapujcka;
   }
   
   public Date getDateFrom() {
      return dateFrom;
   }
   
   public void setDateFrom(Date dateFrom) {
      this.dateFrom = dateFrom;
   }
   
   public Date getDateTo() {
      return dateTo;
   }
   
   public void setDateTo(Date dateTo) {
      this.dateTo = dateTo;
   }
   
   public Integer getIdVozidla() {
      return idVozidla;
   }
   
   public void setIdVozidla(Integer idVozidla) {
      this.idVozidla = idVozidla;
   }
   
   public Integer getIdZakaznik() {
      return idZakaznik;
   }
   
   public void setIdZakaznik(Integer idZakaznik) {
      this.idZakaznik = idZakaznik;
   }
}
