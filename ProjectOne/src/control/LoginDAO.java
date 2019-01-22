package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginDAO {
	//�α���
	public boolean getLogin(String id, String password) {

		String sql = "select * from manager where m_id=? and m_password = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean login = false;

		try {
			con = DBUtil.getConnection();

			// �Է� �� ���̵�� ��й�ȣ�� ���� ������ ó���ϱ�����
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery(); // ó�� ���

			// ��ġ�ϴ�.
			if (rs.next()) {
				login = true;
			}
			

		} catch (ClassNotFoundException e) {
			System.err.println(e);
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				} else if (pstmt != null) {
					pstmt.close();
				} else if (con != null) {
					con.close();
				}
			} catch (Exception e) {
			}
		}
		return login;
	}
}
