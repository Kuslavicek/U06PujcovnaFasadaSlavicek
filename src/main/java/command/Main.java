/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import java.util.Scanner;
import kolekce.LinkSeznam;
import data.*;
import generator.Generator;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.KolekceException;
import kolekce.LinkSeznam.Prvek;
import perzistence.BinarSoubor;
import spravce.Ovladac;

/**
 *
 * @author HP
 */
public class Main {
    private static final String BINARNI_SOUBOR = "seznam.bin";
    private static final String TEXTOVY__SOUBOR = "seznam.txt";
    static Scanner scan = new Scanner(System.in);

    
    public static void main(String[] args) {
        Ovladac ovladac = new Ovladac();

        boolean isActive = true;
        do {
            System.out.println("");
            System.out.println("Zadejte příkaz");
            String command = scan.nextLine();
            switch (command) {
                case "help":
                case "h":
                    System.out.println(
                            """
                    help, h     - výpis příkazů
                    novy,no     - vytvoř novou instanci a vlož data za aktuální prvek
                    najdi,na,n  - najdi v seznamu data podle hodnoty nějakém atributu
                    odeber,od   - odeber data ze seznamu podle nějaké hodnoty atributu 
                    dej         - zobraz aktuální data v seznamu
                    edituj,edit - edituj aktuální data v seznamu
                    vyjmi       - vyjmi aktuální data ze seznamu
                    prvni,pr    - nastav jako aktuální první data v seznamu
                    dalsi,da    - přejdi na další data
                    posledni,po - přejdi na poslední data
                    pocet       - zobraz počet položek v seznamu
                    obnov       - obnov seznam data z binárního souboru
                    zalohuj     - zálohuj seznam dat do binárního souboru
                    vypis       - zobraz seznam dat
                    nactitext,nt- načti seznam data z textového souboru
                    uloztext,ut - ulož seznam data do textového souboru
                    generuj,g   - generuj náhodně data pro testování
                    zrus        - zruš všechny data v seznamu
                    exit        - ukončení programu
                    """
                    );
                    break;
                case "novy":
                case "no":
                    try {
                    DopravniProstredek novy = novy();
                    ovladac.vloz(novy);
                } catch (KolekceException e) {
                    System.out.println("Chyba při vytváření nového prvku");
                }
                break;
                case "najdi":
                case "na":
                case "n":

                   
                    System.out.println("Zadejte hodnotu ID");
                    String text = null;
                    text = scan.nextLine();
                    DopravniProstredek data = (DopravniProstredek)ovladac.najdi(text, new String("i"));
                    if(data!=null){
                        System.out.println(data);
                    }

                    break;
                case "odeber":
                case "od":
                    

                    System.out.println("Zadejte id prvku k odebrání");
                    String textKOdebrani = null;
                    textKOdebrani = scan.nextLine();
                    ovladac.odeber(textKOdebrani);

                break;
                case "dej":
                try {
                    ovladac.dej();
                } catch (KolekceException ex) {
                    System.out.println("Není nastaven aktuální prvek");
                }

                break;

                case "edituj":
                case "edit":
                    try{
                        System.out.println("""
                       Vyberte podle kterou hodnotu chcete změnit
                       1 cena
                       2 hmotnost
                       3 výrobce
                       4 id
                       5 nazev
                       """);
                    boolean validEdit = false;
                    int hodnotaEdit = 0;
                    do {
                        try {
                            hodnotaEdit = scan.nextInt();
                            if (hodnotaEdit >= 1 && hodnotaEdit <= 5) {
                                validEdit = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Špatná hodnota");
                        }
                    } while (validEdit = false);

                    System.out.println("Zadejte novou hodnotu parametru");
                    String textKEditaci = null;
                    boolean validInt = false;
                    scan.nextLine();
                    textKEditaci = scan.nextLine();
                    ovladac.edituj(hodnotaEdit, textKEditaci);
                    }catch(Exception e){
                        System.out.println("Není nastaven aktuální prvek");
                    }
                break;
                case "vyjmi":
                    if(ovladac.nastavenAktualni()){
                        ovladac.vyjmi();
                        System.out.println("Aktuální prvek vyjmut");
                    }else{
                        System.out.println("Není nastaven aktuální prvek");
                    }
                break;
                case "prvni":
                case "pr":
                     ovladac.prvni();
                break;

                case "dalsi":
                case "da":
                    try{
                        ovladac.dalsi();
                    }catch(Exception e){
                        System.out.println("Není nastaven aktuální prvek");
                    }
                break;

                case "posledni":
                case "po":
                    ovladac.posledni();
                break;

                case "pocet":
                    System.out.println(ovladac.dejPocet());
                    break;

                case "obnov":
                    try{
                        ovladac.obnov(BINARNI_SOUBOR);
                    }catch(Exception e){
                        System.out.println("Prázdný soubor");
                    }
                    break;

                case "zalohuj":
                    ovladac.zalohuj(BINARNI_SOUBOR);
                    break;

                case "vypis":
                    ovladac.vypis();
                    break;
                case "zpet":
                    try{
                        ovladac.zpet();
                    }catch(Exception e){
                        System.out.println("Není nastaven aktuální prvek");
                    }
                    break;
                case "nactitext":
                case "nt":
                    try{
                        ovladac.nactiTextSoubor(TEXTOVY__SOUBOR);
                    }catch(Exception e){
                        System.out.println("Prázdný soubor");
                    }
                    break;
                case "uloztext":
                case "ut":
                    ovladac.ulozTextSoubor(TEXTOVY__SOUBOR);
                    break;
                case "generuj", "g":

                    System.out.println("Zadejde počet generování");
                    int pocetGeneraci = scan.nextInt();
                    ovladac.generuj(pocetGeneraci);
                    System.out.println("Vygenerováno");

                    break;
                case "zrus":
                    ovladac.zrus();
                    break;

                case "exit":
                    isActive = false;
                    break;
                default:
                    System.out.println("Neznámý příkaz");
                    break;
            }
        } while (isActive == true);
    }

    private static DopravniProstredek novy() {
        DopravniProstredek novy = null;
        System.out.println("Vytvoření nového prvku");
        System.out.println("""
                           Zvolte typ prostředku
                           1 Dopravní letadlo 
                           2 Vrtulník
                           3 Stíhací letoun
                           """);
        boolean validTyp = false;
        int typ;
        do {
            typ = scan.nextInt();
            if (typ == 1 || typ == 2 || typ == 3) {
                validTyp = true;
            }
        } while (validTyp == false);

        System.out.println("Zadejte cenu");
        boolean validCena = false;
        int cena = 0;
        do {
            try {
                cena = scan.nextInt();
                validCena = true;
            } catch (Exception e) {
                System.out.println("Špatně zadaná cena");
            }
        } while (validCena == false);

        System.out.println("Zadejte hmotnost");
        boolean validHmotnost = false;
        int hmotnost = 0;
        do {
            try {
                hmotnost = scan.nextInt();
                validHmotnost = true;
            } catch (Exception e) {
                System.out.println("Špatně zadaná hmotnost");
            }
        } while (validHmotnost == false);
        scan.nextLine();
        System.out.println("Zadejte výrobce");
        String vyrobce = scan.nextLine();

        System.out.println("Zadejte id");
        String id = scan.nextLine();

        System.out.println("Zadejte název");
        String nazev = scan.nextLine();
        switch (typ) {
            case 1:
                System.out.println("Zadejte počet sedadel letadla");
                boolean validSedadla = false;
                int sedadla = 0;
                do {
                    try {
                        sedadla = scan.nextInt();
                        validSedadla = true;
                    } catch (Exception e) {
                        System.out.println("Špatně zadaný počet sedadel");
                    }
                } while (validSedadla == false);

                novy = new DopravniLetadlo(sedadla, cena, hmotnost, vyrobce,
                        id, nazev);

                break;
            case 2:
                System.out.println("Zadejte počet vrtulí vrtulníku");

                boolean validVrtule = false;
                int vrtule = 0;
                do {
                    try {
                        vrtule = scan.nextInt();
                        validVrtule = true;
                    } catch (Exception e) {
                        System.out.println("Špatně zadaný počet vrtulí");
                    }
                } while (validVrtule == false);

                novy = new Vrtulnik(vrtule, cena, hmotnost, vyrobce, id, nazev);

                break;
            default:
                System.out.println("Zadejte počet zbraní");

                boolean validZbrane = false;
                int zbrane = 0;
                do {
                    try {
                        zbrane = scan.nextInt();
                        validZbrane = true;
                    } catch (Exception e) {
                        System.out.println("Špatně zadaný počet zbraní");
                    }
                } while (validZbrane == false);

                novy = new Vrtulnik(zbrane, cena, hmotnost, vyrobce, id, nazev);
                break;
        }
        return novy;

    }

}
