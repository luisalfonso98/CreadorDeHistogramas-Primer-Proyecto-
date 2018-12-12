package Control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.PaintPanel;
import View.MenuPrincipal;

public class ControladorMM implements ActionListener{
	public final static String MAS="+";
	public final static String MENOS="-";
	private MenuPrincipal w;
	Vector<JTextField[]> aux;
	
	public ControladorMM(MenuPrincipal v){
		w=v;
	}
	public void actionPerformed(ActionEvent e) {
		String cmd = (String)(e.getActionCommand());
		accion(cmd);
	}
	public void accion(String x){
		switch(x){
		case MAS:
			sumar();
			break;
		case MENOS:
			restar();
			break;
		}
	}
	public void sumar(){
		w.getP().setVisible(false);
		aux=new Vector<JTextField[]>();
		JTextField[] basura;
		
		w.getP().removeAll();
		w.getP().setLayout(new GridLayout(w.getV().size()+1, 1));
		for (int i = 0; i < w.getV().size()+1; i++) {
			JPanel c = new JPanel();
			basura=new JTextField[2];
			JTextField jt1 = new JTextField(5);
			JTextField jt2 = new JTextField(5);
			if(i<w.getV().size()){
				jt1.setText(w.getV().get(i)[0].getText());
				jt2.setText(w.getV().get(i)[1].getText());
			}
			basura[0]=jt1;
			basura[1]=jt2;
			aux.add(basura);
			c.add(jt1);
			c.add(jt2);
			w.getP().add(c);
		}
		w.setV(aux);
		w.getP().setVisible(true);
	}
	public void restar(){
		w.getP().setVisible(false);
		aux=new Vector<JTextField[]>();
		JTextField[] basura;
		
		w.getP().removeAll();
		w.getP().setLayout(new GridLayout(w.getV().size()-1, 1));
		for (int i = 0; i < w.getV().size()-1; i++) {
			JPanel c = new JPanel();
			basura=new JTextField[2];
			JTextField jt1 = new JTextField(5);
			JTextField jt2 = new JTextField(5);
			jt1.setText(w.getV().get(i)[0].getText());
			jt2.setText(w.getV().get(i)[1].getText());
			
			basura[0]=jt1;
			basura[1]=jt2;
			aux.add(basura);
			c.add(jt1);
			c.add(jt2);
			w.getP().add(c);
		}
		w.setV(aux);
		w.getP().setVisible(true);
	}
}
