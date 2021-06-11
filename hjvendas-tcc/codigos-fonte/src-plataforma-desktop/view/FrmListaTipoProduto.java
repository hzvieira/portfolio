package view;

import app.Principal;
import entidades.TipoProduto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaTipoProduto extends AbstractTableModel {

    private String colunas[] = {"Nome do tipo do produto"};
    private Class classeColunas[] = {Long.class, String.class};
    private List<TipoProduto> lista;
    private EntityManager em;

    public FrmListaTipoProduto() {
        lista = Principal.emf.createEntityManager().createQuery("from " + TipoProduto.class.getSimpleName()).getResultList();
    }

    public List<TipoProduto> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 1;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        TipoProduto tp = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tp.getNome();
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
