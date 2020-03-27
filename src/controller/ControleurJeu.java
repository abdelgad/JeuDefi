package controller;


import dao.DAODefi;
import dao.DAOScore;
import pojo.Defi;
import pojo.Score;

import java.time.LocalDate;
import java.util.LinkedList;

public class ControleurJeu
{
    private static Defi defi; //L'instance du défi en cours de jeu


    /**
     * Lance le défi
     * @param nomDefiChoisi le nom de défi selectionnée pour jouer
     * @param nomUtilisateurConnected le nom de l'utilisateur
     */
    public static void lancer(String nomDefiChoisi, String nomUtilisateurConnected)
    {
        DAODefi myDAODefi = new DAODefi();

        defi = myDAODefi.find(nomDefiChoisi, nomUtilisateurConnected);

        defi.start();
    }

    /**
     * retourne l'opération suivant dans le défi, ou null si il ne y a plus d'opérations à résoudre
     * @return l'opération à résoudre sous forme de String ou null si il ne y a plus d'opérations à résoudre
     */
    public static String suivant()
    {
        if(defi.getNbOperationCourant() <= defi.getNbOperations())
        {
            defi.incrementNbOperationCourant();
            return defi.suivant().toString();
        }
        else
        {
            defi.updateFullTime();
            Score newScore = new Score(
                    defi.getFullTime(),
                    defi.getNbErreursCourant(),
                    LocalDate.now()
            );

            DAOScore myDAOScore = new DAOScore();
            myDAOScore.create(newScore, defi.getNom());
            return null;
        }
    }

    /**
     * évalue la réponse d'une opération
     * @param reponse la réponse à évaluer
     * @return true si la réponse est juste, false sinon
     */
    public static boolean repondre(int reponse)
    {
        if(!defi.repondre(reponse))
        {
            defi.incrementnbErreursCourant();
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * retoure le temps pris pour résoudre le défis
     * @return le temps en secondes
     */
    public static Double getFullTime()
    {
        return defi.getFullTime();
    }

    /**
     * retourne le nombre d'erreurs dans une certaine instance de défi
     * @return le nombre d'erreurs
     */
    public static int getNbErreurs()
    {
        return defi.getNbErreursCourant();
    }

    /**
     * retourne une liste des scores liés à l'instance défi en cours
     * @return la liste des scores
     */
    public static LinkedList<Score> demanderMeilleursScores()
    {
        DAOScore myDAOScore = new DAOScore();
        return myDAOScore.findAll(defi);
    }

    /**
     * retourne la progression d'un défi sous forme 0 -> 1
     * @return la progression du défi
     */
    public static double getProgress()
    {
        return ((double)defi.getNbOperationCourant()-1)/defi.getNbOperations();
    }
}
