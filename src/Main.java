import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Proprietario proprietario = new Proprietario("Kevin",988135943,"Premium",false);
        Animal a1 = new Animal("Gato",2,"Saldável",proprietario);
        proprietario.addAnimal(a1);
        proprietario.exibirAnimals();
        Consulta c1 = new Consulta("05/11","10:30","Kevin",a1);



    }
}
