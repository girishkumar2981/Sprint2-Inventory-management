package com.cg.anurag.b2.imsd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.anurag.b2.imsd.dto.DisplaySupplier;

@Repository
public interface DisplaySupplierDAO extends JpaRepository<DisplaySupplier,String> {

}
