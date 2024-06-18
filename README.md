# Projeto_Biblioteca_PrograConcorrente

# Sistema de Gerenciamento de Biblioteca

Este repositório tem o cósdigo do projeto que implementa um sistema de gerenciamento de biblioteca que permite listar, alugar, devolver, adicionar e remover livros. O sistema é composto por um cliente e um servidor que se comunicam via sockets. As informações sobre os livros são armazenadas em arquivos JSON.

## Índice

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

## Tecnologias Utilizadas

- Java 17
- Maven
- Eclipse IDE
- Biblioteca Jackson para manipulação de JSON

## Requisitos

Antes de começar, você precisará ter o seguinte instalado em sua máquina:

- Java 17
- Maven
- Eclipse IDE
- Git (para clonar o repositório)
- Os arquivos `livros.json` e `alugados.json` no diretório do projeto

## Instalação

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```

2. Abra o Eclipse IDE.

3. Importe o projeto Maven existente:
    - Vá em **File** > **Import...**
    - Selecione **Existing Maven Projects** e clique em **Next**.
    - Navegue até o diretório do projeto clonado e clique em **Finish**.

4. Certifique-se de que os arquivos `livros.json` e `alugados.json` estão presentes no diretório raiz do projeto. Estes arquivos devem conter a estrutura JSON necessária para o funcionamento do sistema.

### Estrutura dos Arquivos JSON

- `livros.json`: Contém a lista de livros disponíveis na biblioteca.
- `alugados.json`: Contém a lista de livros que foram alugados.

## Uso

### Executando o Servidor

1. No Eclipse, navegue até a classe `Servidor.java` localizada em `src/main/java/teste`.

2. Clique com o botão direito na classe `Servidor.java` e selecione **Run As** > **Java Application**.

### Executando o Cliente

1. No Eclipse, navegue até a classe `Cliente.java` localizada no diretório raiz do projeto.

2. Clique com o botão direito na classe `Cliente.java` e selecione **Run As** > **Java Application**.

### Comandos do Cliente

- `livros`: Lista todos os livros disponíveis na biblioteca.
- `alugar <nome do livro>`: Aluga um livro da biblioteca.
- `meus`: Lista todos os livros alugados pelo usuário.
- `retornar <nome do livro>`: Devolve um livro alugado.
- `adicionar <nome>; <autor>; <genero>; <exemplares>`: Adiciona um novo livro à biblioteca.
- `remover <nome do livro>`: Remove um livro da biblioteca.
- `sair`: Encerra a conexão com o servidor.

## Estrutura do Projeto

- `src/main/java/teste/Servidor.java`: Implementação do servidor que gerencia as operações da biblioteca.
- `src/main/java/teste/BancoDeDados.java`: Classe responsável pela manipulação dos arquivos JSON e operações de CRUD nos livros.
- `src/main/java/teste/Jackson.java`: Classe utilitária para manipulação de JSON usando a biblioteca Jackson.
- `src/main/java/teste/Livro.java`: Classe que representa um livro, contendo atributos como nome, autor, gênero e número de exemplares.
- `Cliente.java`: Implementação do cliente que se conecta ao servidor e envia comandos.

## Contribuindo

Contribuições são bem-vindas! Se você tiver sugestões ou encontrar problemas, por favor, abra uma issue ou envie um pull request.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
