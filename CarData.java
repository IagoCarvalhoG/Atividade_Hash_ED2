import java.io.Serializable;
public class CarData implements Serializable {
    private String plate;
    private String brand;
    private String model;
    private String color;
    private int workerId;
    public CarData(String plate, String brand, String model, String color, int workerId){
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.workerId = workerId;
    }
    public String getPlate() {
        return this.plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public String getBrand() {
        return this.brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getWorkerId() {
        return this.workerId;
    }
    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
    @Override
    public String toString () {
        return this.plate + " " + this.brand + " " + this.model + " " + this.color + " " + this.workerId;
    }
}