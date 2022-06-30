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
				if(menu.equals("1")) { // 목록
					empList();
				} else if(menu.equals("2")) { // 등록
					empAdd();
				} else if(menu.equals("3")) { // 수정
					empEdit();
				} else if(menu.equals("4")) { // 삭제
					empDel();
				} else if(menu.equals("5")) { // 종료
					empSearch();
				} else if(menu.equals("6")){
					empClose();
				} else {
					System.out.println("메뉴에서 골라 임마!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println();
			}
			
		} while (true);
	}
	
	// 목록 출력
	void empPrint(List<EmpVO> list) {
		
		for(int i = 0; i < list.size(); i++) {
			EmpVO vo = list.get(i);
			System.out.printf("%6d %12s %10s %16s %20s\n", vo.getMem_id(), vo.getUsername(), vo.getDepart(), vo.getPhone(), vo.getEmail());
		}
	}
	// 검색
	void empSearch() {
		System.out.print("검색할 사람 : ");
		String username = s.nextLine();
		
		empPrint(dao.empSelect(username));
	}
	
	// 회원 정보 삭제
	void empDel() {
		System.out.print("회원 번호 : ");
		int mem_id = Integer.parseInt(s.nextLine());
		
		int result = dao.empDelete(mem_id);
		
		if(result > 0) System.out.println("회원 삭제 완료");
		else System.out.println("회원 삭제 실패");
	}
	
	// 회원 정보 수정
	void empEdit() {
		EmpVO vo = new EmpVO();
		
		System.out.print("수정할 회원번호 : ");
		vo.setMem_id(Integer.parseInt(s.nextLine()));
		
		System.out.print("수정할 항목 선택[1.이름, 2.부서명, 3.연락처, 4.email] : ");
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
		System.out.print("값 입력 : ");
		String input = s.nextLine();
		
		int cnt = dao.empUpdate(vo, input);
	
		if(cnt > 0) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
	}
	// 회원 등록
	public void empAdd() {
		EmpVO vo = new EmpVO();
		
		System.out.print("회원번호 : ");
		vo.setMem_id(Integer.parseInt(s.nextLine()));
		System.out.print("이름 : ");
		vo.setUsername(s.nextLine());
		System.out.print("부서명 : ");
		vo.setDepart(s.nextLine());
		System.out.print("연락처 : ");
		vo.setPhone(s.nextLine());
		System.out.print("email : ");
		vo.setEmail(s.nextLine());
		
		int cnt = dao.empInsert(vo);
		
		if(cnt > 0) {
			System.out.println("동록 완료");
		} else {
			System.out.println("등록 실패");
		}
	}
	
	// 회원 목록
	void empList() {
		
		String username = null;
		empPrint(dao.empSelect(username));
	}
	
	void empClose() {
		System.exit(0);
	}
	
	String menuShow() {
		System.out.print("메뉴 [1.목록, 2.등록, 3.수정, 4.삭제, 5.검색, 6.종료] : ");
		return s.nextLine();
	}
	
	public static void main(String[] args) {
		new EmpStart();

	}

}
