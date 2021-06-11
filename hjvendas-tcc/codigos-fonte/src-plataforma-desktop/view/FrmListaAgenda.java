package view;

import app.Principal;
import entidades.Agenda;
import entidades.Cliente;
import entidades.TipoContatoAgenda;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FrmListaAgenda extends AbstractTableModel {

    private String colunas[] = {"Dia", "Hora", "Cliente", "Contato"};
    private Class classeColunas[] = {Date.class, String.class, Cliente.class, TipoContatoAgenda.class};
    private List<Agenda> horarios;

    public FrmListaAgenda() {
       horarios = Principal.emf.createEntityManager().createQuery("from " + Agenda.class.getSimpleName() + " a order by a.dia, a.hora").getResultList();
    }

    public List<Agenda> getLista() {
        return horarios;
    }

    public int getRowCount() {
        return horarios.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Agenda a = horarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getDia();
            case 1:
                return a.getHora();
            case 2:
                return a.getCliente();
            case 3:
                   return a.getContato();
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
