package view;

import app.Principal;
import entidades.Fornecedor;
import entidades.ParcelasPagar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaParcelasPagar extends AbstractTableModel {

    private String colunas[] = {"Forncedor", "Data Compra", "Data Vencimento", "Data Pagamento"};
    private Class classeColunas[] = {Fornecedor.class, Date.class, Date.class, Date.class};
    private List<ParcelasPagar> lista;
    private EntityManager em;

    public FrmListaParcelasPagar() {
        lista = Principal.emf.createEntityManager().createQuery("from " + ParcelasPagar.class.getSimpleName()).getResultList();
    }

    public List<ParcelasPagar> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ParcelasPagar pp = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pp.getFornecedor().getNome();
            case 1:
                return pp.getDataCompra();
            case 2:
                return pp.getDataVencimento();
            case 3:
                return pp.getDataPagamento();
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
