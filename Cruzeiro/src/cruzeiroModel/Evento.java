package cruzeiroModel;

import java.sql.Date;
import java.sql.Time;

public class Evento {

	private Integer id;
	private String nome;
	private String desc;
	private Date data;
	private Time hora;
	private String faixaEtaria;
	private double preco;
	
	public Evento() {
		
	}
	
	

	public Evento(String nome, String desc, Date data, Time hora, String faixaEtaria, double preco) {
		super();
		this.nome = nome;
		this.desc = desc;
		this.data = data;
		this.hora = hora;
		this.faixaEtaria = faixaEtaria;
		this.preco = preco;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	
}
