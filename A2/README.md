# Atividade A2 – Grafos (INE5413)

Este projeto contempla operações em grafos direcionados e não direcionados com foco em algoritmos clássicos como componentes fortemente conexas, ordenação topológica e árvores geradoras mínimas.

---

## 1. Componentes Fortemente Conexas (3,0 pts)

Implemente um programa que:
- Recebe como argumento um grafo dirigido e não ponderado
- Ao final, imprime as componentes fortemente conexas do grafo

#### Exemplo de saída:
```plaintext
3,4,5
1,2,6,7
```

---

## 2. Ordenação Topológica (3,0 pts)

Implemente um programa que:
- Recebe como argumento um grafo dirigido e não ponderado com vértices rotulados
- Executa a **ordenação topológica**
- Imprime a ordem dos rótulos dos vértices

#### Exemplo de saída:
```plaintext
Acordar, DesligarDespertador, CalçarSandalias, LevantarDaCama, TomarBanho, EscovarOsDentes, PrepararCafe, PrepararOvosMexidos, TomarCafeDaManha, LavarLouças, EscovarOsDentes2, CalçarMeias, VestirUniforme, ColocarSapato, FecharCasa
```

---

## 3. Árvore Geradora Mínima - Kruskal ou Prim (3,0 pts)

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
