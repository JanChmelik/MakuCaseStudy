package com.MakuCase.Chmelik.dao;

import com.MakuCase.Chmelik.models.Zapujcka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ZapujckaDAO extends JpaRepository<Zapujcka, Integer> {
   //
   @Query(value = "SELECT * FROM zapujcka WHERE id_vozidla=:idVozidla" , nativeQuery = true)
   Optional<List<Zapujcka>> findByIdVozidla(Integer idVozidla);
   //
   @Query(value = "SELECT * FROM zapujcka WHERE id_zakaznik=:idZakaznik" , nativeQuery = true)
   Optional<List<Zapujcka>> findByIdZakaznik(Integer idZakaznik);
}
