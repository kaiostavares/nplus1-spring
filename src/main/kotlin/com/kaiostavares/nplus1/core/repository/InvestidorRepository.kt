package com.kaiostavares.nplus1.core.repository

import com.kaiostavares.nplus1.core.entity.Investidor
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
}
