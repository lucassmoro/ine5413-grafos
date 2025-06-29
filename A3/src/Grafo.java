import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grafo {
    
    Set<String> vertices;
    Map<String, Set<String>> adjacencias;
    Map<String, Map<String, Double>> pesos; 
    Map<Integer, String> indiceParaVertice = new HashMap<>();
    Map<String, Integer> verticeParaIndice = new HashMap<>();
    boolean direcionado;


    public Grafo(String entrada) throws IOException{
        
        this.vertices = new HashSet<>();
        this.adjacencias = new HashMap<>();
        this.pesos = new HashMap<>();
        ler(entrada);
    }
    public Grafo (){
        this.vertices = new HashSet<>();
        this.adjacencias = new HashMap<>();
        this.pesos = new HashMap<>();
    }
    
    public int qtdVertices(){
        return this.vertices.size();
    }
    
    public int qtdArestas(){
        int soma = 0;
        for (String v : adjacencias.keySet()){
            soma+= adjacencias.get(v).size();
        }
        return direcionado? soma : soma/2;
    }
    public int grau(String v){
        if (!adjacencias.containsKey(v)){
            return 0;
        }
        return adjacencias.get(v).size();
    }
    public String rotulo(String v){
        return v;
    }
    public Set<String> vizinhos(String v){
        return adjacencias.getOrDefault(v, new HashSet<>());
    }

    public boolean haAresta(String u, String v){
        return adjacencias.containsKey(u) && adjacencias.get(u).contains(v);
    }
    public Double peso(String u, String v){
        if (haAresta(u,v)){
            return pesos.get(u).get(v);
        }
        return Double.POSITIVE_INFINITY;
    }
    public void ler(String arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        String linha;

        linha = br.readLine().trim();
        String[] header = linha.split("\\s+");
        if (header.length < 2 || !header[0].equalsIgnoreCase("*vertices")) {
            throw new IOException("Formato invalido: primeira linha deve ser '*vertices n'");
        }
        int n = Integer.parseInt(header[1]);

        for (int i = 0; i < n; i++) {
            linha = br.readLine().trim();
            String[] partes = linha.split("\\s+", 2);
            int idx = Integer.parseInt(partes[0]);
            String rotulo = partes[1].replace("\"", "");
            vertices.add(rotulo);
            indiceParaVertice.put(idx, rotulo);
            verticeParaIndice.put(rotulo, idx);
            adjacencias.put(rotulo, new HashSet<>());
            pesos.put(rotulo, new HashMap<>());
        }

        boolean direcionado = false;
        while ((linha = br.readLine()) != null) {
            linha = linha.trim().toLowerCase();
            if (linha.equals("*arcs")) {
                direcionado = true;
                break;
            } else if (linha.equals("*edges")) {
                direcionado = false;
                break;
            }
        }
        this.direcionado = direcionado;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.trim().split("\\s+");
            if (partes.length < 3) continue;

            int idx1 = Integer.parseInt(partes[0]);
            int idx2 = Integer.parseInt(partes[1]);
            double peso = Double.parseDouble(partes[2]);

            String u = indiceParaVertice.get(idx1);
            String v = indiceParaVertice.get(idx2);

            adjacencias.get(u).add(v);
            pesos.get(u).put(v, peso);

         
            if (!this.direcionado) {
                adjacencias.get(v).add(u);
                pesos.get(v).put(u, peso);
            }
        }

        br.close();
    }
    
    
}