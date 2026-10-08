package br.com.alvesmateus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class FluxoFabricaTest {
    private PlacaEletronica placa;

    @BeforeEach
    void setUp() {
        placa = new PlacaEletronica("PLK-101");
    }

    @Test
    @DisplayName("Dada uma placa em Montagem, ao testar, deve avançar para Em Testes")
    void testMontagemParaTestes() {
        assertInstanceOf(EstadoEmMontagem.class, placa.getEstado());

        String retorno = placa.executarTestes();

        assertInstanceOf(EstadoEmTestes.class, placa.getEstado());
        assertTrue(retorno.contains("Montagem concluída"));
    }

    @Test
    @DisplayName("Dada uma placa em Montagem, tentar finalizar deve retornar mensagem de erro")
    void testMontagemFinalizarInvalido() {
        String retorno = placa.concluirProducao();

        assertInstanceOf(EstadoEmMontagem.class, placa.getEstado());
        assertTrue(retorno.contains("Erro: Placa PLK-101 ainda está em montagem"));
    }

    @Test
    @DisplayName("Dada uma placa em Montagem, ao registrar falha, deve ir para Com Defeito")
    void testMontagemParaDefeito() {
        String retorno = placa.registrarFalha();

        assertInstanceOf(EstadoComDefeito.class, placa.getEstado());
        assertTrue(retorno.contains("Defeito mecânico identificado"));
    }

    @Test
    @DisplayName("Dada uma placa em Testes, ao concluir produção, deve avançar para Finalizada")
    void testTestesParaFinalizada() {
        placa.executarTestes();

        String retorno = placa.concluirProducao();

        assertInstanceOf(EstadoFinalizada.class, placa.getEstado());
        assertTrue(retorno.contains("Testes elétricos aprovados com sucesso"));
    }

    @Test
    @DisplayName("Dada uma placa em Testes, ao registrar falha, deve ir para Com Defeito")
    void testTestesParaDefeito() {
        placa.executarTestes();

        String retorno = placa.registrarFalha();

        assertInstanceOf(EstadoComDefeito.class, placa.getEstado());
        assertTrue(retorno.contains("Falha nos testes de continuidade"));
    }

    @Test
    @DisplayName("Dada uma placa Finalizada, qualquer nova ação deve ser bloqueada")
    void testAcoesAposFinalizada() {
        placa.executarTestes();
        placa.concluirProducao();

        String teste = placa.executarTestes();
        String falha = placa.registrarFalha();
        String finalizar = placa.concluirProducao();

        assertInstanceOf(EstadoFinalizada.class, placa.getEstado());
        assertTrue(teste.contains("Erro: Placa PLK-101 já foi finalizada"));
        assertTrue(falha.contains("Erro: Não é possível reprovar"));
        assertTrue(finalizar.contains("Aviso: Placa PLK-101 já se encontra finalizada"));
    }

    @Test
    @DisplayName("Dada uma placa Com Defeito, nenhuma ação de fluxo normal deve ser permitida")
    void testAcoesAposDefeito() {
        placa.registrarFalha();

        String teste = placa.executarTestes();
        String finalizar = placa.concluirProducao();

        assertInstanceOf(EstadoComDefeito.class, placa.getEstado());
        assertTrue(teste.contains("Erro: Placa PLK-101 com defeito"));
        assertTrue(finalizar.contains("Erro: Placa PLK-101 com defeito não pode ser finalizada"));
    }
}
