import java.util.ArrayList;

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
public void adicionarVereador(Vereador vereador) {
    vereadores.add(vereador);
}
public void adicionarPresidente(Presidente presidente) {
    presidentes.add(presidente);
}
public ArrayList<Vereador> getVereadores() {
    return vereadores;
}
public ArrayList<Presidente> getPresidentes() {
    return presidentes;
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

}


/*Ryan, Falta fazer a parte da apuração de votos, o cadastro dos politicos, a útilização do swing
se conseguir utilizar o Firebase(Cloud FireStore), acredito que precisamos utilizar do polimorfismo
e abstração no trabalho
*/