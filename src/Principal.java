import java.util.Scanner;
import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;

public class Principal {
static Scanner scan = new Scanner(System.in);
static final int TAM = 100;
static Usuario[] perfis = new Usuario[TAM]; 
static int quantPerfis = 0;
static int posicaoLogin = 0;
static int[] quantidadePost = new int [100];


	public static void main(String[] args) {
		telaInicial();
	}
	
	private static void telaInicial() {
		titulo("BEM VINDO A ESTA BELÍSSIMA REDE SOCIAL!");
		System.out.println("Digite o número da opção desejada:");
		System.out.println("1: Cadastro");
		System.out.println("2: login");
		System.out.println("3: Fechar");
		int primeiraTela = pegaNumInt();
		
		while(primeiraTela < 1 || primeiraTela > 3) {
			System.out.println("Opção não encontrada!");
			System.out.println("Digite o número da opção desejada:");
			System.out.println("1: Cadastro");
			System.out.println("2: login");
			System.out.println("3: Fechar");
			primeiraTela = pegaNumInt();
		}
		
		if (primeiraTela == 1) {
			titulo("CADASTRO DE NOVO USUÁRIO");
			cadastrarUsuario();
			telaInicial();
			
			
		} else if(primeiraTela == 2) {
			try {
			telaLogin();
			} catch(UserNotFoundException e) {
				System.out.println("usuário não encontrado");
				telaInicial();
			}
						
		} else {
			System.out.println("Até logo!");
		}
	}

	private static void cadastrarUsuario() {
		Usuario a1 = new Usuario();
		System.out.println("Nome completo: ");
		a1.nome = scan.nextLine();
		if (a1.nome.equals("")) {
			System.out.println("Erro! o nome não pode ficar em branco");
			telaInicial();
		}

		System.out.println("Login: ");
		a1.login = scan.nextLine().toUpperCase();
		if(quantPerfis > 0) {
			for(int i = 0; i < quantPerfis; i++) {
					if (a1.login.equals(perfis[i].login)) {
						System.out.println("login já cadastrado! Faça login ou cadastre com outro nome.");
						telaInicial();
					}
			}
		} 
		
		if (a1.login.equals("")) {
			System.out.println("Erro! o login não pode ficar em branco");
			telaInicial();
		}

		System.out.println("Senha: ");
		a1.senha = scan.nextLine();
		if (a1.senha.equals("")) {
			System.out.println("Erro! a senha não pode ficar em branco");
			telaInicial();
		}
		
		perfis[quantPerfis] = a1;
		quantPerfis++;
		System.out.println("USUÁRIO CADASTRADO COM SUCESSO!");
		System.out.println();
	}
	
	private static void telaLogin() throws UserNotFoundException, InvalidPasswordException {
		titulo("LOGIN DE USUÁRIO CADASTRADO");
		try {
			posicaoLogin = verificaLogin();
		}
		catch (UserNotFoundException e) {
			System.out.println("usuário não encontrado");
			telaInicial();
		}
		try {
			verificaSenha();
		} catch (InvalidPasswordException e) {
		System.out.println("Senha não encontrada!");
		telaInicial();
		}
		telaPosLogin();
	}	
	
	static int verificaLogin() {
		System.out.println("Login: ");
		String login = scan.nextLine().toUpperCase();
		boolean verificador = false;
		int posicao = 0;
		if (quantPerfis != 0) {
			for (int i = 0 ; i < quantPerfis; i++ ) {
					if (perfis[i].login.equals(login)) {
						verificador = true;
						posicao = i;
					}
			}
		}
			if(verificador == false) {
				throw new UserNotFoundException();
			} else {
				return posicao;
			}
		}
	
	public static void verificaSenha() {
		System.out.println("Senha: ");
		String senha = scan.nextLine();
		if(perfis[posicaoLogin].senha.equals(senha)) {
			System.out.println("Login realizado com sucesso!");
		} else {
			throw new InvalidPasswordException();
		}
	}

	private static void telaPosLogin() {
		titulo("BEM VINDO AO SEU PERFIL, " + perfis[posicaoLogin].nome.toUpperCase() + "!");
		System.out.println();
		System.out.println("Digite o número da opção desejada:");
		System.out.println("1 - NOVA POSTAGEM");
		System.out.println("2 - VER MINHA TIMELINE");
		System.out.println("3 - SAIR");
		int opcao2 = 0;
		while(opcao2 < 1 || opcao2 > 3) {
			opcao2 = pegaNumInt();
		}
		if (opcao2 == 1) {
			perfis[posicaoLogin].postagens[quantidadePost[posicaoLogin]] = Usuario.novoPost();
			quantidadePost[posicaoLogin]++ ;
			telaPosLogin();
		} else if (opcao2 == 2) {
			Usuario.verPosts(quantidadePost[posicaoLogin], perfis[posicaoLogin]);
			telaPosLogin();
		} else {
			System.out.println("Até breve!");
			telaInicial();
		}
	}


		
	public static void titulo(String mensagem) {
		String str = "~";
		System.out.println(str.repeat(mensagem.length()));
		System.out.println(mensagem);
		System.out.println(str.repeat(mensagem.length()));
		System.out.println();
		System.out.println();
	}
	
	static int pegaNumInt() {
        Scanner scan2 = new Scanner(System.in);
        
        while (!scan2.hasNextInt()) {
            System.out.println("Valor inválido. Por favor, digite um número inteiro");
            scan2.nextLine();
        }
        return scan2.nextInt();
    }
	
}
