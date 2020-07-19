package dag.parser;

public class TestA {
    public static final String sql ="create dag app2_dag (\n" +
            ") with(\n" +
            ");\n" +
            "\n" +
            "create reader node1(\n" +
            "    isStream = 'false',\n" +
            "    url = '10.11.1.193:9092',\n" +
            "    topic = 'EasyPrPart1small',\n" +
            "    maxSize = '2097152',\n" +
            "    tuple = '500',\n" +
            "    offset = 'earliest'\n" +
            ")WITH(\n" +
            "    preNode='none'\n" +
            ");\n" +
            "\n" +
            "create makeframe node2(\n" +
            ")WITH(\n" +
            "    preNode='node1'\n" +
            ");\n" +
            "\n" +
            "create lrp node3(\n" +
            ")WITH(\n" +
            "    preNode='node2'\n" +
            ");\n" +
            "\n" +
            "create groupby node4(\n" +
            ")WITH(\n" +
            "    preNode='node3'\n" +
            ");\n" +
            "\n" +
            "create sort node5(\n" +
            ")WITH(\n" +
            "    preNode='node4'\n" +
            ");\n" +
            "\n" +
            "create writer node6(\n" +
            "    transform='0',\n" +
            "    url='10.11.1.193:9092',\n" +
            "    maxSize='2097152',\n" +
            "    topic='app2output'\n" +
            ")WITH(\n" +
            "    preNode='node5'\n" +
            ");";
}
