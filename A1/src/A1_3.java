import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class A1_3 {

    public static void CicloEuleriano(Grafo grafo){
        if (!HaCicloEuleriano(grafo)){
            System.out.print(0);
        } else {
            Stack<String> pilha = new Stack<>();
            ArrayList<String> lista = new ArrayList<>();
            
            Map<String, Set<String>> copiaAdj = new HashMap<>();
            for (String v : grafo.vertices) {
                copiaAdj.put(v, new HashSet<>(grafo.adjacencias.get(v)));
            }
            
            String inicial = null;
            for (String v : grafo.vertices) {
                if (!grafo.adjacencias.get(v).isEmpty()) {
                    inicial = v;
                    break;
                }
            }
            if (inicial == null) System.out.println(new ArrayList<>());
            pilha.push(inicial);
            while (!pilha.isEmpty()) {
                String u = pilha.peek();
                if (!copiaAdj.get(u).isEmpty()) {
                    String v = copiaAdj.get(u).iterator().next();
                    copiaAdj.get(u).remove(v);
                    copiaAdj.get(v).remove(u);
                    pilha.push(v);
                } else {
                    lista.add(pilha.pop());
                }
            }
            System.out.println(1);

            List<Integer> indices = new ArrayList<>();
            for (String rotulo : lista) {
                indices.add(grafo.verticeParaIndice.get(rotulo));
            }
            System.out.println(indices.toString().replaceAll("[\\[\\]]", ""));
        }
    }    
    public static boolean HaCicloEuleriano(Grafo grafo){
        for (String v : grafo.vertices){
            if (grafo.grau(v)%2 != 0){
                return false;
            }     
        }
        return ehConexo(grafo);
    }
    
    
    public static boolean ehConexo(Grafo grafo) {
    Set<String> visitados = new HashSet<>();

    String inicial = null;
    for (String v : grafo.vertices) {
        if (!grafo.adjacencias.get(v).isEmpty()) {
            inicial = v;
            break;
        }
    }
    if (inicial == null) return true;
    dfs(inicial, visitados, grafo);

    for (String v : grafo.vertices) {
        if (!grafo.adjacencias.get(v).isEmpty() && !visitados.contains(v)) {
            return false;
        }
    }
    return true; 
}
    
    
    public static void dfs(String v, Set<String> visitados, Grafo grafo) {
    visitados.add(v);
        for (String vizinho : grafo.adjacencias.get(v)) {
            if (!visitados.contains(vizinho)) {
            dfs(vizinho, visitados, grafo);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Uso: java A1_3 <arquivo>");
            return;
        }

        String arquivo = "grafos/" + args[0];
        Grafo grafo = new Grafo(arquivo);
        CicloEuleriano(grafo);
    }
}
