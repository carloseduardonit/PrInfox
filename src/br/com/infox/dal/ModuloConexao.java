/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;

import java.sql.*;

/**
 *
 * @author carlos
 */
public class ModuloConexao {

    //método responsavel por  estabelecer a conexão com o banco
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    //Armazenando informações     referente  ao banco
    private final static String URL = "jdbc:mysql://localhost:3306/dbinfox";
    private final static String USER = "Carlos";
    private final static String PASSWORD = "";

    public static Connection conector() {
        java.sql.Connection conexao = null;
        // alinha  abaixo chama o  drive
        // Estabelecendo o acesso ao banco de dados
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            conexao = null;
            return conexao;
        }
    }

    public static void reset() throws SQLException {
        java.sql.Connection conexao = null;
        conexao.close();
        conector();
    }

    public static void inicio() {

    }
}
