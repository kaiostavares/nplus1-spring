package com.kaiostavares.nplus1.core.repository

import com.kaiostavares.nplus1.core.entity.Investidor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvestidorRepository : JpaRepository<Investidor, Long>