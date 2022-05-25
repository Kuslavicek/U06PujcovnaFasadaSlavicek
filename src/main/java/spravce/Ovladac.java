/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spravce;

import data.DopravniLetadlo;
import data.DopravniProstredek;
import generator.Generator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.KolekceException;
import kolekce.LinkSeznam;
import kolekce.LinkSeznam.Prvek;
import perzistence.BinarSoubor;
import perzistence.TextSoubor;

/**
 *
 * @author HP
 */
public class Ovladac<E> implements Ovladani<E> {
    
    private LinkSeznam<E> seznam = new LinkSeznam<E>();
    Comparator<DopravniProstredek> komparatorID =(a,b) -> a.getId().compareTo(b.getId());
    Comparator<DopravniProstredek> komparatorNazev =(a,b) -> a.getNazev().compareTo(b.getNazev());
    Comparator<DopravniProstredek> komparator = komparatorID;
    
    public Iterator<E> iterator() {
        return seznam.iterator();
    }
    
    
    @Override
    public E aktualniPrvek(){
        E prvek = null;
        try {
            prvek = seznam.dejAktualni();
        } catch (KolekceException ex) {
            
        }
        return prvek;
    }
    
    

    @Override
    public E najdi(String klic, String typ) {
        LinkSeznam.Prvek prvni = seznam.prvniPrvek();
        E prvek = null;
        DopravniLetadlo klicDop;
        boolean nalezeno = false;
        if(typ.equals("i")){
            klicDop = new DopravniLetadlo(klic, true);
             komparator = komparatorID;
        }else{
            klicDop= new DopravniLetadlo(klic, false);
            komparator = komparatorNazev;
        }
        
        DopravniProstredek klicD = (DopravniProstredek) klicDop;
        while (prvni.dalsiPrvek() != null && nalezeno == false) {
            DopravniProstredek data
                    = (DopravniProstredek) prvni.getData();
            if(komparator.compare(klicD, data)==0){
                nalezeno = true;
                prvek = (E)prvni.getData();
            } else {
                prvni = prvni.dalsiPrvek();
                }
            }
        
        if (nalezeno == false) {
            System.out.println("Prvek nenalezen");
            
        }
        return prvek;
    }

    @Override
    public E odeber(String klic) {
        E prvek = null;
        int parametr=0;
        try {
            seznam.nastavPrvni();
            
            boolean nalezenoKOdebrani = false;
            DopravniProstredek klicDop = new DopravniLetadlo(klic, true);
            komparator = komparatorID;
                    
                    while (seznam.jeDalsi() == true && nalezenoKOdebrani == false) {
                        DopravniProstredek data
                                = (DopravniProstredek) seznam.dejAktualni();
                        if (komparator.compare(klicDop, data)==0) {
                            nalezenoKOdebrani = true;
                            seznam.odeberAktualni();
                            System.out.println("Prvek odebrán");
                            break;
                        } else {
                            seznam.dalsi();
                        }
                    }
                    
                
                if (nalezenoKOdebrani == false) {
                    System.out.println("Prvek k odstranění nenalezen");
                }

            } catch (KolekceException e) {
                System.out.println("Chyba při odstraňovaní prvku");
            }
        return prvek;
    }

    @Override
    public void prvni() {
        try {
            seznam.nastavPrvni();
        } catch (KolekceException ex) {

        }
        
    }

    @Override
    public void dalsi() {
        try {
            seznam.dalsi();
        } catch (KolekceException ex) {
            
        }
        
    }

    @Override
    public void posledni() {
        try {
            seznam.nastavPosledni();
        } catch (KolekceException ex) {
            
        }
    }

    @Override
    public void vlozZaAktualni(Object data) {
        try {
            seznam.vlozZaAktualni((E) data);
        } catch (KolekceException ex) {
            
        }
    }

    @Override
    public void edituj(int parametr, String novaData) {
        try {
            DopravniProstredek data = (DopravniProstredek) seznam.dejAktualni();
            
            switch (parametr) {
                case 1:
                    int hodnotaKEditaci = Integer.parseInt(novaData);
                    data.setCena(hodnotaKEditaci);
                    break;
                case 2:
                    int hodnotaKEditaci2 = Integer.parseInt(novaData);
                    data.setHmotnost(hodnotaKEditaci2);
                    break;
                case 3:
                    data.setVyrobce(novaData);
                    break;
                case 4:
                    data.setId(novaData);
                    break;
                case 5:
                    data.setNazev(novaData);
                    break;
            }
            seznam.upravAktualni((E)data);
            System.out.println("Editováno");
        } catch (KolekceException ex) {
            
        }
    }

    @Override
    public void obnov(String soubor) {
        seznam = BinarSoubor.zBinarniho(soubor);
    }
    
    public void zpet(){
        Prvek pred = seznam.predAktualnim();
        seznam.nastavAktualni(pred);
    }

    @Override
    public void zalohuj(String soubor) {
        BinarSoubor.doBinarniho(seznam, soubor);
    }

    @Override
    public void nactiTextSoubor(String soubor) {
        try {
            this.seznam = TextSoubor.zTextoveho(soubor);
        } catch (FileNotFoundException ex) {
            
        }
    }

    @Override
    public void ulozTextSoubor(String soubor) {
        try {
            TextSoubor.doTextoveho(seznam, soubor);
        } catch (IOException ex) {
            
        }
    }

    @Override
    public int dejPocet() {
        return seznam.size();
    }

    @Override
    public void zrus() {
        seznam.zrus();
    }
    public void vypis(){
        if (dejPocet() == 0) {
                        System.out.println("Prázdný seznam");
                    } else {                       
                            LinkSeznam.Prvek prvek = seznam.prvniPrvek();
                            System.out.println(prvek.getData());
                            for (int i = 1; i < seznam.size(); i++) {
                                prvek= prvek.dalsiPrvek();
                                System.out.println(prvek.getData());  
                            }
                    }
    }

    @Override
    public void generuj(int j) {
        for(int i = 0;i<j;i++){
                     seznam.vlozPosledni((E) Generator.generate());   
                    }
    }
    
    public void vloz(E data) throws KolekceException{
        seznam.vlozZaAktualni(data);
    }
    public void dej() throws KolekceException{
        System.out.println(seznam.dejAktualni());
    }
    
    public void vyjmi(){
        try {
            seznam.odeberAktualni();
        } catch (KolekceException ex) {
            
        }
    }
    
    public boolean nastavenAktualni(){
        try{
            return seznam.dejAktualni()!=null;
        }catch(Exception e){
            return false;
        }
    }
    
    
    public void uprav(E data){
        seznam.upravAktualni(data);
    }
    public void vlozPosledni(E data){
        seznam.vlozPosledni(data);
    }
    
    
}
