package com.amran.dynamic.multitenant.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


public class CategoriesDto implements Serializable {
   
	private static final long serialVersionUID = 1L;
    
	private Long id;

    private String color;

    private Integer order;

    private String featured;

	private Long parentId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String name;

    private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getFeatured() {
		return featured;
	}

	public void setFeatured(String featured) {
		this.featured = featured;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoriesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoriesDto(Long id, String color, Integer order, String featured, Long parentId, LocalDateTime createdAt,
			LocalDateTime updatedAt, String name, String description) {
		super();
		this.id = id;
		this.color = color;
		this.order = order;
		this.featured = featured;
		this.parentId = parentId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.description = description;
	}
    
   
}
