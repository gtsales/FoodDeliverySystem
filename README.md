# 🍔 Sistema de Delivery de Restaurantes

## 📖 Contexto
Uma associação de restaurantes locais deseja uma plataforma própria para **receber pedidos online**.  
A aplicação deve permitir que **clientes, restaurantes e entregadores** interajam em um fluxo de pedidos simples.

---

## 🎯 Objetivos
- Permitir que clientes façam pedidos online.
- Restaurantes possam cadastrar pratos e gerenciar pedidos.
- Entregadores possam visualizar e aceitar entregas.
- Garantir que cada perfil tenha acesso apenas ao que é seu.

---

## 👥 Perfis de Usuário
### Cliente
- Criar conta e fazer login.
- Navegar pelos restaurantes cadastrados.
- Visualizar cardápio.
- Adicionar pratos ao carrinho e fechar pedido.
- Acompanhar status do pedido.
- Cancelar pedido (somente se ainda não aceito pelo restaurante).

### Restaurante
- Criar conta e fazer login.
- Cadastrar pratos (nome, descrição, preço, foto, disponibilidade).
- Receber pedidos dos clientes.
- Atualizar status do pedido (aguardando → em preparo → pronto para entrega).

### Entregador
- Criar conta e fazer login.
- Visualizar pedidos prontos para entrega.
- Aceitar pedido de entrega.
- Atualizar status do pedido (em rota → entregue).

---

## 📋 Requisitos Funcionais
- Cadastro e autenticação de usuários (clientes, restaurantes e entregadores).
- CRUD de pratos (apenas restaurantes podem gerenciar seus pratos).
- Criar pedido (cliente → restaurante).
- Listar pedidos do cliente.
- Listar pedidos do restaurante.
- Listar pedidos disponíveis para entrega.
- Atualização de status do pedido conforme fluxo.
- Cancelamento de pedidos (restrições definidas).

---

## ⚙️ Requisitos Não Funcionais
- Autenticação via **JWT**.
- Banco de dados relacional (MySQL, PostgreSQL ou SQLite).
- Logs de operações principais.
- Testes unitários.
- Documentação da API (Swagger/OpenAPI).

---

## 🗄️ Modelo de Dados (Proposta)

### Cliente
| Campo      | Tipo        | Observação |
|------------|-------------|------------|
| id         | PK          |            |
| cpf        |             | único      |
| nome       | varchar     |            |
| email      | varchar     | único      |
| senha      | varchar     | hash       |
| endereco   | varchar     | obrigatório|

### Restaurante
| Campo      | Tipo        | Observação |
|------------|-------------|------------|
| id         | PK          |            |
| cnpj       |             | único      |
| nome       | varchar     |            |
| email      | varchar     | único      |
| senha      | varchar     | hash       |
| endereco   | varchar     | obrigatório|

### Entregador
| Campo      | Tipo        | Observação |
|------------|-------------|------------|
| id         | PK          |            |
| cpf        |             | único      |
| num_cnh    |             | único      |
| nome       | varchar     |            |
| email      | varchar     | único      |
| senha      | varchar     | hash       |
| veiculo    | varchar     | moto, bike, carro |

### Prato
| Campo        | Tipo        | Observação |
|--------------|-------------|------------|
| id           | PK          |            |
| nome         | varchar     |            |
| descricao    | text        | opcional   |
| preco        | decimal     |            |
| disponivel   | boolean     | default: true |
| restaurante_id | FK → Restaurante | |

### Pedido
| Campo         | Tipo        | Observação |
|---------------|-------------|------------|
| id            | PK          |            |
| cliente_id    | FK → Cliente |            |
| restaurante_id | FK → Restaurante |        |
| entregador_id | FK → Entregador (null) | só preenchido quando aceito |
| status        | enum        | aguardando, em preparo, pronto, em rota, entregue, cancelado |
| total         | decimal     | calculado  |
| criado_em     | datetime    |            |

### ItensPedido
| Campo      | Tipo        | Observação |
|------------|-------------|------------|
| id         | PK          |            |
| pedido_id  | FK → Pedido |            |
| prato_id   | FK → Prato  |            |
| quantidade | int         |            |
| subtotal   | decimal     | preco * quantidade |

---

## 🔄 Fluxo de Pedido

1. **Cliente** cria pedido → status **Aguardando**.
2. **Restaurante** aceita e coloca em preparo → status **Sendo preparado**.
3. Restaurante finaliza preparo → status **Pedido pronto**.
4. **Entregador** aceita → status **Saiu para entrega**.
5. Entregador conclui → status **Pedido entregue**.
6. Cliente pode visualizar todo o histórico.

---

## 🚀 Roadmap de Implementação

1. **Setup do projeto**  
   - Criar repositório  
   - Configurar ambiente (Spring Boot)  

2. **Banco de Dados**  
   - Criar modelos/tabelas conforme especificado  

3. **Autenticação**  
   - Implementar cadastro/login com JWT  
   - Diferenciar papéis (cliente, restaurante, entregador)  

4. **Módulo de Restaurantes**  
   - CRUD de pratos  
   - Listagem de pedidos recebidos  

5. **Módulo de Clientes**  
   - Listar restaurantes e pratos  
   - Criar pedidos  

6. **Módulo de Entregadores**  
   - Listar pedidos prontos para entrega  
   - Aceitar e atualizar status  

7. **Regras de Negócio**  
   - Impedir cliente de pedir prato indisponível  
   - Calcular total do pedido  
   - Controlar fluxo de status  
---
