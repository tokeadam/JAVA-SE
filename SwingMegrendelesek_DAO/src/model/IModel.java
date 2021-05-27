package model;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IModel {
    List<Szemely> getAllSzemely() throws SQLException;
    Szemely getSzemelyByID(int id) throws SQLException;
    int removeSzemely(Szemely r) throws SQLException;
    int updateSzemely(Szemely r) throws SQLException;
    int addSzemely(Szemely r) throws SQLException;
    
    List<Rendeles> getAllRendeles() throws SQLException;
    int removeRendeles(Rendeles r) throws SQLException;
    int updateRendeles(Rendeles r) throws SQLException;
    int addRendeles(Rendeles r) throws SQLException;
    
    public List<Rendeles> getAllRendeles(Szemely sz) throws SQLException;
    public void exportToFile(File f) throws Exception;
    public Map<Integer, Szemely> getSzemelyMap() throws SQLException;
    
    public void close() throws SQLException;
    
}
