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

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
    
    
    public Dibujar(ArrayList<String> aVertices, int[][] matriz ){
             // create a JGraphT graph
            ListenableGraph<String, DefaultEdge> g =
            new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));
        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(g);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        
        getContentPane().add(component);
        resize(DEFAULT_SIZE);
        
        for(String vertice: aVertices)
            g.addVertex(vertice);
        
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
        String v5 = "v5";
        String v6 = "v6";
        String v7 = "v7";
        String v8 = "v8";
        
        for (int columna = 0; columna < matriz.length; columna++) {
            for (int fila = 0; fila < matriz.length; fila++) {
                if (matriz[columna][fila] ==1) {
                    
                    String inicio = aVertices.get(columna);
                    String fin = aVertices.get(fila);
                    g.addEdge(inicio, fin);
                }
                
                
            }
            
        }


        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
            
        

        // center the circle
        int radius = 250;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
        // that's all there is to it!...
        
    }
  
    
  

 
}
