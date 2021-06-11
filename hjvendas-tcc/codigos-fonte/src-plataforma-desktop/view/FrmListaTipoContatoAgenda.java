package view;

import app.Principal;
import entidades.TipoContatoAgenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaTipoContatoAgenda extends AbstractTableModel {

    private String colunas[] = {"Tipo de Contato"};
    private Class classeColunas[] = {Long.class, String.class};
    private List<TipoContatoAgenda> lista;
    private EntityManager em;

    public FrmListaTipoContatoAgenda() {
        lista = Principal.emf.createEntityManager().createQuery("from " + TipoContatoAgenda.class.getSimpleName()).getResultList();
    }

    public List<TipoContatoAgenda> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        TipoContatoAgenda tr = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tr.getDescricao();
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
