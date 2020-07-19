package dag.parser.javacc;

import dag.ast.*;
import dag.generated.DAGJobSqlParser;
import dag.result.DagNodeResult;
import dag.result.DagResult;
import dag.result.DagTree;
import rpc.NodeType;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.*;

public class SqlParserJC {

    private static List<Object> readerParam = new LinkedList<>();
    private static List<Object> writerParam = new LinkedList<>();
    private static List<String> nodeNameList = new LinkedList<>();
    private static String preNode = "none";
    private static String forkNode = "";

    public static DagTree parseSql(String sql) {
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
                case INSERT:
                    parseInsert((InsertStatement) statement, dagTree, index++);
                    break;
                case SELECT:
                    parseSelect((SelectStatement) statement, dagTree, index++);
                    break;
            }
        }
        DagResult dagResult = new DagResult();
        dagResult.setName("app2_dag");
        dagTree.setDagResult(dagResult);
        System.out.println(readerParam);
        System.out.println(writerParam);
        return dagTree;
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

    private static void parseInsert(InsertStatement statement, DagTree dagTree, int index) {
        List<SimpleExpression> propertyList = statement.valueList;
        List<Object> valueList = new LinkedList<>();
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

    private static void parseSelect(SelectStatement statement, DagTree dagTree, int index) {
        List<Operation> operationList = statement.operationList;
        DagNodeResult node;
        for (Operation op : operationList) {
            node = new DagNodeResult();
            List<SimpleExpression> propertyList = op.valueList;
            List<Object> valueList = new LinkedList<>();
            for (SimpleExpression exp : propertyList) {
                PropertyType type = exp.type;
                String value = String.valueOf(exp.value);
                valueList.add(value);
            }
            node.setName(String.valueOf(valueList.get(0)));
            valueList.remove(0);
            node.setType(getNodeType(op.name));
            node.setPreNode(preNode);
            preNode = node.getName();
            node.setParamList(valueList);

            if (!dagTree.contains(node)) {
                dagTree.getSqlTree().add(node);
            }
        }

        if (statement.group != null && statement.group.length() > 0) {
            node = new DagNodeResult();
            node.setName(statement.group);
            node.setType(NodeType.GroupBy);
            node.setPreNode(preNode);
            preNode = node.getName();
            dagTree.getSqlTree().add(node);
        }

        if (statement.sort != null && statement.sort.length() > 0) {
            node = new DagNodeResult();
            node.setName(statement.sort);
            node.setType(NodeType.Sort);
            node.setPreNode(preNode);
            preNode = node.getName();
            dagTree.getSqlTree().add(node);
        }
    }

    private static NodeType getNodeType(String name) {
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

}
