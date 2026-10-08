package br.com.alvesmateus;

public class EstadoComDefeito implements EstadoPlaca {
    @Override
    public String testar(PlacaEletronica placa) {
        return "Erro: Placa " + placa.getId() + " com defeito. Encaminhada para sucata/retrabalho.";
    }

    @Override
    public String finalizar(PlacaEletronica placa) {
        return "Erro: Placa " + placa.getId() + " com defeito não pode ser finalizada.";
    }

    @Override
    public String reprovar(PlacaEletronica placa) {
        return "Aviso: Placa " + placa.getId() + " já está marcada como defeituosa.";
    }

    @Override
    public String getNomeEstado() {
        return "Com Defeito";
    }
}
