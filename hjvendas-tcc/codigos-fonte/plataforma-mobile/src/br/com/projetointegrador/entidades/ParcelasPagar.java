package br.com.projetointegrador.entidades;

public class ParcelasPagar {

	/*
	 * CREATE TABLE parcelaspagar ( id INTEGER PRIMARY KEY, datacompra DATE,
	 * datapagamento DATE, datavencimento DATE, valor BLOB, fornecedor_id
	 * INTEGER, FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id) );
	 * 
	 * id, datacompra, datapagamento, datavencimento, valor
	 */

	public static String[] colunas = new String[] { "ID", "DATACOMPRA",
			"DATAPAGAMENTO", "DATAVENCIMENTO", "VALOR", "FORNECEDOR_ID" };

	private long id;
	private String dataCompra;
	private String dataPagamento;
	private String dataVencimento;
	private double valor;
	private Fornecedor fornecedor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "Vencimento: " + dataVencimento;
	}

}
