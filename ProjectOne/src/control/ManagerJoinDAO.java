package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ManagerVO;

public class ManagerJoinDAO {

	// ������ �ű� ���
	public ManagerVO getManagerList(ManagerVO mvo) {

		String sql = "insert into manager(no,m_id,m_password,m_name,position,employeenumber,m_phonenumber,m_email) values(manager_sqe.nextval,?,?,?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		ManagerVO retval = null;

		try {
			// ����Ŭ DB ����
			con = DBUtil.getConnection();

			// �Է� ���� ó��
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mvo.getM_id());
			pstmt.setString(2, mvo.getM_password());
			pstmt.setString(3, mvo.getM_name());
			pstmt.setString(4, mvo.getPosition());
			pstmt.setString(5, mvo.getEmployeenumber());
			pstmt.setString(6, mvo.getM_phonenumber());
			pstmt.setString(7, mvo.getM_email());

			// ������ ������
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
				alert.setTitle("������ ���");
				alert.setHeaderText("������ ��� ����");
				alert.setContentText("������ ��� �Է� �� �ּ���.");
				alert.show();
			}
		}
		return retval;
	}// ������ �űԵ�� �޼ҵ� ����
	
	//���̵� �ߺ�Ȯ�� �޼ҵ�
	public boolean getIdOverlap(String idOverlep) throws Exception {
		String dml = "select * from manager where m_id = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean teacherSucess = false;
		try {
			// DBUtil Ŭ���� getConnection �޼���� ������ ���̽��� ����
			con = DBUtil.getConnection();

			// �Է¹��� �л� ������ ó���ϱ����� SQL �� �ۼ�
			pstmt = con.prepareStatement(dml);
			
			pstmt.setString(1, idOverlep);
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				teacherSucess = true; // �ߺ��� ���̵� �ִ�.
			}

		} catch (ClassNotFoundException e) {
			System.out.println("e={" + e + "}");
		} catch (SQLException e) {
			System.out.println("e={" + e + "}");
		} finally {
			// ������ ���̽� ���� ����
			try {
				//������ �ܿ���
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
		// ��� ���
		return teacherSucess;
	}

	
}
