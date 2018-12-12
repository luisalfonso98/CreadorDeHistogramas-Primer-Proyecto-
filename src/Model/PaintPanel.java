package Model;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class PaintPanel extends JPanel {
	int basura=0;
	//elementos de coordenadas 
	private double xbase; 	//minimo elemento mostrado en las x
	private double xend;	//maximo elemento mostrado en las x
	private double xrange;
	private double ybase;	//minimo elemento mostrado en las y
	private double yend;	//maximo elemento mostrado en las y
	private double yrange;
		
		//datos del JFrame o Jpanel
	private int xmax; 		//tamaño total de la pantalla
	private int xoffset;	//distancia que se dejara sin utilizar, sangria
	private int xlength;	//Espacio que se va a utilizar
	private int xspace; //Espacio sobrante
	private int ymax;
	private int yoffset;
	private int ylength;
	private int yspace;
		
		//factores de escalado
	private double xfactor;
	private double yfactor;
		
	private Escala x;
	private Escala y;
	
	private Color colorPrin=Color.BLACK;
	private Color colorSec=colorPrin.brighter();
	private static Vector<Double[]> puntos;
	

	public static Vector<Double[]> getPuntos() {
		return puntos;
	}

	public static void setPuntos(Vector<Double[]> puntos) {
		PaintPanel.puntos = puntos;
	}

	public void setColorPrin(Color colorPrin) {
		this.colorPrin = colorPrin;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PaintPanel(){
		puntos=new Vector<Double[]>();
	}
	
	public void definirEscala(Double double1,Double double2){
		xbase=0;	//de izquierda 
		xend=double1;		// a derecha
		ybase=0;	// de abajo
		yend=double2;	// a arriba ya que el 0.0 esta ubicado en la esquina superior izquierda y para bajar hay que sumar
		
		xmax=this.getWidth();
		ymax=this.getHeight();
		
		xoffset=0;
		yoffset=0;  //esto significaria que usaremos todo el espacio como vemos a continuacion
		
		xlength=xmax;
		ylength=ymax;
		
		xrange=xend-xbase;
		xspace=xmax-xoffset-xlength;
		xfactor=xlength/(xrange);
		
		yspace=ymax-yoffset-ylength;
		yrange=yend-ybase;
		yfactor=-ylength/(yrange);
		
		x=new Escala(xoffset, xbase, xfactor);
		y=new Escala(ymax-yoffset, ybase, yfactor);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		colorSec=colorPrin.brighter();
		double aux=0;
		double aux2=0;
		
		for(int i=0;i<puntos.size();i++){
			if(aux<puntos.get(i)[1]){
				aux=puntos.get(i)[1];
			}
			if(aux2<puntos.get(i)[0]){
				aux2=puntos.get(i)[0];
			}
		}
		definirEscala(aux2+1,aux);
		
		
		if(aux2!=0 && aux!=0){
		for(int i=0;i<puntos.size();i++){
			if(i%2==0){
				g.setColor(colorSec);
			} else {
				g.setColor(colorPrin);
			}
		/*	if(i!=puntos.size()-1){
				g.fillRect(x.toCoor(puntos.get(i)[0])+2, y.toCoor(puntos.get(i)[1])-2, x.toCoor(puntos.get(i+1)[0]-puntos.get(i)[0]), this.getHeight()-y.toCoor(puntos.get(i)[1]));
			} else {
				g.fillRect(x.toCoor(puntos.get(i)[0])+2, y.toCoor(puntos.get(i)[1])-2, this.getWidth()-x.toCoor(puntos.get(i)[0]), this.getHeight()-y.toCoor(puntos.get(i)[1]));
			}*/
			g.fillRect(x.toCoor(puntos.get(i)[0]-0.5)+2, y.toCoor(puntos.get(i)[1])-2, x.toCoor(1), this.getHeight()-y.toCoor(puntos.get(i)[1]));

		}
		
		}
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, this.getHeight()); //eje y de los ejes de la grafica
		g.drawLine(1, 0, 1, this.getHeight());

		g.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1); //eje x de los ejes de la grafica
		g.drawLine(0, this.getHeight()-2, this.getWidth(), this.getHeight()-2);
		
		for (int i = 0; i < aux2; i++) {
			g.drawLine(0, y.toCoor(i)+2, 10, y.toCoor(i)+2);
			g.drawLine(0, y.toCoor(i)+1, 10, y.toCoor(i)+1);
			g.drawLine(0, y.toCoor(i)+3, 10, y.toCoor(i)+3);
			g.drawLine(x.toCoor(i)+2,this.getHeight(),x.toCoor(i)+2,this.getHeight()-10);
			g.drawLine(x.toCoor(i)+1,this.getHeight(),x.toCoor(i)+1,this.getHeight()-10);
			g.drawLine(x.toCoor(i)+3,this.getHeight(),x.toCoor(i)+3,this.getHeight()-10);
		}
		
		if(basura==0){
			dibujarPrede(g);
		}

	}
	
	public void dibujarPrede(Graphics g){
		
		g.setColor(Color.CYAN);
		g.fillRect(52, 200-2, 50, 400); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(52, 200-2, 50, 400);
		
		g.setColor(Color.magenta);
		g.fillRect(102, 300-2, 50, 300); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(102, 300-2, 50, 300);
		
		g.setColor(Color.green);
		g.fillRect(152, 500-2, 50, 100); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(152, 500-2, 50, 100);
		
		g.setColor(Color.red);
		g.fillRect(202, 400-2, 50, 200); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(202, 400-2, 50, 200);
		
		g.setColor(Color.yellow);
		g.fillRect(152, 500-2, 50, 100); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(152, 500-2, 50, 100);
		
		g.setColor(Color.gray);
		g.fillRect(252, 550-2, 50, 50); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(252, 550-2, 50, 50);
		
		g.setColor(Color.ORANGE);
		g.fillRect(302, 350-2, 50, 250); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(302, 350-2, 50, 250);
		
		g.setColor(Color.BLUE);
		g.fillRect(352, 250-2, 50, 350); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(352, 250-2, 50, 350);
		
		g.setColor(Color.PINK);
		g.fillRect(402, 450-2, 50, 150); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(402, 450-2, 50, 150);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(452, 500-2, 50, 100); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(452, 500-2, 50, 100);
		
		g.setColor(Color.WHITE);
		g.fillRect(502, 550-2, 50, 50); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(502, 550-2, 50, 50);
		
		g.setColor(Color.MAGENTA);
		g.fillRect(552, 425-2, 50, 175); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(552, 425-2, 50, 175);
		
		
		g.setColor(Color.green);
		g.fillRect(602, 150-2, 50, 450); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(602, 150-2, 50, 450);
		
		g.setColor(Color.CYAN);
		g.fillRect(652, 275-2, 50, 325); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(652, 275-2, 50, 325);
		
		g.setColor(Color.RED);
		g.fillRect(702, 375-2, 50, 225); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(702, 375-2, 50, 225);

		
		g.setColor(Color.yellow);
		g.fillRect(752, 475-2, 50, 125); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(752, 475-2, 50, 125);
		
		g.setColor(Color.gray);
		g.fillRect(802, 530-2, 50, 70); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(802, 530-2, 50, 70);
		
		g.setColor(Color.blue);
		g.fillRect(852, 325-2, 50, 275); //primera barra color cyan 
		g.setColor(Color.black); //reborde de la primera barra
		g.drawRect(852, 325-2, 50, 275);
		basura++;
	}

}
