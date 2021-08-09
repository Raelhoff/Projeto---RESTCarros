/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author RaelH
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BancoConnection {

    //Classe responsavel de abrir e fechar conexão do banco.
    private static final String DRIVER =   "org.postgresql.Driver";
                                          //"com.mysql.jdbc.Driver";
                                          //"org.postgresql.Driver";

      // Initialize connection variables.
       private static final String host = "restcarros.postgres.database.azure.com";
       private static final String database = "carro";
       private static final String user = "senai@restcarros";
        private static final String password = "admin@2020";

    //ENDERECO:PORTA/NOME_BANCO
    // 
    //private static final String URL = "jdbc:mysql://localhost/contato";
    
  // private static final String URL ="jdbc:mysql://localhost:3306/contato?useTimezone=true&serverTimezone=UTC";
   //  private static final String URL = "jdbc:postgresql://senaidesenv.postgres.database.azure.com";
    // jdbc é o protocolo;
    // postgresql, o sub-protocolo;
    // localhost é o endereço do servidor (IP ou nome);
    // 5432 é a porta, que é obrigatória caso não seja a padrão e opcional caso seja, e;
    // contato é nome do banco de dados.

    //Usuario e senha do banco que você define quando cria o banco.
 //   private static final String USER = "senai@senaidesenv";
  //  private static final String PASS = "admin@2020";

    //Abre conexão com o banco.
    public static Connection getConnection()  throws ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            
            //return DriverManager.getConnection(URL, USER, PASS);
            
            String test = String.format("jdbc:postgresql://%s/%s", host, database);
            // set up the connection properties
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
           // properties.setProperty("ssl", "true");
            
            // String url = "jdbc:postgresql://senaidesenv.postgres.database.azure.com/contato";
   
            // get connection
            return DriverManager.getConnection(test, properties);
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return null;
        }
    }

    //Fecha conexão com o banco.
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
    }

}

