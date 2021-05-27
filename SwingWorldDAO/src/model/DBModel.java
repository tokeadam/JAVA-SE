/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DBModel implements IModel {

    private Connection conn;
    private PreparedStatement getAllCitiesPstmt;
    private PreparedStatement getCitiesByCountryPstmt;
    private PreparedStatement getAllCountriesPstmt;
    private PreparedStatement getCountriesByNamePstmt;

    public DBModel(Connection conn) throws SQLException {
        this.conn = conn;
        getAllCitiesPstmt = conn.prepareStatement("SELECT * FROM city");
        getCitiesByCountryPstmt = conn.prepareStatement("SELECT * FROM city WHERE CountryCode=?");
        getAllCountriesPstmt = conn.prepareStatement("SELECT * FROM country");
        getCountriesByNamePstmt = conn.prepareStatement("SELECT * FROM country WHERE name LIKE ?");
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

    public Map<String, Country> getCountryMap() throws SQLException {
        Map<String, Country> cMap = new HashMap<>();
        Vector<Country> countries = getAllCountries();
        for (Country cou : countries) {
            cMap.put(cou.getCountryCode(), cou);
        }
        return cMap;
    }

    @Override
    public Vector<City> getAllCities() throws SQLException {
        Map<String, Country> cMap = getCountryMap();
        Vector<City> cities = new Vector<>();
        ResultSet rs = getAllCitiesPstmt.executeQuery();
        while (rs.next()) {
            cities.add(new City(
                    rs.getInt("id"),
                    rs.getString("name"),
                    cMap.get(rs.getString("countryCode")),
                    rs.getString("district"),
                    rs.getInt("population")));
        }
        return cities;
    }

    @Override
    public Vector<City> getCitiesByCountry(Country country) throws SQLException {
        Vector<City> cities = new Vector<>();
        getCitiesByCountryPstmt.setString(1, country.getCountryCode());

        ResultSet rs = getCitiesByCountryPstmt.executeQuery();
        while (rs.next()) {
            cities.add(new City(
                    rs.getInt("id"),
                    rs.getString("name"),
                    country,
                    rs.getString("district"),
                    rs.getInt("population")));
        }
        return cities;
    }

    @Override
    public int addCity(City c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateCity(City c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeCity(City c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector<Country> getAllCountries() throws SQLException {
        Vector<Country> cous = new Vector<>();
        ResultSet rs = getAllCountriesPstmt.executeQuery();

        while (rs.next()) {
            Country c = new Country(
                    rs.getString("Code"),
                    rs.getString("Name"),
                    rs.getString("Continent"),
                    rs.getString("Region"),
                    rs.getFloat("SurfaceArea"),
                    rs.getShort("IndepYear"),
                    rs.getInt("Population"),
                    rs.getFloat("LifeExpectancy"),
                    rs.getFloat("GNP"),
                    rs.getFloat("GNPOld"),
                    rs.getString("LocalName"),
                    rs.getString("GovernmentForm"),
                    rs.getString("HeadOfState"),
                    rs.getInt("Capital"),
                    rs.getString("Code2"),
                    null, //Set<City>
                    null);//Set<CountryLanguage>
            cous.add(c);
        }
        return cous;
    }

    @Override
    public int addCountry(Country c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateCountry(Country c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeCountry(Country c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector<Country> getCountriesByName(String name) throws SQLException {
        Vector<Country> cous = new Vector<>();
        getCountriesByNamePstmt.setString(1, name + "%");
        ResultSet rs = getCountriesByNamePstmt.executeQuery();

        while (rs.next()) {
            Country c = new Country(
                    rs.getString("Code"),
                    rs.getString("Name"),
                    rs.getString("Continent"),
                    rs.getString("Region"),
                    rs.getFloat("SurfaceArea"),
                    rs.getShort("IndepYear"),
                    rs.getInt("Population"),
                    rs.getFloat("LifeExpectancy"),
                    rs.getFloat("GNP"),
                    rs.getFloat("GNPOld"),
                    rs.getString("LocalName"),
                    rs.getString("GovernmentForm"),
                    rs.getString("HeadOfState"),
                    rs.getInt("Capital"),
                    rs.getString("Code2"),
                    null, //Set<City>
                    null);//Set<CountryLanguage>
            cous.add(c);
        }
        return cous;
    }

}
