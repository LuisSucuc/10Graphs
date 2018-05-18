
package graphs;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Graphs {


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
            String[] nombreVertices = new String[10];
            
            
            Iterator lista_vertices = elementos.iterator();
            
            int i = 0;
            while (lista_vertices.hasNext()) 
            {
                nombreVertices[i] =lista_vertices.next().toString();
                i++;
            }
            
            
            /*
            //Ingresar a el vector los vértices leídos
            int tamano = elementos.size();
            for (int i=0; i<tamano; i++){
                nombreVertices[i] = elementos.get(i).toString();
                //System.out.println(elementos.get(i).toString());
            }*/
            
            
            //Ingresar a aristas
            Map.Entry aristas = map_grafo.next();
            JSONObject aristas_JSON = new JSONObject((Map) aristas.getValue());
            
            //Obtener el listado de aristas
            JSONArray arista = (JSONArray) aristas_JSON.get("arista");                     
            Iterator lista_aristas = arista.iterator();

            
            while (lista_aristas.hasNext()) 
            {
                
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(lista_aristas.next().toString());
                System.out.println(json.get("inicio"));
                System.out.println(json.get("fin"));
                    
            }
                
 
    }
    
}
