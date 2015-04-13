package br.com.conube.model.dao.impl;

import br.com.conube.model.dao.InvoiceDAO;

public class EntityManager {
	
	public static AppliedTaxDAOImpl appliedTaxDAO = new AppliedTaxDAOImpl();
	public static CompanyDAOImpl companyDAO = new CompanyDAOImpl();
	public static CostumerDAOImpl costumerDAO = new CostumerDAOImpl();
	public static InvoiceDAO invoiceDAO = new InvoiceDAOImpl();
	public static TaxDAOImpl taxDAO = new TaxDAOImpl();

}
