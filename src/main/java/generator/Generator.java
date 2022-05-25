/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator;
import data.*;
import java.util.Random;
/**
 *
 * @author HP
 */
public class Generator {
    
    static String[] vyrobciVrtulniku = new String[]{"Eurocopter", "Sikorski", "Kamov"};
    static String[] vyrobciLetadel = new String[]{"Airbus", "Boeing", "Douglas"};
    static String[] vyrobciStihacichLetounu = new String[]{"Eurofighter", "Lockheed", "OKB Suchoj"};
    
   
    public static DopravniProstredek generate(){
        int hmotnost = (int)(Math.random()*100000);
        int cena = (int) (Math.random()*3000000)+2000000;
        int typIndex = new Random().nextInt(TypProstredku.values().length);
        TypProstredku typ = TypProstredku.values()[typIndex];
        int vyrobceIndex = (int)Math.random()*3;
        String vyrobce;
        String id = randomNumber();
        switch (typ){
            case VRTULNIK:
               Vrtulnik novy;
               vyrobce = vyrobciVrtulniku[vyrobceIndex];
               int pocetVrtuli = (int) (Math.random()+2);
               novy = new Vrtulnik(pocetVrtuli, cena, hmotnost, vyrobce,
                       "V"+id, "Vrtulnik"+id); 
               return novy; 
               
            case STIHACILETOUN:
                StihaciLetoun novySt;
                vyrobce = vyrobciStihacichLetounu[vyrobceIndex];
                int pocetZbrani = (int) (Math.random()*4)+4;
                novySt = new StihaciLetoun(pocetZbrani, cena, hmotnost, 
                        vyrobce, "S"+id, "Stihac"+id);
                return novySt;
                
            default:
                DopravniLetadlo novyDop;
                vyrobce = vyrobciLetadel[vyrobceIndex];
                int pocetSedadel = (int) (Math.random()*100)+200;
                novyDop = new DopravniLetadlo(pocetSedadel, cena, hmotnost,
                    vyrobce, "L"+id, "Letadlo"+id);
                return novyDop;
              
        }
        
    }
    
    private static String randomNumber(){
        Random generCislo = new Random();
        String cislo="";
        for(int i = 0; i<4; i++){
            cislo = cislo + Integer.toString(generCislo.nextInt(10));
        }
        return cislo;
    }
    
}
