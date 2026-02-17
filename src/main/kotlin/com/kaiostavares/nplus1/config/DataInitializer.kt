package com.kaiostavares.nplus1.config

import com.kaiostavares.nplus1.core.repository.CarteiraRepository
import com.kaiostavares.nplus1.core.repository.InvestidorRepository
import com.kaiostavares.nplus1.core.repository.OrdemCompraRepository
import com.kaiostavares.nplus1.core.entity.Carteira
import com.kaiostavares.nplus1.core.entity.Investidor
import com.kaiostavares.nplus1.core.entity.OrdemCompra
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DataInitializer(
    private val investidorRepository: InvestidorRepository,
    private val carteiraRepository: CarteiraRepository,
    private val ordemCompraRepository: OrdemCompraRepository
) : CommandLineRunner {

    override fun run(vararg args: String) {
        if (investidorRepository.count() > 0) {
            println("Database already has data. Skipping initialization.")
            return
        }

        println("Initializing database with sample data...")

        // Criando investidores
        val investidor1 = investidorRepository.save(
            Investidor(nome = "Investidor 1")
        )

        val investidor2 = investidorRepository.save(
            Investidor(nome = "Investidor 2")
        )

        val investidor3 = investidorRepository.save(
            Investidor(nome = "Investidor 3")
        )

        // Criando carteiras
        val carteira1 = carteiraRepository.save(
            Carteira(nome = "Carteira Conservadora", investidor = investidor1)
        )

        val carteira2 = carteiraRepository.save(
            Carteira(nome = "Carteira Moderada", investidor = investidor1)
        )

        val carteira3 = carteiraRepository.save(
            Carteira(nome = "Carteira Agressiva", investidor = investidor2)
        )

        val carteira4 = carteiraRepository.save(
            Carteira(nome = "Carteira Dividend Yield", investidor = investidor2)
        )

        val carteira5 = carteiraRepository.save(
            Carteira(nome = "Carteira Growth", investidor = investidor3)
        )

        // Criando ordens de compra
        val ordensCompra = listOf(
            // Carteira 1 - Investidor 1 (Conservadora)
            OrdemCompra(nome = "ITUB4", quantidade = 100, precoExecucao = BigDecimal("25.50"), carteira = carteira1),
            OrdemCompra(nome = "BBDC4", quantidade = 50, precoExecucao = BigDecimal("18.75"), carteira = carteira1),
            OrdemCompra(nome = "PETR4", quantidade = 30, precoExecucao = BigDecimal("32.10"), carteira = carteira1),

            // Carteira 2 - Investidor 1 (Moderada)
            OrdemCompra(nome = "VALE3", quantidade = 80, precoExecucao = BigDecimal("62.30"), carteira = carteira2),
            OrdemCompra(nome = "WEGE3", quantidade = 40, precoExecucao = BigDecimal("45.80"), carteira = carteira2),
            OrdemCompra(nome = "RENT3", quantidade = 60, precoExecucao = BigDecimal("55.20"), carteira = carteira2),

            // Carteira 3 - Investidor 2 (Agressiva)
            OrdemCompra(nome = "MGLU3", quantidade = 200, precoExecucao = BigDecimal("12.40"), carteira = carteira3),
            OrdemCompra(nome = "AMER3", quantidade = 150, precoExecucao = BigDecimal("22.90"), carteira = carteira3),
            OrdemCompra(nome = "PETZ3", quantidade = 100, precoExecucao = BigDecimal("8.75"), carteira = carteira3),

            // Carteira 4 - Investidor 2 (Dividend Yield)
            OrdemCompra(nome = "TAEE11", quantidade = 300, precoExecucao = BigDecimal("35.60"), carteira = carteira4),
            OrdemCompra(nome = "EGIE3", quantidade = 120, precoExecucao = BigDecimal("42.15"), carteira = carteira4),
            OrdemCompra(nome = "CPLE6", quantidade = 90, precoExecucao = BigDecimal("68.90"), carteira = carteira4),

            // Carteira 5 - Investidor 3 (Growth)
            OrdemCompra(nome = "ASAI3", quantidade = 180, precoExecucao = BigDecimal("15.30"), carteira = carteira5),
            OrdemCompra(nome = "SOMA3", quantidade = 70, precoExecucao = BigDecimal("28.50"), carteira = carteira5),
            OrdemCompra(nome = "LWSA3", quantidade = 250, precoExecucao = BigDecimal("6.80"), carteira = carteira5)
        )

        ordemCompraRepository.saveAll(ordensCompra)

        println("Database initialized successfully!")
        println("Created:")
        println("- ${investidorRepository.count()} investidores")
        println("- ${carteiraRepository.count()} carteiras")
        println("- ${ordemCompraRepository.count()} ordens de compra")
    }
}

