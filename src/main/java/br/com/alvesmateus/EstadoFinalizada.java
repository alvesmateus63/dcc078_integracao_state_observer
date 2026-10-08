package br.com.alvesmateus;

public class EstadoFinalizada implements EstadoPlaca {
    @Override
    public String testar(PlacaEletronica placa) {
        return "Erro: Placa " + placa.getId() + " já foi finalizada e embalada.";
    }

    @Override
    public String finalizar(PlacaEletronica placa) {
        return "Aviso: Placa " + placa.getId() + " já se encontra finalizada.";
    }

    @Override
    public String reprovar(PlacaEletronica placa) {
        return "Erro: Não é possível reprovar uma placa já finalizada.";
    }

    @Override
    public String getNomeEstado() {
        return "Finalizada";
    }
}
