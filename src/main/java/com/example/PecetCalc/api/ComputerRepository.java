package com.example.PecetCalc.api;

import com.example.PecetCalc.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    @Query(value = "SELECT * FROM computers WHERE name = :name", nativeQuery = true)
    Page<Computer> findAll(Pageable pageable, @Param("name") String name);
}
