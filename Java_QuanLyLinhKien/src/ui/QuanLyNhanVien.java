package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachNhanVien;
import entity.NhanVien;

public class QuanLyNhanVien extends JPanel implements MouseListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman",Font.BOLD, 20);
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTim;
	private JButton btnXoaTrang;
	private JTable tblNhanVien;
	private DefaultTableModel tableModel;
	private JCheckBox chkQuanLyVien;
	private JRadioButton radTimTheoMa;
	private JRadioButton radTimTheoTen;
	private JTextField txtTim, txtMaNV, txtTenNV, txtDiaChi, txtSDT, txtEmail;
	private JComboBox<String> cbxGioiTinh;
	private ButtonGroup buttonGroup;
	private DanhSachNhanVien dsNV = new DanhSachNhanVien();


	
	public QuanLyNhanVien() {
		// TODO Auto-generated constructor stub
		
//		this.setPreferredSize(new Dimension(500,600));
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(new BorderLayout());
		setLookAndFeel();
		addNorth();
		addCenter();
		addEast();
		dsNV = new DanhSachNhanVien();
		tblNhanVien.addMouseListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}
	
	private void addNorth() {
		JPanel pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(0,300));
		pnlNorth.setLayout(null);
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTieuDe.setFont(HEADER_FONT);
		pnlNorth.add(lblTieuDe);
		lblTieuDe.setBounds(270, 0, 300, 100);
		
		JLabel lblMaNV, lblTenNV, lblDiaChi, lblSDT, lblEmail, lblQuanLyVien, lblGioiTinh;
		pnlNorth.add(lblMaNV = new JLabel("Mã nhân viên:"));
		pnlNorth.add(lblTenNV = new JLabel("Họ tên nhân viên:"));
		pnlNorth.add(lblDiaChi = new JLabel("Địa chỉ:"));
		pnlNorth.add(lblSDT = new JLabel("Số điện thoại:"));
		pnlNorth.add(lblGioiTinh = new JLabel("Giới tính:"));
		pnlNorth.add(lblEmail = new JLabel("Email:"));
		pnlNorth.add(lblQuanLyVien = new JLabel("Quản lý viên:"));
		
		pnlNorth.add(txtMaNV = new JTextField());
		pnlNorth.add(txtTenNV = new JTextField());
		pnlNorth.add(txtTim = new JTextField());
		pnlNorth.add(txtDiaChi = new JTextField());
		pnlNorth.add(txtSDT = new JTextField());
		pnlNorth.add(txtEmail = new JTextField());
		
		
		
		String[] item = {"Nam", "Nữ"};
		cbxGioiTinh = new JComboBox<String>(item);
		pnlNorth.add(cbxGioiTinh);
		pnlNorth.add(chkQuanLyVien = new JCheckBox());
		pnlNorth.add(btnTim = new JButton("Tìm kiếm"));
		btnTim.setIcon(new ImageIcon("img/search.png"));
		
		radTimTheoMa = new JRadioButton("Tìm theo mã");
		radTimTheoTen = new JRadioButton("Tìm theo tên");
		radTimTheoMa.setSelected(true);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radTimTheoMa);
		buttonGroup.add(radTimTheoTen);
		pnlNorth.add(radTimTheoMa);
		pnlNorth.add(radTimTheoTen);
		
		int w1 = 100, h = 30;
		lblMaNV.setBounds(20, 80, w1, h); txtMaNV.setBounds(110, 80, 600, h);
		
		lblTenNV.setBounds(20, 115, w1, h); txtTenNV.setBounds(110, 115, 400, h);
		lblGioiTinh.setBounds(550, 115, w1, h); cbxGioiTinh.setBounds(610, 115, w1, h);
		
		lblSDT.setBounds(20, 150, w1, h); txtSDT.setBounds(110, 150, 260, h);
		lblEmail.setBounds(400, 150, w1, h); txtEmail.setBounds(440, 150, 270, h);
		
		lblDiaChi.setBounds(20, 185, w1, h); txtDiaChi.setBounds(110, 185, 600, h);
		
		lblQuanLyVien.setBounds(20, 220, w1, h); chkQuanLyVien.setBounds(110, 220, 20, h);
		
		btnTim.setBounds(20, 260, w1, h); txtTim.setBounds(140, 260, 360, h);
		radTimTheoMa.setBounds(520, 260, w1, h);radTimTheoTen.setBounds(620, 260, w1, h);
	}
	
	private void addCenter() {
		String[] headers = "Mã NV;Họ tên NV;Giới tính;Địa chỉ;SĐT;Email;Chức vụ".split(";");
		tableModel = new DefaultTableModel(headers,0);
		tblNhanVien = new JTable(tableModel);
		tblNhanVien.setRowHeight(25);
		JScrollPane jsp = new JScrollPane(tblNhanVien);
		jsp.setPreferredSize(new Dimension(jsp.getPreferredSize().width, 300));
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jsp, BorderLayout.CENTER);
	}
	
	private void addEast() {
		JPanel pnlEast = new JPanel();
		this.add(pnlEast, BorderLayout.EAST);
		pnlEast.setLayout(new GridLayout(8,1,5,5));

		btnThem = new JButton("Thêm nhân viên");
		btnThem.setIcon(new ImageIcon("img/add.png"));

		btnSua = new JButton("Sửa nhân viên");
		btnSua.setIcon(new ImageIcon("img/update.png"));

		btnXoa = new JButton("Xoá nhân viên");
		btnXoa.setIcon(new ImageIcon("img/delete.png"));

		btnXoaTrang = new JButton("Xoá trắng");
		btnXoaTrang.setIcon(new ImageIcon("img/erase.png"));

		
		pnlEast.add(btnThem);
		pnlEast.add(btnSua);
		pnlEast.add(btnXoa);
		pnlEast.add(btnXoaTrang);
	}

	
	private void setLookAndFeel() {
		for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
			if(info.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				} catch (ClassNotFoundException| InstantiationException| IllegalAccessException | UnsupportedLookAndFeelException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
//	private JButton addButtonTo(Box box, String name) {
//		JButton btn = new JButton(name);
//		btn.setFont(NORMAL_FONT);
//		btn.setPreferredSize(new Dimension(130, 25));
//		Box boxButton = Box.createHorizontalBox();
//		boxButton.add(Box.createHorizontalGlue());
//		boxButton.add(btn);
//		boxButton.add(Box.createHorizontalGlue());
//		box.add(Box.createVerticalStrut(5));
//		box.add(boxButton);
//		box.add(Box.createVerticalStrut(5));
//		return btn;
//	}
	
	public void loadDataToTable() {
		deleteDataInTable();
		try {
			dsNV.getAll();
			ArrayList<NhanVien> list = dsNV.getDsNV();

			for (NhanVien nhanVien : list) {
				String[] row = { nhanVien.getMaNV(), nhanVien.getHoTenNV(), nhanVien.isGioiTinh() ? "Nam" : "Nữ",
						nhanVien.getDiaChi(), nhanVien.getSDT(), nhanVien.getEmail(),
						nhanVien.isQuanLyVien() ? "Quản lý viên" : "Nhân viên bán hàng" };
				tableModel.addRow(row);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	
	private void deleteDataInTable() {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}
	
	private void themNhanVien() {
		if (validData()) {
			NhanVien nv = new NhanVien(txtMaNV.getText().trim(), txtTenNV.getText().trim(),
					cbxGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false, txtDiaChi.getText().trim(),
					txtSDT.getText().trim(), txtEmail.getText().trim(), chkQuanLyVien.isSelected());

			try {
				if (dsNV.themNhanVien(nv)) {
					String[] st = { nv.getMaNV(), nv.getHoTenNV(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getDiaChi(),
							nv.getSDT(), nv.getEmail(), nv.isQuanLyVien() ? "Quản lý viên" : "Nhân viên bán hàng" };
					tableModel.addRow(st);
					xoaTrang();
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				} else
					JOptionPane.showMessageDialog(this, "Thêm không thành công");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this, "Trùng mã Nhân Viên");
			}
		}
	}
	
	private void SuaTTNhanVien() throws SQLException {
		int row = tblNhanVien.getSelectedRow();

		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Phải chọn Nhân viên cần sửa");
		} else {
			NhanVien nv = new NhanVien(txtMaNV.getText().trim(), txtTenNV.getText().trim(),
					cbxGioiTinh.getSelectedItem() == "Nam" ? true : false, txtDiaChi.getText().trim(),
					txtSDT.getText().trim(), txtEmail.getText().trim(), chkQuanLyVien.isSelected());
			if (!nv.getMaNV().equalsIgnoreCase(tblNhanVien.getValueAt(row, 0).toString()))
				JOptionPane.showMessageDialog(this, "Không được sửa mã Nhân viên");
			else {
				if (dsNV.suaTTNhanVien(nv)) {

					tableModel.setValueAt(nv.getMaNV(), row, 0);
					tableModel.setValueAt(nv.getHoTenNV(), row, 1);
					tableModel.setValueAt(nv.isGioiTinh() ? "Nam" : "Nữ", row, 2);
					tableModel.setValueAt(nv.getDiaChi(), row, 3);
					tableModel.setValueAt(nv.getSDT(), row, 4);
					tableModel.setValueAt(nv.getEmail(), row, 5);
					tableModel.setValueAt(nv.isQuanLyVien() ? "Quản lý viên" : "Nhân viên bán hàng", row, 6);

					JOptionPane.showMessageDialog(this, "Sửa thành công");

				} else {
					JOptionPane.showMessageDialog(this, "Sửa không thành công");
				}
			}
		}
	}

	private void timTheoTen() throws SQLException {
		if (txtTim.getText().trim().equals(""))
			loadDataToTable();
		else {
			ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
			dsnv = dsNV.timTheoTenNhanVien(txtTim.getText().trim());
			if (dsnv == null) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy Nhân Viên");
			} else {
				deleteDataInTable();
				for (NhanVien nhanVien : dsnv) {
					tableModel.addRow(new Object[] { nhanVien.getMaNV(), nhanVien.getHoTenNV(),
							nhanVien.isGioiTinh() ? "Nam" : "Nữ", nhanVien.getDiaChi(), nhanVien.getSDT(),
							nhanVien.getEmail(), nhanVien.isQuanLyVien() ? "Quản lý viên" : "Nhân viên bán hàng" });
				}
			}
		}

	}
	
	private void xoaNhanVien() {
		int selectedRow = tblNhanVien.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this,
					"Bạn chưa chọn Nhân Viên cần xóa, nhấp chọn vào Nhân Viên cần xóa trong bảng!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa!", "Xác nhận",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
			return;

		int count = 0;
		while (tblNhanVien.getSelectedRow() != -1) {
			int selected = tblNhanVien.getSelectedRow();
			String maNV = (String) tblNhanVien.getValueAt(selected, 0);

			try {
				if (dsNV.xoaNhanVien(maNV)) {
					tableModel.removeRow(selected);
					count++;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi trong khi xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		JOptionPane.showMessageDialog(this, "Xóa thành công " + count + " Nhân Viên!", "Thông báo",
				JOptionPane.INFORMATION_MESSAGE);
	}

	
	private void timTheoMaNhanVien() throws SQLException {
		if (txtTim.getText().trim().equals(""))
			loadDataToTable();
		else {
			NhanVien nv = new NhanVien();
			nv = dsNV.timTheoMaNhanVien(txtTim.getText().trim());
			if (nv == null)
				JOptionPane.showMessageDialog(this, "Không tìm thấy Nhân Viên!");
			else {
				deleteDataInTable();
				tableModel.addRow(new Object[] { nv.getMaNV(), nv.getHoTenNV(), nv.isGioiTinh() ? "Nam" : "Nữ",
						nv.getDiaChi(), nv.getSDT(), nv.getEmail(),
						nv.isQuanLyVien() ? "Quản lý viên" : "Nhân viên bán hàng" });
			}
		}

	}
	
	private boolean validData() {
		String maNV = txtMaNV.getText().trim();
		String hoTenNV = txtTenNV.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String SDT = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();

		Pattern pattern = Pattern.compile("NV[0-9]{2,3}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(maNV);
		boolean match_maNV = matcher.matches();
		if (maNV.length() < 1) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng không được để trống");
			return false;
		} else if (!match_maNV) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng không đúng định dạng");
			return false;
		} else if (hoTenNV.length() < 1) {
			JOptionPane.showMessageDialog(this, "Tên không được để trống");
			return false;
		} else if (!hoTenNV.matches("[\\p{L}a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không chứa số và kí tự đặc biệt");
			return false;
		} else if (diaChi.length() < 1) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
			return false;
		} else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
			return false;
		} else if (!SDT.matches("0[0-9]{9}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không đúng");
			return false;
		}

		return true;
	}
	
	private void xoaTrang() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		cbxGioiTinh.setSelectedItem("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		chkQuanLyVien.isSelected();

	}
	
	private void xoaRong() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		txtTim.setText("");
		radTimTheoMa.setSelected(true);
		chkQuanLyVien.setSelected(false);
		cbxGioiTinh.setSelectedIndex(0);
		tblNhanVien.clearSelection();
	}
	
	public void setPopupMenu(JPopupMenu popup) {
		txtMaNV.setComponentPopupMenu(popup);
		txtTenNV.setComponentPopupMenu(popup);
		txtDiaChi.setComponentPopupMenu(popup);
		txtSDT.setComponentPopupMenu(popup);
		txtEmail.setComponentPopupMenu(popup);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			if (o.equals(btnSua)) {
				SuaTTNhanVien();
			} else if (o.equals(btnXoa)) {
				xoaNhanVien();
			} else if (o.equals(btnThem)) {
				themNhanVien();
			} else if (o.equals(btnTim) && radTimTheoMa.isSelected()) {
				timTheoMaNhanVien();
			} else if (o.equals(btnTim) && radTimTheoTen.isSelected()) {
				timTheoTen();
			} else if (o.equals(btnXoaTrang)) {
				xoaRong();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblNhanVien.getSelectedRow();
		txtMaNV.setText(tblNhanVien.getValueAt(row, 0).toString());
		txtTenNV.setText(tblNhanVien.getValueAt(row, 1).toString());
		txtDiaChi.setText(tblNhanVien.getValueAt(row, 3).toString());
		cbxGioiTinh.setSelectedItem(tblNhanVien.getValueAt(row, 2).toString());
		txtSDT.setText(tblNhanVien.getValueAt(row, 4).toString());
		txtEmail.setText(tblNhanVien.getValueAt(row, 5).toString());
		String cv = tblNhanVien.getValueAt(row, 6).toString();
		if (cv == "Quản lý viên")
			chkQuanLyVien.setSelected(true);
		else
			chkQuanLyVien.setSelected(false);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
