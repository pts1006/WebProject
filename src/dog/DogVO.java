package dog;

public class DogVO {
	private String species;
	private String petName;
	private String age;
	private String color;
	private String bodySize;
	
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBodySize() {
		return bodySize;
	}
	public void setBodySize(String bodySize) {
		this.bodySize = bodySize;
	}
	
	@Override
	public String toString() {
		return "DogVO [species=" + species + ", petName=" + petName + ", age=" + age + ", color=" + color
				+ ", bodySize=" + bodySize + "]";
	}
	
}
