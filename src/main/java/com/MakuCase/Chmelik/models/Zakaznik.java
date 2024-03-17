package com.MakuCase.Chmelik.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Zakaznik {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idZakaznik;
   private String name;
   private String email;
   private String phone;
   
   public Zakaznik() {
   }
   
   public Zakaznik( String name, String email, String phone) {
      this.name = name;
      this.email = email;
      this.phone = phone;
   }
   
   public Integer getIdZakaznik() {
      return idZakaznik;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   public String getPhone() {
      return phone;
   }
   
   public void setPhone(String phone) {
      this.phone = phone;
   }
}
