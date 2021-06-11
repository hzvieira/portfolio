package view;

import app.Principal;
import entidades.Cidade;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FrmListaCidade extends AbstractTableModel {

    private String colunas[] = {"Nome da Cidade"};
    private Class classeColunas[] = {String.class};
    private List<Cidade> lista;

    public FrmListaCidade() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Cidade.class.getSimpleName()).getResultList();
    }

    public List<Cidade> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Cidade cidade = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cidade.getNome();
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
