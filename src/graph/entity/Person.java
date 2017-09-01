package graph.entity;

public class Person{
	
	private int id;
	private String name;
	private String surname;
	private String country;
	
	public Person(int id, String name, String surname, String country) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.country = country;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", country=" + country + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(obj == null || !(obj instanceof Person)){
			return false;
		}
		
		Person person = (Person) obj;
		
		if(this.name.equals(person.getName()) && this.surname.equals(person.getSurname()) && this.country.equals(person.getCountry())){
			System.out.println("Equals Person");
			return true;
		}
	
		return false;
	}

	@Override
    public int hashCode() {
        int result = name.hashCode();
        result += surname.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
	
	
	
}
