/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spravce;

import data.DopravniProstredek;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author HP
 */
public interface Ovladani<E> extends Iterable<E> {
    
    /**
     * Najde prvek v seznamu podle klíče. Typ určuje typ parametru podle kterého se hledá.
     * @param klic
     * @param typ
     * @return vrací nalezený prvek
     */
    
    E najdi(String klic, String typ);
    
    /**
     * Odebere prvek spojového seznamu podle klíče.
     * 
     * @param klic
     * @return vrací odebraný prvek
     */
    
    
    E odeber(String klic);
    
    /**
     * Metoda pohybu prvni() nastaví vnitřní aktuální ukazatel na první
     * data seznamu.
     *
    */
    
    void prvni();
    
    /**
     * Metoda pohybu dalsi() přestaví vnitřní aktuální ukazatel na další prvek
     * seznamu.
     * 
    */
    void dalsi();
    
    /**
     * Metoda pohybu posledni() nastaví vnitřní aktuální ukazatel na
     * poslední data seznamu.
     * 
    */
    void posledni();
    
    /**
     * Vkládací metoda vlozZaAktualni() vloží nový prvek s entitami type E za
     * aktuální prvek. Metoda musí rozpojit seznam a spojit ho zpět přes nově
     * vytvořený prvek.
     *
    */
    void vlozZaAktualni(E data);
    
    /**
     * Edituje data v aktuálním prvku.
     * @param parametr
     * @param novaData 
     */
    void edituj(int parametr, String novaData);
    
    /**
     * Uloží data do binárního souboru.
     * @param soubor 
     */
    void obnov(String soubor);
    
    /**
     * Načte data z binárního souboru.
     * @param soubor 
     */
    void zalohuj(String soubor);
    
    /**
     * Uloží data do textového souboru.
     * @param soubor 
     */
    void nactiTextSoubor(String soubor);
    
    /**
     * Načte data z textového souboru.
     * @param soubor 
     */
    void ulozTextSoubor(String soubor);

    /**
     * Metoda size() vrací aktuální počet dat v seznamu.
     *
     * @return Vrací hodnotu s počtem dat v seznamu.
     */
    int dejPocet();
    
    /**
     * Metoda zrus() odebere všechny data ze seznamu.
     */
    void zrus();
    
    /**
     * vygeneruje i prvků a vloží je do seznamu.
     * @param i 
     */
    void generuj(int i);
    
    /**
     * Metoda převede obsah seznamu na datový proud, který předá při návratu.
     *
     * Tato metoda se v implementačních třídách nepřekrývá.
     *
     * @return datovy proud
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
    /**
     * Vrací aktuální prvek.
     * @return 
     */
    E aktualniPrvek();
    
}
