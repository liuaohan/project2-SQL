package dag.ast;

import java.util.List;

public class Operation {
    public String name;
    //public List<SimpleExpression> valueList;
    public ComplexExpression opValue;
    public Operation( String name, ComplexExpression opValue){
        this.name = name;
        this.opValue = opValue;
    }


}
