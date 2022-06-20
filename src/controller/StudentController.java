package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.StudentDTO;

public class StudentController {
	ArrayList <StudentDTO> list;
	Scanner scanner = new Scanner(System.in);
	int nextId;
	
	public StudentController() {
		list = new ArrayList<>();
		nextId =1;
	}
	
	public void insert(StudentDTO s) {
	
		s.setId(nextId++);
		list.add(s);
	}
	

    public ArrayList<StudentDTO> selectAll() {
        ArrayList<StudentDTO> temp = new ArrayList<>();
     
        for(StudentDTO s : list) {
            temp.add(new StudentDTO(s));
        }
        
        return temp;
    }
  
    public StudentDTO selectOne(int id) {
        for(StudentDTO s : list) {
            if(s.getId() == id) {
                return new StudentDTO(s);
            }
        }
        
        return null;
    }
    
    public void update(StudentDTO s) {
    	int index = list.indexOf(s);
    	list.set(index, s);
    }
	
	public void remove(int id) {
		list.remove(new StudentDTO(id));
	}
}
