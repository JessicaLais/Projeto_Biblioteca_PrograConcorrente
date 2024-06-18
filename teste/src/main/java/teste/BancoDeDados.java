package teste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class BancoDeDados {
    public static JsonNode livros;   
    public static JsonNode alugados;   

    public BancoDeDados() throws IOException
    {
        livros = gerarObjeto("livros.json");
        alugados = gerarObjeto("alugados.json");
    }

    private JsonNode gerarObjeto(String path) throws IOException
    {
        FileReader fr = new FileReader(path); //leitor do arquivo
        BufferedReader br = new BufferedReader(fr); //leitor em pedaços
        String arquivo = ""; //arquivo completo
        String linha = ""; //pedaços do arquivo
        while ((linha = br.readLine()) != null) 
        { 
            arquivo += linha; //escrevendo o arquivo
        }
        br.close(); //encerrando o leitor

        return ConversorJson.stringParaJsonNode(arquivo);
    }

    public static String getLivros(JsonNode json) throws JsonProcessingException, IllegalArgumentException{
        JsonNode listaDeLivros = json.get("livros"); //coletando livros
        return ConversorJson.jsonNodeParaString(listaDeLivros).replace(System.lineSeparator(), "%"); //deixando para quebrar linhas depois
    }

    public static Livro[] getLivrosList(JsonNode json) throws JsonProcessingException, IllegalArgumentException {
        Livro[] listaDeLivros = new Livro[json.get("livros").size()];

        int c = 0;
        for (JsonNode book : json.get("livros")) {
            listaDeLivros[c] = ConversorJson.jsonNodeParaObjeto(book, Livro.class);
            c += 1;
        }
        return listaDeLivros;
    }

    public static boolean contemLivro(Livro[] listaDeLivros, String nome) {
        for (Livro livro : listaDeLivros) {
            if (livro.nome.equalsIgnoreCase(nome)) return true;
        }
        return false;
    }

    public static Livro pegarLivroDaLista(Livro[] listaDeLivros, String nome) {
        for (Livro livro : listaDeLivros) {
            if(livro.nome.equalsIgnoreCase(nome)) return livro;
        }
        return null;
    }
}