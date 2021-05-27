/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlfeldolgozas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XmlFeldolgozas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            
            List<Cd> cdk = new ArrayList<>();
            
            
            File file = new File("//Users/javaoktatashu/prog/cd_catalog.xml");
            
            // File file = new File("c:/prog/cd_catalog.xml");   //Windowson
            Document document = saxBuilder.build(file);
            Element root = document.getRootElement();
            List<Element> cdElementList = root.getChildren();
            
            for (Element e : cdElementList) {                                   
                Cd cd = new Cd(
                        e.getChildText("ARTIST"),
                        e.getChildText("TITLE"),
                        e.getChildText("COMPANY"),
                        e.getChildText("COUNTRY"),
                        Double.parseDouble(e.getChildText("PRICE")),                                
                        Integer.parseInt(e.getChildText("YEAR")));
                cdk.add(cd);
            }
            
            for (Cd cd : cdk) {
                System.out.println(cd);
            }
        } catch (JDOMException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
            
       

    }

}
