package model;

public class LampaModell {
    private boolean[] lampak = new boolean[9]; // true = kék (lekapcsolt), false = sárga (felkapcsolt)

    public LampaModell() {
        ujJatek();
    }

    public void ujJatek() {
        for (int i = 0; i < 9; i++) {
            lampak[i] = false; 
        }
    }

    public boolean[] getAllapotok() {
        return lampak.clone();
    }

    public void setAllapotok(boolean[] allapot) {
        if (allapot.length == 9) {
            this.lampak = allapot.clone();
        }
    }

    public void lampatValt(int index) {
        int[] szomszedok = getSzomszedok(index);
        lampak[index] = !lampak[index]; 
        for (int sz : szomszedok) {
            lampak[sz] = !lampak[sz]; 
        }
    }

    private int[] getSzomszedok(int index) {
        switch (index) {
            case 0: return new int[]{1,3};
            case 1: return new int[]{0,2,4};
            case 2: return new int[]{1,5};
            case 3: return new int[]{0,4,6};
            case 4: return new int[]{1,3,5,7};
            case 5: return new int[]{2,4,8};
            case 6: return new int[]{3,7};
            case 7: return new int[]{4,6,8};
            case 8: return new int[]{5,7};
            default: return new int[]{};
        }
    }
}
