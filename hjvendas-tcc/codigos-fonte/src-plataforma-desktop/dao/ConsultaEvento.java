package dao;

import app.Principal;
import entidades.Evento;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadEvento;
import view.FrmListaEvento;

public class ConsultaEvento extends FrmListaGenerico {

    public ConsultaEvento() {
        setTitle("Cadastro de Evento");
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaEvento();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaEvento lis = (FrmListaEvento) tbDados.getModel();
        Evento evento = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        evento = em.find(Evento.class, evento.getId());
        if (evento == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(evento);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaEvento lis = (FrmListaEvento) tbDados.getModel();
        Evento evento = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        evento = em.find(Evento.class, evento.getId());
        if (evento == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadEvento md = new FrmCadEvento(null, true);
            md.setEntidade(evento);
            md.entidadeTela();
            md.setVisible(true);
            evento = md.getEntidade();
            if (evento != null) {
                em.getTransaction().begin();
                em.merge(evento);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Evento evento = new Evento();
        FrmCadEvento md = new FrmCadEvento(null, true);
        md.setEntidade(evento);
        md.setVisible(true);
        evento = md.getEntidade();
        if (evento != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaEvento());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
