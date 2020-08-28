package provaprog2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ControleDeEstoque {

    /**
     * *
     * 1 - salvar em estoque.dados 2 - listar array de registros 3 - ordenar por
     * id, nome ou quantidade 4 - excluir um registro
     *
     **
     */
    protected RandomAccessFile arquivo;
    private long TAMANHO_ID = 10;
    private long TAMANHO_NOME = 100;
    private long TAMANHO_LOCAL = 50;
    private long TAMANHO_QUANTIDADE = 40;
    private long TAMANHO_REGISTRO = (TAMANHO_ID + TAMANHO_NOME + TAMANHO_LOCAL + TAMANHO_QUANTIDADE) * 2;

    public ControleDeEstoque() {
        try {
            arquivo = new RandomAccessFile("estoque.dados", "rw");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void finalizar() {
        try {
            arquivo.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void salvar(Registro r, long nRegistro) {
        try {
            arquivo.seek(TAMANHO_REGISTRO * nRegistro);
        } catch (IOException e) {
            System.out.println(e);
        }
        gravar(r);
    }

    private void gravar(Registro r) {
        try {
            String id = Integer.toString(r.getID());
            String nome = r.getNomeProduto();
            String local = r.getLocal();
            String quantidade = Float.toString(r.getQuantidade());
            int i = 0;
            for (; i < (int) TAMANHO_ID && i < id.length(); i++) {
                arquivo.writeChar(id.charAt(i));
            }
            for (; i < 10; i++) {
                arquivo.writeChar('\u0000');
            }

            i = 0;
            for (; i < (int) TAMANHO_NOME && i < nome.length(); i++) {
                arquivo.writeChar(nome.charAt(i));
            }
            for (; i < (int) TAMANHO_NOME; i++) {
                arquivo.writeChar('\u0000');
            }

            i = 0;
            for (; i < (int) TAMANHO_LOCAL && i < local.length(); i++) {
                arquivo.writeChar(local.charAt(i));
            }
            for (; i < (int) TAMANHO_LOCAL; i++) {
                arquivo.writeChar('\u0000');
            }
            i = 0;
            for (; i < (int) TAMANHO_QUANTIDADE && i < quantidade.length(); i++) {
                arquivo.writeChar(quantidade.charAt(i));
            }
            for (; i < (int) TAMANHO_QUANTIDADE; i++) {
                arquivo.writeChar('\u0000');
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void adicionar(Registro r) {
        try {
            if (arquivo.length() > 0) {
                arquivo.seek(arquivo.length());
            }
            gravar(r);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Registro ler(long nRegistro) {
        Registro registro = null;
        try {
            arquivo.seek(nRegistro * TAMANHO_REGISTRO);
            String id = "";
            String nome = "";
            String local = "";
            String quantidade = "";

            for (int i = 0; i < TAMANHO_ID; i++) {
                id += arquivo.readChar();
            }

            for (int i = 0; i < TAMANHO_NOME; i++) {
                nome += arquivo.readChar();
            }

            for (int i = 0; i < TAMANHO_LOCAL; i++) {
                local += arquivo.readChar();
            }

            for (int i = 0; i < TAMANHO_QUANTIDADE; i++) {
                quantidade += arquivo.readChar();
            }

            int idd = Integer.parseInt(id.trim());
            registro = new Registro(idd, nome, local, Float.parseFloat(quantidade));
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return registro;
    }

    public long quantidadeRegistros() {
        long tamanho = -1;
        try {
            tamanho = arquivo.length() / (TAMANHO_REGISTRO);
        } catch (Exception e) {
            System.out.println(e);
        }
        return tamanho;
    }

    public Registro[] listar() {
        Registro[] lista;
        lista = new Registro[(int) quantidadeRegistros()];
        for (int i = 0; i < quantidadeRegistros(); i++) {
            lista[i] = (ler(i));
            System.out.println(ler(i));
        }
        return lista;
    }

    private Registro[] ArrayLista() {
        Registro[] lista;
        lista = new Registro[(int) quantidadeRegistros()];
        for (int i = 0; i < quantidadeRegistros(); i++) {
            lista[i] = (ler(i));
        }
        return lista;
    }

    private void sortByID(Registro[] lista) {
        boolean troca = true;
        Registro aux;
        while (troca) {
            troca = false;
            for (int i = 0; i < lista.length - 1; i++) {
                if (lista[i].getID() > lista[i + 1].getID()) {
                    aux = lista[i];
                    lista[i] = lista[i + 1];
                    lista[i + 1] = aux;
                    troca = true;
                }
            }
        }
        for (int i = 0; i < quantidadeRegistros(); i++) {
            System.out.println(lista[i].toString());
        }
    }

    private void sortByQuantidade(Registro[] lista) {
        boolean troca = true;
        Registro aux;
        while (troca) {
            troca = false;
            for (int i = 0; i < lista.length - 1; i++) {
                if (lista[i].getQuantidade() > lista[i + 1].getQuantidade()) {
                    aux = lista[i];
                    lista[i] = lista[i + 1];
                    lista[i + 1] = aux;
                    troca = true;
                }
            }
        }
        for (int i = 0; i < quantidadeRegistros(); i++) {
            System.out.println(lista[i].toString());
        }
    }

    private void sortByNomeProduto(Registro[] lista) {
        boolean troca = true;
        Registro aux;
        while (troca) {
            troca = false;
            for (int j = 0; j < lista.length; j++) {
                for (int i = j + 1; i < lista.length; i++) {
                    if (lista[i].getNomeProduto().compareTo(lista[j].getNomeProduto()) < 0) {
                        aux = lista[j];
                        lista[j] = lista[i];
                        lista[i] = aux;
                        troca = true;
                    }
                }
            }
        }
        for (int i = 0; i < quantidadeRegistros(); i++) {
            System.out.println(lista[i].toString());
        }
    }

    public void ordenar(int valor) {
        switch (valor) {
            case 1:
                sortByID(ArrayLista());
                break;
            case 2:
                sortByNomeProduto(ArrayLista());
                break;
            case 3:
                sortByQuantidade(ArrayLista());
                break;
            default:
                System.out.println("Ordenação inválida");
                break;
        }
    }

    /**
     * *
     * Retorna -1 quando não encontra registros com o id
     */
    private int getPosicaoByID(int id) {
        int pos = -1;
        for (int i = 0; i < quantidadeRegistros(); i++) {
            boolean egual = ler(i).getID() == id;
            if (egual) {
                return i;
            }
        }
        return pos;
    }

//    public void excluir(int id) throws IOException {
//        long posicao = getPosicaoByID(id);
//        long ler = TAMANHO_REGISTRO * (posicao + 1);
//        long escrever = TAMANHO_REGISTRO * posicao;
//        int tamanho = (int) ((int) TAMANHO_REGISTRO * quantidadeRegistros() - (int) TAMANHO_REGISTRO);
//
//        byte[] bytes = new byte[tamanho];
//
//        if (getPosicaoByID(id) == ((int) quantidadeRegistros() - 1)) {
//            arquivo.getChannel().truncate(escrever);
//        } else if (getPosicaoByID(id) > 0 && getPosicaoByID(id) < ((int) quantidadeRegistros() - 1)) {
//            arquivo.seek(ler);
//            arquivo.read(bytes);
//            arquivo.seek(escrever);
//            arquivo.write(bytes);
//            arquivo.getChannel().truncate(tamanho);
//        } else {
//            System.out.println("Índice inválido");
//        }
//    }

    public void excluir(int id) throws IOException {
        long posicao = getPosicaoByID(id);
        long tamanho = TAMANHO_REGISTRO * quantidadeRegistros() - TAMANHO_REGISTRO;
        boolean truncar = posicao > 0 && posicao < ((int) quantidadeRegistros());

        if (truncar) {
            if (posicao != ((int) quantidadeRegistros() - 1)) {
                for (int i = (int) posicao + 1; i < quantidadeRegistros(); i++) {
                    salvar(ler(i), i - 1);
                }
            }
            arquivo.getChannel().truncate(tamanho);
        } else {
            System.out.println("Índice inválido");
        }
    }

    public void limparTudo() throws IOException {
        arquivo.setLength(0);
    }

}
