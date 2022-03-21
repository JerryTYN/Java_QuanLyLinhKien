package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.QuanLyHoSo;

import javax.swing.UIManager.LookAndFeelInfo;

import entity.NhanVien;



public class HoSoNhanVien extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 25);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JTextField txtMaNhanVien;
	private JTextField txtTenNhanVien;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JComboBox<String> cboGioiTinh;
	private JComboBox<String> cboChucVu;
	private JButton btnChinhSua;
	private JButton btnLuu;
	private QuanLyHoSo hoSo = new QuanLyHoSo();
	
	public HoSoNhanVien(boolean isQuanLyVien) {
		this.setPreferredSize(new Dimension(500,600));
		this.setLayout(new BorderLayout());
		setLookAndFeel();
		
		addNorth();
		
		btnChinhSua.addActionListener(this);
		btnLuu.addActionListener(this);
		if(!isQuanLyVien) {
			btnLuu.setEnabled(false);
			btnChinhSua.setEnabled(false);
		}
	}
	
	private void addNorth() {
		JPanel pnlNorth = new JPanel();
		this.add(pnlNorth);
		pnlNorth.setPreferredSize(new Dimension(0,300));
		pnlNorth.setLayout(null);
		

		
		JLabel lblTieuDe = new JLabel("HỒ SƠ CỦA TÔI");
		lblTieuDe.setFont(HEADER_FONT);
		lblTieuDe.setForeground(HEADER_COLOR);
		lblTieuDe.setBounds(300, 0, 300, 100);
		pnlNorth.add(lblTieuDe);
		
		JLabel lblImage = new JLabel(new ImageIcon("img/nv.png"));
		pnlNorth.add(lblImage);
		lblImage.setBounds(30, 80, 220, 300);
		
		JLabel lblMaNV, lblTenNV, lblDiaChi, lblSDT, lblEmail, lblChucVu, lblGioiTinh;
		pnlNorth.add(lblMaNV = new JLabel("Mã nhân viên:"));
		pnlNorth.add(lblTenNV = new JLabel("Họ tên nhân viên:"));
		pnlNorth.add(lblDiaChi = new JLabel("Địa chỉ:"));
		pnlNorth.add(lblSDT = new JLabel("Số điện thoại:"));
		pnlNorth.add(lblGioiTinh = new JLabel("Giới tính:"));
		pnlNorth.add(lblEmail = new JLabel("Email:"));
		pnlNorth.add(lblChucVu = new JLabel("Chức vụ:"));
		
		pnlNorth.add(txtMaNhanVien = new JTextField());
		pnlNorth.add(txtTenNhanVien = new JTextField());
		pnlNorth.add(txtDiaChi = new JTextField());
		pnlNorth.add(txtSDT = new JTextField());
		pnlNorth.add(txtEmail = new JTextField());
		
		cboChucVu = new JComboBox<String>();
		cboChucVu.addItem("Quản lý viên");
		cboChucVu.addItem("Nhân viên");
		pnlNorth.add(cboChucVu);
		cboGioiTinh = new JComboBox<String>();
		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("Nữ");
		pnlNorth.add(cboGioiTinh);
		
		btnChinhSua = new JButton("Chỉnh sửa");
		btnChinhSua.setIcon(new ImageIcon("img/update.png"));
		btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon("img/save.png"));
		btnChinhSua.setBounds(380, 320, 150, 35);
		btnLuu.setBounds(530, 320, 100, 35);
		pnlNorth.add(btnChinhSua);
		pnlNorth.add(btnLuu);
		
		int w1 = 100, h = 30;
		lblMaNV.setBounds(250, 100, w1, h); txtMaNhanVien.setBounds(340, 100, 350, h);
		lblTenNV.setBounds(250, 135, w1, h); txtTenNhanVien.setBounds(340, 135, 350, h);
		lblGioiTinh.setBounds(250, 170, w1, h); cboGioiTinh.setBounds(340, 170, 120, h);
		lblChucVu.setBounds(490, 170, w1, h); cboChucVu.setBounds(550, 170, 140, h);
		lblSDT.setBounds(250, 205, w1, h); txtSDT.setBounds(340, 205, 350, h);
		lblEmail.setBounds(250, 240, w1, h); txtEmail.setBounds(340, 240, 350, h);
		lblDiaChi.setBounds(250, 275, w1, h); txtDiaChi.setBounds(340, 275, 350, h);
	}
	
	private void setLookAndFeel() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (info.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
					break;

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	
	public void loadDataFromDatabaseToPanel() {
		try {
			hoSo.getProfile();
			NhanVien nhanvien = hoSo.getNhanVien();
			txtMaNhanVien.setText(nhanvien.getMaNV());
			txtTenNhanVien.setText(nhanvien.getHoTenNV());
			cboGioiTinh.setSelectedItem(nhanvien.isGioiTinh()? "Nam" : "Nữ");
			txtDiaChi.setText(nhanvien.getDiaChi());
			txtSDT.setText(nhanvien.getSDT());
			txtEmail.setText(nhanvien.getEmail());
			cboChucVu.setSelectedItem(nhanvien.isQuanLyVien()? "Quản lý viên" : "Nhân viên");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void chinhSua() {
		txtTenNhanVien.setEditable(true);
		txtDiaChi.setEditable(true);	
		txtSDT.setEditable(true);	
		txtEmail.setEditable(true);	
		cboGioiTinh.setEnabled(true);
		cboChucVu.setEnabled(true);
}
	
	private void luu() throws SQLException{
		if(validData()) {
			NhanVien nv = new NhanVien(txtMaNhanVien.getText().trim(), txtTenNhanVien.getText().trim(), 
					cboGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false, txtDiaChi.getText().trim(),
					txtSDT.getText().trim(), txtEmail.getText().trim(), cboChucVu.getSelectedItem().toString().equals("Quản lý viên") ? true : false);
			if(hoSo.modifiedProfile(nv)) {
				JOptionPane.showMessageDialog(this, "Sửa thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				txtTenNhanVien.setEditable(false);
				txtDiaChi.setEditable(false);	
				txtSDT.setEditable(false);	
				txtEmail.setEditable(false);	
				cboGioiTinh.setEnabled(false);
				cboChucVu.setEnabled(false);	
			} else {
				JOptionPane.showMessageDialog(this, "Sửa nhân thông tin không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public boolean validData() {
		
		String tennv = txtTenNhanVien.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String email = txtEmail.getText().trim();
			
		//Tên nhân viên không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng trắng
		//Không chứa ký tự đặc biệt và số
		if(tennv.isEmpty() || !tennv.matches("[\\p{L}\\s]+")) {
			JOptionPane.showMessageDialog(this,
					"Tên xe không được bỏ trống. Tên xe có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt và số",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtTenNhanVien.requestFocus();
			return false;
		}
		
		//Địa chỉ không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng trắng
		//Được chứa ký tự '-', '(', ')' và số
		if(diachi.isEmpty() || !diachi.matches("[\\p{L}0-9\\s-(),]+")) {
			JOptionPane.showMessageDialog(this,
					"Địa chỉ không được bỏ trống. Địa chỉ có thể gồm nhiều từ cách nhau bởi khoảng trắng"
					+ "Được chứa ký tự '-', '(', ')' và số",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtDiaChi.requestFocus();
			return false;
		}
		
		
		if(email.isEmpty() || !email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
				+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			JOptionPane.showMessageDialog(this,
					"email không được bỏ trống. email có thể gồm nhiều từ, không được chứa khoảng trắng. "
					+ "Tên hộp thư được chưa các ký tự . và -, tên miền được chứa ký tự .",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtEmail.requestFocus();
			return false;
		}
		return true;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnChinhSua))
			chinhSua();
		else if(o.equals(btnLuu))
			try {
				luu();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}



}
