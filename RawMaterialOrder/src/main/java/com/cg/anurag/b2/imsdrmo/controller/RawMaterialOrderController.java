package com.cg.anurag.b2.imsdrmo.controller;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cg.anurag.b2.imsdrmo.dto.Orders;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialOrder;
import com.cg.anurag.b2.imsdrmo.dto.RawMaterialSpecs;
import com.cg.anurag.b2.imsdrmo.exception.IdNotFoundException;
import com.cg.anurag.b2.imsdrmo.exception.UnsuccessfullOrder;
import com.cg.anurag.b2.imsdrmo.service.RawMaterialOrderService;

@RestController
@CrossOrigin("http://localhost:4200")
public class RawMaterialOrderController {
@Autowired
RawMaterialOrderService rmos;
public void setRmos(RawMaterialOrderService rmos) {
	this.rmos = rmos;
}
@Autowired
RestTemplate restTemplate;
public void setRestTemplate( RestTemplate  restTemplate)
{
	this.restTemplate= restTemplate;
}

@Bean
public  RestTemplate restTemplate()
{
	return new RestTemplate();
}
@GetMapping(value="/getrawmaterialorder/supplierid/{supplierId}/startDate/{startDate}/endDate/{endDate}",produces= {"application/json","application/xml"})
public ResponseEntity<Orders> getRawMaterialOrder(@PathVariable String supplierId,@PathVariable String startDate,@PathVariable String endDate)throws ParseException
{
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate sd = LocalDate.parse(startDate, formatter);
	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate ed=LocalDate.parse(endDate,formatter1);
	Orders oo=rmos.getRawMaterialOrder(supplierId, sd, ed);
	if(oo!=null)
		return new ResponseEntity<>(oo,HttpStatus.OK);
	else
		return new ResponseEntity("Not successful",HttpStatus.NOT_FOUND);
}
@PostMapping("/placeorder")
public ResponseEntity<String> placeorder(@RequestBody RawMaterialOrder rmo)
{
	RawMaterialSpecs sour=restTemplate.getForObject("http://localhost:8095/GetAllRawMaterialSpecs/",RawMaterialSpecs.class);
	if(sour==null)
	{
		throw new UnsuccessfullOrder("unsuccesful in placing order");
	}
	else
	{
		RawMaterialOrder e=rmos.placeorder(rmo,sour);
		if(e!=null)
		{
		ResponseEntity<String>responseEntity = new ResponseEntity<String>("Succefully ordered", HttpStatus.OK);
		return responseEntity;
		}
		else
		{
			ResponseEntity<String>responseEntity1 = new ResponseEntity<String>("Not Succefully ordered", HttpStatus.NO_CONTENT);
			return responseEntity1;
		}
	}
	
}
@GetMapping("/trackorder/{orderId}")
private ResponseEntity<RawMaterialOrder> getorder(@PathVariable int orderId) {
	RawMaterialOrder d = rmos.trackrawmaterialorder(orderId);
	if (d == null) {
		throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
	} else {
		return new ResponseEntity<RawMaterialOrder>(d, new HttpHeaders(), HttpStatus.OK);
	}
}
@PutMapping("/Updatedeliverystatus")
public ResponseEntity<String> updateorder(@RequestBody RawMaterialOrder f)
	{
		boolean e = rmos.updaterawmaterialorder(f);
		if (e==false) {
			throw new IdNotFoundException("Update details Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("delivery status updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
}