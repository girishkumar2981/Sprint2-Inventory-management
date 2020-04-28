package com.cg.anurag.b2.imsd.controller;

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
import com.cg.anurag.b2.imsd.dto.DisplaySupplier;
import com.cg.anurag.b2.imsd.exception.IdNotFoundException;
import com.cg.anurag.b2.imsd.service.DisplaySupplierService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class DisplaySupplierController {
@Autowired
DisplaySupplierService dss;
public void setDss(DisplaySupplierService dss) {
	this.dss = dss;
}
@GetMapping("/GetSupplierDetail/{supplierId}")
private ResponseEntity<DisplaySupplier> getSupplierDetail(@PathVariable String supplierId) {
	DisplaySupplier d = dss.getSupplierDetails(supplierId);
	if (d == null) {
		throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
	} else {
		return new ResponseEntity<DisplaySupplier>(d, new HttpHeaders(), HttpStatus.OK);
	}
}
@DeleteMapping("/DeleteSupplier/{supplierId}")
private ResponseEntity<String> deleteSupplier(@PathVariable String supplierId)
	{
		DisplaySupplier e = dss.deleteSupplierDetails(supplierId);
		if (e == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Supplier deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
@PutMapping("/UpdateSupplier")
	public ResponseEntity<String> updateSupplier(@RequestBody DisplaySupplier s)
		{
			boolean e = dss.updateSupplierDetails(s);
			if (e == false) {
				throw new IdNotFoundException("Update details Unsuccessful,Provided Id does not exist");
			} else {
				return new ResponseEntity<String>("Supplier data updated successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
@GetMapping("/GetAllSuppliers")
private ResponseEntity<List<DisplaySupplier>> getAllSuppliers() 
    {
	List<DisplaySupplier> supplierlist = dss.getAllSuppliers();
	return new ResponseEntity<List<DisplaySupplier>>(supplierlist, new HttpHeaders(), HttpStatus.OK);
    }
@PostMapping("/addSupplier")
public ResponseEntity<String>addSupplier(@RequestBody DisplaySupplier s )
{
	DisplaySupplier e = dss.addSupplierDetails(s);
	if(e == null)
	{
		throw new IdNotFoundException("Enter Valid Id");
	}
	else {
		return new ResponseEntity<String>("Supplier Details added successfully",new HttpHeaders(),HttpStatus.OK);		
	}
}
}


