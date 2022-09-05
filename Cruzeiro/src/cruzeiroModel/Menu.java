package cruzeiroModel;

public class Menu {
	
	private Integer id;
	private int tipo;
	private String nome;
	private String desc;
	private String ingredientes;
	private double preco;
	
	public Menu() {
		
	}	

	public Menu(int tipo, String nome, String desc, String ingredientes, double preco) {
		super();
		this.tipo = tipo;
		this.nome = nome;
		this.desc = desc;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
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

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
