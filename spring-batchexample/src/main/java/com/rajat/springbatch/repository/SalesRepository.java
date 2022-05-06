package com.rajat.springbatch.repository;

import com.rajat.springbatch.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales,Long> {
}
