package com.kaiostavares.nplus1.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name = "investidores")
class Investidor (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,

    @OneToMany(mappedBy = "investidor", fetch = FetchType.LAZY)
    val carteiras: MutableSet<Carteira> = mutableSetOf()
)