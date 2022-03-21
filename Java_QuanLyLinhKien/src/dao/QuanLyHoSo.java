package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connect.DatabaseConnect;
import entity.NhanVien;

public class QuanLyHoSo {
private NhanVien nhanVien;
	
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setnhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public void getProfile() throws SQLException{
		
		Connection con = DatabaseConnect.getConnection();
		String userName = DatabaseConnect.userName;
		String query = "Select * from nhanvien where manv = '" + userName + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
			
		while(result.next()) {
			String manv = result.getString("manv");
			String hoten = result.getString("hotennv");
			boolean gioitinh = result.getBoolean("gioitinh");
			String diachi = result.getString("diachi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			boolean chucvu = result.getBoolean("quanlyvien");
				
			nhanVien = new NhanVien(manv, hoten, gioitinh, diachi, sdt, email, chucvu);
		}
		
		con.close();
	}
	
	public boolean modifiedProfile(NhanVien nv) throws SQLException{
			Connection con = DatabaseConnect.getConnection();
			String userName = DatabaseConnect.userName;
			String query = "update nhanvien set hotennv = ?, gioitinh = ?,diachi = ?,sdt = ?,email = ?,quanlyvien = ? where manv = '"
	                + userName + "'" ;
			PreparedStatement stmt = con.prepareStatement(query);
			 stmt.setString(1,nv.getHoTenNV());
	         stmt.setBoolean(2,nv.isGioiTinh());
	         stmt.setString(3,nv.getDiaChi());
	         stmt.setString(4,nv.getSDT().toString());
	         stmt.setString(5,nv.getEmail().toString());
	         stmt.setBoolean(6,nv.isQuanLyVien());
	         int n = stmt.executeUpdate();
	         if(n>0) {
	        	 con.close();
	             return true;
	         }
	    con.close();
		return false;
	}
}
