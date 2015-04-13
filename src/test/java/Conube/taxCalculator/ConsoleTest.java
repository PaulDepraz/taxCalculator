package Conube.taxCalculator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;

import br.com.conube.controller.AccountantBO;
import br.com.conube.controller.CompanyBO;
import br.com.conube.model.AppliedTax;
import br.com.conube.model.Costumer;
import br.com.conube.model.Invoice;
import br.com.conube.model.Tax;

public class ConsoleTest {

	public static void main(String[] args) {
		CompanyBO companyBO = new CompanyBO();
		AccountantBO accountantBO = new AccountantBO();

		Tax irMock = new Tax("IR", 5.0);
		Tax pisMock = new Tax("PIS", 0.65);
		Tax cofinsMock = new Tax("COFINS", 3.0);
		Tax csllMock = new Tax("CSLL", 1.0);

		accountantBO.addTax(irMock);
		accountantBO.addTax(pisMock);
		accountantBO.addTax(cofinsMock);
		accountantBO.addTax(csllMock);

		Costumer costumerMock0 = new Costumer("Jean Paul Depraz",
				"338.253.618-88");
		Costumer costumerMock1 = new Costumer("Deprazz Serv. em Informatica",
				"20.594.395/0001­05");

		Invoice i0 = companyBO.issueInvoice(costumerMock0,
				new BigDecimal(150.0));
		Invoice i1 = companyBO.issueInvoice(costumerMock1, new BigDecimal(
				10000.0));
		Invoice i2 = companyBO.issueInvoice(costumerMock1, new BigDecimal(
				158300.58));

		System.out.println(i0.getCostumer().getName());
		for (AppliedTax tax : i0.getTaxes()) {
			System.out.println(tax.getName() +": "+ tax.getTaxAmount());
		}
		System.out.println();
		System.out.println(i1.getCostumer().getName());
		for (AppliedTax tax : i1.getTaxes()) {
			System.out.println(tax.getName() +": "+ tax.getTaxAmount());
		}
		System.out.println();
		System.out.println(i2.getCostumer().getName());
		for (AppliedTax tax : i2.getTaxes()) {
			System.out.println(tax.getName() +": "+ tax.getTaxAmount());
		}

		System.out.println();
		System.out.println("Total by Tax:");
		Map<String, BigDecimal> totals = companyBO.printTaxesTotals();
		for (String key : totals.keySet()) {
			System.out.println(key +": "+totals.get(key).doubleValue());
		}
		accountantBO.changeTaxRate("IR", 7.5);
		BigDecimal total = companyBO.getTaxesTotal();
		System.out.println();
		System.out.println("Total amout withheld: "
				+ NumberFormat.getCurrencyInstance()
						.format(total.doubleValue()));
	}

}
