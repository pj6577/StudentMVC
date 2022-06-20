package model;

public class StudentDTO {
	private String name;
	private int id;
	private int kor;
	private int eng;
	private int mat;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	
	public StudentDTO() {
	}
	public StudentDTO(int id) {
		
	}
	
	public StudentDTO(String name, int id, int kor, int eng, int mat) {
		this.name = new String();
		this.id = id;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
//	public StudentDTO(StudentDTO s) {
//		
//	}
	
	public StudentDTO(StudentDTO s) {
		this.name = s.name;
		this.id = s.id;
		this.kor = s.kor;
		this.eng = s.eng;
		this.mat = s.mat;
	}
	

	
	public boolean equals(Object o ) {
		if(o instanceof StudentDTO) {
			StudentDTO s = (StudentDTO)o;
			return id == s.id;
		}
		return false;
	}
	
	
}
