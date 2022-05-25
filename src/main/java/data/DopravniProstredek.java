/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public abstract class DopravniProstredek implements Serializable{
    private int cena;
    private int hmotnost;
    private String vyrobce;
    private TypProstredku typ;
    private String id;
    private String nazev;

    public DopravniProstredek(int cena, int hmotnost, String vyrobce, 
            TypProstredku typ, String id, String nazev) {
        this.cena = cena;
        this.hmotnost = hmotnost;
        this.vyrobce = vyrobce;
        this.id=id;
        this.nazev=nazev;
        this.typ = typ;
    }
    public DopravniProstredek(String parametr, Boolean jeId) {
        this.cena = 0;
        this.hmotnost = 0;
        this.vyrobce = "";
        if(jeId){
            this.id=parametr;
            this.nazev="";
        }else{
            this.id="";
            this.nazev = parametr;
        }
        this.typ = TypProstredku.DOPRAVNILETADLO;
    }
    

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getHmotnost() {
        return hmotnost;
    }

    public void setHmotnost(int hmotnost) {
        this.hmotnost = hmotnost;
    }

    public String getVyrobce() {
        return vyrobce;
    }

    public void setVyrobce(String vyrobce) {
        this.vyrobce = vyrobce;
    }

    public TypProstredku getTyp() {
        return typ;
    }

    public void setTyp(TypProstredku typ) {
        this.typ = typ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return "id=" + id  + ", typ=" + typ.getName() + ", cena=" + cena + ", hmotnost=" + hmotnost + ", vyrobce=" + vyrobce+ ", nazev=" + nazev+" ";
    }
    
    

}
