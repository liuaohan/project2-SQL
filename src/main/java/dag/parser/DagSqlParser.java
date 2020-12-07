package dag.parser;

import Util.SplitSql;
import dag.result.DagNodeResult;
import dag.result.DagResult;
import dag.result.DagTree;
import rpc.NodeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Util.SplitSql.isNullOrEmpty;
import static Util.SplitSql.newArrayList;

public class DagSqlParser {

    private static String patternStr;

    private static Pattern pattern;

    private static NodeType nodeType;

    protected static List<IParser> sqlParserList
            = newArrayList(
            DagParser.newInstance(),
            DetectionParser.newInstance(),
            GroupByParser.newInstance(),
            LRPParser.newInstance(),
            MakeFrameParser.newInstance(),
            ReaderParser.newInstance(),
            SortParser.newInstance(),
            TrackerParser.newInstance(),
            WriterParser.newInstance());
    //public static DagTree dagTree;

    public static DagTree parseSql(String sql) {

        sql = TestA.sql;
        sql = sql.toLowerCase();
        sql = sql.replaceAll("--.*", "")
                .replaceAll("\r\n", " ")
                .replaceAll("\n", " ")
                .replace("\t", " ").trim();

        //将整个sql文件按照';'分隔，分成几个sql
        List<String> sqlArr = SplitSql.splitWithDel(sql, ';');
        DagTree dagTree = new DagTree();

        for (String childSql : sqlArr) {
            if (SplitSql.isNullOrEmpty(childSql)) {
                continue;
            }

            boolean result = false;
            //sqlParserList含有多种解析类型
            //为每一个sql查找合适的解析类型

            for (IParser sqlParser : sqlParserList) {
                patternStr = sqlParser.getPatternStr();
                nodeType = sqlParser.getNodeType();
                if (nodeType != null) {//node
                    DagNodeResult parseResult = parseNodeResult(childSql);
                    result = true;
                    if (parseResult != null) {
                        dagTree.getSqlTree().add(parseResult);
                        break;
                    }
                } else {//dag
                    DagResult parseResult = parseDagResult(childSql);
                    result = true;
                    if (parseResult != null) {
                        dagTree.setDagResult(parseResult);
                        break;
                    }
                }
            }
            if (!result) {
                //没有匹配的语句
                System.out.println("No Sql Matchs");
            }
        }
        //构建dagjob
        return dagTree;
    }

    public static DagResult parseDagResult(String sql) {
        DagResult res = new DagResult();

        pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String name = matcher.group(1);
            res.setName(name);
            return res;
        } else {
            return null;
        }
    }

    public static DagNodeResult parseNodeResult(String sql) {
        DagNodeResult res = new DagNodeResult();

        pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String name = matcher.group(1);
            String params = matcher.group(2);
            String preNode = matcher.group(3).split("'")[1];

            Map<String, String> paramsMap = new HashMap<>();
            if (!isNullOrEmpty(params) && !isNullOrEmpty(params.trim())) {
                paramsMap = SplitSql.parsePropString(params);
            }
            res.setName(name);
            res.setParams(paramsMap);
            res.setPreNode(preNode);
            res.setType(nodeType);
            return res;
        } else {
            return null;
        }

    }
}
