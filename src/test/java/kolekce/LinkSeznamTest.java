package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author karel@simerda.cz
 */
public class LinkSeznamTest {

//<editor-fold defaultstate="collapsed" desc="Testovací třída a objekty">    
    private static class TestClass {

        int a;

        public TestClass(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "T" + a;
        }

    }

    private final TestClass T1 = new TestClass(1);
    private final TestClass T2 = new TestClass(2);
    private final TestClass T3 = new TestClass(3);
    private final TestClass T4 = new TestClass(4);
    private final TestClass T5 = new TestClass(5);
    private final TestClass T6 = new TestClass(6);
    private final TestClass T7 = new TestClass(7);
    private final TestClass T8 = new TestClass(8);
    private final TestClass T9 = new TestClass(9);
//</editor-fold>

    public LinkSeznamTest() {
    }

    //<editor-fold defaultstate="collapsed" desc="01 Vkládání prvního prvku">
    @Test
    public void test_01_01_VlozPrvni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            TestClass[] result = {instance.dejPrvni(), instance.dejPosledni()};
            TestClass[] expected = {T1, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="02 Vkládání posledního prvku">
    @Test
    public void test_02_01_VlozNaKonec() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPosledni(T1);
            TestClass[] result = {instance.dejPrvni(), instance.dejPosledni()};
            TestClass[] expected = {T1, T1};
            assertArrayEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="03 Kontrola nastavení prvního prvku">
    @Test
    public void test_03_01_NastavPrvni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            instance.nastavPrvni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T1;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="04 Kontrola nastavení posledního prvku">
    @Test
    public void test_04_01_NastavPosledni() {
        try {
            LinkSeznam<TestClass> instance = new LinkSeznam<>();
            instance.vlozPrvni(T1);
            instance.nastavPosledni();
            TestClass result = instance.dejAktualni();
            TestClass expected = T1;
            assertEquals(expected, result);
        } catch (KolekceException ex) {
            fail();
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="05 Testy metod dej">
    @Test
    public void test_05_01_DejPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        assertEquals(T1, instance.dejPrvni());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="06 Testy metody odeberPrvni">
    @Test
    public void test_06_01_OdeberPrvniJedenZJednoho() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        assertEquals(T1, instance.odeberPrvni());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="07 Testy metody odeberPosledni">
    @Test
    public void test_07_01_OdeberPosledniJedenZJednoho() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        assertEquals(T1, instance.odeberPosledni());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="08 Procházení seznamu metodou dalsi">
    @Test
    public void test_08_04_JeDalsi() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPrvni(T1);
        instance.vlozPrvni(T2);
        instance.nastavPrvni();
        assertTrue(instance.jeDalsi());
    }

//</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="09 Testy metod vlozZaAktualni">
    /**
     * Ukazatel na aktuální prvek se metodou vlož neposouvá.
     *
     * @throws KolekceException
     */
   

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="10 Testy metod dejAktualni"> 
    @Test
    public void test_10_01_DejAktualniPosledni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T2);       
        instance.nastavPosledni();
        assertEquals(T2, instance.dejAktualni());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="11 Testy metod dejZaAkualnim">
    @Test
    public void test_11_01_DejZaAktualnim() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T2);
        instance.vlozPosledni(T1);
        instance.nastavPrvni();
        assertEquals(T1, instance.dejZaAktualnim());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="12 Testy metod odeberAktualni">
    @Test
    public void test_12_01_OdeberAktualniPrvni() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T2);
        instance.nastavPrvni();
        TestClass result = instance.odeberAktualni();
        assertEquals(T2, result);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="13 Testy metody odeberZaAktualnim">
    @Test
    public void test_13_01_OdeberZaAktualnim() throws KolekceException {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T2);
        instance.vlozPosledni(T1);
        instance.vlozPosledni(T3);
        instance.nastavPrvni();
        TestClass result = instance.odeberZaAktualnim();
        assertEquals(T1, result);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="14 Testy iterátoru">
    @Test
    public void test_14_01_Iterator() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        instance.vlozPosledni(T2);
        Iterator<TestClass> iterator = instance.iterator();
        assertEquals(T2, iterator.next());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="15 Testy na velikost seznamu">
    @Test
    public void test_15_01_JePrazdny() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        assertTrue(instance.jePrazdny());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="16 Výkonnostní testy">
    @Test(timeout = 200L)
    public void test_Size10() {
        LinkSeznam<TestClass> instance = new LinkSeznam<>();
        for (int i = 0; i < 1000000; i++) {
            instance.vlozPosledni(T1);
        }
        int p = instance.size();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Privátní pomocné metody">
    private TestClass[] dejPole(LinkSeznam<TestClass> instance) {
        int pocet = instance.size();
        TestClass[] pole = new TestClass[pocet];
        Iterator<TestClass> iterator = instance.iterator();
        for (int i = 0; i < pocet; i++) {
            pole[i] = iterator.next();
        }
        return pole;
    }
    //</editor-fold>
}
