package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connect.DatabaseConnect;

public class ThongKe {

	public ThongKe() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<DoanhThu> thongKeTheoThang(int thang, int nam) throws SQLException {		
		Connection conn = DatabaseConnect.getConnection();
		
		ArrayList<DoanhThu> list = new ArrayList<DoanhThu>();
		
		String query = "select * from ThongKeTheoThang(?, ?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, thang);
		pst.setInt(2, nam);
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			DoanhThu doanhThu = new DoanhThu();
			doanhThu.setMaNV(rs.getString("maNV"));
			doanhThu.setTenNV(rs.getString("tenNV"));
			doanhThu.setSoLKDaBan(rs.getInt("soLKDaBan"));
			doanhThu.setDoanhThu(rs.getDouble("doanhThu"));
			list.add(doanhThu);
		}
		
		conn.close();
		return list;
	}
	
	public ArrayList<DoanhThu> thongKeTheoNam(int nam) throws SQLException {		
		Connection conn = DatabaseConnect.getConnection();
		
		ArrayList<DoanhThu> list = new ArrayList<DoanhThu>();
		
		String query = "select * from ThongKeTheoNam(?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, nam);
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {;
			DoanhThu doanhThu = new DoanhThu();
			doanhThu.setMaNV(rs.getString("maNV"));
			doanhThu.setTenNV(rs.getString("tenNV"));
			doanhThu.setSoLKDaBan(rs.getInt("solkDaBan"));
			doanhThu.setDoanhThu(rs.getDouble("doanhThu"));
			list.add(doanhThu);
		}
		
		conn.close();
		return list;
	}

}
