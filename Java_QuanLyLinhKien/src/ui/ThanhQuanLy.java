package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import dao.QuanLyHoSo;

public class ThanhQuanLy extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Box boxMenu;
	private final Font H1_FONT = new Font("Times new roman", Font.BOLD, 22);
	private final Font NORMAL_FONT = new Font("Time new roman", Font.PLAIN, 12);
	private final Color H1_COLOR = new Color(0xF3F3F3);
	private final Color ACTIVE_COLOR = new Color(0xd6d9df);
	private final Color NORTH_BACKGROUND = new Color(0x393742);
	private final Color WEST_BACKGROUND = new Color(0x2C2A33);
	private final Color CENTER_BACKGROUND = new Color(0xd6d9df);
	private Component selectedMenuItem = null;

	private JPanel pnlCenter;
	private JPanel pnlCenterOfCenter;
	private JScrollPane scrollMenu;
	private QuanLyHoaDon pnlQLHD;
	private QuanLyLinhKien pnlQLLK;
	private HoSoNhanVien pnlHSNV;
	private QuanLyKhachHang pnlQLKH;
	private QuanLyNhanVien pnlQLNV;
	private ThongKeDoanhThu pnlTKDT;
	private JPanel menuTrangChu;
	private JPanel menuHoSo;
	private JPanel menuQLKH;
	private JPanel menuQLNV;
	private JPanel menuQLHD;
	private JPanel menuQLLK;
	private JPanel menuTKDT;
	private JPanel menuDX;
	private TrangChu pnlTrangChu;
	private boolean isManager;
	private JPopupMenu popMenu;
	private JMenuItem miCut;
	private JMenuItem miCopy;
	private JMenuItem miPaste;
	private String tempData = "";
	private JLabel lblUserName;

	public QuanLyLinhKien getPnlQLLK() {
		return pnlQLLK;
	}

	public QuanLyHoaDon getPnlQLHD() {
		return pnlQLHD;
	}

	public HoSoNhanVien getPnlHSNV() {
		return pnlHSNV;
	}

	public QuanLyKhachHang getPnlQLKH() {
		return pnlQLKH;
	}

	public QuanLyNhanVien getPnlQLNV() {
		return pnlQLNV;
	}

	public ThongKeDoanhThu getPnlTKDT() {
		return pnlTKDT;
	}

	public ThanhQuanLy(boolean isManager) {
		this.isManager = isManager;
		this.setSize(900, 750);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Quản Lý Mua Bán Linh Kiện");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/linhkien.png"));

		setLookAndFeel();

		pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.setBorder(null);
		pnlCenter.setBackground(null);

		getContentPane().add(pnlCenter, BorderLayout.CENTER);

		addCenterOfCenter();
		addNorth();

		ImageIcon icon = new ImageIcon("img/linhkien.png");
		setIconImage(icon.getImage());

		popMenu = new JPopupMenu();

		miCut = new JMenuItem("Cut", new ImageIcon("img/cut.png"));
		miCopy = new JMenuItem("Copy", new ImageIcon("img/copy.png"));
		miPaste = new JMenuItem("Paste", new ImageIcon("img/paste.png"));
		miPaste.setEnabled(false);

		popMenu.add(miCut);
		popMenu.add(miCopy);
		popMenu.add(miPaste);

		addEvent();

		pnlTrangChu = new TrangChu(this, isManager);
		pnlQLHD = new QuanLyHoaDon();
		pnlQLLK = new QuanLyLinhKien();
		pnlQLKH = new QuanLyKhachHang();
		pnlHSNV = new HoSoNhanVien(isManager);
		pnlQLHD.setPopupMenu(popMenu);
		pnlQLLK.setPopupMenu(popMenu);
		pnlQLKH.setPopupMenu(popMenu);
		pnlQLNV = new QuanLyNhanVien();
		pnlQLNV.setPopupMenu(popMenu);
		pnlTKDT = new ThongKeDoanhThu();

		setUser();
	}

	private void addEvent() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				menuTrangChu.setBackground(ACTIVE_COLOR);
				changePanel(pnlTrangChu);
				selectedMenuItem = menuTrangChu;
			}

			@Override
			public void windowClosing(WindowEvent e) {
				warningBeforeClose();
			}
		});

		menuQLHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(pnlQLHD);
			}
		});
		menuQLLK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(pnlQLLK);
			}
		});
		menuQLKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

					changePanel(pnlQLKH);
				
			}
		});
		menuHoSo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(pnlHSNV);
				pnlHSNV.loadDataFromDatabaseToPanel();
			}
		});
		menuTrangChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(pnlTrangChu);
			}
		});
		menuDX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dangXuat();
			}
		});
		if (isManager) {
			menuQLNV.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					changePanel(pnlQLNV);
				}
			});
			menuTKDT.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					changePanel(pnlTKDT);
				}
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(miCut))
			cutData();
		if (o.equals(miCopy))
			copyData();
		if (o.equals(miPaste))
			pasteData();

	}

	private void setUser() {
		QuanLyHoSo qlhs = new QuanLyHoSo();
		try {
			qlhs.getProfile();
			lblUserName.setText(qlhs.getNhanVien().getHoTenNV());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}

	private void addCenterOfCenter() {
		pnlCenterOfCenter = new JPanel(new BorderLayout());
		pnlCenterOfCenter.setBackground(CENTER_BACKGROUND);
		pnlCenterOfCenter.setBorder(null);

		JScrollPane scroll = new JScrollPane(pnlCenterOfCenter);
		scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));

		pnlCenter.add(scroll, BorderLayout.CENTER);
	}

	public JPanel getCenter() {
		return pnlCenterOfCenter;
	}

//	private void addSouth() {
//		JPanel pnlSouth = new JPanel();
//		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
//	}

	private void addNorth() {
		JPanel pnlNorthOfCenter = new JPanel();
		pnlNorthOfCenter.setLayout(new BoxLayout(pnlNorthOfCenter, BoxLayout.Y_AXIS));
		pnlNorthOfCenter.setBackground(NORTH_BACKGROUND);

		pnlCenter.add(pnlNorthOfCenter, BorderLayout.NORTH);

		JLabel lblIcon = new JLabel(new ImageIcon("img/icon.png"));

		JLabel lblHeader = new JLabel("VUA LINH KIỆN");
		lblHeader.setFont(H1_FONT);
		lblHeader.setForeground(H1_COLOR);
		lblHeader.setHorizontalAlignment(JLabel.CENTER);

		JPanel pnlNorth = new JPanel(new BorderLayout());
		pnlNorth.setBackground(null);

		pnlNorthOfCenter.add(Box.createVerticalStrut(5));
		pnlNorthOfCenter.add(pnlNorth);
		pnlNorthOfCenter.add(Box.createVerticalStrut(5));

		JButton btnUser = new JButton();
		btnUser.setLayout(new BoxLayout(btnUser, BoxLayout.Y_AXIS));

		JLabel lblUserIcon = new JLabel(new ImageIcon("img/user.png"));
		lblUserName = new JLabel("User name");

		Box boxUserIcon = Box.createHorizontalBox();
		boxUserIcon.add(Box.createHorizontalGlue());
		boxUserIcon.add(lblUserIcon);
		boxUserIcon.add(Box.createHorizontalGlue());

		Box boxUserName = Box.createHorizontalBox();
		boxUserName.add(Box.createHorizontalGlue());
		boxUserName.add(lblUserName);
		boxUserName.add(Box.createHorizontalGlue());

		btnUser.add(boxUserIcon);
		btnUser.add(boxUserName);

		pnlNorth.add(lblIcon, BorderLayout.WEST);
		pnlNorth.add(lblHeader, BorderLayout.CENTER);
		pnlNorth.add(btnUser, BorderLayout.EAST);
//===========================================================

		JPanel pnlMenu = new JPanel(new BorderLayout());
		pnlMenu.setBackground(null);

		boxMenu = Box.createHorizontalBox();

		pnlMenu.add(boxMenu, BorderLayout.NORTH);

		pnlNorthOfCenter.add(pnlMenu, BorderLayout.NORTH);

		Box boxIcon = Box.createVerticalBox();
		boxIcon.add(Box.createVerticalGlue());

		boxIcon.add(Box.createVerticalGlue());

		boxMenu.add(boxIcon);
		menuTrangChu = addMenuItem("Trang chủ", "img/home_white.png");
		menuHoSo = addMenuItem("Hồ sơ của tôi", "img/profile_white.png");
		menuQLKH = addMenuItem("Quản lý khách hàng", "img/customer_white.png");
		if (isManager)
			menuQLNV = addMenuItem("Quản lý nhân viên", "img/employee_white.png");
		menuQLHD = addMenuItem("Quản lý hóa đơn", "img/order_white.png");
		menuQLLK = addMenuItem("Quản lý linh kiện", "img/product_white.png");
		if (isManager)
			menuTKDT = addMenuItem("Thống kê doanh thu", "img/ledger.png");
		menuDX = addMenuItem("Đăng xuất", "img/logout.png");
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

	public JPanel addMenuItem(String name, String iconPath) {
		JPanel pnl = new JPanel();
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		pnl.setBackground(null);
		pnl.setPreferredSize(new Dimension(150, 60));

		JLabel lbIcon = new JLabel(new ImageIcon(iconPath));

		JLabel lbName = new JLabel(name);
		lbName.setForeground(Color.WHITE);
		lbName.setFont(NORMAL_FONT);

		Box boxItemIcon = Box.createHorizontalBox();
		boxItemIcon.add(Box.createHorizontalGlue());
		boxItemIcon.add(lbIcon);
		boxItemIcon.add(Box.createHorizontalGlue());

		Box boxItem = Box.createHorizontalBox();
		boxItem.add(Box.createHorizontalStrut(10));
		boxItem.add(Box.createHorizontalGlue());
		boxItem.add(lbName);
		boxItem.add(Box.createHorizontalGlue());
		boxItem.add(Box.createHorizontalStrut(10));

		pnl.add(Box.createVerticalGlue());
		pnl.add(boxItemIcon);
		pnl.add(boxItem);
		pnl.add(Box.createVerticalGlue());

		boxMenu.add(pnl);
		boxMenu.add(Box.createVerticalStrut(5));

		pnl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (selectedMenuItem != null)
						if (pnl.equals(selectedMenuItem))
							return;
					pnl.setBackground(ACTIVE_COLOR);
				}
			}
		});
		pnl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (selectedMenuItem != null)
					if (pnl.equals(selectedMenuItem))
						return;
				pnl.setBackground(ACTIVE_COLOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (selectedMenuItem != null)
					if (pnl.equals(selectedMenuItem))
						return;
				pnl.setBackground(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedMenuItem != null)
					if (!pnl.equals(selectedMenuItem))
						selectedMenuItem.setBackground(null);
				selectedMenuItem = pnl;
				pnl.setBackground(ACTIVE_COLOR);
			}
		});

		return pnl;

	}

	public void changePanel(JPanel panel) {
		getCenter().removeAll();
		getCenter().add(panel);
		if (panel.equals(pnlTrangChu))
			pnlTrangChu.focus();
		if (panel.equals(pnlQLHD))
			pnlQLHD.focus();
		if (panel.equals(pnlQLLK))
			pnlQLLK.focus();
		
		getCenter().revalidate();
		getCenter().repaint();

		if (selectedMenuItem != null)
			selectedMenuItem.setBackground(WEST_BACKGROUND);

		if (panel.equals(pnlQLKH)) {
			menuQLKH.setBackground(ACTIVE_COLOR);
			selectedMenuItem = menuQLKH;
			pnlQLKH.updateTableData();
		}
		if (panel.equals(pnlQLHD)) {
			menuQLHD.setBackground(ACTIVE_COLOR);
			selectedMenuItem = menuQLHD;
			pnlQLHD.loadAllDataToTable();
		}
		if (panel.equals(pnlQLLK)) {
			menuQLLK.setBackground(ACTIVE_COLOR);
			selectedMenuItem = menuQLLK;
			pnlQLLK.loadDataToTable();
		}
		if (panel.equals(pnlQLNV)) {
			menuQLNV.setBackground(ACTIVE_COLOR);
			selectedMenuItem = menuQLNV;
			pnlQLNV.loadDataToTable();
		}
		if (panel.equals(pnlHSNV)) {
			menuHoSo.setBackground(ACTIVE_COLOR);
			selectedMenuItem = menuHoSo;
			pnlHSNV.loadDataFromDatabaseToPanel();
		}
		if (panel.equals(pnlTKDT)) {
			menuTKDT.setBackground(ACTIVE_COLOR);
			selectedMenuItem = menuTKDT;
		}
		if (panel.equals(pnlTrangChu)) {
			menuTrangChu.setBackground(ACTIVE_COLOR);
			selectedMenuItem = menuTrangChu;
		}

	}

	private void warningBeforeClose() {
		int rs = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát?", "Xác nhận",
				JOptionPane.OK_CANCEL_OPTION);
		if (rs == JOptionPane.OK_OPTION)
			System.exit(0);
	}

	private void pasteData() {
		Component c = popMenu.getInvoker();
		if (c == null)
			return;
		if (c instanceof JTextField) {
			JTextField txt = (JTextField) c;
			txt.replaceSelection(tempData);
		}
	}

	private void copyData() {
		Component c = popMenu.getInvoker();
		if (c == null)
			return;
		if (c instanceof JTextField) {
			JTextField txt = (JTextField) c;
			tempData = txt.getSelectedText();
			miPaste.setEnabled(true);
		}
	}

	private void cutData() {
		Component c = popMenu.getInvoker();
		if (c == null)
			return;
		if (c instanceof JTextField) {
			JTextField txt = (JTextField) c;
			tempData = txt.getSelectedText();
			txt.replaceSelection("");
			miPaste.setEnabled(true);
		}
	}

	private void dangXuat() {
		int replay = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?", "Cảnh báo",
				JOptionPane.YES_NO_OPTION);
		if (replay == JOptionPane.YES_OPTION) {
			this.setVisible(false);
			new DangNhap().setVisible(true);
		} else
			return;
	}

}
