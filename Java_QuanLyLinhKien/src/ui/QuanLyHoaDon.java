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

		JLabel lblHeader = new JLabel("QU???N L?? H??A ????N");
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

		JLabel lblTimTheo = new JLabel("T??m theo");
		radMaHD = new JRadioButton("M?? h??a ????n");
		radMaHD.setSelected(true);
		radMaNV = new JRadioButton("M?? nh??n vi??n");
		radMaKH = new JRadioButton("M?? kh??ch h??ng");

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

		txtMaHD = addInputItemTo(boxLeft, "M?? h??a ????n");
		txtMaNV = addInputItemTo(boxRight, "M?? nh??n vi??n");
		txtMaKH = addInputItemTo(boxLeft, "M?? kh??ch h??ng");
		txtNgayLap = addInputItemTo(boxRight, "Ng??y l???p");

		JLabel lblChiTietHD = new JLabel("Chi ti???t h??a ????n");
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
		txtMalinhkien = addInputItemTo(boxCTHDInput, "M?? linh ki???n");
		boxCTHDInput.add(Box.createHorizontalStrut(5));
		txtSoLuong = addInputItemTo(boxCTHDInput, "S??? l?????ng");
		boxCTHDInput.add(Box.createHorizontalStrut(10));

		String[] cthdHeader = { "M?? linh ki???n", "S??? l?????ng", "????n gi??", "Th??nh ti???n" };
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
		btnThemCTHD = addButtonTo(boxCTHDButton, "Th??m chi ti???t", "img/add.png");
		btnXoaCTHD = addButtonTo(boxCTHDButton, "X??a chi ti???t", "img/delete.png");
		btnSuaCTHD = addButtonTo(boxCTHDButton, "S???a chi ti???t", "img/update.png");

		boxNorth.add(Box.createVerticalStrut(20));
	}
	private void addCenter() {
		String[] hoaDonHeader = { "M?? h??a ????n", "M?? nh??n vi??n", "M?? kh??ch h??ng", "Ng??y l???p" };
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

		btnThemHD = addButtonTo(boxEast, "Th??m h??a ????n", "img/add.png");
		btnXoaHD = addButtonTo(boxEast, "X??a h??a ????n", "img/delete.png");
		btnSuaHD = addButtonTo(boxEast, "S???a h??a ????n", "img/update.png");
		btnXoaRong = addButtonTo(boxEast, "Xo?? tr???ng", "img/erase.png");
		btnXuatHoaDon = addButtonTo(boxEast, "Xu???t h??a ????n", "");
	}
	private JTextField addInputItemTo(Box box, String name) {
		JLabel label = new JLabel(name);
		label.setFont(NORMAL_FONT);
		if (name != "M?? linh ki???n" && name != "S??? L?????ng" && name != "????n gi??")
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
			JOptionPane.showMessageDialog(this, "L???i k???t n???i!", "L???i", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(this, "L???i trong khi truy xu???t d??? li???u!", "L???i", JOptionPane.ERROR_MESSAGE);
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
					"B???n ch??a ch???n h??a ????n c???n xu???t, nh???p ch???n v??o h??a ????n c???n x??a trong b???ng!", "Th??ng b??o",
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
				int x = JOptionPane.showConfirmDialog(this, "File ???? t???n t???i, b???n c?? mu???n thay th??? file n??y?",
						"X??c nh???n", JOptionPane.OK_CANCEL_OPTION);
				if (x == JOptionPane.CANCEL_OPTION)
					return;

			}
			try {
				dsHD.xuatHoaDon(modelHoaDon.getValueAt(selectedRow, 0).toString(), selectedFile);

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "L???i k???t n???i", "L???i", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "L???i file", "L???i", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	private void xoaCTHD() {
		if (tableCTHD.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Ch??a ch???n chi ti???t h??a ????n c???n x??a!", "C???nh b??o",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (JOptionPane.showConfirmDialog(this, "B???n c?? ch???c ch???n mu???n x??a!", "X??c nh???n",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
			return;

		int i;
		while ((i = tableCTHD.getSelectedRow()) != -1) {
			String maHD = modelHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 0).toString();
			try {
				if (dsCTHD.xoaCTHD(maHD, modelCTHD.getValueAt(i, 0).toString())) {
					JOptionPane.showMessageDialog(this, "Xo?? th??nh c??ng!!", "Th??ng b??o",
							JOptionPane.INFORMATION_MESSAGE);
					modelCTHD.removeRow(i);
				} else {
					JOptionPane.showMessageDialog(this, "Xo?? kh??ng th??nh c??ng!!", "Th??ng b??o",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "L???i khi x??a d??? li???u!!", "L???i", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	private void suaCTHD() {
		int i = tableCTHD.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(this, "Ch??a ch???n chi ti???t h??a ????n c???n s???a!", "C???nh b??o",
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
				JOptionPane.showMessageDialog(this, "M?? linh ki???n kh??ng t???n t???i!", "Th??ng b??o",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			donGia = lk.getDonGia();

			hd = dsHD.timTheoMaHD(maHD);

			if (hd == null) {
				JOptionPane.showMessageDialog(this, "M?? h??a ????n kh??ng t???n t???i!", "Th??ng b??o",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			ChiTietHD cthd = new ChiTietHD(soLuong, donGia, hd, lk);

			if (dsCTHD.suaCTHD(maHD, lk.getMaLK(), cthd)) {
				modelCTHD.setValueAt(cthd.getLinhKien().getMaLK(), i, 0);
				modelCTHD.setValueAt(cthd.getSoLuong(), i, 1);
				modelCTHD.setValueAt(NumberFormat.getInstance().format(cthd.getDonGia()), i, 2);
				modelCTHD.setValueAt(NumberFormat.getInstance().format(cthd.thanhTien()), i, 3);
				JOptionPane.showMessageDialog(this, "S???a th??nh c??ng!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(this, "Kh??ng th??? s???a!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "S??? l?????ng ph???i nh???p s??? nguy??n l???n h??n 0!!", "l???i",
					JOptionPane.ERROR_MESSAGE);
			return;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "l???i truy xu???t d??? li???u", "L???i", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	private void themCTHD() {
		if (tableHoaDon.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Ch??a ch???n h??a ????n !", "C???nh b??o", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "M?? linh ki???n t???n t???i!", "Th??ng b??o",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			donGia = lk.getDonGia();

			hd = dsHD.timTheoMaHD(maHD);

			if (hd == null) {
				JOptionPane.showMessageDialog(this, "M?? h??a ????n t???n t???i!", "Th??ng b??o",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			ChiTietHD cthd = new ChiTietHD(soLuong, donGia, hd, lk);

			if (dsCTHD.themCTHD(cthd)) {
				modelCTHD.addRow(new Object[] { cthd.getLinhKien().getMaLK(), cthd.getSoLuong(), cthd.getDonGia(),
						cthd.thanhTien() });
				JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(this, "???? t???n t???i CTHD n??y!", "Th??ng b??o",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "S??? l?????ng ph???i nh???p s??? nguy??n l???n h??n 0!", "L???i",
					JOptionPane.ERROR_MESSAGE);
			return;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "L???i khi truy xu???t d??? li???u!", "L???i", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "M?? nh??n vi??n kh??ng t???n t???i!", "C???nh b??o",
						JOptionPane.WARNING_MESSAGE);
				txtMaNV.requestFocus();
				return;
			}
			KhachHang kh = new DanhSachKhachHang().timKHTheoMa(maKH);
			if (kh == null) {
				JOptionPane.showMessageDialog(this, "M?? kh??ch h??ng kh??ng t???n t???i!", "L???i", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "S???a h??a ????n th??nh c??ng!", "Th??ng b??o",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "S???a h??a ????n th??nh c??ng!", "Th??ng b??o",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "L???i khi t??m ki???m d??? li???u!", "L???i", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "L???i khi t??m ki???m d??? li???u!", "L???i", JOptionPane.ERROR_MESSAGE);
			}
		} else if (radMaNV.isSelected()) {
			try {
				list = dsHD.timTheoMaNV(txtTimKiem.getText().trim());

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "L???i khi t??m ki???m d??? li???u!", "L???i", JOptionPane.ERROR_MESSAGE);
			}
		} else if (radMaKH.isSelected()) {
			try {
				list = dsHD.timTheoMaKH(txtTimKiem.getText().trim());

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "L???i khi t??m ki???m d??? li???u!", "L???i", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (list != null)
			loadDataToTable(list);
		else {
			JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
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
					"B???n ch??a ch???n h??a ????n c???n x??a, nh???p ch???n v??o h??a ????n c???n x??a trong b???ng!", "Th??ng b??o",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (JOptionPane.showConfirmDialog(this, "B???n c?? ch???c ch???n mu???n x??a!", "X??c nh???n",
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
				JOptionPane.showMessageDialog(this,"L???i trong khi x??a!", "L???i", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		JOptionPane.showMessageDialog(this,"X??a th??nh c??ng " + count + " h??a ????n!", "Th??ng b??o",
				JOptionPane.INFORMATION_MESSAGE);
	}
	private boolean isValidData() {
		String maHD = txtMaHD.getText().trim();

		if (maHD.isEmpty() || !maHD.matches("HD\\d{2,3}")) {
			JOptionPane.showMessageDialog(this,
					"M?? h??a ????n kh??ng ???????c b??? tr???ng v?? ph???i b???t ?????u b???ng 'HD', theo sau l?? 2 ?????n 3 k?? t??? s???!",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			((JComponent) txtMaHD).requestFocus();
			return false;
		}

		String maKH = txtMaKH.getText().trim();

		if (maKH.isEmpty() || !maKH.matches("KH\\d{2,5}")) {
			JOptionPane.showMessageDialog(this,
					"M?? kh??ch h??ng kh??ng ???????c b??? tr???ng v?? ph???i b???t ?????u b???ng 'KH', theo sau l?? 2 ?????n 5 k?? t??? s???!!",
					"C???nh b??o", JOptionPane.WARNING_MESSAGE);
			txtMaKH.requestFocus();
			return false;
		}

		String ngayLap = txtNgayLap.getText().trim();
		if (!ngayLap.matches("\\d{4}-\\d{2}-\\d{2}")) {
			JOptionPane.showMessageDialog(this, "Ng??y l???p ph???i theo ??inh d???ng YYYY-MM-DD!", "C??????nh b????o",
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
				JOptionPane.showMessageDialog(this, mess, "L???i", JOptionPane.ERROR_MESSAGE);
			} else {
				modelHoaDon.addRow(new Object[] { maHD, nv.getMaNV(), maKH, ngayLap.toString() });
				tableHoaDon.addRowSelectionInterval(modelHoaDon.getRowCount() - 1, modelHoaDon.getRowCount() - 1);
				JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "L???i k???t n???i!", "L???i", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
	}
	

}
