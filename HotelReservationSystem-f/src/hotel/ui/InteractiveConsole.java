package hotel.ui;

import hotel.models.Chambre;
import hotel.models.Client;
import hotel.models.Reservation;
import hotel.models.Facture;
import hotel.models.Service;


import hotel.enums.TypeService;

import hotel.Hotel;

import java.time.LocalDate;

import java.util.Scanner;

public class InteractiveConsole {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel myHotel = new Hotel("Mon Super Hotel");

        boolean running = true;

        while (running) {
            System.out.println("\n=== HOTEL MENU ===");
            System.out.println("1. Ajouter client");
            System.out.println("2. Créer réservation");
            System.out.println("3. Vérifier chambres disponibles");
            System.out.println("4. Check-in");
            System.out.println("5. Check-out et facture");
            System.out.println("6. Voir la liste d'attente");
            System.out.println("7. Annuler une réservation");
            System.out.println("8. Quitter");
            System.out.print("Choisissez une option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: // Ajouter client avec validation
                    String nom, prenom, cin, tel, email;

                    // Nom et prénom
                    while (true) {
                        System.out.print("Nom: ");
                        nom = sc.nextLine();
                        if (nom.matches("[a-zA-Z]+")) break;
                        System.out.println("Nom invalide ! Lettres seulement.");
                    }
                    while (true) {
                        System.out.print("Prénom: ");
                        prenom = sc.nextLine();
                        if (prenom.matches("[a-zA-Z]+")) break;
                        System.out.println("Prénom invalide ! Lettres seulement.");
                    }

                    // CIN
                    while (true) {
                        System.out.print("CIN: ");
                        cin = sc.nextLine();
                        if (cin.matches("\\d{5,10}")) break;
                        System.out.println("CIN invalide ! 5 à 10 chiffres.");
                    }

                    // Téléphone marocain
                    while (true) {
                        System.out.print("Téléphone: ");
                        tel = sc.nextLine();
                        if (tel.matches("0[67]\\d{8}")) break;
                        System.out.println("Téléphone invalide ! Format: 06xxxxxxxx ou 07xxxxxxxx.");
                    }

                    // Email
                    while (true) {
                        System.out.print("Email: ");
                        email = sc.nextLine();
                        if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) break;
                        System.out.println("Email invalide !");
                    }

                    Client client = new Client(nom, prenom, cin, tel, email);
                    myHotel.ajouterClient(client);
                    System.out.println("Client ajouté !");
                    break;

                case 2: // Créer réservation
                    System.out.print("CIN du client: ");
                    String clientCin = sc.nextLine();
                    Client rClient = myHotel.chercherClientParCIN(clientCin);
                    if (rClient == null) {
                        System.out.println("Client introuvable !");
                        break;
                    }

                    System.out.print("Numéro de chambre: ");
                    int roomNum = sc.nextInt();
                    sc.nextLine(); // consume newline

                    Chambre resChambre = myHotel.chercherChambreParNumero(roomNum);
                    if (resChambre == null) {
                        System.out.println("Chambre introuvable !");
                        break;
                    }

                    LocalDate start, end;
                    while (true) {
                        System.out.print("Date début (YYYY-MM-DD): ");
                        start = LocalDate.parse(sc.nextLine());
                        System.out.print("Date fin (YYYY-MM-DD): ");
                        end = LocalDate.parse(sc.nextLine());
                        if (!end.isBefore(start)) break;
                        System.out.println("Erreur: la date de fin doit être après la date de début !");
                    }

                    Reservation res = new Reservation(rClient, resChambre, start, end);

                    // services
                    System.out.print("Ajouter petit déjeuner ? (O/N): ");
                    if (sc.nextLine().equalsIgnoreCase("O")) {
                        res.ajouterService(new Service(TypeService.PETIT_DEJ));
                    }
                    System.out.print("Ajouter room service ? (O/N): ");
                    if (sc.nextLine().equalsIgnoreCase("O")) {
                        res.ajouterService(new Service(TypeService.ROOM_SERVICE));
                    }
                    System.out.print("Ajouter une seance de SPA ? (O/N): ");
                    if (sc.nextLine().equalsIgnoreCase("O")) {
                        res.ajouterService(new Service(TypeService.SPA));
                    }
                    System.out.print("Ajouter les frais de Parking ? (O/N): ");
                    if (sc.nextLine().equalsIgnoreCase("O")) {
                        res.ajouterService(new Service(TypeService.PARKING));
                    }


                    myHotel.creerReservation(res); // **important**: adds reservation to hotel + client
                    break;

                case 3: // Vérifier chambres
                    LocalDate dStart, dEnd;
                    while (true) {
                        System.out.print("Date début (YYYY-MM-DD): ");
                        dStart = LocalDate.parse(sc.nextLine());
                        System.out.print("Date fin (YYYY-MM-DD): ");
                        dEnd = LocalDate.parse(sc.nextLine());

                        if (!dEnd.isBefore(dStart)) break;
                        System.out.println("❌ Erreur : la date de fin doit être après la date de début !");
                    }

                    System.out.print("Type de chambre souhaité (SIMPLE, DOUBLE, SUITE) ou ALL: ");
                    String typeInput = sc.nextLine().toUpperCase();

                    System.out.println("\n--- Liste des chambres ---");
                    for (Chambre c : myHotel.getChambres()) {
                        if (!typeInput.equals("ALL") && !c.getType().toString().equals(typeInput)) {
                            continue;
                        }
                        System.out.println(c);
                    }
                    break;




                case 4: // Check-in
                    System.out.print("Numéro de chambre pour check-in: ");
                    int checkInNum = sc.nextInt();
                    sc.nextLine();
                    Reservation toCheckIn = myHotel.chercherReservationParNumeroChambre(checkInNum);
                    if (toCheckIn != null) {
                        myHotel.checkIn(toCheckIn);
                        System.out.println("Check-in effectué !");
                    } else {
                        System.out.println("Réservation introuvable !");
                    }
                    break;

                case 5: // Check-out
                    System.out.print("Numéro de chambre pour check-out: ");
                    int checkOutNum = sc.nextInt();
                    sc.nextLine();
                    Reservation toCheckOut = myHotel.chercherReservationParNumeroChambre(checkOutNum);
                    if (toCheckOut != null) {
                        Facture f = myHotel.checkOut(toCheckOut);
                        System.out.println("Facture générée :");
                        System.out.println(f);
                        System.out.print("La facture a t-elle été payée ? (O/N) ");
                        if (sc.nextLine().equalsIgnoreCase("O")) {
                            f.setPayee(true);
                            System.out.println("Facture payée !");
                        }

                        myHotel.traiterListeAttente(toCheckOut.getChambre());

                    } else {
                        System.out.println("Réservation introuvable !");
                    }
                    break;


                case 6: // la liste d'attente
                    System.out.println("\n--- Liste d'attente ---");
                    if (myHotel.getListeAttente().isEmpty()) {
                        System.out.println("Aucune réservation en attente !");
                    } else {
                        for (Reservation r : myHotel.getListeAttente()) {
                            System.out.println(r + " | Client: " + r.getClient().getNom());
                        }
                    }
                    break;


                case 7: // Annuler une réservation
                    System.out.print("Numéro de chambre de la réservation à annuler: ");
                    int cancelNum = sc.nextInt();
                    sc.nextLine();

                    Reservation toCancel = myHotel.chercherReservationParNumeroChambre(cancelNum);
                    if (toCancel != null) {
                        myHotel.annulerReservation(toCancel);

                        myHotel.traiterListeAttente(toCancel.getChambre());
                    } else {
                        // Check if the reservation is in waiting list
                        boolean found = false;
                        for (Reservation r : myHotel.getListeAttente()) {
                            if (r.getChambre().getNumero() == cancelNum) {
                                myHotel.annulerReservation(r);
                                found = true;
                                break;
                            }
                        }
                        if (!found) System.out.println("Aucune réservation trouvée pour ce numéro de chambre !");
                    }
                    break;


                case 8: // Quitter
                    running = false;
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Option invalide !");



            }
        }

        sc.close();
    }
}
