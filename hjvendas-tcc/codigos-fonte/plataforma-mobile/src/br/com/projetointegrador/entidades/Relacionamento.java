package br.com.projetointegrador.entidades;

public class Relacionamento {

	public static String[] colunas = new String[] { 
		"ID", 
		"ativo",
		"cliente_id", 
		"relacionamento_id", 
		"tiporelacionamento_id"
	};

	private long id;
	private boolean ativo;
	private Cliente cliente;
	private Cliente relacionamento;
	private TipoRelacionamento tipoRelacionamento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(Cliente relacionamento) {
		this.relacionamento = relacionamento;
	}

	public TipoRelacionamento getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	@Override
	public String toString() {
		return relacionamento.getNome();
	}

}
