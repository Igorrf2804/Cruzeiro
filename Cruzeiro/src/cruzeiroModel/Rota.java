package cruzeiroModel;

import java.sql.Date;
import java.sql.Time;

public class Rota {

	private Integer id, idFuncionario;
	private String local, rota;
	private Date dataSaida, dataVolta;
	private Time horaSaida, horaVolta;
	private double preco;
	
	public Rota() {
		
	}

	public Rota(Integer idFuncionario, String local, String rota, Date dataSaida, Date dataVolta,
			Time horaSaida, Time horaVolta, double preco) {
		super();
		this.idFuncionario = idFuncionario;
		this.local = local;
		this.rota = rota;
		this.dataSaida = dataSaida;
		this.dataVolta = dataVolta;
		this.horaSaida = horaSaida;
		this.horaVolta = horaVolta;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataVolta() {
		return dataVolta;
	}

	public void setDataVolta(Date dataVolta) {
		this.dataVolta = dataVolta;
	}

	public Time getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Time horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Time getHoraVolta() {
		return horaVolta;
	}

	public void setHoraVolta(Time horaVolta) {
		this.horaVolta = horaVolta;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
