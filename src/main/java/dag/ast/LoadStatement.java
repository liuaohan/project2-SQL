package dag.ast;

import java.util.List;

public class LoadStatement implements Statement{

    /**
     * 查询的类名
     */
    public String tableName;

    public SimpleExpression source;


    public LoadStatement(String tableName, SimpleExpression source) {
        this.tableName = tableName;
        this.source = source;
    }

    @Override
    public StatementType getType() {
        return StatementType.LOAD;
    }


}
