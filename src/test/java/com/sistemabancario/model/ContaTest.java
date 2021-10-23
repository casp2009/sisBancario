package com.sistemabancario.model;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {
    
    @Test
    void testSetNumeroValido(){
        final Conta instance = new Conta();
        final String esperado = "12345-6";
        instance.setNumero(esperado);
        final String obtido = instance.getNumero();
        assertEquals(esperado, obtido);
    }

    /*@Test
    void testeSetNumeroInvalidoExcecao(){
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido) );
    }*/

    @Test
    void testeSetNumeroInvalidoNaoArmazena(){
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido) );
        final String obtido = instance.getNumero();
        assertNotEquals(invalido, obtido);
    }

    @Test
    void testInstanciaPadraoPoupanca(){
        final Conta instance = new Conta();
        assertFalse(instance.isPoupanca());
    }

    @Test
    void testSetLimiteContaEspecial(){
        final Conta instance = new Conta();
        instance.setEspecial(true);
        final double esperado = 1000;
        instance.setLimite(esperado);
        final double obtido = instance.getLimite();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testSetLimiteContaNaoEspecial(){
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));
    }

    @Test
    void testHistoricoNotNull(){
        final Conta instance = new Conta();
        assertNotNull(instance.getMovimentacoes());
    }

    @Test
    void testGetSaldoTotal(){
        final double limite = 500;
        final double esperado = limite;
        final Conta instanse = new Conta();
        instanse.setEspecial(true);
        instanse.setLimite(limite);
        final double obtido = instanse.getSaldoTotal();
        assertEquals(esperado, obtido);
    }

    @Test
    void testDepositoDinheiro(){
        final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);

        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.0001);
    }
    
    @Test
    void testTipoMovCredito(){
        final Conta instance = new Conta();
        instance.depositoDinheiro(500);
        final char esperado = 'C';
        final char obtido = instance.getMovimentacoes().get(instance.getMovimentacoes().size()-1).getTipo();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testTipoMovConfirmada(){
        final Conta instance = new Conta();
        instance.depositoDinheiro(500);
        final boolean esperado = true;
        final boolean obtido = instance.getMovimentacoes().get(instance.getMovimentacoes().size()-1).isConfirmada();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testValorAtrMov(){
        final Conta instance = new Conta();
        instance.depositoDinheiro(500);
        final double esperado = 0;
        final double obtido = instance.getMovimentacoes().get(instance.getMovimentacoes().size()-1).getValor();
        assertNotEquals(obtido, esperado);
    } 
    
    //se a movimentação não fosse adicionada eu não conseguiria realizar os testes anteriores
    
     @Test
    void testValorMovNegativo(){
        final Conta instance = new Conta();
        final double valor = -500;
        assertThrows(IllegalArgumentException.class, () -> instance.depositoDinheiro(valor));
    } 
    
}
