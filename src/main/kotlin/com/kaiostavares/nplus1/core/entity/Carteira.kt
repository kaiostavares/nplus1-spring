package com.kaiostavares.nplus1.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.math.BigDecimal

@Entity
@Table(name = "carteiras")
class Carteira(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investidor_id")
    val investidor: Investidor,

    @OneToMany(mappedBy = "carteira", fetch = FetchType.LAZY)
    val ordens: MutableSet<OrdemCompra> = mutableSetOf()
) {
    fun totalMovimentado(): BigDecimal {
        return ordens.fold(BigDecimal.ZERO) { acc, ordem ->
            acc.add(ordem.valorTotal())
        }
    }
}