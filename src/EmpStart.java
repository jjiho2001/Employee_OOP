import java.util.List;
import java.util.Scanner;

import DB.EmpDAO;
import DB.EmpVO;

public class EmpStart {

	Scanner s = new Scanner(System.in);
	EmpDAO dao = new EmpDAO();
	
	public EmpStart() {
		start();
	}
	
	void start() {
		
		do {
			try {
				String menu = menuShow();
				if(menu.equals("1")) { // ���
					empList();
				} else if(menu.equals("2")) { // ���
					empAdd();
				} else if(menu.equals("3")) { // ����
					empEdit();
				} else if(menu.equals("4")) { // ����
					empDel();
				} else if(menu.equals("5")) { // ����
					empSearch();
				} else if(menu.equals("6")){
					empClose();
				} else {
					System.out.println("�޴����� ��� �Ӹ�!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println();
			}
			
		} while (true);
	}
	
	// ��� ���
	void empPrint(List<EmpVO> list) {
		
		for(int i = 0; i < list.size(); i++) {
			EmpVO vo = list.get(i);
			System.out.printf("%6d %12s %10s %16s %20s\n", vo.getMem_id(), vo.getUsername(), vo.getDepart(), vo.getPhone(), vo.getEmail());
		}
	}
	// �˻�
	void empSearch() {
		System.out.print("�˻��� ��� : ");
		String username = s.nextLine();
		
		empPrint(dao.empSelect(username));
	}
	
	// ȸ�� ���� ����
	void empDel() {
		System.out.print("ȸ�� ��ȣ : ");
		int mem_id = Integer.parseInt(s.nextLine());
		
		int result = dao.empDelete(mem_id);
		
		if(result > 0) System.out.println("ȸ�� ���� �Ϸ�");
		else System.out.println("ȸ�� ���� ����");
	}
	
	// ȸ�� ���� ����
	void empEdit() {
		EmpVO vo = new EmpVO();
		
		System.out.print("������ ȸ����ȣ : ");
		vo.setMem_id(Integer.parseInt(s.nextLine()));
		
		System.out.print("������ �׸� ����[1.�̸�, 2.�μ���, 3.����ó, 4.email] : ");
		String editField = s.nextLine();
		
		if(editField.equals("1")) { 
			vo.setFieldName("username");
		}
		else if(editField.equals("2")) {
			vo.setFieldName("depart");
		}
		else if(editField.equals("3")) {
			vo.setFieldName("phone");
		}
		else if(editField.equals("4")) {
			vo.setFieldName("email");
		}
		System.out.print("�� �Է� : ");
		String input = s.nextLine();
		
		int cnt = dao.empUpdate(vo, input);
	
		if(cnt > 0) {
			System.out.println("���� �Ϸ�");
		} else {
			System.out.println("���� ����");
		}
	}
	// ȸ�� ���
	public void empAdd() {
		EmpVO vo = new EmpVO();
		
		System.out.print("ȸ����ȣ : ");
		vo.setMem_id(Integer.parseInt(s.nextLine()));
		System.out.print("�̸� : ");
		vo.setUsername(s.nextLine());
		System.out.print("�μ��� : ");
		vo.setDepart(s.nextLine());
		System.out.print("����ó : ");
		vo.setPhone(s.nextLine());
		System.out.print("email : ");
		vo.setEmail(s.nextLine());
		
		int cnt = dao.empInsert(vo);
		
		if(cnt > 0) {
			System.out.println("���� �Ϸ�");
		} else {
			System.out.println("��� ����");
		}
	}
	
	// ȸ�� ���
	void empList() {
		
		String username = null;
		empPrint(dao.empSelect(username));
	}
	
	void empClose() {
		System.exit(0);
	}
	
	String menuShow() {
		System.out.print("�޴� [1.���, 2.���, 3.����, 4.����, 5.�˻�, 6.����] : ");
		return s.nextLine();
	}
	
	public static void main(String[] args) {
		new EmpStart();

	}

}
