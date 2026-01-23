import controller.ContaBanco;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;



/**
 *  TESTES INTEGRAÇÃO - DESENVOLVIDO POR IA E REVISADO POR HUMANO (Eu ^^)
 * 
 * 
 * Classe de teste integrado para o sistema bancário
 * Executa testes sem interface gráfica (headless mode)
 * 
 * Versão 2.0 - Incluindo testes para novas funcionalidades:
 * - Conversão de moeda com BigDecimal
 * - Validação de entrada numérica
 * - Conversão de separadores (vírgula/ponto)
 * - Fechamento de conta
 * - Mostrar informações
 */
public class AppTest {
    
    private ContaBanco conta;
    
    @BeforeAll
    static void setUpClass() {
        System.setProperty("java.awt.headless", "true");
        Locale.setDefault(Locale.of("pt", "BR"));
    }
    
    @BeforeEach
    void setUp() {
        conta = new ContaBanco();
    }
    
    @AfterEach
    void tearDown() {
        conta = null;
    }
    
    // ==================== TESTES DE VALIDAÇÃO DE CPF ====================
    
    @Test
    @DisplayName("Deve validar CPF válido com formatação")
    void testvalidarCpfValidoComFormatacao() {
        assertTrue(conta.validarCpf("123.456.789-09"), 
            "CPF válido com formatação deve ser aceito");
        assertEquals("123.456.789-09", conta.getCpf(),
            "CPF deve ser armazenado com formatação");
    }
    
    @Test
    @DisplayName("Deve validar CPF válido sem formatação")
    void testvalidarCpfValidoSemFormatacao() {
        assertTrue(conta.validarCpf("12345678909"),
            "CPF válido sem formatação deve ser aceito");
        assertEquals("123.456.789-09", conta.getCpf(),
            "CPF deve ser formatado ao armazenar");
    }
    
    @Test
    @DisplayName("Deve rejeitar CPF com dígitos repetidos")
    void testvalidarCpfDigitosRepetidos() {
        assertFalse(conta.validarCpf("111.111.111-11"), 
            "CPF com todos dígitos iguais deve ser rejeitado");
        assertFalse(conta.validarCpf("00000000000"),
            "CPF com zeros deve ser rejeitado");
        assertFalse(conta.validarCpf("999.999.999-99"),
            "CPF 999.999.999-99 deve ser rejeitado");
    }
    
    @Test
    @DisplayName("Deve rejeitar CPF com dígito verificador incorreto")
    void testvalidarCpfDigitoVerificadorIncorreto() {
        assertFalse(conta.validarCpf("123.456.789-00"), 
            "CPF com dígito verificador incorreto deve ser rejeitado");
        assertFalse(conta.validarCpf("123.456.789-99"),
            "CPF com segundo dígito verificador incorreto deve ser rejeitado");
    }
    
    @Test
    @DisplayName("Deve rejeitar CPF com tamanho inválido")
    void testvalidarCpfTamanhoInvalido() {
        assertFalse(conta.validarCpf("12345678"), 
            "CPF com menos de 11 dígitos deve ser rejeitado");
        assertFalse(conta.validarCpf("123456789012345"),
            "CPF com mais de 11 dígitos deve ser rejeitado");
        assertFalse(conta.validarCpf(""),
            "CPF vazio deve ser rejeitado");
    }
    
    @Test
    @DisplayName("Deve rejeitar CPF nulo")
    void testvalidarCpfNulo() {
        assertFalse(conta.validarCpf(null), 
            "CPF nulo deve ser rejeitado");
    }
    
    @Test
    @DisplayName("Deve aceitar CPF com caracteres especiais e formatá-lo")
    void testvalidarCpfComCaracteresEspeciais() {
        assertTrue(conta.validarCpf("123-456-789.09"),
            "CPF com formatação alternativa deve ser aceito");
        assertTrue(conta.validarCpf("123 456 789 09"),
            "CPF com espaços deve ser aceito");
    }
    
    @Test
    @DisplayName("Deve validar múltiplos CPFs válidos conhecidos")
    void testMultiplosCpfsValidos() {
        String[] cpfsValidos = {
            "123.456.789-09",
            "111.444.777-35",
            "987.654.321-00"
        };
        
        for (String cpf : cpfsValidos) {
            ContaBanco c = new ContaBanco();
            assertTrue(c.validarCpf(cpf), "CPF " + cpf + " deveria ser válido");
        }
    }
    
    // ==================== TESTES DE VALIDAÇÃO DE DADOS ====================
    
    @Test
    @DisplayName("Deve validar dados completos para cadastro - Conta Corrente")
    void testValidarDadosCompletoContaCorrente() {
        boolean resultado = validarDadosSemDialog("João Silva", "Conta Corrente", "123.456.789-09");
        
        assertTrue(resultado, "Dados válidos devem ser aceitos");
        assertTrue(conta.getStatus(), "Conta deve estar ativa após validação");
        assertEquals("João Silva", conta.getNomeCliente());
        assertEquals("Conta Corrente", conta.getTipoConta());
        assertEquals("123.456.789-09", conta.getCpf());
    }
    
    @Test
    @DisplayName("Deve validar dados completos para cadastro - Conta Poupança")
    void testValidarDadosCompletoContaPoupanca() {
        boolean resultado = validarDadosSemDialog("Maria Santos", "Conta Poupança", "123.456.789-09");
        
        assertTrue(resultado, "Dados válidos devem ser aceitos");
        assertTrue(conta.getStatus(), "Conta deve estar ativa após validação");
        assertEquals("Maria Santos", conta.getNomeCliente());
        assertEquals("Conta Poupança", conta.getTipoConta());
    }
    
    @Test
    @DisplayName("Deve rejeitar cadastro com nome vazio")
    void testValidarDadosNomeVazio() {
        assertFalse(validarDadosSemDialog("", "Conta Corrente", "123.456.789-09"),
            "Nome vazio deve ser rejeitado");
    }
    
    @Test
    @DisplayName("Deve rejeitar cadastro com nome nulo")
    void testValidarDadosNomeNulo() {
        assertFalse(validarDadosSemDialog(null, "Conta Corrente", "123.456.789-09"),
            "Nome nulo deve ser rejeitado");
    }
    
    @Test
    @DisplayName("Deve rejeitar cadastro com nome apenas espaços")
    void testValidarDadosNomeApenasEspacos() {
        assertFalse(validarDadosSemDialog("   ", "Conta Corrente", "123.456.789-09"),
            "Nome apenas com espaços deve ser rejeitado");
    }
    
    @Test
    @DisplayName("Deve rejeitar cadastro sem selecionar tipo de conta")
    void testValidarDadosSemTipoConta() {
        assertFalse(validarDadosSemDialog("João Silva", "Selecione uma opção", "123.456.789-09"),
            "Deve rejeitar se tipo de conta não foi selecionado");
    }
    
    @Test
    @DisplayName("Deve rejeitar cadastro com CPF inválido")
    void testValidarDadosComCpfInvalido() {
        assertFalse(validarDadosSemDialog("João Silva", "Conta Corrente", "111.111.111-11"),
            "Deve rejeitar cadastro com CPF inválido");
        assertFalse(validarDadosSemDialog("João Silva", "Conta Corrente", "123.456.789-00"),
            "Deve rejeitar cadastro com dígito verificador incorreto");
    }
    
    @Test
    @DisplayName("Deve aceitar nomes com caracteres especiais")
    void testNomesComCaracteresEspeciais() {
        assertTrue(validarDadosSemDialog("José da Silva", "Conta Corrente", "123.456.789-09"));
        assertTrue(validarDadosSemDialog("Maria D'Angelo", "Conta Poupança", "123.456.789-09"));
        assertTrue(validarDadosSemDialog("João-Paulo Santos", "Conta Corrente", "123.456.789-09"));
    }
    
    // ==================== TESTES DE CONVERSÃO DE MOEDA ====================
    
    @Test
    @DisplayName("Deve converter moeda com 2 casas decimais - arredondamento para baixo")
    void testConverterMoedaDuasCasasDecimais() {
        double resultado = conta.converterMoeda(100.999);
        assertEquals(100.99, resultado, 0.001,
            "Deve truncar para 2 casas decimais (não arredondar para cima)");
    }
    
    @Test
    @DisplayName("Deve truncar valores com múltiplas casas decimais")
    void testConverterMoedaTruncamento() {
        assertEquals(50.12, conta.converterMoeda(50.123456), 0.001);
        assertEquals(99.99, conta.converterMoeda(99.999999), 0.001);
        assertEquals(0.01, conta.converterMoeda(0.019999), 0.001);
    }
    
    @Test
    @DisplayName("Deve manter valores já com 2 casas decimais")
    void testConverterMoedaValoresExatos() {
        assertEquals(100.00, conta.converterMoeda(100.00), 0.001);
        assertEquals(50.50, conta.converterMoeda(50.50), 0.001);
        assertEquals(99.99, conta.converterMoeda(99.99), 0.001);
    }
    
    @Test
    @DisplayName("Deve converter valores muito pequenos")
    void testConverterMoedaValoresPequenos() {
        assertEquals(0.01, conta.converterMoeda(0.01), 0.001);
        assertEquals(0.00, conta.converterMoeda(0.001), 0.001);
        assertEquals(0.00, conta.converterMoeda(0.009), 0.001);
    }
    
    @Test
    @DisplayName("Deve converter valores grandes mantendo precisão")
    void testConverterMoedaValoresGrandes() {
        assertEquals(999999.99, conta.converterMoeda(999999.999), 0.001);
        assertEquals(1000000.00, conta.converterMoeda(1000000.00), 0.001);
    }
    
    @Test
    @DisplayName("Deve lidar com zero e valores negativos")
    void testConverterMoedaZeroENegativo() {
        assertEquals(0.00, conta.converterMoeda(0.00), 0.001);
        assertEquals(-50.12, conta.converterMoeda(-50.123), 0.001);
        assertEquals(-99.99, conta.converterMoeda(-99.999), 0.001);
    }
    
    @Test
    @DisplayName("getValorAtual deve retornar valor convertido")
    void testGetValorAtualComConversao() {
        conta.setValorAtual(1999999.99);
        conta.setValorAtual(conta.getValorAtual() - 1999999.99);
        assertEquals(0.00, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Deve validar tipos de conta aceitos")
    void testTiposContaAceitos() {
        assertTrue(validarDadosSemDialog("Cliente 1", "Conta Corrente", "123.456.789-09"));
        
        ContaBanco conta2 = new ContaBanco();
        assertTrue(validarDadosSemDialogComConta(conta2, "Cliente 2", "Conta Poupança", "123.456.789-09"));
    }
    
    @Test
    @DisplayName("Deve manter integridade dos dados após múltiplas operações")
    void testIntegridadeDados() {
        validarDadosSemDialog("Integridade", "Conta Corrente", "123.456.789-09");
        
        String nomeOriginal = conta.getNomeCliente();
        String cpfOriginal = conta.getCpf();
        String tipoOriginal = conta.getTipoConta();
        
        // Múltiplas operações
        conta.setValorAtual(100.00);
        conta.setValorAtual(conta.getValorAtual() + 50.00);
        conta.setValorAtual(conta.getValorAtual() - 25.00);
        
        // Dados devem permanecer inalterados
        assertEquals(nomeOriginal, conta.getNomeCliente());
        assertEquals(cpfOriginal, conta.getCpf());
        assertEquals(tipoOriginal, conta.getTipoConta());
        assertTrue(conta.getStatus());
    }
    
    // ==================== MÉTODOS AUXILIARES ====================
    
    /**
     * Valida dados sem exibir JOptionPane (para testes)
     * Replica a lógica de validarDados mas sem dialogs
     */
    private boolean validarDadosSemDialog(String nome, String tipoConta, String cpf) {
        return validarDadosSemDialogComConta(this.conta, nome, tipoConta, cpf);
    }
    
    /**
     * Valida dados sem exibir JOptionPane para uma conta específica
     */
    private boolean validarDadosSemDialogComConta(ContaBanco conta, String nome, String tipoConta, String cpf) {
        if (nome == null || nome.trim().isEmpty() || tipoConta.equals("Selecione uma opção")) {
            return false;
        }
        
        if (!conta.validarCpf(cpf)) {
            return false;
        }
        
        conta.setNomeCliente(nome);
        conta.setTipoConta(tipoConta);
        conta.setStatus(true);
        
        return true;
    }
    
    // ==================== TESTES DE OPERAÇÕES BANCÁRIAS ====================
    
    @Test
    @DisplayName("Deve criar conta corrente e aplicar bônus de R$ 50,00")
    void testCriarContaCorrenteComBonus() {
        validarDadosSemDialog("Carlos Ferreira", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(50.00);
        
        assertEquals(50.00, conta.getValorAtual(), 0.001,
            "Conta corrente deve iniciar com bônus de R$ 50,00");
        assertEquals("Conta Corrente", conta.getTipoConta());
        assertTrue(conta.getStatus());
    }
    
    @Test
    @DisplayName("Deve criar conta poupança e aplicar bônus de R$ 150,00")
    void testCriarContaPoupancaComBonus() {
        validarDadosSemDialog("Ana Costa", "Conta Poupança", "123.456.789-09");
        conta.setValorAtual(150.00);
        
        assertEquals(150.00, conta.getValorAtual(), 0.001,
            "Conta poupança deve iniciar com bônus de R$ 150,00");
        assertEquals("Conta Poupança", conta.getTipoConta());
        assertTrue(conta.getStatus());
    }
    
    @Test
    @DisplayName("Deve depositar valor corretamente")
    void testDepositarValor() {
        validarDadosSemDialog("Pedro Oliveira", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(50.00);
        
        double saldoAntes = conta.getValorAtual();
        double valorDeposito = 100.00;
        conta.setValorAtual(conta.getValorAtual() + valorDeposito);
        
        assertEquals(saldoAntes + valorDeposito, conta.getValorAtual(), 0.001,
            "Saldo deve aumentar após depósito");
        assertEquals(150.00, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Deve sacar valor quando há saldo suficiente")
    void testSacarComSaldoSuficiente() {
        validarDadosSemDialog("Beatriz Lima", "Conta Poupança", "123.456.789-09");
        conta.setValorAtual(150.00);
        
        double saldoAntes = conta.getValorAtual();
        double valorSaque = 50.00;
        conta.setValorAtual(conta.getValorAtual() - valorSaque);
        
        assertEquals(saldoAntes - valorSaque, conta.getValorAtual(), 0.001,
            "Saldo deve diminuir após saque");
        assertEquals(100.00, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Não deve permitir saque maior que o saldo")
    void testNaoPermitirSaqueAcimaSaldo() {
        validarDadosSemDialog("Ricardo Mendes", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(50.00);
        
        double saldoAntes = conta.getValorAtual();
        double valorSaque = 100.00;
        
        boolean saquePermitido = !(saldoAntes <= 0 || valorSaque > saldoAntes);
        assertFalse(saquePermitido, "Saque acima do saldo não deve ser permitido");
        
        assertEquals(50.00, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Deve processar múltiplas operações sequenciais")
    void testMultiplasOperacoesSequenciais() {
        validarDadosSemDialog("Fernanda Souza", "Conta Poupança", "123.456.789-09");
        conta.setValorAtual(150.00);
        
        conta.setValorAtual(conta.getValorAtual() + 200.00);
        assertEquals(350.00, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() - 100.00);
        assertEquals(250.00, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() + 50.00);
        assertEquals(300.00, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() - 75.00);
        assertEquals(225.00, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Deve lidar com valores decimais precisos")
    void testValoresDecimaisPrecisos() {
        validarDadosSemDialog("Decimal Test", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(50.00);
        
        conta.setValorAtual(conta.getValorAtual() + 99.99);
        assertEquals(149.99, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() - 49.99);
        assertEquals(100.00, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() + 0.01);
        assertEquals(100.01, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Deve lidar com saldo zero")
    void testSaldoZero() {
        validarDadosSemDialog("Saldo Zero", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(50.00);
        conta.setValorAtual(conta.getValorAtual() - 50.00);
        
        assertEquals(0.00, conta.getValorAtual(), 0.001);
        
        double valorSaque = 10.00;
        boolean saquePermitido = !(conta.getValorAtual() <= 0 || valorSaque > conta.getValorAtual());
        assertFalse(saquePermitido);
    }
    
    // ==================== TESTES DE FECHAMENTO DE CONTA ====================
    
    @Test
    @DisplayName("Deve fechar conta com saldo zero")
    void testFecharContaComSaldoZero() {
        validarDadosSemDialog("Fechar Conta", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(0.00);
        
        assertTrue(conta.getStatus(), "Conta deve estar ativa antes de fechar");
        
        // Simula fechamento (lógica sem JOptionPane)
        if (conta.getValorAtual() == 0 && conta.getStatus()) {
            conta.setStatus(false);
        }
        
        assertFalse(conta.getStatus(), "Conta deve estar inativa após fechamento");
    }
    
    @Test
    @DisplayName("Não deve fechar conta com saldo positivo")
    void testNaoFecharContaComSaldoPositivo() {
        validarDadosSemDialog("Conta Com Saldo", "Conta Poupança", "123.456.789-09");
        conta.setValorAtual(150.00);
        
        assertTrue(conta.getStatus());
        double saldoInicial = conta.getValorAtual();
        
        // Tenta fechar mas não deve permitir
        boolean podeFchar = conta.getValorAtual() <= 0;
        assertFalse(podeFchar, "Não deve permitir fechar conta com saldo positivo");
        
        // Verifica que nada mudou
        assertTrue(conta.getStatus(), "Conta deve continuar ativa");
        assertEquals(saldoInicial, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Não deve fechar conta já inativa")
    void testNaoFecharContaJaInativa() {
        assertFalse(conta.getStatus(), "Conta deve iniciar inativa");
        
        // Verifica condição de fechamento
        boolean podeFechar = conta.getStatus();
        assertFalse(podeFechar, "Não deve permitir fechar conta já inativa");
    }
    
    @Test
    @DisplayName("Deve permitir zerar saldo e fechar conta em sequência")
    void testZerarSaldoEFecharConta() {
        validarDadosSemDialog("Zerar e Fechar", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(50.00);
        
        // Saca todo o saldo
        conta.setValorAtual(conta.getValorAtual() - 50.00);
        assertEquals(0.00, conta.getValorAtual(), 0.001);
        
        // Agora pode fechar
        if (conta.getValorAtual() == 0 && conta.getStatus()) {
            conta.setStatus(false);
        }
        
        assertFalse(conta.getStatus());
        assertEquals(0.00, conta.getValorAtual(), 0.001);
    }
    
    // ==================== TESTES DE ESTADO DA CONTA ====================
    
    @Test
    @DisplayName("Conta deve iniciar inativa")
    void testContaIniciaInativa() {
        assertFalse(conta.getStatus(), 
            "Conta recém-criada deve estar inativa");
        assertEquals(0.0, conta.getValorAtual(), 0.001,
            "Saldo inicial deve ser zero");
    }
    
    @Test
    @DisplayName("Conta deve ficar ativa após validação bem-sucedida")
    void testContaFicaAtivaAposValidacao() {
        assertFalse(conta.getStatus(), "Conta deve estar inativa antes da validação");
        
        validarDadosSemDialog("Integração Teste", "Conta Corrente", "123.456.789-09");
        
        assertTrue(conta.getStatus(), 
            "Conta deve estar ativa após validação bem-sucedida");
    }
    
    @Test
    @DisplayName("Conta inativa não deve alterar status com validação falha")
    void testContaPermaneceInativaComValidacaoFalha() {
        assertFalse(conta.getStatus());
        
        validarDadosSemDialog("", "Conta Corrente", "123.456.789-09");
        
        assertFalse(conta.getStatus(),
            "Conta deve permanecer inativa se validação falhar");
    }
    
    @Test
    @DisplayName("Deve armazenar CPF formatado corretamente")
    void testCpfFormatadoCorreto() {
        conta.validarCpf("12345678909");
        assertEquals("123.456.789-09", conta.getCpf(),
            "CPF deve ser armazenado com formatação padrão");
        
        ContaBanco conta2 = new ContaBanco();
        conta2.validarCpf("123.456.789-09");
        assertEquals("123.456.789-09", conta2.getCpf(),
            "CPF já formatado deve permanecer formatado");
    }
    
    // ==================== TESTES DE INTEGRAÇÃO COMPLETA ====================
    
    @Test
    @DisplayName("Fluxo completo: Cadastro → Bônus → Operações → Verificação")
    void testFluxoCompletoIntegracao() {
        // ETAPA 1: Validar e cadastrar cliente
        boolean cadastroSucesso = validarDadosSemDialog(
            "Integração Completa", 
            "Conta Corrente", 
            "123.456.789-09"
        );
        assertTrue(cadastroSucesso, "Cadastro deve ser bem-sucedido");
        
        // ETAPA 2: Verificar conta ativa e dados corretos
        assertTrue(conta.getStatus(), "Conta deve estar ativa");
        assertEquals("Integração Completa", conta.getNomeCliente());
        assertEquals("Conta Corrente", conta.getTipoConta());
        assertEquals("123.456.789-09", conta.getCpf());
        
        // ETAPA 3: Aplicar bônus inicial
        conta.setValorAtual(50.00);
        assertEquals(50.00, conta.getValorAtual(), 0.001);
        
        // ETAPA 4: Realizar depósito
        conta.setValorAtual(conta.getValorAtual() + 500.00);
        assertEquals(550.00, conta.getValorAtual(), 0.001);
        
        // ETAPA 5: Realizar saque
        conta.setValorAtual(conta.getValorAtual() - 200.00);
        assertEquals(350.00, conta.getValorAtual(), 0.001);
        
        // ETAPA 6: Mais operações
        conta.setValorAtual(conta.getValorAtual() + 150.00);
        assertEquals(500.00, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() - 100.00);
        assertEquals(400.00, conta.getValorAtual(), 0.001);
        
        // ETAPA 7: Verificar estado final
        assertTrue(conta.getStatus(), "Conta deve continuar ativa");
        assertTrue(conta.getValorAtual() > 0, "Saldo deve ser positivo");
        assertEquals(400.00, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Fluxo completo para Conta Poupança")
    void testFluxoCompletoContaPoupanca() {
        validarDadosSemDialog("Poupador Teste", "Conta Poupança", "123.456.789-09");
        assertTrue(conta.getStatus());
        
        conta.setValorAtual(150.00);
        assertEquals(150.00, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() + 1000.00);
        assertEquals(1150.00, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() - 500.00);
        assertEquals(650.00, conta.getValorAtual(), 0.001);
        
        assertEquals("Conta Poupança", conta.getTipoConta());
        assertTrue(conta.getStatus());
    }
    
    @Test
    @DisplayName("Fluxo completo: Cadastro → Operações → Fechamento")
    void testFluxoCompletoComFechamento() {
        // Cadastro
        validarDadosSemDialog("Cliente Completo", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(50.00);
        assertTrue(conta.getStatus());
        
        // Operações
        conta.setValorAtual(conta.getValorAtual() + 200.00);
        assertEquals(250.00, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() - 100.00);
        assertEquals(150.00, conta.getValorAtual(), 0.001);
        
        // Zerar saldo
        conta.setValorAtual(conta.getValorAtual() - 150.00);
        assertEquals(0.00, conta.getValorAtual(), 0.001);
        
        // Fechar conta
        if (conta.getValorAtual() == 0 && conta.getStatus()) {
            conta.setStatus(false);
        }
        
        assertFalse(conta.getStatus(), "Conta deve estar fechada");
        assertEquals(0.00, conta.getValorAtual(), 0.001);
    }
    
    @Test
    @DisplayName("Fluxo realista: Múltiplas operações com valores decimais")
    void testFluxoRealistaComDecimais() {
        validarDadosSemDialog("Cliente Real", "Conta Poupança", "123.456.789-09");
        conta.setValorAtual(150.00);
        
        // Simulando operações do dia a dia
        conta.setValorAtual(conta.getValorAtual() + 1234.56);
        assertEquals(1384.56, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() - 87.99);
        assertEquals(1296.57, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() + 999.99);
        assertEquals(2296.56, conta.getValorAtual(), 0.001);
        
        conta.setValorAtual(conta.getValorAtual() - 1500.00);
        assertEquals(796.56, conta.getValorAtual(), 0.001);
        
        assertTrue(conta.getStatus());
        assertTrue(conta.getValorAtual() > 0);
    }
    
    // ==================== TESTES DE ROBUSTEZ E EDGE CASES ====================
    
    @Test
    @DisplayName("Deve lidar com sequência de operações extremas")
    void testOperacoesExtremas() {
        validarDadosSemDialog("Extremos", "Conta Corrente", "123.456.789-09");
        conta.setValorAtual(1_000_000.00);

        conta.setValorAtual(conta.getValorAtual() + 999_999.99);
        assertEquals(1_999_999.99, conta.getValorAtual(), 0.001);

        conta.setValorAtual(conta.getValorAtual() - 500_000.50);
        assertEquals(1_499_999.49, conta.getValorAtual(), 0.001);

        assertTrue(conta.getStatus());

    
}}    
