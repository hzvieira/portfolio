package dao;

import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmListaProntaEntrega;

public class ConsultaProntaEntrega extends FrmListaGenerico {

    public ConsultaProntaEntrega() {
        setTitle("Pronta Entrega");
        painelBotoes.remove(btSelecionar);
        painelBotoes.setVisible(false);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaProntaEntrega();
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
