package com.cg.anurag.b2.imsdrmo.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.anurag.b2.imsdrmo.dao.RawMaterialOrderDAO;
import com.cg.anurag.b2.imsdrmo.dto.Orders;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialOrder;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialSpecs;

@Service
public class RawMaterialOrderService {
@Autowired
RawMaterialOrderDAO rmo;
public void setRmo(RawMaterialOrderDAO rmo) {
	this.rmo = rmo;
}
@Transactional
public RawMaterialOrder placeorder(RawMaterialOrder prmo,RawMaterialSpecs rmspec ) {
	double quavalue=prmo.getQuantityvalue();
	double unitprice=rmspec.getPriceperunit();
	prmo.setTotalprice(unitprice*quavalue);
	prmo.setRawmaterialname(rmspec.getRawmaterialname());
	prmo.setPriceperunit(rmspec.getPriceperunit());
	prmo.setQuantityvalue(prmo.getQuantityvalue());
	prmo.setWarehouseId(rmspec.getWarehouseId());
	prmo.setSupplierId(rmspec.getSupplierId());
	prmo.setManufacturingdate(rmspec.getManufacturingdate());
	prmo.setExpirydate(rmspec.getExpirydate());
	prmo.setDeliverystatus("order ready to ship");
	LocalDate doo = LocalDate.now();
	prmo.setDateoforder(LocalDate.now());
	LocalDate delivery = doo.plusDays(5);
	prmo.setDateofdelivery(delivery);;
return rmo.save(prmo);
}
@Transactional
public  RawMaterialOrder trackrawmaterialorder (int orderId)
{
	return rmo.findById(orderId).get();
}
@Transactional
public Orders getRawMaterialOrder(String supplierId,LocalDate sd,LocalDate ed)
{
	List<RawMaterialOrder> list = rmo.findAllOrdersBySupplierId(supplierId);
	List<RawMaterialOrder> slist=new ArrayList<>();
	for(RawMaterialOrder r : list)
	{
	if(r.getDateoforder().isAfter(sd)&& r.getDateoforder().isBefore(ed))
	{
		slist.add(r);
		
	}
	}
	Orders o=new Orders();
	o.setOrders(slist);
	return o;
	
}
@Transactional
public boolean updaterawmaterialorder(RawMaterialOrder f)
{
	RawMaterialOrder v=rmo.findById(f.getOrderId()).get();
	if(v!=null)
	{
		v.setDeliverystatus(f.getDeliverystatus());
		return true;
	}
	return false;
}

}

