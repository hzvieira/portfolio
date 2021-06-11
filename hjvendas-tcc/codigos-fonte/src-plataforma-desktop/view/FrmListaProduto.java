package view;

import app.Principal;
import entidades.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaProduto extends AbstractTableModel {

    private String colunas[] = {"Nome", "Valor", "Tempo de Duração", "Parte do corpo"};
    private Class classeColunas[] = {String.class, String.class, String.class, String.class};
    private List<Produto> lista;
    private EntityManager em;

   
    public FrmListaProduto() {
            lista = Principal.emf.createEntityManager().createQuery("from " + Produto.class.getSimpleName()).getResultList();
    }

    /*public FrmListaProduto(Long l) {
        if (l == null) {
            lista = Principal.emf.createEntityManager().createQuery("from " + Produto.class.getSimpleName()).getResultList();
        } else {
            lista = Principal.emf.createEntityManager().
                    createNativeQuery("select * from produto "
                    + "where produto.fornecedor_id = " + l
                    + " order by produto.nome").getResultList();
        }
    }*/

    public List<Produto> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto pro = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pro.getNome();
            case 1:
                return pro.getValor();
            case 2:
                return String.valueOf(pro.getDuracao()) + " " + pro.getTempoDuracao();
            case 3:
                return pro.getParteDoCorpo();
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
