package dao;

import app.Principal;
import entidades.Periodo;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadPeriodo;
import view.FrmListaPeriodo;

public class ConsultaPeriodo extends FrmListaGenerico {

    public ConsultaPeriodo() {
        setTitle("Cadastro de Periodo de vendas");
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaPeriodo();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaPeriodo lis = (FrmListaPeriodo) tbDados.getModel();
        Periodo p = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        p = em.find(Periodo.class, p.getId());
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(p);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaPeriodo lis = (FrmListaPeriodo) tbDados.getModel();
        Periodo p = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        p = em.find(Periodo.class, p.getId());
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadPeriodo md = new FrmCadPeriodo(null, true);
            md.setEntidade(p);
            md.entidadeTela();
            md.setVisible(true);
            p = md.getEntidade();
            if (p != null) {
                em.getTransaction().begin();
                em.merge(p);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Periodo p = new Periodo();
        FrmCadPeriodo md = new FrmCadPeriodo(null, true);
        md.setEntidade(p);
        md.setVisible(true);
        p = md.getEntidade();
        if (p != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaPeriodo());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
