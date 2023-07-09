package com.example.PecetCalc.api;

import com.example.PecetCalc.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT * FROM invoices WHERE name = :name", nativeQuery = true)
    Page<Invoice> findAll(Pageable pageable, @Param("name") String name);
}
