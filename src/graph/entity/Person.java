package graph.entity;

public class Person{
	
	private int id;
	private String name;
	private String surname;
	private String country;
	
	/**
	 * @param id
	 * @param name
	 * @param surname
	 * @param country
	 * @throws IllegalAccessException 
	 */
	public Person(int id, String name, String surname, String country) throws IllegalAccessException {
		super();
		if(id < 0){
			throw new IllegalAccessException("Id is less than 0");
		}
		if(name == null || surname == null || country == null){
			throw new IllegalAccessException("Name, surname or country is null");
		}
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.country = country;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", country=" + country + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(obj == null || !(obj instanceof Person)){
			return false;
		}
		
		Person person = (Person) obj;
		
		if(this.name.equals(person.getName()) && this.surname.equals(person.getSurname()) && this.country.equals(person.getCountry())){
			//System.out.println("Equals Person");
			return true;
		}
	
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
    public int hashCode() {
        int result = name.hashCode();
        result += surname.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
	
	
	
}
