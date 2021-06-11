package util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public abstract class FrmListaGenerico extends JDialog implements ActionListener {

    protected JPanel painelBotoes;
    protected JButton btInserir;
    protected JButton btAlterar;
    protected JButton btExcluir;
    protected JButton btPesquisa;
    protected JButton btSelecionar;
    protected JTable tbDados;
    protected JTextField tfPesquisa;

    public abstract TableModel getTableModel();

    public FrmListaGenerico() {
        painelBotoes = new JPanel();
        painelBotoes.setBorder(BorderFactory.createEtchedBorder());

        add(painelBotoes, BorderLayout.SOUTH);
        painelBotoes.setLayout(new FlowLayout());


        btInserir = new JButton("Inserir");
        btInserir.addActionListener(this);
        btAlterar = new JButton("Alterar");
        btAlterar.addActionListener(this);
        btExcluir = new JButton("Excluir");
        btExcluir.addActionListener(this);
        btPesquisa = new JButton("Consultar");
        btPesquisa.addActionListener(this);
        btSelecionar= new JButton("Selecionar");
        btSelecionar.addActionListener(this);
        painelBotoes.add(btInserir);
        painelBotoes.add(btAlterar);
        painelBotoes.add(btExcluir);
        painelBotoes.add(btSelecionar);

        tfPesquisa = new JTextField(25);
        painelBotoes.add(tfPesquisa);
        tbDados = new JTable();
        tbDados.setModel(getTableModel());

        add(new JScrollPane(tbDados), BorderLayout.CENTER);

        tfPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                TableRowSorter<TableModel> pesquisa = new TableRowSorter<TableModel>(getTableModel());
                tbDados.setRowSorter(pesquisa);
                pesquisa.setRowFilter(RowFilter.regexFilter(tfPesquisa.getText()));
            }
        });

        tfPesquisa.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                tbDados.setRowSorter(null);
            }
        });

        setModal(true);
        pack();
        setLocationRelativeTo(null);
    }

    public abstract void excluir();

    public abstract void alterar();

    public abstract void inserir();

    public abstract Object selecionar();

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btExcluir) {
            int selecionado = tbDados.getSelectedRow();
            if (selecionado == -1) {
                JOptionPane.showMessageDialog(this, "Você deve selecionar uma linha", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                excluir();
            }
        } else if (e.getSource() == btInserir) {
            inserir();
        } else if (e.getSource() == btAlterar) {
            int selecionado = tbDados.getSelectedRow();
            if (selecionado == -1) {
                JOptionPane.showMessageDialog(this, "Você deve selecionar uma linha", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                alterar();
            }
        } else if (e.getSource() == btSelecionar){
            selecionar();
            this.setVisible(false);
        }
    }
}
