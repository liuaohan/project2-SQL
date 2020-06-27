package dag.parser;

import rpc.NodeType;

public class GroupByParser implements IParser {

    public static GroupByParser newInstance() {
        return new GroupByParser();
    }


    @Override
    public String getPatternStr() {
        return "create\\s+groupby\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.GroupBy;
    }
}
