package view;

import app.Principal;
import entidades.TipoRelacionamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaTipoRelacionamento extends AbstractTableModel {

    private String colunas[] = {"Tipo de relacionamento"};
    private Class classeColunas[] = {String.class};
    private List<TipoRelacionamento> lista;
    private EntityManager em;

    public FrmListaTipoRelacionamento() {
        lista = Principal.emf.createEntityManager().createQuery("from " + TipoRelacionamento.class.getSimpleName()).getResultList();
    }

    public List<TipoRelacionamento> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        TipoRelacionamento tr = lista.get(rowIndex);
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
