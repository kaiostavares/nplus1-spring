package com.kaiostavares.nplus1.dto

import java.math.BigDecimal

data class InvestidorResumoDTO(
    val investidorId: Long,
    val nomeInvestidor: String,
    val carteiraId: Long?,
    val nomeCarteira: String?,
    val totalMovimentado: BigDecimal?
)
