# 🧠 Logique Métier – Système de Gestion d’Hôtel

## 🎯 Objectif métier
Le système vise à modéliser le fonctionnement réel d’un hôtel :
- réservation de chambres
- occupation et libération des chambres
- gestion des clients
- facturation des services consommés

---

## 🏨 Gestion des chambres
Une chambre est définie par :
- un numéro
- un étage
- un type (SIMPLE, DOUBLE, SUITE)
- un état (DISPONIBLE, RESERVEE, OCCUPEE)

👉 Lorsqu’une réservation est créée ou terminée, l’état de la chambre est automatiquement mis à jour.

---

## 👤 Gestion des clients
Un client possède :
- des informations personnelles (nom, CIN, téléphone, email)
- un historique de réservations

Chaque réservation est liée à **un seul client**.

---

## 📅 Gestion des réservations
Une réservation est définie par :
- une date de début et de fin
- une chambre
- un client
- un état (CONFIRMEE, EN_COURS, TERMINEE)
- une liste de services optionnels

### 🔁 Conflits de réservation
- Si une chambre est déjà réservée ou occupée → la réservation est placée en **liste d’attente**
- Lorsqu’une chambre se libère → la première réservation en attente est automatiquement confirmée

---

## 🧾 Facturation
Une facture est générée au moment du **check-out** :
- prix des nuits (selon le type de chambre)
- prix des services ajoutés
- état de paiement (payée ou non)

---

## 👀 Pattern Observer
Le pattern Observer est utilisé pour :
- surveiller les changements d’état des chambres
- notifier automatiquement l’utilisateur (console)

Exemple :
> 🔔 La chambre 12 est maintenant OCCUPÉE

Cela permet de séparer la logique métier de l’affichage.

---

## ✅ Avantages du système
- Structure claire et modulaire
- Facilement extensible (nouveaux services, nouvelles règles)
- Respect des bonnes pratiques POO
- Simulation réaliste d’un système hôtelier

---

## 🏁 Conclusion
Ce projet modélise un système hôtelier complet, cohérent et extensible, en appliquant des concepts fondamentaux de génie logiciel.
