package dao;

import app.Principal;
import entidades.Produto;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadProduto;
import view.FrmListaProduto;

public class ConsultaProduto extends FrmListaGenerico {

    public ConsultaProduto() {
        setTitle("Cadastro de Produto");
    }

    public TableModel getTableModel() {
        return new FrmListaProduto();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaProduto lis = (FrmListaProduto) tbDados.getModel();
        Produto pro = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        pro = em.find(Produto.class, pro.getId());
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
        FrmListaProduto lis = (FrmListaProduto) tbDados.getModel();
        Produto pro = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        pro = em.find(Produto.class, pro.getId());
        if (pro == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadProduto md = new FrmCadProduto(null, true);
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
        Produto pro = new Produto();
        FrmCadProduto md = new FrmCadProduto(null, true);
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
        tbDados.setModel(new FrmListaProduto());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        FrmListaProduto lis = (FrmListaProduto) tbDados.getModel();
        int linhaSelecionada = tbDados.getSelectedRow();
        Produto pro = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        pro = em.find(Produto.class, pro.getId());
        return pro;
    }

/*    public Produto selProdutoFornecedor(Long l) {
        FrmListaProduto lis = (FrmListaProduto) tbDados.getModel();
        int linhaSelecionada = tbDados.getSelectedRow();
        Produto pro = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        pro = em.find(Produto.class, pro.getId());
        return pro;
    }*/

}
