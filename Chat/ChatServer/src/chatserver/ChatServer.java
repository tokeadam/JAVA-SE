package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    private static Vector<KliensKapcsolat> kliensek;
    
    public static void main(String[] args) {
        try {
            kliensek = new Vector<>();
            System.out.println("Várok egy kliens kapcsolatra...");
            ServerSocket ss = new ServerSocket(8888);

            do {
                Socket socket = ss.accept();

                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientName = br.readLine();
                System.out.println(clientName + " kapcsolódása megtörtént!");
                KliensKapcsolat kk = new KliensKapcsolat(clientName, socket);
                kliensek.add(kk); 
                
                OlvasoSzal osz = new OlvasoSzal(kk, kliensek);
                osz.start();
                
            } while (true);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
