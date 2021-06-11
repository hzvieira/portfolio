package view;

import entidades.Cliente;

public class FrmReceberDinheiro extends javax.swing.JDialog {

    public Cliente entidade;

    public void entidadeTela() {
        tfNome.setText(entidade.getNome());
        System.out.println(entidade.getSaldo());
        tfSaldoAtual.setText(entidade.getSaldo().toString());
    }

    public void telaEntidade() {
        String valorTextField = null;
        try {
            if (tfValorRecebido.getText().contains(",")) {
               valorTextField = tfValorRecebido.getText().replace(",", ".");
            } else {
               valorTextField = tfValorRecebido.getText();
            }

            System.out.println("Transformando valores com virgula para ponto");
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Formato inválido!!!: " + e.toString());
        }
        entidade.setNome(tfNome.getText());
        Double novoSaldo = Double.parseDouble(valorTextField) + entidade.getSaldo();
        System.out.println("Saldo: " + novoSaldo );
        entidade.setSaldo(novoSaldo);
    }

    public Cliente getEntidade() {
        return entidade;
    }

    public void setEntidade(Cliente entidade) {
        this.entidade = entidade;
    }

    private boolean valida() {
        boolean retorno = true;
        System.out.println("tfNome.getText(): " + tfNome.getText());
        if (tfNome.getText().trim().length() == 0){
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }
        
        if ((tfValorRecebido.getText().trim().length() == 0) || 
                (tfValorRecebido.getText().trim().equals(",")) ||
                (tfValorRecebido.getText().trim().equals(".")))  {
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }


        return retorno; //Validou
    }

    /** Creates new form FrmCadCor */
    public FrmReceberDinheiro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        tfNome.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        msg = new javax.swing.JLabel();
        ob = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfSaldoAtual = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ob1 = new javax.swing.JLabel();
        tfValorRecebido = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel2.setText("Cliente:");

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

        msg.setForeground(java.awt.Color.red);

        ob.setForeground(java.awt.Color.red);
        ob.setText("(*)");
        ob.setEnabled(false);

        jLabel1.setText("Saldo atual:");

        tfSaldoAtual.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setText("Valor recebido:");

        ob1.setForeground(java.awt.Color.red);
        ob1.setText("(*)");
        ob1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(ob)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ob1)
                                    .addGap(6, 6, 6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfValorRecebido, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                                        .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(6, 6, 6)
                                    .addComponent(jButton2))))
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ob)
                    .addComponent(jLabel2)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ob1)
                    .addComponent(tfValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FrmReceberDinheiro dialog = new FrmReceberDinheiro(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel msg;
    private javax.swing.JLabel ob;
    private javax.swing.JLabel ob1;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfSaldoAtual;
    private javax.swing.JTextField tfValorRecebido;
    // End of variables declaration//GEN-END:variables
}
