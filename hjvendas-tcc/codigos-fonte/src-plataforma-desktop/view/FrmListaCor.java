package view;

import app.Principal;
import entidades.Cor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaCor extends AbstractTableModel {

    private String colunas[] = {"Nome da Cor"};
    private Class classeColunas[] = {Long.class, String.class};
    private List<Cor> lista;
    private EntityManager em;

    public FrmListaCor() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Cor.class.getSimpleName()).getResultList();
    }

    public List<Cor> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Cor cor = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cor.getNome();
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
