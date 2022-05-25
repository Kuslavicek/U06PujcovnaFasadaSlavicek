/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author HP
 */
public final class Vrtulnik extends DopravniProstredek {
    private int početVrtuli;

    public Vrtulnik(int početVrtuli, int cena, int hmotnost, String vyrobce, String id, String nazev) {
        super(cena, hmotnost, vyrobce, TypProstredku.VRTULNIK, id, nazev);
        this.početVrtuli = početVrtuli;
        
    }

    public int getPočetVrtuli() {
        return početVrtuli;
    }

    public void setPočetVrtuli(int početVrtuli) {
        this.početVrtuli = početVrtuli;
    }

    @Override
    public String toString() {
        return super.toString() + "početVrtuli=" + početVrtuli;
    }
    
    
}
