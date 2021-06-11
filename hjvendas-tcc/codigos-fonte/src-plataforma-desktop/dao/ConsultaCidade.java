package dao;

import app.Principal;
import entidades.Cidade;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadCidade;
import view.FrmListaCidade;

public class ConsultaCidade extends FrmListaGenerico {

    public ConsultaCidade() {
        setTitle("Cadastro de Cidade");
        painelBotoes.remove(btSelecionar);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaCidade();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaCidade lis = (FrmListaCidade) tbDados.getModel();
        Cidade cidade = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        cidade = em.find(Cidade.class, cidade.getId());
        if (cidade == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(cidade);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaCidade lis = (FrmListaCidade) tbDados.getModel();
        Cidade cidade = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        cidade = em.find(Cidade.class, cidade.getId());
        if (cidade == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadCidade md = new FrmCadCidade(null, true);
            md.setEntidade(cidade);
            md.entidadeTela();
            md.setVisible(true);
            cidade = md.getEntidade();
            if (cidade != null) {
                em.getTransaction().begin();
                em.merge(cidade);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Cidade cidade = new Cidade();
        FrmCadCidade md = new FrmCadCidade(null, true);
        md.setEntidade(cidade);
        md.setVisible(true);
        cidade = md.getEntidade();
        if (cidade != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(cidade);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaCidade());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
