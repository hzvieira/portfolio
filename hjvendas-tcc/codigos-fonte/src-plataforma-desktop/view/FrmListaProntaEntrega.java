package view;

import app.Principal;
import entidades.Produto;
import entidades.ProntaEntrega;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaProntaEntrega extends AbstractTableModel {

    private String colunas[] = {"Produto", "Quantidade"};
    private Class classeColunas[] = {Produto.class, Long.class};
    private List<ProntaEntrega> lista;
    private EntityManager em;

    public FrmListaProntaEntrega() {
        lista = Principal.emf.createEntityManager().createQuery("from " + ProntaEntrega.class.getSimpleName()).getResultList();
    }

    public List<ProntaEntrega> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ProntaEntrega pe = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pe.getProduto().getNome();
            case 1:
                return pe.getQuantidade();
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
