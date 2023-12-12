package com.rent.tool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Rent2")
public class ToolEntity {
	

	@EmbeddedId
	ToolEmbId ToolEmbId;
	
	@Column(name = "MOBILE_NO" )
	private String mobileNo;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "ITEM-NAME")
	private String itemName;
	
	@Column(name = "QUANTITY")
	private String quantity;
	
	@Column(name = "AMOUNT")
	private String amount;
	
	@Column(name = "DELIVERD-DATE")
	private String deliverdDate;
	
	@Column(name = "RETURN-DATE")
	private String returnDate;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "NN")
	private String nn;
	
	
	
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getNn() {
		return nn;
	}

	public void setNn(String nn) {
		this.nn = nn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDeliverdDate() {
		return deliverdDate;
	}

	public void setDeliverdDate(String deliverdDate) {
		this.deliverdDate = deliverdDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ToolEmbId getToolEmbId() {
		return ToolEmbId;
	}

	public void setToolEmbId(ToolEmbId ToolEmbId) {
		this.ToolEmbId = ToolEmbId;
	}

	public ToolEntity(ToolEmbId ToolEmbId, String address, String itemName, String quantity, String amount,
			String deliverdDate, String returnDate, String status, String nn, String id, String name, String mobileNo) {
		super();
		this.ToolEmbId = ToolEmbId;
		this.mobileNo = mobileNo;
		this.address = address;
		this.itemName = itemName;
		this.quantity = quantity;
		this.amount = amount;
		this.deliverdDate = deliverdDate;
		this.returnDate = returnDate;
		this.status = status;
		this.nn = nn;
		
	}
	
	public ToolEntity() {
		super();
	}
}
