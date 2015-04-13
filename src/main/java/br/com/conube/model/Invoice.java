package br.com.conube.model;

import java.math.BigDecimal;
import java.util.List;

public class Invoice {
	
	private Costumer costumer;
	private List<AppliedTax> taxes;
	private BigDecimal amount;
	
	public Invoice(Costumer costumer, List<AppliedTax> taxes, BigDecimal amount) {
		super();
		this.costumer = costumer;
		this.taxes = taxes;
		this.amount = amount;
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}

	public List<AppliedTax> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<AppliedTax> taxes) {
		this.taxes = taxes;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
