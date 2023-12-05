import java.io.Serializable;
public class CarsList implements Serializable {
    private NodeCar first;
    private NodeCar last;
    private int size;

    public boolean isEmpty(){
        if(this.size == 0){
            return true;
        }
        return false;
    }
    public void inserir (CarData a) {
        NodeCar novo = new NodeCar (a);
        
        if (this.isEmpty () == true) {
            this.first = novo;
            this.last = novo;
            this.size = 1;
        }
        else {
            this.last.setNext (novo);
            this.last = novo;
            this.size++;
        }
    }

    public void gravarListaCars () {
        NodeCar aux = this.first;
        
        if (this.isEmpty () == true) {
            System.out.println("Lista vazia");
        }
        else {
            ManipulateFile arq = new ManipulateFile ();
            arq.openToWrite("CarsValues.dat");
            while (aux != null) {
                arq.gravarDados (aux.getInfo());
                aux = aux.getNext();
            }
            arq.closeWriteFile();
        }
    }

    public void carregarListaCars () {
        ManipulateFile arq = new ManipulateFile();
        CarData car;
        boolean exists = arq.openToRead ("CarsValues.dat");
        if (exists == true) {
            car = arq.lerDados ();
            while (car != null) {
                this.inserir(car);
                car = arq.lerDados ();
            }
            arq.closeReadFile();
        }

    }

}
