package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import connect.DatabaseConnect;
import dao.QuanLyDangNhap;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DangNhap extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDangNhap;
	private JPasswordField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DangNhap frame = new DangNhap();
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
	public DangNhap() {
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setOpacity(0.95f);
		this.setTitle("VUA LINH KIỆN");
//		this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		this.setResizable(false);

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/linhkien.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 850, 485);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.LIGHT_GRAY);
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null);
		setLookAndFeel();

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(352, 0, 508, 485);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTitle = new JLabel("Đăng nhập vào VUA LINH KIỆN");
		lblTitle.setForeground(new Color(255, 69, 0));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(61, 36, 378, 77);
		panel_1.add(lblTitle);
//		panel_1.setBackground(new Color(0xFFFFFF));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(50, 113, 378, 211);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTenDN = new JLabel("Tài khoản: ");
		lblTenDN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTenDN.setBounds(10, 29, 126, 27);
		panel_2.add(lblTenDN);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMatKhau.setBounds(10, 124, 126, 27);
		panel_2.add(lblMatKhau);

		txtDangNhap = new JTextField();
		txtDangNhap.setToolTipText("Nhập tên đăng nhập của bạn");
		txtDangNhap.setBounds(51, 66, 317, 39);
		panel_2.add(txtDangNhap);
		txtDangNhap.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setToolTipText("Nhập mật khẩu");
		txtMatKhau.setBounds(51, 161, 317, 40);
		panel_2.add(txtMatKhau);

		JLabel lblAnhDN = new JLabel("");
		lblAnhDN.setIcon(new ImageIcon("img/username.png"));
		lblAnhDN.setBounds(20, 66, 45, 39);
		panel_2.add(lblAnhDN);

		JLabel lblAnhMK = new JLabel("");
		lblAnhMK.setIcon(new ImageIcon("img/password.png"));
		lblAnhMK.setBounds(20, 161, 45, 39);
		panel_2.add(lblAnhMK);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(50, 344, 376, 74);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDangNhap.setBounds(53, 10, 133, 54);
		panel_3.add(btnDangNhap);

		btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(233, 10, 133, 54);
		panel_3.add(btnThoat);

//		JLabel lblTenCongTy = new JLabel("VUA LINH KIỆN");
//		lblTenCongTy.setToolTipText("");
//		lblTenCongTy.setHorizontalAlignment(SwingConstants.CENTER);
//		lblTenCongTy.setForeground(Color.WHITE);
//		lblTenCongTy.setFont(new Font("Tahoma", Font.BOLD, 27));
//		lblTenCongTy.setBounds(10, 163, 329, 93);
//		contentPane.add(lblTenCongTy);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon("img/logolk.png"));
		lblLogo.setToolTipText("");
		lblLogo.setBounds(10, 34, 329, 250);
		contentPane.add(lblLogo);

		JLabel lblSlogan1 = new JLabel("CHÚNG TÔI SỐ 2 - KHÔNG AI SỐ 1");
		lblSlogan1.setToolTipText("");
		lblSlogan1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlogan1.setForeground(new Color(165, 42, 42));
		lblSlogan1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSlogan1.setBounds(10, 286, 329, 52);
		contentPane.add(lblSlogan1);

		JLabel lblSlogan2 = new JLabel("CAM KẾT CHÍNH HÃNG - GIÁ TỐT");
		lblSlogan2.setToolTipText("");
		lblSlogan2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlogan2.setForeground(new Color(165, 42, 42));
		lblSlogan2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSlogan2.setBounds(10, 316, 329, 45);
		contentPane.add(lblSlogan2);

		JLabel lblHotline = new JLabel("HotLine: 0896639172 - 0886680151");
		lblHotline.setToolTipText("");
		lblHotline.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotline.setForeground(Color.BLUE);
		lblHotline.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHotline.setBounds(41, 425, 363, 28);
		contentPane.add(lblHotline);

		JLabel lblDiachi = new JLabel("Đ/c:  12, Nguyễn Văn Bảo, Gò Vấp");
		lblDiachi.setToolTipText("");
		lblDiachi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiachi.setForeground(Color.BLUE);
		lblDiachi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiachi.setBounds(98, 447, 266, 28);
		contentPane.add(lblDiachi);
		
		addEvent();
	}

	private void addEvent() {
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		btnDangNhap.addKeyListener(this);
		btnThoat.addKeyListener(this);
		txtMatKhau.addKeyListener(this);
		txtDangNhap.addKeyListener(this);
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
	
	private String getMatKhau(char[] pass) {
		String s= "";
		for(int i = 0; i < pass.length; i++)
			s+= pass[i];
		return s;
	}
	
	private void dangNhap() {
		if(txtDangNhap.getText().isEmpty() || txtMatKhau.getPassword().length <= 0) {
			JOptionPane.showMessageDialog(this, "Tên tài khoản và mật khẩu không được bỏ trống!", "Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		DatabaseConnect.userName = txtDangNhap.getText();
		DatabaseConnect.password = getMatKhau(txtMatKhau.getPassword());
		
		try {
			Connection conn = DatabaseConnect.getConnection();
			this.setVisible(false);
			new ThanhQuanLy(QuanLyDangNhap.laQuanLyVien()).setVisible(true);
//			JOptionPane.showMessageDialog(this, "Dang nhap thanh cong");
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Không thể kết nối!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThoat))
			System.exit(0);
		if (o.equals(btnDangNhap))
			dangNhap();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (o.equals(btnDangNhap))
				dangNhap();
			if (o.equals(txtMatKhau))
				dangNhap();
			if (o.equals(btnThoat))
				System.exit(0);
			if (o.equals(txtDangNhap))
				txtDangNhap.transferFocus();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
