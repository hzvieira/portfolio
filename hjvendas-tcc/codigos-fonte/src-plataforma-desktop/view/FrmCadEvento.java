package view;

import dao.ConsultaTipoRelacionamento;
import entidades.Evento;
import entidades.TipoRelacionamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ComboBoxModelGenerico;

public class FrmCadEvento extends javax.swing.JDialog {

    public Evento entidade;

    // Passa para alterar
    public void entidadeTela() {
        tfNome.setText(entidade.getDescricao());
        tfDataInicial.setText(entidade.getDataInicial().toString());
        tfDataFinal.setText(entidade.getDataFinal().toString());
        cbRelacao.setSelectedItem(entidade.getTipoRelacionamento().getDescricao());
    }

    // Recebe
    public void telaEntidade() {

        entidade.setDescricao(tfNome.getText());

        try {
            entidade.setDataInicial(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataInicial.getText()));
            entidade.setDataFinal(new SimpleDateFormat("dd/MM/yyyy").parse(tfDataFinal.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadEvento.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data invalida, formato dd/mm/aaaa");
        }
        entidade.setTipoRelacionamento((TipoRelacionamento) cbRelacao.getSelectedItem());
    }

    public Evento getEntidade() {
        return entidade;
    }

    public void setEntidade(Evento entidade) {
        this.entidade = entidade;
    }

    private boolean valida() {
        boolean retorno = true;
        System.out.println("tfNome.getText(): " + tfNome.getText());
        if (tfNome.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        if (tfDataFinal.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        if (tfDataInicial.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        if (cbRelacao.getSelectedItem() == null) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        return retorno; //Validou
    }

    /** Creates new form FrmCadCor */
    public FrmCadEvento(java.awt.Frame parent, boolean modal) {
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfDataInicial = new javax.swing.JTextField();
        tfDataFinal = new javax.swing.JTextField();
        cbRelacao = new javax.swing.JComboBox();
        ob1 = new javax.swing.JLabel();
        ob2 = new javax.swing.JLabel();
        ob3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel2.setText("Descrição:");

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

        jLabel3.setText("Data Inicial:");

        jLabel4.setText("Data Final:");

        jLabel5.setText("Relação:");

        cbRelacao.setModel(new ComboBoxModelGenerico(TipoRelacionamento.class));

        ob1.setForeground(java.awt.Color.red);
        ob1.setText("(*)");
        ob1.setEnabled(false);

        ob2.setForeground(java.awt.Color.red);
        ob2.setText("(*)");
        ob2.setEnabled(false);

        ob3.setForeground(java.awt.Color.red);
        ob3.setText("(*)");
        ob3.setEnabled(false);

        jButton3.setText("Nova relação");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
                            .addComponent(ob2)
                            .addComponent(ob3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfNome, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbRelacao, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))))
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
                        .addComponent(jLabel2)
                        .addComponent(ob))
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ob1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ob2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbRelacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ob3)
                    .addComponent(jButton3))
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
        ConsultaTipoRelacionamento con = new ConsultaTipoRelacionamento();
        con.inserir();
        cbRelacao.setModel(new ComboBoxModelGenerico(TipoRelacionamento.class));
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FrmCadEvento dialog = new FrmCadEvento(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cbRelacao;
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
    private javax.swing.JLabel ob3;
    private javax.swing.JTextField tfDataFinal;
    private javax.swing.JTextField tfDataInicial;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
