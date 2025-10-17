Arquivos:

```
└───src
    └───main
            Client.java - Inicia a conexão com o servidor e troca mensagens criptografadas usando RSA.java via Connection.java.
            Connection.java - – Ponte entre Server e Client, gerencia a comunicação via sockets.
            RSA.java - Algoritmo de criptografia e descriptografia das mensagens.
            Server.java - Escuta conexões de clientes, processa e responde mensagens criptografadas usando RSA.java.
```

---

Abra o projeto de preferência em uma IDE. Para o desenvolvimento desse, utilizei IntelliJ.

Ordem de execução:
- 1 Server.java
- 2 Client.java
  
---

Classes do projeto (em UML):
<img width="1228" height="2000" alt="package" src="https://github.com/user-attachments/assets/5f148984-7d38-4913-9a64-a831fed4e136" />

---

## Criptografia e Descriptografia das mensagens - valores utilizados (vide `RSA.java`):

Valores utilizados

| Etapa | Descrição | Valor |
|:------|:-----------|:------|
| **Encontrar dois primos P e Q** | P = 61, Q = 53 | P = 61<br>Q = 53 |
| **Calcular N = P × Q (modulus)** | 61 × 53 | **N = 3233** |
| **Calcular ϕ(N)** | ϕ(N) = (P - 1) × (Q - 1) | **ϕ(N) = 3120** |
| **Definir E (expoente público)** | 1 < E < ϕ(N), mdc(E, ϕ(N)) = 1 | **E = 17** |
| **Calcular D (inverso multiplicativo de E mod ϕ(N))** | D × E ≡ 1 (mod ϕ(N)) | **D = 2753** |

Chaves

| Tipo de Chave | Componentes | Valores | Finalidade |
|:---------------|:------------|:---------|:------------|
| **Chaves Públicas** | N, E | N = 3233<br>E = 17 | Usadas para **criptografar** |
| **Chaves Públicas** | P, Q, E | P = 61<br>Q = 53<br>E = 17 | Usadas para **criptografar** |
| **Chaves Privadas** | P, Q, D | P = 61<br>Q = 53<br>D = 2753 | Usadas para **decifrar** |
| **Chaves Privadas** | N, D | N = 3233<br>D = 2753 | Usadas para **decifrar** |







