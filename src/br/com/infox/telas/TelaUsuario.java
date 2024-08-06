/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos
 */
@SuppressWarnings("serial")
public class TelaUsuario extends javax.swing.JInternalFrame {

    private static Connection conexao = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void limpar() {
        // limpa os campos preenchidos na aula 14
        txtNome.setText(null);
        txtTelefone.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        cbUsuPerfil.setSelectedItem(null);
    }

    private void adicionar() {
        //Aula 14
        String sql = "insert into tbusuarios"
                + "(iduser,usuario,fone,login,senha,perfil) values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtID.getText()));
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtTelefone.getText());
            pst.setString(4, txtLogin.getText());
            pst.setString(5, txtSenha.getText());
            pst.setString(6, cbUsuPerfil.getSelectedItem().toString());
            // Validação dos campos obrigadorios.
            if (txtID.getText().isEmpty() || txtNome.getText().isEmpty()
                    || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()
                    || cbUsuPerfil.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe todos os "
                        + "Campos Obrigatorio");
            } else {
                // a linha abaixo atualiza a tabela usuario coomos
                //dados do formulario
                int adiciona = pst.executeUpdate();
                if (adiciona > 0) {
                    //Aula 14
                    JOptionPane.showMessageDialog(null, "Usuario foi cadastrado "
                            + "com Sucesso!!!");
                    txtID.setText(null);
                    limpar();
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void editar() {
        // Aula  15
        String sql = "update tbusuarios set usuario=?, fone=?,login=?,senha=?,"
                + "perfil=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(6, Integer.parseInt(txtID.getText()));
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText());
            pst.setString(3, txtLogin.getText());
            pst.setString(4, txtSenha.getText());
            pst.setString(5, cbUsuPerfil.getSelectedItem().toString());
            if (txtID.getText().isEmpty()
                    || txtNome.getText().isEmpty() || txtLogin.getText().isEmpty()
                    || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe todos os "
                        + "campos obrigatorio");
            } else {
                int edita = pst.executeUpdate();
                if (edita > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario alterado "
                            + "com sucesso!!!");
                    txtID.setText(null);
                    limpar();
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void deletar() {
        int deleta = JOptionPane.showConfirmDialog(null, "Tem certeza que "
                + "deseja deleta o usuario?", "Atenção",
                JOptionPane.YES_NO_OPTION);
        try {
            if (deleta == JOptionPane.YES_OPTION) {
                String sql = "delete from tbusuarios where iduser=?";
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(txtID.getText()));
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario foi excluido com"
                            + " sucesso !!!");
                    txtID.setText(null);
                    limpar();
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void consulta() {
        // Aula 13
        String sql = "select * from tbusuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtID.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                txtTelefone.setText(rs.getString(3));
                txtLogin.setText(rs.getString(4));
                txtSenha.setText(rs.getString(5));
                cbUsuPerfil.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado!!!");
                limpar();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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

        lblID = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblLogin1 = new javax.swing.JLabel();
        lblSenha1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        lblUsuario2 = new javax.swing.JLabel();
        lblPefil1 = new javax.swing.JLabel();
        cbUsuPerfil = new javax.swing.JComboBox<>();
        btnADD = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnSeach = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Usuário");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(500, 500));

        lblID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblID.setText("*ID:");

        lblTelefone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTelefone.setText("Telefone:");

        lblLogin1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLogin1.setText("*Login:");

        lblSenha1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSenha1.setText("*Senha:");

        txtID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        txtSenha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtTelefone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });

        txtLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lblUsuario2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUsuario2.setText("*Nome:");

        lblPefil1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPefil1.setText("*Perfil:");

        cbUsuPerfil.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        cbUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUsuPerfilActionPerformed(evt);
            }
        });

        btnADD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/ADD.png"))); // NOI18N
        btnADD.setToolTipText("Adicionar");
        btnADD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnADD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnADDMouseClicked(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/EDIT.png"))); // NOI18N
        btnEdit.setToolTipText("Editar");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setPreferredSize(new java.awt.Dimension(32, 32));
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        btnSeach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/SEACH.png"))); // NOI18N
        btnSeach.setToolTipText("Procurar");
        btnSeach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSeach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSeachMouseClicked(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/DELETE.png"))); // NOI18N
        btnDelete.setToolTipText("Deletar");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        jLabel1.setText("* - Campo Obrigatorio.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTelefone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsuario2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSenha1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnADD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSenha)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(117, 117, 117)
                                    .addComponent(jLabel5))
                                .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblLogin1)
                            .addComponent(lblPefil1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbUsuPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLogin)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(btnSeach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(125, 125, 125)))
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblID)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario2)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefone)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLogin1)
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha1)
                    .addComponent(lblPefil1)
                    .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnADD)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeach)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnADD, btnDelete, btnEdit, btnSeach});

        setBounds(0, 0, 514, 410);
    }// </editor-fold>//GEN-END:initComponents

    private void cbUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUsuPerfilActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnSeachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeachMouseClicked
        // TODO add your handling code here:
        consulta();
    }//GEN-LAST:event_btnSeachMouseClicked

    private void btnADDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnADDMouseClicked
        // Chama o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnADDMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // Chama o metodo editar
        editar();
    }//GEN-LAST:event_btnEditMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // Chama  o metodo deletar
        deletar();
    }//GEN-LAST:event_btnDeleteMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnADD;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnSeach;
    private javax.swing.JComboBox<String> cbUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLogin1;
    private javax.swing.JLabel lblPefil1;
    private javax.swing.JLabel lblSenha1;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblUsuario2;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
