package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.DatabaseConnect;

public class QuanLyDangNhap {
	public static boolean laQuanLyVien() throws SQLException {
		Connection conn = DatabaseConnect.getConnection();

		String query = "select * from nhanvien where manv = ?";

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, DatabaseConnect.userName);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			if (rs.getBoolean("quanlyvien"))
				return true;
		}

		conn.close();

		return false;

	}
}
