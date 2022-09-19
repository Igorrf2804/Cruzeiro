package cruzeiroModel;

import java.time.LocalTime;
import java.util.Date;


public class Rota {

	private Integer id, idFuncionario;
	private String local, rota;
	private Date dataSaida, dataVolta;
	private LocalTime horaSaida, horaVolta;
	private double preco;
	
	public Rota() {
		
	}

	public Rota(Integer idFuncionario, String local, String rota, Date dataSaida, Date dataVolta,
			LocalTime horaSaida, LocalTime horaVolta, double preco) {
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

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public LocalTime getHoraVolta() {
		return horaVolta;
	}

	public void setHoraVolta(LocalTime horaVolta) {
		this.horaVolta = horaVolta;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String toString() {
		return "Local de destino: " + getLocal() + " - rota: " + getRota();  
	}
}
