package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


public class OlvasoSzal extends Thread{
    private BufferedReader br;
    private JTextArea taUzenetek;

    public OlvasoSzal(BufferedReader br, JTextArea taUzenetek) {
        this.br = br;
        this.taUzenetek = taUzenetek;
        setDaemon(true);
    }

    @Override
    public void run() {
        do { 
            try {
                String uzenet = br.readLine();
                taUzenetek.append(uzenet+"\n");           
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } while(true);
    }
    
    
}
