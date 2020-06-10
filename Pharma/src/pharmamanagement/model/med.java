package pharmamanagement.model;

public class med {

	protected int id;
	protected String name;
	protected String company;

	public med() {
	}

	public med(String name, String company) {
		super();
		this.name = name;
		this.company = company;

	}

	public med(int id, String name, String company) {
		super();
		this.id = id;
		this.name = name;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
