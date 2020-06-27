package dag.parser;

import org.w3c.dom.Node;
import rpc.NodeType;

import java.util.regex.Pattern;

public class ReaderParser implements IParser {
    private static final String PATTERN_STR = "create\\s+reader\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";

    private static final Pattern PATTERN = Pattern.compile(PATTERN_STR);

    public boolean verify(String sql) {
        return PATTERN.matcher(sql).find();
    }

    public NodeType getNodeType() {
        return NodeType.Reader;
    }

    @Override
    public String getPatternStr() {
        return "create\\s+reader\\s+(\\S+)\\s*\\((.+)\\)\\s*with\\s*\\((.+)\\)";
    }

    public static ReaderParser newInstance() {
        return new ReaderParser();
    }


    public static void main(String... ssss) {
        String sql = "create reader node1(isStream = 'false',url = '10.11.1.193:9092',topic = 'EasyPrPart10_test3',maxSize = '2097152',tuple = '500',offset = 'earliest')with(preNode='none')";
        System.out.println(PATTERN.matcher(sql).find());
    }
}
