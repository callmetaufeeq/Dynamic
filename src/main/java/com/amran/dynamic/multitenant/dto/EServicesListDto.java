package com.amran.dynamic.multitenant.dto;

public class EServicesListDto {

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

	private CategoriesDto categoriesDto;

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

	public CategoriesDto getCategoriesDto() {
		return categoriesDto;
	}

	public void setCategoriesDto(CategoriesDto categoriesDto) {
		this.categoriesDto = categoriesDto;
	}

}
