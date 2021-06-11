package dao;

import app.Principal;
import entidades.ParcelasPagar;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadParcelasPagar;
import view.FrmListaParcelasPagar;


public class ConsultaParcelasPagar extends FrmListaGenerico {

    public ConsultaParcelasPagar() {
        setTitle("Cadastro de Parcelas a Pagar");
        painelBotoes.remove(btSelecionar);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaParcelasPagar();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaParcelasPagar lis = (FrmListaParcelasPagar) tbDados.getModel();
        ParcelasPagar pp = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        pp = em.find(ParcelasPagar.class, pp.getId());
        if (pp == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(pp);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaParcelasPagar lis = (FrmListaParcelasPagar) tbDados.getModel();
        ParcelasPagar pp = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        pp = em.find(ParcelasPagar.class, pp.getId());
        if (pp == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadParcelasPagar md = new FrmCadParcelasPagar(null, true);
            md.setEntidade(pp);
            md.entidadeTela();
            md.setVisible(true);
            pp = md.getEntidade();
            if (pp != null) {
                em.getTransaction().begin();
                em.merge(pp);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        ParcelasPagar pp = new ParcelasPagar();
        FrmCadParcelasPagar md = new FrmCadParcelasPagar(null, true);
        md.setEntidade(pp);
        md.setVisible(true);
        pp = md.getEntidade();
        if (pp != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(pp);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaParcelasPagar());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
