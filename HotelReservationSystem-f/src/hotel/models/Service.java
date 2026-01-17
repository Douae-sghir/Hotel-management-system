package hotel.models;

import hotel.enums.TypeService;

public class Service {

    private TypeService type;


    public Service(TypeService type) {
        this.type = type;
    }


    public TypeService getType() {
        return type;
    }


    public double getPrix() {
        return type.getPrix();
    }

    @Override
    public String toString() {
        return type + " (" + getPrix() + "€)";
    }
}
