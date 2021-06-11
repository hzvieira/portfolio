package view;

import app.Principal;
import entidades.Fornecedor;
import entidades.Periodo;
import entidades.Produto;
import entidades.Promocao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaPromocao extends AbstractTableModel {

    private String colunas[] = {"Produto","Fornecedor","Periodo","Valor"};
    private Class classeColunas[] = {Produto.class, Fornecedor.class, Periodo.class, Double.class};
    private List<Promocao> lista;
    private EntityManager em;

    public FrmListaPromocao() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Promocao.class.getSimpleName()).getResultList();
    }

    public List<Promocao> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Promocao pro = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pro.getProduto();
            case 1:
                return pro.getProduto().getFornecedor();
            case 2:
                return pro.getPeriodo();
            case 3:
                return pro.getValor();
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
