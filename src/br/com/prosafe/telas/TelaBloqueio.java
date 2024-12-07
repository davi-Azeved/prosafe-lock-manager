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
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Tela de criação dos bloqueios
 *
 * @author Gustavo Calixto && Davi Botelho
 */
public class TelaBloqueio extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private String tipo;

    /**
     * Creates new form TelaBloqueio
     */
    public TelaBloqueio() {
        initComponents();
        conexao = ModuloConexao.conector();
        lblEmail.setText(UsuarioLogado.getEmail());
    }

    public class UsuarioLogado {

        private static String email;

        public static String getEmail() {
            return email;
        }

        public static void setEmail(String email) {
            UsuarioLogado.email = email;
        }
    }

    /**
     * Método responsável pela limpeza dos campos ja preenchidos
     */
    private void limpar() {
        txtBlockMaqNome.setText(null);
        ((DefaultTableModel) tbBlockMaq.getModel()).setRowCount(0);
        txtBlockMaqDef.setText(null);
        txtBlockMot.setText(null);
    }

    /**
     * Método responsável pela pesquisa da máquina que será vinculado ao
     * bloqueio
     */
    private void pesquisar_maquina() {
        String sql = "select nomemaq as Nome, areamaq as Area, tipomaq as Tipo from tbmaquinas where nomemaq like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBlockMaqNome.getText() + "%");
            rs = pst.executeQuery();

            tbBlockMaq.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método responsável por setar a máquina que ao bloqueio
     */
    private void setar_campos() {
        int setar = tbBlockMaq.getSelectedRow();
        txtBlockMaqDef.setText(tbBlockMaq.getModel().getValueAt(setar, 0).toString());
    }

    private void guardar_login() {
        String sql = "INSERT INTO tbblock(usuario) VALUES(?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, lblEmail.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método responsável pela emissão de um bloqueio
     */
    private void emitir_bloqueio() {
        String sql = "INSERT INTO tbblock(tipo, usuario, nomemaq, motivoblock) VALUES(?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, tipo);
            pst.setString(2, lblEmail.getText());
            pst.setString(3, txtBlockMaqDef.getText());
            pst.setString(4, txtBlockMot.getText());

            // Verificar campos obrigatórios
            if (txtBlockMaqDef.getText().isEmpty() || txtBlockMot.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            } else {
                if (!termos.isSelected()) {
                    btnBlock.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Aceite os termos");

                    btnBlock.setEnabled(true);
                } else {
                    int adicionado = pst.executeUpdate();

                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
                        imprimir();
                        limpar();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método responsável pela impressão da etiqueta de um bloqueio
     */
    private void imprimir() {
        int confirma = JOptionPane.showConfirmDialog(null, "Imprimir etiqueta?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                String sql = "SELECT MAX(idblock) AS ultimo_id FROM tbblock";
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    int ultimoId = rs.getInt("ultimo_id");

                    HashMap<String, Object> filtro = new HashMap<>();
                    filtro.put("idblock", ultimoId);

                    JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/etiqueta.jasper"), filtro, conexao);
                    JasperViewer.viewReport(print, false);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum registro encontrado na tabela 'block");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar o último ID: " + e.getMessage());
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório: " + e.getMessage());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBlockMaq = new javax.swing.JTable();
        txtBlockMaqNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnBlock = new javax.swing.JButton();
        txtBlockMot = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rdBotaoBlock = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        termos = new javax.swing.JCheckBox();
        txtBlockMaqDef = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Data");

        setClosable(true);
        setMaximumSize(new java.awt.Dimension(360, 617));
        setMinimumSize(new java.awt.Dimension(360, 617));
        setPreferredSize(new java.awt.Dimension(360, 617));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        tbBlockMaq = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbBlockMaq.setModel(new javax.swing.table.DefaultTableModel(
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
        tbBlockMaq.getTableHeader().setResizingAllowed(false);
        tbBlockMaq.getTableHeader().setReorderingAllowed(false);
        tbBlockMaq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBlockMaqMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBlockMaq);

        txtBlockMaqNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBlockMaqNomeActionPerformed(evt);
            }
        });
        txtBlockMaqNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBlockMaqNomeKeyReleased(evt);
            }
        });

        jLabel6.setText("Nome");

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(39, 56, 219));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Bloqueios");

        btnBlock.setBackground(new java.awt.Color(39, 56, 219));
        btnBlock.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBlock.setForeground(new java.awt.Color(255, 255, 255));
        btnBlock.setText("Prosseguir");
        btnBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlockActionPerformed(evt);
            }
        });

        jLabel1.setText("Motivo");

        buttonGroup1.add(rdBotaoBlock);
        rdBotaoBlock.setText("Bloqueio");
        rdBotaoBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBotaoBlockActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Desbloqueio");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        termos.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        termos.setForeground(new java.awt.Color(102, 102, 102));
        termos.setText("Aceito termos de responsabilidade");

        txtBlockMaqDef.setEditable(false);
        txtBlockMaqDef.setForeground(new java.awt.Color(102, 102, 102));
        txtBlockMaqDef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBlockMaqDefActionPerformed(evt);
            }
        });
        txtBlockMaqDef.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBlockMaqDefKeyReleased(evt);
            }
        });

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("test");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(rdBotaoBlock)
                            .addGap(18, 18, 18)
                            .addComponent(jRadioButton2)
                            .addGap(37, 37, 37))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(63, 63, 63))
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBlockMot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtBlockMaqDef, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBlockMaqNome, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(termos))))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBlockMaqNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBlockMaqDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdBotaoBlock)
                    .addComponent(jRadioButton2))
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBlockMot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(termos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        setBounds(0, 0, 360, 617);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlockActionPerformed
        // TODO add your handling code here:
        emitir_bloqueio();
    }//GEN-LAST:event_btnBlockActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        tipo = "desbloqueio";
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void tbBlockMaqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBlockMaqMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tbBlockMaqMouseClicked

    private void txtBlockMaqNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBlockMaqNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBlockMaqNomeActionPerformed

    private void txtBlockMaqNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBlockMaqNomeKeyReleased
        // TODO add your handling code here:
        pesquisar_maquina();
    }//GEN-LAST:event_txtBlockMaqNomeKeyReleased

    private void txtBlockMaqDefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBlockMaqDefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBlockMaqDefActionPerformed

    private void txtBlockMaqDefKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBlockMaqDefKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBlockMaqDefKeyReleased

    private void rdBotaoBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBotaoBlockActionPerformed
        // TODO add your handling code here:
        tipo = "bloqueio";
    }//GEN-LAST:event_rdBotaoBlockActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        rdBotaoBlock.setSelected(true);
        tipo = "bloqueio";

        guardar_login();
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlock;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblEmail;
    private javax.swing.JRadioButton rdBotaoBlock;
    private javax.swing.JTable tbBlockMaq;
    private javax.swing.JCheckBox termos;
    private javax.swing.JTextField txtBlockMaqDef;
    private javax.swing.JTextField txtBlockMaqNome;
    private javax.swing.JTextField txtBlockMot;
    // End of variables declaration//GEN-END:variables
}
