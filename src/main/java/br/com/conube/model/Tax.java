package br.com.conube.model;


public class Tax {
	
	private String name;
	private Double rate;
	
	public Tax(String name, Double rate) {
		super();
		this.name = name;
		this.rate = rate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
}
