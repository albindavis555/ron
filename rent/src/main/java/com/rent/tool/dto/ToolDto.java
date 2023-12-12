package com.rent.tool.dto;
,

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class ToolDto {
	
	
	private String id;
	
	@NotEmpty
	private String name;
	
	private String address;
	
	@NotEmpty(message = "{NotEmpty.toolDto.mobileNo}")
	@Size(min = 10 , max = 10,message = "{Size.toolDto.mobileNo}")
	private String mobileNo;
	
	private String itemName;
	
	private String quantity;
	
	private String amount;
	
	private String deliverdDate;
	
	private String returnDate;
	
	private String status;

	private String nn;

	
	public String getNn() {
		return nn;
	}

	public void setNn(String nn) {
		this.nn = nn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String addresss) {
		this.address = addresss;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getItemName() {
		return itemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	
	public ToolDto(String name, String address, String mobileNo,String itemName, String quantity, String amount, String deliverdDate,
			String returnDate, String status ,String id, String nn) {
		super();
		this.name = name;
		this.address = address;
		this.mobileNo = mobileNo;
		this.itemName = itemName;
		this.quantity = quantity;
		this.amount = amount;
		this.deliverdDate = deliverdDate;
		this.returnDate = returnDate;
		this.status = status;
		this.id = id;
		this.nn = nn;
		
	}

	public ToolDto() {
		super();
	}

}
