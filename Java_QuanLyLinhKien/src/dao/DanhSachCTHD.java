package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connect.DatabaseConnect;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.LinhKien;

public class DanhSachCTHD {
	private LinhKien getLinhKien(Connection con, String maLinhKien) throws SQLException {
		String query = "select * from linhkien where malk = '" + maLinhKien + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		if (result.next()) {
			String maLK = result.getString("malk");
			String tenLK = result.getString("tenlk");
			String loaiLK = result.getString("loailk");
			String thuongHieu = result.getString("thuonghieu");
			String nuocSX = result.getString("nuocsanxuat");
			int soLuongTon = result.getInt("soluongton");
			double donGia = result.getDouble("dongia");
			int baoHanh = result.getInt("baohanh");

			LinhKien lk = new LinhKien(maLK, tenLK, loaiLK, thuongHieu, nuocSX, soLuongTon, donGia, baoHanh);

			return lk;
		} else
			return null;
	}

	private HoaDon getHoaDon(Connection con, String maHoaDon) throws SQLException {
		String query = "select * from hoadon where mahd = '" + maHoaDon + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		String maKH = "";
		String maNV = "";
		LocalDate ngayLap = null;

		if (result.next()) {
			maKH = result.getString(2);
			maNV = result.getString(3);
			ngayLap = LocalDate.parse(result.getDate(4).toString());
		}

		NhanVien nv = getNhanVien(con, maNV);
		KhachHang kh = getKhachHang(con, maKH);

		HoaDon hd = new HoaDon(maHoaDon, nv, kh, ngayLap);

		return hd;
	}

	public KhachHang getKhachHang(Connection conn, String maKH) throws SQLException {
		String query = "select * from khachhang where makh = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, maKH);
		ResultSet result = stmt.executeQuery();

		if (result.next()) {
			String hoTen = result.getString("tenKh");
			String diaChi = result.getString("diachi");
			boolean gioiTinh = result.getBoolean("gioitinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");

			KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, diaChi, sdt, email);
			return kh;
		} else
			return null;
	}

	public NhanVien getNhanVien(Connection conn, String maNhanVien) throws SQLException {
		String query = "select * from nhanvien where manv = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, maNhanVien);

		ResultSet result = stmt.executeQuery();

		if (result.next()) {
			String maNV = result.getString("manv");
			String hoTen = result.getString("hotennv");
			boolean gioiTinh = result.getBoolean("gioitinh");
			String diaChi = result.getString("diachi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			boolean quanLyVien = result.getBoolean("quanlyvien");

			NhanVien nv = new NhanVien(maNV, hoTen, gioiTinh, diaChi, sdt, email, quanLyVien);
			return nv;
		} else
			return null;
	}

	public ArrayList<ChiTietHD> timTheoMaHD(String ma) throws SQLException {
		ArrayList<ChiTietHD> dsCTHD = new ArrayList<ChiTietHD>();
		Connection conn = null;

		conn = DatabaseConnect.getConnection();

		String query = "select * from CT_hoadon where mahd = ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, ma);

		ResultSet result = pst.executeQuery();

		ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();

		String maHD = "";
		String maLK = "";
		while (result.next()) {
			ArrayList<Object> l = new ArrayList<Object>();
			maHD = result.getString(1);
			maLK = result.getString(2);
			int soLuong = result.getInt(3);
			double donGia = result.getDouble(4);

			l.add(maHD);
			l.add(maLK);
			l.add(soLuong);
			l.add(donGia);

			list.add(l);
		}

		HoaDon hd = getHoaDon(conn, ma);

		for (ArrayList<Object> arrayList : list) {
			ChiTietHD cthd = new ChiTietHD();
			LinhKien xm = getLinhKien(conn, (String) arrayList.get(1));

			cthd.setHoaDon(hd);
			cthd.setLinhKien(xm);
			cthd.setSoLuong((int) arrayList.get(2));
			cthd.setDonGia((double) arrayList.get(3));

			dsCTHD.add(cthd);
		}

		conn.close();
		return dsCTHD;
	}

	public boolean themCTHD(ChiTietHD cthd) throws SQLException {
		Connection conn = DatabaseConnect.getConnection();

		String query = "select * from CT_hoadon where mahd = ? and malk = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, cthd.getHoaDon().getMaHD());
		pst.setString(2, cthd.getLinhKien().getMaLK());

		ResultSet rs = pst.executeQuery();
		if (rs.next())
			return false;

		query = "insert into CT_hoadon values(?, ?, ?, ?)";
		pst = conn.prepareStatement(query);
		pst.setString(1, cthd.getHoaDon().getMaHD());
		pst.setString(2, cthd.getLinhKien().getMaLK());
		pst.setInt(3, cthd.getSoLuong());
		pst.setDouble(4, cthd.getDonGia());

		pst.execute();

		conn.close();
		return true;
	}

	public boolean xoaCTHD(String maHD, String maLK) throws SQLException {
		Connection conn = DatabaseConnect.getConnection();

		String query = "select * from CT_hoadon where mahd = ? and malk = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, maHD);
		pst.setString(2, maLK);

		ResultSet rs = pst.executeQuery();

		if (!rs.next())
			return false;

		query = "delete from CT_hoadon where mahd = ? and malk = ?";
		pst = conn.prepareStatement(query);
		pst.setString(1, maHD);
		pst.setString(2, maLK);

		pst.execute();

		conn.close();
		return true;
	}

	public boolean suaCTHD(String maHD, String maLK, ChiTietHD cthdMoi) throws SQLException {
		Connection conn = DatabaseConnect.getConnection();

		String query = "select * from CT_hoadon where mahd = ? and malk= ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, maHD);
		pst.setString(2, maLK);

		ResultSet rs = pst.executeQuery();

		if (!rs.next())
			return false;
		
		query = "update CT_hoadon set soLuong = ?, donGia = ?";
		pst = conn.prepareStatement(query);
		pst.setInt(1, cthdMoi.getSoLuong());
		pst.setDouble(2, cthdMoi.getDonGia());
		
		pst.execute();
		
		conn.close();
		return true;
	}
}
