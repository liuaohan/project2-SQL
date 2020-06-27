package dag.parser;

import rpc.NodeType;

public class LRPParser implements IParser {
    public static LRPParser newInstance() {
        return new LRPParser();
    }

    @Override
    public String getPatternStr() {
        return "create\\s+lrp\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.LPR;
    }
}
