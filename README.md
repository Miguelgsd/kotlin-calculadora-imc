# Calculadora de IMC

## Descrição
Aplicativo Android desenvolvido em Jetpack Compose que permite realizar o cálculo do Índice de Massa Corporal (IMC) utilizando o peso e altura do usuário. Possui dois campos de entrada para inserção dos valores, um switch que permite o arredondamento do valor do IMC, um botão que chama a função responsável pelo cálculo e a amostra do resultado na tela.

## Tecnologias utilizadas
- **Linguagem:** Kotlin
- **Toolkit de UI:** Jetpack Compose (Material 3)
- **IDE:** Android Studio Otter 2 Feature Drop | 2025.2.2 Patch 1
- **Switch:** androidx.compose.material3.Switch

## Como compilar e executar o projeto
1. Clone o repositório:
```bash
   git clone https://github.com/Miguelgsd/kotlin-calculadora-imc
```
2. Abra o Android Studio.
3. Selecione **Open** e escolha a pasta do projeto clonado.
4. Aguarde a sincronização do Gradle.
5. Certifique-se de ter um emulador configurado ou um dispositivo físico conectado com depuração USB ativada.
6. Clique em **Run** para compilar e executar o app no emulador/dispositivo selecionado.

## Descrição dos composables
`MainActivity.onCreate`: Ponto de entrada do app. Ativa o modo edge-to-edge e define o conteúdo da tela via `setContent`, aplicando o tema `CalculadoraImcTheme` e chamando o composable `CalcLayout`.  
`CalcLayout`: Composable raiz da tela. Organiza verticalmente (`Column`) os textos e campos de entrada, centralizando tudo horizontalmente.  
`EditField`: Função reutilizável para criar os campos de entrada para `peso` e `altura`.  
`ArredondarImc`: Cria o switch na tela responsável por permitir ou não o arredondamento do IMC de acordo com a vontade do usuário, organizando o texto e o switch em uma row.  
`Classificar`:  Permite classificar os valores do IMC em "Abaixo do Peso", "Peso Normal", "Sobrepeso" e "Obesidade" utilizando faixas de valores e comparando com `when`.  

## Diagrama da hierarquia dos composables
```
CalcLayout
   |-- Column
	|-- Text
	|-- EditField
	|-- EditField
	|-- Text
	|-- Text
	|-- ArredondarImc
	|-- Button

EditField
    |-- TextField

ArredondarImc
    |-- Row
	|-- Text
	|-- Switch

Classificar
    |-- When

```
## Aluno
- Miguel Garcia de Souza Dutra, 3° Período
