package provaprog2;

import java.io.IOException;
import java.util.Arrays;

public class Provaprog2 {

    public static void main(String[] args) throws IOException {
        ControleDeEstoque estoque = new ControleDeEstoque();
//        for (int i = 0 ; i < 5; i++){
//            Registro registro = new Registro(i, "nome "+ (1+i), "Bairro "+ (int)(Math.random() * 10), i*2 + 3);
//    estoque.adicionar(registro);
//        }
//        
//        estoque.listar();
        

        Registro registro = new Registro(4,"Jurema","Mercearia",3000);
        estoque.adicionar(registro);
        registro = new Registro(2,"Piracicaba","Frutas",20000);
        estoque.adicionar(registro);
        registro = new Registro(1,"Biribinhas","Pet Shop",1000);
        estoque.adicionar(registro);
        registro = new Registro(3,"Mimosa","Açougue",50000);
        estoque.adicionar(registro);
        
        estoque.listar();
        
        
        System.out.println("--teste ordenação--\n");
        
        System.out.println("Por id");
        estoque.ordenar(1);
        
        System.out.println("Por nome");
        estoque.ordenar(2);
        
        System.out.println("Por quantidade");
        estoque.ordenar(3);

        estoque.limparTudo();
        
    }
    
}
