package view;

import dao.ConsultaFornecedor;
import dao.ConsultaParcelasPagar;
import entidades.Fornecedor;
import entidades.ParcelasPagar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ComboBoxModelGenerico;

public class FrmCadParcelasPagar extends javax.swing.JDialog {

    public ParcelasPagar entidade;

    // Passa para alterar
    public void entidadeTela() {
        tfDataCompra.setText(entidade.getDataCompra().toString());
        tfDataVencimento.setText(entidade.getDataVencimento().toString());
        tfDataPagamento.setText(entidade.getDataPagamento().toString());
        cbFornecedor.setSelectedItem(entidade.getFornecedor().getNome());
    }

    // Recebe
    public void telaEntidade() {
        System.out.println("" + tfDataPagamento.getText().trim().length());

        if (tfDataPagamento.getText().trim().length() == 4) {
            entidade.setDataPagamento(null);
        } else {
            try {
                entidade.setDataPagamento(new SimpleDateFormat("dd/mm/yyyy").parse(tfDataPagamento.getText()));
            } catch (ParseException ex) {
                Logger.getLogger(FrmCadParcelasPagar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            entidade.setDataCompra(new SimpleDateFormat("dd/mm/yyyy").parse(tfDataCompra.getText()));
            entidade.setDataVencimento(new SimpleDateFormat("dd/mm/yyyy").parse(tfDataVencimento.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadParcelasPagar.class.getName()).log(Level.SEVERE, null, ex);

        }
        entidade.setFornecedor((Fornecedor) cbFornecedor.getSelectedItem());
    }

    public ParcelasPagar getEntidade() {
        return entidade;
    }

    public void setEntidade(ParcelasPagar entidade) {
        this.entidade = entidade;
    }

    private boolean valida() {
        boolean retorno = true;
        //System.out.println("tfNome.getText(): " + tfDataCompra.getText());
        if (tfDataCompra.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        if (tfDataVencimento.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        if (!retorno) {
            JOptionPane.showMessageDialog(null, "Data invalida, formato dd/mm/aaaa");
        }

        if (cbFornecedor.getSelectedItem() == null) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        return retorno; //Validou
    }

    /** Creates new form FrmCadCor */
    public FrmCadParcelasPagar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        tfDataCompra.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ob = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbFornecedor = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        ob1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ob2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        msg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tfDataCompra = new javax.swing.JFormattedTextField();
        tfDataVencimento = new javax.swing.JFormattedTextField();
        tfDataPagamento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        ob.setForeground(java.awt.Color.red);
        ob.setText("(*)");
        ob.setEnabled(false);

        jLabel2.setText("Forncedor:");

        cbFornecedor.setModel(new ComboBoxModelGenerico(Fornecedor.class));

        jButton3.setText("Novo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        ob1.setForeground(java.awt.Color.red);
        ob1.setText("(*)");
        ob1.setEnabled(false);

        jLabel3.setText("Data compra:");

        ob2.setForeground(java.awt.Color.red);
        ob2.setText("(*)");
        ob2.setEnabled(false);

        jLabel4.setText("Data vencimento:");

        jLabel5.setText("Data pagamento:");

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
            tfDataCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfDataVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ob1)
                                .addComponent(ob))
                            .addComponent(ob2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addGap(19, 19, 19))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(tfDataCompra, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tfDataVencimento, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tfDataPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ob)
                    .addComponent(cbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(ob1)
                        .addComponent(tfDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ob2)
                            .addComponent(tfDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (valida()) {
            telaEntidade();
            setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        entidade = null;
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ConsultaFornecedor con = new ConsultaFornecedor();
        con.inserir();
        cbFornecedor.setModel(new ComboBoxModelGenerico(Fornecedor.class));
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FrmCadParcelasPagar dialog = new FrmCadParcelasPagar(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cbFornecedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel msg;
    private javax.swing.JLabel ob;
    private javax.swing.JLabel ob1;
    private javax.swing.JLabel ob2;
    private javax.swing.JFormattedTextField tfDataCompra;
    private javax.swing.JTextField tfDataPagamento;
    private javax.swing.JFormattedTextField tfDataVencimento;
    // End of variables declaration//GEN-END:variables
}
