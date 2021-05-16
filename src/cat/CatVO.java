package cat;

public class CatVO {

	private String catName;
	private String catSpecies;
	private String catGender;
	private int catAge;
	private String catSlave;

	public String getCatSlave() {
		return catSlave;
	}

	public void setCatSlave(String catSlave) {
		this.catSlave = catSlave;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatSpecies(String catSpecies) {
		this.catSpecies = catSpecies;
	}

	public String getCatSpecies() {
		return catSpecies;
	}

	public void setCatGender(String catGender) {
		this.catGender = catGender;
	}

	public String getCatGender() {
		return catGender;
	}

	public void setCatAge(int catAge) {
		this.catAge = catAge;
	}

	public int getCatAge() {
		return catAge;
	}

	@Override
	public String toString() {
		return "CatVO [catName=" + catName + ", catSpecies=" + catSpecies + ", catGender=" + catGender + ", catAge="
				+ catAge + ", catSlave=" + catSlave + "]";
	}

}
