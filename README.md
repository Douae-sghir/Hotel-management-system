# 🏨 Système de Gestion d’Hôtel (Java)

## 📌 Description
Ce projet est une application Java de gestion d’hôtel permettant de gérer :
- les chambres
- les clients
- les réservations
- les services
- la facturation  
  avec une **interface console interactive**.

Le système utilise des concepts de **programmation orientée objet**, des **énumérations**, et le **pattern Observer** pour notifier les changements d’état des chambres.

---

## ⚙️ Fonctionnalités principales
- Ajouter des clients avec validation des données
- Créer des réservations avec gestion des conflits
- Gérer les check-in / check-out
- Générer des factures automatiquement
- Ajouter des services (Petit-déjeuner, SPA, etc.)
- Gérer une liste d’attente
- Vérifier les chambres disponibles par date et type
- Notifications automatiques lors du changement d’état d’une chambre

---

## 🧱 Architecture du projet
hotel/

│
├── enums/ → États et types (Chambre, Réservation, Service)

├── models/ → Classes métier (Chambre, Client, Reservation, Facture…)

├── observer/ → Pattern Observer (Observable / Observer)

├── ui/ → Interface console interactive

├── Hotel.java → Cœur de la logique métier

└── Main.java → Exemple d’exécution


---

## ▶️ Lancer le projet
1. Ouvrir le projet dans un IDE Java (IntelliJ, Eclipse…)
2. Lancer :
    - `InteractiveConsole.java` pour l’application interactive
    - ou `Main.java` pour un scénario de test rapide

---

## 🧠 Concepts utilisés
- Programmation Orientée Objet (POO)
- Encapsulation, associations, énumérations
- Pattern Observer
- Gestion des dates avec `LocalDate`
- Listes (`ArrayList`)
- Validation des entrées utilisateur

---

## 👨‍🏫 Objectif pédagogique
Ce projet a pour but de mettre en pratique :
- la modélisation UML
- la séparation logique métier / interface
- la gestion d’un système réel (hôtel)


