package hotel.enums;

public enum TypeChambre {

    SIMPLE(400, 1),
    DOUBLE(600, 2),
    SUITE(1200, 4);

    private final double prixParNuit;
    private final int capacite;

    TypeChambre(double prixParNuit, int capacite) {
        this.prixParNuit = prixParNuit;
        this.capacite = capacite;
    }

    public double getPrix() {
        return prixParNuit;
    }

    public int getCapacite() {
        return capacite;
    }
}
