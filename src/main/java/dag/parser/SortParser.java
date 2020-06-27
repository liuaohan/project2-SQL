package dag.parser;

import rpc.NodeType;

public class SortParser implements IParser {

    public static SortParser newInstance() {
        return new SortParser();
    }


    @Override
    public String getPatternStr() {
        return "create\\s+sort\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.Sort;
    }
}
