import java.util.ArrayList;

public class Proprietario {
    private String nome;
    private String contato;
    private String assinatura;
    private boolean vip;
    private ArrayList<Animal> animais;

    public Proprietario(String nome, String contato, String assinatura, boolean vip) {
        if (!contato.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Contato deve ter 10 ou 11 dígitos");
        }
        this.nome = nome;
        this.contato = contato;
        this.assinatura = assinatura;
        this.vip = vip;
        this.animais = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        if (!contato.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Contato deve ter 10 ou 11 dígitos");
        }
        this.contato = contato;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public void addAnimal(Animal animal) {
        animais.add(animal);
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void exibirAnimais() {
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            System.out.println("\n===== ANIMAIS DE " + nome.toUpperCase() + " =====");
            System.out.println("Status: " + (vip ? "VIP" : "Regular"));
            System.out.println("Plano: " + assinatura);
            for (Animal animal : animais) {
                System.out.println("------------------------------");
                System.out.println("Nome: " + animal.getNome());
                System.out.println("Espécie: " + animal.getEspecie());
                System.out.println("Idade: " + animal.getIdade() + " ano(s)");
            }
        }
    }

    public String getContatoFormatado() {
        if (contato.length() == 11) {
            return String.format("(%s) %s-%s",
                    contato.substring(0, 2),
                    contato.substring(2, 7),
                    contato.substring(7));
        } else {
            return String.format("(%s) %s-%s",
                    contato.substring(0, 2),
                    contato.substring(2, 6),
                    contato.substring(6));
        }
    }
}