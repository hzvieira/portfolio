package view;

import app.Principal;
import entidades.ItensPedido;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FrmListaItens extends AbstractTableModel {

    private String colunas[] = {"Produto", "Fornecedor", "Valor", "Quantidade", "Entregar"};
    private Class classeColunas[] = {String.class, String.class, Double.class, Long.class, String.class};
    private List<ItensPedido> lista;

    public FrmListaItens() {
        lista = Principal.emf.createEntityManager().createQuery("from " + ItensPedido.class.getSimpleName()).getResultList();
    }

    public FrmListaItens(List<ItensPedido> a) {
        lista = a;
    }

    public List<ItensPedido> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ItensPedido ip = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ip.getProduto().getNome();
            case 1:
                return ip.getProduto().getFornecedor().getNome();
            case 2:
                return ip.getValor();
            case 3:
                return ip.getQuantidade();
            case 4:
                if (ip.isEntregue()) {
                    return "Sim";
                } else {
                    return "Não";
                }
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
