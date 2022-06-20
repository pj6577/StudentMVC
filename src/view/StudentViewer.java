package view;

import java.util.Scanner;
import java.util.ArrayList;


import controller.StudentController;
import model.StudentDTO;
import util.ScannerUtil;

public class StudentViewer {
	private StudentController controller;
	private Scanner scanner;
	

	public StudentViewer() {
		controller = new StudentController();
		scanner = new Scanner(System.in);
	}

	public void showMenu() {
		while (true) {
			String message = "1. 학생 등록 2. 학생 출력 3.종료";
			int userChoice = ScannerUtil.nextInt(scanner, message);
			if (userChoice == 1) {
				studentInsert();
			} else if (userChoice == 2) {
				studentPrint();
			} else if (userChoice == 3) {
				System.out.println("종료");
				break;
			}
		}
	}

	private void studentInsert() {
		StudentDTO s = new StudentDTO();

		String message = "학생의 이름을 입력해주세요";
		s.setName(ScannerUtil.nextLine(scanner, message));

		message = "국어 성적을 입력해주세요 ";
		s.setKor(ScannerUtil.nextInt(scanner, message));

		message = "영어 성적을 입력해주세요";
		s.setEng(ScannerUtil.nextInt(scanner, message));

		message = "수학 성적을 입력해주세요";
		s.setMat(ScannerUtil.nextInt(scanner, message));

		controller.insert(s);
	}

	private void studentPrint() {
		ArrayList<StudentDTO> list = controller.selectAll();

		if (list.isEmpty()) {
			System.out.println("등록된 학생이 없습니다");
		} else {
			for (StudentDTO s : list) {
				System.out.printf("%d. %s\n", s.getId(), s.getName());
			}
			String message = "상세보기할 학생의 번호나 뒤로 가려면 0를 입력해주세요";
			int userChoice = ScannerUtil.nextInt(scanner, message);

			while (userChoice != 0 && controller.selectOne(userChoice) == null) {
				System.out.println("잘못 입력하셨습니다.");
				userChoice = ScannerUtil.nextInt(scanner, message);
			}

			if (userChoice != 0) {
				printOne(userChoice);
			}
		}
	}

	private void printOne(int id) {
		StudentDTO s = controller.selectOne(id);
		System.out.println("---------------");
		System.out.println("번호 :" + s.getId());
		System.out.println("이름 :" + s.getName());
		System.out.println("---------------");
		System.out.println("국어 점수 : " + s.getKor() + "점  영어 점수 :  " + s.getEng() + "점 수학 점수 : " + s.getMat() + " 점");
		int total = s.getKor() + s.getEng() + s.getMat();
		double avg = (double)total/3;
		System.out.println("총점 : " + total  + "점 평균 : " + avg + " 점");
		
		String message = "1 . 수정 2. 삭제 3. 뒤로가기";
		int userChoice = ScannerUtil.nextInt(scanner, message);
		if(userChoice == 1 ) {
			update(id);
		} else if (userChoice == 2) {
			remove(id);
		} else if (userChoice == 3 ) {
			studentPrint();
		}

	}

	private void update(int id) {
		StudentDTO s = controller.selectOne(id);
		System.out.println(	s.getName() + "님의 수정 페이지" );
	
		String message = "국어 점수 수정";
		s.setKor(ScannerUtil.nextInt(scanner, message));
		
		message = "영어 점수 수정";
		s.setEng(ScannerUtil.nextInt(scanner, message));
		
		message = "수학 점수 수정";
		s.setMat(ScannerUtil.nextInt(scanner, message));
		
		controller.update(s);
		
	}
	private void remove(int id ) {
		String yesNo = "정말 삭제 하시겠습니까 Y/N";
		if(yesNo.equalsIgnoreCase("Y")) {
			controller.remove(id);
			studentPrint();
		}else {
			printOne(id);
		}
	}

}
