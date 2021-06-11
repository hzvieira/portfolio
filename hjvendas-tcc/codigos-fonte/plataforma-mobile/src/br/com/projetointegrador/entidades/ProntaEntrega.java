package br.com.projetointegrador.entidades;

public class ProntaEntrega {

	public static String[] colunas = new String[] { "ID", "QUANTIDADE",
			"PRODUTO_ID" };

	private long id;
	private long quantidade;
	private Produto produto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Produto: " + produto + ", Quantidade: "	+ quantidade;
	}

	
}
