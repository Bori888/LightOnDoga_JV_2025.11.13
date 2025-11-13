package test;

import javax.swing.SwingUtilities;
import model.LampaModell;
import nezet.GuiLampaNezet;
import vezerlo.LampaVezerlo;

public class GuiLampaNezetTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuiLampaNezet nezet = new GuiLampaNezet();
                LampaModell modell = new LampaModell();
                LampaVezerlo vezerlo = new LampaVezerlo(nezet, modell);
                
                nezet.setVisible(true);
                
                System.out.println("GUI teszt elindult: Ablak látható.");
                
                try {
                    Thread.sleep(1000);
                    System.out.println("Új játék indítása...");
                    nezet.triggerUjJatek();
                    Thread.sleep(1000);
                    System.out.println("Első lámpa kattintása...");
                    nezet.triggerLampClick(0);
                    Thread.sleep(1000);
                    System.out.println("Teszt vége. GUI működőképes.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
