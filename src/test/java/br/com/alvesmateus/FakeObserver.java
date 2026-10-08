package br.com.alvesmateus;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FakeObserver implements Observer {
    private final List<EventoProducao> eventosRecebidos = new ArrayList<>();
    private int chamadasUpdate = 0;

    @Override
    public void update(Observable o, Object arg) {
        chamadasUpdate++;
        if (arg instanceof EventoProducao evento) {
            eventosRecebidos.add(evento);
        }
    }

    public int getChamadasUpdate() {
        return chamadasUpdate;
    }

    public List<EventoProducao> getEventosRecebidos() {
        return eventosRecebidos;
    }

    public EventoProducao getUltimoEvento() {
        if (eventosRecebidos.isEmpty()) {
            return null;
        }
        return eventosRecebidos.get(eventosRecebidos.size() - 1);
    }
}
