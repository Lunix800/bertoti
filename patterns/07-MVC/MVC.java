import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MVC {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.adicionar(new Tarefa("Pagar conta", 10));

        Projeto estudo = new Projeto("Estudar");
        estudo.adicionar(new Tarefa("Ler resumo", 20));
        estudo.adicionar(new Tarefa("Fazer exercicio", 30));
        controller.adicionar(estudo);

        controller.mudarOrdem(new PorDuracao());
        controller.mostrar();
    }

    interface Item {
        String nome();
        int duracao();
        void exibir(String espaco);
    }

    static class Tarefa implements Item {
        String nome;
        int minutos;

        Tarefa(String nome, int minutos) {
            this.nome = nome;
            this.minutos = minutos;
        }

        public String nome() {
            return nome;
        }

        public int duracao() {
            return minutos;
        }

        public void exibir(String espaco) {
            System.out.println(espaco + "- " + nome + " - " + minutos + " min");
        }
    }

    static class Projeto implements Item {
        String nome;
        List<Item> itens = new ArrayList<>();

        Projeto(String nome) {
            this.nome = nome;
        }

        void adicionar(Item item) {
            itens.add(item);
        }

        public String nome() {
            return nome;
        }

        public int duracao() {
            int total = 0;
            for (Item item : itens) {
                total += item.duracao();
            }
            return total;
        }

        public void exibir(String espaco) {
            System.out.println(espaco + "+ " + nome + " - " + duracao() + " min");
            for (Item item : itens) {
                item.exibir(espaco + "  ");
            }
        }
    }

    interface Estrategia {
        void ordenar(List<Item> itens);
    }

    static class PorDuracao implements Estrategia {
        public void ordenar(List<Item> itens) {
            itens.sort(Comparator.comparingInt(Item::duracao));
        }
    }

    interface Observador {
        void atualizar(String mensagem);
    }

    static class Model {
        List<Item> itens = new ArrayList<>();
        List<Observador> observadores = new ArrayList<>();
        Estrategia estrategia = new PorDuracao();

        void adicionar(Item item) {
            itens.add(item);
            avisar("Item adicionado: " + item.nome());
        }

        void setEstrategia(Estrategia estrategia) {
            this.estrategia = estrategia;
            avisar("Ordem alterada");
        }

        List<Item> listar() {
            List<Item> copia = new ArrayList<>(itens);
            estrategia.ordenar(copia);
            return copia;
        }

        void observar(Observador observador) {
            observadores.add(observador);
        }

        void avisar(String mensagem) {
            for (Observador observador : observadores) {
                observador.atualizar(mensagem);
            }
        }
    }

    static class View implements Observador {
        public void atualizar(String mensagem) {
            System.out.println("[View] " + mensagem);
        }

        void mostrar(List<Item> itens) {
            System.out.println("\nAgenda:");
            for (Item item : itens) {
                item.exibir("");
            }
        }
    }

    static class Controller {
        Model model;
        View view;

        Controller(Model model, View view) {
            this.model = model;
            this.view = view;
            this.model.observar(view);
        }

        void adicionar(Item item) {
            model.adicionar(item);
        }

        void mudarOrdem(Estrategia estrategia) {
            model.setEstrategia(estrategia);
        }

        void mostrar() {
            view.mostrar(model.listar());
        }
    }
}

