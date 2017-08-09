package com.ufcg.si1.prefeitura;

import com.ufcg.si1.service.*;

public class PrefeituraFachada {
	QueixaService queixaService = new QueixaServiceImpl();
	EspecialidadeService especialidadeService = new EspecialidadeServiceImpl();
	UnidadeSaudeService unidadeSaudeService = new UnidadeSaudeServiceImpl();

	private SituacaoPrefeitura situacaoAtualPrefeitura = SituacaoPrefeitura.NORMAL;

	public PrefeituraFachada() {

	}

}
