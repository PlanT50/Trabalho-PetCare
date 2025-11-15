import java.util.ArrayList;

public class Proprietario  {
    private String nome;
    private int contato;
    private String assinatura;
    private boolean Vip;
    private ArrayList<Animal> animais = new ArrayList<>();

    public Proprietario(String nome, int contato, String assinatura, boolean Vip) {
        this.nome = nome;
        this.contato = contato;
        this.assinatura = assinatura;
        this.Vip = Vip;
        animais = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getcontato(){
        return contato;
    }

    public void setcontato(int contato){
        this.contato = contato;
    }

    public String getassinatura(){
        return assinatura;
    }

    public void setassinatura(String assinatura){
        this.assinatura = assinatura;
    }

    public boolean getVip(){
        return Vip;
    }

    public void setVip(boolean Vip){
        this.Vip = Vip;
    }

    public void addAnimal(Animal animal){
        animais.add(animal);
    }

    public ArrayList<Animal> getAnimais(){
        return animais;
    }

    public void exibirAnimals(){
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal encontrado");
        }
        else {
            for (Animal animal : animais) {
                System.out.println(animal.getHistorico());
                System.out.println(animal.getIdade());
                System.out.println(animal.getProprietario());
            }
        }
    }


}
