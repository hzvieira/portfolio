package dao;

import app.Principal;
import entidades.ParteDoCorpo;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadParteDoCorpo;
import view.FrmListaParteDoCorpo;

public class ConsultaParteDoCorpo extends FrmListaGenerico {

    public ConsultaParteDoCorpo() {
        setTitle("Cadastro de Parte do Corpo");
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaParteDoCorpo();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaParteDoCorpo lis = (FrmListaParteDoCorpo) tbDados.getModel();
        ParteDoCorpo pc = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        pc = em.find(ParteDoCorpo.class, pc.getId());
        if (pc == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(pc);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaParteDoCorpo lis = (FrmListaParteDoCorpo) tbDados.getModel();
        ParteDoCorpo pc = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        pc = em.find(ParteDoCorpo.class, pc.getId());
        if (pc == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadParteDoCorpo md = new FrmCadParteDoCorpo(null, true);
            md.setEntidade(pc);
            md.entidadeTela();
            md.setVisible(true);
            pc = md.getEntidade();
            if (pc != null) {
                em.getTransaction().begin();
                em.merge(pc);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        ParteDoCorpo pc = new ParteDoCorpo();
        FrmCadParteDoCorpo md = new FrmCadParteDoCorpo(null, true);
        md.setEntidade(pc);
        md.setVisible(true);
        pc = md.getEntidade();
        if (pc != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(pc);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaParteDoCorpo());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
