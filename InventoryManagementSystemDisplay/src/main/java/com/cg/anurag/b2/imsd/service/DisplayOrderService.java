package com.cg.anurag.b2.imsd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.anurag.b2.imsd.dao.DisplayDistributorDAO;
import com.cg.anurag.b2.imsd.dao.DisplayProductOrderDAO;
import com.cg.anurag.b2.imsd.dao.DisplayRawMaterialOrderDAO;
import com.cg.anurag.b2.imsd.dao.DisplaySupplierDAO;
import com.cg.anurag.b2.imsd.dto.DisplayDistributor;
import com.cg.anurag.b2.imsd.dto.DisplayProductOrder;
import com.cg.anurag.b2.imsd.dto.DisplayRawMaterialOrder;
import com.cg.anurag.b2.imsd.dto.DisplaySupplier;
import com.cg.anurag.b2.imsd.dto.Orders;

@Service
public class DisplayOrderService {
	@Autowired
	DisplayRawMaterialOrderDAO rdao;
	@Autowired
	DisplayProductOrderDAO pdao;
	@Autowired
	DisplaySupplierDAO sdao;
	@Autowired
	DisplayDistributorDAO ddao;
	
	public void setRdao(DisplayRawMaterialOrderDAO rdao) {
		this.rdao = rdao;
	}
	
	public void setPdao(DisplayProductOrderDAO pdao) {
		this.pdao = pdao;
	}
	
	public void setSdao(DisplaySupplierDAO sdao) {
		this.sdao = sdao;
	}
	public void setDdao(DisplayDistributorDAO ddao) {
		this.ddao = ddao;
	}
	@Transactional
	public DisplaySupplier getSupplierDetails(String supplierId)
	{
		return sdao.findById(supplierId).get();
	}
	@Transactional
	public DisplayDistributor getDistributorDetails(String distributorId)
	{
		return ddao.findById(distributorId).get();
	}
	@Transactional
	public Orders getRawMaterialOrder(String supplierId,String deliverystatus,Date startDate,Date endDate)
	{
		List<DisplayRawMaterialOrder> list = rdao.findAllOrdersBySupplierId(supplierId);
		List<DisplayRawMaterialOrder> slist=new ArrayList<>();
		for(DisplayRawMaterialOrder r : list)
		{
		//System.out.println((r.getDateoforder().after(startDate)+" "+r.getDateoforder().before(endDate)));
		if(r.getDateoforder().after(startDate)&& r.getDateoforder().before(endDate) && r.getDeliverystatus().equalsIgnoreCase(deliverystatus))
		{
			slist.add(r);
			//System.out.println("Hi");
		}
		}
		/*for(DisplayRawMaterialOrder d:slist)
		{
			System.out.println(d.getRawmaterialname());
		}*/
		Orders o=new Orders();
		o.setOrders(slist);
		return o;
		
	}
	@Transactional
	public Orders getProductOrder(String distributorId,String deliverystatus,Date startDate,Date endDate)
	{
		List<DisplayProductOrder> alist = pdao.findAllOrdersByDistributorId(distributorId);
		List<DisplayProductOrder> blist=new ArrayList<>();
		for(DisplayProductOrder p : alist)
		{
		if(p.getDateoforder().after(startDate)&&p.getDateoforder().before(endDate)&&(p.getDeliverystatus().equalsIgnoreCase(deliverystatus)))
		{
			blist.add(p);
		}
		}
		Orders op=new Orders();
		//op.setProductorders(blist);
		return op;
		
	}
}
	
	

