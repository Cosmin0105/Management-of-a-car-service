package com.serviceproject.web.repository;

import com.serviceproject.web.models.Autoturisme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutoturismeRepository extends JpaRepository<Autoturisme, Long> {

Optional<Autoturisme> findByMarca(String url);
@Query("SELECT a FROM Autoturisme a WHERE a.marca LIKE CONCAT('%', :query, '%')\n")
List<Autoturisme> searchAutoturisme(String query);

}
