package br.com.academy.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.academy.Client.Client;
import br.com.academy.Flights.Flights;

public class Painel {
	public static void main(String[] args) {
		new Painel().iniciarSistema();
	}

	public void iniciarSistema() {

		Scanner entrada = new Scanner(System.in);
		Map<String, Client> passageiros = new HashMap();
		Map<Integer, Flights> listasDeVoos = new HashMap();
//		Map<Integer, Flights> reservaPassagem = new HashMap();
		Client usuarioLogado = null;

//--------------------------------------------------------------------------------------------------
		// Metodos
		listasDeVoos = carregarVoos();

//---------------------------------------------------------------------------------------------------
		// Declarações
		int painel;
		int areaDoCliente;
		boolean iniciarAreaDoCliente = false;
		boolean painelPrincipal = true;
		boolean programaLigado = true;
		int reservation;
//----------------------------------------------------------------------------------------------------

		while (programaLigado) {

			while (painelPrincipal) {
				System.out.println(" ");
				System.out.println("Painel de vigagem");
				System.out.println("1.) Rotas");
				System.out.println("2.) Cadastro");
				System.out.println("3.) Login");
				System.out.println("4.) Sair.");
				System.out.println("\nInsira sua opção: ");
				painel = entrada.nextInt();

				switch (painel) {
				case 1:
					rotas(listasDeVoos);
					break;
				case 2:
					cadastro(entrada, passageiros);
					break;
				case 3:
					usuarioLogado = logIn(entrada, passageiros);
					if(usuarioLogado != null){
						iniciarAreaDoCliente = true;
					}
					break;
				//Fechar Programa
				case 4:
					System.out.println("Fechando o sistema em 3,2,1... ");
					return;

				}
//--------------------------------------------------------------------------------------------------			
				// Painel do usuário
				while (iniciarAreaDoCliente) {
					System.out.println(" ");
					System.out.println("Seja bem-vindo ao Painel do Usuário, " + usuarioLogado.getNome() + "!");
					System.out.println("");
					System.out.println("1.) Reserva");
					System.out.println("2.) Cancelamento");
					System.out.println("3.) LogOff");
					System.out.println("\nInsira sua opção: ");
					areaDoCliente = entrada.nextInt();

					switch (areaDoCliente) {
					case 1:

						System.out.println(" ");
						System.out.println("O melhor destino é o seu!");
						System.out.println(" ");
						System.out.println("0 = Voltar ao painel de usuário");
						listasDeVoos.entrySet().forEach(entry -> {
							System.out.println(entry.getKey() + " = " + entry.getValue());
						});

						System.out.println("\nInsira sua opção: ");
						reservation = entrada.nextInt();

						reserva(reservation, usuarioLogado, listasDeVoos);

					case 2:

						cancelaReserva(entrada, usuarioLogado);

					case 3:
						iniciarAreaDoCliente = false;
						painelPrincipal = true;
						break;

					}
				}
			}
		}
	}
	
	
	//Metodos
	
	private void cancelaReserva(Scanner entrada, Client usuarioLogado) {
		
		System.out.println(" ");
		System.out.println("Painel de cancelamento da Academy\n");
		System.out.println("Suas reservas são: ");

		usuarioLogado.getReservaIndividual().entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		});

		System.out.println(" ");
		System.out.println("0 = Voltar ao painel de usuário");

		System.out.println("\nInsira sua opção: ");
		int cancelamentoDeReserva = entrada.nextInt();
		
		usuarioLogado.getReservaIndividual().remove(cancelamentoDeReserva);
		System.out.println("Passagem cancelada");
		System.out.println(usuarioLogado.getReservaIndividual());

	}

	private void reserva(int reservation, Client usuarioLogado, Map<Integer, Flights> listasDeVoos) {
		
		Flights vooSelecionado = listasDeVoos.get(reservation);
		if(vooSelecionado == null) {
			System.out.println("Voo não existe!");
		}else {
		usuarioLogado.getReservaIndividual().put(reservation, vooSelecionado);
		}
		
	}

	private Client logIn(Scanner entrada, Map<String, Client> passageiros) {
		System.out.println("Digite seu CPF");
		String cpf = entrada.next();
		System.out.println("Digite sua senha");
		String pass = entrada.next();

		Client cliente = passageiros.get(cpf);
		
		if(cliente == null || !cliente.getPass().equals(pass)) {
			System.out.println("CPF ou senha incorreta.");
			return null;
		}else{
			return cliente;
			
		}
	}

	private Map cadastro(Scanner entrada, Map<String, Client> passageiros) {
		System.out.println(" ");
		System.out.println("Digite seu nome?");
		String nome = entrada.next();
		System.out.println("Digite seu CPF");
		String cpf = entrada.next();
		System.out.println("Digite sua senha");
		String pass = entrada.next();

		Client usuarioLogado = new Client(nome, cpf, pass);

		if (passageiros.containsKey(cpf)) {

			System.out.println("CPF já existente!");
		} else {

			passageiros.put(cpf, usuarioLogado);
			System.out.println("Usuário criado com sucesso!");
		}

		return passageiros;
	}

	private void rotas(Map<Integer, Flights> listasDeVoos) {
		System.out.println("Na Academy Flights oferecemos essas rotas: ");

		for (Integer chave : listasDeVoos.keySet()) {

			System.out.println(listasDeVoos.get(chave));
		}
		System.out.println(" ");
		System.out.println("Observação: É preciso ter cadastro para fazer reserva.");

	}

	private Map carregarVoos() {
		Map<Integer, Flights> listasDeVoos = new HashMap();

		Flights xSP = new Flights();
		Flights xRJ = new Flights();
		Flights xCuiaba = new Flights();

		xSP.setDestino("São Paulo");
		listasDeVoos.put(1, xSP);
		xRJ.setDestino("Rio de Janeiro");
		listasDeVoos.put(2, xRJ);
		xCuiaba.setDestino("Cuiabá");
		listasDeVoos.put(3, xCuiaba);
		return listasDeVoos;

	}
}
