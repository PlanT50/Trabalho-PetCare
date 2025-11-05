import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Proprietario p1 = new Proprietario("Kevin",988135943,"Premium",false);
        Animal a1 = new Animal("Lua","Gato",2,"Saudável",p1);
        Consulta c1 = new Consulta("05/11","10:30",a1);
        c1.exibirConsulta(a1);



    }
}
