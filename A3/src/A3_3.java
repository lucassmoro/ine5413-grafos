
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */
public class A3_3 {
    
    public void Lawler(Grafo grafo){
        int n = grafo.qtdVertices();
        int[] X = new int[1 << n];
        X[0] = 0;
        
        for (int s = 1; s < (1 << n); s++){
            X[s] = Integer.MAX_VALUE;
            for (int i = 0; i < (1 << n); i++) {
                if ((i & s) == i) {
                    if (isIndependente(i, grafo, n)) {
                        int resto = s & ~i;
                        if (X[resto] != Integer.MAX_VALUE) {
                            X[s] = Math.min(X[s], X[resto] + 1);
                        }
                    }
                }
            }
        }
    }
    public boolean isIndependente(int subconjunto, Grafo grafo, int n) {
        List<String> lista = new ArrayList<>(grafo.vertices);
        for (int u = 0; u < n; u++){
            if ((subconjunto & (1 << u)) == 0){
                continue;
            }

            for (int v = u + 1; v < n; v++) {
                if ((subconjunto & (1 << v)) == 0){
                    continue;
                }

                String su = lista.get(u);
                String sv = lista.get(v);
                if (grafo.haAresta(su, sv)) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Uso: java A3_3 <arquivo.net>");
            return;
        }
        Grafo grafo = new Grafo(args[0]);
        A3_3 algoritmo = new A3_3();
        int n = grafo.qtdVertices();
        int[] X = new int[1 << n];
        int[] corPorIndice = new int[n];

        X[0] = 0;
        for (int s = 1; s < (1 << n); s++) {
            X[s] = Integer.MAX_VALUE;
            for (int i = s; i > 0; i = (i - 1) & s) {
                if (algoritmo.isIndependente(i, grafo, n)) {
                    int resto = s & ~i;
                    if (X[resto] != Integer.MAX_VALUE) {
                        X[s] = Math.min(X[s], X[resto] + 1);
                    }
                }
            }
        }

        int estado = (1 << n) - 1;
        int corAtual = 1;
        List<String> lista = new ArrayList<>(grafo.vertices);
        while (estado > 0) {
            for (int i = estado; i > 0; i = (i - 1) & estado) {
                if (algoritmo.isIndependente(i, grafo, n)) {
                    int resto = estado & ~i;
                    if (X[estado] == X[resto] + 1) {
                        for (int v = 0; v < n; v++) {
                            if ((i & (1 << v)) != 0) {
                                corPorIndice[v] = corAtual;
                            }
                        }
                        estado = resto;
                        corAtual++;
                        break;
                    }
                }
            }
        }
        System.out.println(X[(1 << n) - 1]);

        List<String> ordenados = new ArrayList<>(grafo.vertices);
        ordenados.sort((a, b) -> grafo.verticeParaIndice.get(a) - grafo.verticeParaIndice.get(b));

        List<String> cores = new ArrayList<>();
        for (String v : ordenados) {
            int idx = grafo.verticeParaIndice.get(v) - 1;
            cores.add(String.valueOf(corPorIndice[idx]));
        }
        System.out.println(String.join(", ", cores));
    }

}