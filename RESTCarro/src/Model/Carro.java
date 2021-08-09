package Model;


/**
 *
 * @author RaelH
 */
public class Carro {
    private int id;
    private String model;
    private String color;
    private double price;

    public  Carro (){
    }
    
    public Carro (String model, String color, Double price){
    	this.model = model;
    	this.color = color;
    	this.price = price;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
}
