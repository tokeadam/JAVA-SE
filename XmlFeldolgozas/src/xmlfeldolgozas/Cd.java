/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlfeldolgozas;

public class Cd {
    private String artist;
    private String title;
    private String company;
    private String country;
    private double price;
    private int year;

    public Cd() {
    }

    public Cd(String artist, String title, String company, String country, double price, int year) {
        this.artist = artist;
        this.title = title;
        this.company = company;
        this.country = country;
        this.price = price;
        this.year = year;
    }

    @Override
    public String toString() {
        return artist + ", title=" + title + ", company=" + company + ", country=" + country + ", price=" + price + ", year=" + year ;
    }

  
        
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
