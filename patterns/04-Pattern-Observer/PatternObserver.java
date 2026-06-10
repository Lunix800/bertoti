import java.util.ArrayList;
import java.util.List;

class PatternObserver {
    public static void main(String[] args) {
        Tarefa tarefa = new Tarefa("Tomar remedio");
        tarefa.adicionar(new Email());
        tarefa.adicionar(new Painel());
        tarefa.adicionar(new Historico());
        tarefa.concluir();
    }

    interface Observador {
        void atualizar(Tarefa tarefa);
    }

    static class Tarefa {
        String nome;
        String status = "pendente";
        List<Observador> observadores = new ArrayList<>();

        Tarefa(String nome) {
            this.nome = nome;
        }

        void adicionar(Observador observador) {
            observadores.add(observador);
        }

        void concluir() {
            status = "concluida";
            for (Observador observador : observadores) {
                observador.atualizar(this);
            }
        }
    }

    static class Email implements Observador {
        public void atualizar(Tarefa tarefa) {
            System.out.println("Email: " + tarefa.nome + " foi " + tarefa.status);
        }
    }

    static class Painel implements Observador {
        public void atualizar(Tarefa tarefa) {
            System.out.println("Painel atualizado");
        }
    }

    static class Historico implements Observador {
        public void atualizar(Tarefa tarefa) {
            System.out.println("Historico salvo");
        }
    }
}

