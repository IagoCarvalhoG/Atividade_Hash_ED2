import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.Serializable;
public class ManipulateFile implements Serializable {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    public void openToWrite (String nomeArq) {
        try {
            File arq = new File(nomeArq);
            FileOutputStream fileOut = new FileOutputStream(arq);
            output = new ObjectOutputStream(fileOut);
            System.out.println("Arquivo criado");
        }
        catch (IOException ioException){
            System.err.println("Erro ao tentar criar o arquivo para gravação");
            System.exit(1);
        }
    }
    
    public boolean openToRead (String nomeArq) {
        try {
             File arq = new File(nomeArq);
             if (arq.exists() == false) {
                return false;
            } else {
                FileInputStream fileIn = new FileInputStream(arq);
                input = new ObjectInputStream(fileIn);
                return true;
            }
        }
        catch (IOException ioException){
            System.err.println("Erro ao tentar abrir o arquivo para leitura");
            return false;
        }
    }
    
    public void closeWriteFile() {
        try {
            if (output != null) {
                output.flush();
                output.close();
                output = null;
                System.out.println("Arquivo fechado após gravação");
             }
        }
        catch (IOException ioException){
            System.err.println("Erro ao tentar fechar o arquivo após gravação");
            System.exit(1);
        } 
    }
    
    public void closeReadFile() {
        try {
            if (input != null) {
                input.close();
                input = null;
                System.out.println("Arquivo fechado após leitura");
             }
        }
        catch (IOException ioException){
            System.err.println("Erro ao tentar fechar o arquivo após leitura");
            System.exit(1);
        } 
    }
    
    public void gravarDados (Object al) {
        try {
             output.writeObject (al);
        }
        catch (IOException ioException){
            System.err.println("Erro de gravação");
            System.exit(1);
        }
    }
 
    public CarData lerDados (){
        CarData car;
        
        try {
            car = (CarData) input.readObject();
            return car;
        }
        catch (EOFException endOfFileException) {
            return null; //fim de arquivo
        }
        catch (ClassNotFoundException classNotFoundException) {
            return null;
        }
        catch (IOException ioException){
            return null;
        } 
    }
    //alterar
    public Node readFromPosition(String file, long position) {
        try (RandomAccessFile reader = new RandomAccessFile(file, "r")) {
            //byte[] positionByte = new byte[(int) reader.length()];
            //reader.read(positionByte, (int)position, (int)reader.length());
            //ByteArrayInputStream byteData = new ByteArrayInputStream(positionByte);
            reader.seek(position);
            long bytePosition = reader.length() - reader.getFilePointer();
            byte[] objBytes = new byte[(int) bytePosition];
            reader.read(objBytes);
            InputStream byteData = new ByteArrayInputStream(objBytes);
            this.input = new ObjectInputStream(byteData);
            Node object = (Node) this.input.readObject();
            reader.seek(0);
            return object;
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public byte[] convertNodeToByte( Node node) throws IOException {
            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
            ObjectOutputStream data = new ObjectOutputStream(byteBuffer);
            try {
                data.writeObject(node);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            byte[] imput = byteBuffer.toByteArray();
            return imput;
    }

    public void registerNodeHash(String file, long position, byte[] data){
    try (RandomAccessFile write = new RandomAccessFile(file, "rw")) {
        write.seek(position);
        write.write(data);
        write.seek(0);
        //reader.seek(position);
        //long bytePosition = reader.length() - reader.getFilePointer();
        //byte[] objBytes = new byte[(int) bytePosition];
        //reader.read(objBytes);
        //ByteArrayInputStream bis = new ByteArrayInputStream(objBytes);

    
            
        } catch (IOException e) {
           System.out.println("Problema em escrever");
        }

    }
} 

