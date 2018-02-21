package br.edu.utfpr.gerenciador.web.gerenciadorweb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class teste {

	public static void main(String[] args) {
		String nome = LocalDate.now().toString().replaceAll("[^0-9]", "") + "_" + LocalTime.now().toString().replaceAll("[^0-9]", "");
		System.out.println(nome);
		System.out.println(LocalDateTime.now().toString().replaceAll("[^0-9]", ""));
	}
	
}
