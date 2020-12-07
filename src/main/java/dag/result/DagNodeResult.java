package dag.result;

import rpc.NodeType;

import java.util.List;
import java.util.Map;

public class DagNodeResult implements IResult {
    private String name;

    private Map<String, String> params;

    private List<String> paramList;

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

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getPreNode() {
        return preNode;
    }

    public void setPreNode(String preNode) {
        this.preNode = preNode;
    }


    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }
}
