package br.com.conube.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.conube.model.Invoice;
import br.com.conube.model.dao.InvoiceDAO;

public class InvoiceDAOImpl extends DAOImpl<Invoice, Integer> implements InvoiceDAO {

	@Override
	public List<Invoice> getAll() {
		return new ArrayList<Invoice>(mockedDB);
	}

}
