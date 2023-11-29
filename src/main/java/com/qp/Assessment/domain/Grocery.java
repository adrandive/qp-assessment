package com.qp.Assessment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Grocery")
@NamedQuery(name="Grocery.findAll",query="SELECT g from Grocery g")
public class Grocery {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
	@SequenceGenerator(sequenceName = "ID_SEQ", name = "ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "itemId")
	private long itemId;
	
	@Column(name="itemName")
	private String itemName;
	
	@Column(name="itemPrice")
	private long itemPrice;
	
	@Column(name="itemQuantity")
	private long itemQuantity;
	
	public long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(long itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Grocery(long itemId,String itemName,long itemPrice,long itemQuantity) {
		this.itemId=itemId;
		this.itemName=itemName;
		this.itemPrice=itemPrice;
		this.itemQuantity=itemQuantity;
	}
	
	public Grocery() {
		
	}
}
