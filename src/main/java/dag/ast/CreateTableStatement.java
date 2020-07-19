package dag.ast;

import java.util.List;

public class CreateTableStatement implements Statement{

    public String tableName;

    public List<Property> propertyList;
    public CreateTableStatement(String tableName, List<Property> propertyList){
        this.tableName = tableName;
        this.propertyList = propertyList;
    }
    @Override
    public StatementType getType() {
        return StatementType.CREATE_TABLE;
    }
}
