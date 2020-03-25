package pojo;

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


    public Defi(String nom, int nbOperations, int domaineOperande1Min, int domaineOperande1Max, int domaineOperande2Min, int domaineOperande2Max)
    {
        this.nom = nom;
        this.nbOperations = nbOperations;
        this.domaineOperande1 = new Domain(domaineOperande1Min, domaineOperande1Max);
        this.domaineOperande2 = new Domain(domaineOperande2Min, domaineOperande2Max);
        this.nbOperationCourant = nbOperations;
        this.bestScore = null;
    }

    public Defi(String nom, int nbOperations, int domaineOperande1Min, int domaineOperande1Max, int domaineOperande2Min, int domaineOperande2Max, Double bestScore)
    {
        this.nom = nom;
        this.nbOperations = nbOperations;
        this.domaineOperande1 = new Domain(domaineOperande1Min, domaineOperande1Max);
        this.domaineOperande2 = new Domain(domaineOperande2Min, domaineOperande2Max);
        this.nbOperationCourant = nbOperations;
        this.bestScore = bestScore;
    }

    public void start()
    {
        this.startTime = System.currentTimeMillis();
        this.nbErreursCourant = 0;
        this.nbOperationCourant = 1;
    }

    public void updateFullTime()
    {
        this.fullTime = (System.currentTimeMillis() - this.startTime) / 1000.0;
    }

    //TODO: meilleurTemps()


    public Operation suivant()
    {
       return this.operationCourant = new Operation
                (
                        (int) (Math.random() * (domaineOperande1.getMax() + 1 - domaineOperande1.getMin())) + domaineOperande1.getMin(),
                        (int) (Math.random() * (domaineOperande2.getMax() + 1 - domaineOperande2.getMin())) + domaineOperande2.getMin()
                );
    }

    public boolean repondre(int reponse)
    {
        return operationCourant.evaluer(reponse);
    }

    public void incrementNbOperationCourant()
    {
        this.nbOperationCourant++;
    }

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
}




