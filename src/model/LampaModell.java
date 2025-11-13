package model;

public class LampaModell {
    private int sorszama;
    private boolean allapot; // fel = true, le = false

    public LampaModell(int sorszama, boolean allapot) {
        this.sorszama = sorszama;
        this.allapot = allapot;
    }

    public int getSorszama() {
        return sorszama;
    }

    public boolean isAllapot() {
        return allapot;
    }

    public void setAllapot(boolean allapot) {
        this.allapot = allapot;
    }

    public void toggleAllapot() {
        this.allapot = !this.allapot;
    }
}
