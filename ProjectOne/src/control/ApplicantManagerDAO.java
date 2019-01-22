package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ApplicantVO;

public class ApplicantManagerDAO {
	// ������ ���� DB ����
	public ApplicantVO applicant(ApplicantVO avo) {

		String sql = "insert into applicant values(applicant_sqe.nextval,?,?,?,?,?,?,?,?,?,?,sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;
		ApplicantVO reval = new ApplicantVO();
		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, avo.getJob());
			pstmt.setString(2, avo.getName());
			pstmt.setString(3, avo.getAge());
			pstmt.setString(4, avo.getHeight());
			pstmt.setString(5, avo.getWeight());
			pstmt.setString(6, avo.getPhoneNumber());
			pstmt.setString(7, avo.getEmail());
			pstmt.setString(8, avo.getSpecialnote());
			pstmt.setString(9, avo.getEmployeeNumber());
			pstmt.setString(10, avo.getImage());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�����ڵ��");
				alert.setHeaderText("��ϼ���");
				alert.setContentText("��Ͽ� ���� �Ͽ����ϴ�.");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("�����ڵ��");
				alert.setHeaderText("��Ͻ���");
				alert.setContentText("������ ������ �ٽ� ���� �� �ּ���.");
				alert.showAndWait();
			}

			reval = new ApplicantVO();

		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�����ڵ��");
			alert.setHeaderText("��Ͻ���");
			alert.setContentText("������ ������ �ٽ� ���� �� �ּ���.");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�����ڵ��");
			alert.setHeaderText("��Ͻ���");
			alert.setContentText("������ ������ �ٽ� ���� �� �ּ���.");
			alert.showAndWait();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				} else if (con != null) {
					con.close();
				}
			} catch (Exception e) {
			}
		}
		return reval;
	}

	// ������ ���� ���� �޼ҵ�
	public void deleteApplicant(int no) {
		String sql = "delete from applicant where no=?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

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
			System.out.println(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				} else if (con != null) {
					con.close();
				}
			} catch (Exception e) {
			}
		}
	}

	// ������ ���� ���� �޼ҵ�
	public ApplicantVO applicantUpdate(ApplicantVO avo, int no) {
		String sql = "update applicant set job=? ,name=? ,age=? ,height=? ,weight=? ,phonenumber=?,"
				+ "email=?, specialnote=?, employeenumber=? ,image=? where no=? ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ApplicantVO retval = null;

		try {

			con = DBUtil.getConnection();

			// �������� �� �Ѱ��ֱ�
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, avo.getJob());
			pstmt.setString(2, avo.getName());
			pstmt.setString(3, avo.getAge());
			pstmt.setString(4, avo.getHeight());
			pstmt.setString(5, avo.getWeight());
			pstmt.setString(6, avo.getPhoneNumber());
			pstmt.setString(7, avo.getEmail());
			pstmt.setString(8, avo.getSpecialnote());
			pstmt.setString(9, avo.getEmployeeNumber());
			pstmt.setString(10, avo.getImage());
			pstmt.setInt(11, no);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����");
				alert.setHeaderText("��������");
				alert.setContentText("������ ���� �Ͽ����ϴ�.");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("����");
				alert.setHeaderText("��������");
				alert.setContentText("������ ���� �Ͽ����ϴ�.");
				alert.showAndWait();
			}

			retval = new ApplicantVO();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				} else if (con != null) {
					con.close();
				}
			} catch (Exception e) {
			}
		}
		return retval;
	}

	// �̸����� �˻�
	public ApplicantVO searchName(String name) {
		String sql = "select * from applicant  where name like '%'||?||'%' ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ApplicantVO avo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				avo = new ApplicantVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12));
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
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
		return avo;
	}

	// �����оߺ�  �˻�
	public ApplicantVO searchjob(String job) {
		String sql = "select * from applicant where list = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ApplicantVO avo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, job);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				avo = new ApplicantVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12));
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
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
		return avo;
	}

	// ������ ���̺� ȣ�� �޼ҵ�
	public ArrayList<ApplicantVO> getAppcantTotal() {
		ArrayList<ApplicantVO> list = new ArrayList<>();
		String sql = "select * from applicant";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ApplicantVO avo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				avo = new ApplicantVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12));
				list.add(avo);
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
		return list;
	}

	// DB ������ ���̺� �÷� ����
	public ArrayList<String> columnName() {
		ArrayList<String> columnName = new ArrayList<>();
		String sql = "select * from applicant";
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
	
	// �����ȣ�� �˻�
		public ApplicantVO searchEmployeeNumber(String EmployeeNumber) {
			String sql = "select * from applicant  where employeenumber =?";
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ApplicantVO avo = null;

			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, EmployeeNumber);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					avo = new ApplicantVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
							rs.getString(11), rs.getString(12));
				}

			} catch (ClassNotFoundException e) {
				System.out.println(e);
			} catch (SQLException e) {
				System.out.println(e);
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
			return avo;
		}

}
