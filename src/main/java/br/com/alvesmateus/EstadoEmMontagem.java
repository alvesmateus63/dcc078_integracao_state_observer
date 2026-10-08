package br.com.alvesmateus;

public class EstadoEmMontagem implements EstadoPlaca {
    @Override
    public String testar(PlacaEletronica placa) {
        placa.setEstado(new EstadoEmTestes());
        return "Placa " + placa.getId() + ": Montagem concluída. Enviada para bancada de testes.";
    }

    @Override
    public String finalizar(PlacaEletronica placa) {
        return "Erro: Placa " + placa.getId() + " ainda está em montagem e precisa ser testada primeiro.";
    }

    @Override
    public String reprovar(PlacaEletronica placa) {
        placa.setEstado(new EstadoComDefeito());
        return "Placa " + placa.getId() + ": Defeito mecânico identificado durante a montagem.";
    }

    @Override
    public String getNomeEstado() {
        return "Em Montagem";
    }
}
