package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import Model.Carro;
import DAO.CarroDAO;

/**
 *
 * @author RaelH
 */
public class CarroController {
     
     public boolean addCarro(Carro obj) throws ClassNotFoundException{
    	 CarroDAO cdao = new CarroDAO();
         if(cdao.add_carro(obj)){
             return true;
         }else{
             return false;
         }
     }
     
     public List <Carro> getListCarro() throws ClassNotFoundException{
    	 CarroDAO cdao = new CarroDAO();
         return cdao.mostrar_carros();
     }
     
     public Carro getCarro(int id) throws ClassNotFoundException{
    	   boolean Achou = false;
           
           CarroDAO cdao = new CarroDAO();
           Carro c = cdao.achar_carro(id);
  
           return c;    
       }
     
     public boolean update(int id, Carro obj) throws ClassNotFoundException{
         boolean Achou = false;
         
         CarroDAO cdao = new CarroDAO();
         Carro c = cdao.achar_carro(id);
         if(c != null){
              cdao.alterar_carro(c.getId(), 
                                  obj.getModel(), 
                                  obj.getColor(),
                                  obj.getPrice());  
              Achou = true;
         }else{
             return false;
         }
         return Achou;    
     }
     
     
   
     public boolean delete(int id) throws ClassNotFoundException{
    	 CarroDAO cdao = new CarroDAO();
            
         if(cdao.delete_carro(id)){
             return true;
         }else{
             return false;
         }
     }
     
}
