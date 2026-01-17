package hotel.models;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private String nom;
    private String prenom;
    private String CIN;
    private String telephone;
    private String email;

    private List<Reservation> historique;

    public Client(String nom, String prenom, String CIN, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.telephone = telephone;
        this.email = email;
        this.historique = new ArrayList<>();
    }


    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getCIN() { return CIN; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public List<Reservation> getHistorique() { return historique; }


    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setEmail(String email) { this.email = email; }



    public void ajouterReservation(Reservation r) {
        historique.add(r);
    }


    @Override
    public String toString() {
        return prenom + " " + nom + " | CIN: " + CIN +
                " | Tel: " + telephone + " | Email: " + email +
                " | Réservations: " + historique.size();
    }
}
