package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachLinhKien;
import entity.LinhKien;

public class QuanLyLinhKien extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JButton btnSearch;
	private JTextField txtSearch;
	private JPanel pnlTitle;
	private JRadioButton radTimTheoMa;
	private JTextField txtDonGia;
	private JTextField txtSoLuongTon;
	private JRadioButton radTimTheoTen;
	private JComboBox<String> ckbLoaiLK;

	private JTextField txtMaLK;
	private JTextField txtTenLK;
	private DefaultTableModel defaultTable;
	private JTable tableLK;
	private JScrollPane scroll;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private DanhSachLinhKien listLK = new DanhSachLinhKien();
	private JTextField txtThuongHieu;
	private JTextField txtNuocSanXuat;
	private JTextField txtBaoHanh;

	public QuanLyLinhKien() {

		setSize(750, 650);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		addNorth();
		addCenter();
		addEast();

		listLK = new DanhSachLinhKien();
		tableLK.addMouseListener(this);
		btnSearch.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
	}

	private void addNorth() {
		Box boxNorth, boxSearch, boxLineRad;
		btnSearch = new JButton("", new ImageIcon("img/search.png"));
		btnSearch.setFont(NORMAL_FONT);
		txtSearch = new JTextField();
		pnlTitle = new JPanel();
		JLabel lblHeader = new JLabel("QUẢN LÝ LINH KIỆN");
		lblHeader.setFont(HEADER_FONT);
		lblHeader.setForeground(HEADER_COLOR);

		JLabel lblTimTheoMa = new JLabel("Tìm theo mã linh kiện");
		lblTimTheoMa.setFont(NORMAL_FONT);
		radTimTheoMa = new JRadioButton();
		JLabel lblTimTheoTen = new JLabel("Tìm theo thương hiệu");
		lblTimTheoTen.setFont(NORMAL_FONT);
		radTimTheoTen = new JRadioButton();
		// JLabel lblTimTheoDungTich = new JLabel("Tìm theo hãng linh kiện");
		// lblTimTheoDungTich.setFont(NORMAL_FONT);
		// radTimTheoDungTich = new JRadioButton();
		ButtonGroup group = new ButtonGroup();
		// group.add(radTimTheoDungTich);
		group.add(radTimTheoTen);
		group.add(radTimTheoMa);

		boxLineRad = Box.createHorizontalBox();
		boxLineRad.add(radTimTheoMa);
		radTimTheoMa.setSelected(true);
		boxLineRad.add(lblTimTheoMa);
		boxLineRad.add(Box.createHorizontalStrut(20));
		boxLineRad.add(radTimTheoTen);
		boxLineRad.add(lblTimTheoTen);
		// boxLineRad.add(Box.createHorizontalStrut(20));
		// boxLineRad.add(radTimTheoDungTich);
		// boxLineRad.add(lblTimTheoDungTich);

		boxSearch = Box.createHorizontalBox();
		boxSearch.add(Box.createHorizontalStrut(20));
		boxSearch.add(txtSearch);
		boxSearch.add(Box.createHorizontalStrut(5));
		boxSearch.add(btnSearch);
		boxSearch.add(Box.createHorizontalStrut(20));

		boxNorth = Box.createVerticalBox();
		pnlTitle.add(lblHeader);
		boxNorth.add(pnlTitle);
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxSearch);
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxLineRad);
		boxNorth.add(Box.createVerticalStrut(20));

		Box boxLeft, boxRight, boxGroup;

		JLabel lblLoai = new JLabel("Loại linh kiện:");

		txtMaLK = new JTextField();
		txtTenLK = new JTextField();
		txtSoLuongTon = new JTextField();
		txtDonGia = new JTextField();
		txtBaoHanh = new JTextField();
		txtThuongHieu = new JTextField();
		txtNuocSanXuat = new JTextField();

		ckbLoaiLK = new JComboBox<String>();
		ckbLoaiLK.addItem("CPU-Bộ  vi xử lý");
		ckbLoaiLK.addItem("VGA-Card màn hình");
		ckbLoaiLK.addItem("Ram-Bộ nhớ trong");
		ckbLoaiLK.addItem("Ổ cứng (HHD,SSD)");
		ckbLoaiLK.addItem("Bàn phím-Chuột");
		ckbLoaiLK.addItem("Mainboard");

		boxLeft = Box.createVerticalBox();
		boxRight = Box.createVerticalBox();
		boxGroup = Box.createHorizontalBox();

		txtMaLK = addInputItemTo(boxLeft, "Mã linh kiện:");
		txtTenLK = addInputItemTo(boxRight, "Tên linh kiện:");

		txtThuongHieu = addInputItemTo(boxLeft, "Thương hiệu:");
		txtNuocSanXuat = addInputItemTo(boxRight, "Nước sản xuất:");

		txtBaoHanh = addInputItemTo(boxLeft, "Bảo hành");

		txtDonGia = addInputItemTo(boxRight, "Đơn giá:");
		txtSoLuongTon = addInputItemTo(boxLeft, "Số lượng tồn:");

		Box boxCkb;
		boxCkb = Box.createHorizontalBox();
		boxCkb.add(Box.createHorizontalGlue());
		boxCkb.add(lblLoai);
		boxCkb.add(Box.createHorizontalStrut(5));
		boxCkb.add(ckbLoaiLK);
		boxCkb.add(Box.createHorizontalGlue());

		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.add(boxLeft);
		boxGroup.add(Box.createHorizontalStrut(10));
		boxGroup.add(boxRight);
		boxGroup.add(Box.createHorizontalStrut(20));
		boxNorth.add(boxGroup);

		boxNorth.add(boxCkb);
		boxNorth.add(Box.createVerticalStrut(20));

		this.add(boxNorth, BorderLayout.NORTH);
	}

	private JTextField addInputItemTo(Box box, String name) {
		JLabel label = new JLabel(name);
		label.setFont(NORMAL_FONT);
		label.setPreferredSize(new Dimension(100, 25));
		JTextField text = new JTextField();
		text.setFont(NORMAL_FONT);

		Box boxItem = Box.createHorizontalBox();
		boxItem.add(Box.createHorizontalGlue());
		boxItem.add(label);
		boxItem.add(Box.createHorizontalStrut(5));
		boxItem.add(text);
		boxItem.add(Box.createHorizontalGlue());

		box.add(Box.createVerticalStrut(5));
		box.add(boxItem);
		box.add(Box.createVerticalStrut(5));

		return text;
	}

	private void addCenter() {

		String[] header = { "Mã linh kiện", "Tên linh kiện", "Loại linh kiện", "Thương hiệu", "Nước sản xuất",
				"Số lượng tồn", "Đơn giá", "Bảo hành" };
		defaultTable = new DefaultTableModel(header, 0);
		tableLK = new JTable(defaultTable) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableLK.setFont(NORMAL_FONT);
		tableLK.setRowHeight(25);
		scroll = new JScrollPane(tableLK, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(scroll, BorderLayout.CENTER);
	}

	private void addEast() {

		Box boxButton;

		boxButton = Box.createVerticalBox();
		boxButton.add(Box.createVerticalStrut(50));
		btnThem = addButtonTo(boxButton, "Thêm", "img/add.png");
		btnSua = addButtonTo(boxButton, "Sửa", "img/update.png");
		btnXoa = addButtonTo(boxButton, "Xoá", "img/delete.png");
		btnXoaTrang = addButtonTo(boxButton, "Xoá trắng", "img/erase.png");

		this.add(boxButton, BorderLayout.EAST);
	}

	private JButton addButtonTo(Box box, String name, String iconPath) {
		JButton btn = new JButton(name, new ImageIcon(iconPath));
		btn.setFont(NORMAL_FONT);
		btn.setPreferredSize(new Dimension(150, 25));

		Box boxButton = Box.createHorizontalBox();
		boxButton.add(Box.createHorizontalGlue());
		boxButton.add(btn);
		boxButton.add(Box.createHorizontalGlue());

		box.add(Box.createVerticalStrut(5));
		box.add(boxButton);
		box.add(Box.createVerticalStrut(5));

		return btn;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableLK.getSelectedRow();
		String temp = defaultTable.getValueAt(row, 6).toString();
		temp = temp.replace(",", "");
		txtMaLK.setText(defaultTable.getValueAt(row, 0).toString());
		txtTenLK.setText(defaultTable.getValueAt(row, 1).toString());
		ckbLoaiLK.setSelectedItem(defaultTable.getValueAt(row, 2).toString());
		txtThuongHieu.setText(defaultTable.getValueAt(row, 3).toString());
		txtNuocSanXuat.setText(defaultTable.getValueAt(row, 4).toString());
		txtBaoHanh.setText(defaultTable.getValueAt(row, 7).toString());
		txtSoLuongTon.setText(defaultTable.getValueAt(row, 5).toString());
		txtDonGia.setText(temp);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		try {
			if (o.equals(btnThem))
				themLinhKien();
			else if (o.equals(btnXoa))
				xoaLinhKien();
			else if (o.equals(btnSua))
				suaTTLinhKien();
			else if (o.equals(btnSearch) && radTimTheoMa.isSelected())
				timLinhKienTheoMa();
			else if (o.equals(btnSearch) && radTimTheoTen.isSelected())
				timLinhKienTheoThuongHieu();
//			else if(o.equals(btnSearch) && radTimTheoMa.isSelected())
//				timTheoMa();
			else
				xoaTrang();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public void focus() {
		txtSearch.requestFocus();
	}
	
	public void setPopupMenu(JPopupMenu popup) {
		txtMaLK.setComponentPopupMenu(popup);
		txtTenLK.setComponentPopupMenu(popup);
		txtThuongHieu.setComponentPopupMenu(popup);
		txtNuocSanXuat.setComponentPopupMenu(popup);
		txtBaoHanh.setComponentPopupMenu(popup);
		txtSoLuongTon.setComponentPopupMenu(popup);
		txtDonGia.setComponentPopupMenu(popup);
		txtSearch.setComponentPopupMenu(popup);
	}

	public void loadDataToTable() {
		try {
			deleteDataInTable();
			NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));

			List<LinhKien> dsLK = listLK.getAll();
			for (LinhKien lk : dsLK) {
				String donGia = nf.format(lk.getDonGia());
				defaultTable.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), lk.getLoaiLK(), lk.getThuongHieu(),
						lk.getNuocSanXuat(), lk.getSoLuongTon(), donGia, lk.getBaoHanh() });
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteDataInTable() {
		while (defaultTable.getRowCount() > 0) {
			defaultTable.removeRow(0);
		}
	}

	private void xoaTrang() {
		txtMaLK.setText("");
		txtTenLK.setText("");
		txtThuongHieu.setText("");
		ckbLoaiLK.setSelectedItem("");
		txtBaoHanh.setText("");
		txtNuocSanXuat.setText("");
		txtSoLuongTon.setText("");
		txtDonGia.setText("");
		txtMaLK.requestFocus();
	}

	private void themLinhKien() throws SQLException {
		if (kiemTraDuLieu()) {
			LinhKien lk = new LinhKien(txtMaLK.getText().trim(), txtTenLK.getText().trim(),
					ckbLoaiLK.getSelectedItem().toString(), txtThuongHieu.getText().trim(),
					txtNuocSanXuat.getText().trim(), Integer.parseInt(txtSoLuongTon.getText()),
					Double.parseDouble(txtDonGia.getText()), Integer.parseInt(txtBaoHanh.getText()));
			if (listLK.themLinhKien(lk)) {
				loadDataToTable();
				xoaTrang();
				JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
		} else
			return;
	}

	private void xoaLinhKien() throws SQLException {
		int row = tableLK.getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(this, "Phải chọn xe cần xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		else {
			int replay = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá dòng này!!", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (replay == JOptionPane.YES_OPTION) {
				List<LinhKien> dsLK = listLK.getAll();
				int rows = tableLK.getSelectedRow();
				if (rows >= 0 || rows < dsLK.size()) {
					LinhKien lk = dsLK.get(rows);
					if (listLK.xoaLinhKien(lk)) {
						loadDataToTable();
						xoaTrang();
						JOptionPane.showMessageDialog(this, "Xoá thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(this, "Xoá không thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
				}
			}
			return;
		}
	}

	private void suaTTLinhKien() throws SQLException {
		int row = tableLK.getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(this, "Phải chọn linh kiện cần sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		else {
			if (kiemTraDuLieu()) {
				LinhKien lk = new LinhKien(txtMaLK.getText().trim(), txtTenLK.getText().trim(),
						ckbLoaiLK.getSelectedItem().toString(), txtThuongHieu.getText().trim(),
						txtNuocSanXuat.getText().trim(), Integer.parseInt(txtSoLuongTon.getText()),
						Double.parseDouble(txtDonGia.getText()), Integer.parseInt(txtBaoHanh.getText()));
//				if (!lk.getMaLK().equalsIgnoreCase(defaultTable.getValueAt(row, 0).toString())) {
//					JOptionPane.showMessageDialog(this, "Không được sửa mã linh kiện!!");
//					txtMaLK.setText(defaultTable.getValueAt(row, 0).toString());
//				} else {
					if (listLK.suaTTLinhKien(lk)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(this, "Sửa thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(this, "Sửa không thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
				}
			
		}
	}

	private void timLinhKienTheoMa() throws SQLException {
		if (txtSearch.getText().equals(""))
			loadDataToTable();
		else {
			
			List<LinhKien> dsLK = new ArrayList<LinhKien>();
			dsLK = listLK.timTheoMaLinhKien(txtSearch.getText());
			if (dsLK == null)
				JOptionPane.showMessageDialog(this, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			else {
				deleteDataInTable();
				NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
				for (LinhKien lk : dsLK) {
					String donGia = nf.format(lk.getDonGia());
					defaultTable.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), lk.getLoaiLK(), lk.getThuongHieu(),
							lk.getNuocSanXuat(), lk.getSoLuongTon(), donGia, lk.getBaoHanh() });
				}
			}
			
//			LinhKien lk = new LinhKien();
//			lk = listLK.timTheoMaLinhKien(txtSearch.getText());
//			if (lk == null)
//				JOptionPane.showMessageDialog(this, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//			else {
//				deleteDataInTable();
//				NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
//				String donGia = nf.format(lk.getDonGia());
//				defaultTable.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), lk.getLoaiLK(), lk.getThuongHieu(),
//						lk.getNuocSanXuat(), lk.getSoLuongTon(), donGia, lk.getBaoHanh() });
//			}
		}
	}

	private void timLinhKienTheoThuongHieu() throws SQLException {
		if (txtSearch.getText().equals(""))
			loadDataToTable();
		else {
			List<LinhKien> dsLK = new ArrayList<LinhKien>();
			dsLK = listLK.timTheoThuongHieu(txtSearch.getText());
			if (dsLK == null)
				JOptionPane.showMessageDialog(this, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			else {
				deleteDataInTable();
				NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
				for (LinhKien lk : dsLK) {
					String donGia = nf.format(lk.getDonGia());
					defaultTable.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), lk.getLoaiLK(), lk.getThuongHieu(),
							lk.getNuocSanXuat(), lk.getSoLuongTon(), donGia, lk.getBaoHanh() });
				}
			}
		}
	}

	private boolean kiemTraDuLieu() {
		String ma = txtMaLK.getText().trim();
		String ten = txtTenLK.getText().trim();
		String thuongHieu = txtThuongHieu.getText().trim();
		String baoHanh = txtBaoHanh.getText().trim();
		String nuocsanxuat = txtNuocSanXuat.getText().trim();
		String soluongton = txtSoLuongTon.getText().trim();
		String dongia = txtDonGia.getText().trim();

		// Mã linh kiện không được bỏ trống và phải bắt đầu bằng 'XM', theo sau là 2 đến
		// 3 kí tự số!
		if (!(!ma.isEmpty() && ma.matches("LK\\d{2,3}"))) {
			JOptionPane.showMessageDialog(this,
					"Mã linh kiện không được bỏ trống và phải bắt đầu bằng 'LK', theo sau là 2 đến 3 kí tự số!",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtMaLK.requestFocus();
			return false;
		}

		// Tên linh kiện không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng
		// trắng
		// Không chứa ký tự đặc biệt
		if (!(!ten.isEmpty() && ten.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Tên linh kiện không được bỏ trống. Tên linh kiện có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtTenLK.requestFocus();
			return false;
		}

		// Thương hiệu không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng
		// trắng
		// Không chứa ký tự đặc biệt
		if (!(!thuongHieu.isEmpty() && thuongHieu.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Thương hiệu không được bỏ trống. Thương hiệu có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtThuongHieu.requestFocus();
			return false;
		}

		// Thời gian bảo hành không được bỏ trống, dung tích phải lớn hơn 0
		if (!baoHanh.isEmpty()) {
			try {
				int dt = Integer.parseInt(baoHanh);
				if (dt < 0) {
					JOptionPane.showMessageDialog(this, "Thời gian bảo hành phải lớn hơn hoặc bằng 0", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Thời gian bảo hành", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Thời gian bảo hành không được bỏ trống", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// Nước sản xuất không được bỏ trống, có thể gồm nhiều từ cách nhau bởi khoảng
		// trắng
		// Không chứa ký tự đặc biệt
		if (!(!nuocsanxuat.isEmpty() && nuocsanxuat.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Nước sản xuất không được bỏ trống. Nước sản xuất có thể gồm nhiều từ cách nhau bởi khoảng trắng và không chứa ký tự đặc biệt",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtNuocSanXuat.requestFocus();
			return false;
		}

		// Số lượng tồn không được bỏ trống, số lượng tồn phải lớn hơn hoặc bằng 0
		if (!soluongton.isEmpty()) {
			try {
				int slt = Integer.parseInt(soluongton);
				if (slt < 0) {
					JOptionPane.showMessageDialog(this, "Số lượng tồn phải lớn hơn hoặc bằng 0", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Số lượng tồn phải nhập số", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Số lượng tồn không được bỏ trống", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// Đơn giá không được bỏ trống, đơn giá phải lớn hơn hoặc bằng 0
		if (!dongia.isEmpty()) {
			try {
				double dg = Double.parseDouble(dongia);
				if (dg < 0) {
					JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn hoặc bằng 0", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Đơn giá phải nhập số", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Đơn giá không được bỏ trống", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
}
