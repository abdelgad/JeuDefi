package pojo;

public class Operation
{
    int operande1;
    int operande2;

    Operation(int operande1, int operande2)
    {
        this.operande1 = operande1;
        this.operande2 = operande2;
    }

    public int getOperande1()
    {
        return operande1;
    }

    public int getOperande2()
    {
        return operande2;
    }

    public boolean evaluer(int response)
    {
        return response == this.operande1 * this.operande2;
    }

    public String toString()
    {
        return Integer.toString(operande1) + "×" + Integer.toString(operande2);
    }
}
