package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.DatabaseConnect;
import entity.NhanVien;

public class DanhSachNhanVien {
	private ArrayList<NhanVien> dsNV;
	private Connection conn;
	
	public ArrayList<NhanVien> getDsNV() {
		return dsNV;
	}
	public void setDsNV(ArrayList<NhanVien> dsNV) {
		this.dsNV = dsNV;
	}

	public DanhSachNhanVien() {
		dsNV = new ArrayList<NhanVien>();
	}
	
	public void getAll() throws SQLException{
		conn = DatabaseConnect.getConnection();
		String query = "select * from nhanvien";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			String maNV = result.getString("manv");
			String hoTen = result.getString("hotennv");
			boolean gioiTinh = result.getBoolean("gioitinh");
			String diaChi = result.getString("diachi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			boolean quanLyVien = result.getBoolean("quanlyvien");
			
			NhanVien nv = new NhanVien(maNV, hoTen, gioiTinh, diaChi, sdt, email, quanLyVien);
			if(!dsNV.contains(nv)) {
				dsNV.add(nv);
			}
		}
		conn.close();
	}
	
	public boolean xoaNhanVien(String maNV) throws SQLException{
		conn = DatabaseConnect.getConnection();

		String query = "select * from nhanvien where manv = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, maNV);
		if (!st.executeQuery().next())
			return false;

		query = "delete from nhanvien where manv = ?";
		st = conn.prepareStatement(query);
		st.setString(1, maNV);
		st.execute();

		conn.close();

		return true;
	}
	
	public boolean themNhanVien(NhanVien nv) throws SQLException {
		conn = DatabaseConnect.getConnection();
		String sql = "insert into nhanvien values(?,?,?,?,?,?,?)";
		// Statement st = conn.createStatement();

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, nv.getMaNV());
		stmt.setString(2, nv.getHoTenNV());
		stmt.setBoolean(3, nv.isGioiTinh());
		stmt.setString(4, nv.getDiaChi());
		stmt.setString(5, nv.getSDT());
		stmt.setString(6, nv.getEmail());
		stmt.setBoolean(7, nv.isQuanLyVien());
		int n = stmt.executeUpdate();
		if (n > 0)
			return true;

		conn.close();

		return false;
	}
	
	public NhanVien timTheoMaNhanVien(String maNV) throws SQLException {
		conn = DatabaseConnect.getConnection();

		String query = "select * from nhanvien where manv = '" + maNV + "'";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		NhanVien nv = null;
		if (result.next()) {
			String ma = result.getString("manv");
			String hoTen = result.getString("hotennv");
			String diaChi = result.getString("diachi");
			boolean gioiTinh = result.getBoolean("gioitinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			boolean isManager = result.getBoolean("quanlyvien");

			nv = new NhanVien(ma, hoTen, gioiTinh, diaChi, sdt, email, isManager);

		}
		conn.close();
		return nv;
	}
	
	public ArrayList<NhanVien> timTheoTenNhanVien(String tenNV) throws SQLException {
		conn = DatabaseConnect.getConnection();
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();

		String query = "select * from nhanvien where hotennv like " + "N'%" + tenNV + "%'";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		while (result.next()) {
			String ma = result.getString("manv");
			String hoTen = result.getString("hotennv");
			String diaChi = result.getString("diachi");
			boolean gioiTinh = result.getBoolean("gioitinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			boolean isManager = result.getBoolean("quanlyvien");

			NhanVien nv = new NhanVien(ma, hoTen, gioiTinh, diaChi, sdt, email, isManager);
			dsNV.add(nv);

		}
		conn.close();
		if (dsNV.size() > 0)
			return dsNV;
		else
			return null;

	}
	
	public boolean suaTTNhanVien(NhanVien nv) throws SQLException {
		conn = DatabaseConnect.getConnection();
		try {
			String sql = "update nhanvien set hotennv = ?, gioitinh = ?,diachi = ?,sdt = ?,email = ?,quanlyvien = ? where manv = '"
					+ nv.getMaNV() + "'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nv.getHoTenNV());
			stmt.setBoolean(2, nv.isGioiTinh());
			stmt.setString(3, nv.getDiaChi());
			stmt.setString(4, nv.getSDT().toString());
			stmt.setString(5, nv.getEmail().toString());
			stmt.setBoolean(6, nv.isQuanLyVien());

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		conn.close();
		return false;
	}
	
	public ArrayList<NhanVien> layDanhSach() {
		return dsNV;
	}

	@Override
	public String toString() {
		return "DanhSachNhanVien{" + "dsNV=" + dsNV + '}';
	}
}
