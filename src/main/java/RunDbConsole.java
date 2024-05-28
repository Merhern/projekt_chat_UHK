import org.apache.derby.tools.ij;

import java.io.IOException;

public class RunDbConsole {
    public static void main(String[] args) {
        try {
            // connect 'jdbc:derby:ChatClient_skB;create=true';     příkaz na připojení k DB
            ij.main(args); // provoláme derbytools
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
