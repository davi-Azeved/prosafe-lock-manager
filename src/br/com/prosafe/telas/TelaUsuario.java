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
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    /**
     * Método responsável pela limpeza dos campos ja preenchidos
     */
    private void limpar(){
        txtUsuNome.setText(null);
        txtUsuCargo.setText(null);
        txtUsuEmail.setText(null);
        txtUsuSenha.setText(null);
    }
    
    /**
     * Método responsável pela adição de usuários
     */
    private void adicionar() {
        String sql = "insert into tbusuarios(usuario,cargo,email,senha,perfil) values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuCargo.getText());
            pst.setString(3, txtUsuEmail.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());

            if ((txtUsuNome.getText().isEmpty()) || (txtUsuCargo.getText().isEmpty()) || (txtUsuEmail.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())  ){
                JOptionPane.showMessageDialog(null, "Preencha todos campos");
            } else{
           
                int adicionado =pst.executeUpdate();
                    
                if (adicionado > 0){
                    JOptionPane.showMessageDialog(null,"Usuário adicionado");
                    limpar();
                } 
            }    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }   
    
    /**
     * Método responsável pela consulta de usuários
     */
    private void pesquisar() {
        String sql = "select * from tbusuarios where usuario like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText() + "%");
            rs = pst.executeQuery();

            tblUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * Método responsável por setar os campos de um usuário existente
     */
    private void setar_campos() {
        int setar = tblUsuarios.getSelectedRow();
        txtUsuNome.setText(tblUsuarios.getModel().getValueAt(setar, 1).toString());
        txtUsuCargo.setText(tblUsuarios.getModel().getValueAt(setar, 2).toString());
        txtUsuEmail.setText(tblUsuarios.getModel().getValueAt(setar, 3).toString());
        txtUsuSenha.setText(tblUsuarios.getModel().getValueAt(setar, 4).toString());
        cboUsuPerfil.setSelectedItem(tblUsuarios.getModel().getValueAt(setar, 5).toString());
        /**btnUsuCreate.setEnabled(false);*/
    }
    
    /**
     * Método responsável pela alteração de máquinas
     */
    private void alterar(){
        String sql = "update tbusuarios set usuario=?, cargo=?, email=?, senha=?, perfil=? where usuario=?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuCargo.getText());
            pst.setString(3, txtUsuEmail.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(6, txtUsuNome.getText());
            
            if ( (txtUsuNome.getText().isEmpty()) || (txtUsuCargo.getText().isEmpty()) || (txtUsuEmail.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())  ){
                JOptionPane.showMessageDialog(null, "Preencha todos campos");
            } else{
           
                int adicionado = pst.executeUpdate();
                    
                if (adicionado > 0){
                    JOptionPane.showMessageDialog(null,"Dados alterados");
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
        int confirma = JOptionPane.showConfirmDialog(null, "Remover esse usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where usuario=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuNome.getText());
                
                int apagado = pst.executeUpdate();
                if (apagado > 0){
                    JOptionPane.showMessageDialog(null, "Usuário removido!");
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

        txtUsuNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuCargo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsuSenha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtUsuId = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 0, 0));
        setClosable(true);
        setPreferredSize(new java.awt.Dimension(360, 617));

        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });
        txtUsuNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuNomeKeyReleased(evt);
            }
        });

        jLabel2.setText("Nome");

        jLabel3.setText("Cargo");

        jLabel4.setText("Email");

        jLabel5.setText("Senha");

        jLabel6.setText("Perfil");

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user", "admin" }));

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(39, 56, 219));
        jLabel7.setText("Usuários");

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prosafe/icones/adicionar.png"))); // NOI18N
        btnUsuCreate.setBorder(null);
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prosafe/icones/excluir.png"))); // NOI18N
        btnUsuDelete.setBorder(null);
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prosafe/icones/editar.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("");
        btnUsuUpdate.setBorder(null);
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        tblUsuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Cargo", "Email", "Perfil"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setFocusable(false);
        tblUsuarios.getTableHeader().setResizingAllowed(false);
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        tblUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblUsuariosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        txtUsuId.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuId.setText("jTextField1");
        txtUsuId.setCaretColor(new java.awt.Color(255, 255, 255));
        txtUsuId.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtUsuId.setSelectionColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuCargo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel7))
                            .addComponent(cboUsuPerfil, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 360, 617);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuNomeActionPerformed
        // TODO add your handling code here: 
    }//GEN-LAST:event_txtUsuNomeActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void tblUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblUsuariosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUsuariosKeyReleased

    private void txtUsuNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuNomeKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtUsuNomeKeyReleased

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtUsuCargo;
    private javax.swing.JTextField txtUsuEmail;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
