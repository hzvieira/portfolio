package dao;

import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmListaAgenda;

public class ConsultaAgenda extends FrmListaGenerico {

    public ConsultaAgenda() {
        setTitle("Agenda!");
        painelBotoes.remove(btSelecionar);
        painelBotoes.setVisible(false);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaAgenda();
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void alterar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void inserir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
