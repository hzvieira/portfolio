package view;

import app.Principal;
import entidades.Periodo;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaPeriodo extends AbstractTableModel {

    private String colunas[] = {"Fornecedor", "Data Inicial", "Data Final", "Fez o pedido", "Recebeu encomentda"};
    private Class classeColunas[] = {String.class, String.class, String.class, String.class, String.class};
    private List<Periodo> lista;
    private EntityManager em;

    public FrmListaPeriodo() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Periodo.class.getSimpleName()).getResultList();
    }

    public List<Periodo> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Periodo p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getFornecedor();
            case 1:
                return new SimpleDateFormat("dd/MM/yyyy").format(p.getDataInicial());
            case 2:
                return new SimpleDateFormat("dd/MM/yyyy").format(p.getDataFinal());
            case 3:
                if (p.isPedidoFeito()) {
                    return "Pedido feito!";
                } else {
                    return "Pedido ainda não realizado!";
                }
            case 4:
                if (p.isPedidoFeito() && !p.isRecebeuEncomenda()) {
                    return "Não recebeu a encomenda";
                } else if (!p.isPedidoFeito()) {
                    return "Pedido ainda não realizado!";
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
