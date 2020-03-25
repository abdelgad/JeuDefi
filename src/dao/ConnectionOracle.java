package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class ConnectionOracle
{
    private static Connection connexionBD = null;
//    voir fichier properties

    private ConnectionOracle()
    {
        //Properties file
        Properties props = new Properties(); //Collection clé/valeurs (sub à HashTable)
        try(FileInputStream fis = new FileInputStream("conf.properties"))
        {
            props.load(fis);
        } catch (FileNotFoundException e)
        {
            //C'est mieux de logger l'erreur mais j'utilise syso
            System.out.println("Properties file not found");
            System.exit(-1);
        } catch (IOException e)
        {
            System.out.println("Error while opening properties file");
            System.exit(-1);
        }

        //Driver
        try
        {
            Class.forName(props.getProperty("jdbc.driver.class"));
        } catch (ClassNotFoundException e)
        {
            System.out.println("Driver not found");
            System.exit(-1);
        }
        String dbURL = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        //Connection
        try
        {
            connexionBD = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e)
        {
            System.out.println("Database connection error");
            System.exit(-1);
        }
    }

    public static Connection getConnectionInstance()
    {
        if(connexionBD == null)
        {
            new ConnectionOracle();
        }
        return connexionBD;
    }

    public static void closeConnectionInstace()
    {
        if(connexionBD != null)
        {
            try
            {
                connexionBD.close();
            }
            catch (SQLException e)
            {
                System.out.println("Error while closing connection to the DB");
                System.exit(-1);
            }
        }
    }
}