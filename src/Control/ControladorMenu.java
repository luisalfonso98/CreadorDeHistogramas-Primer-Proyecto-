package Control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Lector;
import View.MenuPrincipal;

public class ControladorMenu extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	public final static String ABRIR="A";
	public final static String EXPORTAR="E";
	public final static String COLOR="C";
	public final static String SALIR="S";
	public final static String GTXT="G";
	public final static String USO="U";
	public final static String TXT="T";
	public final static String NUEVO="N";
	static int serial;
	JInternalFrame frame;
	static JColorChooser color=new JColorChooser();
	static Color colorselec=Color.BLACK;
	
	MenuPrincipal w;
	Lector leer;
	
	public ControladorMenu(MenuPrincipal w){
		this.w=w;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = (String)(e.getActionCommand());
        abrirVentana(cmd);
	}
	
	@SuppressWarnings("static-access")
	public void abrirVentana(String cmd) {
    	switch(cmd) {
    	case ControladorMenu.ABRIR:
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
    		JFileChooser chooser = new JFileChooser();
    		chooser.setCurrentDirectory(null);
    		chooser.setDialogTitle("Titulo");
    		chooser.setFileFilter(filter);
    		chooser.setAcceptAllFileFilterUsed(false);
    		w.add(chooser);
    		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    			leer=new Lector(chooser.getSelectedFile().toString());
    			leer.leer();
    			actPanel(leer.getPuntos());
    			activar(w.getV());
    			w.getActualizar().setEnabled(true);
    			w.getMenuItemaux().setEnabled(true);
    			w.getMenuItemcol().setEnabled(true);
    			w.getEjes().setPuntos(leer.getPuntos());
    			w.getEjes().repaint();
    			rellenarTF(w.getEjes().getPuntos());
    		} else {
    			System.out.println("No seleccion ");
    		}
    		break;
    	case ControladorMenu.EXPORTAR:
    		guardarImagen(w.getEjes());
    		break;

    	case ControladorMenu.SALIR:
    		w.dispose();
    		System.exit(0);
    		break;
    	case ControladorMenu.COLOR:
    		colorselec=color.showDialog(frame, "COLORES", colorselec);
    		w.getEjes().setColorPrin(colorselec);
    		w.getEjes().repaint();
    		break;
    	case ControladorMenu.USO:
    		JDialog x= new JDialog();
    		x.setLayout(new BorderLayout());
    		x.add(new JLabel("Instrucciones de Uso de la Aplicación para Creacion de histogramas"), "North");
    		x.add(new JLabel("paso 1"), "Center");
    		x.setVisible(true);
    		break;
    	case ControladorMenu.TXT:
    		JDialog y= new JDialog();
    		y.setLayout(new BorderLayout());
    		y.add(new JLabel("Debe cumplir este estilo el archivo txt"), "North");
    		y.add(new JLabel("ppppp"), "Center");
    		y.setVisible(true);
    		break;
    	case ControladorMenu.GTXT:
    		
    		break;
    	case ControladorMenu.NUEVO:
    		activar(w.getV());
    		w.getMenuItemaux().setEnabled(true);
    		w.getActualizar().setEnabled(true);
    		w.getEjes().getPuntos().removeAllElements();
    		w.getEjes().repaint();
    		w.getMenuItemcol().setEnabled(true);
    		rellenarTF(w.getEjes().getPuntos());
    		break;
    	}
    	
	}
	
	public void guardarImagen(JPanel p){
		 BufferedImage image = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);
         Graphics2D g = image.createGraphics();
         p.printAll(g);
         g.dispose();
         try {
             ImageIO.write(image, "jpg", new File(serial+"Paint.jpg"));
             ImageIO.write(image, "png", new File(serial+"Paint.png"));
             serial++;
         } catch (IOException exp) {
             exp.printStackTrace();
         }
	}
	public void actPanel(Vector<Double[]> vector){
		w.getP().removeAll();
		w.getP().setVisible(false);
		w.getV().clear();
		w.getP().setLayout(new GridLayout(vector.size(), 1));
		JTextField[] basura;
		for (int i = 0; i < vector.size(); i++) {
			JPanel c = new JPanel();
			basura=new JTextField[2];
			JTextField jt1 = new JTextField(5);
			JTextField jt2 = new JTextField(5);
			basura[0]=jt1;
			basura[1]=jt2;
			w.getV().add(basura);
			c.add(jt1);
			c.add(jt2);
			w.getP().add(c);
		}
		w.getP().setVisible(true);
	}
	
	public void rellenarTF(Vector<Double[]> ve){
		for(int i=0;i<ve.size();i++){
			for(int j=0;j<2;j++){
				w.getV().get(i)[j].setText(String.valueOf(ve.get(i)[j]));
			}
		}
	}
	public void activar(Vector<JTextField[]> ve){
		for(int i=0;i<ve.size();i++){
			ve.get(i)[0].setEnabled(true);			
			ve.get(i)[1].setEnabled(true);
		}
		w.getMas().setEnabled(true);
		w.getMenos().setEnabled(true);
	}
	
}
