package com.yao.utils.custom;

public class ObjectUtils {

    public static void main(String[] args) {

        ObjectUtils.print(new Father("laodou"));
        ObjectUtils.print(new Mother("laoma"));

        new FanXing<Person>(new Person("Person")).print();
        new FanXing<Person>(new Father("Person-Father")).print();
        new FanXing<Person>(new Mother("Person-Mother")).print();
    }

    public static void print(Object obj) {
        if (obj instanceof Father) {
            System.out.println(((Father) obj).getName());
        } else if (obj instanceof Mother) {
            System.out.println(((Mother) obj).getName());
        }
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Father extends Person {

    public Father(String name) {
        super(name);
    }
}

class Mother extends Person {

    public Mother(String name) {
        super(name);
    }
}

class FanXing<T extends Person> {

    T person;

    public FanXing(T person) {
        this.person = person;
    }

    public T getPerson() {
        return person;
    }

    public void setPerson(T person) {
        this.person = person;
    }

    public void print() {
        System.out.println(person.getName());
    }
}