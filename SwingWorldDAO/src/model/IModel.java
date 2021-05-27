/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.Vector;

public interface IModel {
    void close() throws SQLException;
    
    Vector<City> getAllCities() throws SQLException;
    Vector<City> getCitiesByCountry(Country country) throws SQLException;    
    int addCity(City c) throws SQLException;
    int updateCity(City c) throws SQLException;
    int removeCity(City c) throws SQLException;
    
    Vector<Country> getAllCountries() throws SQLException;
    Vector<Country> getCountriesByName(String name) throws SQLException;
    int addCountry(Country c) throws SQLException;
    int updateCountry(Country c) throws SQLException;
    int removeCountry(Country c) throws SQLException;
}
