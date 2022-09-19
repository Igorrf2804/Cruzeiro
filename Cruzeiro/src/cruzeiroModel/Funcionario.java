package cruzeiroModel;

public class Funcionario {


	private Integer id, idPessoa;
	private String endereco;
	private String senha;
	private String login;
	private Pessoa pessoa;
	
	public Funcionario() {
		
	}

	public Funcionario(Integer idPessoa, String endereco, String senha, String login) {
		super();
		this.idPessoa = idPessoa;
		this.endereco = endereco;
		this.senha = senha;
		this.login = login;
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
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
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
