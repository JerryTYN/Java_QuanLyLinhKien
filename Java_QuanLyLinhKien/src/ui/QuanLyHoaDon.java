package ui;




import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachCTHD;
import dao.DanhSachHoaDon;
import dao.DanhSachKhachHang;
import dao.DanhSachLinhKien;
import dao.DanhSachNhanVien;

import connect.DatabaseConnect;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;

public class QuanLyHoaDon extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Times new roman", Font.PLAIN, 14);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JRadioButton radMaHD;
	private JRadioButton radMaNV;
	private JRadioButton radMaKH;
	private JTextField txtMaHD;
	private JTextField txtMaNV;
	private JTextField txtMaKH;
	private JTextField txtNgayLap;
	private JTextField txtMalinhkien;
	private JTextField txtSoLuong;
	private JTable tableCTHD;
	private DefaultTableModel modelCTHD;
	private JButton btnThemCTHD;
	private JButton btnXoaCTHD;
	private JButton btnSuaCTHD;
	private DefaultTableModel modelHoaDon;
	private JTable tableHoaDon;
	private JButton btnThemHD;
	private JButton btnXoaHD;
	private JButton btnSuaHD;
	private JButton btnXoaRong;
	private JButton btnXuatHoaDon;
	private DanhSachHoaDon dsHD;
	private DanhSachCTHD dsCTHD;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					QuanLyHoaDon frame = new QuanLyHoaDon();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public QuanLyHoaDon() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 650);
		setLayout(new BorderLayout());
		
		//  DatabaseConnect.getinstance().connect();
		setLookAndFeel();
		addNorth();
		addCenter();
		addEast();
		setBackground(null);
		addEvent();
		dsHD = new DanhSachHoaDon();
		dsCTHD = new DanhSachCTHD();
		
	
		

	}
	private void addNorth() {
		Box boxNorth = Box.createVerticalBox();

		this.add(boxNorth, BorderLayout.NORTH);

		Box boxHeader = Box.createHorizontalBox();

		boxNorth.add(Box.createVerticalStrut(5));
		boxNorth.add(boxHeader);
		boxNorth.add(Box.createVerticalStrut(5));

		JLabel lblHeader = new JLabel("QUẢN LÝ HÓA ĐƠN");
		lblHeader.setForeground(HEADER_COLOR);
		lblHeader.setFont(HEADER_FONT);

		boxHeader.add(Box.createHorizontalGlue());
		boxHeader.add(lblHeader);
		boxHeader.add(Box.createHorizontalGlue());

		Box boxSearch = Box.createHorizontalBox();

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(NORMAL_FONT);
		btnTimKiem = new JButton(new ImageIcon("img/search.png"));
		btnTimKiem.setFont(NORMAL_FONT);

		boxSearch.add(Box.createHorizontalGlue());
		boxSearch.add(Box.createHorizontalStrut(50));
		boxSearch.add(txtTimKiem);
		boxSearch.add(Box.createHorizontalStrut(5));
		boxSearch.add(btnTimKiem);
		boxSearch.add(Box.createHorizontalStrut(50));
		boxSearch.add(Box.createHorizontalGlue());

		Box boxRadio = Box.createHorizontalBox();

		JLabel lblTimTheo = new JLabel("Tìm theo");
		radMaHD = new JRadioButton("Mã hóa đơn");
		radMaHD.setSelected(true);
		radMaNV = new JRadioButton("Mã nhân viên");
		radMaKH = new JRadioButton("Mã khách hàng");

		ButtonGroup group = new ButtonGroup();
		group.add(radMaHD);
		group.add(radMaNV);
		group.add(radMaKH);

		boxRadio.add(Box.createHorizontalGlue());
		boxRadio.add(lblTimTheo);
		boxRadio.add(Box.createHorizontalStrut(5));
		boxRadio.add(radMaHD);
		boxRadio.add(Box.createHorizontalStrut(5));
		boxRadio.add(radMaNV);
		boxRadio.add(Box.createHorizontalStrut(5));
		boxRadio.add(radMaKH);
		boxRadio.add(Box.createHorizontalGlue());

		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxSearch);
		boxNorth.add(Box.createVerticalStrut(5));
		boxNorth.add(boxRadio);
		boxNorth.add(Box.createVerticalStrut(10));

		Box boxLeft = Box.createVerticalBox();
		Box boxRight = Box.createVerticalBox();

		Box boxInput = Box.createHorizontalBox();

		boxInput.add(Box.createHorizontalStrut(10));
		boxInput.add(boxLeft);
		boxInput.add(Box.createHorizontalStrut(10));
		boxInput.add(boxRight);
		boxInput.add(Box.createHorizontalStrut(10));

		boxNorth.add(boxInput);

		txtMaHD = addInputItemTo(boxLeft, "Mã hóa đơn");
		txtMaNV = addInputItemTo(boxRight, "Mã nhân viên");
		txtMaKH = addInputItemTo(boxLeft, "Mã khách hàng");
		txtNgayLap = addInputItemTo(boxRight, "Ngày lập");

		JLabel lblChiTietHD = new JLabel("Chi tiết hóa đơn");
		lblChiTietHD.setFont(NORMAL_FONT);

		Box boxLblCTHD = Box.createHorizontalBox();
		boxLblCTHD.add(Box.createHorizontalGlue());
		boxLblCTHD.add(lblChiTietHD);
		boxLblCTHD.add(Box.createHorizontalGlue());

		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxLblCTHD);

		JPanel pnlChiTietHD = new JPanel(new BorderLayout());
		pnlChiTietHD.setBorder(BorderFactory.createLineBorder(Color.black));

		Box boxFixedCTHD = Box.createHorizontalBox();
		boxFixedCTHD.add(Box.createHorizontalStrut(100));
		boxFixedCTHD.add(pnlChiTietHD);
		boxFixedCTHD.add(Box.createHorizontalStrut(100));

		boxNorth.add(boxFixedCTHD);

		Box boxCTHDCenter = Box.createVerticalBox();
		pnlChiTietHD.add(boxCTHDCenter, BorderLayout.CENTER);

		Box boxCTHDInput = Box.createHorizontalBox();
		pnlChiTietHD.add(boxCTHDInput, BorderLayout.NORTH);

		boxCTHDInput.add(Box.createHorizontalStrut(10));
		txtMalinhkien = addInputItemTo(boxCTHDInput, "Mã linh kiện");
		boxCTHDInput.add(Box.createHorizontalStrut(5));
		txtSoLuong = addInputItemTo(boxCTHDInput, "Số lượng");
		boxCTHDInput.add(Box.createHorizontalStrut(10));

		String[] cthdHeader = { "Mã linh kiện", "Số lượng", "Đơn giá", "Thành tiền" };
		modelCTHD = new DefaultTableModel(cthdHeader, 0);
		tableCTHD = new JTable(modelCTHD);
		tableCTHD.setFont(NORMAL_FONT);
		JScrollPane scroll = new JScrollPane(tableCTHD);
		scroll.setPreferredSize(new Dimension(scroll.getPreferredSize().width, 150));

		boxCTHDCenter.add(Box.createVerticalStrut(10));
		boxCTHDCenter.add(scroll);

		Box boxCTHDButton = Box.createVerticalBox();
		pnlChiTietHD.add(boxCTHDButton, BorderLayout.EAST);

		boxCTHDButton.add(Box.createVerticalStrut(5));
		btnThemCTHD = addButtonTo(boxCTHDButton, "Thêm chi tiết", "img/add.png");
		btnXoaCTHD = addButtonTo(boxCTHDButton, "Xóa chi tiết", "img/delete.png");
		btnSuaCTHD = addButtonTo(boxCTHDButton, "Sửa chi tiết", "img/update.png");

		boxNorth.add(Box.createVerticalStrut(20));
	}
	private void addCenter() {
		String[] hoaDonHeader = { "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày lập" };
		modelHoaDon = new DefaultTableModel(hoaDonHeader, 0);
		tableHoaDon = new JTable(modelHoaDon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableHoaDon.setRowHeight(25);
		tableHoaDon.setFont(NORMAL_FONT);
		JScrollPane scroll = new JScrollPane(tableHoaDon);
		scroll.setPreferredSize(new Dimension(scroll.getPreferredSize().width, 300));

		this.add(scroll, BorderLayout.CENTER);
	}
	private void addEast() {
		Box boxEast = Box.createVerticalBox();
		this.add(boxEast, BorderLayout.EAST);

		btnThemHD = addButtonTo(boxEast, "Thêm hóa đơn", "img/add.png");
		btnXoaHD = addButtonTo(boxEast, "Xóa hóa đơn", "img/delete.png");
		btnSuaHD = addButtonTo(boxEast, "Sửa hóa đơn", "img/update.png");
		btnXoaRong = addButtonTo(boxEast, "Xoá trắng", "img/erase.png");
		btnXuatHoaDon = addButtonTo(boxEast, "Xuất hóa đơn", "");
	}
	private JTextField addInputItemTo(Box box, String name) {
		JLabel label = new JLabel(name);
		label.setFont(NORMAL_FONT);
		if (name != "Mã linh kiện" && name != "Số Lượng" && name != "Đơn giá")
			label.setPreferredSize(new Dimension(90, 25));
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnThemHD))
			themHoaDon();
		if (o.equals(btnXoaHD))
			xoaHoaDon();
		if (o.equals(btnXoaRong))
			xoaRong();
		if (o.equals(btnTimKiem))
			timKiem();
		if (o.equals(btnSuaHD))
			suaHoaDon();
		if (o.equals(btnThemCTHD))
			themCTHD();
		if (o.equals(btnSuaCTHD))
			suaCTHD();
		if (o.equals(btnXoaCTHD))
			xoaCTHD();
		if (o.equals(btnXuatHoaDon))
			xuatHD();;

	}
	private void deleteDataInTable() {
		while (modelHoaDon.getRowCount() > 0) {
			modelHoaDon.removeRow(0);
		}
	}
	private void deleteDataInTableCTHD() {
		while (modelCTHD.getRowCount() > 0) {
			modelCTHD.removeRow(0);
		}
	}
	public void loadAllDataToTable() {
		try {
			deleteDataInTable();

			ArrayList<HoaDon> list = dsHD.getAll();

			for (HoaDon item : list) {
				String[] row = { item.getMaHD(), item.getNhanVien().getMaNV(), item.getKhachHang().getMaKH(),
						item.getNgayLap().toString() };

				modelHoaDon.addRow(row);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	public void loadDataToChiTietHDTable(String ma) {
		try {

			deleteDataInTableCTHD();

			ArrayList<ChiTietHD> list = dsCTHD.timTheoMaHD(ma);

			for (ChiTietHD item : list) {
				LinhKien lk = item.getLinhKien();
				modelCTHD.addRow(new Object[] { lk.getMaLK(), item.getSoLuong(),
						NumberFormat.getInstance().format(item.getDonGia()),
						NumberFormat.getInstance().format(item.getSoLuong() * item.getDonGia()) });
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi trong khi truy xuất dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	private void loadDataToTable(ArrayList<HoaDon> data) {
		deleteDataInTable();

		for (HoaDon item : data) {
			String[] row = { item.getMaHD(), item.getNhanVien().getMaNV(), item.getKhachHang().getMaKH(),
					item.getNgayLap().toString() };

			modelHoaDon.addRow(row);
		}
	}
	public void focus() {
		txtTimKiem.requestFocus();
	}
	public void setPopupMenu(JPopupMenu popup) {
		((JComponent) txtMaHD).setComponentPopupMenu(popup);
		txtMaKH.setComponentPopupMenu(popup);
		txtMaNV.setComponentPopupMenu(popup);
		txtMalinhkien.setComponentPopupMenu(popup);
		txtNgayLap.setComponentPopupMenu(popup);
		txtTimKiem.setComponentPopupMenu(popup);
		txtSoLuong.setComponentPopupMenu(popup);
		tableCTHD.setComponentPopupMenu(popup);
		tableHoaDon.setComponentPopupMenu(popup);
	}
	private void addEvent() {
		btnThemHD.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoaHD.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSuaHD.addActionListener(this);
		btnThemCTHD.addActionListener(this);
		btnXoaCTHD.addActionListener(this);
		btnSuaCTHD.addActionListener(this);
		btnXuatHoaDon.addActionListener(this);
		tableHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				changeText();
				updateCTHDTable();
			}
		});
		tableCTHD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				changeCTHDText();
			}
		});
	}
	private void xuatHD() {
		int selectedRow = tableHoaDon.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this,
					"Bạn chưa chọn hóa đơn cần xuất, nhấp chọn vào hóa đơn cần xóa trong bảng!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel file (*.xlsx)", "xlsx");
		fileChooser.addChoosableFileFilter(excelFilter);
		fileChooser.setFileFilter(excelFilter);
		fileChooser.setSelectedFile(new File("HoaDon.xlsx"));
		
		int result = fileChooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile.exists()) {
				int x = JOptionPane.showConfirmDialog(this, "File đã tồn tại, bạn có muốn thay thế file này?",
						"Xác nhận", JOptionPane.OK_CANCEL_OPTION);
				if (x == JOptionPane.CANCEL_OPTION)
					return;

			}
			try {
				dsHD.xuatHoaDon(modelHoaDon.getValueAt(selectedRow, 0).toString(), selectedFile);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi kết nối", "Lỗi", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Lỗi file", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	private void xoaCTHD() {
		if (tableCTHD.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn chi tiết hóa đơn cần xóa!", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa!", "Xác nhận",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
			return;

		int i;
		while ((i = tableCTHD.getSelectedRow()) != -1) {
			String maHD = modelHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 0).toString();
			try {
				if (dsCTHD.xoaCTHD(maHD, modelCTHD.getValueAt(i, 0).toString())) {
					JOptionPane.showMessageDialog(this, "Xoá thành công!!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					modelCTHD.removeRow(i);
				} else {
					JOptionPane.showMessageDialog(this, "Xoá không thành công!!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	private void suaCTHD() {
		int i = tableCTHD.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn chi tiết hóa đơn cần sửa!", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		String maHD = modelHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 0).toString();

		String malk = txtMalinhkien.getText().trim();
		int soLuong = 0;
		double donGia = 0;
		LinhKien lk = null;
		HoaDon hd = null;

		try {
			soLuong = Integer.parseInt(txtSoLuong.getText());

			lk = new DanhSachLinhKien().timTheoMa(malk);
			if (lk == null) {
				JOptionPane.showMessageDialog(this, "Mã linh kiện không tồn tại!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			donGia = lk.getDonGia();

			hd = dsHD.timTheoMaHD(maHD);

			if (hd == null) {
				JOptionPane.showMessageDialog(this, "Mã hóa đơn không tồn tại!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			ChiTietHD cthd = new ChiTietHD(soLuong, donGia, hd, lk);

			if (dsCTHD.suaCTHD(maHD, lk.getMaLK(), cthd)) {
				modelCTHD.setValueAt(cthd.getLinhKien().getMaLK(), i, 0);
				modelCTHD.setValueAt(cthd.getSoLuong(), i, 1);
				modelCTHD.setValueAt(NumberFormat.getInstance().format(cthd.getDonGia()), i, 2);
				modelCTHD.setValueAt(NumberFormat.getInstance().format(cthd.thanhTien()), i, 3);
				JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(this, "Không thể sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Số lượng phải nhập số nguyên lớn hơn 0!!", "lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "lỗi truy xuất dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	private void themCTHD() {
		if (tableHoaDon.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String maHD = modelHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 0).toString();

		String malk = txtMalinhkien.getText().trim();
		int soLuong = 0;
		double donGia = 0;
		LinhKien lk = null;
		HoaDon hd = null;

		try {
			soLuong = Integer.parseInt(txtSoLuong.getText());

			lk = new DanhSachLinhKien().timTheoMa(malk);
			if (lk == null) {
				JOptionPane.showMessageDialog(this, "Mã linh kiện tồn tại!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			donGia = lk.getDonGia();

			hd = dsHD.timTheoMaHD(maHD);

			if (hd == null) {
				JOptionPane.showMessageDialog(this, "Mã hóa đơn tồn tại!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			ChiTietHD cthd = new ChiTietHD(soLuong, donGia, hd, lk);

			if (dsCTHD.themCTHD(cthd)) {
				modelCTHD.addRow(new Object[] { cthd.getLinhKien().getMaLK(), cthd.getSoLuong(), cthd.getDonGia(),
						cthd.thanhTien() });
				JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(this, "Đã tồn tại CTHD này!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Số lượng phải nhập số nguyên lớn hơn 0!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}
	private void suaHoaDon() {
		if (!isValidData())
			return;
		String maHD = txtMaHD.getText().trim();
		String maKH = txtMaKH.getText().trim();
		String maNV = txtMaNV.getText().trim();
		String ngayLap = txtNgayLap.getText().trim();

		try {
			NhanVien nv = new DanhSachNhanVien().timTheoMaNhanVien(maNV);
			if (nv == null) {
				JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại!", "Cảnh báo",
						JOptionPane.WARNING_MESSAGE);
				txtMaNV.requestFocus();
				return;
			}
			KhachHang kh = new DanhSachKhachHang().timKHTheoMa(maKH);
			if (kh == null) {
				JOptionPane.showMessageDialog(this, "Mã khách hàng không tồn tại!", "Lỗi", JOptionPane.WARNING_MESSAGE);
				txtMaKH.requestFocus();
				return;
			}
			HoaDon hd = new HoaDon(maHD, nv, kh, LocalDate.parse(ngayLap));
			if (dsHD.suaHoaDon(maHD, hd)) {
				for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
					if (modelHoaDon.getValueAt(i, 0).toString().equalsIgnoreCase(maHD)) {
						modelHoaDon.setValueAt(hd.getNhanVien().getMaNV(), i, 1);
						modelHoaDon.setValueAt(hd.getKhachHang().getMaKH(), i, 2);
						modelHoaDon.setValueAt(hd.getNgayLap(), i, 3);
						break;
					}
				}
				JOptionPane.showMessageDialog(this, "Sửa hóa đơn thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Sửa hóa đơn thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	private void updateCTHDTable() {
		int selected = tableHoaDon.getSelectedRow();
		if (selected == -1 || tableHoaDon.getSelectedRows().length > 1) {
			deleteDataInTableCTHD();
			return;
		}

		String maHD = (String) modelHoaDon.getValueAt(selected, 0);

		loadDataToChiTietHDTable(maHD);
	}
	private void timKiem() {
		if (txtTimKiem.getText().trim().isEmpty()) {
			loadAllDataToTable();
			return;
		}

		ArrayList<HoaDon> list = null;
		if (radMaHD.isSelected()) {
			try {
				HoaDon hd = dsHD.timTheoMaHD(txtTimKiem.getText().trim());
				if (hd != null) {
					list = new ArrayList<HoaDon>();
					list.add(hd);
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else if (radMaNV.isSelected()) {
			try {
				list = dsHD.timTheoMaNV(txtTimKiem.getText().trim());

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else if (radMaKH.isSelected()) {
			try {
				list = dsHD.timTheoMaKH(txtTimKiem.getText().trim());

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (list != null)
			loadDataToTable(list);
		else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private void xoaRong() {
		txtMaHD.setText("");
		txtMaNV.setText("");
		txtMaKH.setText("");
		txtNgayLap.setText("");
		txtSoLuong.setText("");
		txtMalinhkien.setText("");
		txtTimKiem.setText("");
		tableHoaDon.clearSelection();
		tableCTHD.clearSelection();
		radMaHD.setSelected(true);
	}
	private void changeCTHDText() {
		int selected = tableCTHD.getSelectedRow();
		if (selected == -1)
			return;
		if (tableHoaDon.getSelectedRows().length > 1) {
			txtMalinhkien.setText("");
			txtSoLuong.setText("");
			return;
		}

		txtMalinhkien.setText(modelCTHD.getValueAt(selected, 0).toString());
		txtSoLuong.setText(modelCTHD.getValueAt(selected, 1).toString());
	}
	private void changeText() {
		int selected = tableHoaDon.getSelectedRow();
		if (selected == -1)
			return;
		if (tableHoaDon.getSelectedRows().length > 1) {
			txtMaHD.setText("");
			txtMaNV.setText("");
			txtMaKH.setText("");
			txtNgayLap.setText("");
			txtSoLuong.setText("");
			txtMalinhkien.setText("");
			return;
		}

		txtMaHD.setText((String) modelHoaDon.getValueAt(selected, 0));
		txtMaNV.setText((String) modelHoaDon.getValueAt(selected, 1));
		txtMaKH.setText((String) modelHoaDon.getValueAt(selected, 2));
		txtNgayLap.setText((String) modelHoaDon.getValueAt(selected, 3));
	}
	private void xoaHoaDon() {
		int selectedRow = tableHoaDon.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this,
					"Bạn chưa chọn hóa đơn cần xóa, nhấp chọn vào hóa đơn cần xóa trong bảng!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa!", "Xác nhận",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
			return;

		int count = 0;
		while (tableHoaDon.getSelectedRow() != -1) {
			int selected = tableHoaDon.getSelectedRow();
			String maHD = (String) modelHoaDon.getValueAt(selected, 0);

			try {
				if (dsHD.xoaHoaDon(maHD)) {
					modelHoaDon.removeRow(selected);
					count++;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this,"Lỗi trong khi xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		JOptionPane.showMessageDialog(this,"Xóa thành công " + count + " hóa đơn!", "Thông báo",
				JOptionPane.INFORMATION_MESSAGE);
	}
	private boolean isValidData() {
		String maHD = txtMaHD.getText().trim();

		if (maHD.isEmpty() || !maHD.matches("HD\\d{2,3}")) {
			JOptionPane.showMessageDialog(this,
					"Mã hóa đơn không được bỏ trống và phải bắt đầu bằng 'HD', theo sau là 2 đến 3 kí tự số!",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			((JComponent) txtMaHD).requestFocus();
			return false;
		}

		String maKH = txtMaKH.getText().trim();

		if (maKH.isEmpty() || !maKH.matches("KH\\d{2,5}")) {
			JOptionPane.showMessageDialog(this,
					"Mã khách hàng không được bỏ trống và phải bắt đầu bằng 'KH', theo sau là 2 đến 5 kí tự số!!",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtMaKH.requestFocus();
			return false;
		}

		String ngayLap = txtNgayLap.getText().trim();
		if (!ngayLap.matches("\\d{4}-\\d{2}-\\d{2}")) {
			JOptionPane.showMessageDialog(this, "Ngày lập phải theo đinh dạng YYYY-MM-DD!", "Cáº£nh bÃ¡o",
					JOptionPane.WARNING_MESSAGE);
			txtNgayLap.requestFocus();
			return false;
		}
		return true;
	}
	private void themHoaDon() {
		if (!isValidData())
			return;

		String maHD = txtMaHD.getText().trim();
		String maKH = txtMaKH.getText().trim();

		NhanVien nv = new NhanVien(DatabaseConnect.userName.toUpperCase());
		KhachHang kh = new KhachHang(maKH);
		LocalDate ngayLap = LocalDate.now();

		HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

		try {
			String mess = dsHD.themHoaDon(hd);

			if (mess != null) {
				JOptionPane.showMessageDialog(this, mess, "Lỗi", JOptionPane.ERROR_MESSAGE);
			} else {
				modelHoaDon.addRow(new Object[] { maHD, nv.getMaNV(), maKH, ngayLap.toString() });
				tableHoaDon.addRowSelectionInterval(modelHoaDon.getRowCount() - 1, modelHoaDon.getRowCount() - 1);
				JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
	}
	

}
