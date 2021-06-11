package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class ParcelasPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Fornecedor fornecedor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVencimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPagamento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCompra;
    private Double valor;

    public ParcelasPagar() {
    }

    public ParcelasPagar(Long id, Fornecedor fornecedor, Date dataVencimento, Date dataPagamento, Date dataCompra, Double valor) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.dataCompra = dataCompra;
        this.valor = valor;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof ParcelasPagar)) {
            return false;
        }
        ParcelasPagar other = (ParcelasPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ParcelasPagar[id=" + id + "]";
    }
}
