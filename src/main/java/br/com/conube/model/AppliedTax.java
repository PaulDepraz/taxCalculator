package br.com.conube.model;

import java.math.BigDecimal;

public class AppliedTax extends Tax {

	private BigDecimal taxAmount;
	
	public AppliedTax(Tax tax, BigDecimal invoiceAmount) {
		super(tax.getName(), tax.getRate());
		this.taxAmount = invoiceAmount.multiply(BigDecimal.valueOf(tax.getRate()/100));
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
}
