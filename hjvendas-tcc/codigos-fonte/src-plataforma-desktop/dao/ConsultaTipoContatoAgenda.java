package dao;

import app.Principal;
import entidades.TipoContatoAgenda;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadTipoContatoAgenda;
import view.FrmListaTipoContatoAgenda;

public class ConsultaTipoContatoAgenda extends FrmListaGenerico {

    public ConsultaTipoContatoAgenda() {
        setTitle("Cadastro de Tipo de contato agenda");
        btSelecionar.setVisible(false);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaTipoContatoAgenda();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaTipoContatoAgenda lis = (FrmListaTipoContatoAgenda) tbDados.getModel();
        TipoContatoAgenda tca = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        tca = em.find(TipoContatoAgenda.class, tca.getId());
        if (tca == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(tca);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaTipoContatoAgenda lis = (FrmListaTipoContatoAgenda) tbDados.getModel();
        TipoContatoAgenda tca = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        tca = em.find(TipoContatoAgenda.class, tca.getId());
        if (tca == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadTipoContatoAgenda md = new FrmCadTipoContatoAgenda(null, true);
            md.setEntidade(tca);
            md.entidadeTela();
            md.setVisible(true);
            tca = md.getEntidade();
            if (tca != null) {
                em.getTransaction().begin();
                em.merge(tca);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        TipoContatoAgenda tca = new TipoContatoAgenda();
        FrmCadTipoContatoAgenda md = new FrmCadTipoContatoAgenda(null, true);
        md.setEntidade(tca);
        md.setVisible(true);
        tca = md.getEntidade();
        if (tca != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(tca);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaTipoContatoAgenda());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
