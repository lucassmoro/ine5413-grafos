# Atividade A2 – Grafos (INE5413)

Este repositório contém a implementação de três algoritmos: Edmonds.

---

## 1. Edmonds-Karp (Fluxo Máximo)

Crie um programa que receba um grafo dirigido e ponderado como argumento o vértice
de origem s e o vértice de destino t. Ao final, imprima na tela o valor do fluxo máximo resultante da execução do
algoritmo de Edmonds-Karp considerando o fluxo de s para t, como no exemplo abaixo.



#### Exemplo de saída:
```plaintext
3,4,5
1,2,6,7
```

---

## 2. Hopcroft-Karp (Emparelhamento Máximo)

Implemente um programa que:
- Recebe como argumento um grafo dirigido e não ponderado com vértices rotulados
- Executa a **ordenação topológica**
- Imprime a ordem dos rótulos dos vértices

#### Exemplo de saída:
```plaintext
Acordar, DesligarDespertador, CalçarSandalias, LevantarDaCama, TomarBanho, EscovarOsDentes, PrepararCafe, PrepararOvosMexidos, TomarCafeDaManha, LavarLouças, EscovarOsDentes2, CalçarMeias, VestirUniforme, ColocarSapato, FecharCasa
```

---

## 3. Lawler (Coloração Ótima)

Implemente um programa que:
- Recebe como argumento um grafo não dirigido e ponderado
- Determina a **árvore geradora mínima** utilizando Kruskal ou Prim
- Imprime:
  - A soma dos pesos na primeira linha
  - A lista de arestas na segunda linha

#### Exemplo de saída:
```plaintext
22.0
6-5, 1-2, 3-4, 4-1, 5-2
```

---

## 4. Relatório (3,0 pts)

Elabore um relatório de **uma página** em PDF contendo:
- Justificativas para as estruturas de dados utilizadas em cada exercício
- Nomes dos integrantes da equipe

---

## Padrão de Arquivo de Entrada

O arquivo de entrada deve seguir o seguinte formato:

- Primeira linha: número de vértices
- Linhas seguintes: rótulo de cada vértice com índice de 1 a n
- Linha com `*edges` ou `*arcs`:
  - `*edges` para grafos **não direcionados**
  - `*arcs` para grafos **direcionados**
- Linhas seguintes: cada aresta no formato `a b valor_do_peso`

#### Exemplo:
```plaintext
*vertices 3
1 A
2 B
3 C
*edges
1 2 4.0
2 3 5.0
```

