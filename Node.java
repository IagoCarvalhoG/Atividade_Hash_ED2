import java.io.Serializable;
public class Node implements Serializable {
    private String plate;
    private int pos;
    private int stats;
    private int next;

    public Node(String plate){
        this.plate = plate;
        this.pos = -1;
        this.stats = 0;
        this.next = -1;
    }

    public String getPlate(){
        return this.plate;
    }
    public void setPlate(String plate){
        this.plate = plate;
    }
    public int getPos(){
        return this.pos;
    }
    public void setPos(int pos){
        this.pos = pos;
    }
    public int getStats(){
        return this.stats;
    }
    public void setStats(int stats){
        this.stats = stats;
    }
    public int getNext(){
        return this.next;
    }
    public void setNext(int next){
        this.next = next;
    }
    @Override
    public String toString () {
        return this.plate + " " + this.pos + " " + this.stats + " " + this.next;
    }
}
