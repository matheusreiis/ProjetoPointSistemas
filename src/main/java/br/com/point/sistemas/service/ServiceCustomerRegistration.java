package br.com.point.sistemas.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.point.sistemas.model.Client;
import br.com.point.sistemas.repository.Customers;
import br.com.point.sistemas.util.Transacional;

public class ServiceCustomerRegistration implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Customers customers;

	@Transacional
	public void toSave(Client client) {
		customers.save(client);
	}

	@Transacional
	public void delete(Client client) {
		customers.toRemove(client);
	}

}