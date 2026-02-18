package com.kaiostavares.nplus1.core.repository

import com.kaiostavares.nplus1.core.entity.Investidor
import com.kaiostavares.nplus1.dto.InvestidorResumoDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface InvestidorRepository : JpaRepository<Investidor, Long> {

    @Query("""
        SELECT DISTINCT i FROM Investidor i
        LEFT JOIN FETCH i.carteiras c
        LEFT JOIN FETCH c.ordens
    """)
    fun buscarTodosComInvestimentos(): List<Investidor>

    @Query("""
        SELECT new com.kaiostavares.nplus1.dto.InvestidorResumoDTO(
            i.id,
            i.nome,
            c.id,
            c.nome,
            SUM(o.precoExecucao * o.quantidade)
        )
        FROM Investidor i
        LEFT JOIN i.carteiras c
        LEFT JOIN c.ordens o
        GROUP BY i.id, i.nome, c.id, c.nome
    """)
    fun buscarRelatorioResumido(): List<InvestidorResumoDTO>
}
