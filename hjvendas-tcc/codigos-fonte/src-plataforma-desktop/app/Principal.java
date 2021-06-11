package app;

import dao.ConnectionFactory;
import dao.ConsultaAgenda;
import dao.ConsultaCidade;
import dao.ConsultaCliente;
import dao.ConsultaContasReceber;
import dao.ConsultaCor;
import dao.ConsultaDevolucao;
import dao.ConsultaEvento;
import dao.ConsultaFornecedor;
import dao.ConsultaGenero;
import dao.ConsultaParcelasPagar;
import dao.ConsultaParteDoCorpo;
import dao.ConsultaPeriodo;
import dao.ConsultaProduto;
import dao.ConsultaPromocao;
import dao.ConsultaProntaEntrega;
import dao.ConsultaTipoContatoAgenda;
import dao.ConsultaTipoProduto;
import dao.ConsultaTipoRelacionamento;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.FrmPedido;

public class Principal extends javax.swing.JFrame {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoIntegrador");
    public static EntityManager em = Principal.emf.createEntityManager();

    class AcaoCadastroCor extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaCor().setVisible(true);
        }
    }

    class AcaoCadastroTipoRelacionamento extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaTipoRelacionamento().setVisible(true);
        }
    }

    class AcaoCadastroTipoContatoAgenda extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaTipoContatoAgenda().setVisible(true);
        }
    }

    class AcaoCadastroParteDoCorpo extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaParteDoCorpo().setVisible(true);
        }
    }

    class AcaoCadastroEvento extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaEvento().setVisible(true);
        }
    }

    class AcaoCadastroTipoProduto extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaTipoProduto().setVisible(true);
        }
    }

    class AcaoCadastroFornecedor extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaFornecedor().setVisible(true);
        }
    }

    class AcaoCadastroCidade extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaCidade().setVisible(true);
        }
    }

    class AcaoCadastroPeriodo extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaPeriodo().setVisible(true);
        }
    }

    class AcaoCadastroCliente extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaCliente().setVisible(true);
        }
    }

    class AcaoCadastroProduto extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaProduto().setVisible(true);
        }
    }

    class AcaoCadastroGenero extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaGenero().setVisible(true);
        }
    }

    class AcaoPedido extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new FrmPedido(null, true).setVisible(true);
        }
    }

    class AcaoCadastroDevolucao extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaDevolucao().setVisible(true);
        }
    }

    class AcaoCadastroProntaEntrega extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaProntaEntrega().setVisible(true);
        }
    }

    class AcaoCadastroParcelaPagar extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaParcelasPagar().setVisible(true);
        }
    }

    class AcaoContasReceber extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaContasReceber().setVisible(true);
        }
    }

    class AcaoCadastroPromocao extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaPromocao().setVisible(true);
        }
    }

    class AcaoCadastroAgenda extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            new ConsultaAgenda().setVisible(true);
        }
    }

    class AcaoRelatorio1 extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            URL arquivo = getClass().getResource("../relatorios/ProdFor.jasper");
            JasperReport jasperReport;

            try {
                jasperReport = (JasperReport) JRLoader.loadObject(arquivo);
                
                Map parametros = new HashMap();
                parametros.put("where", "1=1");

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,  ConnectionFactory.criarConexao());

                JasperViewer jrviewer = new JasperViewer(jasperPrint, false);

                JOptionPane.showMessageDialog(null, "Exibindo relatório!");
                jrviewer.setVisible(true);

            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, "lalalalla", ex);

            }
        }
    }

    class AcaoRelatorioMelhoresCLientes extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            try {
                em.createNativeQuery("DELETE FROM melhores");
                em.createNativeQuery("INSERT INTO melhores SELECT cli.id, cli.nome, " +
                        "SUM(ip.quantidade * ip.valor) as total, 0 as perc " +
                        "from pessoa cli JOIN pedido ped ON (cli.id = ped.cliente_id) " +
                        "JOIN itenspedido ip ON (ped.id = ip.pedido_id) " +
                        "LEFT JOIN produto p ON (ip.produto_id = p.id) " +
                        "GROUP BY 1,2");
                em.createNativeQuery("UPDATE melhores set percentual = valor/ (select sum(valor) from melhores) * 100");

                URL arquivo = getClass().getResource("../relatorios/melhores.jasper");
                JasperReport jasperReport;

                jasperReport = (JasperReport) JRLoader.loadObject(arquivo);

                Map parametros = new HashMap();
                parametros.put("where", "1=1");

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,  ConnectionFactory.criarConexao());

                JasperViewer jrviewer = new JasperViewer(jasperPrint, false);

                JOptionPane.showMessageDialog(null, "Relatório de melhores clientes");
                jrviewer.setVisible(true);

            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, "lalalalla", ex);
                JOptionPane.showMessageDialog(null, "Não possível executar a SQL");
            }
        }
    }

    class AcaoRelatorioRelacionamento extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            URL arquivo = getClass().getResource("../relatorios/relacionamento.jasper");
            JasperReport jasperReport;

            try {
                jasperReport = (JasperReport) JRLoader.loadObject(arquivo);

                Map parametros = new HashMap();
                parametros.put("where", "1=1");

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,  ConnectionFactory.criarConexao());

                JasperViewer jrviewer = new JasperViewer(jasperPrint, false);

                JOptionPane.showMessageDialog(null, "Exibindo relatório!");
                jrviewer.setVisible(true);

            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, "lalalalla", ex);

            }
        }
    }

    
    private AcaoCadastroCor acaoCadastroCor = new AcaoCadastroCor();
    private AcaoCadastroTipoRelacionamento acaoCadastroTipoRelacionamento = new AcaoCadastroTipoRelacionamento();
    private AcaoCadastroTipoContatoAgenda acaoCadastroTipoContatoAgenda = new AcaoCadastroTipoContatoAgenda();
    private AcaoCadastroParteDoCorpo acaoCadastroParteDoCorpo = new AcaoCadastroParteDoCorpo();
    private AcaoCadastroEvento acaoCadastroEvento = new AcaoCadastroEvento();
    private AcaoCadastroTipoProduto acaoCadastroTipoProduto = new AcaoCadastroTipoProduto();
    private AcaoCadastroFornecedor acaoCadastroFornecedor = new AcaoCadastroFornecedor();
    private AcaoCadastroCidade acaoCadastroCidade = new AcaoCadastroCidade();
    private AcaoCadastroPeriodo acaoCadastroPeriodo = new AcaoCadastroPeriodo();
    private AcaoCadastroCliente acaoCadastroCliente = new AcaoCadastroCliente();
    private AcaoCadastroProduto acaoCadastroProduto = new AcaoCadastroProduto();
    private AcaoCadastroGenero acaoCadastroGenero = new AcaoCadastroGenero();
    private AcaoCadastroDevolucao acaoCadastroDevolucao = new AcaoCadastroDevolucao();
    private AcaoCadastroProntaEntrega acaoCadastroProntaEntrega = new AcaoCadastroProntaEntrega();
    private AcaoCadastroParcelaPagar acaoCadastroParcelaPagar = new AcaoCadastroParcelaPagar();
    private AcaoContasReceber acaoContasReceber = new AcaoContasReceber();
    private AcaoCadastroPromocao acaoCadastroPromocao = new AcaoCadastroPromocao();
    private AcaoCadastroAgenda acaoCadastroAgenda = new AcaoCadastroAgenda();
    // 1 - Produtos por fornecedor
    private AcaoRelatorio1 acaoRelatorio1 = new AcaoRelatorio1();
    private AcaoRelatorioMelhoresCLientes acaoRelatorioMelhoresCLientes = new AcaoRelatorioMelhoresCLientes();
    private AcaoRelatorioRelacionamento acaoRelatorioRelacionamento = new AcaoRelatorioRelacionamento();

    public Principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Cadastro");

        jMenu3.setText("Agenda");

        jMenuItem1.setAction(acaoCadastroAgenda);
        jMenuItem1.setText("Horarios");
        jMenu3.add(jMenuItem1);

        jMenuItem3.setAction(acaoCadastroTipoContatoAgenda);
        jMenuItem3.setText("Tipo de contato");
        jMenu3.add(jMenuItem3);

        jMenu1.add(jMenu3);

        jMenu4.setText("Cliente");

        jMenuItem4.setAction(acaoCadastroCliente);
        jMenuItem4.setText("Cliente");
        jMenu4.add(jMenuItem4);

        jMenuItem5.setAction(acaoCadastroTipoRelacionamento);
        jMenuItem5.setText("Tipo Relacionamento");
        jMenu4.add(jMenuItem5);

        jMenu1.add(jMenu4);

        jMenu5.setText("Fornecedor");

        jMenuItem6.setAction(acaoCadastroFornecedor);
        jMenuItem6.setText("Fornecedor");
        jMenu5.add(jMenuItem6);

        jMenuItem7.setAction(acaoCadastroPeriodo);
        jMenuItem7.setText("Periodo de vendas");
        jMenu5.add(jMenuItem7);

        jMenu1.add(jMenu5);

        jMenu6.setText("Produto");

        jMenuItem8.setAction(acaoCadastroProduto);
        jMenuItem8.setText("Produto");
        jMenu6.add(jMenuItem8);

        jMenuItem10.setAction(acaoCadastroPromocao);
        jMenuItem10.setText("Promoção");
        jMenu6.add(jMenuItem10);

        jMenuItem11.setAction(acaoCadastroParteDoCorpo);
        jMenuItem11.setText("Parte do corpo");
        jMenu6.add(jMenuItem11);

        jMenuItem9.setAction(acaoCadastroTipoProduto);
        jMenuItem9.setText("Tipo produto");
        jMenu6.add(jMenuItem9);

        jMenuItem12.setAction(acaoCadastroCor);
        jMenuItem12.setText("Cor");
        jMenu6.add(jMenuItem12);

        jMenuItem20.setAction(acaoCadastroGenero);
        jMenuItem20.setText("Genero");
        jMenu6.add(jMenuItem20);

        jMenu1.add(jMenu6);

        jMenuItem2.setAction(acaoCadastroEvento);
        jMenuItem2.setText("Evento");
        jMenu1.add(jMenuItem2);

        jMenuItem19.setAction(acaoCadastroCidade);
        jMenuItem19.setText("Cidade");
        jMenu1.add(jMenuItem19);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Movimentações");

        jMenuItem13.setAction(acaoCadastroDevolucao);
        jMenuItem13.setText("Devolução");
        jMenu2.add(jMenuItem13);

        jMenuItem14.setAction(new AcaoPedido());
        jMenuItem14.setText("Pedido");
        jMenu2.add(jMenuItem14);

        jMenuItem15.setAction(acaoCadastroProntaEntrega);
        jMenuItem15.setText("Pronta Entrega");
        jMenu2.add(jMenuItem15);

        jMenuItem18.setText("Pedido para Fornecedor");
        jMenu2.add(jMenuItem18);

        jMenu7.setText("Financeiro");

        jMenuItem16.setAction(acaoCadastroParcelaPagar);
        jMenuItem16.setText("Contas a Pagar");
        jMenu7.add(jMenuItem16);

        jMenuItem17.setAction(acaoContasReceber);
        jMenuItem17.setText("Contas a Receber");
        jMenu7.add(jMenuItem17);

        jMenu2.add(jMenu7);

        jMenuBar1.add(jMenu2);

        jMenu8.setText("Relatórios");

        jMenuItem21.setAction(acaoRelatorio1);
        jMenuItem21.setText("Produtos por fornecedor");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem21);

        jMenuItem22.setAction(acaoRelatorioMelhoresCLientes);
        jMenuItem22.setText("Melhores clientes");
        jMenu8.add(jMenuItem22);

        jMenuItem23.setAction(acaoRelatorioRelacionamento);
        jMenuItem23.setText("Relacionamentos dos clientes");
        jMenu8.add(jMenuItem23);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
