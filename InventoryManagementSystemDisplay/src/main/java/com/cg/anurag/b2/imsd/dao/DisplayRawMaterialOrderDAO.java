package com.cg.anurag.b2.imsd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.anurag.b2.imsd.dto.DisplayRawMaterialOrder;
@Repository
public interface DisplayRawMaterialOrderDAO extends JpaRepository<DisplayRawMaterialOrder,String> {

	public List<DisplayRawMaterialOrder> findAllOrdersBySupplierId(String supplierId);
}
