/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author HP
 */
public final class StihaciLetoun extends DopravniProstredek{
    private int pocetZbrani;

    public StihaciLetoun(int pocetZbrani, int cena, int hmotnost, String vyrobce, String id, String nazev) {
        super(cena, hmotnost, vyrobce, TypProstredku.STIHACILETOUN, id, nazev);
        this.pocetZbrani = pocetZbrani;
    }

    public int getPocetZbrani() {
        return pocetZbrani;
    }

    public void setPocetZbrani(int pocetZbrani) {
        this.pocetZbrani = pocetZbrani;
    }

    @Override
    public String toString() {
        return super.toString() + "pocetZbrani=" + pocetZbrani;
    }
    
    
}
