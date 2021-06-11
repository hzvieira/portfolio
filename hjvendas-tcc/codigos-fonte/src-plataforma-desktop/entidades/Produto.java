package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Produto implements Serializable,Comparable<Produto> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descMaterial;
    private Double valor;
    private Long duracao;
    private String nome;
    private String produto;
    @ManyToOne
    private Fornecedor fornecedor;
    @ManyToOne
    private Cor cor;
    @ManyToOne
    private Genero sexo;
    private String tempoDuracao;
    @ManyToOne
    private ParteDoCorpo parteDoCorpo;
    private Long volume;
    private String volumeCosmetico;
    @ManyToOne
    private TipoProduto tipoProduto;
    private String tamanho;
    private Long tamanhoSalto;
    private Long numero;

    public Produto() {
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
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

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(Genero sexo) {
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public int compareTo(Produto o) {
       return this.nome.compareTo(o.nome);
    }
}
