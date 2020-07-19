import dag.ast.Statement;
import dag.generated.DAGJobSqlParser;

import java.io.FileInputStream;
import java.util.List;

public class Test {
    public static void main(String args[]) {

        String filePath = "E:\\Projects\\StructuredStreaming\\src\\main\\resources\\testDagJobSQL6";
        try {
            System.out.println("读取文件成功");
            FileInputStream inputStream = new FileInputStream(filePath);
            List<Statement> statementList = DAGJobSqlParser.parse(inputStream);
            for (Statement statement : statementList) {
                System.out.println(statement);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
