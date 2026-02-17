package com.kaiostavares.nplus1.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "ordem_compras")
class OrdemCompra(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val quantidade: Int,
    val precoExecucao: BigDecimal,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carteira_id")
    val carteira: Carteira
) {
    fun valorTotal(): BigDecimal {
        return precoExecucao.multiply(BigDecimal(quantidade))
    }
}

