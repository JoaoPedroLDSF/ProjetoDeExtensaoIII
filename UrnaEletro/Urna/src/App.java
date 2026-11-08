public class App {

    public static void main(String[] args) {

    
        Urna urna = new Urna();
        Eleitor eleitorNulo = new Eleitor("Lucas");

System.out.println("Votos nulos");

System.out.println("Nulo para presidente primeiro: "
    + urna.votarNuloPresidente(eleitorNulo, 999));

System.out.println("Nulo para vereador: "
    + urna.votarNuloVereador(eleitorNulo, 999));

System.out.println("Nulo para presidente depois: "
    + urna.votarNuloPresidente(eleitorNulo, 999));

System.out.println("Finalizou: "
    + eleitorNulo.jaFinalizouVotacao());
}}
/* Aqui pode reorganizar, utilizar como ambiente de testes antes do swing*/