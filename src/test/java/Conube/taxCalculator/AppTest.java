package Conube.taxCalculator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.conube.controller.AccountantBO;
import br.com.conube.controller.CompanyBO;
import br.com.conube.model.Costumer;
import br.com.conube.model.Tax;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest{
	
	CompanyBO companyBO = new CompanyBO();
	AccountantBO accountantBO = new AccountantBO();
	
    /**
     * Sets test up and mock.
     */
    @Before
    public void setUpTest(){
    	
        MockitoAnnotations.initMocks(this);
        
        Tax irMock = new Tax("IR", 5.0);
        Tax pisMock = new Tax("PIS", 0.65);
        Tax cofinsMock = new Tax("COFINS", 3.0);
        Tax csllMock = new Tax("CSLL", 1.0);
        
        accountantBO.addTax(irMock);
        accountantBO.addTax(pisMock);
        accountantBO.addTax(cofinsMock);
        accountantBO.addTax(csllMock);
        
        Costumer costumerMock0 = new Costumer("Jean Paul Depraz", "338.253.618-88");
        Costumer costumerMock1 = new Costumer("Deprazz Serv. em Informatica", "20.594.395/0001­05");
        
        companyBO.issueInvoice(costumerMock0, new BigDecimal(150.0));
        companyBO.issueInvoice(costumerMock1, new BigDecimal(10000.0));
        companyBO.issueInvoice(costumerMock1, new BigDecimal(158300.58));
        
    }
    
    /*
     * AS a company that provides services.
     * I want to know the total amount of the following withhold taxes: IR, PIS, COFINS, CSLL.
     * That way I know how much money will be discounted when a customer pays my invoice.
     */
    @Test
    public void Story01(){
    	Map<String, BigDecimal> totals = companyBO.printTaxesTotals();
    	String ir = NumberFormat.getCurrencyInstance().
				format(totals.get("IR").doubleValue());
    	String pis = NumberFormat.getCurrencyInstance().
				format(totals.get("PIS").doubleValue());
    	String cofins = NumberFormat.getCurrencyInstance().
				format(totals.get("COFINS").doubleValue());
    	String csll = NumberFormat.getCurrencyInstance().
				format(totals.get("CSLL").doubleValue());
    	Assert.assertEquals("$8,422.53", ir);
    	Assert.assertEquals("$1,093.95", pis);
    	Assert.assertEquals("$5,049.02", cofins);
    	Assert.assertEquals("$1,683.01", csll);
    }
    
    /*
     * AS a system administrator (accountant).
     * I want to be able to change the withhold tax rates for IR, PIS, COFINS, CSLL.
     * That way I don't need to change the code when the tax rate changes.
     */
    @Test
    public void Story02(){
    	Assert.assertEquals(5.0, accountantBO.getTaxRate("IR"));
    	accountantBO.changeTaxRate("IR", 7.5);
    	Assert.assertEquals(7.5, accountantBO.getTaxRate("IR"));
    }
    
    /*
     * AS a company that provide services.
     * I want to see the amounts withheld rounded correctly to 2 decimal places.
     * That way I will not be confused about how much will be discounted from my payment. 
     */
    @Test
    public void Story03(){
    	BigDecimal total = companyBO.getTaxesTotal();
    	String totalS = NumberFormat.getCurrencyInstance().
				format(total.doubleValue());
    	Assert.assertEquals("$52,956.78", totalS);
    }

}