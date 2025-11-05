import java.util.ArrayList;

public class Proprietario {
    private int contato;
    private String assinatura;
    private boolean Vip;
    private ArrayList<Animal> animais;

    public Proprietario(int contato, String assinatura, boolean Vip) {
        this.contato = contato;
        this.assinatura = assinatura;
        this.Vip = Vip;
        animais = new ArrayList<>();
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


}
