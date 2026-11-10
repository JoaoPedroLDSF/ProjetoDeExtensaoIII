import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Urna {

    private ArrayList<Vereador> vereadores;
    private ArrayList<Presidente> presidentes;
    private int votosBrancosVereador;
    private int votosNulosVereador;
    private int votosBrancosPresidente;
    private int votosNulosPresidente;

public Urna() {
    vereadores = new ArrayList<>();
    presidentes = new ArrayList<>();
}
public boolean adicionarVereador(Vereador vereador) {
    if (buscarVereador(vereador.getNumero()) != null) {
        return false; //número já existe
    }
    vereadores.add(vereador);
    return true;
}


public boolean adicionarPresidente(Presidente presidente) {
    if (buscarPresidente(presidente.getNumero()) != null) {
        return false;
    }
    presidentes.add(presidente);
    return true;
}

public ArrayList<Vereador> getVereadores() {
    return vereadores;
}
public ArrayList<Presidente> getPresidentes() {
    return presidentes;
}

public int getVotosBrancosVereador() {
    return votosBrancosVereador;
}

public  int getVotosNulosVereador() {
    return votosNulosVereador;
}

public int getVotosBrancosPresidente() {
    return votosBrancosPresidente;
}

public int getVotosNulosPresidente() {
    return votosNulosPresidente;
}


public Vereador buscarVereador(int numero) {

    for (Vereador vereador : vereadores) {

        if (vereador.getNumero() == numero) {
            return vereador;
        }

    }

    return null;
}
public Presidente buscarPresidente(int numero) {

    for (Presidente presidente : presidentes) {

        if (presidente.getNumero() == numero) {
            return presidente;
        }

    }

    return null;
}

public boolean votarVereador(Eleitor eleitor, int numero) {

    // Verifica se já votou para vereador
    if (eleitor.jaVotouVereador()) {
        return false;
    }

    Vereador vereador = buscarVereador(numero);

    if (vereador != null) {
        vereador.registrarVoto();
        eleitor.registrarVotoVereador();
        return true;
    }

    return false;
}

public boolean votarPresidente(Eleitor eleitor, int numero) {

    
    if (!eleitor.jaVotouVereador()) {
        return false;
    }

    // Impede votar novamente para presidente
    if (eleitor.jaVotouPresidente()) {
        return false;
    }

    Presidente presidente = buscarPresidente(numero);

    if (presidente != null) {
        presidente.registrarVoto();
        eleitor.registrarVotoPresidente();
        return true;
    }

    return false;
}

public boolean votarBrancoVereador(Eleitor eleitor) {

    if (eleitor.jaVotouVereador()) {
        return false;
    }

    votosBrancosVereador++;
    eleitor.registrarVotoVereador();

    return true;
}

public boolean votarBrancoPresidente(Eleitor eleitor) {

    if (!eleitor.jaVotouVereador()) {
        return false;
    }

    if (eleitor.jaVotouPresidente()) {
        return false;
    }

    votosBrancosPresidente++;
    eleitor.registrarVotoPresidente();

    return true;
}

public boolean votarNuloVereador(Eleitor eleitor, int numero) {

    if (eleitor.jaVotouVereador()) {
        return false;
    }

    Vereador vereador = buscarVereador(numero);

    if (vereador != null) {
        return false;
    }

    votosNulosVereador++;
    eleitor.registrarVotoVereador();

    return true;
}

public boolean votarNuloPresidente(Eleitor eleitor, int numero) {

    if (!eleitor.jaVotouVereador()) {
    return false;
    }

    
    if (eleitor.jaVotouPresidente()) {
        return false;
    }

    Presidente presidente = buscarPresidente(numero);

    if (presidente != null) {
        return false;
    }

    votosNulosPresidente++;
    eleitor.registrarVotoPresidente();

    return true;
}

public void apurarVereador() {
    apurar("VEREADOR", vereadores, votosBrancosVereador, votosNulosVereador);

}

public void apurarPresidente() {
    apurar("PRESIDENTE", presidentes, votosBrancosPresidente, votosNulosPresidente);
}

//Método único que serve para qualquer cargo (polimorfismo: recebe Politico)
private void apurar(String cargo, List<? extends Politico > candidatos, int brancos, int nulos) {

    ArrayList<Politico> ranking = new ArrayList<>(candidatos);
    ranking.sort(Comparator.comparingInt(Politico::getVotos).reversed());

    int validos = 0;
    for (Politico p : ranking) {
        validos += p.getVotos();
    }
    int total = validos + brancos + nulos;

    System.out.println("\n===== APURAÇÂO: " + cargo + " =====");

    if (total == 0) {
        System.out.println("Nenhum voto registrado.");
        return;
    }

    int posicao = 1;
    for(Politico p : ranking) {
        double percentual = p.getVotos() * 100.0 / total;
        System.out.printf("%dº %s (%d) - %s: %d votos (%.1f%%)%n",
            posicao++, p.getNome(), p.getNumero(), p.getPartido(),
            p.getVotos(), percentual);
    }

    System.out.printf("Brancos: %d (%.1f%%)%n", brancos, brancos * 100.0 / total);
    System.out.printf("Nulos: %d (%.1f%%)%n", nulos, nulos * 100.0 / total);
    System.out.println("Total de votos: " + total);

    if ( validos == 0) {
        System.out.println("Resultado: sem votos válidos");
    } else if (ranking.size() > 1 && ranking.get(0).getVotos() == ranking.get(1).getVotos()) {
        System.out.println("Resultado: EMPATE entre os mais votados.");
    } else {
        Politico vencedor = ranking.get(0);
        System.out.println("Vencedor: " + vencedor.getNome() + " ( " + vencedor.getCargo() + ")");
    }
}
}


/*Ryan, Falta fazer a parte da apuração de votos, o cadastro dos politicos, a útilização do swing
se conseguir utilizar o Firebase(Cloud FireStore), acredito que precisamos utilizar do polimorfismo
e abstração no trabalho
*/