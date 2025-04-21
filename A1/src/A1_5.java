import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lucas
 */
public class A1_5 {
    public static void FloydWarshall(Grafo grafo){
        int n = grafo.vertices.size();
        double[][] dist = new double[n][n];
        
        for (String vi : grafo.vertices) {
            for (String vj : grafo.vertices) {
                int i = grafo.verticeParaIndice.get(vi) - 1;
                int j = grafo.verticeParaIndice.get(vj) - 1;

                if (vi.equals(vj)) {
                    dist[i][j] = 0;
                } else if (grafo.pesos.containsKey(vi) && grafo.pesos.get(vi).containsKey(vj)) {
                dist[i][j] = grafo.pesos.get(vi).get(vj);
                } else {
                dist[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        Map<Integer, String> indiceParaRotulo = new HashMap<>();
        for (Map.Entry<String, Integer> entry : grafo.verticeParaIndice.entrySet()) {
           indiceParaRotulo.put(entry.getValue(), entry.getKey());
        }
        for (int i = 1; i <= n; i++) {
            String vi = indiceParaRotulo.get(i);
            int idxI = i - 1;

            StringBuilder linha = new StringBuilder(i + ":");

            for (int j = 0; j < n; j++) {
                if (j > 0) linha.append(",");
                if (dist[idxI][j] == Double.POSITIVE_INFINITY) {
                    linha.append("INF"); 
                } else {
                    linha.append((int) dist[idxI][j]); 
                }
            }
            System.out.println(linha);
        }
    }
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Uso: java A1_5 <arquivo>");
            return;
        }

        String arquivo = args[0];
        Grafo grafo = new Grafo(arquivo);
        FloydWarshall(grafo);
    }
}
