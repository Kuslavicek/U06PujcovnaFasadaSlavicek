/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kolekce;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author HP
 */
public class LinkSeznam<E> implements SpojovySeznam<E>, Serializable {

    private int počet = 0;
    private Prvek<E> aktualniPrvek;
    private Prvek<E> prvniPrvek;
    private Prvek<E> posledniPrvek;

    public void upravAktualni(E data) {
        this.aktualniPrvek.setData(data);
    }

    public Prvek prvniPrvek() {
        return prvniPrvek;
    }

    @Override
    public void nastavPrvni() throws KolekceException {
        if (počet == 0) {
            throw new KolekceException();
        }
        this.aktualniPrvek = this.prvniPrvek;
    }

    @Override
    public void nastavPosledni() throws KolekceException {
        if (počet == 0) {
            throw new KolekceException();
        }
        this.aktualniPrvek = this.posledniPrvek;
    }

    @Override
    public void dalsi() throws KolekceException {
        if (jeDalsi() == false) {
            throw new KolekceException();
        }else{
            this.aktualniPrvek = this.aktualniPrvek.dalsiPrvek();
        }
        
    }

    @Override
    public boolean jeDalsi() {
        if(aktualniPrvek==null){
            return false;
        }else{
            return aktualniPrvek.dalsiPrvek() != null;    
        }
    }
    

    @Override
    public void vlozPrvni(E data) {
        if (data == null) {
            throw new NullPointerException();
        }else{
           if (počet != 0) {
                Prvek druhyPrvek = this.prvniPrvek;
                Prvek novyPrvniPrvek = new Prvek(data);
                this.prvniPrvek = novyPrvniPrvek;
                prvniPrvek.setDalsiPrvek(druhyPrvek);
            } else {
                this.prvniPrvek = new Prvek(data);
                this.posledniPrvek = this.prvniPrvek;
            }
            počet++; 
        }
    }

    @Override
    public void vlozPosledni(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (počet != 0) {
            this.posledniPrvek.setDalsi(data);
            this.posledniPrvek = this.posledniPrvek.dalsiPrvek();
        } else {
            this.prvniPrvek = new Prvek(data);
            this.posledniPrvek = this.prvniPrvek;
        }
        počet++;
    }

    @Override
    public void vlozZaAktualni(E data) throws KolekceException {
        if (data == null) {
            throw new NullPointerException();
        }
        if (this.aktualniPrvek == null) {
            throw new KolekceException();
        }
        if(this.aktualniPrvek == this.posledniPrvek){
            vlozPosledni(data);
        }else{
            Prvek zaAktualnim = this.aktualniPrvek.dalsiPrvek();
            Prvek naVlozeni = new Prvek(data);
            this.aktualniPrvek.setDalsiPrvek(naVlozeni);
            naVlozeni.setDalsiPrvek(zaAktualnim);
            počet++;
        }
        
    }

    @Override
    public boolean jePrazdny() {
        return (počet == 0);
    }

    @Override
    public E dejPrvni() throws KolekceException {
        kontrolaPrazdnoty();
        return prvniPrvek.getData();
    }

    @Override
    public E dejPosledni() throws KolekceException {
        kontrolaPrazdnoty();
        return this.posledniPrvek.getData();
    }

    @Override
    public E dejAktualni() throws KolekceException {
        kontrolaAktualnihoAPrazdnoty();
        return this.aktualniPrvek.getData();
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        kontrolaAktualnihoAPrazdnoty();
        if(jeDalsi()){
        return this.aktualniPrvek.dalsiPrvek().getData();
        }else{
            throw new KolekceException();
        }
    }

    @Override
    public E odeberPrvni() throws KolekceException {
        kontrolaPrazdnoty();
        E data = this.prvniPrvek.getData();
        if (this.aktualniPrvek == this.prvniPrvek) {
            this.aktualniPrvek = null;
        }
        if (this.posledniPrvek == this.prvniPrvek) {
            this.posledniPrvek = null;
            this.prvniPrvek=null;
            this.aktualniPrvek=null;
        }else{
            this.prvniPrvek = this.prvniPrvek.dalsiPrvek();
        }
        počet--;
        return data;
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        kontrolaPrazdnoty();
        E data = this.posledniPrvek.getData();
        if (this.aktualniPrvek == this.posledniPrvek) {
            this.aktualniPrvek = null;
        }
        if (this.prvniPrvek == this.posledniPrvek) {
            this.prvniPrvek = null;
            this.aktualniPrvek = null;
            this.posledniPrvek = null;
        }else{
            this.posledniPrvek = predposledniPrvek();
            this.posledniPrvek.setDalsiPrvek(null);
        }
        počet--;
        return data;
    }

    @Override
    public E odeberAktualni() throws KolekceException {
        kontrolaAktualnihoAPrazdnoty();
        E data;
        if (this.aktualniPrvek == this.prvniPrvek) {
            data = odeberPrvni();
        } else if (this.aktualniPrvek == this.posledniPrvek) {
            data = odeberPosledni();

        } else {
            data = this.aktualniPrvek.getData();
            Prvek pred = predAktualnim();
            Prvek za = this.aktualniPrvek.dalsiPrvek();
            pred.setDalsiPrvek(za);
            this.aktualniPrvek = null;
            počet--;
        }

        return data;
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        kontrolaAktualnihoAPrazdnoty();
        if(this.aktualniPrvek.dalsiPrvek()==null){
            throw new KolekceException();
        }else if(this.aktualniPrvek.dalsiPrvek()==this.posledniPrvek){
            return odeberPosledni();
        }else{
            E data = this.aktualniPrvek.dalsiPrvek().getData();
            this.aktualniPrvek.setDalsiPrvek(this.aktualniPrvek.dalsiPrvek().dalsiPrvek());
            počet--;
            return data;
        }
        
    }

    @Override
    public int size() {
        return počet;
    }

    @Override
    public void zrus() {
        this.prvniPrvek = null;
        this.počet = 0;
        this.aktualniPrvek = null;
        this.posledniPrvek = null;
    }

    @Override
    public Iterator<E> iterator() {

        Iterator<E> iterator = new Iterator<E>() {
            private Prvek<E> p = prvniPrvek;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E data = p.getData();
                    p = p.dalsiPrvek();
                    return data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
        return iterator;
    }

    private void kontrolaAktualnihoAPrazdnoty() throws KolekceException {
        if (this.aktualniPrvek == null || jePrazdny()) {
            throw new KolekceException();
        }
    }

    private void kontrolaPrazdnoty() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
    }

    public Prvek<E> predAktualnim() {

        Prvek<E> p = this.prvniPrvek;
        while (p.dalsiPrvek() != this.aktualniPrvek) {
            p = p.dalsiPrvek();
        }
        return p;
    }

    public void nastavAktualni(Prvek p) {
        this.aktualniPrvek = p;
    }

    private Prvek<E> predposledniPrvek() {
        Prvek prvni = this.prvniPrvek;
        while(prvni.dalsi!=this.posledniPrvek){
            prvni = prvni.dalsi;
        }
        return prvni;
    }

    public class Prvek<E> implements Serializable {

        private Prvek<E> dalsi;
        private E data;

        public Prvek(E data) {
            this.dalsi = null;
            this.data = data;
        }

        public Prvek<E> dalsiPrvek() {
            return dalsi;
        }

        public void setDalsi(E data) {
            this.dalsi = new Prvek(data);
        }

        public void setDalsiPrvek(Prvek dalsi) {
            this.dalsi = dalsi;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

    }
}
