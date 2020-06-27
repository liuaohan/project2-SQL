package dag.parser;

import rpc.NodeType;

public class WriterParser implements IParser {
    public static WriterParser newInstance() {
        return new WriterParser();
    }


    @Override
    public String getPatternStr() {
        return "create\\s+writer\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.Writer;
    }


}
