package test;

import model.LampaModell;
import nezet.GuiLampaNezet;
import vezerlo.LampaVezerlo;

public class LampaVezerloTest {

    public static void main(String[] args) {
        new LampaVezerloTest().futtat();
    }

    void futtat() {
        tesztLampaKattintas();
        tesztUjJatek();
        tesztNezetFrissit();
        System.out.println("Minden teszt lefutott!");
    }

    void tesztLampaKattintas() {
        System.out.println("teszteset: Lámpa kattintása módosítja az állapotot");
        LampaModell model = new LampaModell();
        GuiLampaNezet nezet = new GuiLampaNezet();
        LampaVezerlo vezerlo = new LampaVezerlo(nezet, model);

        boolean[] elotte = model.getAllapotok().clone();
        vezerlo.lampatValtTeszt(4);
        boolean[] utana = model.getAllapotok();

        boolean valtozott = false;
        for (int i = 0; i < elotte.length; i++) {
            if (elotte[i] != utana[i]) valtozott = true;
        }
        assert valtozott : "A modell állapota nem változott a kattintás után!";
        System.out.println("Teszteset sikeresen lefutott: Lámpa kattintás");
    }

    void tesztUjJatek() {
        System.out.println("teszteset: Új játék új véletlen állapotot hoz létre");
        LampaModell model = new LampaModell();
        GuiLampaNezet nezet = new GuiLampaNezet();
        LampaVezerlo vezerlo = new LampaVezerlo(nezet, model);

        boolean[] elotte = model.getAllapotok().clone();
        vezerlo.ujJatek();
        boolean[] utana = model.getAllapotok();

        boolean mas = false;
        for (int i = 0; i < elotte.length; i++) {
            if (elotte[i] != utana[i]) mas = true;
        }
        assert mas : "Az új játék nem hozott létre új állapotot.";
        System.out.println("Teszteset sikeresen lefutott: Új játék");
    }

    void tesztNezetFrissit() {
        System.out.println("teszteset: Nézet frissítés nem dob hibát");
        LampaModell model = new LampaModell();
        GuiLampaNezet nezet = new GuiLampaNezet();
        try {
            nezet.frissit(model.getAllapotok());
        } catch (Exception e) {
            assert false : "A nézet frissítése hibát dobott.";
        }
        System.out.println("Teszteset sikeresen lefutott: Nézet frissítés");
    }
}
