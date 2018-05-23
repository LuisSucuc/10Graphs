package graphs;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Lectura {

    String path;

    public void getPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Selecciona un archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().toString();
        } else {
            System.exit(0);
        }
    }

    public void leerArchivo() throws FileNotFoundException {

        try {

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

//            System.out.println(inicio + " --> " +fin);
            }

            /* Imprimir en consola*/
            imprimirMatriz(matriz, aVertices);
            imprimirErrores(aErrors, aristaError);

            Dibujar applet = new Dibujar(aVertices, matriz);
            JFrame frame = new JFrame();
            frame.getContentPane().add(applet);
            frame.setTitle("Dibujar grafos UMG");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El archivo no tiene el formato esperado. \nSeleccione otro archivo", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            getPath();
        }

    }

    private void imprimirErrores(ArrayList<String> aErrors, ArrayList<String> aristaError) {
        if (aErrors.size() > 0) {
            System.out.println("\n*********** Vertices no dibujados ***********");
            String sVertices = "";
            sVertices = aErrors.stream().map((vertice) -> vertice + ", ").reduce(sVertices, String::concat);
            System.out.println(sVertices);
        }

        if (aristaError.size() > 0) {
            System.out.println("\n*********** Aristas no dibujadas ***********");
            String sArista = "";
            for (String arista : aristaError) {
                System.out.println(arista + " ");
            }
        }

    }

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

    public static void main(String[] args) throws FileNotFoundException {
        Lectura leer = new Lectura();
        leer.getPath();
        leer.leerArchivo();

    }

}
