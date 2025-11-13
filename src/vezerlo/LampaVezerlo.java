package vezerlo;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JOptionPane;
import model.LampaModell;
import nezet.GuiLampaNezet;

public class LampaVezerlo {

    private GuiLampaNezet view;
    private LampaModell model;

    public LampaVezerlo(GuiLampaNezet view, LampaModell model) {
        this.view = view;
        this.model = model;

        view.addKilepesListener(e -> kilepes_Megerosit());
        view.addUjJatekListener(e -> ujJatek());
        view.addFajlbaMentListener(e -> fajlbaMent());
        view.addBetoltFajlbolListener(e -> fajlbolBetolt());

        for (int i = 0; i < 9; i++) {
            final int index = i;
            view.addLampButtonListener(i, e -> lampatValt(index));
        }

        frissitView();
    }

    private void kilepes_Megerosit() {
        int valasz = JOptionPane.showConfirmDialog(
                view,
                "Biztos ki szeretnél lépni?",
                "Megerősítés",
                JOptionPane.YES_NO_OPTION
        );
        if (valasz == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void ujJatek() {
        model.ujJatek();
        frissitView();
        view.setMessage("Új játék indítva!");
    }

    private void lampatValt(int index) {
        model.lampatValt(index);
        frissitView();
        if (jatekVege()) {
            view.setMessage("Gratulálok! Minden lámpa lekapcsolva!");
        }
    }

    private boolean jatekVege() {
        for (boolean allapot : model.getAllapotok()) {
            if (!allapot) return false;
        }
        return true;
    }

    private void frissitView() {
        boolean[] allapotok = model.getAllapotok();
        for (int i = 0; i < allapotok.length; i++) {
            view.setLampColor(i, allapotok[i] ? Color.BLUE : Color.YELLOW);
        }
    }

    private void fajlbaMent() {
        String tartalom = "";
        for (boolean allapot : model.getAllapotok()) {
            tartalom += allapot ? "1" : "0";
        }
        try {
            Files.writeString(Path.of("lampaAllas.txt"), tartalom);
            view.setMessage("Játék mentve!");
        } catch (IOException e) {
            view.setMessage("Mentés sikertelen!");
        }
    }

    private void fajlbolBetolt() {
        try {
            String sor = Files.readString(Path.of("lampaAllas.txt")).trim();
            if (sor.length() == 9) {
                boolean[] ujAllapot = new boolean[9];
                for (int i = 0; i < 9; i++) {
                    ujAllapot[i] = sor.charAt(i) == '1';
                }
                model.setAllapotok(ujAllapot);
                frissitView();
                view.setMessage("Játék betöltve!");
            } else {
                view.setMessage("Hibás fájlformátum!");
            }
        } catch (IOException e) {
            view.setMessage("Betöltés sikertelen!");
        }
    }
}
