package br.com.projetointegrador.entidades;

public class PeriodoVendas {

	public static String[] colunas = new String[] { "ID", "DATAFINAL",
			"DATAINICIAL", "PEDIDOFEITO", "RECEBEUENCOMENDA", "FORNECEDOR_ID" };

	private long id;
	private String dataFinal;
	private String dataInicial;
	private boolean pedidoFeito;
	private boolean recebeuEncomenda;
	private Fornecedor fornecedor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public boolean isPedidoFeito() {
		return pedidoFeito;
	}

	public void setPedidoFeito(boolean pedidoFeito) {
		this.pedidoFeito = pedidoFeito;
	}

	public boolean isRecebeuEncomenda() {
		return recebeuEncomenda;
	}

	public void setRecebeuEncomenda(boolean recebeuEncomenda) {
		this.recebeuEncomenda = recebeuEncomenda;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
