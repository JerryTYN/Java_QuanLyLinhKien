package dao;

public class DoanhThu {
	private String maNV;
	private String tenNV;
	private int soLKDaBan;
	private double doanhThu;
	
	
	public DoanhThu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoanhThu(String maNV, String tenNV, int soLKDaBan, double doanhThu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.soLKDaBan = soLKDaBan;
		this.doanhThu = doanhThu;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	
	public int getSoLKDaBan() {
		return soLKDaBan;
	}
	public void setSoLKDaBan(int soLKDaBan) {
		this.soLKDaBan = soLKDaBan;
	}
	public double getDoanhThu() {
		return doanhThu;
	}
	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}
	@Override
	public String toString() {
		return "DoanhThu [maNV=" + maNV + ", tenNV=" + tenNV + ", soXeDaBan=" + soLKDaBan + ", doanhThu="
				+ doanhThu + "]";
	}
}
