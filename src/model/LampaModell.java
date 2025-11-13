package model;

import java.util.Arrays;

public class LampaModell {
    private boolean[] lampak;//true kék lekpcsolva ,sarga false fel kapcsolva

    public LampaModell() {
        lampak = new boolean[9];
        Arrays.fill(lampak, true); 
    }

    public boolean isAllapot(int index) {
        return lampak[index];
    }

    public void toggleAllapot(int index) {
        lampak[index] = !lampak[index];
    }

    public void setAllapot(int index, boolean allapot) {
        lampak[index] = allapot;
    }

    public boolean[] getAllapotok() {
        return lampak;
    }

    public void setAllapotok(boolean[] ujAllapotok) {
        if (ujAllapotok.length == 9) {
            System.arraycopy(ujAllapotok, 0, lampak, 0, 9);
        }
    }
}
