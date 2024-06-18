import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
	static Socket socket = null;
	static InputStreamReader Input = null;
	static OutputStreamWriter Output = null;
	static BufferedReader LeitorBuffered = null;
	static BufferedWriter EscritaBuffered = null;

	public static void main(String[] args) throws IOException {
		socket = new Socket("localhost", 9999);
		System.out.println("Conectado com: " + socket.getInetAddress());
		Input = new InputStreamReader(socket.getInputStream());
		LeitorBuffered = new BufferedReader(Input);
		Output = new OutputStreamWriter(socket.getOutputStream());
		EscritaBuffered = new BufferedWriter(Output);
        Scanner scanner = new Scanner(System.in, "UTF-8");

		while (true) {
			System.out.print("-> ");
			String mensagem = scanner.nextLine();
			EscritaBuffered.write(mensagem);
			EscritaBuffered.newLine();
			EscritaBuffered.flush();
			System.out.println(mensagem);
			System.out.println("\n" + quebrarLinhas(LeitorBuffered.readLine()) + "\n");
			if (mensagem.startsWith("sair")) {
				break; 
			}
		}
		fecharTudo();
        scanner.close();
	}
	
	public static void fecharTudo() throws IOException{
		if (socket != null) { socket.close(); }			
		if (Input != null) { Input.close(); }
		if (Output != null) { Output.close(); }
		if (LeitorBuffered != null) { LeitorBuffered.close(); }
		if (EscritaBuffered != null) { EscritaBuffered.close(); }
	}
	public static String quebrarLinhas(String texto) {
		try {
			return texto.replace("%", System.lineSeparator());
		} catch (Exception e) {return texto;}
	}
}
