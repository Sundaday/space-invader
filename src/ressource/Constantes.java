package ressource;

public abstract class Constantes {

    //Dimension fenetre
    public static final int LARGUEUR_FENETRE = 600;
    public static final int HAUTEUR_FENETRE = 600;
    public static final int MARGE_FENETRE = 50;

    //Vaisseau
    //Dimensions Vaisseau
    public static final int LARGEUR_VAISSEAU = 39;
    public static final int HAUTEUR_VAISSEAU = 24;
    //Position initiale du vaisseau
    public static final int X_POS_INIT_VAISSEAU = (LARGUEUR_FENETRE- LARGEUR_VAISSEAU)/2;
    public static final int Y_POS_VAISSEAU = 490;
    //Unité de deplacement du vaisseau
    public static final int DX_VAISSEAU = 1;
}
