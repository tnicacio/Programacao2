package provaprog2;

public class Registro {

    private int id;
    private String nomeProduto;
    private String local;
    private float quantidade;
    

    public Registro() {
    }

    public Registro(int id,String nomeProduto, String local, float quantidade) {

        try {
            if (nomeProduto.length() > 100 || local.length() > 50) {
                System.out.println("Ultrapassou o limite de caracteres");

            } else {
//                int idNovo = id++;
                this.id = id;
                this.nomeProduto = nomeProduto;
                this.local = local;
                this.quantidade = quantidade;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public void setLocal(String local){
        this.local = local;
    }
    
    public void setQuantidade(float quantidade){
        this.quantidade = quantidade;
    }
    
    public String getNomeProduto(){
        return nomeProduto;
    }
    
    public String getLocal(){
        return local;
    }
    
    public float getQuantidade(){
        return quantidade;
    }
    
    public int getID(){
        return id;
    }
    
    @Override
    public String toString(){
        return "{ID: " + id + " ;Produto: " + nomeProduto + " ;Local: " + local + " Qtd: " + quantidade + "}";
    }
    
}
