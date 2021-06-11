package br.com.projetointegrador.entidades;

public class ItemPedido {

	public static String[] colunas = new String[] { "ID", "ENTREGUE", "QUANTIDADE", "VALOR", "PEDIDO_ID", "PRODUTO_ID" };
	
	private long id;
	private boolean entregue;
	private long quantidade;
	private Double valor;
	private Pedido pedido;
	private Produto produto;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isEntregue() {
		return entregue;
	}
	
	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}
	
	public long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return super.toString();
	}

    public void adicionaQuantidade(ItemPedido itemDuplicado) {
        quantidade += itemDuplicado.quantidade;
    }

}
