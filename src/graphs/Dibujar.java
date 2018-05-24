package graphs;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Dibujar extends JApplet {
    
    private static final long serialVersionUID = 1;

    private static final Dimension DEFAULT_SIZE = new Dimension(1000, 820);

    private final JGraphXAdapter<String, DefaultEdge> jgxAdapter;
    
    /**
     * Método que genera el dibujo del grafo
     * @param aVertices ArrayList que contiene los vértices leídos
     * @param matriz Matriz de adyacencia
     */
    public Dibujar(ArrayList<String> aVertices, int[][] matriz ){
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> grafo =
            new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));
        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(grafo);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        
        getContentPane().add(component);
        resize(DEFAULT_SIZE);
        
        //Por cada vértice leido añadir uno al grafo generado
        aVertices.forEach((vertice) -> {
            grafo.addVertex(vertice);
        });

        //Lectura de la matriz para añadir las Aristas
        for (int columna = 0; columna < matriz.length; columna++) {
            for (int fila = 0; fila < matriz.length; fila++) {
                if (matriz[columna][fila] ==1) {
                    
                    String inicio = aVertices.get(columna);
                    String fin = aVertices.get(fila);
                    grafo.addEdge(inicio, fin);
                }
            }
        }

        //La forma de graficar será circular
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        //Tamaño del radio del círculo
        int radius = 250;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
        
    }


 
}
