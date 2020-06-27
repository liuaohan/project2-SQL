package dag.result;

import rpc.NodeType;

import java.util.Map;

public class DagNodeResult implements IResult {
    private String name;

    private Map<String, Object> params;

    private String preNode;

    private NodeType type;

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getPreNode() {
        return preNode;
    }

    public void setPreNode(String preNode) {
        this.preNode = preNode;
    }
}
