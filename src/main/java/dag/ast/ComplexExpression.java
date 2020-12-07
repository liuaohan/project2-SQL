package dag.ast;

public class ComplexExpression {
    /**
     * 表达式的值
     */
    public Object value;

    /**
     * 表达式的类型
     */
    public PropertyType type;

    public ComplexExpression(Object value, PropertyType type) {
        this.value = value;
        this.type = type;
    }

}
