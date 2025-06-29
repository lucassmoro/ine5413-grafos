
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */
public class A3_2 {
    
    Set<String> X = new HashSet<>();
    Map<String, String> mate = new HashMap<>();

    public void HopcroftKarp(Grafo grafo){
        Map<String, Integer> D = new HashMap<>();
        //Set<String> verticesAux = new LinkedHashSet<>(); // estrutura auxiliar pra percorrer X
        mate.clear();
        int m = 0;
        for (String v : grafo.vertices){
            mate.put(v, null);
        } 
        while (BFS(grafo, mate, D) == true){
            for (String x : X){
                if (mate.containsKey(x) && mate.get(x) == null){
                    if (DFS(grafo, mate, x, D) == true){
                        m++;
                    }
                }
            }
        }
    }
    
    public boolean DFS(Grafo grafo, Map<String, String> mate, String x, Map<String, Integer> D){
        if (x != null){
            for (String y : grafo.vizinhos(x)){
                if (D.get(mate.get(y)) != null && D.get(mate.get(y)) == D.get(x) + 1){
                    if (DFS(grafo, mate, mate.get(y), D) == true) {
                        mate.put(x, y);
                        mate.put(y, x);
                        return true;
                    }
                }
            }
            D.put(x, Integer.MAX_VALUE);
            return false;
        }
        return true;
    }
    public boolean BFS(Grafo grafo, Map<String, String> mate, Map<String, Integer> D){
        Queue<String> fila = new LinkedList<>();
        for (String x : X) {
            if (mate.containsKey(x) && mate.get(x) == null) {
                D.put(x, 0);
                fila.add(x);
            } else {
                D.put(x, Integer.MAX_VALUE);
            }
        }
        D.put(null, Integer.MAX_VALUE);
        while (!fila.isEmpty()){
            String x = fila.remove();
            if (D.get(x) < D.get(null)){
                for (String y : grafo.vizinhos(x)){
                    if (D.get(mate.get(y)) == Integer.MAX_VALUE){
                        D.put(mate.get(y), D.get(x) + 1);
                        fila.add(mate.get(y));
                    }
                }
            }
        }
        return D.get(null) != Integer.MAX_VALUE;
    }
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Uso: java A3_2 <arquivo.net>");
            return;
        }

        Grafo grafo = new Grafo(args[0]);
        A3_2 algoritmo = new A3_2();

        for (int idx = 1; idx <= grafo.qtdVertices() / 2; idx++) {
            algoritmo.X.add(grafo.indiceParaVertice.get(idx));
        }

        algoritmo.HopcroftKarp(grafo);

        Set<String> usados = new HashSet<>();
        List<String> emparelhamentos = new ArrayList<>();

        for (String u : grafo.vertices) {
            String v = algoritmo.mate.get(u);
            if (v != null && !usados.contains(v)) {
                emparelhamentos.add(u + "-" + v);
                usados.add(u);
                usados.add(v);
            }
        }

        System.out.println(emparelhamentos.size());
        System.out.println(String.join(", ", emparelhamentos));
    }
}

