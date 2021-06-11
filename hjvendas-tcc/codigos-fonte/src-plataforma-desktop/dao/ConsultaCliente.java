package dao;

import app.Principal;
import entidades.Cliente;
import entidades.Relacionamento;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.FrmListaGenerico;
import view.FrmCadCliente;
import view.FrmListaCliente;


public class ConsultaCliente extends FrmListaGenerico {

    public ConsultaCliente() {
        setTitle("Cadastro de Cliente");
    }

    @Override
    public TableModel getTableModel() {
        return new FrmListaCliente();
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaCliente lis = (FrmListaCliente) tbDados.getModel();
        Cliente cli = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        cli = em.find(Cliente.class, cli.getId());
        if (cli == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            for (Relacionamento relacoes : lis.getRelacao(cli))
            {
                em.remove(relacoes);
            }
            em.remove(cli);
            em.getTransaction().commit();

        }
        atualizaTabela();
    }

    @Override
    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaCliente lis = (FrmListaCliente) tbDados.getModel();
        Cliente cli = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        cli = em.find(Cliente.class, cli.getId());
        if (cli == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadCliente md = new FrmCadCliente(null, true);
            md.setEntidade(cli);
            md.entidadeTela();
            md.setVisible(true);
            cli = md.getEntidade();
            if (cli != null) {
                em.getTransaction().begin();
                em.merge(cli);
                em.getTransaction().commit();
            }
        }
        atualizaTabela();
    }

    @Override
    public void inserir() {
        Cliente cli = new Cliente();
        FrmCadCliente md = new FrmCadCliente(null, true);
        md.setEntidade(cli);
        md.setVisible(true);
        cli = md.getEntidade();
        if (cli != null) {
            cli.setSaldo(0d);
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(cli);
            em.getTransaction().commit();
            for (Relacionamento rel : md.getListaRelacionamento())
            {
                JOptionPane.showMessageDialog(null, "Relacionado!");
                em.getTransaction().begin();
                rel.getRelacionamento().setSaldo(0d);
                rel.getRelacionamento().setDataNascimento(null);
                rel.getRelacionamento().setRenda(0d);
                em.persist(rel.getRelacionamento());
                rel.setCliente(cli);
                em.persist(rel);
                em.getTransaction().commit();
            }
            atualizaTabela();
        }
    }

    private void atualizaTabela() {
        tbDados.setModel(new FrmListaCliente());
        tbDados.updateUI();
    }

    @Override
    public Object selecionar() {
        FrmListaCliente lis = (FrmListaCliente) tbDados.getModel();
        int linhaSelecionada = tbDados.getSelectedRow();
        Cliente cli = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        cli = em.find(Cliente.class, cli.getId());
        return cli;
    }
}
