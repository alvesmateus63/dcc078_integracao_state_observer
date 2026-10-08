package br.com.alvesmateus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.*;

public class ObserverTest {
    @Test
    @DisplayName("Deve notificar o Observer real quando o estado da placa mudar")
    void testNotificacaoObserverReal() {
        PlacaEletronica placa = new PlacaEletronica("PLK-202");
        SistemaMonitoramentoFabrica monitor = new SistemaMonitoramentoFabrica();

        placa.addObserver(monitor);
        placa.executarTestes();

        assertEquals(1, monitor.getHistoricoEventos().size());

        EventoProducao evento = monitor.getHistoricoEventos().get(0);
        assertEquals("PLK-202", evento.idPlaca());
        assertEquals("Em Montagem", evento.estadoAnterior());
        assertEquals("Em Testes", evento.estadoAtual());
    }

    @Test
    @DisplayName("Deve notificar múltiplos observadores cadastrados")
    void testMultiplosObservers() {
        PlacaEletronica placa = new PlacaEletronica("PLK-303");

        FakeObserver observer1 = new FakeObserver();
        FakeObserver observer2 = new FakeObserver();

        placa.addObserver(observer1);
        placa.addObserver(observer2);

        placa.executarTestes();

        assertEquals(1, observer1.getChamadasUpdate());
        assertEquals(1, observer2.getChamadasUpdate());
    }

    @Test
    @DisplayName("Garante o correto encadeamento e conteúdo dos eventos transmitidos aos observers")
    void testConteudoEventoObserver() {
        PlacaEletronica placa = new PlacaEletronica("PLK-404");
        FakeObserver fakeObserver = new FakeObserver();

        placa.addObserver(fakeObserver);

        placa.executarTestes();
        placa.concluirProducao();

        assertEquals(2, fakeObserver.getChamadasUpdate());

        EventoProducao segundoEvento = fakeObserver.getUltimoEvento();
        assertNotNull(segundoEvento);
        assertEquals("PLK-404", segundoEvento.idPlaca());
        assertEquals("Em Testes", segundoEvento.estadoAnterior());
        assertEquals("Finalizada", segundoEvento.estadoAtual());
    }

    @Test
    @DisplayName("Não deve notificar o observer quando a ação não altera o estado")
    void testAcaoSemMudancaDeEstadoNaoNotifica() {
        PlacaEletronica placa = new PlacaEletronica("PLK-505");
        FakeObserver fakeObserver = new FakeObserver();

        placa.addObserver(fakeObserver);

        placa.concluirProducao();

        assertEquals(0, fakeObserver.getChamadasUpdate());
        assertTrue(fakeObserver.getEventosRecebidos().isEmpty());
    }
}
