import java.util.Scanner;

public class Usuario {
	static Scanner scaner = new Scanner(System.in);
	String nome;
	String login;
	String senha;
	Post[] postagens = new Post[100];
	
	static Post novoPost() {
		Post postagem = new Post();
		System.out.println("Digite a data de hoje: (dd/mm/aaaa)");
		postagem.data = scaner.nextLine();
		System.out.println("Digite que horas são: (hh:mm)");
		postagem.hora = scaner.nextLine();
		System.out.println("Escreva abaixo sua postagem: ");
		postagem.texto = scaner.nextLine();
		System.out.println("Postagem realizada com sucesso!");
		return postagem;
	}
	
	static void verPosts(int quantPostagem, Usuario perfil) {
		for (int i = 0; i < quantPostagem; i++) {
			System.out.println("Data: " + perfil.postagens[i].data + "   Hora: " + perfil.postagens[i].hora);
			System.out.println("Postagem: " + perfil.postagens[i].texto);
			System.out.println();
		}
		
	}
	

}

