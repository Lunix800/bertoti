class AntipatternObserver {
    public static void main(String[] args) {
        Tarefa tarefa = new Tarefa("Tomar remedio");
        tarefa.concluir();
    }

    static class Tarefa {
        String nome;

        Tarefa(String nome) {
            this.nome = nome;
        }

        void concluir() {
            System.out.println("Tarefa concluida: " + nome);
            enviarEmail();
            atualizarPainel();
            salvarHistorico();
        }

        void enviarEmail() {
            System.out.println("Email enviado");
        }

        void atualizarPainel() {
            System.out.println("Painel atualizado");
        }

        void salvarHistorico() {
            System.out.println("Historico salvo");
        }
    }
}

