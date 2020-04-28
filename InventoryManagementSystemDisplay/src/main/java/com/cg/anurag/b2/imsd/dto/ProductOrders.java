package com.cg.anurag.b2.imsd.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement
public class ProductOrders {
private List<DisplayProductOrder> productorders;	

public void setProductorders(List<DisplayProductOrder> productorders) {
	this.productorders = productorders;
}
public ProductOrders ( ) {}
public List<DisplayProductOrder> getProductorders() {
	return productorders;
}
public ProductOrders(List<DisplayProductOrder> productorders) {
		this.productorders = productorders;
	}


}
