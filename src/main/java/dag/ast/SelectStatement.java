package dag.ast;

import java.util.List;

public class SelectStatement implements Statement{
    /**
     * 投影的列表
     */
    public List<Operation> operationList;

    /**
     * 查询的类名
     */
    public String tableName;

    public String intoTableName;

    public String group;

    public String sort;

    public SelectStatement(List<Operation> operationList, String tableName, String intoTableName, String group, String sort) {
        this.operationList = operationList;
        this.tableName = tableName;
        this.intoTableName = intoTableName;
        this.group = group;
        this.sort = sort;
    }

    public SelectStatement(List<Operation> operationList, String tableName, String intoTableName) {
        this.operationList = operationList;
        this.tableName = tableName;
        this.intoTableName = intoTableName;
    }

    @Override
    public StatementType getType() {
        return StatementType.SELECT;
    }

    @Override
    public String toString() {
        return "SelectStatement{" +
                "operationList=" + operationList +
                ", tableName='" + tableName + '\'' +
                ", groupby=" + group +
                ", sortby=" + sort +
                '}';
    }
}
