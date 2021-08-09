/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author RaelH
 */
import DAO.CarroDAO;
import Model.Carro;
import java.util.Scanner;

/**
 *
 * @author RaelH
 */

public class Main {
	
	public static void main(String[] args) {

    Main m = new Main();
        try {
			m.menu();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Scanner getScanner() {
        return new Scanner(System.in);
    }
    

    public void menu() throws ClassNotFoundException {

        System.out.println("1) Cadastrar Carro");
        System.out.println("2) Listar Todos Carros");
        System.out.println("3) Excluir Carro");
        System.out.println("4) Alterar Carro");
        System.out.println("5) Sair");
        System.out.print("Opção: ");
        int op = getScanner().nextInt();

        switch (op) {

            case 1:
                add_carro();
            case 2:
                listar_carro();
            case 3:
                excluir_carro();
            case 4:
                alterar_carro();
            case 5:
                System.exit(0);
            default:
                System.out.println("Digite uma opção valida!");
                menu();
        }
    }

    private void add_carro() throws ClassNotFoundException {

        System.out.print("\nDigite o model: ");
        String model = getScanner().nextLine();
        System.out.print("Digite o color: ");
        String color = getScanner().nextLine();
        System.out.print("Digite o price: ");
        Double price =  getScanner().nextDouble();
         
        //Instanciei um carro, normal...
        Carro c = new Carro(model, color, price);

        //Instanciando a classe DAO do Animal, chamando o metodo add_animal e passando como parametro o animal
        //criado acima
        CarroDAO cdao = new CarroDAO();
        cdao.add_carro(c);

        menu();
    }

    public void listar_carro() throws ClassNotFoundException {

        //Instanciando a classe DAO do animal
    	CarroDAO cdao = new CarroDAO();

        System.out.println("\t\n--- Todos os carros ---\n");

        //Passando um for no arraylist que o metodo mostrar_carros retorna
        for (Carro c : cdao.mostrar_carros()) {
            System.out.println("ID do carro: " + c.getId());
            System.out.println("Model: " + c.getModel());
            System.out.println("Color: " + c.getColor());
            System.out.println("Price: " + c.getPrice());
        }
        menu();
    }

    public void excluir_carro() throws ClassNotFoundException {

    	CarroDAO cdao = new CarroDAO();

        System.out.print("\nDigite o número id para excluir: ");
        int id_carro = getScanner().nextInt();

        //Metodo que remove o carro pelo numero do chassi
        cdao.delete_carro(id_carro);
        menu();

    }

    public void alterar_carro() throws ClassNotFoundException {

        //Tem varias forma de fazer essa alteração, escolhi encontrar o carro  chamando o metodo achar_carro da classe DAO, 
        //mostrar ele, e dar a liberdade de alterar todas as informações, em seguida passando essas novas informaçoes
        //pro metodos altera_carro da classe CarroDAO
    	CarroDAO cdao = new CarroDAO();

        System.out.print("\nDigite o número do id para alterar: ");
        int id_contato = getScanner().nextInt();

        Carro c = cdao.achar_carro(id_contato);

        System.out.println("\nAlterando Informações do Contato: \n");
        System.out.println("Model: "     + c.getModel());
        System.out.println("Color: " + c.getColor());
        System.out.println("Price: " + c.getPrice());
        
        System.out.println("Digite as novas informações: \n");

        System.out.print("Model: ");
        String model = getScanner().nextLine();
        System.out.print("Color: ");
        String color = getScanner().nextLine();
        
        System.out.print("Price: ");
        Double price = getScanner().nextDouble();
        
        //Passando como parametro as informações e o numero do chassi do carro que foi digitado e posteriormente encontrado
        cdao.alterar_carro(c.getId(), c.getModel(), c.getColor(), c.getPrice());
        menu();
    }
    
}
