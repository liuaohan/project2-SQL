package dag.parser;

import Util.SplitSql;
import dag.result.IResult;
import dag.result.DagNodeResult;
import rpc.NodeType;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakeFrameParser implements IParser {
    public static MakeFrameParser newInstance() {
        return new MakeFrameParser();
    }

    private static final String PATTERN_STR = "create\\s+makeframe\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";

    private static final Pattern PATTERN = Pattern.compile(PATTERN_STR);

    public boolean verify(String sql) {
        return PATTERN.matcher(sql).find();
    }

    @Override
    public String getPatternStr() {
        return "create\\s+makeframe\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.MakeFrame;
    }

    public DagNodeResult parseSql(String sql) {
        DagNodeResult res = new DagNodeResult();

        Matcher matcher = PATTERN.matcher(sql);
        if (matcher.find()) {
            String name = matcher.group(1);
            String params = matcher.group(2);
            String preNode = matcher.group(3).split("'")[1];
            Map<String, String> paramsMap = SplitSql.parsePropString(params);

            //((DagNodeResult) res).setName(name);
            //((DagNodeResult) res).setParams(paramsMap);
            //((DagNodeResult) res).setPreNode(preNode);
            res.setName(name);
            res.setParams(paramsMap);
            res.setPreNode(preNode);
        }
        return res;
    }
}
