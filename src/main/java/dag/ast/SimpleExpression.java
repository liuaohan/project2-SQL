package dag.ast;

public class SimpleExpression {
    /**
     * 表达式的值
     */
    public Object value;

    /**
     * 表达式的类型
     */
    public PropertyType type;

    public SimpleExpression(Object value, PropertyType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return "SimpleExpression{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
