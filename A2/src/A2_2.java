
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */
public class A2_2 {
    public List<String> OrdenacaoTopologica(Grafo grafo){
        Set<String> conhecido = new HashSet<>();
        Map<String,Set<String>> antecessor = new HashMap<>();
        Map<String, Integer> TempoInicio = new HashMap<>();
        Map<String, Integer> TempoFinal = new HashMap<>();
        
        for (String v : grafo.vertices) {
            antecessor.put(v, new HashSet<>()); //resolver problema de nullpointer
        }
        
        int[] tempo = new int[1];
        List<String> O = new LinkedList<>();
        for (String v : grafo.vertices){
            if (!conhecido.contains(v)){
                DFSvisitOT(grafo, v, conhecido, TempoInicio, TempoFinal, tempo, O);
            }
        }
        return O;
    }
    
    public void DFSvisitOT(Grafo grafo, String vertice, Set<String> conhecido, 
                        Map<String, Integer> TempoInicio, Map<String, Integer> TempoFinal,
                        int[] tempo, List<String> O){
        
        conhecido.add(vertice);
        tempo[0]++;
        TempoInicio.put(vertice, tempo[0]);
        for (String v : grafo.adjacencias.get(vertice)){
            if (!conhecido.contains(v)){
                DFSvisitOT(grafo, v, conhecido, TempoInicio, TempoFinal, tempo, O);
            }
        }
        tempo[0]++;
        TempoFinal.put(vertice, tempo[0]);
        O.addFirst(vertice);
    }
    
    public static void main(String[] args){
        if (args.length < 1) {
            System.out.println("Uso: java A2_2 <arquivo_de_entrada>");
            return;
        }

        String nomeArquivo = "grafos/" + args[0];
        
        try {
        Grafo grafo = new Grafo(nomeArquivo);
        A2_2 algoritmo = new A2_2();
        List<String> ordenacao = algoritmo.OrdenacaoTopologica(grafo);

        System.out.println(String.join(" , ", ordenacao));
        } catch (IOException e) {
        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
