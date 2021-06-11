package br.com.projetointegrador.entidades;

public class Produto {
	
	public static String[] colunas = new String[] { 
		"ID",
		"descmaterial",
		"duracao",
		"nome",
		"numero",
		"produto",
		"tamanho",
		"tamanhosalto",
		"tempoduracao",
		"valor",
		"volume",
		"volumecosmetico",
		"cor_id",
		"fornecedor_id",
		"partedocorpo_id",
		"genero_id",
		"tipoproduto_id"
	};
	
    private long id;
    private String descMaterial;
    private Double valor;
    private Long duracao;
    private String nome;
    private String produto;
    private Fornecedor fornecedor;
    private CorProduto cor;
    private GeneroProduto sexo;
    private String tempoDuracao;
    private ParteDoCorpo parteDoCorpo;
    private Long volume;
    private String volumeCosmetico;
    private TipoProduto tipoProduto;
    private String tamanho;
    private Long tamanhoSalto;
    private Long numero;

    public Produto() {
    }

    public CorProduto getCor() {
        return cor;
    }

    public void setCor(CorProduto cor) {
        this.cor = cor;
    }

    public String getDescMaterial() {
        return descMaterial;
    }

    public void setDescMaterial(String descMaterial) {
        this.descMaterial = descMaterial;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public ParteDoCorpo getParteDoCorpo() {
        return parteDoCorpo;
    }

    public void setParteDoCorpo(ParteDoCorpo parteDoCorpo) {
        this.parteDoCorpo = parteDoCorpo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public GeneroProduto getGenero() {
        return sexo;
    }

    public void setGenero(GeneroProduto sexo) {
        this.sexo = sexo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Long getTamanhoSalto() {
        return tamanhoSalto;
    }

    public void setTamanhoSalto(Long tamanhoSalto) {
        this.tamanhoSalto = tamanhoSalto;
    }

    public String getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(String tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getVolumeCosmetico() {
        return volumeCosmetico;
    }

    public void setVolumeCosmetico(String volumeCosmetico) {
        this.volumeCosmetico = volumeCosmetico;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
