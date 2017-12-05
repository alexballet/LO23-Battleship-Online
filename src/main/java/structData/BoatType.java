/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;

/**
 * BoatType is an emumerate of all the types of boats and numbur of cases associated.
 * @author lola
 */
public enum BoatType {
    PORTEAVIONS("porte-avions", 5), 
    CROISEURFR("croiseur", 4),
    CONTRETORPILLEUR("contre-torpilleur", 3),
    SOUSMARINFR("sous-marin", 3),
    TORPILLEUR("torpilleur", 2),
    CUIRASSE("cuirassé", 4),
    CROISEURB("croiseur", 3),
    SOUSMARINB("sous-marin", 1);
    
    private final String name;
    private final int nbCases;

    private BoatType(String name, int nbCases) {
        this.name = name;
        this.nbCases = nbCases;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the nbCases
     */
    public int getNbCases() {
        return nbCases;
    }
    
    
}
