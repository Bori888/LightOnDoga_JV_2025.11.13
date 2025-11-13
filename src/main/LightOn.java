package main;

import model.LampaModell;
import nezet.GuiLampaNezet;
import vezerlo.LampaVezerlo;

public class LightOn {
    public static void main(String[] args) {
        GuiLampaNezet nezet = new GuiLampaNezet();
        LampaModell modell = new LampaModell();
        new LampaVezerlo(nezet, modell);
        nezet.setVisible(true);
    }
}
