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

---
## Exemplos de execução e validação no _[RSA Calculator](https://www.cs.drexel.edu/~popyack/Courses/CSP/Fa17/notes/10.1_Cryptography/RSA_Express_EncryptDecrypt_v2.html)_

Client envia

<img width="1227" height="295" alt="Screenshot 2025-10-17 185039" src="https://github.com/user-attachments/assets/7c8c4eea-9952-4094-8958-92eda65f22b1" />

Server recebe

<img width="1295" height="285" alt="Screenshot 2025-10-17 185057" src="https://github.com/user-attachments/assets/33d61d10-b707-4189-b897-6eb055ff1a6a" />

Server envia

<img width="1028" height="323" alt="Screenshot 2025-10-17 185121" src="https://github.com/user-attachments/assets/e61152b2-23b3-4091-8975-cd063542b5ed" />

Client recebe

<img width="1071" height="298" alt="Screenshot 2025-10-17 185240" src="https://github.com/user-attachments/assets/b0217e32-96bb-4f2b-912f-55fddb8d2abb" />

## Validações

Compare com as execuções acima

Critografia e Descriptografia "_Ola servidor, tudo bem?_"

<img width="767" height="363" alt="Screenshot 2025-10-17 185459" src="https://github.com/user-attachments/assets/c7835824-5ba8-430e-8769-e220aebabaef" />

Criptografia e Descriptografia "_Ola client, tudo e voce?_"

<img width="769" height="365" alt="Screenshot 2025-10-17 185548" src="https://github.com/user-attachments/assets/82517da6-8bbf-49a0-86c6-64798c84d173" />































