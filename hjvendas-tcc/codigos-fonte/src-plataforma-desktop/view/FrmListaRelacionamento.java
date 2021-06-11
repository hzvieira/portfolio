package view;

import app.Principal;
import entidades.Relacionamento;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FrmListaRelacionamento extends AbstractTableModel {

    private String colunas[] = {"Nome", "Telefone", "Ralação", "Ativo"};
    private Class classeColunas[] = {String.class, String.class, String.class, String.class};
    private List<Relacionamento> lista;

    public FrmListaRelacionamento() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Relacionamento.class.getSimpleName()).getResultList();
    }

    public FrmListaRelacionamento(List<Relacionamento> a) {
        lista = a;
    }

    public List<Relacionamento> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Relacionamento rel = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rel.getRelacionamento().getNome();
            case 1:
                return rel.getRelacionamento().getTelefoneCelular();
            case 2:
                return rel.getTipoRelacionamento().getDescricao();
            case 3:
                if (rel.isAtivo()) {
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
