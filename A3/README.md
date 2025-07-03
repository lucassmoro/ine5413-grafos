# Atividade A2 – Grafos (INE5413)

Este repositório contém a implementação de três algoritmos: Edmonds.

---

## 1. Edmonds-Karp (Fluxo Máximo)

Crie um programa que receba um grafo dirigido e ponderado como argumento o vértice
de origem s e o vértice de destino t. Ao final, imprima na tela o valor do fluxo máximo resultante da execução do
algoritmo de Edmonds-Karp considerando o fluxo de s para t, como no exemplo abaixo.



#### Exemplo de saída:
```plaintext
17
```

---

## 2. Hopcroft-Karp (Emparelhamento Máximo)

Crie um programa que receba um arquivo de grafo bipartido, não-dirigido, não-ponderado
e informe qual o valor do emparelhamento máximo e quais arestas pertencem a ele. Utilize o algoritmo de Hopcroft-
Karp. Ao final, imprima na tela a quantidade de emparelhamentos encontrados (na primeira linha) e quais são
as arestas correspondentes (na segunda linha), como no exemplo abaixo. Para as instâncias disponibilizadas pelo
professor, considere que para um grafo com n vértices, os vértices 1, 2, . . . ,[n/2] estão na primeira parte e os vértices [n/2] + 1,
[n/2] + 2, . . ., n estão na segunda parte do grafo bipartido.

#### Exemplo de saída:
```plaintext
3
1-4, 2-5, 3-6
```

---

## 3. Coloração de Vértices (Lawler)

Crie um programa que recebe um grafo não-dirigido e não-ponderado como
argumento. Ao final, informe a coloração mínima e qual número cromático foi utilizado em cada vértice. Use o
algoritmo de Lawler para cumprir essa questão. Ao final, imprima na tela a quantidade de cores encontradas (na
primeira linha) e qual é a cor correspondente a cada vértice (segunda linha), como no exemplo de 6 vértices na
saída abaixo (vértice 1 tem a cor 2, vértice 2 tem a cor 1, vértice 3 tem a cor 2, vértice 4 tem a cor 1, vértice 5 tem
a cor 3 e vértice 6 tem a cor 2)
#### Exemplo de saída:
```plaintext
3
2, 1, 2, 1, 3, 2
```
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

