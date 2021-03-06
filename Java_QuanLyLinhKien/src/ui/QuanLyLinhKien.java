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
		JLabel lblHeader = new JLabel("QU???N L?? LINH KI???N");
		lblHeader.setFont(HEADER_FONT);
		lblHeader.setForeground(HEADER_COLOR);

		JLabel lblTimTheoMa = new JLabel("T??m theo m?? linh ki???n");
		lblTimTheoMa.setFont(NORMAL_FONT);
		radTimTheoMa = new JRadioButton();
		JLabel lblTimTheoTen = new JLabel("T??m theo th????ng hi???u");
		lblTimTheoTen.setFont(NORMAL_FONT);
		radTimTheoTen = new JRadioButton();
		// JLabel lblTimTheoDungTich = new JLabel("T??m theo h??ng linh ki???n");
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

		JLabel lblLoai = new JLabel("Lo???i linh ki???n:");

		txtMaLK = new JTextField();
		txtTenLK = new JTextField();
		txtSoLuongTon = new JTextField();
		txtDonGia = new JTextField();
		txtBaoHanh = new JTextField();
		txtThuongHieu = new JTextField();
		txtNuocSanXuat = new JTextField();

		ckbLoaiLK = new JComboBox<String>();
		ckbLoaiLK.addItem("CPU-B???  vi x??? l??");
		ckbLoaiLK.addItem("VGA-Card m??n h??nh");
		ckbLoaiLK.addItem("Ram-B??? nh??? trong");
		ckbLoaiLK.addItem("??? c???ng (HHD,SSD)");
		ckbLoaiLK.addItem("B??n ph??m-Chu???t");
		ckbLoaiLK.addItem("Mainboard");

		boxLeft = Box.createVerticalBox();
		boxRight = Box.createVerticalBox();
		boxGroup = Box.createHorizontalBox();

		txtMaLK = addInputItemTo(boxLeft, "M?? linh ki???n:");
		txtTenLK = addInputItemTo(boxRight, "T??n linh ki???n:");

		txtThuongHieu = addInputItemTo(boxLeft, "Th????ng hi???u:");
		txtNuocSanXuat = addInputItemTo(boxRight, "N?????c s???n xu???t:");

		txtBaoHanh = addInputItemTo(boxLeft, "B???o h??nh");

		txtDonGia = addInputItemTo(boxRight, "????n gi??:");
		txtSoLuongTon = addInputItemTo(boxLeft, "S??? l?????ng t???n:");

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

		String[] header = { "M?? linh ki???n", "T??n linh ki???n", "Lo???i linh ki???n", "Th????ng hi???u", "N?????c s???n xu???t",
				"S??? l?????ng t???n", "????n gi??", "B???o h??nh" };
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
		btnThem = addButtonTo(boxButton, "Th??m", "img/add.png");
		btnSua = addButtonTo(boxButton, "S???a", "img/update.png");
		btnXoa = addButtonTo(boxButton, "Xo??", "img/delete.png");
		btnXoaTrang = addButtonTo(boxButton, "Xo?? tr???ng", "img/erase.png");

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
			JOptionPane.showMessageDialog(this, "L???i k???t n???i!", "L???i", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this, "Th??m kh??ng th??nh c??ng", "Th??ng b??o",
						JOptionPane.INFORMATION_MESSAGE);
		} else
			return;
	}

	private void xoaLinhKien() throws SQLException {
		int row = tableLK.getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(this, "Ph???i ch???n xe c???n xo??", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
		else {
			int replay = JOptionPane.showConfirmDialog(this, "B???n c?? ch???c mu???n xo?? d??ng n??y!!", "C???nh b??o",
					JOptionPane.YES_NO_OPTION);
			if (replay == JOptionPane.YES_OPTION) {
				List<LinhKien> dsLK = listLK.getAll();
				int rows = tableLK.getSelectedRow();
				if (rows >= 0 || rows < dsLK.size()) {
					LinhKien lk = dsLK.get(rows);
					if (listLK.xoaLinhKien(lk)) {
						loadDataToTable();
						xoaTrang();
						JOptionPane.showMessageDialog(this, "Xo?? th??nh c??ng", "Th??ng b??o",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(this, "Xo?? kh??ng th??nh c??ng", "Th??ng b??o",
								JOptionPane.INFORMATION_MESSAGE);
				}
			}
			return;
		}
	}

	private void suaTTLinhKien() throws SQLException {
		int row = tableLK.getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(this, "Ph???i ch???n linh ki???n c???n s???a", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
		else {
			if (kiemTraDuLieu()) {
				LinhKien lk = new LinhKien(txtMaLK.getText().trim(), txtTenLK.getText().trim(),
						ckbLoaiLK.getSelectedItem().toString(), txtThuongHieu.getText().trim(),
						txtNuocSanXuat.getText().trim(), Integer.parseInt(txtSoLuongTon.getText()),
						Double.parseDouble(txtDonGia.getText()), Integer.parseInt(txtBaoHanh.getText()));
//				if (!lk.getMaLK().equalsIgnoreCase(defaultTable.getValueAt(row, 0).toString())) {
//					JOptionPane.showMessageDialog(this, "Kh??ng ???????c s???a m?? linh ki???n!!");
//					txtMaLK.setText(defaultTable.getValueAt(row, 0).toString());
//				} else {
					if (listLK.suaTTLinhKien(lk)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(this, "S???a th??nh c??ng", "Th??ng b??o",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(this, "S???a kh??ng th??nh c??ng", "Th??ng b??o",
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
				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
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
//				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
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

		// M?? linh ki???n kh??ng ???????c b??? tr???ng v?? ph???i b???t ?????u b???ng 'XM', theo sau l?? 2 ?????n
		// 3 k?? t??? s???!
		if (!(!ma.isEmpty() && ma.matches("LK\\d{2,3}"))) {
			JOptionPane.showMessageDialog(this,
					"M?? linh ki???n kh??ng ???????c b??? tr???ng v?? ph???i b???t ?????u b???ng 'LK', theo sau l?? 2 ?????n 3 k?? t??? s???!",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtMaLK.requestFocus();
			return false;
		}

		// T??n linh ki???n kh??ng ???????c b??? tr???ng, c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng
		// tr???ng
		// Kh??ng ch???a k?? t??? ?????c bi???t
		if (!(!ten.isEmpty() && ten.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"T??n linh ki???n kh??ng ???????c b??? tr???ng. T??n linh ki???n c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng v?? kh??ng ch???a k?? t??? ?????c bi???t",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtTenLK.requestFocus();
			return false;
		}

		// Th????ng hi???u kh??ng ???????c b??? tr???ng, c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng
		// tr???ng
		// Kh??ng ch???a k?? t??? ?????c bi???t
		if (!(!thuongHieu.isEmpty() && thuongHieu.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"Th????ng hi???u kh??ng ???????c b??? tr???ng. Th????ng hi???u c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng v?? kh??ng ch???a k?? t??? ?????c bi???t",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtThuongHieu.requestFocus();
			return false;
		}

		// Th???i gian b???o h??nh kh??ng ???????c b??? tr???ng, dung t??ch ph???i l???n h??n 0
		if (!baoHanh.isEmpty()) {
			try {
				int dt = Integer.parseInt(baoHanh);
				if (dt < 0) {
					JOptionPane.showMessageDialog(this, "Th???i gian b???o h??nh ph???i l???n h??n ho???c b???ng 0", "C???nh b??o",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Th???i gian b???o h??nh", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Th???i gian b???o h??nh kh??ng ???????c b??? tr???ng", "C???nh b??o",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// N?????c s???n xu???t kh??ng ???????c b??? tr???ng, c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng
		// tr???ng
		// Kh??ng ch???a k?? t??? ?????c bi???t
		if (!(!nuocsanxuat.isEmpty() && nuocsanxuat.matches("[\\p{L}0-9\\s]+"))) {
			JOptionPane.showMessageDialog(this,
					"N?????c s???n xu???t kh??ng ???????c b??? tr???ng. N?????c s???n xu???t c?? th??? g???m nhi???u t??? c??ch nhau b???i kho???ng tr???ng v?? kh??ng ch???a k?? t??? ?????c bi???t",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtNuocSanXuat.requestFocus();
			return false;
		}

		// S??? l?????ng t???n kh??ng ???????c b??? tr???ng, s??? l?????ng t???n ph???i l???n h??n ho???c b???ng 0
		if (!soluongton.isEmpty()) {
			try {
				int slt = Integer.parseInt(soluongton);
				if (slt < 0) {
					JOptionPane.showMessageDialog(this, "S??? l?????ng t???n ph???i l???n h??n ho???c b???ng 0", "C???nh b??o",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "S??? l?????ng t???n ph???i nh???p s???", "C???nh b??o",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "S??? l?????ng t???n kh??ng ???????c b??? tr???ng", "C???nh b??o",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		// ????n gi?? kh??ng ???????c b??? tr???ng, ????n gi?? ph???i l???n h??n ho???c b???ng 0
		if (!dongia.isEmpty()) {
			try {
				double dg = Double.parseDouble(dongia);
				if (dg < 0) {
					JOptionPane.showMessageDialog(this, "????n gi?? ph???i l???n h??n ho???c b???ng 0", "C???nh b??o",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "????n gi?? ph???i nh???p s???", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "????n gi?? kh??ng ???????c b??? tr???ng", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
}
