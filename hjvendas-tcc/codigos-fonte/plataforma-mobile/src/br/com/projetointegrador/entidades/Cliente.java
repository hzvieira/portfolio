package br.com.projetointegrador.entidades;

public class Cliente {
	
	public static String[] colunas = new String[] { 
		"id",
		"nome",
		"telefoneresidencial",
		"telefonecelular",
		"email01",
		"email02",
		"rua",
		"numero",
		"complemento",
		"bairro",
		"cep",
		"genero",
		"datanascimento",
		"profissao",
		"renda",
		"saldo",
		"cidade_id",		
		"observacoes"
	};

    private long id;
    private String nome;
    private String telefoneResidencial;
    private String telefoneCelular;
    private String email01;
    private String email02;
    private String rua;
    private long numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String observacoes;
    private Cidade cidade;
    private GeneroProduto genero;
    private String nascimento;
    private String profissao;
    private Double renda;
    private Double saldo;

    
    
    public GeneroProduto getGenero() {
		return genero;
	}

	public void setGenero(GeneroProduto genero) {
		this.genero = genero;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Double getRenda() {
		return renda;
	}

	public void setRenda(Double renda) {
		this.renda = renda;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEmail01() {
        return email01;
    }

    public void setEmail01(String email01) {
        this.email01 = email01;
    }

    public String getEmail02() {
        return email02;
    }

    public void setEmail02(String email02) {
        this.email02 = email02;
    }

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

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }
    
	@Override
    public String toString() {
        return nome;
    }
}
	