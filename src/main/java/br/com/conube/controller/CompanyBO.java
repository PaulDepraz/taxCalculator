package br.com.conube.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.conube.model.AppliedTax;
import br.com.conube.model.Company;
import br.com.conube.model.Costumer;
import br.com.conube.model.Invoice;
import br.com.conube.model.Tax;
import br.com.conube.model.dao.impl.EntityManager;

public class CompanyBO {
	
	private static Company company;
	
	private final BigDecimal IR_TRESHOLD = new BigDecimal(10);
	private final BigDecimal PCC_TRESHOLD = new BigDecimal(5000);
	

	public CompanyBO(){
		company = new Company("Conube", "18.792.479/0001-01");
	}
	
	public Invoice issueInvoice(Costumer costumer, BigDecimal amount){
		
		List<AppliedTax> taxes = new ArrayList<AppliedTax>(); 

		// Tax logic
		if(amount.compareTo(IR_TRESHOLD)>0){
			Tax ir = EntityManager.taxDAO.searchByName("IR");
			taxes.add(new AppliedTax(ir, amount));
		}
		if(amount.compareTo(PCC_TRESHOLD)>0){
			Tax pis = EntityManager.taxDAO.searchByName("PIS");
			Tax cofins = EntityManager.taxDAO.searchByName("COFINS");
			Tax csll = EntityManager.taxDAO.searchByName("CSLL");
			taxes.add(new AppliedTax(pis, amount));
			taxes.add(new AppliedTax(cofins, amount));
			taxes.add(new AppliedTax(csll, amount));
		}
		
		Invoice i = new Invoice(costumer, taxes, amount);
		EntityManager.invoiceDAO.persist(i);
		return i;
	}
	
	/*
	 * Could have used ENUMs to represent Taxes.
	 * Also could use Reflection to make the sums 
	 * no matter what taxes applied.
	 */
	public Map<String, BigDecimal> printTaxesTotals(){
		
		BigDecimal ir = new BigDecimal(0);
		BigDecimal pis = new BigDecimal(0);
		BigDecimal cofins = new BigDecimal(0);
		BigDecimal csll = new BigDecimal(0);

		for (Invoice i : EntityManager.invoiceDAO.getAll()) {
			for (AppliedTax tax : i.getTaxes()) {
				switch (tax.getName()) {
					case "IR":
						ir = ir.add(tax.getTaxAmount());
						break;
					case "PIS":
						pis = pis.add(tax.getTaxAmount());
						break;
					case "COFINS":
						cofins = cofins.add(tax.getTaxAmount());
						break;
					case "CSLL":
						csll = csll.add(tax.getTaxAmount());
						break;
	
					default:
						break;
				}
			}
		}
		Map<String, BigDecimal> table = new HashMap<String, BigDecimal>();
		table.put("IR", ir);
		table.put("PIS", pis);
		table.put("COFINS", cofins);
		table.put("CSLL", csll);
		return table;
	}
	
	public BigDecimal getTaxesTotal(){
		BigDecimal total = new BigDecimal(0);
		for (Invoice i : EntityManager.invoiceDAO.getAll()) {
			for (AppliedTax tax : i.getTaxes()) {
				total = total.add(tax.getTaxAmount());
			}
		}
		return total;
	}

	public static Company getCompany() {
		return company;
	}

}
