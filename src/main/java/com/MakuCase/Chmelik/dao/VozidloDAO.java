package com.MakuCase.Chmelik.dao;

import com.MakuCase.Chmelik.models.Vozidla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VozidloDAO extends JpaRepository<Vozidla, Integer> {
   @Query(value = "SELECT * FROM vozidla v WHERE available = true", nativeQuery = true)
   List<Vozidla> findAllByAvailable();
}
