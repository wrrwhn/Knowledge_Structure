package concurrentCollection.skipList;

/**
 * Created by Yao on 2016/3/8.
 */
public class Contact {

    private int name;
    private String phone;

    public Contact(int name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Contact() {

    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name=" + name +
                ", phone='" + phone + '\'' +
                '}';
    }
}
