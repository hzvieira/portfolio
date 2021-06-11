package view;

import app.Principal;
import entidades.Genero;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaGenero extends AbstractTableModel {

    private String colunas[] = {"Nome do Genero"};
    private Class classeColunas[] = {String.class};
    private List<Genero> lista;
    private EntityManager em;

    public FrmListaGenero() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Genero.class.getSimpleName()).getResultList();
    }

    public List<Genero> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Genero g = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return g.getNome();
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
