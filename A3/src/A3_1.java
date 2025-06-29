
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
public class A3_1 {
    private Map<String, Map<String, Double>> fluxo = new HashMap<>();
    
    public List<String> EdmondsKarp(Grafo grafo, String s, String t){
        
        for (String u : grafo.vertices) {
            fluxo.put(u, new HashMap<>());
            for (String v : grafo.vizinhos(u)) {
                fluxo.get(u).put(v, 0.0);
            }
        }
        
        Queue<String> fila = new LinkedList<>();
        Set<String> conhecidos = new HashSet<>();
        Map<String, String> pai = new HashMap<>();
        
        fila.add(s);
        conhecidos.add(s);
        
        while (!fila.isEmpty()) {
            String u = fila.remove();
            for (String v : grafo.vizinhos(u)){
                if (!conhecidos.contains(v) && capacidadeResidual(grafo, u, v) > 0){
                    conhecidos.add(v);
                    pai.put(v, u);
                    if (v.equals(t)){
                        List<String> caminho = new LinkedList<>();
                        String w = t;
                        caminho.addFirst(w);
                        while (!w.equals(s)){
                            w = pai.get(w);
                            caminho.addFirst(w);
                        }
                        return caminho;
                    }
                    fila.add(v);
                }
            }
        }
        return null;
    }
    
    private double capacidadeResidual(Grafo grafo, String u, String v) {
        double capacidade = grafo.peso(u, v); // capacidade original
        double fluxoAtual = fluxo.getOrDefault(u, new HashMap<>()).getOrDefault(v, 0.0);
        return capacidade - fluxoAtual;
    }
}

