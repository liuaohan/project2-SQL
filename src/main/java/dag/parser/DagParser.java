package dag.parser;

import rpc.NodeType;

public class DagParser implements IParser{

    @Override
    public String getPatternStr() {
        return "create\\s+dag\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    public static DagParser newInstance() {
        return new DagParser();
    }

}
