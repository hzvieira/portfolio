package br.com.projetointegrador.entidades;

public class Devolucao {

	public static String[] colunas = new String[] { "ID", "DATADEVOLUCAO", "CLIENTE_ID", "PRODUTO_ID" };
	
	private long id;
	private String data;
	private Cliente cliente;
	private Produto produto;

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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
