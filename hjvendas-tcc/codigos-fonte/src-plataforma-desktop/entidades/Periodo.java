package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Periodo implements Serializable, Comparable<Periodo> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicial;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFinal;
    private boolean recebeuEncomenda;
    private boolean pedidoFeito;
    @OneToOne
    private Fornecedor fornecedor;

    public Periodo() {
    }

    public Periodo(Long id, Date dataInicial, Date dataFinal, boolean recebeuEncomenda, boolean pedidoFeito, Fornecedor fornecedor) {
        this.id = id;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.recebeuEncomenda = recebeuEncomenda;
        this.pedidoFeito = pedidoFeito;
        this.fornecedor = fornecedor;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.dataInicial)
                    + "-" +
               new SimpleDateFormat("dd/MM/yyyy").format(this.dataFinal);
    }

    public int compareTo(Periodo o) {
        //return this.dataFinal.compareTo(o.dataFinal);
        //return this.dataInicial.compareTo(o.dataInicial);
        return 21;
    }
}
