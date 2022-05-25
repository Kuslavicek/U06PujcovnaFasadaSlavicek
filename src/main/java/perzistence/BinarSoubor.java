/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perzistence;
import data.DopravniProstredek;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import kolekce.LinkSeznam;
/**
 *
 * @author HP
 */
public class BinarSoubor {
    public static void doBinarniho(LinkSeznam seznam, String cesta){
        try{
            ObjectOutputStream out = new ObjectOutputStream(
                                 new FileOutputStream(cesta));
            out.writeObject(seznam);
            out.close();
        }catch(Exception e){
            System.out.println("Chyba při serializaci");
            e.printStackTrace();
        }
            
            
    }
    public static LinkSeznam zBinarniho(String cesta){
        LinkSeznam seznam= new LinkSeznam();
        try{
        ObjectInputStream vstup = new ObjectInputStream(
                                    new FileInputStream(cesta));
        seznam = (LinkSeznam)vstup.readObject();
        vstup.close();
        }catch(Exception e){
            System.out.println("Konec Souboru");
            e.printStackTrace();
        }
        return seznam;
    }
}
