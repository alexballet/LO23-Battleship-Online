package structData;

/**
 * BoatType is an emumerate of all the types of boats and numbur of cases associated.
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
     * Accessor for the boat's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the boat's nbCases
     * @return the nbCases : the number of the boat's number of cases
     */
    public int getNbCases() {
        return nbCases;
    }
    
}

