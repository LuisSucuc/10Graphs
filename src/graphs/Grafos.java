
package graphs;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Grafos {
    
    public int getPosicion(Vertice[] vector, String nombre) {
        for (Vertice v : vector){
            if (v.nombre.equals(nombre)) {
                return v.ubicacion;
                
            }
        }
        return 100;
    }

    public static void main(String[] args) throws Exception {
        
            Object archivoJson = new JSONParser().parse(new FileReader("entrada.txt"));
            JSONObject archivo = (JSONObject) archivoJson;
            
            //Iniciar el grafo
            Map grafo = ((Map)archivo.get("grafo"));
            Iterator<Map.Entry> map_grafo = grafo.entrySet().iterator();
            
            //Ingresar a vértices
            Map.Entry vertices = map_grafo.next();
            JSONObject vertices_JSON = new JSONObject((Map) vertices.getValue());
            
            //Obtener el listado de vertices
            JSONArray elementos = (JSONArray) vertices_JSON.get("vertice");
            Vertice[] vertc = new Vertice[10];
            
            
            Iterator lista_vertices = elementos.iterator();
            
            int i = 0;
            int posicion = Flags.ARRIBA;
            while (lista_vertices.hasNext()) 
            {
   
                if (i>= 5) {
                    posicion = Flags.ABAJO;
                }
                
                vertc[i] = new Vertice(lista_vertices.next().toString(), i, posicion);
                i++;
            }
            
                        
            //Ingresar a aristas
            Map.Entry aristas = map_grafo.next();
            JSONObject aristas_JSON = new JSONObject((Map) aristas.getValue());
            
            //Obtener el listado de aristas
            JSONArray arista = (JSONArray) aristas_JSON.get("arista");                     
            Iterator lista_aristas = arista.iterator();
            
            int aristas_inicio_fin[][] = new int[3][3];

            
            while (lista_aristas.hasNext()) 
            {
                
                JSONObject json = (JSONObject) new JSONParser().parse(lista_aristas.next().toString());
                System.out.println(json.get("inicio") + "---->" + json.get("fin"));
                
            }
 
    }
}
