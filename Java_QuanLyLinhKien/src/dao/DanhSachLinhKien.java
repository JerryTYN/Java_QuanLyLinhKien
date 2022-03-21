package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connect.DatabaseConnect;
import entity.LinhKien;

public class DanhSachLinhKien {
	private Connection conn;

	public DanhSachLinhKien() {
		try {
			conn = DatabaseConnect.getConnection();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể kết nối!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public List<LinhKien> getAll() throws SQLException {
		List<LinhKien> dsLK = new ArrayList<LinhKien>();

		String query = "select * from linhkien";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maLK = result.getString("malk");
			String tenLK = result.getString("tenlk");
			String loaiLK = result.getString("loailk");
			String thuongHieu = result.getString("thuonghieu");
			String nuocSX = result.getString("nuocsanxuat");
			int soLuongTon = Integer.parseInt(result.getString("soluongton"));
			double donGia = result.getDouble("dongia");
			int baoHanh = Integer.parseInt(result.getString("baohanh"));

			LinhKien lk = new LinhKien(maLK, tenLK, loaiLK, thuongHieu, nuocSX, soLuongTon, donGia, baoHanh);
			dsLK.add(lk);
		}
		return dsLK;
	}

	public boolean themLinhKien(LinhKien lk) {
		String sql = "insert into linhkien values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, lk.getMaLK());
			stmt.setString(2, lk.getTenLK());
			stmt.setString(3, lk.getLoaiLK());
			stmt.setString(4, lk.getThuongHieu());
			stmt.setString(5, lk.getNuocSanXuat());
			stmt.setInt(6, lk.getSoLuongTon());
			stmt.setDouble(7, lk.getDonGia());
			stmt.setInt(8, lk.getBaoHanh());

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Trùng mã linh kiện!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		return false;
	}

	public boolean xoaLinhKien(LinhKien lk) throws SQLException {
		try {
			String sql = "delete from linhkien where malk = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, lk.getMaLK());
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return false;
	}

	public boolean suaTTLinhKien(LinhKien lk) throws SQLException {
		conn = DatabaseConnect.getConnection();
		try {
			String sql = "update linhkien set tenlk = ?, loailk = ?, thuonghieu = ?, nuocsanxuat = ?, soluongton = ?, dongia = ?,baohanh = ? where malk = '"
					+ lk.getMaLK() + "'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, lk.getTenLK());
			stmt.setString(2, lk.getLoaiLK());
			stmt.setString(3, lk.getThuongHieu());
			stmt.setString(4, lk.getNuocSanXuat());
			stmt.setInt(5, lk.getSoLuongTon());
			stmt.setDouble(6, lk.getDonGia());
			stmt.setInt(7, lk.getBaoHanh());

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		conn.close();
		return false;
	}



//	public List<LinhKien> timTheobaohanh(int baohanh) throws SQLException {
//		List<LinhKien> dslk = new ArrayList<LinhKien>();
//		
//		String query = "select * from LinhKien where baohanh <= " + baohanh;
//		Statement stmt = con.createStatement();
//		ResultSet result = stmt.executeQuery(query);
//
//		while (result.next()) {
//			String maLK = result.getString("maLK");
//			String tenLK = result.getString("tenLK");
//			String loaiLK = result.getString("loaiLK");
//			String thuongHieu = result.getString("thuongHieu");
//			int dt = Integer.parseInt(result.getString("baohanh"));
//			String mauxe = result.getString("mauXe");
//			String nuocsx = result.getString("nuocsanxuat");
//			int soluongton = Integer.parseInt(result.getString("soLuongTon"));
//			double dongia = result.getDouble("donGia");
//
//			LinhKien lk = new LinhKien(maLK, tenLK, loaiLK, thuongHieu, dt, mauxe, nuocsx, soluongton, dongia);
//			dslk.add(lk);
//		}
//		if (dslk.size() > 0)
//			return dslk;
//		else
//			return null;
//	}

	public List<LinhKien> timTheoTenLinhKien(String ten) throws SQLException {
		List<LinhKien> dsLK = new ArrayList<LinhKien>();

		String query = "select * from linhkien where tenlk like " + "N'%" + ten + "%'";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maLK = result.getString("malk");
			String tenLK = result.getString("tenlk");
			String loaiLK = result.getString("loailk");
			String thuongHieu = result.getString("thuonghieu");
			String nuocsx = result.getString("nuocsanxuat");
			int soluongton = Integer.parseInt(result.getString("soluongton"));
			double dongia = result.getDouble("dongia");
			int baoHanh = Integer.parseInt(result.getString("baohanh"));
			LinhKien lk = new LinhKien(maLK, tenLK, loaiLK, thuongHieu, nuocsx, soluongton, dongia, baoHanh);
			dsLK.add(lk);
		}
		if (dsLK.size() > 0)
			return dsLK;
		else
			return null;

	}
	
	public LinhKien timTheoMa(String ma) throws SQLException {
		conn = DatabaseConnect.getConnection();
		LinhKien lk = null;
		String query = "select * from linhkien where malk = '" + ma + "'";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		if (result.next()) {
			String maLK = result.getString("malk");
			String tenLK = result.getString("tenlk");
			String loaiLK = result.getString("loailk");
			String thuongHieu = result.getString("thuonghieu");
			String nuocsx = result.getString("nuocsanxuat");
			int soluongton = Integer.parseInt(result.getString("soluongton"));
			double dongia = result.getDouble("dongia");
			int baoHanh = Integer.parseInt(result.getString("baohanh"));

			lk = new LinhKien(maLK, tenLK, loaiLK, thuongHieu, nuocsx, soluongton, dongia, baoHanh);
			
		}
		conn.close();
		return lk;
	}
	
	public List<LinhKien> timTheoMaLinhKien(String ma) throws SQLException {
		List<LinhKien> dsLK = new ArrayList<LinhKien>();

		String query = "select * from linhkien where malk like " + "N'%" + ma + "%'";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maLK = result.getString("malk");
			String tenLK = result.getString("tenlk");
			String loaiLK = result.getString("loailk");
			String thuongHieu = result.getString("thuonghieu");
			String nuocsx = result.getString("nuocsanxuat");
			int soluongton = Integer.parseInt(result.getString("soluongton"));
			double dongia = result.getDouble("dongia");
			int baoHanh = Integer.parseInt(result.getString("baohanh"));
			LinhKien lk = new LinhKien(maLK, tenLK, loaiLK, thuongHieu, nuocsx, soluongton, dongia, baoHanh);
			dsLK.add(lk);
		}
		if (dsLK.size() > 0)
			return dsLK;
		else
			return null;

	}

	public List<LinhKien> timTheoThuongHieu(String th) throws SQLException {
		List<LinhKien> dsLK = new ArrayList<LinhKien>();
		
		String query = "select * from linhkien where thuonghieu = '" + th + "'";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maLK = result.getString("malk");
			String tenLK = result.getString("tenlk");
			String loaiLK = result.getString("loailk");
			String thuongHieu = result.getString("thuonghieu");
			String nuocsx = result.getString("nuocsanxuat");
			int soluongton = Integer.parseInt(result.getString("soluongton"));
			double dongia = result.getDouble("dongia");
			int baoHanh = Integer.parseInt(result.getString("baohanh"));
			LinhKien lk = new LinhKien(maLK, tenLK, loaiLK, thuongHieu, nuocsx, soluongton, dongia, baoHanh);
			dsLK.add(lk);
		}
		if (dsLK.size() > 0)
			return dsLK;
		else
			return null;
	}
}
