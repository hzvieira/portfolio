package view;

import app.Principal;
import entidades.Fornecedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaFornecedor extends AbstractTableModel {

    private String colunas[] = {"Nome", "Telefone", "E-Mail",};
    private Class classeColunas[] = {String.class, String.class, String.class};
    private List<Fornecedor> lista;
    private EntityManager em;

    public FrmListaFornecedor() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Fornecedor.class.getSimpleName()).getResultList();
    }

    public List<Fornecedor> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornecedor forn = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return forn.getNome();
            case 1:
                return forn.getTelefoneResidencial();
            case 2:
                return forn.getEmail01();
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
