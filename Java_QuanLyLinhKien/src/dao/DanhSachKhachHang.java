package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.DatabaseConnect;
import entity.KhachHang;

public class DanhSachKhachHang {
	private Connection con;
	private ArrayList<KhachHang> dsKH;
	
	public DanhSachKhachHang() {
	}
	public ArrayList<KhachHang> getDsKH() {
		return dsKH;
	}
	public List<KhachHang> getAll() throws SQLException {
		con = DatabaseConnect.getConnection();

		List<KhachHang> dskh = new ArrayList<KhachHang>();
		String query = "select * from khachhang";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			String maKH =  result.getString("makh");
			String hoTen = result.getString("tenKh");
			boolean gioiTinh = result.getBoolean("gioitinh");
			String diaChi = result.getString("diachi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, diaChi, sdt, email);
			dskh.add(kh);
		}
		con.close();
		return dskh;

	}

	
	public boolean themKhachHang(KhachHang kh) throws SQLException {
		con = DatabaseConnect.getConnection();
		String sql = "insert into khachhang values(?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getTenKH());
			stmt.setString(4, kh.getDiaChi());
			stmt.setBoolean(3, kh.isGioiTinh());
			stmt.setString(6, kh.getEmail());
			stmt.setString(5, kh.getSdt());
			
			int n = stmt.executeUpdate();
			if(n > 0) {
				con.close();
				return true;
			}
		con.close();
		return false;
	}
	

	
	public boolean xoaKhachHang(KhachHang kh) throws SQLException {
		con = DatabaseConnect.getConnection();
		try {
			String sql = "delete from khachhang where makh = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			int n = stmt.executeUpdate();
			if(n > 0) {
				con.close();
				return true;
			}
				
		}catch (SQLException e) {
			throw new SQLException(e);
		}
		con.close();
		return false;
	}
	
	public KhachHang timKHTheoMa(String maKH) throws SQLException {
		Connection con = DatabaseConnect.getConnection();
		
		String query = "select * from khachhang where makh = '" + maKH + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		if (result.next()) {
			String ma =  result.getString("makh");
			String hoTen = result.getString("tenKh");
			String diaChi = result.getString("diachi");
			boolean gioiTinh = result.getBoolean("gioitinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			
			KhachHang kh = new KhachHang(ma, hoTen, gioiTinh, diaChi, sdt, email);
			con.close();
			return kh;
		} else {
			con.close();
			return null;
		}
	}
	
	public boolean suaTTKhachHang(KhachHang kh) throws SQLException {
		con = DatabaseConnect.getConnection();
		
			String sql = "update khachhang set makh = ?, tenKh = ?, gioitinh = ?, diachi = ?, sdt = ?, email = ? where makh = '" + kh.getMaKH() + "'";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getTenKH());
			stmt.setString(4, kh.getDiaChi());
			stmt.setBoolean(3, kh.isGioiTinh());
			stmt.setString(6, kh.getEmail());
			stmt.setString(5, kh.getSdt());
			
			int n = stmt.executeUpdate();
			if(n > 0) {
				con.close();
				return true;
			}
			con.close();	
			return false;
		
	}
	
	public ArrayList<KhachHang> layDanhSach() {
		return dsKH;
	}

	@Override
	public String toString() {
		return "DanhSachKhachHang [dsKH=" + dsKH + "]";
	}
	
	public KhachHang timTheoMa(String ma) throws SQLException{
		con = DatabaseConnect.getConnection();
		
		KhachHang kh;
		String query = "select * from khachhang where makh = '" + ma + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		if (result.next()) {
			String maa =  result.getString("makh");
			String hoTen = result.getString("tenkh");
			String diaChi = result.getString("diachi");
			boolean gioiTinh = result.getBoolean("gioitinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			
			kh = new KhachHang(maa, hoTen, gioiTinh, diaChi, sdt, email);
			con.close();
			return kh;
		} else {
			con.close();
			return null;
		}
	}
	
	public List<KhachHang> timTheoTenKhachHang(String tenKH) throws SQLException{
		con = DatabaseConnect.getConnection();
		
		List<KhachHang> dskh = new ArrayList<KhachHang>();
			String query = "select * from khachhang where tenKh like " + "N'%" + tenKH + "%'";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()) {
				String maa =  result.getString("makh");
				String hoTen = result.getString("tenKh");
				String diaChi = result.getString("diachi");
				boolean gioiTinh = result.getBoolean("gioitinh");
				String email = result.getString("email");
				String sdt = result.getString("sdt");
				
				KhachHang kh = new KhachHang(maa, hoTen, gioiTinh, diaChi, sdt, email);
				dskh.add(kh);	
			}
			
			
			if(dskh.size() > 0) {
				con.close();
				return dskh;
			}
				
			else {
				con.close();
				return null;
			}

		
	}
	
	
}
