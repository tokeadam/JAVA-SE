package swinglampa;

import javax.swing.JPanel;

public class VezerloSzal extends Thread {

    private LampaModel model;
    private JPanel panel;

    public VezerloSzal(LampaModel model, JPanel panel) {
        this.model = model;
        this.panel = panel;
        setDaemon(true);
    }

    @Override
    public void run() {

        try {
            while (true) {
                model.setLampa(true, false, false);
                panel.repaint();
                Thread.sleep(1000);

                model.setLampa(true, true, false);
                panel.repaint();
                Thread.sleep(1000);

                model.setLampa(false, false, true);
                panel.repaint();
                Thread.sleep(1000);

                model.setLampa(false, true, false);
                panel.repaint();
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
           
        }

    }

}
