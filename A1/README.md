# Atividade Prática A1 – Grafos (INE5413)

Este projeto contempla a implementação de operações fundamentais sobre grafos não-dirigidos e ponderados, incluindo representação, buscas, detecção de ciclo euleriano, algoritmos de caminhos mínimos e um relatório explicativo.

## 1. Representação do Grafo (2,0 pts)

Implemente um tipo estruturado ou classe para representar um grafo não-dirigido e ponderado \( G(V, E, w) \), onde:
- \( V \) é o conjunto de vértices
- \( E \) é o conjunto de arestas
- \( w: E 
ightarrow \mathbb{R} \) representa os pesos das arestas

### Métodos obrigatórios:
- `qtdVertices()`: retorna o número de vértices;
- `qtdArestas()`: retorna o número de arestas;
- `grau(v)`: retorna o grau do vértice `v`;
- `rotulo(v)`: retorna o rótulo do vértice `v`;
- `vizinhos(v)`: retorna os vizinhos do vértice `v`;
- `haAresta(u, v)`: retorna `true` se {u, v} ∈ E; `false` caso contrário;
- `peso(u, v)`: retorna o peso de {u, v}; ou infinito positivo se não existir;
- `ler(arquivo)`: carrega o grafo a partir de um arquivo no formato definido.

---

## 2. Busca em Largura (2,0 pts)

Implemente um programa que recebe:
- um arquivo com a definição do grafo
- o índice do vértice inicial `s`

Executa uma **busca em largura (BFS)** e imprime os vértices por nível:

#### Exemplo de saída:
```plaintext
0: 8
1: 3,4,5
2: 1,2,6,7
```

---

## 3. Ciclo Euleriano (2,0 pts)

Implemente um programa que:
- recebe um grafo como argumento
- determina se há um ciclo euleriano e, caso haja, imprime a sequência de vértices do ciclo.

#### Exemplo de saída (com ciclo):
```plaintext
1
2,4,3,1,5,6,2
```

#### Exemplo de saída (sem ciclo):
```plaintext
0
```

---

## 4. Algoritmo de Bellman-Ford ou Dijkstra (2,0 pts)

Implemente um programa que:
- recebe um grafo e um vértice inicial `s`
- executa o algoritmo de **Bellman-Ford** ou **Dijkstra**
- imprime o menor caminho e a distância de `s` até cada vértice

#### Exemplo de saída:
```plaintext
1: 2,3,4,1; d=7
2: 2; d=0
3: 2,3; d=4
4: 2,3,4; d=6
5: 2,3,5; d=8
```

---

## 5. Algoritmo de Floyd-Warshall (2,0 pts)

Implemente um programa que:
- recebe um grafo como argumento
- executa o algoritmo de **Floyd-Warshall**
- imprime a matriz de distâncias entre todos os pares de vértices

#### Exemplo de saída:
```plaintext
1:0,10,3,5
2:10,0,9,8
3:3,9,0,11
4:5,8,11,0
```

---

## 6. Relatório (2,0 pts)

Elabore um relatório de uma página em formato PDF contendo:
- justificativas para as estruturas de dados utilizadas em cada item;
- nomes dos integrantes da equipe.
