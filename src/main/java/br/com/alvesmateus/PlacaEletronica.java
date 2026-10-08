package br.com.alvesmateus;

import java.util.Observable;

public class PlacaEletronica extends Observable {
    private final String id;
    private EstadoPlaca estadoAtual;

    public PlacaEletronica(String id) {
        this.id = id;
        this.estadoAtual = new EstadoEmMontagem();
    }

    public String getId() {
        return id;
    }

    public EstadoPlaca getEstado() {
        return estadoAtual;
    }

    protected void setEstado(EstadoPlaca novoEstado) {
        String estadoAnterior = this.estadoAtual.getNomeEstado();
        this.estadoAtual = novoEstado;

        setChanged();
        notifyObservers(new EventoProducao(
                this.id,
                estadoAnterior,
                novoEstado.getNomeEstado(),
                "Transição de estado realizada"
        ));
    }

    public String executarTestes() {
        return this.estadoAtual.testar(this);
    }

    public String concluirProducao() {
        return this.estadoAtual.finalizar(this);
    }

    public String registrarFalha() {
        return this.estadoAtual.reprovar(this);
    }
}
