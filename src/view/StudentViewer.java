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
			String message = "1. �л� ��� 2. �л� ��� 3.����";
			int userChoice = ScannerUtil.nextInt(scanner, message);
			if (userChoice == 1) {
				studentInsert();
			} else if (userChoice == 2) {
				studentPrint();
			} else if (userChoice == 3) {
				System.out.println("����");
				break;
			}
		}
	}

	private void studentInsert() {
		StudentDTO s = new StudentDTO();

		String message = "�л��� �̸��� �Է����ּ���";
		s.setName(ScannerUtil.nextLine(scanner, message));

		message = "���� ������ �Է����ּ��� ";
		s.setKor(ScannerUtil.nextInt(scanner, message));

		message = "���� ������ �Է����ּ���";
		s.setEng(ScannerUtil.nextInt(scanner, message));

		message = "���� ������ �Է����ּ���";
		s.setMat(ScannerUtil.nextInt(scanner, message));

		controller.insert(s);
	}

	private void studentPrint() {
		ArrayList<StudentDTO> list = controller.selectAll();

		if (list.isEmpty()) {
			System.out.println("��ϵ� �л��� �����ϴ�");
		} else {
			for (StudentDTO s : list) {
				System.out.printf("%d. %s\n", s.getId(), s.getName());
			}
			String message = "�󼼺����� �л��� ��ȣ�� �ڷ� ������ 0�� �Է����ּ���";
			int userChoice = ScannerUtil.nextInt(scanner, message);

			while (userChoice != 0 && controller.selectOne(userChoice) == null) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
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
		System.out.println("��ȣ :" + s.getId());
		System.out.println("�̸� :" + s.getName());
		System.out.println("---------------");
		System.out.println("���� ���� : " + s.getKor() + "��  ���� ���� :  " + s.getEng() + "�� ���� ���� : " + s.getMat() + " ��");
		int total = s.getKor() + s.getEng() + s.getMat();
		double avg = (double)total/3;
		System.out.println("���� : " + total  + "�� ��� : " + avg + " ��");
		
		String message = "1 . ���� 2. ���� 3. �ڷΰ���";
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
		System.out.println(	s.getName() + "���� ���� ������" );
	
		String message = "���� ���� ����";
		s.setKor(ScannerUtil.nextInt(scanner, message));
		
		message = "���� ���� ����";
		s.setEng(ScannerUtil.nextInt(scanner, message));
		
		message = "���� ���� ����";
		s.setMat(ScannerUtil.nextInt(scanner, message));
		
		controller.update(s);
		
	}
	private void remove(int id ) {
		String yesNo = "���� ���� �Ͻðڽ��ϱ� Y/N";
		if(yesNo.equalsIgnoreCase("Y")) {
			controller.remove(id);
			studentPrint();
		}else {
			printOne(id);
		}
	}

}
