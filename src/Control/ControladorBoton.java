package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextField;

import Model.PaintPanel;
import View.MenuPrincipal;

public class ControladorBoton implements ActionListener{
	PaintPanel paint;
	Vector<JTextField[]> v;
		
		public ControladorBoton(PaintPanel p, Vector<JTextField[]> v2){
			paint=p;
			v=v2;
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		PaintPanel.getPuntos().clear();
		v=MenuPrincipal.getV();
		double aux = 0;
		double aux2 = 0;
		Double[] a;
		for (int i = 0; i < v.size(); i++) {
			a=new Double[2];
			try {
				if (v.get(i)[0].getText().isEmpty() == false) {
					aux = Double.parseDouble(v.get(i)[0].getText());
				}
				
				if (v.get(i)[1].getText().isEmpty() == false) {
					aux2 = Double.parseDouble(v.get(i)[1].getText());
				}
			} catch (NumberFormatException exc) {
				aux = 0;
			}
			a[0]=aux;
			a[1]=aux2;
			PaintPanel.getPuntos().add(a);
		}
		paint.repaint();
	}
}
