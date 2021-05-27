package swinglampa;

public class LampaModel {
    private boolean piros;
    private boolean sarga;
    private boolean zold;

    public LampaModel() {
    }

    public LampaModel(boolean piros, boolean sarga, boolean zold) {
        this.piros = piros;
        this.sarga = sarga;
        this.zold = zold;
    }
    
    public void pirosKapcsol() {
        piros = !piros;
    }
    
    public void sargaKapcsol(){
        sarga = !sarga;
    }

    public void zoldKapcsol(){
        zold = !zold;
    }
    
    public void setLampa(boolean piros, boolean sarga, boolean zold) {
        this.piros = piros;
        this.sarga = sarga;
        this.zold = zold;
    }
     
    public boolean isZold() {
        return zold;
    }

    public void setZold(boolean zold) {
        this.zold = zold;
    }

    public boolean isPiros() {
        return piros;
    }

    public void setPiros(boolean piros) {
        this.piros = piros;
    }

    public boolean isSarga() {
        return sarga;
    }

    public void setSarga(boolean sarga) {
        this.sarga = sarga;
    }
    
    
}
