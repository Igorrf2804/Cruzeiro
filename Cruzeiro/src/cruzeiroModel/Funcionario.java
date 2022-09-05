package cruzeiroModel;

public class Funcionario {


	private Integer id, idPessoa;
	private String endereco;
	private String senha;
	
	public Funcionario() {
		
	}

	public Funcionario(Integer idPessoa, String endereco, String senha) {
		super();
		this.idPessoa = idPessoa;
		this.endereco = endereco;
		this.senha = senha;
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

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
