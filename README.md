# N+1 Queries Problem - Spring Data JPA

![Java](https://img.shields.io/badge/Java-21-orange)
![Kotlin](https://img.shields.io/badge/Kotlin-2.2.21-7F52FF)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-6DB33F)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-4.0.2-6DB33F)
![H2 Database](https://img.shields.io/badge/H2%20Database-2.4.240-4479A1)
![Gradle](https://img.shields.io/badge/Gradle-9.3.0-02303A)

Projeto de demonstração prático sobre o problema das **N+1 consultas** em aplicações Spring Data JPA e como resolvê-lo com diferentes estratégias.

## 📖 Artigo Completo

Confira a explicação detalhada no Medium: [Entenda o problema das N+1 consultas e como resolvê-lo em aplicações Spring Data JPA](https://medium.com/@kaio.stavaress/entenda-o-problema-das-n-1-consultas-e-como-resolv%C3%AA-lo-em-aplica%C3%A7%C3%B5es-spring-data-jpa-7b02736ce668)

## ⚙️ Como Executar

### Pré-requisitos
- Java 21+
- Gradle 8.x (ou use `./gradlew`)

### Executar
```bash
git clone https://github.com/kaiostavares/nplus1-spring.git
cd nplus1-spring
./gradlew bootRun
```

Acesse em `http://localhost:8080` e o console H2 em `http://localhost:8080/h2-console` (usuário: `sa`, senha: `password`).

## 📈 Cenário do Projeto

Demonstração prática usando um domínio do mercado financeiro:
- **Investidores** com múltiplas **carteiras**
- Cada carteira com múltiplas **ordens de compra**

## 🎯 Exemplos Implementados

- Performance com e sem otimização
- Soluções com `JOIN FETCH`
- Paginação
- Projections

---

**Autor**: [Kaio Sande Tavares](https://medium.com/@kaio.stavaress)
