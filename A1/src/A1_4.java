import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Lucas
 */
public class A1_4 {
    
    public static void Dijkstra(Grafo grafo, String origem){
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> anterior = new HashMap<>();
        Set<String> naoVisitados = new HashSet<>(grafo.vertices);

        for (String v : grafo.vertices){
            dist.put(v, Double.POSITIVE_INFINITY);
            anterior.put(v, null);
        }
        dist.put(origem, 0.0);
        while(!naoVisitados.isEmpty()){
            String u = null;
            double menorDistancia = Double.POSITIVE_INFINITY;
            for (String v : naoVisitados) {
            if (dist.get(v) < menorDistancia) {
                menorDistancia = dist.get(v);
                u = v;
                }
            }
            naoVisitados.remove(u);
            
            for (String vizinho : grafo.vizinhos(u)) { 
                if (!naoVisitados.contains(vizinho)) continue;
                double peso = grafo.pesos.get(u).get(vizinho);
                double alt = dist.get(u) + peso;

                if (alt < dist.get(vizinho)) {
                dist.put(vizinho, alt);
                anterior.put(vizinho, u);
                }
            }
        }
        for (String destino : grafo.vertices) {
            List<String> caminho = new ArrayList<>();
            String atual = destino;

            while (atual != null) {
            caminho.add(0, atual);
            atual = anterior.get(atual);
            }

            if (dist.get(destino) == Double.POSITIVE_INFINITY) {
                continue;
            }
            List<String> indices = new ArrayList<>();
            for (String rotulo : caminho) {
                indices.add(String.valueOf(grafo.verticeParaIndice.get(rotulo)));
            }
            String linha = grafo.verticeParaIndice.get(destino) + ": " +
                           String.join(",", indices) +
                           "; d=" + dist.get(destino).intValue();

            System.out.println(linha);
        }
    }
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Uso: java A1_4 <arquivo> <rotulo-origem>");
            return;
        }

        String arquivo = args[0];
        String origem = args[1];

        Grafo grafo = new Grafo(arquivo);
        Dijkstra(grafo, origem);
    }
}
