package com.kaiostavares.nplus1.dto

import java.math.BigDecimal

data class ResumoFiscalResponse(
    val nomeInvestidor: String,
    val movimentacao: List<CarteiraDTO>,
)

data class CarteiraDTO(
    val nome: String,
    val totalMovimentado: BigDecimal
)