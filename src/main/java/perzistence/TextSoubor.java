/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perzistence;
import data.DopravniLetadlo;
import data.DopravniProstredek;
import data.StihaciLetoun;
import data.TypProstredku;
import data.Vrtulnik;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import kolekce.LinkSeznam;
import kolekce.LinkSeznam.Prvek;
/**
 *
 * @author HP
 */
public class TextSoubor {
    public static void doTextoveho(LinkSeznam seznam, String cesta) throws IOException{
        FileWriter writer = new FileWriter(cesta);
        Prvek prvni = seznam.prvniPrvek();
        do{
            DopravniProstredek data = (DopravniProstredek)prvni.getData();
            TypProstredku typ = data.getTyp();
            writer.append(data.getTyp()+" ,"+data.getCena()+ " ,"+data.getHmotnost()+" ,"+data.getVyrobce()+" ,"+data.getId()+" ,"+data.getNazev());
            switch(typ){
                    case DOPRAVNILETADLO:
                        DopravniLetadlo dataDop = (DopravniLetadlo)data;
                        writer.append(" ,"+dataDop.getPočetSedadel());
                        break;
                    case STIHACILETOUN:
                        StihaciLetoun dataStih = (StihaciLetoun)data;
                        writer.append(" ,"+dataStih.getPocetZbrani());
                        break;
                    case VRTULNIK:
                        Vrtulnik dataVrt = (Vrtulnik)data;
                        writer.append(" ,"+dataVrt.getPočetVrtuli());
                        break;
            }
            writer.append('\n');
            prvni = prvni.dalsiPrvek();
            
        }while(prvni!=null);
        writer.close();
        
        
        
    }
    public static LinkSeznam zTextoveho(String cesta) throws FileNotFoundException{
        LinkSeznam seznam=new LinkSeznam();
        File file = new File(cesta);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            String text = sc.nextLine();
            String[] pole = text.split(" ,");
            String typ = pole[0];
            int cena  = Integer.parseInt(pole[1]);
            int hmotnost  = Integer.parseInt(pole[2]);
            String vyrobce = pole[3];
            String id = pole[4];
            String nazev = pole[5];
            int special = Integer.parseInt(pole[6]);
            
            switch(typ){
                    case "DOPRAVNILETADLO":
                        DopravniLetadlo noveLet = new DopravniLetadlo(special,cena,hmotnost,vyrobce,id,nazev);
                        seznam.vlozPosledni(noveLet);
                        break;
                    case "STIHACILETOUN":
                        StihaciLetoun novyStih = new StihaciLetoun(special,cena,hmotnost,vyrobce,id,nazev);
                        seznam.vlozPosledni(novyStih);
                        break;
                    case "VRTULNIK":
                        Vrtulnik novyVrt = new Vrtulnik(special,cena,hmotnost,vyrobce,id,nazev);
                        seznam.vlozPosledni(novyVrt);
                        break;
            
            }
        }
        
        return seznam;
    }
}
