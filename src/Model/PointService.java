package Model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PointService {

	public List<Puntos> leerPuntos(String archivo) throws FileNotFoundException, IOException {
        String line;
        List<Puntos> lstPuntos= new ArrayList<Puntos>();
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((line = b.readLine())!=null) {
            String[] arrayPuntos = line.split(",");
            int x= Integer.parseInt(arrayPuntos[0]);
            int y= Integer.parseInt(arrayPuntos[1]);
            
            Puntos puntos = new Puntos(x, y);
            lstPuntos.add(puntos);
            
            System.out.println("El punto "+arrayPuntos[0]+" aparece "+arrayPuntos[1]+" veces");
            
        }
        b.close();
        
        return lstPuntos;
    }
}
