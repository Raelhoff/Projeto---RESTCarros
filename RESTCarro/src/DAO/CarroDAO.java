/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Carro;

/**
 *
 * @author RaelH
 
 
 
 
 
 
 
 create database carro
 
 CREATE TABLE listacar(
id SERIAL PRIMARY KEY,
model VARCHAR NOT NULL,
color VARCHAR NOT NULL,
price real
);
 
 
 
 
 * 
 */
public class CarroDAO {
/*
    * A Classe DAO vai ser responsavel de fazer todas as operações CRUD desse objeto no banco,
    * O recomendado e ter um Classe DAO pra cada entidade do seu projeto, ContatoDAO, etc...
     */
    private Connection con = null;
    //Sempre que instacia ele vai pegar a conexao com banco, da classe que criamos BancoConnection.
    public CarroDAO() throws ClassNotFoundException {
        con = BancoConnection.getConnection();
    }

    //Metodo que recebe um animal pra adicionar no banco!
    public boolean add_carro(Carro c) throws ClassNotFoundException {
        //Aqui eu monto a query que vai adicionar o contato, tem que saber o basico de QUERYS SQL, 
        //os nomes da tabela, e os campos tem que estar igual a do banco.

        String sql = "INSERT INTO listacar (model, color, price) VALUES (?, ?, ?)";

        try {
            con = BancoConnection.getConnection();
            //O preparedStatement serve pra preparar a query que criei acima, subistituindo os '?' pelos valores que eu quero,
            //serve pra nao usar querys fixas e unicas, onde o '?' pode receber qualquer valor.
            //OBS: Só pode usar a notação '?' nos dados.
            PreparedStatement stmt = con.prepareStatement(sql);

            //Digo que no 2º '?' ele vai ser trocado pelo nome do animal, e assim por diante.
            stmt.setString(1, c.getModel());
            stmt.setString(2, c.getColor());
            stmt.setDouble(3, c.getPrice());
            
            
            //Query preparada com os devidos '?' substituidos pelos valores do obj contato, agora eu executo essa Query com o metodo execute().
            stmt.execute();
            //o método execute() é utilizado em situações em que a query pode retornar mais de um resultado 
            //(somente em situações muito particulares ele é utilizado, como em algumas execuções de stored procedures).
            System.out.println("\nCarro adicionado no Banco de Dados\n");
            return true;
            //Pronto aqui ele já inseriu no banco.
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            BancoConnection.closeConnection(con);
        }

    }

    //Metodo que lista todos os animal que retorna um arraylist de animal, necessitando fazer a listagem no Main.
    public ArrayList<Carro> mostrar_carros() throws ClassNotFoundException {

        //ArrayList que irei retornar
        ArrayList<Carro> retorno = new ArrayList<>();

        //Query que irei lançar, retorna todos os animais (incluido os já vendidos).
        String sql = "SELECT * FROM listacar";

        try {
            con = BancoConnection.getConnection();
            //Passo a Query que vai ser preparada e executada.
            PreparedStatement stmt = con.prepareStatement(sql);

            //Executo essa Query e atribuo o resultado a rs. Onde ira guardar todo resultado que for pego no banco, 
            //ele guarda o resultado de uma pesquisa numa estrutura de dados que pode ser percorrida. 
            ResultSet rs = stmt.executeQuery();
            // O método executeQuery() é usado para executar consultas apenas, e não deve ser usado 
            //para comandos como update, delete, create, etc.
            
            //Percorro o resultado enquanto tiver proximo.
            while (rs.next()) {

                //Instacio um tipo contato pra criar o contato e adicionar no ArrayList que irei retornar.
                Carro c = new Carro();
                c.setId(rs.getInt("id"));
                c.setModel(rs.getString("model"));
                c.setColor(rs.getString("color"));
                c.setPrice(rs.getDouble("price"));
                
                //Adiciono no ArrayList.
                retorno.add(c);
                //Repete esse processo ate acabar o ResultSet, e no final o ARRAY vai ficar cheio com todos os animais.
            }

            rs.close();
            //Retorno o ARRAY com todos os animais.
            return retorno;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            BancoConnection.closeConnection(con);
        }
        return null;
    }

    //Metodo que deleta o animal pelo numero do chassi passado pelo parametro.
    public boolean delete_carro(int id) throws ClassNotFoundException {

        String sql1 = "DELETE FROM listacar WHERE id = ?";
        
        try {
            con = BancoConnection.getConnection();
            //Removendo todas as vendas do animal
            PreparedStatement stmt1 = con.prepareStatement(sql1);
            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo chassi do animal que recebemos no parametro.
            stmt1.setInt(1, id);
            stmt1.executeUpdate();
            System.out.println("\nCarro Deletado do Banco de Dados\n");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            BancoConnection.closeConnection(con);
        }

    }

    //Metodo que retorna o contato com o id passado pelo parametro.
    public Carro achar_carro(int id) throws ClassNotFoundException {

        Carro c = new Carro();
        String sql = "SELECT * FROM listacar WHERE id = ?";

        try {
            con = BancoConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            //Preparo sendo feito, digo que no 1º '?' ele vai ser trocado pelo chassi do animal que recebemos no parametro.
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //Chamo o Setters padrão do animal, e no parametro coloco o rs.getTipo("nome_da_coluna_igual_do_banco");              
                c.setId(rs.getInt("id"));
                c.setModel(rs.getString("model"));
                c.setColor(rs.getString("color"));
                c.setColor(rs.getString("price"));
                
            }
            return c;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            BancoConnection.closeConnection(con);
        }
    }

    //Metodo alterar animal, onde pega as novas informações do parametro e faz o UPDATE na tabela pelo chassi do animal.
    public void alterar_carro(int id, String model, String color, double preco) throws ClassNotFoundException {

        String sql = "UPDATE listacar SET model = ?, color = ?, price = ? WHERE id = ?";

        try {
            con = BancoConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, model);
            stmt.setString(2, color);
            stmt.setDouble(3, preco);
            stmt.setInt(4, id);
            
            
            stmt.executeUpdate();
            
            System.out.println("\nCarro Editado no Banco de Dados\n");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            BancoConnection.closeConnection(con);
        }
    }

}
