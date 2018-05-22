package graphs;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Lectura {

    public static void main(String[] args) throws FileNotFoundException {
        
        FileReader file     = new FileReader("entrada.txt");
        JsonElement datos   = new JsonParser().parse(file)  ;
        //Se convierte en un objeto porque tiene {}
        JsonObject jobject  = datos.getAsJsonObject();
        //Obtener y convertir porque está dentro de un diccionario {}
        JsonObject grafo    = jobject.get("grafo").getAsJsonObject();
        
        /*---- VERTICE ----*/
        
        //Obtener y convertir porque está dentro de un diccionario {}
        JsonObject vertices  = grafo.get("vertices").getAsJsonObject();
        //Obtener y convertir a Array porque está dentro de una lista []
        JsonArray vertice   = vertices.get("vertice").getAsJsonArray();
        
        ArrayList<Vertice> arrayVertice = new ArrayList<>();
        ArrayList<Vertice> arrayError = new ArrayList<>();
        int i = 0;
        for (JsonElement verticeStr : vertice) {
            String valor = verticeStr.getAsString();
            
            //Máximo de valores
            if (i < 10)
                arrayVertice.add(new Vertice(valor));
            else
                arrayError.add(new Vertice(valor));
            i++;
            System.out.println(valor);
        }
        
            System.out.println("");
            System.out.println("");
            
        /*---- ARISTAS ----*/
        //Obtener y convertir porque está dentro de un diccionario {}
        JsonObject aristas = grafo.get("aristas").getAsJsonObject();
        //Obtener y convertir a Array porque está dentro de una lista []
        JsonArray arista   = aristas.get("arista").getAsJsonArray();
        
        for (JsonElement aristaStr : arista) {
            JsonObject direccion = aristaStr.getAsJsonObject();
            String inicio   = direccion.get("inicio").getAsString();
            String fin   = direccion.get("fin").getAsString();
            System.out.println(inicio + " --> " +fin);
        }
        
    }

}
