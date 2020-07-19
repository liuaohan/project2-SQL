package dag.ast;

import java.util.List;

public class InsertStatement implements Statement{
    /**
     * 操作的类名
     */
    public String tableName;

    /**
     * 插入的属性值列表
     */
    public List<SimpleExpression> valueList;

    public InsertStatement(String className, List<SimpleExpression> valueList) {
        this.tableName = className;
        this.valueList = valueList;
    }

    @Override
    public StatementType getType() {
        return StatementType.INSERT;
    }

    @Override
    public String toString() {
        return "InsertStatement{" +
                "className='" + tableName + '\'' +
                ", valueList=" + valueList +
                '}';
    }
}
