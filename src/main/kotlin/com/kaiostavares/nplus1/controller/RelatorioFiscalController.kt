package com.kaiostavares.nplus1.controller

import com.kaiostavares.nplus1.dto.ResumoFiscalResponse
import com.kaiostavares.nplus1.service.RelatorioFiscalService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/relatorios")
class RelatorioFiscalController(
    private val service: RelatorioFiscalService
) {
    @GetMapping
    fun gerarRelatorio(): ResponseEntity<Set<ResumoFiscalResponse>> {
        val relatorio = service.gerarRelatorio()
        return ResponseEntity.ok(relatorio)
    }

    @GetMapping("/otimizado")
    fun gerarRelatorioOtimizado(): ResponseEntity<Set<ResumoFiscalResponse>> {
        val relatorio = service.gerarRelatorioOtimizado()
        return ResponseEntity.ok(relatorio)
    }

    @GetMapping("/ultra-otimizado")
    fun gerarRelatorioUltraOtimizado(): ResponseEntity<Set<ResumoFiscalResponse>> {
        val relatorio = service.gerarRelatorioComProjecao()
        return ResponseEntity.ok(relatorio)
    }

    @GetMapping("/paginado")
    fun gerarRelatorioPaginado(
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Page<ResumoFiscalResponse>> {
        val relatorio = service.gerarRelatorioPaginado(pageable)
        return ResponseEntity.ok(relatorio)
    }
}