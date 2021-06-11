package view;

import app.Principal;
import entidades.Evento;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class FrmListaEvento extends AbstractTableModel {

    private String colunas[] = {"Evento", "Data Inicial", "Data Final", "Relação"};
    private Class classeColunas[] = {String.class, Date.class, Date.class, String.class};
    private List<Evento> lista;
    private EntityManager em;

    public FrmListaEvento() {
        lista = Principal.emf.createEntityManager().createQuery("from " + Evento.class.getSimpleName()).getResultList();
    }

    public List<Evento> getLista() {
        return lista;
    }

    public int getRowCount() {
        return lista.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Evento e = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return e.getDescricao();
            case 1:
                return e.getDataInicial();
            case 2:
                return e.getDataFinal();
            case 3:
                return e.getTipoRelacionamento().getDescricao();
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
