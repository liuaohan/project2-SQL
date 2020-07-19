package dag.ast;

public class Property {
    /**
     * 类属性的名字
     */
    public String name;

    /**
     * 类属性的类型
     */
    public PropertyType type;

    public Property(String name, PropertyType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
