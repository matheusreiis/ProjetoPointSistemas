package br.com.point.sistemas.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.point.sistemas.model.Client;

public class Customers implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Customers() {

	}

	public Customers(EntityManager manager) {
		this.manager = manager;
	}

	public Client porId(Long id) {
		return manager.find(Client.class, id);
	}

	public List<Client> search(String nome) {
		String jpql = "from Client where nome like :nome";

		TypedQuery<Client> query = manager.createQuery(jpql, Client.class);

		query.setParameter("nome", nome + "%");

		return query.getResultList();
	}

	public List<Client> allClientes() {
		return manager.createQuery("from Client", Client.class).getResultList();
	}

	public Client save(Client client) {
		return manager.merge(client);
	}

	public void toRemove(Client client) {
		client = porId(client.getId());
		manager.remove(client);
	}
}