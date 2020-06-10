package net.grocerymanagement.model;
public class Grocery {
    protected int id;
    protected String name;
    protected String type;
    protected String company;

    public Grocery() {}

    public Grocery(String name, String type, String company) {
        super();
        this.name = name;
        this.type = type;
        this.company = company;
    }

    public Grocery(int id, String name, String type, String company) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
}