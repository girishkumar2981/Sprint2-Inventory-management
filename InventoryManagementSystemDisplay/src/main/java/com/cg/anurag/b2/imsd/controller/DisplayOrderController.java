package com.cg.anurag.b2.imsd.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.anurag.b2.imsd.dto.DisplayDistributor;
import com.cg.anurag.b2.imsd.dto.DisplaySupplier;
import com.cg.anurag.b2.imsd.dto.Orders;
import com.cg.anurag.b2.imsd.service.DisplayOrderService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class DisplayOrderController {
@Autowired
DisplayOrderService dos;
public void setDos(DisplayOrderService dos) {
	this.dos = dos;
}
@ExceptionHandler(value = NoSuchElementException.class)
public ResponseEntity<String> noSuchElementException()
{
	return new ResponseEntity<String>("order does not exist",HttpStatus.NOT_FOUND);
}
@GetMapping(value="/getsupplierdetails/supplierid/{supplierId}",produces= {"application/json","application/xml"})
public DisplaySupplier getsupplierdetails1(@PathVariable String supplierId)
{
	return dos.getSupplierDetails(supplierId);
}
@GetMapping(value="/getdistributordetails/distributorid/{distributorId}",produces= {"application/json","application/xml"})
public DisplayDistributor getdistributordetails1(@PathVariable String distributorId)
{
	return dos.getDistributorDetails(distributorId);
}
@GetMapping(value="/getrawmaterialorder/supplierid/{supplierId}/deliverystatus/{deliverystatus}/startDate/{startDate}/endDate/{endDate}",produces= {"application/json","application/xml"})
public ResponseEntity<Orders> getRawMaterialOrder(@PathVariable String supplierId,@PathVariable String deliverystatus,@PathVariable String startDate,@PathVariable String endDate)throws ParseException
{
	Date sd = new SimpleDateFormat("yyyy-mm-dd").parse(startDate);
	Date ed = new SimpleDateFormat("yyyy-mm-dd").parse(endDate);
	Orders oo=dos.getRawMaterialOrder(supplierId, deliverystatus, sd, ed);
	if(oo!=null)
		return new ResponseEntity<>(oo,HttpStatus.OK);
	else
		return new ResponseEntity("Not successful",HttpStatus.NOT_FOUND);
}
@GetMapping(value="/getproductorder/distributorid/{distributorId}/deliverystatus/{deliverystatus}/startDate/{startDate}/endDate/{endDate}",produces= {"application/json","application/xml"})
public ResponseEntity<Orders> getProductOrder(@PathVariable String distributorId,@PathVariable String deliverystatus,@PathVariable String startDate,@PathVariable String endDate)throws ParseException
{
	Date ssd = new SimpleDateFormat("yyyy-mm-dd").parse(startDate);
	Date eed = new SimpleDateFormat("yyyy-mm-dd").parse(endDate);
	Orders ooo=dos.getProductOrder(distributorId, deliverystatus, ssd, eed);
	if(ooo!=null)
		return new ResponseEntity<>(ooo,HttpStatus.OK);
	else
		return new ResponseEntity("Not successful",HttpStatus.NOT_FOUND);
}
}
