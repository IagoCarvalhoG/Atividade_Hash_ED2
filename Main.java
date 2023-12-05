import java.io.IOException;
import java.io.Serializable;
public class Main implements Serializable {
    public static void main(String[] args) throws IOException {
        HashList hl = new HashList(101);
        hl.startHashList();
        hl.registerPlate("abcd");
        hl.exhibitNode("abcd");
    }
}
