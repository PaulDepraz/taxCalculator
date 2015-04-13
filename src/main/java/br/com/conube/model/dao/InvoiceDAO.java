package br.com.conube.model.dao;

import java.util.List;

import br.com.conube.model.Invoice;

public interface InvoiceDAO extends DAO<Invoice, Integer> {
	
	public List<Invoice> getAll();
	
}
