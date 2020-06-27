package dag.parser;

import rpc.NodeType;

public class TrackerParser implements IParser {
    public static TrackerParser newInstance() {
        return new TrackerParser();
    }


    @Override
    public String getPatternStr() {
        return "create\\s+tracker\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.Tracker;
    }

}
