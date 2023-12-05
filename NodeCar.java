import java.io.Serializable;
class NodeCar implements Serializable {
    private CarData info;
    private NodeCar next;
    
    NodeCar (CarData value) { // Construtor
        this.info = value;
    }
    
    void setInfo (CarData value) {
        this.info = value;   
    }
    
    CarData getInfo() {
        return this.info;
    }
    
    void setNext (NodeCar no) {
        this.next = no;
    }
    
    NodeCar getNext() {
        return this.next;
    }
    
}
