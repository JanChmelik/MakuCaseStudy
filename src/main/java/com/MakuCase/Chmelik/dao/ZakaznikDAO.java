package com.MakuCase.Chmelik.dao;

import com.MakuCase.Chmelik.models.Zakaznik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ZakaznikDAO extends JpaRepository<Zakaznik, Integer> {
   @Query(value = "SELECT * FROM zakaznik WHERE email=:email",nativeQuery = true)
   Zakaznik findByEmail(String email);
//
   @Query(value = "SELECT * FROM zakaznik WHERE phone=:phone",nativeQuery = true)
   Zakaznik findByPhone(String phone);
}
