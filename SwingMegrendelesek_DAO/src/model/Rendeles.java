package model;

public class Rendeles {
    private int id;
    private int rendeloId;
    private int osszeg;
    private int darabszam;
    private boolean teljesitve;

    public Rendeles() {
    }

    public Rendeles(int id, int rendeloId, int osszeg, int darabszam, boolean teljesitve) {
        this.id = id;
        this.rendeloId = rendeloId;
        this.osszeg = osszeg;
        this.darabszam = darabszam;
        this.teljesitve = teljesitve;
    }

    public Rendeles(int rendeloId, int osszeg, int darabszam, boolean teljesitve) {
        this.rendeloId = rendeloId;
        this.osszeg = osszeg;
        this.darabszam = darabszam;
        this.teljesitve = teljesitve;
    }

    public boolean isTeljesitve() {
        return teljesitve;
    }

    public void setTeljesitve(boolean teljesitve) {
        this.teljesitve = teljesitve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRendeloId() {
        return rendeloId;
    }

    public void setRendeloId(int rendeloId) {
        this.rendeloId = rendeloId;
    }

    public int getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg = osszeg;
    }

    public int getDarabszam() {
        return darabszam;
    }

    public void setDarabszam(int darabszam) {
        this.darabszam = darabszam;
    }
    
    
}
