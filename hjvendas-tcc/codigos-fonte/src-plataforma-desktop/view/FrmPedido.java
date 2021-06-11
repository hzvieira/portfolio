package view;

import app.Principal;
import dao.ConsultaCliente;
import entidades.Cliente;
import entidades.ItensPedido;
import entidades.Pedido;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class FrmPedido extends javax.swing.JDialog {

    public Pedido entidade = new Pedido();
    public Cliente cliente = new Cliente();
    public List<ItensPedido> itens = new ArrayList<ItensPedido>();
    public Double subTotal = 0d;

    public Pedido getEntidade() {
        return entidade;
    }

    public void setEntidade(Pedido entidade) {
        this.entidade = entidade;
    }

    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }

    public void telaEntidade() {
        try {
            entidade.setCliente(cliente);
            entidade.setDataPedido((Date) new SimpleDateFormat("dd/MM/yyyy").parse(tfData.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(FrmPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void entidadeTela() {
        tfData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }

    class AcaoSelecionaCliente extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            Cliente cli = new Cliente();
            ConsultaCliente consulta = new ConsultaCliente();
            consulta.setVisible(true);
            cli = (Cliente) consulta.selecionar();
            cliente = cli;
            consulta.setVisible(false);
            tfCliente.setText(cliente.getNome());
        }
    }

    public void SelecionaProduto() {
        ItensPedido ip = new ItensPedido();

        FrmInserirProduto frm = new FrmInserirProduto(null, true);
        frm.setEntidade(ip);
        frm.setVisible(true);
        ip = frm.getEntidade();
        if (ip != null) {
            itens.add(ip);
            System.out.println(itens.get(0).toString());
            subTotal += ip.getSubtotal();
            verificaDuplicados();
            tbItens.setModel(new FrmListaItens(itens));
            tbItens.updateUI();
            tfSubTotal.setText(subTotal.toString());
        }
    }

    private void verificaDuplicados() {
        List<ItensPedido> aExcluir = new ArrayList<ItensPedido>();
        for (int i = 0; i < itens.size(); i++) {
            for (int j = i + 1; j < itens.size(); j++) {
                ItensPedido itemAtual = itens.get(i);
                ItensPedido itemDuplicado = itens.get(j);
                if (itemAtual.getProduto().equals(itemDuplicado.getProduto())) {
                    if (itemAtual.isEntregue() == itemDuplicado.isEntregue()) {
                        itemDuplicado.setId((long) j);
                        aExcluir.add(itemDuplicado);
                        itemAtual.adicionaQuantidade(itemDuplicado);
                    }
                }
            }
        }
        for (ItensPedido itemDuplicado : aExcluir) {
            itens.remove(itemDuplicado);
        }
    }

    public void salvarPedido() {

       telaEntidade();

        if (getEntidade() != null) {
            EntityManager em = Principal.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(getEntidade());
            em.getTransaction().commit();
            for (ItensPedido ip : getItens()) {
                em.getTransaction().begin();
                ip.setPedido(getEntidade());
                em.persist(ip);
                em.getTransaction().commit();
            }
            JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso para " + cliente.getNome());
        }

    }

    public boolean valida() {
        boolean retorno = true;
        if (cliente.getId() == null) {
            retorno = false;
            msg.setText("É obrigatório selecionar um cliente !");
        }
        if (itens.isEmpty()) {
            retorno = false;
            msg.setText("É obrigatório selecionar pelo menos um item!");
        }

        return retorno;
    }
    private AcaoSelecionaCliente acaoSelecionaCliente = new AcaoSelecionaCliente();

    public FrmPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tfData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfSubTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItens = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        msg = new javax.swing.JLabel();
        tfData = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cliente: ");

        jLabel2.setText("Data:");

        jLabel3.setText("Total:");

        tfSubTotal.setEditable(false);

        jButton1.setText("Finalizar");
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

        jScrollPane1.setViewportView(tbItens);

        jButton3.setAction(acaoSelecionaCliente);
        jButton3.setText("Pro");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jButton4.setText("Colocar item");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(tfData, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addGap(304, 304, 304))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(31, 31, 31)
                        .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                    .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SelecionaProduto();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (valida()) {
            salvarPedido();
            setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        entidade = null;
        cliente = null;
        itens = null;
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FrmPedido dialog = new FrmPedido(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel msg;
    private javax.swing.JTable tbItens;
    private javax.swing.JTextField tfCliente;
    private javax.swing.JFormattedTextField tfData;
    private javax.swing.JTextField tfSubTotal;
    // End of variables declaration//GEN-END:variables
}
