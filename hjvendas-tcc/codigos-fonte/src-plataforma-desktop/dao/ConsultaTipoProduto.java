package dao;

import app.Principal;
import entidades.TipoProduto;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadTipoProduto;
import view.FrmListaTipoProduto;

public class ConsultaTipoProduto extends FrmListaGenerico {

    public ConsultaTipoProduto() {
        setTitle("Cadastro de tipo do produto");
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaTipoProduto();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaTipoProduto lis = (FrmListaTipoProduto) tbDados.getModel();
        TipoProduto tp = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        tp = em.find(TipoProduto.class, tp.getId());
        if (tp == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(tp);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaTipoProduto lis = (FrmListaTipoProduto) tbDados.getModel();
        TipoProduto tp = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        tp = em.find(TipoProduto.class, tp.getId());
        if (tp == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadTipoProduto md = new FrmCadTipoProduto(null, true);
            md.setEntidade(tp);
            md.entidadeTela();
            md.setVisible(true);
            tp = md.getEntidade();
            if (tp != null) {
                em.getTransaction().begin();
                em.merge(tp);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        TipoProduto tp = new TipoProduto();
        FrmCadTipoProduto md = new FrmCadTipoProduto(null, true);
        md.setEntidade(tp);
        md.setVisible(true);
        tp = md.getEntidade();
        if (tp != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(tp);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaTipoProduto());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
