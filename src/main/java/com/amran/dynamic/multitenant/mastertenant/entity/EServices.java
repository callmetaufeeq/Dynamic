package com.amran.dynamic.multitenant.mastertenant.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Accessors(chain = true)
@Table(name = "e_services")
public class EServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Lob
    private String description;

    @Lob
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(name = "service_frequency")
    private int serviceFrequency;

    @Column(name = "cost")
    private double cost;

    @Column(name = "fixed")
    private boolean fixed;

    @Column(name = "is_online")
    private boolean isOnline;

    @Column(name = "gender")
    private String gender;

    @Column(name = "start")
    private int start;

    @Column(name = "process")
    private int process;

    @Column(name = "finish")
    private int finish;

    @Column(length = 16)
    private int totalServiceDuration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "category_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
    private Categories categories;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}
    
    
}
