package br.com.alvesmateus;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SistemaMonitoramentoFabrica implements Observer {
    private final List<EventoProducao> historicoEventos = new ArrayList<>();

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof EventoProducao evento) {
            historicoEventos.add(evento);
        }
    }

    public List<EventoProducao> getHistoricoEventos() {
        return new ArrayList<>(historicoEventos);
    }

    public String getUltimoLog() {
        if (historicoEventos.isEmpty()) {
            return "Nenhum evento registrado.";
        }
        EventoProducao ultimo = historicoEventos.get(historicoEventos.size() - 1);
        return String.format("[%s] Estado alterado de '%s' para '%s'",
                ultimo.idPlaca(), ultimo.estadoAnterior(), ultimo.estadoAtual());
    }
}
