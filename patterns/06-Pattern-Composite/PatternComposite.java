import java.util.ArrayList;
import java.util.List;

class PatternComposite {
    public static void main(String[] args) {
        Projeto dia = new Projeto("Meu dia");
        dia.adicionar(new Tarefa("Responder mensagem", 10));

        Projeto mercado = new Projeto("Ir ao mercado");
        mercado.adicionar(new Tarefa("Fazer lista", 5));
        mercado.adicionar(new Tarefa("Comprar itens", 40));

        dia.adicionar(mercado);
        dia.exibir("");
    }

    interface Item {
        void exibir(String espaco);
    }

    static class Tarefa implements Item {
        String nome;
        int minutos;

        Tarefa(String nome, int minutos) {
            this.nome = nome;
            this.minutos = minutos;
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

        public void exibir(String espaco) {
            System.out.println(espaco + "+ " + nome);
            for (Item item : itens) {
                item.exibir(espaco + "  ");
            }
        }
    }
}

