# 🏦 Sistema Bancário Digital

Sistema de gerenciamento bancário desenvolvido em Java com interface gráfica Swing, implementando conceitos de Programação Orientada a Objetos (POO) para simular operações bancárias básicas.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Executar](#como-executar)
- [Capturas de Tela](#capturas-de-tela)
- [Testes](#testes)
- [Aprendizados](#aprendizados)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

## 🎯 Sobre o Projeto

Este projeto foi desenvolvido como ferramenta de aprendizado para aprimorar conhecimentos em:
- Programação Orientada a Objetos (POO)
- Java SE e Swing
- Padrão MVC (Model-View-Controller)
- Validação de dados
- Testes unitários com JUnit 5
- Manipulação de valores monetários com BigDecimal
- Workflow (CI/CD)

O sistema simula um banco digital básico onde é possível cadastrar clientes, criar contas (corrente ou poupança), realizar depósitos e saques, consultar informações e encerrar contas.

## ✨ Funcionalidades

### 📝 Cadastro de Cliente
- ✅ Validação de nome (não aceita campos vazios ou apenas espaços)
- ✅ Validação de CPF com dígito verificador
- ✅ Formatação automática de CPF (XXX.XXX.XXX-XX)
- ✅ Seleção de tipo de conta (Corrente ou Poupança)
- ✅ Interface moderna e intuitiva

### 💰 Bônus de Boas-vindas
- 💵 **Conta Corrente**: R$ 50,00 de bônus inicial
- 🏦 **Conta Poupança**: R$ 150,00 de bônus inicial

### 🔄 Operações Bancárias
- 📥 **Depósito**: Adicionar valores à conta com validação
  - Aceita vírgula ou ponto como separador decimal
  - Validação de valores numéricos
  - Atualização em tempo real do saldo

- 📤 **Saque**: Retirar valores da conta com validações
  - Verificação de saldo disponível
  - Impede saque em conta com saldo insuficiente
  - Conversão precisa de valores monetários

- ℹ️ **Consultar Informações**: Exibe dados completos da conta
  - Nome do cliente
  - CPF formatado
  - Tipo de conta
  - Saldo disponível
  - Status da conta (Ativa/Inativa)

- 🚪 **Encerrar Conta**: Finaliza a conta bancária
  - Validação: só permite encerramento com saldo zerado
  - Atualiza status da conta para "Inativa"

### 🛡️ Segurança e Validações
- ✅ Validação robusta de CPF usando algoritmo de dígito verificador
- ✅ Prevenção de operações em contas inativas
- ✅ Conversão precisa de valores monetários usando BigDecimal
- ✅ Truncamento para 2 casas decimais (sem arredondamento indevido)
- ✅ Tratamento de erros e exceções

## 🚀 Tecnologias Utilizadas

- **Java 17+** - Linguagem de programação
- **Swing** - Interface gráfica
- **JUnit 5** - Framework de testes
- **BigDecimal** - Manipulação precisa de valores monetários
- **Locale PT-BR** - Formatação brasileira de números
- **Maven/Gradle** - Gerenciamento de dependências (opcional)

## 📁 Estrutura do Projeto

```
ContaBanco
│   .gitignore
│   pom.xml                # Dependências
│
├───.github
│   └───workflows
│           maven-tests.yml
│
├───screenshots
│       cadastro.png
│       cadastro_corrente.png
│       cadastro_dadosValidados.png
│       principal.png
│       principal_encerrarContaComSaldo.png
│       principal_infoConta.png
│
├───src
   ├───main
   │   └───java
   │       │   App.java    # Entrada da aplicação
   │       │
   │       ├───controller
   │       │       ContaBanco.java            # Lógica de negócio
   │       │
   │       └───view
   │               JCadastrarCliente.java    # Tela de cadastr
   │               JContaBancoUi.java        # Tela principal
   │
   └───test
       └───java
               AppTest.java                # Testes unitários
```

### Arquitetura MVC

O projeto segue o padrão MVC simplificado:
- **Model**: `#N/D` - Representa os dados de negócio (Não houve desenvolvimento)
- **View**: `JCadastrarCliente.java` e `JContaBancoUi.java` - Interface gráfica
- **Controller**: Lógica de controle dentro de `ContaBanco.java`

## 💻 Como Executar

### Pré-requisitos
- Java JDK 17 ou superior
- IDE Java (Eclipse, IntelliJ IDEA, NetBeans ou VS Code com extensões Java)

### Passo a Passo

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/sistema-bancario.git
cd sistema-bancario
```

2. **Compile o projeto**
```bash
javac -d bin src/**/*.java
```

3. **Execute a aplicação**
```bash
java -cp bin App
```

Ou abra o projeto em sua IDE favorita e execute a classe `App.java`.

### Executando com Maven (opcional)
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="App"
```

## 📸 Capturas de Tela

### Tela de Cadastro
![Tela de Cadastro]([screenshots/tela-cadastro.png](https://github.com/irlan24/Simulador_conta_bancaria/blob/trampo/ContaBanco/screenshots/cadastro.png))
*Tela inicial para cadastro de novos clientes com validação de CPF*

### Tela Principal - Gerenciamento de Conta
![Tela Principal](https://github.com/irlan24/Simulador_conta_bancaria/blob/trampo/ContaBanco/screenshots/principal.png)
*Interface principal mostrando saldo e opções de operações*

### Consulta de Informações
![Informações](https://github.com/irlan24/Simulador_conta_bancaria/blob/trampo/ContaBanco/screenshots/principal_infoConta.png)
*Exibindo informações completas da conta*


## 🧪 Testes

O projeto inclui uma suite completa de testes unitários usando JUnit 5.

### Cobertura de Testes
- ✅ Validação de CPF (8 cenários)
- ✅ Validação de dados de cadastro (7 cenários)
- ✅ Conversão de moeda com BigDecimal (6 cenários)
- ✅ Operações bancárias (8 cenários)
- ✅ Fechamento de conta (4 cenários)
- ✅ Estado da conta (4 cenários)
- ✅ Fluxos de integração completos (5 cenários)
- ✅ Casos extremos e robustez (4 cenários)

**Total: 46+ casos de teste**

### Executar Testes

**Com IDE:** Clique com botão direito em `AppTest.java` → Run as → JUnit Test

**Com Maven:**
```bash
mvn test
```

**Manualmente:**
```bash
javac -cp .:junit-platform-console-standalone.jar test/AppTest.java
java -jar junit-platform-console-standalone.jar --class-path . --scan-class-path
```

### Exemplo de Saída dos Testes
```
✓ Deve validar CPF válido com formatação
✓ Deve rejeitar CPF com dígitos repetidos
✓ Deve converter moeda com 2 casas decimais
✓ Deve criar conta corrente e aplicar bônus de R$ 50,00
✓ Deve fechar conta com saldo zero
✓ Fluxo completo: Cadastro → Bônus → Operações → Verificação

Tests run: 46, Failures: 0, Errors: 0, Skipped: 0
```

## 📚 Aprendizados

Este projeto demonstra a aplicação prática de diversos conceitos:

### POO (Programação Orientada a Objetos)
- **Encapsulamento**: Atributos privados com getters/setters
- **Abstração**: Métodos que encapsulam lógica complexa
- **Modularização**: Separação clara de responsabilidades

### Boas Práticas de Código
- Validação de entrada do usuário
- Tratamento de exceções
- Uso de BigDecimal para valores monetários
- Código testável e manutenível
- Nomenclatura clara e descritiva

### Design Patterns
- MVC simplificado
- Separação de camadas (View, Controller, Model)

### Qualidade de Software
- Testes unitários abrangentes
- Cobertura de casos extremos
- Documentação clara

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um Fork do projeto
2. Crie uma Branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a Branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

### Ideias para Melhorias
- [ ] Adicionar autenticação com senha
- [ ] Implementar transferências entre contas
- [ ] Criar extrato de transações
- [ ] Adicionar limite de crédito para conta corrente
- [ ] Implementar rendimento para conta poupança
- [ ] Adicionar persistência de dados (banco de dados)
- [ ] Criar relatórios gerenciais
- [ ] Implementar múltiplas moedas

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

Desenvolvido como projeto de estudo para aprimoramento em Java e POO.

## 📞 Contato

- GitHub: [@irlan24](https://github.com/irlan24)
- LinkedIn: [Irlan Nonato](https://linkedin.com/in/irlan24)
- Email: irlan.nonato97@hotmail.com

---

⭐ Se este projeto foi útil para você, considere dar uma estrela!

**Última atualização**: Janeiro de 2026
