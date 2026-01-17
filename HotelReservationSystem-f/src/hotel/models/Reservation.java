package hotel.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import hotel.enums.EtatReservation;

public class Reservation {

    private Client client;              // who booked
    private Chambre chambre;            // which room
    private LocalDate dateDebut;        // start date
    private LocalDate dateFin;          // end date
    private List<Service> services;     // extra services added
    private EtatReservation etat; // instead of isActive
    // if reservation is currently active

    // Constructor
    public Reservation(Client client, Chambre chambre, LocalDate dateDebut, LocalDate dateFin) {
        this.client = client;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.services = new ArrayList<>();
        this.etat = EtatReservation.CONFIRMEE;

    }


    public Client getClient() { return client; }
    public Chambre getChambre() { return chambre; }
    public LocalDate getDateDebut() { return dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public List<Service> getServices() { return services; }
    public EtatReservation getEtat() { return etat; }


    public void setChambre(Chambre chambre) { this.chambre = chambre; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public void setEtat(EtatReservation etat) { this.etat = etat; }



    public void ajouterService(Service s) {
        services.add(s);
    }


    public int getNbNuits() {
        return Period.between(dateDebut, dateFin).getDays();
    }
    public boolean estConflictuelle(LocalDate debut, LocalDate fin) {

        return !dateFin.isBefore(debut) && !dateDebut.isAfter(fin);
    }


    @Override
    public String toString() {
        return "Réservation: " + client.getNom() + " " + client.getPrenom() +
                " | Chambre: " + chambre.getNumero() +
                " | Du: " + dateDebut + " Au: " + dateFin +
                " | Services: " + services.size()+
                " | Etat: " + etat;

    }
}
