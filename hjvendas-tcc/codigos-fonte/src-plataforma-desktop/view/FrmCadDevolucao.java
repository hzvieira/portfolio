package view;

import app.Principal;
import dao.ConsultaCliente;
import dao.ConsultaProduto;
import entidades.Cliente;
import entidades.Devolucao;
import entidades.Produto;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class FrmCadDevolucao extends javax.swing.JDialog {

    public Devolucao entidade;
    public Cliente cliente;
    public Produto produto;
    private List<Devolucao> lista;

    class AcaoSelecionaCliente extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            ConsultaCliente consulta = new ConsultaCliente();
            consulta.setVisible(true);
            cliente = (Cliente) consulta.selecionar();
            consulta.setVisible(false);
            tfCli.setText(cliente.getNome());
        }
    }

    class AcaoSelecionaProduto extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            ConsultaProduto consulta = new ConsultaProduto();
            consulta.setVisible(true);
            produto = (Produto) consulta.selecionar();
            consulta.setVisible(false);
            tfProduto.setText(produto.getNome());
        }
    }

    // Passa para alterar
    public void entidadeTela() {
        tfCli.setText(entidade.getCliente().getNome());
        tfData.setText(entidade.getDataDevolucao().toString());
        tfProduto.setText(entidade.getProduto().getNome());
    }

    // Recebe
    public void telaEntidade() {

        entidade.setCliente(cliente);

        try {
            entidade.setDataDevolucao(new SimpleDateFormat("dd/mm/yyyy").parse(tfData.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadDevolucao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data invalida, formato dd/mm/aaaa");
        }
        entidade.setProduto(produto);
    }

    public Devolucao getEntidade() {
        return entidade;
    }

    public void setEntidade(Devolucao entidade) {
        this.entidade = entidade;
    }

    private boolean valida() {
        boolean retorno = true;
        System.out.println("tfNome.getText(): " + tfCli.getText());
        if (tfCli.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }

        if (tfData.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }

        if (tfProduto.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }

        return retorno; //Validou
    }

    public boolean validaQuery() {
        boolean retorno = false;

        lista = Principal.emf.createEntityManager().createNativeQuery("select ip.entregue from "
                + "pedido p Inner Join itenspedido ip on (ip.id = p.id) Inner Join produto pro on (ip.produto_id = p.id)"
                + "where ip.entregue = true and p.id = " + produto.getId()).getResultList();

        if (lista.size() == 1) {
            retorno = true;
        } else {
            //msg.setText("Este produto não foi adquirido por este cliente!");
            JOptionPane.showMessageDialog(null, "Este produto não foi adquirido por este cliente!");
        }
        
        return retorno;
    }
    private AcaoSelecionaCliente acaoSelecionaCliente = new AcaoSelecionaCliente();
    private AcaoSelecionaProduto acaoSelecionaProduto = new AcaoSelecionaProduto();

    /** Creates new form  */
    public FrmCadDevolucao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        tfData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        tfCli.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ob = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfCli = new javax.swing.JTextField();
        btCli = new javax.swing.JButton();
        ob1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ob3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfProduto = new javax.swing.JTextField();
        btPro = new javax.swing.JButton();
        msg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tfData = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        ob.setForeground(java.awt.Color.red);
        ob.setText("(*)");
        ob.setEnabled(false);

        jLabel2.setText("Cliente:");

        btCli.setAction(acaoSelecionaCliente);
        btCli.setText("Buscar");
        btCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCliActionPerformed(evt);
            }
        });

        ob1.setForeground(java.awt.Color.red);
        ob1.setText("(*)");
        ob1.setEnabled(false);

        jLabel3.setText("Data:");

        ob3.setForeground(java.awt.Color.red);
        ob3.setText("(*)");
        ob3.setEnabled(false);

        jLabel5.setText("Produto: ");

        btPro.setAction(acaoSelecionaProduto);
        btPro.setText("Buscar");
        btPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProActionPerformed(evt);
            }
        });

        msg.setForeground(java.awt.Color.red);

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        try {
            tfData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ob3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ob1)
                                .addComponent(ob)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(tfCli, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btCli)
                                    .addComponent(btPro))
                                .addGap(30, 30, 30))
                            .addComponent(tfData, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ob)
                        .addComponent(jLabel2)
                        .addComponent(tfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ob1)
                        .addComponent(jLabel3))
                    .addComponent(tfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ob3)
                    .addComponent(tfProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (valida() && validaQuery()) {
            telaEntidade();
            setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        entidade = null;
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProActionPerformed
    }//GEN-LAST:event_btProActionPerformed

    private void btCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btCliActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FrmCadDevolucao dialog = new FrmCadDevolucao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCli;
    private javax.swing.JButton btPro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel msg;
    private javax.swing.JLabel ob;
    private javax.swing.JLabel ob1;
    private javax.swing.JLabel ob3;
    private javax.swing.JTextField tfCli;
    private javax.swing.JFormattedTextField tfData;
    private javax.swing.JTextField tfProduto;
    // End of variables declaration//GEN-END:variables
}
