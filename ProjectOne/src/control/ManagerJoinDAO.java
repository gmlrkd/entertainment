package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ManagerVO;

public class ManagerJoinDAO {

	// 관리자 신규 등록
	public ManagerVO getManagerList(ManagerVO mvo) {

		String sql = "insert into manager(no,m_id,m_password,m_name,position,employeenumber,m_phonenumber,m_email) values(manager_sqe.nextval,?,?,?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		ManagerVO retval = null;

		try {
			// 오라클 DB 연동
			con = DBUtil.getConnection();

			// 입력 정보 처리
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mvo.getM_id());
			pstmt.setString(2, mvo.getM_password());
			pstmt.setString(3, mvo.getM_name());
			pstmt.setString(4, mvo.getPosition());
			pstmt.setString(5, mvo.getEmployeenumber());
			pstmt.setString(6, mvo.getM_phonenumber());
			pstmt.setString(7, mvo.getM_email());

			// 수행결과 얻어오기
			int i = pstmt.executeUpdate();

			retval = new ManagerVO();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				} else if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("관리자 등록");
				alert.setHeaderText("관리자 등록 실패");
				alert.setContentText("정보를 모두 입력 해 주세요.");
				alert.show();
			}
		}
		return retval;
	}// 관리자 신규등록 메소드 종료
	
	//아이디 중복확인 메소드
	public boolean getIdOverlap(String idOverlep) throws Exception {
		String dml = "select * from manager where m_id = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean teacherSucess = false;
		try {
			// DBUtil 클라스의 getConnection 메서드로 데이터 베이스와 연결
			con = DBUtil.getConnection();

			// 입력받은 학생 정보를 처리하기위한 SQL 문 작성
			pstmt = con.prepareStatement(dml);
			
			pstmt.setString(1, idOverlep);
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				teacherSucess = true; // 중복된 아이디가 있다.
			}

		} catch (ClassNotFoundException e) {
			System.out.println("e={" + e + "}");
		} catch (SQLException e) {
			System.out.println("e={" + e + "}");
		} finally {
			// 데이터 베이스 연결 해제
			try {
				//순서를 외우자
				if (rs != null) {
					rs.close();
				} else if (pstmt != null) {
					pstmt.close();
				} else if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			}
		}
		// 결과 출력
		return teacherSucess;
	}

	
}
