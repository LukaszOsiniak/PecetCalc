package com.example.PecetCalc.api;

import com.example.PecetCalc.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
