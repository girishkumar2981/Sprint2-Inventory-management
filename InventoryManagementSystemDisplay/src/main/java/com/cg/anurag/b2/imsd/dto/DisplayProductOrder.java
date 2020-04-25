package com.cg.anurag.b2.imsd.dto;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name="displayproductorder")
@XmlRootElement

public class DisplayProductOrder {
	
		@Id
		@Column(name="orderid")
		String orderId;
		@Column(name="productname")
		String productname;
		@Column(name="distributorid")
		String distributorId;
		@Column(name="quantityvalue")
		double quantityvalue;
		@Column(name="quantityunit")
		double quantityunit;
		@Column(name="dateoforder")
		Date dateoforder;
		@Column(name="dateofdelivery")
		Date dateofdelivery;
		@Column(name="priceperunit")
		double priceperunit;
		@Column(name="totalprice")
		double totalprice;
		@Column(name="deliverystatus")
		String deliverystatus;
		@Column(name="warehouseid")
		String warehouseId;
		public DisplayProductOrder() {}
		public DisplayProductOrder(String orderId, String productname, String distributorId, double quantityvalue,
				double quantityunit, Date dateoforder, Date dateofdelivery, double priceperunit, double totalprice,
				String deliverystatus, String warehouseId) {
			this.orderId = orderId;
			this.productname = productname;
			this.distributorId = distributorId;
			this.quantityvalue = quantityvalue;
			this.quantityunit = quantityunit;
			this.dateoforder = dateoforder;
			this.dateofdelivery = dateofdelivery;
			this.priceperunit = priceperunit;
			this.totalprice = totalprice;
			this.deliverystatus = deliverystatus;
			this.warehouseId = warehouseId;
		}
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public String getProductname() {
			return productname;
		}
		public void setProductname(String productname) {
			this.productname = productname;
		}
		public String getDistributorId() {
			return distributorId;
		}
		public void setDistributorId(String distributorId) {
			this.distributorId = distributorId;
		}
		public double getQuantityvalue() {
			return quantityvalue;
		}
		public void setQuantityvalue(double quantityvalue) {
			this.quantityvalue = quantityvalue;
		}
		public double getQuantityunit() {
			return quantityunit;
		}
		public void setQuantityunit(double quantityunit) {
			this.quantityunit = quantityunit;
		}
		public Date getDateoforder() {
			return dateoforder;
		}
		public void setDateoforder(Date dateoforder) {
			this.dateoforder = dateoforder;
		}
		public Date getDateofdelivery() {
			return dateofdelivery;
		}
		public void setDateofdelivery(Date dateofdelivery) {
			this.dateofdelivery = dateofdelivery;
		}
		public double getPriceperunit() {
			return priceperunit;
		}
		public void setPriceperunit(double priceperunit) {
			this.priceperunit = priceperunit;
		}
		public double getTotalprice() {
			return totalprice;
		}
		public void setTotalprice(double totalprice) {
			this.totalprice = totalprice;
		}
		public String getDeliverystatus() {
			return deliverystatus;
		}
		public void setDeliverystatus(String deliverystatus) {
			this.deliverystatus = deliverystatus;
		}
		public String getWarehouseId() {
			return warehouseId;
		}
		public void setWarehouseId(String warehouseId) {
			this.warehouseId = warehouseId;
		}
		
		}