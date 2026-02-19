package com.kaiostavares.nplus1.service

import com.kaiostavares.nplus1.core.repository.InvestidorRepository
import com.kaiostavares.nplus1.dto.CarteiraDTO
import com.kaiostavares.nplus1.dto.ResumoFiscalResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    @Transactional(readOnly = true)
    fun gerarRelatorioPaginado(pageable: Pageable): Page<ResumoFiscalResponse> {
        val pageIds = investidorRepository.buscarIdsPaginado(pageable)
        if (pageIds.isEmpty) {
            return Page.empty(pageable)
        }
        val investidores = investidorRepository.buscarTodosComInvestimentosPorIds(pageIds.content)
        val investidoresMap = investidores.associateBy { it.id }
        return pageIds.map { id ->
            val investidor = investidoresMap[id]
                ?: throw IllegalStateException("Investidor $id não encontrado no banco")
            val resumosCarteira = investidor.carteiras.map { carteira ->
                CarteiraDTO(carteira.nome, carteira.totalMovimentado())
            }
            ResumoFiscalResponse(investidor.nome, resumosCarteira)
        }
    }

}