package controller;


import dao.DAODefi;
import dao.DAOUser;
import pojo.Defi;
import pojo.User;

import javax.swing.text.TableView;
import java.util.LinkedList;


public class ControleurGestion
{

    //TODO: Static DAO's
    public static boolean connect(String nom, String mdp)
    {
        DAOUser myDAOUser = new DAOUser();
        return myDAOUser.find(nom, mdp) != null;
    }

    public static LinkedList<Defi> chercherListesDefis(String username)
    {
        DAODefi myDAODefi = new DAODefi();
        DAOUser myDAOUser = new DAOUser();

        return myDAODefi.findAll(username);
    }

    public static boolean creerQuiz(String nom, int nb, int min1, int max1, int min2, int max2, String username)
    {
        DAODefi myDAODefi = new DAODefi();
        return myDAODefi.create(new Defi(nom, nb, min1, max1, min2, max2), username);
    }

    public static boolean creerUtilisateur(String nom, String mdp)
    {
        DAOUser myDAOUser = new DAOUser();
        return myDAOUser.create(new User(nom, mdp));
    }
}
