public class Vereador extends Politico {

    public Vereador(int numero, String nome, String partido) {
        super(numero, nome, partido);
    }

    @Override
    public String getCargo() {
        return "Vereador";
    }
}