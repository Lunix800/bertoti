import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class AntipatternStrategy {
    public static void main(String[] args) {
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.add(new Tarefa("Pagar conta", 2, 1));
        tarefas.add(new Tarefa("Estudar Java", 5, 3));
        tarefas.add(new Tarefa("Comprar comida", 1, 2));

        organizar(tarefas, "prazo");
        System.out.println("Por prazo:");
        imprimir(tarefas);

        organizar(tarefas, "prioridade");
        System.out.println("\nPor prioridade:");
        imprimir(tarefas);
    }

    static void organizar(List<Tarefa> tarefas, String criterio) {
        if (criterio.equals("prazo")) {
            tarefas.sort(Comparator.comparingInt(t -> t.prazo));
        } else if (criterio.equals("prioridade")) {
            tarefas.sort(Comparator.comparingInt((Tarefa t) -> t.prioridade).reversed());
        }
    }

    static void imprimir(List<Tarefa> tarefas) {
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa.nome + " - prazo " + tarefa.prazo + " - prioridade " + tarefa.prioridade);
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

