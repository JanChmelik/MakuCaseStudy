package com.MakuCase.Chmelik.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vozidla {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idVozidlo;
   private String brand;
   private String model;
   private Boolean available;
   
   public Vozidla() {
   }
   
   public Vozidla(String brand, String model, Boolean available) {
      this.brand = brand;
      this.model = model;
      this.available = available;
   }
   
   public Integer getIdVozidlo() {
      return idVozidlo;
   }
   
   public Boolean getAvailable() {
      return available;
   }
   
   public void setAvailable(Boolean available) {
      this.available = available;
   }
   
   public String getModel() {
      return model;
   }
   
   public void setModel(String model) {
      this.model = model;
   }
   
   public String getBrand() {
      return brand;
   }
   
   public void setBrand(String brand) {
      this.brand = brand;
   }
}
