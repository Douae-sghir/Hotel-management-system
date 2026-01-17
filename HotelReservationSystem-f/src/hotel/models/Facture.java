package hotel.models;

import java.time.LocalDate;

public class Facture {

    private Reservation reservation;
    private double total;
    private boolean payee;
    private LocalDate dateFacture;

    private static final double TAUX_TAXE = 0.10; // 10%

    public Facture(Reservation reservation) {
        this.reservation = reservation;
        this.dateFacture = LocalDate.now();
        this.payee = false;
        calculerTotal();
    }

    private void calculerTotal() {
        double montant = 0;


        int nbNuits = reservation.getNbNuits();
        montant += nbNuits * reservation.getChambre().getPrixParNuit();

        // Services supplémentaires
        for (Service s : reservation.getServices()) {
            montant += s.getPrix();
        }


        montant += montant * TAUX_TAXE;

        this.total = montant;
    }
    public void setPayee(boolean payee) {
        this.payee = payee;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "total=" + total + "€, " +
                "payee=" + payee +
                ", date=" + dateFacture +
                ", reservation=" + reservation +
                '}';
    }
}
