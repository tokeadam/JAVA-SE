package model;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBModel implements IModel {

    private Connection conn;
    private PreparedStatement getAllSzemelyPstmt;
    private PreparedStatement getSzemelyByIDPstmt;
    private PreparedStatement addSzemelyPstmt;
    private PreparedStatement updateSzemelyPstmt;
    private PreparedStatement removeSzemelyPstmt;
    private PreparedStatement addRendelesPstmt;
    private PreparedStatement updateRendelesPstmt;
    private PreparedStatement removeRendelesPstmt;
    private PreparedStatement getRendelesekSzemelyPstmt;
    private PreparedStatement getRendelesekAllPstmt;

    public DBModel(Connection conn) throws SQLException {
        this.conn = conn;
        getAllSzemelyPstmt = conn.prepareStatement("SELECT * FROM szemely");
        getSzemelyByIDPstmt = conn.prepareStatement("SELECT * FROM szemely WHERE id=?");
        addSzemelyPstmt = conn.prepareStatement("INSERT INTO szemely (nev,email) VALUES (?,?)");
        updateSzemelyPstmt = conn.prepareStatement("UPDATE szemely SET nev=?, email=? WHERE id=?");
        removeSzemelyPstmt = conn.prepareStatement("DELETE FROM szemely WHERE id=?");
        addRendelesPstmt = conn.prepareStatement("INSERT INTO rendeles (rendeloid, osszeg, darabszam, teljesitve) VALUES (?,?,?,?)");
        updateRendelesPstmt = conn.prepareStatement("UPDATE rendeles SET rendeloid=?, osszeg=?, darabszam=?, teljesitve=? WHERE id=?");
        removeRendelesPstmt = conn.prepareStatement("DELETE from rendeles WHERE id=?");
        getRendelesekSzemelyPstmt = conn.prepareStatement("SELECT * FROM rendeles WHERE rendeloid=?");
        getRendelesekAllPstmt = conn.prepareStatement("SELECT * FROM rendeles");
    }

    @Override
    public List<Szemely> getAllSzemely() throws SQLException {
        List<Szemely> szemelyek = new ArrayList<>();

        ResultSet rs = getAllSzemelyPstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nev = rs.getString("nev");
            String email = rs.getString("email");

            Szemely sz = new Szemely(id, nev, email);
            szemelyek.add(sz);
        }
        rs.close();

        return szemelyek;
    }

    @Override
    public Szemely getSzemelyByID(int id) throws SQLException {
        getSzemelyByIDPstmt.setInt(1, id);
        ResultSet rs = getSzemelyByIDPstmt.executeQuery();
        Szemely sz = null ;

        if (rs.next()) {
            sz = new Szemely(
                    rs.getInt("id"),
                    rs.getString("nev"),
                    rs.getString("email"));
        }
        rs.close();
        return sz;
    }

    @Override
    public int addSzemely(Szemely sz) throws SQLException {
        addSzemelyPstmt.setString(1, sz.getNev());
        addSzemelyPstmt.setString(2, sz.getEmail());

        return addSzemelyPstmt.executeUpdate();
    }

    @Override
    public int updateSzemely(Szemely sz) throws SQLException {
        updateSzemelyPstmt.setString(1, sz.getNev());
        updateSzemelyPstmt.setString(2, sz.getEmail());
        updateSzemelyPstmt.setInt(3, sz.getId());
        return updateSzemelyPstmt.executeUpdate();
    }

    @Override
    public int removeSzemely(Szemely sz) throws SQLException {
        removeSzemelyPstmt.setInt(1, sz.getId());
        return removeSzemelyPstmt.executeUpdate();
    }

    @Override
    public int addRendeles(Rendeles m) throws SQLException {
        addRendelesPstmt.setInt(1, m.getRendeloId());
        addRendelesPstmt.setInt(2, m.getOsszeg());
        addRendelesPstmt.setInt(3, m.getDarabszam());
        addRendelesPstmt.setBoolean(4, m.isTeljesitve());
        return addRendelesPstmt.executeUpdate();
    }

    @Override
    public int updateRendeles(Rendeles m) throws SQLException {
        updateRendelesPstmt.setInt(1, m.getRendeloId());
        updateRendelesPstmt.setInt(2, m.getOsszeg());
        updateRendelesPstmt.setInt(3, m.getDarabszam());
        updateRendelesPstmt.setBoolean(4, m.isTeljesitve());
        updateRendelesPstmt.setInt(5, m.getId());
        return updateRendelesPstmt.executeUpdate();
    }

    @Override
    public int removeRendeles(Rendeles m) throws SQLException {
        removeRendelesPstmt.setInt(1, m.getId());
        return removeRendelesPstmt.executeUpdate();
    }

    @Override
    public List<Rendeles> getAllRendeles() throws SQLException {
        ResultSet rs = getRendelesekAllPstmt.executeQuery();
        List<Rendeles> rendelesek = new ArrayList<>();

        while (rs.next()) {
            Rendeles r = new Rendeles(
                    rs.getInt("id"),
                    rs.getInt("rendeloid"),
                    rs.getInt("osszeg"),
                    rs.getInt("darabszam"),
                    rs.getBoolean("teljesitve"));

            rendelesek.add(r);
        }
        rs.close();
        return rendelesek;
    }

    @Override
    public List<Rendeles> getAllRendeles(Szemely sz) throws SQLException {
        getRendelesekSzemelyPstmt.setInt(1, sz.getId());

        ResultSet rs = getRendelesekSzemelyPstmt.executeQuery();
        List<Rendeles> rendelesek = new ArrayList<>();
        while (rs.next()) {
            Rendeles r
                    = new Rendeles(
                            rs.getInt("id"),
                            rs.getInt("rendeloid"),
                            rs.getInt("osszeg"),
                            rs.getInt("darabszam"),
                            rs.getBoolean("teljesitve"));
            rendelesek.add(r);
        }
        rs.close();
        return rendelesek;
    }

    @Override
    public void exportToFile(File f) throws Exception {

        PrintWriter pw = new PrintWriter(new FileWriter(f));

        pw.println("id;nev;email");
        List<Szemely> szemelyek = getAllSzemely();
        for (Szemely sz : szemelyek) {
            pw.println(sz.getId() + ";" + sz.getNev() + ";" + sz.getEmail());
        }

        pw.println("id;rendeloid;osszeg;darabszam;teljesitve");
        List<Rendeles> rendelesek = getAllRendeles();
        for (Rendeles r : rendelesek) {
            pw.println(r.getId() + ";" + r.getRendeloId() + ";" + r.getOsszeg()
                    + ";" + r.getDarabszam() + ";" + r.isTeljesitve());
        }

        pw.close();
    }

    @Override
    public Map<Integer, Szemely> getSzemelyMap() throws SQLException {
        Map<Integer, Szemely> szemelyMap = new HashMap<>();

        List<Szemely> szemelyek = getAllSzemely();
        for (Szemely sz : szemelyek) {
            szemelyMap.put(sz.getId(), sz);
        }

        return szemelyMap;
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

}
