package entidades;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Fornecedor extends Pessoa implements Serializable, Comparable<Fornecedor> {

    private String cnpj;

    public Fornecedor() {
    }

    public Fornecedor(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int compareTo(Fornecedor o) {
        return this.nome.compareTo(o.nome);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
