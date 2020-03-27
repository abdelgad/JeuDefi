package controller;


import dao.DAODefi;
import dao.DAOUser;
import pojo.Defi;
import pojo.User;

import javax.swing.text.TableView;
import java.util.LinkedList;


public class ControleurGestion
{

    /**
     * connecter un utilisateur
     * @param nom le username de l'utilisateur à login
     * @param mdp le password de l'utilisateur à login
     * @return true si le username/password existent bien dans la BD, false sinon
     */
    public static boolean connect(String nom, String mdp)
    {
        DAOUser myDAOUser = new DAOUser();
        return myDAOUser.find(nom, mdp) != null;
    }

    /**
     * chercher la liste des défis liée à un certain utilisateur
     * @param username le username de l'utilisateur connecté
     * @return La liste défis liée à l'utilisateur username
     */
    public static LinkedList<Defi> chercherListesDefis(String username)
    {
        DAODefi myDAODefi = new DAODefi();
        DAOUser myDAOUser = new DAOUser();

        return myDAODefi.findAll(username);
    }

    /**
     * Créer un nouveau défi lié à l'utlisateur connecté
     * @param nom le nom de défi
     * @param nb le nombre d'opérations dans le défi
     * @param min1 le min du premier domaine
     * @param max1 le max du premier domaine
     * @param min2 le min du deuxème domaine
     * @param max2 le max du deuxème domaine
     * @param username le username de l'utilisateur connecté
     * @return true si le défi à été crée avec réussite, false sinon
     */
    public static boolean creerQuiz(String nom, int nb, int min1, int max1, int min2, int max2, String username)
    {
        DAODefi myDAODefi = new DAODefi();
        return myDAODefi.create(new Defi(nom, nb, min1, max1, min2, max2), username);
    }

    /**
     * Créer un nouveau utilisateur
     * @param nom le username de l'utilisateur à créer
     * @param mdp le password de l'utilisateur à créer
     * @return true si l'utilisateur à été crée avec succès, false sinon
     */
    public static boolean creerUtilisateur(String nom, String mdp)
    {
        DAOUser myDAOUser = new DAOUser();
        return myDAOUser.create(new User(nom, mdp));
    }
}
