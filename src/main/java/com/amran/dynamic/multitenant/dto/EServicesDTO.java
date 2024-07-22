package com.amran.dynamic.multitenant.dto;

import java.io.Serializable;

public class EServicesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Double price;

	private String description;

	private String name;

	private int serviceFrequency;

	private double cost;

	private boolean fixed;

	private boolean isOnline;

	private String gender;

	private int start;

	private int process;

	private int finish;

	private int totalServiceDuration;

	private Long categoryId;

	public EServicesDTO(Long id, Double price, String description, String name, int serviceFrequency, double cost,
			boolean fixed, boolean isOnline, String gender, int start, int process, int finish,
			int totalServiceDuration, Long categoryId) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
		this.name = name;
		this.serviceFrequency = serviceFrequency;
		this.cost = cost;
		this.fixed = fixed;
		this.isOnline = isOnline;
		this.gender = gender;
		this.start = start;
		this.process = process;
		this.finish = finish;
		this.totalServiceDuration = totalServiceDuration;
		this.categoryId = categoryId;
	}
	
	public EServicesDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getServiceFrequency() {
		return serviceFrequency;
	}

	public void setServiceFrequency(int serviceFrequency) {
		this.serviceFrequency = serviceFrequency;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public int getTotalServiceDuration() {
		return totalServiceDuration;
	}

	public void setTotalServiceDuration(int totalServiceDuration) {
		this.totalServiceDuration = totalServiceDuration;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
