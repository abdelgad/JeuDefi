package controller;


import dao.DAODefi;
import dao.DAOScore;
import pojo.Defi;
import pojo.Score;

import java.time.LocalDate;
import java.util.LinkedList;

public class ControleurJeu
{
    private static Defi defi;

    //TODO: Static DAO's
    public static void lancer(String nomDefiChoisi, String nomUtilisateurConnected)
    {
        DAODefi myDAODefi = new DAODefi();

        defi = myDAODefi.find(nomDefiChoisi, nomUtilisateurConnected);

        defi.start();
    }

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


    public static Double getFullTime()
    {
        return defi.getFullTime();
    }

    public static int getNbErreurs()
    {
        return defi.getNbErreursCourant();
    }

    public static LinkedList<Score> demanderMeilleursScores()
    {
        DAOScore myDAOScore = new DAOScore();
        return myDAOScore.findAll(defi);
    }

    public static double getProgress()
    {
        return ((double)defi.getNbOperationCourant()-1)/defi.getNbOperations();
    }
}
