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

import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import br.com.prosafe.model.ModuloConexao;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Autenticação do usuário
 *
 * @author Gustavo Calixto && Davi Botelho
 */
public class TelaPrincipal extends javax.swing.JFrame {
    Connection conexao = null;
    
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    /**
     * Método responsável pela emissão de relatórios gerais sobre os bloqueios
     */
    private void relatorioBloqueios() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            
            try {
                JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/blocks.jasper"), null, conexao);
                JasperViewer.viewReport(print,false);
                
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, e);
                
            }
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The coaantent of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        menu = new javax.swing.JMenuBar();
        menuBloqueio = new javax.swing.JMenu();
        menuCriarBlock = new javax.swing.JMenuItem();
        menuRelatorio = new javax.swing.JMenuItem();
        menuConfig = new javax.swing.JMenu();
        menuConfigMaq = new javax.swing.JMenuItem();
        menuConfigFontes = new javax.swing.JMenuItem();
        menuConfigUsers = new javax.swing.JMenuItem();
        menuConfigSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ProSafe");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        desktop.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );

        menu.setBorder(null);
        menu.setToolTipText("raa]");

        menuBloqueio.setText("Bloqueio");
        menuBloqueio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBloqueioActionPerformed(evt);
            }
        });

        menuCriarBlock.setText("Criar");
        menuCriarBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCriarBlockActionPerformed(evt);
            }
        });
        menuBloqueio.add(menuCriarBlock);

        menuRelatorio.setText("Relatório");
        menuRelatorio.setEnabled(false);
        menuRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioActionPerformed(evt);
            }
        });
        menuBloqueio.add(menuRelatorio);

        menu.add(menuBloqueio);

        menuConfig.setText("Configurações");

        menuConfigMaq.setText("Maquinário");
        menuConfigMaq.setEnabled(false);
        menuConfigMaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConfigMaqActionPerformed(evt);
            }
        });
        menuConfig.add(menuConfigMaq);

        menuConfigFontes.setText("Fontes de energia");
        menuConfigFontes.setEnabled(false);
        menuConfigFontes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConfigFontesActionPerformed(evt);
            }
        });
        menuConfig.add(menuConfigFontes);

        menuConfigUsers.setText("Usuarios");
        menuConfigUsers.setEnabled(false);
        menuConfigUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConfigUsersActionPerformed(evt);
            }
        });
        menuConfig.add(menuConfigUsers);

        menuConfigSair.setText("Logout");
        menuConfigSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConfigSairActionPerformed(evt);
            }
        });
        menuConfig.add(menuConfigSair);

        menu.add(menuConfig);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );

        setSize(new java.awt.Dimension(376, 648));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuConfigSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfigSairActionPerformed
        // TODO add your handling code here:
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?","Atenção",JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_menuConfigSairActionPerformed

    private void menuConfigUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfigUsersActionPerformed
        // TODO add your handling code here:
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);
    }//GEN-LAST:event_menuConfigUsersActionPerformed

    private void menuConfigMaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfigMaqActionPerformed
        // TODO add your handling code here:
        TelaMaquinas maquina = new TelaMaquinas();
        maquina.setVisible(true);
        desktop.add(maquina);
    }//GEN-LAST:event_menuConfigMaqActionPerformed

    private void menuBloqueioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBloqueioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_menuBloqueioActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void menuConfigFontesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfigFontesActionPerformed
        // TODO add your handling code here:
        TelaFontes fontes = new TelaFontes();
        fontes.setVisible(true);
        desktop.add(fontes);
    }//GEN-LAST:event_menuConfigFontesActionPerformed

    private void menuCriarBlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCriarBlockActionPerformed
        TelaBloqueio Bloqueio = new TelaBloqueio();
        desktop.add(Bloqueio);
        Bloqueio.setVisible(true);
    }//GEN-LAST:event_menuCriarBlockActionPerformed

    private void menuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioActionPerformed
        // TODO add your handling code here:
        relatorioBloqueios();
    }//GEN-LAST:event_menuRelatorioActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuBar menu;
    public static javax.swing.JMenu menuBloqueio;
    public static javax.swing.JMenu menuConfig;
    public static javax.swing.JMenuItem menuConfigFontes;
    public static javax.swing.JMenuItem menuConfigMaq;
    public static javax.swing.JMenuItem menuConfigSair;
    public static javax.swing.JMenuItem menuConfigUsers;
    public static javax.swing.JMenuItem menuCriarBlock;
    public static javax.swing.JMenuItem menuRelatorio;
    // End of variables declaration//GEN-END:variables
}
