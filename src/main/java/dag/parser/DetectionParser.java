package dag.parser;

import rpc.NodeType;

public class DetectionParser implements IParser {
    public static DetectionParser newInstance() {
        return new DetectionParser();
    }

    @Override
    public String getPatternStr() {
        return "create\\s+detection\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.Detection;
    }

}
