package main;

import vezerlo.LampaVezerlo;
import nezet.GuiLampaNezet;
import model.LampaModell;

public class LightOn {
    public static void main(String[] args) {
        LampaModell modell = new LampaModell();
        GuiLampaNezet nezet = new GuiLampaNezet();
        new LampaVezerlo(nezet, modell);
        nezet.setVisible(true);
    }
}
