package dao;

import app.Principal;
import entidades.Cliente;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmListaContasReceber;
import view.FrmReceberDinheiro;

public class ConsultaContasReceber extends FrmListaGenerico {

    public ConsultaContasReceber() {
        setTitle("Pronta Entrega");
        btAlterar.setText("Receber dinheiro");
        painelBotoes.remove(btSelecionar);
        painelBotoes.remove(btInserir);
        painelBotoes.remove(btExcluir);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaContasReceber();
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void alterar() {

        // Merge
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaContasReceber lis = (FrmListaContasReceber) tbDados.getModel();
        Cliente cli = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        cli = em.find(Cliente.class, cli.getId());
        if (cli == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmReceberDinheiro md = new FrmReceberDinheiro(null, true);
            md.setEntidade(cli);
            md.entidadeTela();
            md.setVisible(true);
            cli = md.getEntidade();
            if (cli != null) {
                em.getTransaction().begin();
                em.merge(cli);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaContasReceber());
        tbDados.updateUI();
    }
}
