package executor.forAll;

/**
 * Created by Yao on 2015/9/28.
 */
public class Result {

    private String name;
    private Integer value;

    public Result(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
