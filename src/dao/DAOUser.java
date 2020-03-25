package dao;

import pojo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser extends DAO<User>
{
    //Find By Username/Password
    public User find(String username, String password)
    {
        User user = null;
        String query = "SELECT * FROM Users Where Username=? AND PASSWORD=?";
        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setString(1, username);
            prStat.setString(2, password);

            try(ResultSet resultSet = prStat.executeQuery())
            {
                if(resultSet.next())
                {
                    user = new User(
                            resultSet.getString("Username"),
                            resultSet.getString("Password")
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
        return user;
    }


    //Find By Username/Password
    public User find(String username)
    {
        User user = null;
        String query = "SELECT * FROM Users Where Username=?";
        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setString(1, username);

            try(ResultSet resultSet = prStat.executeQuery())
            {
                if(resultSet.next())
                {
                    user = new User(
                            resultSet.getString("Username"),
                            resultSet.getString("Password")
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
        return user;
    }




    public boolean create(User user)
    {
        boolean estInseree = false;
        String query = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?,?)";
        try(PreparedStatement prStat = connection.prepareStatement(query))
        {
            prStat.setString(1, user.getUsername());
            prStat.setString(2, user.getPassword());

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
}
