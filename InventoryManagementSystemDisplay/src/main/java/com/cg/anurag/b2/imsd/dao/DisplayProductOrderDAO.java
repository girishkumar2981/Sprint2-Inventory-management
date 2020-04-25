package com.cg.anurag.b2.imsd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.anurag.b2.imsd.dto.DisplayProductOrder;

@Repository
public interface DisplayProductOrderDAO extends JpaRepository<DisplayProductOrder,String> {
	public List<DisplayProductOrder> findAllOrdersByDistributorId(String distributorId);
}
