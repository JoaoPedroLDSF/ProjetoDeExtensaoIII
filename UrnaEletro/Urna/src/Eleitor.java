public class Eleitor {

    private String nome; //Verifica o nome do eleitor
    private boolean votouVereador; // saber se votou no vereador ou presidente
    private boolean votouPresidente;// saber se votou no vereador ou presidente

    public Eleitor(String nome) {
        this.nome = nome;
        this.votouVereador = false;
        this.votouPresidente = false;
    }

    public String getNome() {
        return nome;
    }

    //verifica se já votou
    public boolean jaVotouVereador() {
        return votouVereador; 
    }
    
    public boolean jaVotouPresidente() {
        return votouPresidente;
    }

    //Registra os votos
    public void registrarVotoVereador() {
        this.votouVereador = true;
    }
    
    public void registrarVotoPresidente() {
        this.votouPresidente = true;
    }

    public boolean jaFinalizouVotacao() {
        return votouVereador && votouPresidente; //Verifica se votou no vereador e no presidente
    }
}