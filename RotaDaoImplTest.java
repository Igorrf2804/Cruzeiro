/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruzeiroDao;

import cruzeiroEntidade.Rota;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author igor.ferreira1
 */
public class RotaDaoImplTest {
    
    RotaDao rotaDao;
    Rota rota;
    
    public RotaDaoImplTest() {
        rotaDao = new RotaDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        rota = Rota();
    }

    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        Rota rota = null;
        RotaDaoImpl instance = new RotaDaoImpl();
        instance.alterar(rota);
        fail("The test case is a prototype.");
    }

    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        Integer id = null;
        RotaDaoImpl instance = new RotaDaoImpl();
        instance.excluir(id);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisarPorId() throws Exception {
        System.out.println("pesquisarPorId");
        Integer id = null;
        RotaDaoImpl instance = new RotaDaoImpl();
        Rota expResult = null;
        Rota result = instance.pesquisarPorId(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRegistrarPedido() throws Exception {
        System.out.println("registrarPedido");
        Integer idCliente = null;
        Integer idRota = null;
        RotaDaoImpl instance = new RotaDaoImpl();
        instance.registrarPedido(idCliente, idRota);
        fail("The test case is a prototype.");
    }
    
}
