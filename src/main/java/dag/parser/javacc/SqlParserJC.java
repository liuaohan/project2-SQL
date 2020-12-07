package dag.parser.javacc;

import Util.SplitSql;
import dag.ast.*;
import dag.generated.DAGJobSqlParser;
import dag.result.DagNodeResult;
import dag.result.DagResult;
import dag.result.DagTree;
import rpc.NodeType;

import java.io.ByteArrayInputStream;
import java.util.*;

public class SqlParserJC {

    private static List<Object> readerParam = new LinkedList<>();
    private static List<Object> writerParam = new LinkedList<>();
    private static List<String> nodeNameList = new LinkedList<>();
    private static String preNode = "none";
    private static String forkNode = "";
    private static int nodeIndex = 1;
    private static Map<String, DagNodeResult> writerNodes = new LinkedHashMap<>();
    private static DagNodeResult groupByNode = new DagNodeResult();
    private static DagNodeResult sortNode = new DagNodeResult();
    private static List<String> tempTableName = new LinkedList<>();


    public static DagTree parseSql(String sql) {
        init();
        List<Statement> statementList = new LinkedList<>();
        DagTree dagTree = new DagTree();
        try {
            statementList = DAGJobSqlParser.parse(new ByteArrayInputStream(sql.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Statement statement : statementList) {
            StatementType type = statement.getType();
            int index = 0;
            switch (type) {
                case CREATE_TABLE:
                    parseCreateTable((CreateTableStatement) statement, dagTree);
                    break;
                case LOAD:
                    parseLoad((LoadStatement) statement, dagTree);
                    break;
                case CREATE_TEMPORARY_TABLE:
                    parseCreateTemporaryTable((CreateTemporaryTableStatement) statement, dagTree);
                    break;
                case INSERT:
                    parseInsert((InsertStatement) statement, dagTree);
                    break;
                case SELECT:
                    parseSelect((SelectStatement) statement, dagTree);
                    break;
            }
        }
        //last(dagTree);
        DagResult dagResult = new DagResult();
        dagTree.setDagResult(dagResult);
        return dagTree;
    }


    public static void init() {
        readerParam = new LinkedList<>();
        writerParam = new LinkedList<>();
        nodeNameList = new LinkedList<>();
        preNode = "none";
        forkNode = "";
        nodeIndex = 1;
        writerNodes = new HashMap<>();
    }

    private static void parseCreateTable(CreateTableStatement statement, DagTree dagTree) {
        List<Property> propertyList = statement.propertyList;
        List<Object> fields = new LinkedList<>();

        if (statement.tableName.contains("reader")) {
            fields = readerParam;
        } else if (statement.tableName.contains("writer")) {
            fields = writerParam;
        }
        for (Property pro : propertyList) {
            fields.add(pro.name);
        }
    }

    private static void parseCreateTemporaryTable(CreateTemporaryTableStatement statement, DagTree dagTree) {
        List<Object> fields = new LinkedList<>();

        if (statement.tableName.contains("reader")) {
            fields = readerParam;
        } else if (statement.tableName.contains("writer")) {
            fields = writerParam;
        }

        tempTableName.add(statement.tableName);
    }

    private static void parseLoad(LoadStatement statement, DagTree dagTree) {
        if (statement.tableName.contains("reader")) {
            SimpleExpression properties = statement.source;
            String prop = properties.value.toString().split("\\/\\/")[1];
            List<String> valueList = SplitSql.splitWithDel(prop, ',');
            properties.value.toString().split(",");
            DagNodeResult res = new DagNodeResult();

            res.setName(getNodeName());
            res.setParamList(valueList);
            res.setPreNode(preNode);
            preNode = res.getName();
            res.setType(getNodeType(statement.tableName));
            dagTree.getSqlTree().add(res);
        } else {
            SimpleExpression properties = statement.source;
            String prop = properties.value.toString().split("\\/\\/")[1];
            List<String> valueList = SplitSql.splitWithDel(prop, ',');
            properties.value.toString().split(",");
            DagNodeResult res = new DagNodeResult();

            res.setParamList(valueList);
            res.setPreNode(preNode);
            res.setType(getNodeType(statement.tableName));
            writerNodes.put(statement.tableName, res);
        }
    }

    private static void parseInsert(InsertStatement statement, DagTree dagTree) {
        List<SimpleExpression> propertyList = statement.valueList;
        List<String> valueList = new LinkedList<>();
        DagNodeResult res = new DagNodeResult();
        for (SimpleExpression exp : propertyList) {
            PropertyType type = exp.type;
            String value = String.valueOf(exp.value);
            valueList.add(value);
        }
        res.setName(String.valueOf(valueList.get(0)));
        valueList.remove(0);
        res.setParamList(valueList);
        res.setPreNode(preNode);
        preNode = res.getName();
        res.setType(getNodeType(statement.tableName));
        dagTree.getSqlTree().add(res);
        System.out.println(valueList);
    }

    private static void parseSelect(SelectStatement statement, DagTree dagTree) {
        List<Operation> operationList = statement.operationList;
        DagNodeResult node;
        String lastprenode = preNode;
        for (Operation op : operationList) {
            List<String> valueList = new LinkedList<>();
            node = new DagNodeResult();
            if (op.opValue.value != null) {
                valueList = SplitSql.splitWithDel(op.opValue.value.toString(), ',');
            }
            node.setName(getNodeName());
            node.setType(getNodeType(op.name));
            node.setPreNode(preNode);
            preNode = node.getName();
            node.setParamList(valueList);

            if (!dagTree.contains(node)) {
                dagTree.getSqlTree().add(node);
            }
        }

        if (statement.group != null && statement.group.length() > 0) {
            groupByNode = new DagNodeResult();
            groupByNode.setType(NodeType.GroupBy);
        }

        if (statement.sort != null && statement.sort.length() > 0) {
            sortNode = new DagNodeResult();
            sortNode.setType(NodeType.Sort);
        }

        if (tempTableName.contains(statement.tableName)) {
            node = new DagNodeResult();

            if (groupByNode != null) {
                node.setName(getNodeName());
                node.setPreNode(preNode);
                preNode = node.getName();
                node.setType(NodeType.GroupBy);
                node.setParamList(groupByNode.getParamList());
                dagTree.getSqlTree().add(node);
            }

            if (sortNode != null) {
                node = new DagNodeResult();
                node.setName(getNodeName());
                node.setPreNode(preNode);
                preNode = node.getName();
                node.setType(NodeType.Sort);
                node.setParamList(groupByNode.getParamList());
                dagTree.getSqlTree().add(node);
            }

            node = writerNodes.getOrDefault(statement.intoTableName, new DagNodeResult());
            node.setName(getNodeName());
            node.setPreNode(preNode);
            preNode = node.getName();
            dagTree.getSqlTree().add(node);

            preNode = lastprenode;
        }
    }

    private static void last(DagTree dagTree) {
        int nums = writerNodes.size();
        for (Map.Entry<String, DagNodeResult> entry : writerNodes.entrySet()) {
            DagNodeResult node = new DagNodeResult();

            if (groupByNode != null) {
                node.setName(getNodeName());
                node.setPreNode(preNode);
                preNode = node.getName();
                node.setType(NodeType.GroupBy);
                node.setParamList(groupByNode.getParamList());
                dagTree.getSqlTree().add(node);
            }

            if (sortNode != null) {
                node = new DagNodeResult();
                node.setName(getNodeName());
                node.setPreNode(preNode);
                preNode = node.getName();
                node.setType(NodeType.Sort);
                node.setParamList(groupByNode.getParamList());
                dagTree.getSqlTree().add(node);
            }

            node = entry.getValue();
            node.setName(getNodeName());
            node.setPreNode(preNode);
            preNode = node.getName();
            dagTree.getSqlTree().add(node);
        }
    }

    private static NodeType getNodeType(String name) {
        name = name.toLowerCase();
        if (name.contains("reader"))
            return NodeType.Reader;
        else if (name.contains("writer"))
            return NodeType.Writer;
        else if (name.contains("tracker"))
            return NodeType.Tracker;
        else if (name.contains("lpr"))
            return NodeType.LPR;
        else if (name.contains("detection"))
            return NodeType.Detection;
        else if (name.contains("makeframe"))
            return NodeType.MakeFrame;
        else if (name.contains("group"))
            return NodeType.GroupBy;
        else if (name.contains("sort"))
            return NodeType.Sort;
        else if (name.contains("classification"))
            return NodeType.Classification;
        else if (name.contains("segmentation"))
            return NodeType.Segmentation;
        else if (name.contains("fork"))
            return NodeType.Fork;
        else if (name.contains("crop"))
            return NodeType.Crop;
        else return NodeType.Detection;
    }

    private static String getNodeName() {
        return "node" + nodeIndex++;
    }

}
