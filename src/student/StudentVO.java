package student;

public class StudentVO {

	private int studentCode;
	private String studentName;
	private int studentGrade;
	private int studentClass;
	private String studentGender;
	
	public int getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(int studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(int studentGrade) {
		this.studentGrade = studentGrade;
	}


	public int getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(int studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}


	@Override
	public String toString() {
		return "StudentVO [studentCode=" + studentCode + ", studentName=" + studentName + ", studentGrade="
				+ studentGrade + ", studentClass=" + studentClass + ", studentGender=" + studentGender + "]";
	}
	
}
