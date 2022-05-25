/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author HP
 */
public final class DopravniLetadlo extends DopravniProstredek{
    private int početSedadel=0;

    public DopravniLetadlo(int početSedadel, int cena, int hmotnost, String vyrobce, String id, String nazev) {
        super(cena, hmotnost, vyrobce, TypProstredku.DOPRAVNILETADLO, id, nazev);
        this.početSedadel = početSedadel;
    }
    public DopravniLetadlo(String parametr, boolean jeId){
        super(parametr, jeId);
    }

    public int getPočetSedadel() {
        return početSedadel;
    }

    public void setPočetSedadel(int početSedadel) {
        this.početSedadel = početSedadel;
    }

    @Override
    public String toString() {
        return super.toString() + " početSedadel=" + početSedadel;
    }
    
    
    
}
