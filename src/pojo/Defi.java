package pojo;

import java.util.ArrayList;

public class Defi
{
    private String nom;
    private int nbOperations;
    private Domain domaineOperande1;
    private Domain domaineOperande2;
    private Double bestScore;
    private long startTime;
    private Double fullTime;
    private int nbErreursCourant;
    private int nbOperationCourant;
    private Operation operationCourant;
    private ArrayList<Score> scores;
    private User user;


    public Defi(String nom, int nbOperations, int domaineOperande1Min, int domaineOperande1Max, int domaineOperande2Min, int domaineOperande2Max, User user)
    {
        this.nom = nom;
        this.nbOperations = nbOperations;
        this.domaineOperande1 = new Domain(domaineOperande1Min, domaineOperande1Max);
        this.domaineOperande2 = new Domain(domaineOperande2Min, domaineOperande2Max);
        this.bestScore = null;
        this.scores = new ArrayList<Score>();
        this.user = user;
    }

    public Defi(String nom, int nbOperations, int domaineOperande1Min, int domaineOperande1Max, int domaineOperande2Min, int domaineOperande2Max, Double bestScore)
    {
        this.nom = nom;
        this.nbOperations = nbOperations;
        this.domaineOperande1 = new Domain(domaineOperande1Min, domaineOperande1Max);
        this.domaineOperande2 = new Domain(domaineOperande2Min, domaineOperande2Max);
        this.bestScore = bestScore;
        this.scores = new ArrayList<Score>();
    }

    /**
     * Lancer une instance d'un défi, "remet le timer à 0" et le nombre d'erreurs à 0
     */
    public void start()
    {
        this.startTime = System.currentTimeMillis();
        this.nbErreursCourant = 0;
        this.nbOperationCourant = 1;
    }

    /**
     *Fixer le temps de la fin d'une instance de défi
     */
    public void updateFullTime()
    {
        this.fullTime = (System.currentTimeMillis() - this.startTime) / 1000.0;
    }


    /**
     * Générer une instance d'opération avec des operandes aléatoires qui respectent les domains définis
     * dans le défi
     * @return l'instance de l'opération génerée
     */
    public Operation suivant()
    {
       return this.operationCourant = new Operation
                (
                        (int) (Math.random() * (domaineOperande1.getMax() + 1 - domaineOperande1.getMin())) + domaineOperande1.getMin(),
                        (int) (Math.random() * (domaineOperande2.getMax() + 1 - domaineOperande2.getMin())) + domaineOperande2.getMin()
                );
    }

    /**
     * évaluer la réponse de l'opération courante
     * @param reponse la réponse à évaluer
     * @return true si la réponse est juste, false sinon
     */
    public boolean repondre(int reponse)
    {
        return operationCourant.evaluer(reponse);
    }

    /**
     * Incrémente le nombre d'opération en cours
     */
    public void incrementNbOperationCourant()
    {
        this.nbOperationCourant++;
    }

    /**
     * Incrémente le nombre d'erreurs d'une instance d'un défi
     */
    public void incrementnbErreursCourant()
    {
        this.nbErreursCourant++;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public int getNbOperations()
    {
        return nbOperations;
    }

    public void setNbOperations(int nbOperations)
    {
        this.nbOperations = nbOperations;
    }

    public Domain getDomaineOperande1()
    {
        return domaineOperande1;
    }

    public void setDomaineOperande1(Domain domaineOperande1)
    {
        this.domaineOperande1 = domaineOperande1;
    }

    public Domain getDomaineOperande2()
    {
        return domaineOperande2;
    }

    public void setDomaineOperande2(Domain domaineOperande2)
    {
        this.domaineOperande2 = domaineOperande2;
    }

    public int getNbOperationCourant()
    {
        return nbOperationCourant;
    }

    public Double getBestScore()
    {
        return bestScore;
    }

    public int getNbErreursCourant()
    {
        return nbErreursCourant;
    }

    public void setNbErreursCourant(int nbErreursCourant)
    {
        this.nbErreursCourant = nbErreursCourant;
    }

    public Double getFullTime()
    {
        return fullTime;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}




