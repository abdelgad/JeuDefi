package dao;

import pojo.Defi;
import pojo.Score;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

public class DAOScore extends DAO<Score>
{
    public boolean create (Score score, String idDefi)
    {
        boolean estInseree = false;
        String query = "INSERT INTO SCORES (Temps, NBErreurs, ScoreDate, RefDefi) VALUES (?,?,?,?)";

        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setDouble(1, score.getTemps());
            prStat.setInt(2, score.getNbErreurs());
            prStat.setObject(3, score.getDate());
            prStat.setString(4, idDefi);

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


    public Score findBestScore(String defiNom)
    {
        Score score = null;
        String query = "SELECT * FROM (SELECT  * FROM Scores WHERE REFDEFI = ? order by TEMPS asc) where ROWNUM = 1";
        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setString(1, defiNom);

            try(ResultSet resultSet = prStat.executeQuery())
            {
                if(resultSet.next())
                {
                    score = new Score(
                            resultSet.getDouble("Temps"),
                            resultSet.getInt("NBErreurs"),
                            resultSet.getObject("ScoreDate", LocalDate.class)
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
        return score;
    }


    public LinkedList<Score> findAll(Defi defi)
    {
        LinkedList<Score> scores = new LinkedList<Score>();

        String query = "SELECT * FROM SCORES WHERE REFDEFI=?";
        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setString(1, defi.getNom());

            try(ResultSet resultSet = prStat.executeQuery())
            {
                Score score = null;
                while(resultSet.next())
                {
                    score = new Score(
                            resultSet.getDouble("temps"),
                            resultSet.getInt("NBErreurs"),
                            resultSet.getObject("ScoreDate", LocalDate.class)
                    );
                    scores.add(score);
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
        return scores;
    }
}
