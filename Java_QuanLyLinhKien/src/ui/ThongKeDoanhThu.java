package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DoanhThu;
import dao.ThongKe;

public class ThongKeDoanhThu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private JTextField txtnam;
	private JTable table;
	private JTextField txttongdt;
	private JLabel lbltitle;
	private JLabel lblthongke;
	private JLabel lblThongTin;
	private JRadioButton radthang;
	private JRadioButton radnam;
	private JComboBox<?> cbothang;
	private JLabel lblthang;
	private JLabel lblnam;
	private JButton btnketqua;
	private JScrollPane scrollPane;
	private JLabel lbltongdt;
	private Box boxChinh;
	private Box box1;
	private Box box2;
	private Box box3;
	private DefaultTableModel defaultTable;
	private JTable tableDT;
	private JScrollPane scroll;
	private JPanel pnsourth;
	private ThongKe thongKeDoanhThu;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ThongKeDoanhThu frame = new ThongKeDoanhThu();
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
	public ThongKeDoanhThu() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 650);
		setLayout(new BorderLayout());
		setLookAndFeel();
		addNorth();
		addCenter();
		addsourth();
		
		btnketqua.addActionListener(this);

	}

	private void addNorth() {
		boxChinh = Box.createVerticalBox();
		box1 = Box.createHorizontalBox();
		lbltitle = new JLabel("THỐNG KÊ DOANH THU ");
		lbltitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbltitle.setBackground(SystemColor.activeCaptionBorder);
		lbltitle.setBounds(191, 24, 389, 57);
		box1.add(lbltitle);
		boxChinh.add(box1);

		box2 = Box.createHorizontalBox();
		lblthongke = new JLabel("Thống kê theo : ");
		lblthongke.setFont(new Font("Times New Roman", Font.BOLD, 18));
		box2.add(lblthongke);

		radthang = new JRadioButton("Tháng");
		radthang.setBackground(SystemColor.controlHighlight);
		radthang.setFont(new Font("Times New Roman", Font.BOLD, 14));
		box2.add(radthang);

		radnam = new JRadioButton("Năm");
		radnam.setFont(new Font("Times New Roman", Font.BOLD, 14));
		radnam.setBackground(SystemColor.controlHighlight);
		ButtonGroup bg = new ButtonGroup();
		bg.add(radnam);
		bg.add(radthang);
		box2.add(radnam);
		boxChinh.add(box2);
		box3 = Box.createHorizontalBox();

		lblthang = new JLabel("Tháng : ");
		lblthang.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblthang.setBounds(85, 150, 76, 27);
		box3.add(lblthang);
		cbothang = new JComboBox();
		cbothang.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cbothang.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cbothang.setBounds(170, 155, 43, 21);
		box3.add(cbothang);

		lblnam = new JLabel("Năm : ");
		lblnam.setFont(new Font("Times New Roman", Font.BOLD, 18));

		box3.add(lblnam);

		txtnam = new JTextField(30);

		box3.add(txtnam);
		txtnam.setColumns(10);

		btnketqua = new JButton("Xuất doanh thu");
		btnketqua.setBackground(SystemColor.activeCaption);
		btnketqua.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnketqua.setBounds(480, 143, 132, 45);
		box3.add(btnketqua);
		boxChinh.add(box3);
		this.add(boxChinh, BorderLayout.NORTH);
	}

	private void addCenter() {
		String[] header = { "Mã nhân viên ", "Tên nhân viên", "Số linh kiện đã bán", "Doang thu của nhân viên", };
		defaultTable = new DefaultTableModel(header, 0);
		tableDT = new JTable(defaultTable) {
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
		tableDT.setFont(NORMAL_FONT);
		tableDT.setRowHeight(25);
		scroll = new JScrollPane(tableDT, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, BorderLayout.CENTER);
	}

	private void addsourth() {
		
		pnsourth = new JPanel();
		lblThongTin = new JLabel("");
		lblThongTin.setFont(new Font("Arial", Font.BOLD, 18));
		pnsourth.add(lblThongTin);
		lbltongdt = new JLabel(": ");
		lbltongdt.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lbltongdt.setBounds(20, 572, 152, 21);
		pnsourth.add(lbltongdt);

		txttongdt = new JTextField();
		txttongdt.setBackground(SystemColor.controlHighlight);
		txttongdt.setFont(new Font("Arial", Font.BOLD, 18));
		txttongdt.setEnabled(false);
		txttongdt.setEditable(false);
		txttongdt.setBounds(170, 575, 152, 19);
		pnsourth.add(txttongdt);
		txttongdt.setColumns(10);
		this.add(pnsourth, BorderLayout.SOUTH);
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

	private void thongKe() {
		thongKeDoanhThu = new ThongKe();
		int thang = cbothang.getSelectedIndex() + 1;
		int nam = Integer.parseInt(txtnam.getText().toString());
		double tam = 0;
		
		try {
			List<DoanhThu> list = null;
			if (radthang.isSelected()) {
				list = thongKeDoanhThu.thongKeTheoThang(thang, nam);
				lblThongTin.setText("Kết quả thống kê doanh thu tháng " + thang + " năm " + nam );
			} else {
				list = thongKeDoanhThu.thongKeTheoNam(nam);
				lblThongTin.setText("Kết quả thống kê doanh thu năm " + nam);
			}
			defaultTable.setRowCount(0);
			for (DoanhThu item : list) {
				defaultTable.addRow(
						new Object[] { item.getMaNV(), item.getTenNV(), item.getSoLKDaBan(), item.getDoanhThu() });
			}
			for(DoanhThu item: list) {
				tam += item.getDoanhThu();
			}
			
			
			txttongdt.setText(Double.toString(tam));

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình thống kê!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnketqua)) {
			thongKe();
		}
	}
}
