package hotel;

import hotel.models.*;
import hotel.enums.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // Create the hotel
        Hotel myHotel = new Hotel("Mon Super Hotel");

        //  Add rooms
        myHotel.ajouterChambre(new Chambre(101, 1, TypeChambre.SIMPLE, EtatChambre.DISPONIBLE));
        myHotel.ajouterChambre(new Chambre(102, 1, TypeChambre.DOUBLE, EtatChambre.DISPONIBLE));
        myHotel.ajouterChambre(new Chambre(201, 2, TypeChambre.SUITE, EtatChambre.DISPONIBLE));

        // Add clients
        Client client1 = new Client("John", "Doe", "AB123", "0612345678", "john@mail.com");
        Client client2 = new Client("Jane", "Smith", "CD456", "0698765432", "jane@mail.com");

        myHotel.ajouterClient(client1);
        myHotel.ajouterClient(client2);

        // Create reservations
        Reservation res1 = new Reservation(
                client1,
                myHotel.getChambres().get(0),
                LocalDate.of(2026, 1, 5),
                LocalDate.of(2026, 1, 7)
        );
        res1.ajouterService(new Service(TypeService.PETIT_DEJ));
        res1.ajouterService(new Service(TypeService.SPA));

        Reservation res2 = new Reservation(
                client2,
                myHotel.getChambres().get(1),
                LocalDate.of(2026, 1, 6),
                LocalDate.of(2026, 1, 8)
        );
        res2.ajouterService(new Service(TypeService.ROOM_SERVICE));

        //  Add reservations
        myHotel.creerReservation(res1);
        myHotel.creerReservation(res2);

        //  Available rooms
        System.out.println("Chambres disponibles du 2026-01-05 au 2026-01-07:");
        for (Chambre c : myHotel.chambresDisponibles(
                LocalDate.of(2026, 1, 5),
                LocalDate.of(2026, 1, 7),
                1
        )) {
            System.out.println(c);
        }

        //  Check-in
        myHotel.checkIn(res1);

        //  Check-out + facture
        Facture facture1 = myHotel.checkOut(res1);
        System.out.println("\nFacture pour " + res1.getClient().getNom() + ":");
        System.out.println(facture1);



    }

}
