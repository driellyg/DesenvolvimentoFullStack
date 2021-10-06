package br.ufg.inf.fs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.ufg.inf.fs.entities.Hotel;
import br.ufg.inf.fs.entities.Quarto;
import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.entities.Hospedagem;
import br.ufg.inf.fs.enums.CategoriaQuarto;
import br.ufg.inf.fs.repositories.HotelRepository;
import br.ufg.inf.fs.repositories.QuartoRepository;
import br.ufg.inf.fs.repositories.HospedeRepository;
import br.ufg.inf.fs.repositories.HospedagemRepository;

@Configuration
@Profile("dev")
public class Config implements CommandLineRunner {

	@Autowired
	private HotelRepository hoteRepository;

	@Autowired
	private QuartoRepository quartoRepository;

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private HospedagemRepository hospedagemRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		/*
		 * INSERIR NO MEU BANCO DE DADOS INFORMAÇÕES INICIAIS...
		 */

		Hotel h1 = new Hotel(null, "Copacabana Palace", "Rio de Janeiro", 5);
		Hotel h2 = new Hotel(null, "Oitis Hotel", "Goiania", 3);
		Hotel h3 = new Hotel(null, "Beiramar Hotel", "Rio de Janeiro", 4);
		Hotel h4 = new Hotel(null, "Bourbon Hotel", "Sao Paulo", 5);

		h1 = hoteRepository.save(h1);
		h2 = hoteRepository.save(h2);
		h3 = hoteRepository.save(h3);
		h4 = hoteRepository.save(h4);

		Quarto q1 = quartoRepository.save(new Quarto(null, h1, CategoriaQuarto.APARTAMENTO, 3, 1010, 350.0));
		Quarto q2 = quartoRepository.save(new Quarto(null, h2, CategoriaQuarto.SIMPLES, 1, 7, 150.0));
		Quarto q3 = quartoRepository.save(new Quarto(null, h3, CategoriaQuarto.PADRAO, 2, 306, 200.0));
		Quarto q4 = quartoRepository.save(new Quarto(null, h4, CategoriaQuarto.LUXO, 4, 1313, 650.0));

		q1 = quartoRepository.save(q1);
		q2 = quartoRepository.save(q2);
		q3 = quartoRepository.save(q3);
		q4 = quartoRepository.save(q4);

		Hospede hos1 = new Hospede(null, "Claudia", new SimpleDateFormat("dd-MM-yyyy").parse("14-08-1995"), 701765432);
		Hospede hos2 = new Hospede(null, "Isabel", new SimpleDateFormat("dd-MM-yyyy").parse("03-01-1999"), 702786743);
		Hospede hos3 = new Hospede(null, "Thomas", new SimpleDateFormat("dd-MM-yyyy").parse("22-09-1997"), 703214321);
		Hospede hos4 = new Hospede(null, "Marcelo", new SimpleDateFormat("dd-MM-yyyy").parse("06-11-1999"), 704561032);

		hos1 = hospedeRepository.save(hos1);
		hos2 = hospedeRepository.save(hos2);
		hos3 = hospedeRepository.save(hos3);
		hos4 = hospedeRepository.save(hos4);

		Hospedagem hosp1 = new Hospedagem(null, q1.getIdQuarto(), hos1.getIdHospede(), new SimpleDateFormat("dd-MM-yyyy").parse("01-10-2021"),
				new SimpleDateFormat("dd-MM-yyyy").parse("07-10-2021"));
		Hospedagem hosp2 = new Hospedagem(null, q2.getIdQuarto(), hos2.getIdHospede(), new SimpleDateFormat("dd-MM-yyyy").parse("01-02-2022"),
				new SimpleDateFormat("dd-MM-yyyy").parse("18-02-2022"));
		Hospedagem hosp3 = new Hospedagem(null, q3.getIdQuarto(), hos3.getIdHospede(), new SimpleDateFormat("dd-MM-yyyy").parse("01-12-2021"),
				new SimpleDateFormat("dd-MM-yyyy").parse("15-12-2021"));
		Hospedagem hosp4 = new Hospedagem(null, q4.getIdQuarto(), hos4.getIdHospede(), new SimpleDateFormat("dd-MM-yyyy").parse("15-06-2021"),
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2021"));

		hosp1 = hospedagemRepository.save(hosp1);
		hosp2 = hospedagemRepository.save(hosp2);
		hosp3 = hospedagemRepository.save(hosp3);
		hosp4 = hospedagemRepository.save(hosp4);

		System.out.println("Deletando dados...");
		quartoRepository.delete(q4);
		hoteRepository.delete(h4);
		hospedeRepository.delete(hos4);
		hospedagemRepository.delete(hosp4);
		System.out.println("Dados deletados.");

		System.out.println("Hotéis cadastrados: ");
		for (Hotel h : hoteRepository.findAll()) {
			System.out.println(h);
		}

		System.out.println("Quartos cadastrados: ");
		for (Quarto q : quartoRepository.findAll()) {
			System.out.println(q);
		}

		System.out.println("Hospedes cadastrados: ");
		for (Hospede h : hospedeRepository.findAll()) {
			System.out.println(h);
		}

		System.out.println("Hospedagens cadastrados: ");
		for (Hospedagem h : hospedagemRepository.findAll()) {
			System.out.println(h);
		}
	}

}
