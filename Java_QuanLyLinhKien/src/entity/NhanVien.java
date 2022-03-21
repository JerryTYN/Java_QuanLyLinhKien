package entity;

public class NhanVien {
	private String maNV;
	private String hoTenNV;
	private boolean gioiTinh;
	private String diaChi;
	private String SDT;
	private String email;
	private boolean quanLyVien;

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String maNV, String hoTenNV, boolean gioiTinh, String diaChi, String sDT, String email,
			boolean quanLyVien) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		SDT = sDT;
		this.email = email;
		this.quanLyVien = quanLyVien;
	}
	
    public NhanVien(String trim, String trim0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTenNV() {
		return hoTenNV;
	}

	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isQuanLyVien() {
		return quanLyVien;
	}

	public void setQuanLyVien(boolean quanLyVien) {
		this.quanLyVien = quanLyVien;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}
	
    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", hoTenNV=" + hoTenNV + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", SDT=" + SDT + ", email=" + email + ", quanLyVien=" + quanLyVien + '}';
    }
}
