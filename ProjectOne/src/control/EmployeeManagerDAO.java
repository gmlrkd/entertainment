package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ManagerVO;

public class EmployeeManagerDAO {
	// �����޼ҵ�
	public ManagerVO Edite(ManagerVO mvo, int no) {
		String sql = "update manager set m_name = ?,employeenumber=?,position=?,m_phonenumber=?,m_email=?,m_id=?,m_password=?  where no=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ManagerVO reval = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mvo.getM_name());
			pstmt.setString(2, mvo.getEmployeenumber());
			pstmt.setString(3, mvo.getPosition());
			pstmt.setString(4, mvo.getM_phonenumber());
			pstmt.setString(5, mvo.getM_email());
			pstmt.setString(6, mvo.getM_id());
			pstmt.setString(7, mvo.getM_password());
			pstmt.setInt(8, no);

			System.out.println( mvo.getM_name());
			System.out.println(mvo.getM_password());
			System.out.println(no);
			pstmt.executeQuery();

			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����");
				alert.setHeaderText("��������");
				alert.setContentText("������ ���� �Ͽ����ϴ�.");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����");
				alert.setHeaderText("��������");
				alert.setContentText("������ ���� �Ͽ����ϴ�.");
				alert.showAndWait();
			}

			reval = new ManagerVO();

		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("����");
			alert.setHeaderText("��������");
			alert.setContentText("������ ���� �Ͽ����ϴ�.");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("����");
			alert.setHeaderText("��������");
			alert.setContentText("������ ���� �Ͽ����ϴ�.");
			alert.showAndWait();
		}
		return reval;
	}

	public void DeleteEmployee(int no) {
		String sql = "delete from manager where no=?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeQuery();
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����");
				alert.setHeaderText("��������");
				alert.setContentText("������ ���� �Ͽ����ϴ�.");
				alert.showAndWait();
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("����");
			alert.setHeaderText("��������");
		   alert.setContentText("�ش� ������ �����ȣ�� ��ϵ� �����ڰ� �ֽ��ϴ�.");
		   alert.show();
		}
	}

	public ArrayList<ManagerVO> getEmployee() {
		ArrayList<ManagerVO> list = new ArrayList<>();
		String sql = "select * from manager";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagerVO mvo = null;

		try {
			// DB�� Connect
			con = DBUtil.getConnection();

			// ��ɹ� ����
			pstmt = con.prepareStatement(sql);
			// ��� ���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mvo = new ManagerVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
				list.add(mvo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return list;
	}

	// DB ������ ���̺� �÷� ����
	public ArrayList<String> columnName() {
		ArrayList<String> columnName = new ArrayList<>();
		String sql = "select * from manager";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				} else if (con != null) {
					con.close();
				}
			} catch (Exception e) {
			}
		}
		return columnName;
	}

}
