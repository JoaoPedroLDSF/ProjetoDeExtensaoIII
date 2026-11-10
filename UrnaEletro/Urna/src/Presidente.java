public class Presidente extends Politico {

    public Presidente(int numero, String nome, String partido) {
        super(numero, nome, partido);
    }

    @Override
    public String getCargo() {
        return "Presidente";
    }
}