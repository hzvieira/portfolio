package dao;

import app.Principal;
import entidades.Devolucao;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadDevolucao;
import view.FrmListaDevolucao;

public class ConsultaDevolucao extends FrmListaGenerico {

    public ConsultaDevolucao() {
        setTitle("Cadastro de Devolucao");
        painelBotoes.remove(btSelecionar);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaDevolucao();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaDevolucao lis = (FrmListaDevolucao) tbDados.getModel();
        Devolucao dev = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        dev = em.find(Devolucao.class, dev.getId());
        if (dev == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(dev);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaDevolucao lis = (FrmListaDevolucao) tbDados.getModel();
        Devolucao dev = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        dev = em.find(Devolucao.class, dev.getId());
        if (dev == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadDevolucao md = new FrmCadDevolucao(null, true);
            md.setEntidade(dev);
            md.entidadeTela();
            md.setVisible(true);
            dev = md.getEntidade();
            if (dev != null) {
                em.getTransaction().begin();
                em.merge(dev);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Devolucao dev = new Devolucao();
        FrmCadDevolucao md = new FrmCadDevolucao(null, true);
        md.setEntidade(dev);
        md.setVisible(true);
        dev = md.getEntidade();
        if (dev != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(dev);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaDevolucao());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
