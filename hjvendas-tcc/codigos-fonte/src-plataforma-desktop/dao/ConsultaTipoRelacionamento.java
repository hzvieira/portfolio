package dao;

import app.Principal;
import entidades.TipoRelacionamento;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadTipoRelacionamento;
import view.FrmListaTipoRelacionamento;

public class ConsultaTipoRelacionamento extends FrmListaGenerico {

    public ConsultaTipoRelacionamento() {
        setTitle("Cadastro de Tipo de relacionamento");
        btSelecionar.setVisible(false);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaTipoRelacionamento();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaTipoRelacionamento lis = (FrmListaTipoRelacionamento) tbDados.getModel();
        TipoRelacionamento tr = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        tr = em.find(TipoRelacionamento.class, tr.getId());
        if (tr == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(tr);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaTipoRelacionamento lis = (FrmListaTipoRelacionamento) tbDados.getModel();
        TipoRelacionamento tr = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        tr = em.find(TipoRelacionamento.class, tr.getId());
        if (tr == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadTipoRelacionamento md = new FrmCadTipoRelacionamento(null, true);
            md.setEntidade(tr);
            md.entidadeTela();
            md.setVisible(true);
            tr = md.getEntidade();
            if (tr != null) {
                em.getTransaction().begin();
                em.merge(tr);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        TipoRelacionamento tr = new TipoRelacionamento();
        FrmCadTipoRelacionamento md = new FrmCadTipoRelacionamento(null, true);
        md.setEntidade(tr);
        md.setVisible(true);
        tr = md.getEntidade();
        if (tr != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(tr);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaTipoRelacionamento());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
