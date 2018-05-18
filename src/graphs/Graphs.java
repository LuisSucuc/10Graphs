
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
            
            Map.Entry vertices = map_grafo.next();
            JSONObject vertices_JSON = new JSONObject((Map) vertices.getValue());
            
            JSONArray elementos = (JSONArray) vertices_JSON.get("vertice");
            List<String> listStrings = new ArrayList<>();
            

            
            int len = elementos.size();
               for (int i=0;i<len;i++){ 
                    listStrings.add(elementos.get(i).toString());
               }
               
               
            System.out.println(listStrings.get(0));

            
            /*            
            Iterator lista = elementos.iterator();
            
            
            while (lista.hasNext()) 
            {
                    System.out.println(lista.next().toString());
                    
		}
                */
 
    }
    
}
