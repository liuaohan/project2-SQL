import dag.parser.DagSqlParser;
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
            File file = new File("E:\\Projects\\StructuredStreaming\\src\\main\\resources\\testDagJobSQL2");
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
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql);

        //编译sql
        DagTree dagTree = DagSqlParser.parseSql(sql.toString());
        //构建dagjob
        DAGJob dag = buildNode(dagTree);
    }

    private static DAGJob buildNode(DagTree dagTree) {
        List<Node> allNodes = new ArrayList<>();

        for (DagNodeResult res : dagTree.getSqlTree()) {
            List<String> paramsList = new ArrayList(res.getParams().values());

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
        DAGJob dag = DAGJob.newBuilder().setName(dagTree.getDagResult().getName()).addAllAllNodeList(allNodes).build();
        System.out.println(dag);
        return dag;
    }


}
