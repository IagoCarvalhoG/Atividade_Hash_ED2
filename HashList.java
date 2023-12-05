
import java.io.IOException;
import java.io.Serializable;
public class HashList implements Serializable {
    private int size;
    private String fileName;
    
   
    public HashList(int size){
        this.size = size;
        this.fileName = "HashList.dat";
    }
    public int getSize(){
        return this.size;
    }
    public void setSize(int size){
        this.size = size;
    }

    public void startHashList(){
        ManipulateFile mf = new ManipulateFile();
        mf.openToWrite(this.fileName);
        for(int i =0; i<this.size; i++){
            Node node = new Node(null);
            mf.gravarDados(node);
        }
        mf.closeWriteFile();
    }

    public int getHashValue(String plate){
        int soma = 0;
        for(int i =0; i<plate.length(); i++){
            soma = soma + plate.charAt(i);
        }
        int result = soma % this.size;
        return result;
    }


    public int searchPlate(String plate){
        ManipulateFile mf = new ManipulateFile();
        int position = getHashValue(plate);
        Node node =  mf.readFromPosition(this.fileName, position);
        if(node.getStats() == 0){
            return -1;
        }
        return recursiveSearchPlate(plate, position);
    }
    private int recursiveSearchPlate(String plate, int position){
         ManipulateFile mf = new ManipulateFile();
         Node node =  mf.readFromPosition(this.fileName, position);
        if(node.getPlate().equals(plate)){
            return position;
        }
        if(node.getNext() != -1){
            return recursiveSearchPlate(plate, node.getNext());
        }
        return -1;
    }

    public void registerPlate(String plate) throws IOException{
        int position = getHashValue(plate);
        ManipulateFile mf = new ManipulateFile();
        Node node =  mf.readFromPosition(this.fileName, position);
        
        if(searchPlate(plate) != -1){
            System.out.println("O cadastramento nao pode ser efetuado pois a placa ja existe");
        }else if(node.getStats()==0){
            node.setPlate(plate);
            node.setStats(1);
            //node.setPos(position);
            byte[] data = mf.convertNodeToByte(node);
            mf.registerNodeHash(this.fileName, position, data);
        }/*else{
            Node nodeNext = table[position];
            while (nodeNext.getNext() != -1) {
                nodeNext = table[nodeNext.getNext()];
            }
            if(nodeNext.getPos() <this.size){
                position = findNextEmptyPos(101);
                if(position == -1){
                    System.out.println("Nao foi possivel registrar a chave por falta de espaço");
                }else{
                Node nodeToRegister = new Node(plate,position);
                nodeNext.setNext(position);
                this.table[position] = nodeToRegister;
                }
          } */  
        }
        public void exhibitNode(String plate){
            ManipulateFile mf = new ManipulateFile();
            int position = searchPlate(plate);
            if(position == -1){
                System.out.println("Placa nao encontrada");
            }else{
                Node node =  mf.readFromPosition(this.fileName, position);
                String result = node.toString();
                System.out.println(result);
            }
        }
    }
    /* 
    private int findNextEmptyPos(int pos){
        for(int i = pos; i<table.length;i++){
            if(table[i] == null){
                return i;
            }
        }
        return -1;
    }*/
    

