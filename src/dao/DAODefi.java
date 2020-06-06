package dao;

import pojo.Defi;
import pojo.Domain;
import pojo.Score;
import pojo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAODefi extends DAO<Defi>
{
    public boolean create(Defi defi)
    {
        boolean estInseree = false;
        String query = "INSERT INTO Defis (Nom, nbOperations, DOMAIN1MIN, DOMAIN1MAX, DOMAIN2MIN, DOMAIN2MAX, REFUSER) VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setString(1, defi.getNom());
            prStat.setString(2, Integer.toString(defi.getNbOperations()));
            prStat.setString(3, Integer.toString(defi.getDomaineOperande1().getMin()));
            prStat.setString(4, Integer.toString(defi.getDomaineOperande1().getMax()));
            prStat.setString(5, Integer.toString(defi.getDomaineOperande2().getMin()));
            prStat.setString(6, Integer.toString(defi.getDomaineOperande2().getMax()));
            prStat.setString(7, defi.getUser().getUsername());

            int nbrLigneInsert = prStat.executeUpdate();
            if(nbrLigneInsert > 0)
            {
                estInseree = true;
            }
        }
        catch (SQLException e)
        {
            while(e != null)
            {
                //System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                //System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        }
        return estInseree;
    }

    public Defi find(String idDefi, String idUtilisateur)
    {
        Defi defi = null;
        String query = "SELECT * FROM DEFIS WHERE NOM =? AND REFUSER=?";
        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setString(1, idDefi);
            prStat.setString(2, idUtilisateur);

            try(ResultSet resultSet = prStat.executeQuery())
            {
                if(resultSet.next())
                {
                    Double bestScore = null;
                    DAOScore myDAOScore = new DAOScore();
                    Score bestScoreFound = myDAOScore.findBestScore(resultSet.getString("Nom"));
                    if(bestScoreFound != null)
                    {
                        bestScore = bestScoreFound.getTemps();
                    }

                    defi = new Defi(
                            resultSet.getString("Nom"),
                            resultSet.getInt("nbOperations"),
                            resultSet.getInt("Domain1Min"),
                            resultSet.getInt("Domain1Max"),
                            resultSet.getInt("Domain2Min"),
                            resultSet.getInt("Domain2Max"),
                            bestScore
                    );
                }
            }
        }
        catch(SQLException e)
        {
            while(e != null)
            {
                //System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                //System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        }
            return defi;
    }


    public LinkedList<Defi> findAll(String username)
    {
        LinkedList<Defi> defis = new LinkedList<Defi>();

        String query = "SELECT * FROM DEFIS WHERE REFUSER=?";
        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setString(1, username);

            try(ResultSet resultSet = prStat.executeQuery())
            {
                Defi defi = null;
                while(resultSet.next())
                {
                    Double bestScore = null;
                    DAOScore myDAOScore = new DAOScore();
                    Score bestScoreFound = myDAOScore.findBestScore(resultSet.getString("Nom"));
                    if(bestScoreFound != null)
                    {
                        bestScore = bestScoreFound.getTemps();
                    }

                    defi = new Defi(
                            resultSet.getString("Nom"),
                            resultSet.getInt("NbOperations"),
                            resultSet.getInt("Domain1Min"),
                            resultSet.getInt("Domain1Max"),
                            resultSet.getInt("Domain2Min"),
                            resultSet.getInt("Domain2Max"),
                            bestScore
                    );
                    defis.add(defi);
                }
            }
        }
        catch (SQLException e)
        {
            while(e != null)
            {
                //System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Message: " + e.getMessage());
                //System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        }
        return defis;
    }
}
