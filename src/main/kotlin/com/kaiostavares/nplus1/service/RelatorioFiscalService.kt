package com.kaiostavares.nplus1.service

import com.kaiostavares.nplus1.core.repository.InvestidorRepository
import com.kaiostavares.nplus1.dto.CarteiraDTO
import com.kaiostavares.nplus1.dto.ResumoFiscalResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class RelatorioFiscalService (
    private val investidorRepository: InvestidorRepository,
) {
    @Transactional(readOnly = true)
    fun gerarRelatorio(): Set<ResumoFiscalResponse> {
        val investidores = investidorRepository.findAll()
        return investidores.map { investidor ->
            val resumosCarteira = investidor.carteiras.map { carteira ->
                CarteiraDTO(carteira.nome, carteira.totalMovimentado())
            }
            ResumoFiscalResponse(investidor.nome, resumosCarteira)
        }.toSet()
    }

    @Transactional(readOnly = true)
    fun gerarRelatorioOtimizado(): Set<ResumoFiscalResponse> {
        val investidores = investidorRepository.buscarTodosComInvestimentos()
        return investidores.map { investidor ->
            val resumosCarteira = investidor.carteiras.map { carteira ->
                CarteiraDTO(carteira.nome, carteira.totalMovimentado())
            }
            ResumoFiscalResponse(investidor.nome, resumosCarteira)
        }.toSet()
    }

    @Transactional(readOnly = true)
    fun gerarRelatorioComProjecao(): Set<ResumoFiscalResponse> {
        val dadosPlanos = investidorRepository.buscarRelatorioResumido()
        return dadosPlanos
            .groupBy { it.nomeInvestidor }
            .map { (nomeInvestidor, items) ->
                val carteiras = items.mapNotNull { item ->
                    if (item.nomeCarteira != null) {
                        CarteiraDTO(
                            nome = item.nomeCarteira,
                            totalMovimentado = item.totalMovimentado ?: BigDecimal.ZERO
                        )
                    } else null
                }
                ResumoFiscalResponse(nomeInvestidor, carteiras)
            }.toSet()
    }

}