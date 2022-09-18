/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruzeiroDao;

import cruzeiroEntidade.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author igor.ferreira1
 */
public class PessoaDaoImplTest {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    PessoaDao pessoaDao;
    Pessoa pessoa;
    
    public PessoaDaoImplTest() {
        pessoaDao = new PessoaDaoImpl();
    }

//    @Test
    public void testSalvarPessoa() throws Exception {
        System.out.println("salvarPessoa");
        pessoa = new Pessoa("João Pereira", "123.456.123-78", "teste@teste.com", "1111-1111", 18);
        pessoaDao.salvarPessoa(pessoa);
        assertNotNull(pessoa.getId());
    }

//    @Test
    public void testAlterarPessoa() throws Exception {
        System.out.println("alterarPessoa");
        buscarPessoaBD();
        pessoa.setNome("Tomas");
        pessoaDao.alterarPessoa(pessoa);
        Pessoa pessoaAlt = pessoaDao.pesquisarPorIdPessoa(pessoa.getId());
        assertEquals(pessoa.getNome(), pessoaAlt.getNome());

    }

//    @Test
    public void testExcluirPessoa() throws Exception {
        System.out.println("excluirPessoa");
        buscarPessoaBD();
        pessoaDao.excluirPessoa(pessoa.getId());
        Pessoa pessoaExc = pessoaDao.pesquisarPorIdPessoa(pessoa.getId());
        assertNull(pessoaExc);
    }

//    @Test
    public void testPesquisarPorIdPessoa() throws Exception {
        System.out.println("pesquisarPorIdPessoa");
        buscarPessoaBD();
        Pessoa pessoaPesquisada = pessoaDao.pesquisarPorIdPessoa(pessoa.getId());
        assertNotNull(pessoaPesquisada);
    }

//    @Test
    public void testPesquisarTodasPessoas() throws Exception {
        System.out.println("pesquisarTodasPessoas");
        buscarPessoaBD();
        
        List<Pessoa> pessoas = pessoaDao.pesquisarTodasPessoas();
        assertTrue(!pessoas.isEmpty());
    }

//    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        String nome = "jo";
        List<Pessoa> pessoas = pessoaDao.pesquisarPorNome(nome);
        assertTrue(!pessoas.isEmpty());
    }
    
    public Pessoa buscarPessoaBD() throws Exception {
        String sql = "select * from pessoas";
   
        conn = FabricaConexao.abrirConexao();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
            
        if (rs.next()) {
            pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setIdade(rs.getInt("idade"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setTelefone(rs.getString("telefone"));
        }
        else
            testSalvarPessoa();
        return pessoa;
    }
    
}
