package br.com.projetointegrador.entidades;

public class ParteDoCorpo {

	public static String[] colunas = new String[] { "ID", "NOME" };
	
	private long id;
	private String nome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

}
