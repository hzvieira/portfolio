package dao;

import app.Principal;
import entidades.Fornecedor;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadFornecedor;
import view.FrmListaFornecedor;

public class ConsultaFornecedor extends FrmListaGenerico {

    public ConsultaFornecedor() {
        setTitle("Cadastro de Fornecedor");
        btSelecionar.setVisible(false);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaFornecedor();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaFornecedor lis = (FrmListaFornecedor) tbDados.getModel();
        Fornecedor forn = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        forn = em.find(Fornecedor.class, forn.getId());
        if (forn == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(forn);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaFornecedor lis = (FrmListaFornecedor) tbDados.getModel();
        Fornecedor forn = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        forn = em.find(Fornecedor.class, forn.getId());
        if (forn == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadFornecedor md = new FrmCadFornecedor(null, true);
            md.setEntidade(forn);
            md.entidadeTela();
            md.setVisible(true);
            forn = md.getEntidade();
            if (forn != null) {
                em.getTransaction().begin();
                em.merge(forn);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Fornecedor forn = new Fornecedor();
        FrmCadFornecedor md = new FrmCadFornecedor(null, true);
        md.setEntidade(forn);
        md.setVisible(true);
        forn = md.getEntidade();
        if (forn != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(forn);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaFornecedor());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
