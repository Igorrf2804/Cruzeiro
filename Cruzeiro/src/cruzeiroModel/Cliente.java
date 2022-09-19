package cruzeiroModel;

public class Cliente {
	

	private Integer id, idPessoa;
	private int pacote;
	private String senha;
	private double total;
	private Pessoa pessoa;
	
	public Cliente() {

	}
	
	public Cliente(Integer idPessoa, int pacote, String senha, double total) {
		super();
		this.idPessoa = idPessoa;
		this.pacote = pacote;
		this.senha = senha;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer id_Pessoa) {
		this.idPessoa = id_Pessoa;
	}
	public int getPacote() {
		return pacote;
	}
	public void setPacote(int pacote) {
		this.pacote = pacote;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String toString() {
		if (pessoa!=null)
			return pessoa.getNome() + " - cpf:" + pessoa.getCpf() + " - idade:" + pessoa.getIdade();
		else
			return String.valueOf(getId());
	}

}
