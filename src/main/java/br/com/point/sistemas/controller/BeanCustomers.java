package br.com.point.sistemas.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.point.sistemas.model.Client;
import br.com.point.sistemas.model.GenderType;
import br.com.point.sistemas.repository.Customers;
import br.com.point.sistemas.service.ServiceCustomerRegistration;
import br.com.point.sistemas.util.FacesMessages;

/**
 * 
 * Execução de listagem e cadastramento de clientes.
 * 
 * @author Matheus Cristiano
 *
 */

@Named
@ViewScoped
public class BeanCustomers implements Serializable {

	/**
	 * LOG.
	 */
	private static final Logger LOG = Logger.getLogger(BeanCustomers.class.getName());

	private static final long serialVersionUID = 1L;

	@Inject
	private Customers customers;

	@Inject
	private ServiceCustomerRegistration serviceCustomerRegistration;

	@Inject
	private FacesMessages messages;

	private List<Client> listClients;

	private String termSearch;

	public void updateRecords() {

		try {
			if (researched()) {
				search();
			} else {
				customerList();
				LOG.info("Tabela atualizada com sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean researched() {
		return termSearch != null && !"".equals(termSearch);
	}

	private Client client;

	public void prepareNewClient() {
		client = new Client();
	}

	public void toSave() {

		try {
			serviceCustomerRegistration.toSave(client);
			updateRecords();

			String messageInfo = "Cliente salvo com sucesso!";

			messages.info(messageInfo);
			LOG.info(messageInfo);
			RequestContext.getCurrentInstance().update(Arrays.asList("frm:clientsDataTable", "frm:messages"));

		} catch (Exception e) {
			LOG.warning("Erro ao realizar cadastramento do cliente.");
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			serviceCustomerRegistration.delete(client);
			client = null;
			updateRecords();

			String messageInfo = "Cliente excluído com sucesso!";

			messages.info(messageInfo);
			LOG.info(messageInfo);

		} catch (Exception e) {
			LOG.warning("Erro ao excluir cliente.");
			e.printStackTrace();
		}
	}

	public void search() {
		try {
			listClients = customers.search(termSearch);

			if (listClients.isEmpty()) {

				String messageInfo = "Sua consulta não retornou registros.";

				messages.info(messageInfo);
				LOG.info(messageInfo);
			}

		} catch (Exception e) {
			LOG.warning("Erro ao pesquisar cliente.");
			e.printStackTrace();
		}
	}

	public void customerList() {
			listClients = customers.allClientes();
	}

	public List<Client> getListClients() {
		return listClients;
	}

	public String getTermSearch() {
		return termSearch;
	}

	public void setTermSearch(String termSearch) {
		this.termSearch = termSearch;
	}

	public GenderType[] getGenres() {
		return GenderType.values();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isClientSelect() {
		return client != null && client.getId() != null;
	}
}