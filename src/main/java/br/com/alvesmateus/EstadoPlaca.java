package br.com.alvesmateus;

public interface EstadoPlaca {
    String testar(PlacaEletronica placa);
    String finalizar(PlacaEletronica placa);
    String reprovar(PlacaEletronica placa);
    String getNomeEstado();
}
