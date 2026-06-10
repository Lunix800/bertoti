import java.util.ArrayList;
import java.util.List;

class AntipatternComposite {
    public static void main(String[] args) {
        List<Tarefa> tarefas = new ArrayList<>();
        List<Projeto> projetos = new ArrayList<>();

        tarefas.add(new Tarefa("Responder mensagem", 10));

        Projeto mercado = new Projeto("Ir ao mercado");
        mercado.subtarefas.add(new Tarefa("Fazer lista", 5));
        mercado.subtarefas.add(new Tarefa("Comprar itens", 40));
        projetos.add(mercado);

        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa.nome + " - " + tarefa.minutos + " min");
        }
        for (Projeto projeto : projetos) {
            System.out.println(projeto.nome);
            for (Tarefa tarefa : projeto.subtarefas) {
                System.out.println("  " + tarefa.nome + " - " + tarefa.minutos + " min");
            }
        }
    }

    static class Tarefa {
        String nome;
        int minutos;

        Tarefa(String nome, int minutos) {
            this.nome = nome;
            this.minutos = minutos;
        }
    }

    static class Projeto {
        String nome;
        List<Tarefa> subtarefas = new ArrayList<>();

        Projeto(String nome) {
            this.nome = nome;
        }
    }
}

