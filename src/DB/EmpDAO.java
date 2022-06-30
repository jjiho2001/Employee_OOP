package DB;

import java.util.ArrayList;
import java.util.List;

public class EmpDAO extends DBConnection{

	public EmpDAO() {
		
	}

	public static EmpDAO getInstance() {
		return new EmpDAO();
	}
	
	
	// 회원 검색
	public List<EmpVO> empSelect(String username) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			getConnection();
			
			sql = "select mem_id, username, depart, phone, email from member ";
			if(username != null) {
				sql += "where username like ? " ;
			}
				sql += "order by mem_id";
			pstmt = conn.prepareStatement(sql);
			
			if(username != null) {
				pstmt.setString(1, "%" + username + "%");
			}
			System.out.println(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 회원을 VO에 담기
				EmpVO vo = new EmpVO();
				vo.setMem_id(rs.getInt(1));
				vo.setUsername(rs.getString(2));
				vo.setDepart(rs.getString(3));
				vo.setPhone(rs.getString(4));
				vo.setEmail(rs.getString(5));
				
				// VO를 ArrayList에 담가
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose();
		}
		return list;
	}
	
	// 회원 등록
	public int empInsert(EmpVO vo) {
		int result = 0;
		try {
			getConnection();
			
			sql = "insert into member(mem_id, username, depart, phone, email) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMem_id());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getDepart());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose();
		}
		
		return result;
	}
	
	// 회원 수정
	public int empUpdate(EmpVO vo, String input) {
		int result = 0;
		try {
			getConnection();
			
			sql = "update member set " + vo.getFieldName() + " = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, vo.getFieldName());
			pstmt.setString(1, input);
			pstmt.setInt(2, vo.getMem_id());
			
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose();
		}
		
		return result;
	}
	
	// 회원 삭제
	public int empDelete(int mem_id) {
		int result = 0;
		
		try {
			getConnection();
			
			sql = "delete from member where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose();
		}
		return result;
	}

}
