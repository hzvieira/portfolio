package view;

import app.Principal;
import dao.ConsultaCidade;
import entidades.Cidade;
import entidades.Cliente;
import entidades.Relacionamento;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import util.ComboBoxModelGenerico;

public class FrmCadCliente extends javax.swing.JDialog {

    public Cliente entidade;
    private List<Relacionamento> listaRelacionamento = new ArrayList<Relacionamento>();

    public List<Relacionamento> getListaRelacionamento() {
        return listaRelacionamento;
    }

    public void setListaRelacionamento(List<Relacionamento> listaRelacionamento) {
        this.listaRelacionamento = listaRelacionamento;
    }

    public void entidadeTela() {
        tfNome.setText(entidade.getNome());
        tfBairro.setText(entidade.getBairro());
        taObservacoes.setText(entidade.getObservacoes());
        tfRenda.setText(entidade.getRenda().toString());
        tfComplemento.setText(entidade.getComplemento());
        tfEmail01.setText(entidade.getEmail01());
        tfEmail02.setText(entidade.getEmail02());
        tfNumero.setText(entidade.getNumero().toString());
        tfTelefoneRes.setText(entidade.getTelefoneResidencial().toString());
        tfTelefoneCel.setText(entidade.getTelefoneCelular().toString());
        cbCidade.setSelectedItem(entidade.getCidade());
        tfRua.setText(entidade.getRua());
        tfProfissao.setText(entidade.getProfissao());
        tbDados.setModel(new FrmListaRelacionamento());
        tfCep.setText(entidade.getCep().toString());
        tfNascimento.setText(entidade.getDataNascimento().toString());
    }

    public void telaEntidade() {
        entidade.setNome(tfNome.getText());
        entidade.setBairro(tfBairro.getText());
        entidade.setCep(tfCep.getText().trim());
        entidade.setCidade((Cidade) cbCidade.getSelectedItem());
        entidade.setComplemento(tfComplemento.getText());
        entidade.setEmail01(tfEmail01.getText());
        entidade.setEmail02(tfEmail02.getText());
        entidade.setNumero(Short.parseShort(tfNumero.getText().trim()));
        entidade.setObservacoes(taObservacoes.getText());
        entidade.setTelefoneCelular(tfTelefoneCel.getText().trim());
        entidade.setTelefoneResidencial(tfTelefoneRes.getText().trim());
        entidade.setRua(tfRua.getText());
        entidade.setProfissao(tfProfissao.getText());
        entidade.setRenda(Double.parseDouble(tfRenda.getText()));

        try {
            entidade.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(tfNascimento.getText()));
        } catch (ParseException ex) {
        }
    }

    public Cliente getEntidade() {
        return entidade;
    }

    public void setEntidade(Cliente entidade) {
        this.entidade = entidade;
    }

    private boolean valida() {
        boolean retorno = true;
        if (tfNome.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }
        if (tfTelefoneRes.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }
        if (tfRua.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }
        if (tfNumero.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorios!");
            retorno = false;
        }
        return retorno; //Validou
    }

    /** Creates new form FrmCadCor */
    public FrmCadCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        tfNome.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ob = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfTelefoneRes = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        tfTelefoneCel = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        tfNascimento = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        tfEmail01 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfEmail02 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfRua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfNumero = new javax.swing.JTextField();
        tfComplemento = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfCep = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taObservacoes = new javax.swing.JTextArea();
        msg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ob1 = new javax.swing.JLabel();
        ob3 = new javax.swing.JLabel();
        ob4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbCidade = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        tfProfissao = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDados = new javax.swing.JTable();
        btInserir = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btAtivo = new javax.swing.JButton();
        tfRenda = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        ob.setForeground(java.awt.Color.red);
        ob.setText("(*)");
        ob.setEnabled(false);

        jLabel2.setText("Nome:");

        jLabel4.setText("Telefone:");

        try {
            tfTelefoneRes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Celular:");

        try {
            tfTelefoneCel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setText("Nascimento: ");

        tfNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel6.setText("E-Mail:");

        jLabel7.setText("E-Mail 2:");

        jLabel3.setText("Endereço:");

        jLabel8.setText("Numero:");

        tfComplemento.setText("Complemento:");

        jLabel11.setText("CEP:");

        try {
            tfCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel10.setText("Bairro:");

        jLabel13.setText("Renda: R$");

        jLabel12.setText("Observações:");

        taObservacoes.setColumns(20);
        taObservacoes.setRows(5);
        jScrollPane1.setViewportView(taObservacoes);

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

        ob1.setForeground(java.awt.Color.red);
        ob1.setText("(*)");
        ob1.setEnabled(false);

        ob3.setForeground(java.awt.Color.red);
        ob3.setText("(*)");
        ob3.setEnabled(false);

        ob4.setForeground(java.awt.Color.red);
        ob4.setText("(*)");
        ob4.setEnabled(false);

        jLabel1.setText("Cidade: ");

        cbCidade.setModel(new ComboBoxModelGenerico(Cidade.class));

        jLabel14.setText("Profissão: ");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbDados);

        btInserir.setText("Incluir");
        btInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInserirActionPerformed(evt);
            }
        });

        btAlterar.setText("Alterar");

        btAtivo.setText("Ativar/Desativar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btInserir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAtivo))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btInserir)
                    .addComponent(btAlterar)
                    .addComponent(btAtivo))
                .addContainerGap())
        );

        tfRenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        jButton3.setText("Nova cidade");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel15.setText("Relacionados:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(ob)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addComponent(tfEmail02, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ob3)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(5, 5, 5)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(ob1)
                            .addComponent(jLabel4)
                            .addGap(3, 3, 3)
                            .addComponent(tfTelefoneRes, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)
                            .addComponent(jLabel5)
                            .addGap(6, 6, 6)
                            .addComponent(tfTelefoneCel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9)
                            .addGap(3, 3, 3)
                            .addComponent(tfNascimento))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(jLabel6)
                            .addGap(27, 27, 27)
                            .addComponent(tfEmail01, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRenda, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ob4)
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfComplemento))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(10, 10, 10)
                                        .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel11)
                                        .addGap(6, 6, 6)
                                        .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)))))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ob))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ob1)
                    .addComponent(jLabel4)
                    .addComponent(tfTelefoneRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tfTelefoneCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addComponent(tfNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6))
                    .addComponent(tfEmail01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7))
                    .addComponent(tfEmail02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(ob3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3))
                    .addComponent(tfRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ob4)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(tfComplemento))
                            .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel11))
                            .addComponent(tfCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfRenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(tfProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
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
        ConsultaCidade con = new ConsultaCidade();
        con.inserir();
        cbCidade.setModel(new ComboBoxModelGenerico(Cidade.class));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInserirActionPerformed
        inserir();
    }//GEN-LAST:event_btInserirActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FrmCadCliente dialog = new FrmCadCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btAtivo;
    private javax.swing.JButton btInserir;
    private javax.swing.JComboBox cbCidade;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel msg;
    private javax.swing.JLabel ob;
    private javax.swing.JLabel ob1;
    private javax.swing.JLabel ob3;
    private javax.swing.JLabel ob4;
    private javax.swing.JTextArea taObservacoes;
    private javax.swing.JTable tbDados;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JFormattedTextField tfCep;
    private javax.swing.JLabel tfComplemento;
    private javax.swing.JTextField tfEmail01;
    private javax.swing.JTextField tfEmail02;
    private javax.swing.JFormattedTextField tfNascimento;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfProfissao;
    private javax.swing.JFormattedTextField tfRenda;
    private javax.swing.JTextField tfRua;
    private javax.swing.JFormattedTextField tfTelefoneCel;
    private javax.swing.JFormattedTextField tfTelefoneRes;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAtivo) {
            int selecionado = tbDados.getSelectedRow();
            if (selecionado == -1) {
                JOptionPane.showMessageDialog(this, "Você deve selecionar uma linha", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ativar();
            }
        } else if (e.getSource() == btInserir) {
            System.out.println("Inserir o//");
            inserir();
        } else if (e.getSource() == btAlterar) {
            int selecionado = tbDados.getSelectedRow();
            if (selecionado == -1) {
                JOptionPane.showMessageDialog(this, "Você deve selecionar uma linha", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                alterar();
            }
        }
    }

    public void ativar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaRelacionamento lis = (FrmListaRelacionamento) tbDados.getModel();
        Relacionamento rel = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        em.getTransaction().begin();
        rel = em.find(Relacionamento.class, rel.getId());
        if (rel == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            if (rel.isAtivo()) {
                rel.setAtivo(false);
            } else {
                rel.setAtivo(true);
            }
            em.merge(rel);
            em.getTransaction().commit();

        }
        tbDados.updateUI();
    }

    public void alterar() {
        int linhaSelecionada = tbDados.getSelectedRow();
        FrmListaRelacionamento lis = (FrmListaRelacionamento) tbDados.getModel();
        Relacionamento rel = lis.getLista().get(linhaSelecionada);
        EntityManager em = Principal.emf.createEntityManager();
        rel = em.find(Relacionamento.class, rel.getId());
        if (rel == null) {
            JOptionPane.showMessageDialog(this, "Este registro já foi excluído", "Aviso", JOptionPane.WARNING_MESSAGE);

        } else {
            FrmCadRelacionamento md = new FrmCadRelacionamento(null, true);
            md.setEntidade(rel);
            md.entidadeTela();
            md.setVisible(true);
            rel = md.getEntidade();
            rel.setRelacionamento(md.getCliente());
            if (rel != null) {
                em.getTransaction().begin();
                em.merge(rel);
                em.getTransaction().commit();
            }
        }
        tbDados.updateUI();
    }

    public void inserir() {
        Cliente cli = new Cliente();
        Relacionamento rel = new Relacionamento();

        FrmCadRelacionamento md = new FrmCadRelacionamento(null, true);
        md.setEntidade(rel);
        md.setVisible(true);

        rel = md.getEntidade();
        cli = md.getCliente();
        if (rel != null) {
            rel.setRelacionamento(cli);
            System.out.println(rel);
            listaRelacionamento.add(rel);
            System.out.println(listaRelacionamento.size());
            tbDados.setModel(new FrmListaRelacionamento(listaRelacionamento));
            tbDados.updateUI();

        }
    }
}
