package br.com.conube.model;

public class Costumer {
	
	private String name;
	private String socialId;
	
	public Costumer(String name, String socialId) {
		super();
		this.name = name;
		this.socialId = socialId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSocialId() {
		return socialId;
	}
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

}
