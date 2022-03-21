package entity;

public class ChiTietHD {
	private int soLuong;
	private double donGia;
	private HoaDon hoaDon;
	private LinhKien linhKien;
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public LinhKien getLinhKien() {
		return linhKien;
	}
	public void setLinhKien(LinhKien linhKien) {
		this.linhKien = linhKien;
	}
	public ChiTietHD() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietHD(int soLuong, double donGia, HoaDon hoaDon, LinhKien linhKien) {
		super();
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.hoaDon = hoaDon;
		this.linhKien = linhKien;
	}
	
	public double thanhTien() {
		return soLuong * donGia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(donGia);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
		result = prime * result + ((linhKien == null) ? 0 : linhKien.hashCode());
		result = prime * result + soLuong;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHD other = (ChiTietHD) obj;
		if (Double.doubleToLongBits(donGia) != Double.doubleToLongBits(other.donGia))
			return false;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		if (linhKien == null) {
			if (other.linhKien != null)
				return false;
		} else if (!linhKien.equals(other.linhKien))
			return false;
		if (soLuong != other.soLuong)
			return false;
		return true;
	}
	
	
}
