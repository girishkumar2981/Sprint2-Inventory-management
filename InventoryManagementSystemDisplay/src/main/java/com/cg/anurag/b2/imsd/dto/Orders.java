package com.cg.anurag.b2.imsd.dto;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement
public class Orders {
private List<DisplayRawMaterialOrder> orders;
public Orders( ) {}
public Orders(List<DisplayRawMaterialOrder> orders) {
	
	this.orders = orders;
}
public List<DisplayRawMaterialOrder> getOrders() {
	return orders;
}
public void setOrders(List<DisplayRawMaterialOrder> orders) {
	this.orders = orders;
}


}
