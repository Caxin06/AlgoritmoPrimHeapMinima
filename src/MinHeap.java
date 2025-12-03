import java.util.ArrayList;

public class MinHeap {
    private ArrayList<Vertice> lista;

    public MinHeap() {
        this.lista = new ArrayList<>();
    }

    public void adicionar(Vertice v) {
        lista.add(v);
    }

    public boolean estaVazio() {
        return lista.isEmpty();
    }

    public boolean contem(Vertice v) {
        return lista.contains(v);
    }

    public Vertice extractMin() {
        if (lista.isEmpty()) {
            return null;
        }

        Vertice min = lista.get(0);

        int ultimoIndice = lista.size() - 1;
        Vertice ultimo = lista.get(ultimoIndice);

        lista.remove(ultimoIndice);

        if (!lista.isEmpty()) {
            lista.set(0, ultimo);
            OrganizarHeap(0);
        }

        return min;
    }

    private void OrganizarHeap(int indice) {
        int menor = indice;
        int esquerda = 2 * indice + 1;
        int direita = 2 * indice + 2;

        if (esquerda < lista.size()) {
            if (lista.get(esquerda).chave < lista.get(menor).chave) {
                menor = esquerda;
            }
        }
        if (direita < lista.size()) {
            if (lista.get(direita).chave < lista.get(menor).chave) {
                menor = direita;
            }
        }
        if (menor != indice) {
            trocar(indice, menor);
            OrganizarHeap(menor);
        }
    }

    public void diminuirChave(Vertice v) {
        int indice = lista.indexOf(v);

        while (indice > 0) {
            int pai = (indice - 1) / 2;
            if (lista.get(indice).chave < lista.get(pai).chave) {
                trocar(indice, pai);
                indice = pai;
            } else {
                break;
            }
        }
    }

    private void trocar(int i, int j) {
        Vertice temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
}