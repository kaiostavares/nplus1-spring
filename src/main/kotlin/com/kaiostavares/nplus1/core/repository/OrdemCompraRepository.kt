package com.kaiostavares.nplus1.core.repository

import com.kaiostavares.nplus1.core.entity.OrdemCompra
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdemCompraRepository : JpaRepository<OrdemCompra, Long>
