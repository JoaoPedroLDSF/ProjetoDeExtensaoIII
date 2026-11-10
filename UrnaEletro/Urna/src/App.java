public class App {

    public static void main(String[] args) {

        Urna urna = new Urna();

        // ===== Cadastro de candidatos =====
        urna.adicionarVereador(new Vereador(12345, "Maria Silva", "PXX"));
        urna.adicionarVereador(new Vereador(54321, "João Souza", "PYY"));
        urna.adicionarVereador(new Vereador(11111, "Carlos Miguel", "CRB"));

        urna.adicionarPresidente(new Presidente(23, "Ian Silva", "PB"));
        urna.adicionarPresidente(new Presidente(17, "Casimiro Miguel", "CAZE TV"));
        // ==================================

        new MenuConsole(urna).iniciar();
    }
}