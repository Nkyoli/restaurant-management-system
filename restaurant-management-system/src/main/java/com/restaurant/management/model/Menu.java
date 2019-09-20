package com.restaurant.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "price")
	@NumberFormat(style = Style.CURRENCY)
	private Double price;
	
	@Column(name = "img_location")
	private String imgLocation;
	
	@Column(name = "calories")
	private Integer calories;
	
	@Transient
	private Integer quantity;
	
	@Transient
	@NumberFormat(style = Style.CURRENCY)
	private Double totalPrice;
	
	public Menu() {}
	
	public Menu(String id, String name, String type, Double price, Integer calories, String imgLocation, Integer quantity) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.calories = calories;
		this.imgLocation = imgLocation;
		this.quantity = quantity;
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public String getImgLocation() {
		return imgLocation;
	}

	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice() {
		this.totalPrice = this.price * this.quantity;
	}
}
