package model;

public class PersonDetails {
	private String name;
    private int age;

    public PersonDetails(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
