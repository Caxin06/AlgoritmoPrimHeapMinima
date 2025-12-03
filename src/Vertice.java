public class Vertice {
    int id;
    int chave;
    Vertice pai;
    boolean visitado;

    public Vertice(int id) {
        this.id = id;
        this.chave = Integer.MAX_VALUE;
        this.pai = null;
        this.visitado = false;
    }

    @Override
    public String toString() {
        return "V" + id;
    }
}