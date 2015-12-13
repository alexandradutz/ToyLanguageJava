package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Heap.IHeap;


/**
 * Created by Dutzi on 11/9/2015.
 */
public class ReadExp extends Exp {
    private Integer number;

    public ReadExp(Integer no)
    {
        this.number = no;
    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) {
        //int no;
        //Scanner reader = new Scanner(System.in);
        //System.out.println("Introduces an integer for ToyLanguage ");
        //Integer no = reader.nextInt();
        //this.number = no;
        return number;
    }

    @Override
    public String toStr() {
        return "read()";
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
