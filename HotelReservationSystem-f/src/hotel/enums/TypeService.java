package hotel.enums;

public enum TypeService {
    PETIT_DEJ(10),
    ROOM_SERVICE(20),
    SPA(60),
    PARKING(5);

    private final double prix;

    TypeService(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }
}
