package dao;

import app.Principal;
import entidades.Promocao;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadPromocao;
import view.FrmListaPromocao;

public class ConsultaPromocao extends FrmListaGenerico {

    public ConsultaPromocao() {
        setTitle("Cadastro de Promoção");
        painelBotoes.remove(btSelecionar);
        painelBotoes.remove(btExcluir);
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaPromocao();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaPromocao lis = (FrmListaPromocao) tbDados.getModel();
        Promocao pro = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        pro = em.find(Promocao.class, pro.getId());
        if (pro == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            em.remove(pro);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaPromocao lis = (FrmListaPromocao) tbDados.getModel();
        Promocao pro = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        pro = em.find(Promocao.class, pro.getId());
        if (pro == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadPromocao md = new FrmCadPromocao(null, true);
            md.setEntidade(pro);
            md.entidadeTela();
            md.setVisible(true);
            pro = md.getEntidade();
            if (pro != null) {
                em.getTransaction().begin();
                em.merge(pro);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Promocao pro = new Promocao();
        FrmCadPromocao md = new FrmCadPromocao(null, true);
        md.setEntidade(pro);
        md.setVisible(true);
        pro = md.getEntidade();
        if (pro != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(pro);
            em.getTransaction().commit();
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaPromocao());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
