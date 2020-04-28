package com.cg.anurag.b2.imsd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.anurag.b2.imsd.dao.DisplayProductOrderDAO;
import com.cg.anurag.b2.imsd.dao.DisplayRawMaterialOrderDAO;
import com.cg.anurag.b2.imsd.dto.DisplayProductOrder;
import com.cg.anurag.b2.imsd.dto.DisplayRawMaterialOrder;
import com.cg.anurag.b2.imsd.dto.Orders;
import com.cg.anurag.b2.imsd.dto.ProductOrders;

@Service
public class DisplayOrderService {
	@Autowired
	DisplayRawMaterialOrderDAO rdao;
	@Autowired
	DisplayProductOrderDAO pdao;
	
	public void setRdao(DisplayRawMaterialOrderDAO rdao) {
		this.rdao = rdao;
	}
	
	public void setPdao(DisplayProductOrderDAO pdao) {
		this.pdao = pdao;
	}
	@Transactional
	public Orders getRawMaterialOrder(String supplierId,String deliverystatus,Date startDate,Date endDate)
	{
		List<DisplayRawMaterialOrder> list = rdao.findAllOrdersBySupplierId(supplierId);
		List<DisplayRawMaterialOrder> slist=new ArrayList<>();
		for(DisplayRawMaterialOrder r : list)
		{
		if(r.getDateoforder().after(startDate)&& r.getDateoforder().before(endDate) && r.getDeliverystatus().equalsIgnoreCase(deliverystatus))
		{
			slist.add(r);
			
		}
		}
		Orders o=new Orders();
		o.setOrders(slist);
		return o;
		
	}
	@Transactional
	public ProductOrders getProductOrder(String distributorId,String deliverystatus,Date startDate,Date endDate)
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
		ProductOrders op=new ProductOrders();
		op.setProductorders(blist);
		return op;
		
	}
}
	
	

