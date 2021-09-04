package com.globallogic.demo.api.users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PHONES")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",unique=true,nullable=false)
	private long id;

	@Column(name="NUMBER")
	private String number;
	
	@Column(name="CITY_CODE")
	private String cityCode;
	

	@Column(name="COUNTRY_CODE")
	private String countryCode;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getCityCode() {
		return cityCode;
	}


	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countycode) {
		this.countryCode = countycode;
	}
	
	
}
