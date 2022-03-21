package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachKhachHang;
import entity.KhachHang;

public class QuanLyKhachHang extends JPanel implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel pN;
	private JLabel lblTitle;
	private JPanel pTitle;
	private DanhSachKhachHang dskh = new DanhSachKhachHang();
	private JLabel lblMa;
	private JTextField txtMa;
	private JTextField txtTen;
	private JLabel lblTen;
	private JComboBox<String> jcbGioiTinh;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTextField txtSdt;

	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JRadioButton radTimTheoMa;
	private JRadioButton radTimTheoTen;
	private ButtonGroup buttonGroup;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnThemKH;
	private JButton btnSuaTT;
	private JButton btnXoaKH;
	private JButton btnXoatrang;
	private DanhSachKhachHang dsKH;

	public QuanLyKhachHang() {

		this.setSize(750, 650);
		this.setLayout(new BorderLayout());
		setLookAndFeel();

		createGUI();

		btnThemKH.addActionListener(this);
		btnXoaKH.addActionListener(this);
		btnSuaTT.addActionListener(this);
		btnXoatrang.addActionListener(this);
		btnTimKiem.addActionListener(this);

		table.addMouseListener(this);

		dsKH = new DanhSachKhachHang();


	}

	public void createGUI() {
		pN = new JPanel();
		pN.setLayout(new BoxLayout(pN, BoxLayout.Y_AXIS));

		pTitle = new JPanel();

		pTitle.add(lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG"));
		lblTitle.setFont(new Font("Times new roman", Font.BOLD, 25));

		pN.add(pTitle);

		Box boxN = Box.createVerticalBox();
		pN.add(boxN);

		// hang1
		Box b1 = Box.createHorizontalBox();
		b1.add(Box.createHorizontalStrut(20));
		lblMa = new JLabel("Mã khách hàng");
		b1.add(lblMa);
		b1.add(Box.createHorizontalStrut(10));
		txtMa = new JTextField();
		b1.add(txtMa);

		b1.add(Box.createHorizontalStrut(18));
		lblTen = new JLabel("Tên khách hàng");
		b1.add(lblTen);
		b1.add(Box.createHorizontalStrut(10));
		txtTen = new JTextField();
		b1.add(txtTen);
		// hang2
		Box b2 = Box.createHorizontalBox();
		b2.add(Box.createHorizontalStrut(20));
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(lblMa.getPreferredSize());
		JLabel lblGioiTinh = new JLabel("Giới tính");
		String[] items = { "Nam", "Nữ" };
		jcbGioiTinh = new JComboBox<String>(items);
		b2.add(lblDiaChi);
		b2.add(Box.createHorizontalStrut(10));
		txtDiaChi = new JTextField();
		b2.add(txtDiaChi);
		b2.add(Box.createHorizontalStrut(20));
		b2.add(lblGioiTinh);
		b2.add(jcbGioiTinh);
		b2.add(Box.createHorizontalStrut(20));

		/// line 3
		Box b3 = Box.createHorizontalBox();
		b3.add(Box.createHorizontalStrut(20));
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setPreferredSize(lblMa.getPreferredSize());
		JLabel lblSdt = new JLabel("Số điện thoại");
		b3.add(lblEmail);
		b3.add(Box.createHorizontalStrut(10));
		txtEmail = new JTextField();

		b3.add(txtEmail);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(lblSdt);
		b3.add(Box.createHorizontalStrut(10));
		txtSdt = new JTextField();
		b3.add(txtSdt);
		b3.add(Box.createHorizontalStrut(20));

		// timkiem
		JPanel pnlTimKiem = new JPanel();
		txtTimKiem = new JTextField(50);

		btnTimKiem = new JButton("Tìm kiếm", new ImageIcon("img/search.png"));
		pnlTimKiem.add(btnTimKiem);
		pnlTimKiem.add(txtTimKiem);
		JPanel pnlTimTuyChon = new JPanel();
		pnlTimTuyChon.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		radTimTheoMa = new JRadioButton("Tìm theo mã");
		radTimTheoMa.setSelected(true);
		radTimTheoTen = new JRadioButton("Tìm theo tên");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radTimTheoMa);
		buttonGroup.add(radTimTheoTen);
		pnlTimTuyChon.add(radTimTheoMa);
		pnlTimTuyChon.add(radTimTheoTen);

		Box b4 = Box.createHorizontalBox();
		b4.add(Box.createHorizontalStrut(20));
		b4.add(pnlTimKiem);

		Box b5 = Box.createHorizontalBox();
		b5.add(Box.createHorizontalStrut(20));
		b5.add(pnlTimTuyChon);

		boxN.add(b1);
		boxN.add(Box.createVerticalStrut(10));
		boxN.add(b2);
		boxN.add(Box.createVerticalStrut(10));
		boxN.add(b3);
		boxN.add(Box.createVerticalStrut(10));
		boxN.add(b4);
//		boxN.add(Box.createVerticalStrut(10));
		boxN.add(b5);

		Box boxCenter = Box.createHorizontalBox();

		String[] header = { "Mã khách hàng", "Tên khách hàng", "Giới tính", "Địa chỉ", "Số điện thoại", "Email" };
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setRowHeight(25);

		JScrollPane pane = new JScrollPane(table);

		boxCenter.add(pane);

		Box boxEast = Box.createVerticalBox();

		btnThemKH = new JButton("Thêm", new ImageIcon("img/add.png"));
		btnSuaTT = new JButton("Sửa", new ImageIcon("img/update.png"));
		btnXoaKH = new JButton("Xóa", new ImageIcon("img/delete.png"));
		btnXoatrang = new JButton("Xóa trắng", new ImageIcon("img/erase.png"));

		Dimension d = new Dimension(150, 80);
		btnThemKH.setPreferredSize(d);
		btnXoaKH.setPreferredSize(d);
		btnSuaTT.setPreferredSize(d);
		btnXoatrang.setPreferredSize(d);

		Box boxThem = Box.createHorizontalBox();
		boxThem.add(Box.createHorizontalGlue());
		boxThem.add(btnThemKH);
		boxThem.add(Box.createHorizontalGlue());

		Box boxXoa = Box.createHorizontalBox();
		boxXoa.add(Box.createHorizontalGlue());
		boxXoa.add(btnXoaKH);
		boxXoa.add(Box.createHorizontalGlue());

		Box boxSua = Box.createHorizontalBox();
		boxSua.add(Box.createHorizontalGlue());
		boxSua.add(btnSuaTT);
		boxSua.add(Box.createHorizontalGlue());

		Box boxXoaTrang = Box.createHorizontalBox();
		boxXoaTrang.add(Box.createHorizontalGlue());
		boxXoaTrang.add(btnXoatrang);
		boxXoaTrang.add(Box.createHorizontalGlue());

		boxEast.add(boxThem);
		boxEast.add(boxSua);
		boxEast.add(boxXoa);
		boxEast.add(boxXoaTrang);

		this.add(pN, BorderLayout.NORTH);
		this.add(boxCenter, BorderLayout.CENTER);
		this.add(boxEast, BorderLayout.EAST);
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

	public void updateTableData() {
		xoabang();
		try {
			List<KhachHang> list = dskh.getAll();
			for (KhachHang s : list) {
				String[] row = { s.getMaKH(), s.getTenKH(), s.isGioiTinh() ? "Nam" : "Nữ", s.getDiaChi(), s.getSdt(),
						s.getEmail(), };
				model.addRow(row);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void xoabang() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMa.setText(table.getValueAt(row, 0).toString());
		txtTen.setText(table.getValueAt(row, 1).toString());
		txtDiaChi.setText(table.getValueAt(row, 3).toString());
		jcbGioiTinh.setSelectedItem(table.getValueAt(row, 2).toString());

		Object sdt = table.getValueAt(row, 4);
		Object email = table.getValueAt(row, 5);
		if (sdt != null)
			txtSdt.setText(sdt.toString());
		else
			txtSdt.setText("");
		if (email != null)
			txtEmail.setText(email.toString());
		else
			txtEmail.setText("");

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		try {
			if (o.equals(btnThemKH))
				themKhachHang();
			else if (o.equals(btnXoaKH))
				xoaKhachHang();
			else if (o.equals(btnSuaTT))
				suaKhachHang();
			else if (o.equals(btnTimKiem) && radTimTheoMa.isSelected()) {
				timTheoMa();
			} else if (o.equals(btnTimKiem) && radTimTheoTen.isSelected())
				timTheoTen();
			else if (o.equals(btnXoatrang))
				xoaRong();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	private void xoaRong() {
		txtMa.setText("");
		txtTen.setText("");
		txtDiaChi.setText("");
		txtSdt.setText("");
		txtEmail.setText("");

		table.clearSelection();
		jcbGioiTinh.setSelectedIndex(0);
//	rdbtnTmTheoM.setSelected(true);
	}

	private void timTheoTen() throws SQLException {
		if (txtTimKiem.getText().trim().equals(""))
			updateTableData();
		else {
			List<KhachHang> dskh = new ArrayList<KhachHang>();
			dskh = dsKH.timTheoTenKhachHang(txtTimKiem.getText().trim());
			if (dskh == null) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
			} else {
				xoabang();
				for (KhachHang kh : dskh) {
					model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(),kh.isGioiTinh() ? "Nam" : "Nữ", kh.getDiaChi(),
							 kh.getSdt(), kh.getEmail() });
				}
			}
		}

	}

	private void timTheoMa() throws SQLException {
		if (txtTimKiem.getText().trim().equals(""))
			updateTableData();
		else {
			KhachHang kh = new KhachHang();
			kh = dsKH.timTheoMa(txtTimKiem.getText().trim());
			if (kh == null)
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!");
			else {
				xoabang();
				model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(),kh.isGioiTinh() ? "Nam" : "Nữ", kh.getDiaChi(),
						 kh.getSdt(), kh.getEmail() });
			}
		}

	}

	private void suaKhachHang() throws SQLException {
		int row = table.getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(this, "Chọn khách hàng cần sửa");
		else {
			String ma = txtMa.getText().trim();
			String hoTen = txtTen.getText().trim();
			boolean gioiTinh = jcbGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false;
			String diaChi = txtDiaChi.getText().trim();
			String sdt = txtSdt.getText().trim();
			String email = txtEmail.getText().trim();
			KhachHang kh = new KhachHang(ma, hoTen, gioiTinh, diaChi, sdt, email);

			if (dsKH.suaTTKhachHang(kh)) {
				updateTableData();
				JOptionPane.showMessageDialog(this, "Sửa thành công");
			} else {
				String maCu = (String) model.getValueAt(row, 0);
				KhachHang khCu = new KhachHang(maCu);
				if (!khCu.equals(maCu)) {
					JOptionPane.showMessageDialog(this, "Không được sửa mã Khách hàng!!");
					txtMa.requestFocus();
					txtMa.selectAll();
				} else
					JOptionPane.showMessageDialog(this, "Sửa không thành công");
			}

		}

	}

	private void xoaKhachHang() throws SQLException {
		int row = table.getSelectedRow();
		if (row == -1)
			JOptionPane.showMessageDialog(this, "Phải chọn khách hàng cần xóa");
		else {
			int replay = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dòng này ?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (replay == JOptionPane.YES_OPTION) {
				List<KhachHang> dskh = dsKH.getAll();
				int rows = table.getSelectedRow();
				if (rows >= 0 || rows < dskh.size()) {
					KhachHang kh = dskh.get(rows);
					if (dsKH.xoaKhachHang(kh)) {
						updateTableData();
						xoaRong();
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					} else
						JOptionPane.showMessageDialog(this, "Xóa không thành công");
				}
			}
		}

	}

	private void themKhachHang() {
		if (validData()) {
			String ma = txtMa.getText().trim();
			String hoTen = txtTen.getText().trim();
			boolean gioiTinh = jcbGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false;
			String diaChi = txtDiaChi.getText().trim();
			String sdt = txtSdt.getText().trim();
			String email = txtEmail.getText().trim();

			KhachHang kh = new KhachHang(ma, hoTen, gioiTinh, diaChi, sdt, email);
			try {
				if (dsKH.themKhachHang(kh)) {
					xoabang();
					updateTableData();
//				loadDataToTable();
					xoaRong();
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				} else
					JOptionPane.showMessageDialog(this, "Thêm thất bại");
			} catch (HeadlessException | SQLException e) {
				JOptionPane.showMessageDialog(this, "Trùng mã");
			}
		}

	}

	private boolean validData() {
		String ma = txtMa.getText().trim();
		String hoTen = txtTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String std = txtSdt.getText().trim();
		String email = txtEmail.getText().trim();

		Pattern pattern = Pattern.compile("KH[0-9]{2,5}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(ma);
		boolean match_ma = matcher.matches();
		if (ma.length() < 1) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng không được để trống");
			return false;
		} else if (!match_ma) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng không đúng định dạng");
			return false;
		} else if (hoTen.length() < 1) {
			JOptionPane.showMessageDialog(this, "Tên không được để trống");
			return false;
		} else if (!hoTen.matches("[\\p{L}a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không chứa số và kí tự đặc biệt");
			return false;
		} else if (diaChi.length() < 1) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
			return false;
		}

		else if (!email.isEmpty()) {
			if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
				JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
				return false;
			}
		} else if (std.length() == 0) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
			return false;
		} else if (!std.matches("0[0-9]{9}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không đúng");
			return false;
		}

		return true;
	}

	public void setPopupMenu(JPopupMenu popup) {
		txtMa.setComponentPopupMenu(popup);
		txtTen.setComponentPopupMenu(popup);
		txtDiaChi.setComponentPopupMenu(popup);
		txtEmail.setComponentPopupMenu(popup);
		txtSdt.setComponentPopupMenu(popup);
	}
}
