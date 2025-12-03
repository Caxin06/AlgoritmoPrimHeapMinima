import java.util.ArrayList;
import java.util.List;

public class GrafoPrim {
    List<List<Aresta>> adjacencia;
    List<Vertice> vertices;

    public GrafoPrim(int numeroVertices) {
        adjacencia = new ArrayList<>();
        vertices = new ArrayList<>();

        for (int i = 0; i < numeroVertices; i++) {
            vertices.add(new Vertice(i));
            adjacencia.add(new ArrayList<>());
        }
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        Vertice vOrigem = vertices.get(origem);
        Vertice vDestino = vertices.get(destino);
        adjacencia.get(origem).add(new Aresta(vDestino, peso));
        adjacencia.get(destino).add(new Aresta(vOrigem, peso));
    }


    public void executarPrim(int indiceRaiz) {
        System.out.println("\n INICIANDO ALGORITMO DO PRIM (Raiz: " + indiceRaiz + ") \n");

        MinHeap Q = new MinHeap();
        Vertice raiz = vertices.get(indiceRaiz);
        raiz.chave = 0;

        for (Vertice v : vertices) {
            Q.adicionar(v);
        }
        Q.diminuirChave(raiz);

        while (!Q.estaVazio()) {
            Vertice u = Q.extractMin();
            u.visitado = true;

            System.out.println("------------------------------------------------");
            System.out.println("1. Extraído Mínimo: Vértice " + u.id + " (Peso atual: " + u.chave + ")");

            List<Aresta> vizinhos = adjacencia.get(u.id);
            boolean atualizouAlgum = false;

            for (Aresta aresta : vizinhos) {
                Vertice v = aresta.destino;
                int pesoAresta = aresta.peso;

                if (!v.visitado && pesoAresta < v.chave) {
                    System.out.println("   -> Atualizando vizinho V" + v.id + ":");
                    System.out.println("      Peso antigo: " + v.chave);
                    System.out.println("      Novo peso:   " + pesoAresta + " (Vindo de V" + u.id + ")");

                    v.pai = u;
                    v.chave = pesoAresta;
                    Q.diminuirChave(v);
                    atualizouAlgum = true;
                }
            }

            if (!atualizouAlgum) {
                System.out.println("   (Nenhum vizinho precisou ser atualizado)");
            }
        }

        imprimirResultado();
    }

    public void imprimirResultado() {
        System.out.println("\n================================================");
        System.out.println("RESULTADO FINAL: ÁRVORE GERADORA MÍNIMA (MST)");
        System.out.println("================================================");

        int custoTotal = 0;
        System.out.println("Arestas escolhidas:");

        for (Vertice v : vertices) {
            if (v.pai != null) {
                System.out.println("  (V" + v.pai.id + ") ----- " + v.chave + " ----- (V" + v.id + ")");
                custoTotal += v.chave;
            }
        }
        System.out.println("------------------------------------------------");
        System.out.println("CUSTO TOTAL DA ÁRVORE: " + custoTotal);
        System.out.println("================================================");
    }

    public static void main(String[] args) {
        GrafoPrim grafo = new GrafoPrim(5);

        grafo.adicionarAresta(0, 1, 2);
        grafo.adicionarAresta(0, 3, 6);
        grafo.adicionarAresta(1, 2, 3);
        grafo.adicionarAresta(1, 3, 8);
        grafo.adicionarAresta(1, 4, 5);
        grafo.adicionarAresta(2, 4, 7);
        grafo.adicionarAresta(3, 4, 9);

        grafo.executarPrim(0);
    }
}