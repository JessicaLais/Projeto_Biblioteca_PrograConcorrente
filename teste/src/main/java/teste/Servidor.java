package teste;

import java.io.*;
import java.net.*;

public class Servidor {
	public static Socket socket;
	public static BufferedReader LeitorBuffered;
	public static BufferedWriter EscritaBuffered;
	public static InputStreamReader Input;
	public static OutputStreamWriter Output;
	public static BancoDeDados BancoDados;

	public static void main(String[] args) throws Exception
	{
		BancoDados = new BancoDeDados(); 
		
		esperarConexao(new ServerSocket(9999));	
		Input = new InputStreamReader(socket.getInputStream()); 
		Output = new OutputStreamWriter(socket.getOutputStream()); 
		LeitorBuffered = new BufferedReader(Input); 
		EscritaBuffered = new BufferedWriter(Output); 

		while (true)
		{
			String clientInput = LeitorBuffered.readLine(); 

			if (clientInput == null) 
			{
				break; 
			}

			System.out.println("# " + clientInput);

			if (clientInput.startsWith("livros")) { livros(clientInput); }
			else if (clientInput.startsWith("alugar")) { alugar(clientInput); }
			else if (clientInput.startsWith("meus")) { meus(clientInput); }
			else if (clientInput.startsWith("retornar")) { retornar(clientInput); }
			else if (clientInput.startsWith("adicionar")) { adicionar(clientInput); }
			else if (clientInput.startsWith("remover")) { remover(clientInput); }
			else if (clientInput.startsWith("sair")) { break; }
			else { 
				EscritaBuffered.write("Não entendi");
				EscritaBuffered.newLine();
				EscritaBuffered.flush();
			}
		}
		fecharTudo();
	}

	public static void esperarConexao(ServerSocket serverSocket) throws IOException {
		System.out.println("Servidor pronto para ser conectado");
		socket = serverSocket.accept(); 
		System.out.println("O Cliente " + socket.getInetAddress() + " se conectou ao servidor");
	}

	public static void fecharTudo() throws IOException{
		socket.close();
		Input.close();
		Output.close();
		LeitorBuffered.close();
		EscritaBuffered.close();
	}

	public static void livros(String contexto) throws Exception {
		EscritaBuffered.write(BancoDados.getLivros(BancoDados.livros));
		EscritaBuffered.newLine();
		EscritaBuffered.flush();
	}

	public static void meus(String contexto) throws Exception {
		EscritaBuffered.write(BancoDados.getLivros(BancoDados.alugados));
		EscritaBuffered.newLine();
		EscritaBuffered.flush();
	}

	public static void alugar(String context) throws Exception {
		String nomeDoLivro  = context.substring(6).trim(); 
		try {
			BancoDados.alugarLivro(nomeDoLivro);
			EscritaBuffered.write("Alugado: " + nomeDoLivro);
			EscritaBuffered.newLine();
			EscritaBuffered.flush();
		} catch (Exception e) {
			e.printStackTrace();
			EscritaBuffered.write("Tente novamente");
			EscritaBuffered.newLine();
			EscritaBuffered.flush();
		}
	}
	
	public static void retornar(String context) throws Exception {
		String nomeDoLivro  = context.substring(8).trim();
		try {
			BancoDados.devolverLivro(nomeDoLivro);
			EscritaBuffered.write("Devolvido: " + nomeDoLivro);
			EscritaBuffered.newLine();
			EscritaBuffered.flush();
		} catch (Exception e) {
			e.printStackTrace();
			EscritaBuffered.write("Tente novamente");
			EscritaBuffered.newLine();
			EscritaBuffered.flush();
		}
	}
		
	public static void adicionar(String contexto) throws IOException {
		contexto = contexto.substring(9);
		String[] dados = contexto.split(";"); 
		Livro novoLivro = new Livro();
		try {
				novoLivro.nome = dados[0].trim();
				novoLivro.autor = dados[1].trim();
				novoLivro.genero = dados[2].trim();
				novoLivro.exemplares = Integer.parseInt(dados[3].trim());
			BancoDados.adicionarLivro(novoLivro);	
		} catch (Exception e) {
			e.printStackTrace();
			EscritaBuffered.write("Nem todos os dados do livro foram inseridos corretamente!");
			EscritaBuffered.newLine();
			EscritaBuffered.flush();
			return;
		}
		EscritaBuffered.write("Novo livro:%" + ConversorJson.jsonNodeParaString(ConversorJson.objetoParaJsonNode(novoLivro)).replace(System.lineSeparator(), "%"));
		EscritaBuffered.newLine();
		EscritaBuffered.flush();
	}

	public static void remover(String contexto) throws IOException {
		String nomeDoLivro = contexto.substring(7).trim();
		try {
			BancoDados.remover(nomeDoLivro);
			EscritaBuffered.write("Livro removido: " + nomeDoLivro);
			EscritaBuffered.newLine();
			EscritaBuffered.flush();
		} catch (Exception e) {
			e.printStackTrace();
			EscritaBuffered.write("Não consegui remover o livro: " + nomeDoLivro);
			EscritaBuffered.newLine();
			EscritaBuffered.flush();
			return;
		}
	}
}
