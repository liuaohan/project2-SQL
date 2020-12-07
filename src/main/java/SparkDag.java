import dag.parser.DagSqlParser;
import dag.parser.javacc.SqlParserJC;
import dag.result.DagNodeResult;
import dag.result.DagTree;
import rpc.DAGJob;
import rpc.Node;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SparkDag {

    public static void main(String[] args) {
        StringBuffer sql = new StringBuffer();
        try {
            File file = new File("E:\\Projects\\StructuredStreaming\\src\\main\\resources\\SQLAPP1.txt");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            //自定义缓冲区
            byte[] buffer = new byte[10240];
            int flag = 0;
            while ((flag = bis.read(buffer)) != -1) {
                sql.append(new String(buffer, 0, flag));
            }
            System.out.println(sql);
            //关闭的时候只需要关闭最外层的流就行了
            System.out.println(sql);

            //编译sql
            DagTree dagTree = SqlParserJC.parseSql(sql.toString());
            DagTree dagTree2 = DagSqlParser.parseSql(sql.toString());
            //构建dagjob
            DAGJob dag = buildNode(dagTree);
            DAGJob dag2 = buildNode(dagTree2);
            System.out.println(dag.toString());
            //System.out.println(dag2.toString());
            if (dag.toString().equals(dag2.toString())) {
                System.out.println("equals");
            } else {
                System.out.println("Not equals");
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static DAGJob buildNode(DagTree dagTree) {
        List<Node> allNodes = new ArrayList<>();

        List<String> paramsList;
        for (DagNodeResult res : dagTree.getSqlTree()) {
            if (res.getParams() != null && !res.getParams().isEmpty()) {
                paramsList = new ArrayList(res.getParams().values());
            } else if (res.getParamList() != null && !res.getParamList().isEmpty()) {
                paramsList = new ArrayList(res.getParamList());
            } else {
                paramsList = new ArrayList<>();
            }
            Node.Builder builder = Node.newBuilder();
            builder.setName(res.getName());
            builder.setType(res.getType());
            builder.addAllParameterList(paramsList);
            if (!"none".equals(res.getPreNode())) {
                builder.addPreNodeList(res.getPreNode());
            }
            Node node = builder.build();
            allNodes.add(node);
            System.out.println("");
        }

        //DAGJob dag = DAGJob.newBuilder().setName(dagTree.getDagResult().getName()).addAllAllNodeList(allNodes).build();
        DAGJob dag = DAGJob.newBuilder().addAllAllNodeList(allNodes).build();

        return dag;
    }
}
