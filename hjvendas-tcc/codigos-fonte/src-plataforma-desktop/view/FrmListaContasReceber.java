package view;

import app.Principal;
import entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaContasReceber extends AbstractTableModel {

    private String colunas[] = {"Nome", "Telefone", "Saldo",};
    private Class classeColunas[] = {String.class, String.class, Double.class};
    private List<Cliente> lista;
    private EntityManager em;

    public FrmListaContasReceber() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Cliente.class.getSimpleName() + " order by saldo desc").getResultList();
    }

    public List<Cliente> getLista() {
        return lista;
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
                return cli.getSaldo();
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
