package com.sakab.fufu_car_app;

/**
 * Created by pierre on 22/03/2018.
 */

public class Moteur {
    public static int vitesseGauche;
    public static int vitesseDroite;
    public static boolean sens;

    public Moteur() {
    }

    public static int getVitesseGauche() {
        return vitesseGauche;
    }

    public static void setVitesseGauche(int vitesseGauche) {
        Moteur.vitesseGauche = vitesseGauche;
    }

    public static int getVitesseDroite() {
        return vitesseDroite;
    }

    public static void setVitesseDroite(int vitesseDroite) {
        Moteur.vitesseDroite = vitesseDroite;
    }

    public static boolean isSens() {
        return sens;
    }

    public static void setSens(boolean sens) {
        Moteur.sens = sens;
    }

    public Moteur calculVitesse(Moteur moteur, int X, int Y){
        if(Y > 0){
            if(X > 0){ //On tourne à droite
                moteur.setVitesseGauche(100);
                moteur.setVitesseDroite(100 - X);
            }else if(X < 0){ // On tourne à gauche
                moteur.setVitesseDroite(100);
                moteur.setVitesseGauche(100 + X);
            }
        }else{
            if(X > 0){ //On tourne à gauche
                moteur.setVitesseGauche(-100);
                moteur.setVitesseDroite(-100 + X);
            }else if(X < 0){ // On tourne à droite
                moteur.setVitesseGauche(-100 - X);
                moteur.setVitesseDroite(-100);
            }
        }
        return moteur;
    }

}
