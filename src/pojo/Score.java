package pojo;

import java.time.LocalDate;

public class Score
{
    Double temps;
    int nbErreurs;
    LocalDate date;


    public Score(double temps, int nbErreurs, LocalDate date)
    {
        this.temps = temps;
        this.nbErreurs = nbErreurs;
        this.date = date;
    }

    public Double getTemps()
    {
        return temps;
    }

    public void setTemps(Double temps)
    {
        this.temps = temps;
    }

    public int getNbErreurs()
    {
        return nbErreurs;
    }

    public void setNbErreurs(int nbErreurs)
    {
        this.nbErreurs = nbErreurs;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }
}
