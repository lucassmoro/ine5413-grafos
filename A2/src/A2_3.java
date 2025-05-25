import java.io.*;
import java.util.*;

public class A2_3 {

    public Grafo Kruskal(Grafo grafo){
        Grafo A = new Grafo();
        Map<String,String> S = new HashMap<>();

        for (String v : grafo.vertices){
            A.vertices.add(v);
            A.adjacencias.put(v, new HashSet<>());
            A.pesos.put(v, new HashMap<>());
            S.put(v, v);
        }

        List<Aresta> E = new ArrayList<>();
        for (String v : grafo.adjacencias.keySet()){
            for (String u : grafo.adjacencias.get(v)){
                if (v.compareTo(u) < 0) {
                    double peso = grafo.peso(u, v);
                    E.add(new Aresta(u, v, peso));
                }
            }
        }

        E.sort(Comparator.comparingDouble(a -> a.peso));

        for (Aresta a : E) {
            String u = a.u;
            String v = a.v;
            String compU = S.get(u), compV = S.get(v);
            if (!compU.equals(compV)) {
                A.adjacencias.get(u).add(v);
                A.adjacencias.get(v).add(u);
                A.pesos.get(u).put(v, a.peso);
                A.pesos.get(v).put(u, a.peso);
                for (String w : S.keySet()) {
                    if (S.get(w).equals(compV)) {
                        S.put(w, compU);
                    }
                }
            }
        }
        return A;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Uso: java A2_3 <arquivo_grafo>");
            return;
        }

        String nomeArquivo = args[0];
        Grafo grafo = new Grafo();
        Map<Integer, String> indiceParaVertice = new HashMap<>();
        Map<String, Integer> verticeParaIndice = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            boolean lendoVertices = false;
            boolean lendoArestas = false;
            String linha;
            int indice = 1;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;

                if (linha.toLowerCase().startsWith("*vertices")) {
                    lendoVertices = true;
                    lendoArestas = false;
                    continue;
                } else if (linha.toLowerCase().startsWith("*edges") || linha.toLowerCase().startsWith("*arcs")) {
                    lendoVertices = false;
                    lendoArestas = true;
                    continue;
                }

                if (lendoVertices) {
                    String[] partes = linha.split(" ", 2);
                    if (partes.length < 2) continue;
                    String nome = partes[1].replaceAll("\"", "").trim();

                    grafo.vertices.add(nome);
                    grafo.adjacencias.put(nome, new HashSet<>());
                    grafo.pesos.put(nome, new HashMap<>());
                    indiceParaVertice.put(indice, nome);
                    verticeParaIndice.put(nome, indice);
                    indice++;
                } else if (lendoArestas) {
                    String[] partes = linha.split(" ");
                    if (partes.length < 3) continue;

                    try {
                        int idxU = Integer.parseInt(partes[0]);
                        int idxV = Integer.parseInt(partes[1]);
                        double peso = Double.parseDouble(partes[2]);

                        String u = indiceParaVertice.get(idxU);
                        String v = indiceParaVertice.get(idxV);

                        grafo.adjacencias.get(u).add(v);
                        grafo.adjacencias.get(v).add(u);
                        grafo.pesos.get(u).put(v, peso);
                        grafo.pesos.get(v).put(u, peso);
                    } catch (NumberFormatException | NullPointerException e) {
                        System.err.println("Erro ao processar linha de aresta: " + linha);
                    }
                }
            }
        }

        A2_3 algoritmo = new A2_3();
        Grafo agm = algoritmo.Kruskal(grafo);

        double somaPesos = 0.0;
        Set<String> arestasImpressas = new HashSet<>();
        List<String> arestasFormatadas = new ArrayList<>();

        for (String u : agm.adjacencias.keySet()) {
            for (String v : agm.adjacencias.get(u)) {
                int idxU = verticeParaIndice.get(u);
                int idxV = verticeParaIndice.get(v);
                String id = idxU < idxV ? idxU + "-" + idxV : idxV + "-" + idxU;

                if (!arestasImpressas.contains(id)) {
                    somaPesos += agm.pesos.get(u).get(v);
                    arestasImpressas.add(id);
                    arestasFormatadas.add(id);
                }
            }
        }

        System.out.println(somaPesos);
        System.out.println(String.join(", ", arestasFormatadas));
    }
}
