import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class A1_2 {
   
   public static void Buscas(Grafo grafo, int indice){
       String vertice = grafo.indiceParaVertice.get(indice);
       Queue<String> fila = new LinkedList<>();
       Map<String, Integer> nivel = new HashMap<>();
       
       fila.add(vertice);
       nivel.put(vertice, 0);
       while (!fila.isEmpty()) {
           String u = fila.remove();
           int nivelAtual = nivel.get(u);
           for (String v : grafo.vizinhos(u)){
               if (!nivel.containsKey(v)){
                   nivel.put(v, nivelAtual + 1);
                   fila.add(v);
               }
           }
       }
        Map<Integer, List<String>> porNivel = new TreeMap<>();
        for (Map.Entry<String, Integer> entrada : nivel.entrySet()) {
            int n = entrada.getValue();
            porNivel.putIfAbsent(n, new ArrayList<>());
            porNivel.get(n).add(entrada.getKey());
        }
        for (Map.Entry<Integer, List<String>> entrada : porNivel.entrySet()) {
            System.out.print(entrada.getKey() + ": ");
            List<Integer> indices = new ArrayList<>();
            for (String rotulo : entrada.getValue()) {
                indices.add(grafo.verticeParaIndice.get(rotulo));
            }
            Collections.sort(indices);
            System.out.println(indices.toString().replaceAll("[\\[\\]]", ""));

        }

   }
   public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Uso: java A1_2 <arquivo> <indice>");
            return;
        }
        String arquivo = "grafos/" + args[0];
        int indice = Integer.parseInt(args[1]);

        Grafo grafo = new Grafo(arquivo);
        Buscas(grafo, indice);
    }
}
