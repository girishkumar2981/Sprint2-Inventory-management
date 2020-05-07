package com.cg.anurag.b2.imsdd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.anurag.b2.imsdd.dto.DisplayDistributor;
import com.cg.anurag.b2.imsdd.exception.IdNotFoundException;
import com.cg.anurag.b2.imsdd.service.DisplayDistributorService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class DisplayDistributorController {
@Autowired
DisplayDistributorService ddss;
public void setDds(DisplayDistributorService ddss) {
	this.ddss = ddss;
}
@GetMapping("/GetDistributorDetail/{distributorId}")
private ResponseEntity<DisplayDistributor> getDistributorDetail(@PathVariable String distributorId) {
	DisplayDistributor d = ddss.getDistributorDetails(distributorId);
	if (d == null) {
		throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
	} else {
		return new ResponseEntity<DisplayDistributor>(d, new HttpHeaders(), HttpStatus.OK);
	}
}
@DeleteMapping("/DeleteDistributor/{distributorId}")
private ResponseEntity<String> deleteDistributor(@PathVariable String distributorId)
	{
		DisplayDistributor e = ddss.deleteDistributorDetails(distributorId);
		if (e == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Distributor deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
@PutMapping("/UpdateDistributor")
	public ResponseEntity<String> updateDistributor(@RequestBody DisplayDistributor d)
		{
			boolean e = ddss.updateDistributorDetails(d);
			if (e==false) {
				throw new IdNotFoundException("Update details Unsuccessful,Provided Id does not exist");
			} else {
				return new ResponseEntity<String>("Distributor data updated successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
@GetMapping("/GetAllDistributors")
private ResponseEntity<List<DisplayDistributor>> getAllDistributors() 
    {
	List<DisplayDistributor> distributorlist = ddss.getAllDistributors();
	return new ResponseEntity<List<DisplayDistributor>>(distributorlist, new HttpHeaders(), HttpStatus.OK);
    }
@PostMapping("/addDistributor")
public ResponseEntity<String>addDistributor(@RequestBody DisplayDistributor d )
{
	DisplayDistributor e = ddss.addDistributorDetails(d);
	if(e == null)
	{
		throw new IdNotFoundException("Enter Valid Id");
	}
	else {
		return new ResponseEntity<String>("Distributor Details added successfully",new HttpHeaders(),HttpStatus.OK);		
	}
}
}
