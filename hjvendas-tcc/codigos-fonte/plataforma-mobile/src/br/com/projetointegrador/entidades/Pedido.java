package br.com.projetointegrador.entidades;

public class Pedido {

	public static String[] colunas = new String[] { "ID", "DATAPEDIDO", "CLIENTE_ID" };

	private long id;
	private String data;
	private Cliente cliente;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return cliente.toString();
	}

}
