/*
 * License

 *This project, ProSafe, is protected by a Restricted Use License.  

 * © 2024 Gustavo Calixto & Lucas Sousa & Davi Botelho.

 * Granted Rights: 
 * The source code may be viewed and downloaded exclusively for analysis and consultation.  
 * Feedback and comments on the code are welcome.  

 * Restrictions: 
 * Prohibited actions include, but are not limited to:  
 * Commercial or non-commercial use of the code.  
 * Modification or redistribution of the code.  
 * Integration into other projects or products.  

 * Special Permission:
 * Any usage beyond the granted rights requires explicit written authorization from the authors.
 */
package br.com.prosafe.telas;

import java.sql.*;
import br.com.prosafe.model.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 * Autenticação do usuário
 *
 * @author Gustavo Calixto && Davi Botelho
 */
public class TelaMaquinas extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaMaquinas
     */
    public TelaMaquinas() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    /**
     * Método responsável pela limpeza dos campos ja preenchidos
     */
    private void limpar() {
        txtMaqNome.setText(null);
        ((DefaultTableModel) tblMaquinas.getModel()).setRowCount(0);
    }

    /**
     * Método responsável pela adição de máquinas
     */
    private void adicionar() {
        String sql = "insert into tbmaquinas(nomemaq,areamaq,tipomaq) values(?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtMaqNome.getText());
            pst.setString(2, cboMaqArea.getSelectedItem().toString());
            pst.setString(3, cboMaqTipo.getSelectedItem().toString());

            if (txtMaqNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Máquina adicionada");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método responsável pela consulta de máquinas
     */
    private void pesquisar() {
        String sql = "select * from tbmaquinas where nomemaq like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtMaqNome.getText() + "%");
            rs = pst.executeQuery();

            tblMaquinas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método responsável por setar os campos de uma máquina existente
     */
    private void setar_campos() {
        int setar = tblMaquinas.getSelectedRow();
        txtMaqNome.setText(tblMaquinas.getModel().getValueAt(setar, 1).toString());
        cboMaqArea.setSelectedItem(tblMaquinas.getModel().getValueAt(setar, 2).toString());
        cboMaqTipo.setSelectedItem(tblMaquinas.getModel().getValueAt(setar, 3).toString());
        /**
         * btnAdicionar.setEnabled(false);
         */
    }

    /**
     * Método responsável pela alteração de máquinas
     */
    private void alterar() {
        String sql = "update tbmaquinas set nomemaq=?, areamaq=?, tipomaq=? where nomemaq=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtMaqNome.getText());
            pst.setString(2, cboMaqArea.getSelectedItem().toString());
            pst.setString(3, cboMaqTipo.getSelectedItem().toString());
            pst.setString(4, txtMaqNome.getText());

            if (txtMaqNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos campos");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterados");
                    limpar();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método responsável pela remoção de máquinas
     */
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Remover essa máquina?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbmaquinas where nomemaq=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtMaqNome.getText());

                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Máquina removida!");
                    limpar();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        txtMaqNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaquinas = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        cboMaqTipo = new javax.swing.JComboBox<>();
        cboMaqArea = new javax.swing.JComboBox<>();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(360, 617));

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(39, 56, 219));
        jLabel7.setText("Maquinas");

        txtMaqNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaqNomeActionPerformed(evt);
            }
        });
        txtMaqNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaqNomeKeyReleased(evt);
            }
        });

        jLabel6.setText("Nome");

        jLabel8.setText("Área");

        jLabel9.setText("Tipo");

        tblMaquinas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblMaquinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Area", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMaquinas.getTableHeader().setResizingAllowed(false);
        tblMaquinas.getTableHeader().setReorderingAllowed(false);
        tblMaquinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMaquinasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMaquinas);

        btnAlterar.setForeground(new java.awt.Color(39, 56, 219));
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prosafe/icones/editar.png"))); // NOI18N
        btnAlterar.setBorder(null);
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prosafe/icones/excluir.png"))); // NOI18N
        btnRemover.setBorder(null);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prosafe/icones/adicionar.png"))); // NOI18N
        btnAdicionar.setBorder(null);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        cboMaqTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Turbinas", "Geradores", "Disjuntores", "Motores de combustão", "Relés de proteção", "Retificadores", "Inversores" }));
        cboMaqTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaqTipoActionPerformed(evt);
            }
        });

        cboMaqArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Armazéns", "Depósitos", "Escritórios", "Usina", "Subestações", "Centro de Pesquisa" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaqNome, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cboMaqTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboMaqArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel7))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaqNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMaqArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(7, 7, 7)
                .addComponent(cboMaqTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(170, Short.MAX_VALUE))
        );

        setBounds(0, 0, 360, 617);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void cboMaqTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaqTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaqTipoActionPerformed

    private void txtMaqNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaqNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaqNomeActionPerformed

    private void txtMaqNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaqNomeKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtMaqNomeKeyReleased

    private void tblMaquinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMaquinasMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblMaquinasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cboMaqArea;
    private javax.swing.JComboBox<String> cboMaqTipo;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMaquinas;
    private javax.swing.JTextField txtMaqNome;
    // End of variables declaration//GEN-END:variables
}
