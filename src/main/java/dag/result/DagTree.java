package dag.result;

import java.util.ArrayList;
import java.util.List;

public class DagTree {

    private DagResult dagResult;
    private List<DagNodeResult> sqlTree;
    private String appInfo;

    public List<DagNodeResult> getSqlTree() {
        return sqlTree;
    }

    public DagTree() {
        sqlTree = new ArrayList<>();
    }

    public DagResult getDagResult() {
        return dagResult;
    }

    public void setDagResult(DagResult dagResult) {
        this.dagResult = dagResult;
    }

    public void setSqlTree(List<DagNodeResult> sqlTree) {
        this.sqlTree = sqlTree;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }


    public boolean contains(DagNodeResult node) {
        for (DagNodeResult dagNodeResult : sqlTree) {
            if (node.getName().equals(dagNodeResult.getName())) {
                return true;
            }
        }
        return false;
    }

    //储存各源表的分隔符和列属性
    public static ArrayList<String> delimiters = new ArrayList<>();
    public static ArrayList<List> columnLists = new ArrayList<>();
}
