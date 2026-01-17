package hotel;

import hotel.models.Chambre;
import hotel.models.Client;
import hotel.models.Reservation;
import hotel.models.Facture;

import hotel.enums.TypeChambre;
import hotel.enums.EtatChambre;

import hotel.enums.EtatReservation;
import hotel.observer.ChambreObserver;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String nom;
    private List<Chambre> chambres;
    private List<Client> clients;
    private List<Reservation> reservations;
    private List<Reservation> listeAttente;

    public Hotel(String nom) {
        this.nom = nom;
        this.chambres = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.listeAttente = new ArrayList<>();

        ChambreObserver observer = new ChambreObserver();



        chambres.add(new Chambre(101, 1, TypeChambre.SIMPLE, EtatChambre.DISPONIBLE));
        chambres.add(new Chambre(102, 1, TypeChambre.DOUBLE, EtatChambre.DISPONIBLE));
        chambres.add(new Chambre(201, 2, TypeChambre.SUITE, EtatChambre.DISPONIBLE));
        chambres.add(new Chambre(13, 1, TypeChambre.SIMPLE, EtatChambre.DISPONIBLE));
        chambres.add(new Chambre(12, 3, TypeChambre.DOUBLE, EtatChambre.DISPONIBLE));
        chambres.add(new Chambre(21, 2, TypeChambre.SUITE, EtatChambre.DISPONIBLE));
        for (Chambre c : chambres) {
            c.addObserver(observer);
        }

    }


    public void ajouterChambre(Chambre c) { chambres.add(c); }
    public void ajouterClient(Client c) { clients.add(c); }


    public void creerReservation(Reservation r) {
        boolean conflit = false;
        for (Reservation existing : reservations) {
            if (existing.getChambre().getNumero() == r.getChambre().getNumero() &&
                    (existing.getEtat() == EtatReservation.CONFIRMEE || existing.getEtat() == EtatReservation.EN_COURS) &&
                    existing.estConflictuelle(r.getDateDebut(), r.getDateFin())) {
                conflit = true;
                break;
            }
        }

        if (!conflit && r.getChambre().getEtat() == EtatChambre.DISPONIBLE) {

            r.getChambre().setEtat(EtatChambre.RESERVEE);
            r.setEtat(EtatReservation.CONFIRMEE);
            reservations.add(r);
            r.getClient().ajouterReservation(r);
        } else {

            listeAttente.add(r); // ⚡ important : being in listeAttente = waiting
            reservations.add(r); // still track the reservation
            r.getClient().ajouterReservation(r);
            System.out.println("Réservation en attente, chambre occupée !");
        }
    }


    public Client chercherClientParCIN(String cin) {
        for (Client c : clients) if (c.getCIN().equals(cin)) return c;
        return null;
    }

    public Chambre chercherChambreParNumero(int numero) {
        for (Chambre c : chambres) if (c.getNumero() == numero) return c;
        return null;
    }

    public Reservation chercherReservationParNumeroChambre(int numero) {
        for (Reservation r : reservations) if (r.getChambre().getNumero() == numero) return r;
        return null;
    }


    public void checkIn(Reservation r) {
        if (r.getEtat() == EtatReservation.CONFIRMEE) { // check the enum, not isActive
            r.setEtat(EtatReservation.EN_COURS);       // mark as checked-in
            r.getChambre().setEtat(EtatChambre.OCCUPEE);
        } else {
            System.out.println("Check-in impossible: réservation pas confirmée");
        }
    }
    //  Check-out + facture
    public Facture checkOut(Reservation r) {

        if (r.getEtat() != EtatReservation.EN_COURS) {
            System.out.println("Impossible de check-out, réservation pas en cours.");
            return null;
        }


        r.setEtat(EtatReservation.TERMINEE);
        r.getChambre().setEtat(EtatChambre.DISPONIBLE);


        Facture f = new Facture(r);


        traiterListeAttente(r.getChambre());

        return f;
    }







    public List<Chambre> chambresDisponibles(LocalDate start, LocalDate end, int capaciteMin) {
        List<Chambre> dispo = new ArrayList<>();
        for (Chambre c : chambres) {
            if (c.getCapacite() >= capaciteMin && c.getEtat() == EtatChambre.DISPONIBLE) {
                boolean conflict = false;
                for (Reservation r : reservations) {
                    if (r.getChambre().getNumero() == c.getNumero() &&
                            r.estConflictuelle(start, end)) {
                        conflict = true;
                        break;
                    }
                }
                if (!conflict) dispo.add(c);
            }
        }
        return dispo;
    }


    public List<Chambre> getChambres() { return chambres; }

    public void annulerReservation(Reservation r) {
        reservations.remove(r);
        r.getChambre().setEtat(EtatChambre.DISPONIBLE);


        traiterListeAttente(r.getChambre());
    }


    public void traiterListeAttente(Chambre chambre) {
        for (Reservation r : new ArrayList<>(listeAttente)) {
            if (r.getChambre().getNumero() == chambre.getNumero()) {
                r.setEtat(EtatReservation.CONFIRMEE);
                r.getChambre().setEtat(EtatChambre.RESERVEE);
                listeAttente.remove(r);
                break;
            }
        }
    }



    public List<Reservation> getListeAttente() { return listeAttente; }
}
