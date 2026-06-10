import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class PatternStrategy {
    public static void main(String[] args) {
        Organizador organizador = new Organizador(new PorPrazo());
        organizador.adicionar(new Tarefa("Pagar conta", 2, 1));
        organizador.adicionar(new Tarefa("Estudar Java", 5, 3));
        organizador.adicionar(new Tarefa("Comprar comida", 1, 2));

        organizador.organizar();
        organizador.imprimir("Por prazo");

        organizador.setEstrategia(new PorPrioridade());
        organizador.organizar();
        organizador.imprimir("Por prioridade");
    }

    interface Estrategia {
        void ordenar(List<Tarefa> tarefas);
    }

    static class PorPrazo implements Estrategia {
        public void ordenar(List<Tarefa> tarefas) {
            tarefas.sort(Comparator.comparingInt(t -> t.prazo));
        }
    }

    static class PorPrioridade implements Estrategia {
        public void ordenar(List<Tarefa> tarefas) {
            tarefas.sort(Comparator.comparingInt((Tarefa t) -> t.prioridade).reversed());
        }
    }

    static class Organizador {
        List<Tarefa> tarefas = new ArrayList<>();
        Estrategia estrategia;

        Organizador(Estrategia estrategia) {
            this.estrategia = estrategia;
        }

        void adicionar(Tarefa tarefa) {
            tarefas.add(tarefa);
        }

        void setEstrategia(Estrategia estrategia) {
            this.estrategia = estrategia;
        }

        void organizar() {
            estrategia.ordenar(tarefas);
        }

        void imprimir(String titulo) {
            System.out.println("\n" + titulo + ":");
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa.nome + " - prazo " + tarefa.prazo + " - prioridade " + tarefa.prioridade);
            }
        }
    }

    static class Tarefa {
        String nome;
        int prazo;
        int prioridade;

        Tarefa(String nome, int prazo, int prioridade) {
            this.nome = nome;
            this.prazo = prazo;
            this.prioridade = prioridade;
        }
    }
}

