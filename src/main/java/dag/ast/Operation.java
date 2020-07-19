package dag.ast;

import java.util.List;

public class Operation {
    public String name;
    public List<SimpleExpression> valueList;

    public Operation( String name, List<SimpleExpression> valueList){
        this.name = name;
        this.valueList = valueList;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", type=" + valueList +
                '}';
    }
}
