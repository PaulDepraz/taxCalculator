package br.com.conube.controller;

import br.com.conube.model.Tax;
import br.com.conube.model.dao.impl.EntityManager;

public class AccountantBO {
	
	public void changeTaxRate(String taxName, Double newRate){
		Tax tax = EntityManager.taxDAO.searchByName(taxName);
		tax.setRate(newRate);
		EntityManager.taxDAO.persist(tax);
	}
	
	public void addTax(Tax tax){
		EntityManager.taxDAO.persist(tax);
	}
	
	public Double getTaxRate(String name){
		return EntityManager.taxDAO.searchByName(name).getRate();
	}

}
