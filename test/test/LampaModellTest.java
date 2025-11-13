package test;

import model.LampaModell;
import java.util.Arrays;

public class LampaModellTest {
    public static void main(String[] args) {
        new LampaModellTest().futtat();
    }

    void futtat() {
        testUjJatekVeletlenAllapot();
        testLampatValtSajatValtas();
        testLampatValtSzomszedValtas();
        testSetGetAllapotok();
        testHataronKivuliIndex();
    }

    void testUjJatekVeletlenAllapot() {
        System.out.println("teszt: ujJatek() véletlenszerűen generál állapotokat");
        LampaModell m1 = new LampaModell();
        boolean[] a1 = m1.getAllapotok();
        m1.ujJatek();
        boolean[] a2 = m1.getAllapotok();
        assert !Arrays.equals(a1, a2) : "ujJatek nem változtatott az állapotokon!";
    }

    void testLampatValtSajatValtas() {
        System.out.println("teszt: lampatValt() megfordítja a saját állapotát");
        LampaModell m = new LampaModell();
        boolean[] allap = new boolean[9];
        m.setAllapotok(allap);
        m.lampatValt(4);
        assert m.getAllapotok()[4] : "Saját lámpa nem váltott!";
    }

    void testLampatValtSzomszedValtas() {
        System.out.println("teszt: lampatValt() megfordítja a szomszédokat is");
        LampaModell m = new LampaModell();
        boolean[] allap = new boolean[9];
        m.setAllapotok(allap);
        m.lampatValt(0);
        boolean[] v = m.getAllapotok();
        assert v[0] && v[1] && v[3] : "Szomszédok nem váltottak megfelelően!";
    }

    void testSetGetAllapotok() {
        System.out.println("teszt: setAllapotok() és getAllapotok() megfelelően működik");
        LampaModell m = new LampaModell();
        boolean[] beallit = {true,false,true,false,true,false,true,false,true};
        m.setAllapotok(beallit);
        boolean[] kapott = m.getAllapotok();
        assert Arrays.equals(beallit, kapott) : "Allapotok nem megfelelően lettek beállítva!";
    }

    void testHataronKivuliIndex() {
        System.out.println("teszt: getSzomszedok() hibás index esetén üres tömböt ad");
        LampaModell m = new LampaModell();
        boolean hiba = false;
        try {
            m.lampatValt(10);
        } catch (Exception e) {
            hiba = true;
        }
        assert !hiba : "Nem kellett volna kivételt dobni!";
    }
}
