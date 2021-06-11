package dao;

import app.Principal;
import entidades.Genero;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadGenero;
import view.FrmListaGenero;

public class ConsultaGenero extends FrmListaGenerico {

    public ConsultaGenero() {
        setTitle("Cadastro de Genero");
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaGenero();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaGenero lis = (FrmListaGenero) tbDados.getModel();
        Genero g = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        g = em.find(Genero.class, g.getId());
        if (g == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(g);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaGenero lis = (FrmListaGenero) tbDados.getModel();
        Genero g = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        g = em.find(Genero.class, g.getId());
        if (g == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadGenero md = new FrmCadGenero(null, true);
            md.setEntidade(g);
            md.entidadeTela();
            md.setVisible(true);
            g = md.getEntidade();
            if (g != null) {
                em.getTransaction().begin();
                em.merge(g);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Genero g = new Genero();
        FrmCadGenero md = new FrmCadGenero(null, true);
        md.setEntidade(g);
        md.setVisible(true);
        g = md.getEntidade();
        if (g != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaGenero());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
