package com.globallogic.demo.api.users.jsons;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phones {
	
	@JsonProperty("number")
	@NotBlank(message = "number is required")
	private String number;
	
	@JsonProperty("citycode")
	@NotBlank(message = "city code is required")
	private String cityCode;
	
	@JsonProperty("countrycode")
	@NotBlank(message = "country code is required")
	private String countryCode;

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

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Phones [number=" + number + ", cityCode=" + cityCode + ", countryCode=" + countryCode + "]";
	}
	
}
