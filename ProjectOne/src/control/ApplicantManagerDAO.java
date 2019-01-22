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
	// 지원자 정보 DB 저장
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
				alert.setTitle("지원자등록");
				alert.setHeaderText("등록성공");
				alert.setContentText("등록에 성공 하였습니다.");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("지원자등록");
				alert.setHeaderText("등록실패");
				alert.setContentText("지원자 정보를 다시 기입 해 주세요.");
				alert.showAndWait();
			}

			reval = new ApplicantVO();

		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("지원자등록");
			alert.setHeaderText("등록실패");
			alert.setContentText("지원자 정보를 다시 기입 해 주세요.");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("지원자등록");
			alert.setHeaderText("등록실패");
			alert.setContentText("지원자 정보를 다시 기입 해 주세요.");
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

	// 지원자 정보 삭제 메소드
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
				alert.setTitle("삭제");
				alert.setHeaderText("삭제성공");
				alert.setContentText("삭제에 성공 하였습니다.");
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

	// 지원자 정보 수정 메소드
	public ApplicantVO applicantUpdate(ApplicantVO avo, int no) {
		String sql = "update applicant set job=? ,name=? ,age=? ,height=? ,weight=? ,phonenumber=?,"
				+ "email=?, specialnote=?, employeenumber=? ,image=? where no=? ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ApplicantVO retval = null;

		try {

			con = DBUtil.getConnection();

			// 쿼리문에 값 넘겨주기
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
				alert.setTitle("수정");
				alert.setHeaderText("수정성공");
				alert.setContentText("수정에 성공 하였습니다.");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("수정");
				alert.setHeaderText("수정실패");
				alert.setContentText("수정에 실패 하였습니다.");
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

	// 이름으로 검색
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

	// 지원분야별  검색
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

	// 지원자 테이블 호출 메소드
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

	// DB 에서의 테이블 컬럼 개수
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
	
	// 사원번호로 검색
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
