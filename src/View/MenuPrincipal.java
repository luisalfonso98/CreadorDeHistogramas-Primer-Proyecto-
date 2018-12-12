package View;

import java.awt.event.KeyEvent;
import java.util.Vector;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Control.ControladorBoton;
import Control.ControladorMM;
import Control.ControladorMenu;
import Model.PaintPanel;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton actualizar;
	private PaintPanel ejes;
	private JLabel x;
	private JLabel y;
	private static Vector<JTextField[]> v = new Vector<JTextField[]>();
	private ControladorMenu controlador;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenuItem menuItemaux;
	private JMenuItem menuItemcol;
	private JPanel p;
	private JScrollPane pane;
	private JButton mas;
	private JButton menos;
	private ControladorMM con;

	
	
	public JButton getMas() {
		return mas;
	}

	public void setMas(JButton mas) {
		this.mas = mas;
	}

	public JButton getMenos() {
		return menos;
	}

	public void setMenos(JButton menos) {
		this.menos = menos;
	}

	public JMenuItem getMenuItemcol() {
		return menuItemcol;
	}

	public void setMenuItemcol(JMenuItem menuItemcol) {
		this.menuItemcol = menuItemcol;
	}

	public JMenuItem getMenuItemaux() {
		return menuItemaux;
	}

	public void setMenuItemaux(JMenuItem menuItemaux) {
		this.menuItemaux = menuItemaux;
	}

	public JScrollPane getPane() {
		return pane;
	}

	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}

	public JButton getActualizar() {
		return actualizar;
	}

	public void setActualizar(JButton actualizar) {
		this.actualizar = actualizar;
	}

	public PaintPanel getEjes() {
		return ejes;
	}

	public void setEjes(PaintPanel ejes) {
		this.ejes = ejes;
	}

	public JLabel getXetiqueta() {
		return x;
	}

	public void setX(JLabel x) {
		this.x = x;
	}

	public JLabel getYetiqueta() {
		return y;
	}

	public void setY(JLabel y) {
		this.y = y;
	}

	public static Vector<JTextField[]> getV() {
		return v;
	}

	public static void setV(Vector<JTextField[]> v) {
		MenuPrincipal.v = v;
	}

	public ControladorMenu getControlador() {
		return controlador;
	}

	public void setControlador(ControladorMenu controlador) {
		this.controlador = controlador;
	}

	public JMenuBar getThisMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getMenu() {
		return menu;
	}

	public void setMenu(JMenu menu) {
		this.menu = menu;
	}

	public JMenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(JMenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MenuPrincipal() {
		super();
		configurarVentana();
		inicializarComponentes();
		crearMenu();
	}

	public void configurarVentana() {
		this.setTitle("Menu Principal");
		this.setSize(1250, 750);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);

	}

	public void inicializarComponentes() {

		x = new JLabel();
		x.setText("X");
		x.setBounds(1100, 70, 10, 10);
		this.add(x);

		y = new JLabel();
		y.setText("Y");
		y.setBounds(1170, 70, 10, 10);
		this.add(y);

		actualizar = new JButton();
		actualizar.setEnabled(false);
		actualizar.setText("Actualizar");
		actualizar.setBounds(1080, 550, 125, 75);
		this.add(actualizar);

		ejes = new PaintPanel();
		ejes.setBounds(50, 50, 1000, 600);
		ejes.setVisible(true);
		this.add(ejes);

		pane = new JScrollPane();
		pane.setBounds(1050, 110, 200, 400);
		p = new JPanel();
		p.setLayout(new GridLayout(10, 1));
		
		
		JTextField[] basura;
		for (int i = 0; i < 10; i++) {
			basura=new JTextField[2];
			JPanel c = new JPanel();
			JTextField jt1 = new JTextField(5);
			JTextField jt2 = new JTextField(5);
			jt1.setEnabled(false);
			jt2.setEnabled(false);
			basura[0]=jt1;
			basura[1]=jt2;
			v.add(basura);
			c.add(jt1);
			c.add(jt2);
			p.add(c);
		}

		pane.setViewportView(p);
		actualizar.addActionListener(new ControladorBoton(ejes, v));
		this.add(pane);
		
		con=new ControladorMM(this);
		
		mas=new JButton("+");
		mas.setActionCommand(ControladorMM.MAS);
		mas.setBounds(1220, 508, 30, 30);
		mas.setVisible(true);
		mas.setEnabled(false);
		mas.addActionListener(con);
		this.add(mas);

		menos=new JButton("-");
		menos.setBounds(1050, 508, 30, 30);
		menos.setActionCommand(ControladorMM.MENOS);
		menos.addActionListener(con);
		menos.setVisible(true);
		menos.setEnabled(false);
		this.add(menos);
	}

	public void crearMenu() {
		// Crea la barra de menú
		controlador = new ControladorMenu(this);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Primer menú
		menu = new JMenu("Archivo");
		menu.setMnemonic(KeyEvent.VK_F);

		menuItem = new JMenuItem("Nuevo", KeyEvent.VK_N);
		menuItem.getAccessibleContext().setAccessibleDescription("Nuevo");
		// Asociamos un comando del controlador
		menuItem.setActionCommand(ControladorMenu.NUEVO);
		// Añadimos controlador al item de menú
		menuItem.addActionListener(controlador);
		menu.add(menuItem);

		menuItem = new JMenuItem("Abrir", KeyEvent.VK_A);
		menuItem.getAccessibleContext().setAccessibleDescription("Abrir");
		menuItem.setActionCommand(ControladorMenu.ABRIR);
		menuItem.addActionListener(controlador);
		menu.add(menuItem);

		menuItemaux = new JMenuItem("Guardar TXT", KeyEvent.VK_T);
		menuItemaux.getAccessibleContext().setAccessibleDescription("Guardar TXT");
		menuItemaux.setActionCommand(ControladorMenu.EXPORTAR);
		menuItemaux.setEnabled(false);
		menuItemaux.addActionListener(controlador);
		menu.add(menuItemaux);

		menuItem = new JMenuItem("Exportar", KeyEvent.VK_E);
		menuItem.getAccessibleContext().setAccessibleDescription("Exportar");
		menuItem.setActionCommand(ControladorMenu.EXPORTAR);
		menuItem.addActionListener(controlador);
		menu.add(menuItem);

		menuItem = new JMenuItem("Salir", KeyEvent.VK_S);
		menuItem.getAccessibleContext().setAccessibleDescription("Salir");
		menuItem.setActionCommand(ControladorMenu.SALIR);
		menuItem.addActionListener(controlador);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Preferencias");
		menu.setMnemonic(KeyEvent.VK_P);

		menuItemcol = new JMenuItem("Color", KeyEvent.VK_C);
		menuItemcol.getAccessibleContext().setAccessibleDescription("Color");
		menuItemcol.setActionCommand(ControladorMenu.COLOR);
		menuItemcol.setEnabled(false);
		menuItemcol.addActionListener(controlador);
		menu.add(menuItemcol);

		menuBar.add(menu);

		menu = new JMenu("Ayuda");
		menu.setMnemonic(KeyEvent.VK_H);

		menuItem = new JMenuItem("Uso de Programa", KeyEvent.VK_U);
		menuItem.getAccessibleContext().setAccessibleDescription("Color");
		menuItem.setActionCommand(ControladorMenu.USO);
		menuItem.addActionListener(controlador);
		menu.add(menuItem);

		menuItem = new JMenuItem("Configuracion del TXT", KeyEvent.VK_U);
		menuItem.getAccessibleContext().setAccessibleDescription("Color");
		menuItem.setActionCommand(ControladorMenu.TXT);
		menuItem.addActionListener(controlador);
		menu.add(menuItem);

		menuBar.add(menu);
	}

}
