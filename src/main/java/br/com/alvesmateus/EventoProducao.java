package br.com.alvesmateus;

public record EventoProducao(
        String idPlaca,
        String estadoAnterior,
        String estadoAtual,
        String mensagem)
{}
