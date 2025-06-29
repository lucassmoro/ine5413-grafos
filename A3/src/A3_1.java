
import java.io.IOException;
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
public double calcularFluxoMaximo(Grafo grafo, String s, String t) {
    double fluxoMaximo = 0;

    for (String u : grafo.vertices) {
        fluxo.put(u, new HashMap<>());
        for (String v : grafo.vizinhos(u)) {
            fluxo.get(u).put(v, 0.0);
        }
    }

    while (true) {
        List<String> caminho = EdmondsKarp(grafo, s, t);
        if (caminho == null) break;

        double gargalo = Double.POSITIVE_INFINITY;
        for (int i = 0; i < caminho.size() - 1; i++) {
            String u = caminho.get(i);
            String v = caminho.get(i + 1);
            gargalo = Math.min(gargalo, capacidadeResidual(grafo, u, v));
        }
        for (int i = 0; i < caminho.size() - 1; i++) {
            String u = caminho.get(i);
            String v = caminho.get(i + 1);

            fluxo.putIfAbsent(u, new HashMap<>()); 
            fluxo.putIfAbsent(v, new HashMap<>()); 

            fluxo.get(u).put(v, fluxo.get(u).getOrDefault(v, 0.0) + gargalo);
            fluxo.get(v).put(u, fluxo.get(v).getOrDefault(u, 0.0) - gargalo);
        }
        fluxoMaximo += gargalo;
    }

    return fluxoMaximo;
}


    public static void main(String[] args) {
    if (args.length != 3) return;

    String arquivo = args[0];
    String origem = args[1];
    String destino = args[2];

    try {
        Grafo grafo = new Grafo(arquivo); 
        if (!grafo.vertices.contains(origem) && origem.matches("\\d+")) {
            origem = grafo.indiceParaVertice.getOrDefault(Integer.valueOf(origem), origem);
        }
        if (!grafo.vertices.contains(destino) && destino.matches("\\d+")) {
            destino = grafo.indiceParaVertice.getOrDefault(Integer.valueOf(destino), destino);
        }

        A3_1 solver = new A3_1();
        double fluxoMaximo = solver.calcularFluxoMaximo(grafo, origem, destino);
        System.out.println((int) fluxoMaximo);

    } catch (IOException e) {
    }
}
}


