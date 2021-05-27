package chatserver;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OlvasoSzal extends Thread {

    KliensKapcsolat kliens;
    Vector<KliensKapcsolat> kliensek;

    public OlvasoSzal(KliensKapcsolat kliens, Vector<KliensKapcsolat> kliensek) {
        this.kliens = kliens;
        this.kliensek = kliensek;
        setDaemon(true);
    }

    @Override
    public void run() {
        String uzenet = "";
        do {
            try {
                uzenet = kliens.getBr().readLine();
                if (!"quit".equals(uzenet)) {
                    System.out.println(kliens.getNev() + ": " + uzenet);
                    
                    for (KliensKapcsolat kk : kliensek) {
                        if (kk != kliens) {
                            kk.getPw().println(kliens.getNev() + ": " + uzenet);
                            kk.getPw().flush();
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } while (!uzenet.equals("quit"));
        kliensek.remove(kliens);
        System.out.println(kliens.getNev() + " kilépett a beszélgetésből");  //a server consolra kiirja
        
        for (KliensKapcsolat kk : kliensek) { //minden résztvevőnek elküldjük
            kk.getPw().println(kliens.getNev() + " kilépett a beszélgetésből");
            kk.getPw().flush();
        }
    }
}
