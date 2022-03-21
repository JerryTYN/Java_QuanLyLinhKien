package entity;

public class LinhKien {
	private String maLK;
	private String tenLK;
	private String loaiLK;
	private String thuongHieu;
	private String nuocSanXuat;
	private int soLuongTon;
	private double donGia;
	private int baoHanh;
	
	
	
	public LinhKien(String maLK) {
		super();
		this.maLK = maLK;
	}
	public LinhKien(String maLK, String tenLK, String loaiLK, String thuongHieu, String nuocSanXuat, int soLuongTon,
			double donGia, int baoHanh) {
		super();
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.loaiLK = loaiLK;
		this.thuongHieu = thuongHieu;
		this.nuocSanXuat = nuocSanXuat;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.baoHanh = baoHanh;
	}
	public LinhKien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaLK() {
		return maLK;
	}
	public void setMaLK(String maLK) {
		this.maLK = maLK;
	}
	public String getTenLK() {
		return tenLK;
	}
	public void setTenLK(String tenLK) {
		this.tenLK = tenLK;
	}
	public String getLoaiLK() {
		return loaiLK;
	}
	public void setLoaiLK(String loaiLK) {
		this.loaiLK = loaiLK;
	}
	public String getThuongHieu() {
		return thuongHieu;
	}
	public void setThuongHieu(String thuongHieu) {
		this.thuongHieu = thuongHieu;
	}
	public String getNuocSanXuat() {
		return nuocSanXuat;
	}
	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public int getBaoHanh() {
		return baoHanh;
	}
	public void setBaoHanh(int baoHanh) {
		this.baoHanh = baoHanh;
	}
	@Override
	public String toString() {
		return "LinhKien [maLK=" + maLK + ", tenLK=" + tenLK + ", loaiLK=" + loaiLK + ", thuongHieu=" + thuongHieu
				+ ", nuocSanXuat=" + nuocSanXuat + ", soLuongTon=" + soLuongTon + ", donGia=" + donGia + ", baoHanh="
				+ baoHanh + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLK == null) ? 0 : maLK.hashCode());
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
		LinhKien other = (LinhKien) obj;
		if (maLK == null) {
			if (other.maLK != null)
				return false;
		} else if (!maLK.equals(other.maLK))
			return false;
		return true;
	}
	
	
	
}
