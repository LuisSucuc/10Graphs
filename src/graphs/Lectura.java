package graphs;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Lectura {
 
    /**
     * Leer el archivo enviado y convertirlo a JSON
     * y posteriormente realizar la llamda a
     * la clase para dibujarlo 
     * @param path
     * @throws java.io.FileNotFoundException
     */
    public void leerArchivoYCrear(String path) throws FileNotFoundException {
        
            FileReader file = new FileReader(path);
            JsonElement datos = new JsonParser().parse(file);
            //Se convierte en un objeto porque tiene {}
            JsonObject jobject = datos.getAsJsonObject();
            //Obtener y convertir porque está dentro de un diccionario {}
            JsonObject grafo = jobject.get("grafo").getAsJsonObject();

            /*---- VERTICE ----*/
            //Obtener y convertir porque está dentro de un diccionario {}
            JsonObject vertices = grafo.get("vertices").getAsJsonObject();
            //Obtener y convertir a Array porque está dentro de una lista []
            JsonArray vertice = vertices.get("vertice").getAsJsonArray();

            ArrayList<String> aVertices = new ArrayList<>();
            ArrayList<String> aErrors = new ArrayList<>();
            int i = 0;
            for (JsonElement verticeStr : vertice) {
                String valor = verticeStr.getAsString();

                //Máximo de valores
                if (i < 10 && aVertices.indexOf(valor) < 0) {
                    aVertices.add(valor);
                } else {
                    if (aVertices.indexOf(valor) >= 0) {
                        i--;
                    }
                    aErrors.add(valor);
                }
                i++;

            }
            System.out.println("");

            /*---- ARISTAS ----*/
            int[][] matriz = new int[10][10];
            ArrayList<String> aristaError = new ArrayList<>();

            //Obtener y convertir porque está dentro de un diccionario {}
            JsonObject aristas = grafo.get("aristas").getAsJsonObject();
            //Obtener y convertir a Array porque está dentro de una lista []
            JsonArray arista = aristas.get("arista").getAsJsonArray();

            for (JsonElement aristaStr : arista) {

                JsonObject direccion = aristaStr.getAsJsonObject();
                //Obtener inicio y fin
                String inicio = direccion.get("inicio").getAsString();
                String fin = direccion.get("fin").getAsString();

                //Obtener la posición en el array
                int posInicio = aVertices.indexOf(inicio);
                int posFin = aVertices.indexOf(fin);

                //1 en la posición correspondiente en la matriz
                if (posInicio >= 0 & posFin >= 0) {
                    matriz[posInicio][posFin] = 1;
                } //Añadir aristas si no coinciden
                else {
                    String error = inicio + " --> " + fin + "  ";
                    if (posInicio < 0) {
                        error += inicio + " no existe. ";
                    }
                    if (posFin < 0) {
                        error += fin + " no existe.";
                    }

                    aristaError.add(error);
                }
                
            }

            /* Imprimir en consola*/
            imprimirMatriz(matriz, aVertices);
            imprimirErrores(aErrors, aristaError);

            Dibujar applet = new Dibujar(aVertices, matriz);
            JFrame frame = new JFrame();
            frame.getContentPane().add(applet);
            frame.setTitle("Grafo generado");
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

      

    }
    
    /*
    * Muestra los errores en popup
    */
    private void imprimirErrores(ArrayList<String> aErrors, ArrayList<String> aristaError) {
        String errors = "";
        if (aErrors.size() > 0) {
            System.out.println("\n*********** "
                    + "Vertices no dibujados ***********");
            String sVertices = "";
            sVertices = aErrors.stream().map((vertice) -> vertice + ", ").reduce(sVertices, String::concat);
            System.out.println(sVertices);
            errors  += "VERTICES NO DIBUJADOS \n" + sVertices + "\n\n";
        }

        if (aristaError.size() > 0) {
            String errorArista ="";
            System.out.println("\n*********** Aristas no dibujadas ***********");
            String sArista = "";
            for (String arista : aristaError) {
                System.out.println(arista + " ");
                errorArista +=arista + "\n";
            }
            errors  += "\nARISTAS NO DIBUJADAS \n" + errorArista;
        }
        
        if (!"".equals(errors)) {
             JOptionPane.showMessageDialog(null, errors, "ADVERTENCIA",
                    JOptionPane.WARNING_MESSAGE);
            
        }

    }
    
    /*
    * Imprime en consola la matriz que utilizará para 
    * generar el grafo 
    */
    private void imprimirMatriz(int[][] matriz, ArrayList<String> aVertices) {

        //Valor máximo a imprimir es el tamaño de arrayList
        int max = aVertices.size();

        //Imprimir los vertices de encabezado
        String titulo = "       ";
        for (String vertice : aVertices) {
            titulo += vertice + "   ";
        }
        System.out.println(titulo);

        //Imprimir el contenido
        for (int fila = 0; fila < max; fila++) {

            String imprimir = aVertices.get(fila) + "   | ";

            for (int columna = 0; columna < max; columna++) {
                imprimir += matriz[fila][columna] + "    ";
            }

            System.out.println(imprimir + "|");

        }
    }
}
