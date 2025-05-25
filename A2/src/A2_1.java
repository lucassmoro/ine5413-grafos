import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class A2_1 {
    public List<Set<String>> kosaraju(Grafo grafo) {
        Set<String> conhecido = new HashSet<>();
        Map<String, Integer> TempoInicio = new HashMap<>();
        Map<String, Integer> TempoFinal = new HashMap<>();
        int[] tempo = new int[1];

        for (String v : grafo.vertices) {
            if (!conhecido.contains(v)) {
                DFSvisit(grafo, v, conhecido, TempoInicio, TempoFinal, tempo);
            }
        }

        Grafo GT = new Grafo();
        for (String v : grafo.vertices) {
            GT.vertices.add(v);
            GT.adjacencias.put(v, new HashSet<>());
        }
        for (String u : grafo.vertices) {
            for (String v : grafo.adjacencias.get(u)) {
                GT.adjacencias.get(v).add(u); // inverte aresta
            }
        }

        List<String> ordem = new ArrayList<>(grafo.vertices);
        ordem.sort((a, b) -> Integer.compare(TempoFinal.get(b), TempoFinal.get(a)));

        tempo[0] = 0; // RESET
        Set<String> conhecidoT = new HashSet<>();
        Map<String, Integer> TempoInicioT = new HashMap<>();
        Map<String, Integer> TempoFinalT = new HashMap<>();

        List<Set<String>> componentes = new ArrayList<>();
        for (String v : ordem) {
            if (!conhecidoT.contains(v)) {
                Set<String> componente = new HashSet<>();
                DFSVisitColetandoComponente(GT, v, conhecidoT, TempoInicioT, TempoFinalT, tempo, componente);
                componentes.add(componente);
            }
        }
        return componentes;
    }

    public void DFSvisit(Grafo grafo, String v, Set<String> conhecido, Map<String, Integer> TempoInicio, Map<String, Integer> TempoFinal, int[] tempo) {
        conhecido.add(v);
        tempo[0]++;
        TempoInicio.put(v, tempo[0]);
        for (String u : grafo.adjacencias.get(v)) {
            if (!conhecido.contains(u)) {
                DFSvisit(grafo, u, conhecido, TempoInicio, TempoFinal, tempo);
            }
        }
        tempo[0]++;
        TempoFinal.put(v, tempo[0]);
    }

    public void DFSVisitColetandoComponente(Grafo grafo, String v, Set<String> conhecido, Map<String, Integer> TempoInicio, Map<String, Integer> TempoFinal, int[] tempo, Set<String> componente) {
        conhecido.add(v);
        componente.add(v);
        tempo[0]++;
        TempoInicio.put(v, tempo[0]);
        for (String u : grafo.adjacencias.getOrDefault(v, new HashSet<>())) {
            if (!conhecido.contains(u)) {
                DFSVisitColetandoComponente(grafo, u, conhecido, TempoInicio, TempoFinal, tempo, componente);
            }
        }
        tempo[0]++;
        TempoFinal.put(v, tempo[0]);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java A2_1 <arquivo_de_entrada>");
            return;
        }

        String nomeArquivo = "grafos/" + args[0];

        try {
            Grafo grafo = new Grafo(nomeArquivo);
            A2_1 algoritmo = new A2_1();
            List<Set<String>> componentes = algoritmo.kosaraju(grafo);

            for (Set<String> componente : componentes) {
                List<String> ordenado = new ArrayList<>(componente);
                ordenado.sort(Comparator.naturalOrder());
                System.out.println(String.join(",", ordenado));
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
