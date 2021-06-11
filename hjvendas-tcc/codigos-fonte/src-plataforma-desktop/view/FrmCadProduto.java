package view;

import entidades.Cor;
import entidades.Fornecedor;
import entidades.ParteDoCorpo;
import entidades.Produto;
import entidades.Genero;
import entidades.TipoProduto;
import util.ComboBoxModelGenerico;

public class FrmCadProduto extends javax.swing.JDialog {

    public Produto entidade;

    public void entidadeTela() {

        tfDesc.setText(entidade.getNome());
        tfNome.setText(entidade.getNome());
        tfValor.setText(entidade.getValor().toString());
        tfDur.setText(entidade.getDuracao().toString());
        cbDura.setSelectedItem(entidade.getTempoDuracao());
        cbSexo.setSelectedItem(entidade.getSexo());
        cbParteCorpo.setSelectedItem(entidade.getParteDoCorpo());
        cbFornecedor.setSelectedItem(entidade.getFornecedor());

        cbProduto.setSelectedItem(entidade.getProduto());
        //Acessorio
        tfTamanho.setText(entidade.getTamanho());
        //Calçado
        tfSalto.setText(entidade.getTamanhoSalto().toString());
        tfNumero.setText(entidade.getNumero().toString());
        //Cosmetico
        tfVolume.setText(entidade.getVolume().toString());
        cbVolume.setSelectedItem(entidade.getVolumeCosmetico());
        cbTipoProduto.setSelectedItem(entidade.getTipoProduto());
        cbCor.setSelectedItem(entidade.getCor());
        oculta(entidade.getProduto());
    }

    public void telaEntidade() {
        entidade.setDescMaterial(tfDesc.getText());
        entidade.setNome(tfNome.getText());
        entidade.setCor((Cor) cbCor.getSelectedItem());
        entidade.setParteDoCorpo((ParteDoCorpo) cbParteCorpo.getSelectedItem());
        entidade.setProduto((String) cbProduto.getSelectedItem());
        entidade.setSexo((Genero) cbSexo.getSelectedItem());
        entidade.setFornecedor((Fornecedor) cbFornecedor.getSelectedItem());
        entidade.setDuracao(Long.parseLong(tfDur.getText()));
        entidade.setVolumeCosmetico(cbVolume.getSelectedItem().toString());
        entidade.setTempoDuracao(cbDura.getSelectedItem().toString());
        entidade.setTamanho(tfTamanho.getText());
        entidade.setTipoProduto((TipoProduto) cbTipoProduto.getSelectedItem());
        entidade.setValor(Double.parseDouble(tfValor.getText().replace(",", ".")));


        if (tfNumero.getText().trim().equals("")) {
            entidade.setNumero(0L);
        }
        if (tfSalto.getText().trim().equals("")) {
            entidade.setTamanhoSalto(0L);
        }

        if (tfVolume.getText().trim().equals("")) {
            entidade.setVolume(0L);
        }

        if (tfDur.getText().trim().equals("")) {
            entidade.setDuracao(0L);
        }

        //entidade.setNumero(Long.parseLong(tfNumero.getText()));
        //entidade.setTamanhoSalto(Long.parseLong(tfSalto.getText()));
        //entidade.setVolume(Long.parseLong(tfVolume.getText()));
        //entidade.setDuracao(Long.parseLong(tfDur.getText()));


    }

    public Produto getEntidade() {
        return entidade;
    }

    public void setEntidade(Produto entidade) {
        this.entidade = entidade;
    }

    private boolean valida() {
        boolean retorno = true;
        if (tfDesc.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }
        if (tfValor.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        if (tfNome.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        if (tfDur.getText().trim().length() == 0) {
            msg.setText("(*) Campos obrigatorio!");
            retorno = false;
        }

        if (cbFornecedor.getSelectedItem() == ""){
            msg.setText("(*) Campos obrigatórios");
            retorno = false;
        }
        return retorno; //Validou
    }

    /** Creates new form FrmCadCor */
    public FrmCadProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        tfDesc.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ob = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfDesc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        msg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ob1 = new javax.swing.JLabel();
        tfValor = new javax.swing.JFormattedTextField();
        cbDura = new javax.swing.JComboBox();
        cbSexo = new javax.swing.JComboBox();
        cbParteCorpo = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        cbProduto = new javax.swing.JComboBox();
        panAce = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tfTamanho = new javax.swing.JTextField();
        panCal = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tf = new javax.swing.JLabel();
        tfSalto = new javax.swing.JFormattedTextField();
        tfNumero = new javax.swing.JFormattedTextField();
        panCos = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbVolume = new javax.swing.JComboBox();
        cbTipoProduto = new javax.swing.JComboBox();
        tfVolume = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        cbCor = new javax.swing.JComboBox();
        ob2 = new javax.swing.JLabel();
        ob3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbFornecedor = new javax.swing.JComboBox();
        tfDur = new javax.swing.JFormattedTextField();
        ob4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        ob.setForeground(java.awt.Color.red);
        ob.setText("(*)");
        ob.setEnabled(false);

        jLabel2.setText("Descrição:");

        jLabel4.setText("Duração:");

        jLabel5.setText("Genero:");

        jLabel6.setText("Nome:");

        jLabel7.setText("Parte do corpo:");

        jLabel3.setText("Produto:");

        jLabel13.setText("Valor: R$");

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

        tfValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        cbDura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Dia(s)", "Mês(es)", "Ano(s)" }));

        cbSexo.setModel(new ComboBoxModelGenerico(entidades.Genero.class));

        cbParteCorpo.setModel(new ComboBoxModelGenerico(ParteDoCorpo.class));

        cbProduto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Acessório", "Calçado", "Cosmético" }));
        cbProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProdutoActionPerformed(evt);
            }
        });

        panAce.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Acessório"));
        panAce.setToolTipText("");

        jLabel8.setText("Tamanho:");

        tfTamanho.setEnabled(false);

        javax.swing.GroupLayout panAceLayout = new javax.swing.GroupLayout(panAce);
        panAce.setLayout(panAceLayout);
        panAceLayout.setHorizontalGroup(
            panAceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfTamanho, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );
        panAceLayout.setVerticalGroup(
            panAceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAceLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        panCal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Calçado"));
        panCal.setToolTipText("");
        panCal.setEnabled(false);

        jLabel15.setText("Tamanho salto:");

        jLabel16.setText("Numero:");

        tf.setText("cm");

        tfSalto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        tfSalto.setEnabled(false);

        tfNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        tfNumero.setEnabled(false);

        javax.swing.GroupLayout panCalLayout = new javax.swing.GroupLayout(panCal);
        panCal.setLayout(panCalLayout);
        panCalLayout.setHorizontalGroup(
            panCalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panCalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addGroup(panCalLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(tfSalto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tf))
                    .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panCalLayout.setVerticalGroup(
            panCalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCalLayout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panCalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf)
                    .addComponent(tfSalto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panCos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cosmético"));
        panCos.setToolTipText("");
        panCos.setEnabled(false);

        jLabel17.setText("Volume:");

        jLabel18.setText("Tipo do produto:");

        cbVolume.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ml", "Litro" }));
        cbVolume.setEnabled(false);

        cbTipoProduto.setModel(new ComboBoxModelGenerico(TipoProduto.class));
        cbTipoProduto.setEnabled(false);

        tfVolume.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        tfVolume.setEnabled(false);

        javax.swing.GroupLayout panCosLayout = new javax.swing.GroupLayout(panCos);
        panCos.setLayout(panCosLayout);
        panCosLayout.setHorizontalGroup(
            panCosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panCosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTipoProduto, 0, 193, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addGroup(panCosLayout.createSequentialGroup()
                        .addComponent(tfVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(cbVolume, 0, 143, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panCosLayout.setVerticalGroup(
            panCosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCosLayout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panCosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setText("Cor:");

        cbCor.setModel(new ComboBoxModelGenerico(Cor.class));

        ob2.setForeground(java.awt.Color.red);
        ob2.setText("(*)");
        ob2.setEnabled(false);

        ob3.setForeground(java.awt.Color.red);
        ob3.setText("(*)");
        ob3.setEnabled(false);

        jLabel9.setText("Fornecedor:");

        cbFornecedor.setModel(new ComboBoxModelGenerico(Fornecedor.class));

        tfDur.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        ob4.setForeground(java.awt.Color.red);
        ob4.setText("(*)");
        ob4.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ob4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFornecedor, 0, 478, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ob)
                                    .addComponent(ob1)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ob2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(ob3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfDur, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbDura, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbParteCorpo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCor, 0, 64, Short.MAX_VALUE)
                .addGap(248, 248, 248))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(5, 5, 5)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panAce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panCal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(panCos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(ob))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(tfDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ob2))
                        .addGap(3, 3, 3))
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ob1)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ob3)
                            .addComponent(jLabel4)
                            .addComponent(tfDur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ob4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbParteCorpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panCal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panAce, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panCos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addContainerGap())
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

    private void cbProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProdutoActionPerformed
        // TODO add your handling code here:
        oculta(cbProduto.getSelectedItem().toString());
        System.out.println("cbProduto.getSelectedItem().toString():" + cbProduto.getSelectedItem().toString());
    }//GEN-LAST:event_cbProdutoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FrmCadProduto dialog = new FrmCadProduto(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cbCor;
    private javax.swing.JComboBox cbDura;
    private javax.swing.JComboBox cbFornecedor;
    private javax.swing.JComboBox cbParteCorpo;
    private javax.swing.JComboBox cbProduto;
    private javax.swing.JComboBox cbSexo;
    private javax.swing.JComboBox cbTipoProduto;
    private javax.swing.JComboBox cbVolume;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel msg;
    private javax.swing.JLabel ob;
    private javax.swing.JLabel ob1;
    private javax.swing.JLabel ob2;
    private javax.swing.JLabel ob3;
    private javax.swing.JLabel ob4;
    private javax.swing.JPanel panAce;
    private javax.swing.JPanel panCal;
    private javax.swing.JPanel panCos;
    private javax.swing.JLabel tf;
    private javax.swing.JTextField tfDesc;
    private javax.swing.JFormattedTextField tfDur;
    private javax.swing.JTextField tfNome;
    private javax.swing.JFormattedTextField tfNumero;
    private javax.swing.JFormattedTextField tfSalto;
    private javax.swing.JTextField tfTamanho;
    private javax.swing.JFormattedTextField tfValor;
    private javax.swing.JFormattedTextField tfVolume;
    // End of variables declaration//GEN-END:variables

    private void oculta(String produto) {
        if (produto.equals("Acessório")) {
            //Calçado
            tfSalto.setEnabled(false);
            tfNumero.setEnabled(false);
            //Cosmético
            tfVolume.setEnabled(false);
            cbVolume.setEnabled(false);
            cbTipoProduto.setEnabled(false);
            //Acessório
            tfTamanho.setEnabled(true);
        }
        if (produto.equals("Calçado")) {
            //Acessório
            tfTamanho.setEnabled(false);
            //Cometico
            tfVolume.setEnabled(false);
            cbVolume.setEnabled(false);
            cbTipoProduto.setEnabled(false);
            //Calçado
            tfSalto.setEnabled(true);
            tfNumero.setEnabled(true);
        }
        if (produto.equals("Cosmético")) {
            //Calçado
            tfSalto.setEnabled(false);
            tfNumero.setEnabled(false);
            //Acessório
            tfTamanho.setEnabled(false);
            //Cometico
            tfVolume.setEnabled(true);
            cbVolume.setEnabled(true);
            cbTipoProduto.setEnabled(true);
        }
        if (produto.equals(" ")) {
            tfSalto.setEnabled(false);
            tfNumero.setEnabled(false);
            //Acessório
            tfTamanho.setEnabled(false);
            //Cometico
            tfVolume.setEnabled(false);
            cbVolume.setEnabled(false);
            cbTipoProduto.setEnabled(false);
        }
    }
}
