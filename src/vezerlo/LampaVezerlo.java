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

    public LampaVezerlo(GuiLampaNezet view, LampaModell modell) {
        this.view = view;
        this.model = modell;
                view.addKilepesListener(e -> kilepes_Megerosit());


       // this.view.addUjJatekListener(e -> ujJatek());
        
        this.view.addFajlbaMentListener(e -> fajlbaMent());
        this.view.addBetoltFajlbolListener(e -> fajlbolBetolt());
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
private void frissitView() {
    boolean[] allapotok = model.getAllapotok();
    for (int i = 0; i < allapotok.length; i++) {
        view.setLampColor(i, allapotok[i] ? Color.BLUE : Color.YELLOW);
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
