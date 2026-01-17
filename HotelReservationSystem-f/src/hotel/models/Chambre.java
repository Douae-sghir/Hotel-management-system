package hotel.models;


import hotel.enums.TypeChambre;
import hotel.enums.EtatChambre;
import hotel.observer.Observable;

public class Chambre extends Observable {
    private int numero;
    private int etage;
    private TypeChambre type;
    private EtatChambre etat;

    public Chambre(int numero,int etage,TypeChambre type,EtatChambre etat) {
        this.numero = numero;
        this.etage = etage;
        this.type = type;
        this.etat = EtatChambre.DISPONIBLE;;
    }
    public int getNumero() {return numero;}
    public int getEtage(){return etage;}
    public TypeChambre getType() {return type;}
    public EtatChambre getEtat() {return etat;}
    public double getPrixParNuit() {
        return type.getPrix();
    }
    public int getCapacite() {
        return type.getCapacite();
    }
    public void setEtat(EtatChambre etat) { this.etat = etat;
        notifyObservers("La chambre " + numero + " est maintenant " + etat);}

    @Override
    public String toString() {
        return "Chambre " + numero +
                " | Type: " + type +
                " | Etat: " + etat +
                " | Etage: " + etage +
                " | Prix: " + getPrixParNuit() +
                " | Capacite: " + getCapacite();
    }


}
