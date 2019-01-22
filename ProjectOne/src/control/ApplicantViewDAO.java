package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.chart.BarChart;
import model.ApplicantVO;

public class ApplicantViewDAO {

	// ���̺� �ҷ�����
	public ArrayList<ApplicantVO> getApplicantView() {
		String sql = "select * from applicant";
		ArrayList<ApplicantVO> list = new ArrayList<>();
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
		return list;
	}

	// �� �ο��� �޼ҵ�
	public int pieChartModel() {
		String sql = "select count(name) from applicant group by list having list = '��' ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count(name)");
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
		return count;
	}

	// DJ �ο� �� �޼ҵ�
	public int pieChartDJ() {
		String sql = "select count(name) from applicant group by list having list = 'DJ' ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count(name)");
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
		return count;
	}

	// �����ռ� �ο��� �޼ҵ�
	public int pieChartPerformance() {
		String sql = "select count(name) from applicant group by list having list = '�����ռ�' ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count(name)");
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
		return count;
	}

	// �̸����� �˻�
	public ApplicantVO searchName(String name) {
		String sql = "select * from applicant  where name like ? "; // ���ϵ� ī��� ��� �Ұ��� �Ѱ�....?
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

	// ���� �оߺ� �˻�
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

	// ��� �Ⱓ�� ������ �˻�
	public ApplicantVO searchDate(String startday, String andDay) {
		String sql = "select * from applicant "
				+ "where day between to_date(?, 'yyyy-mm-dd') and to_date(?, 'yyyy-mm-dd')";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ApplicantVO avo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, startday);
			pstmt.setString(2, andDay);
			pstmt.executeQuery();
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
