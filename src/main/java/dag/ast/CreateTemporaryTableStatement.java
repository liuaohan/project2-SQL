package dag.ast;

import java.util.List;

public class CreateTemporaryTableStatement implements Statement{

    public String tableName;

    public CreateTemporaryTableStatement(String tableName){
        this.tableName = tableName;
    }
    @Override
    public StatementType getType() {
        return StatementType.CREATE_TEMPORARY_TABLE;
    }
}
