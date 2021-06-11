package view;

import app.Principal;
import entidades.ParteDoCorpo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaParteDoCorpo extends AbstractTableModel {

    private String colunas[] = {"Descrição da parte do corpo"};
    private Class classeColunas[] = {Long.class, String.class};
    private List<ParteDoCorpo> lista;
    private EntityManager em;

    public FrmListaParteDoCorpo() {
        lista = Principal.emf.createEntityManager().createQuery("from " + ParteDoCorpo.class.getSimpleName()).getResultList();
    }

    public List<ParteDoCorpo> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ParteDoCorpo pc = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pc.getDescricao();
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
