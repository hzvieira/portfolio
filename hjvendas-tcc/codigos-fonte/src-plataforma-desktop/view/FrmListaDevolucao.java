package view;

import app.Principal;
import entidades.Cliente;
import entidades.Devolucao;
import entidades.Produto;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaDevolucao extends AbstractTableModel {

    private String colunas[] = {"Cliente", "Data", "Produto"};
    private Class classeColunas[] = {Cliente.class, Date.class, Produto.class};
    private List<Devolucao> lista;
    private EntityManager em;

    public FrmListaDevolucao() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Devolucao.class.getSimpleName()).getResultList();
    }

    public List<Devolucao> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Devolucao d = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return d.getCliente().getNome();
            case 1:
                return d.getDataDevolucao();
            case 2:
                return d.getProduto().getNome();
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
