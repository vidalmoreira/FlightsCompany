package br.com.academy.Client;

import java.util.HashMap;

import br.com.academy.Flights.Flights;

public class Client {

	private String nome;
	private String cpf;
	private String pass;

	private HashMap<Integer, Flights> reservaIndividual = new HashMap<Integer, Flights>();

	public Client(String nome, String cpf, String pass) {
		this.nome = nome;
		this.cpf = cpf;
		this.pass = pass;
	}

	public Client() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public HashMap<Integer, Flights> getReservaIndividual() {
		return reservaIndividual;
	}

	public void setReservaIndividual(HashMap<Integer, Flights> reservaIndividual) {
		this.reservaIndividual = reservaIndividual;
	}
}
