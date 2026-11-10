import java.util.Scanner;

public class MenuConsole {

    private static final String SENHA_MESARIO = "1234";

    private Urna urna;
    private Scanner scanner;

    public MenuConsole(Urna urna) {
        this.urna = urna;
        this.scanner = new Scanner(System.in);
    }

    //Loop principal: um eleitor após o outro, até o mesário encerrar

    public void iniciar() {
        boolean aberta = true;

        while (aberta) {
            System.out.println("\n==== URNA ELETRÔNICA ====");
            System.out.print("Nome do eleitor (ou 'mesario' para encerrar): ");
            String nome = scanner.nextLine().trim();

            if(nome.equalsIgnoreCase("mesario")) {
                aberta = !encerrar();
            } else if (nome.isEmpty()) {
                System.out.println("Digite um nome válido.");
            } else {
                votar(new Eleitor(nome));
            }
        }

        scanner.close();
    }

    // Pede a senha e, se estiver certa, mostra a apuração
    private boolean encerrar() {
        System.out.print("Senha do mesário: ");
        String senha = scanner.nextLine().trim();

        if(!senha.equals(SENHA_MESARIO)) {
            System.out.println("Senha incorreta. A votação continua.");
            return false;
        }

        System.out.println("\nVOTAÇÃO ENCERRADA.");
        urna.apurarVereador();
        urna.apurarPresidente();
        return true;
    }

    //Fluxo de um eleitor: vereador primeiro, depois presidente
    private void votar(Eleitor eleitor) {
        System.out.println("\nEleitor: " + eleitor.getNome());

        votarVereador(eleitor);
        votarPresidente(eleitor);

        System.out.println("\nFIM! Voto registrado. Obrigado. " + eleitor.getNome() + ".");

    }
    private void votarVereador(Eleitor eleitor) {

        while(!eleitor.jaVotouVereador()) {
            System.out.println("\n--- VEREADOR ---");
            String entrada = lerEntrada();

            if (entrada.equalsIgnoreCase("B")) {
                if(confirmar("VOTO EM BRANCO")) {
                    urna.votarBrancoVereador(eleitor);
                }
                continue;
            }

            Integer numero = converterNumero(entrada);
            if (numero == null) {
                System.out.println("ENtrada inválida; Digite apenas numeros ou B.");
                continue;
            }

            Vereador vereador = urna.buscarVereador(numero);

            if (vereador != null) {
                mostrarCandidato(vereador);
                if (confirmar("CONFIRMA O VOTO?")) {
                    urna.votarVereador(eleitor, numero);
                }
            } else {
                System.out.println("NUMERO ERRADO - SEU VOTO SERÁ NULO");
                if (confirmar("CONFIRMA O VOTO NULO?")) {
                    urna.votarNuloVereador(eleitor, numero);
                }
            }
        }
    }

      private void votarPresidente(Eleitor eleitor) {
        while (!eleitor.jaVotouPresidente()) {
            System.out.println("\n--- PRESIDENTE ---");
            String entrada = lerEntrada();

            if (entrada.equalsIgnoreCase("B")) {
                if (confirmar("VOTO EM BRANCO")) {
                    urna.votarBrancoPresidente(eleitor);
                }
                continue;
            }

            Integer numero = converterNumero(entrada);
            if (numero == null) {
                System.out.println("Entrada inválida. Digite apenas números ou B.");
                continue;
            }

            Presidente presidente = urna.buscarPresidente(numero);

            if (presidente != null) {
                mostrarCandidato(presidente);
                if (confirmar("CONFIRMA O VOTO?")) {
                    urna.votarPresidente(eleitor, numero);
                }
            } else {
                System.out.println("NÚMERO ERRADO - SEU VOTO SERÁ NULO");
                if (confirmar("CONFIRMA O VOTO NULO?")) {
                    urna.votarNuloPresidente(eleitor, numero);
                }
            }
        }
    }

    // ==== Métodos auxiliares ====

    private String lerEntrada() {
        System.out.print("Digite o numero do candidato (ou B para branco): ");
        return scanner.nextLine().trim();
    }

    // Recebe qualquer Politico (polimorfismo) : funciona para vereador

    private void mostrarCandidato(Politico p) {
        System.out.println("Cargo: " + p.getCargo());
        System.out.println("Nome: " + p.getNome());
        System.out.println("Partido: " + p.getPartido());
    }

    private boolean confirmar (String mensagem) {
        while (true) {
            System.out.print(mensagem + " [1 = CONFIRMA | 2 = CORRIGE]: ");
            String resposta = scanner.nextLine().trim();

            if(resposta.equals("1")) {
                return true;
            }
            if (resposta.equals("2")) {
                return false;
            }
            System.out.println("Opção inválida");
        }
    }

    // Retorna null se o texto não for um número válido

    private Integer converterNumero(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return null;
        }
    
    }
}