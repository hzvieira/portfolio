package view;

import app.Principal;
import entidades.Cliente;
import entidades.Relacionamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaCliente extends AbstractTableModel {

    private String colunas[] = {"Nome", "Telefone", "E-Mail",};
    private Class classeColunas[] = {String.class, String.class, String.class};
    private List<Cliente> lista;
    private List<Relacionamento> relacao;
    private EntityManager em;

    public FrmListaCliente() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Cliente.class.getSimpleName()).getResultList();
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public List<Relacionamento> getRelacao(Cliente cli) {
        relacao = Principal.emf.createEntityManager().createQuery("from " + Relacionamento.class.getSimpleName() + " where cliente_id = " + cli.getId()).getResultList();
        return relacao;
    }

    public void setRelacao(List<Relacionamento> relacao) {
        this.relacao = relacao;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cli = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cli.getNome();
            case 1:
                return cli.getTelefoneResidencial();
            case 2:
                return cli.getEmail01();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classeColunas[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
