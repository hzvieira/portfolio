package dao;

import app.Principal;
import entidades.Cor;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadCor;
import view.FrmListaCor;

public class ConsultaCor extends FrmListaGenerico {

    public ConsultaCor() {
        setTitle("Cadastro de Cor");
        painelBotoes.remove(btSelecionar);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaCor();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaCor lis = (FrmListaCor) tbDados.getModel();
        Cor cor = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        cor = em.find(Cor.class, cor.getId());
        if (cor == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(cor);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaCor lis = (FrmListaCor) tbDados.getModel();
        Cor cor = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        cor = em.find(Cor.class, cor.getId());
        if (cor == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadCor md = new FrmCadCor(null, true);
            md.setEntidade(cor);
            md.entidadeTela();
            md.setVisible(true);
            cor = md.getEntidade();
            if (cor != null) {
                em.getTransaction().begin();
                em.merge(cor);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Cor cor = new Cor();
        FrmCadCor md = new FrmCadCor(null, true);
        md.setEntidade(cor);
        md.setVisible(true);
        cor = md.getEntidade();
        if (cor != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(cor);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaCor());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
