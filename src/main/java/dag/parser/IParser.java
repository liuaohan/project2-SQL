package dag.parser;


import dag.result.IResult;
import rpc.NodeType;

public interface IParser {
    /**
     * 是否满足该解析类型
     */
    default boolean verify(String sql) {
        return false;
    }

    /***
     * 解析sql
     */
    default IResult parseSql(String sql) {
        return null;
    }

    String getPatternStr();

    default NodeType getNodeType() {
        return null;
    }
}
