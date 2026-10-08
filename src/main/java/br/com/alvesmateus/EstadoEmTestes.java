package br.com.alvesmateus;

public class EstadoEmTestes implements EstadoPlaca {
    @Override
    public String testar(PlacaEletronica placa) {
        return "Aviso: Placa " + placa.getId() + " já está na fase de testes.";
    }

    @Override
    public String finalizar(PlacaEletronica placa) {
        placa.setEstado(new EstadoFinalizada());
        return "Placa " + placa.getId() + ": Testes elétricos aprovados com sucesso. Produção concluída.";
    }

    @Override
    public String reprovar(PlacaEletronica placa) {
        placa.setEstado(new EstadoComDefeito());
        return "Placa " + placa.getId() + ": Falha nos testes de continuidade elétricos.";
    }

    @Override
    public String getNomeEstado() {
        return "Em Testes";
    }
}
