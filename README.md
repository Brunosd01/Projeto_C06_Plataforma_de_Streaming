# Plataforma de Streaming de Vídeo (Java)

##  Integrantes do Projeto

| Nome | Matrícula | Curso |
| :--- | :---: | :---: |
| Bruno Silva Defelicibus | 571 | GES |
| João Victor Batista Alves | 592 | GES |
| Alex de Oliveira Pezzi Vitorino | 589 | GES |
| Gabriel Fonseca Ferreira | 2193 | GEC |

---


Sistema de gerenciamento e simulação de uma plataforma de streaming de vídeo via terminal, desenvolvido em Java para a disciplina de Programação Orientada a Objetos (C06).

| Informações do Projeto | Detalhes |
| :--- | :--- |
| **Disciplina** | C06 – Programação Orientada a Objetos |
| **Linguagem** | Java |
| **Interface** | Terminal / Console (`Scanner`) |
| **Persistência** | Arquivos de Texto (`.txt` via `java.nio`) |

---

## Sobre o Projeto

O projeto consiste em um sistema interativo em terminal que simula as operações de um serviço de streaming de vídeo. A aplicação contempla o gerenciamento de catálogo de filmes e séries, contas de assinantes e administradores, menus de navegação polimórficos, avaliações de mídias e persistência de dados em arquivos físicos de texto.

---

## Conceitos de POO e Recursos Aplicados

- **Herança e Abstração:** Classe abstrata `Conteudo` servindo de base para `Filme` e `Serie`, além da superclasse abstrata `Usuario` para `Assinante` e `Administrador`.
- **Interfaces e Polimorfismo:** Interface `Reproduzivel` implementada na hierarquia de mídias, permitindo chamadas polimórficas para execução de conteúdos.
- **Associações:**
    - **Composição:** Relação entre `Serie` e `Episodio` (instanciação e gerenciamento do ciclo de vida dos episódios mantidos internamente pela série).
    - **Agregação:** Relação entre `Assinante` e `Conteudo` (listas de favoritos e histórico de exibição).
- **Coleções (API Java Collections):** Uso de `HashMap` para busca por chave (e-mail de usuário e ID de mídia) e `ArrayList` para manipulação dinâmica de listas.
- **Tratamento de Exceções:** Exceções customizadas (`LoginInvalidoException`, `ConteudoNaoEncontradoException`) para tratamento de regras de negócio.
- **Persistência de Dados:** Leitura e escrita de arquivos de texto (`Catalogo.txt`, `Assinante.txt`, `Administrador.txt`) organizados no diretório `dados/` através da API `java.nio.file`.

---

## 📁 Estrutura de Pacotes

```text
src/
├── model/                  # Classes de domínio (Conteudo, Filme, Serie, Episodio, Usuario, Assinante, Administrador)
├── service/                # Gerenciadores de negócio e persistência (PlataformaStreaming, GerenciadorArquivos)
├── exception/              # Classes de exceções customizadas
└── Main.java               # Ponto de entrada da aplicação

```

---

## Declaração de Uso de IA

Em conformidade com as exigências da disciplina, o grupo declara formalmente:

- **Utilizou IA?** Sim.
- **Qual ferramenta?** Google Gemini.
- **Como?** O Gemini foi utilizado como assistente técnico na implementação do código em Java. Auxiliou na estruturação da lógica de Programação Orientada a Objetos (POO), implementação dos algoritmos de persistência em arquivos `.txt` com `java.nio.file`, resolução de erros de compilação e pacotes, organização do fluxo dos menus no terminal e formatação do arquivo `README.md`.

---

### Prompts Utilizados:

Como está as relações entre essas classes? parece que a classe Catalago está fazendo algumas funções que fazem mais sentido ser da classe PlataformaStreaming?

como eu implemento essa a List<Episodios> no meu código?

como fica a classe Episodios?

explique melhor esse ponto, e me de alguma sugestão de como implementar uma interface realmente util para essse diagrama?

eu quero que lá no terminal, o usuário possa ter a poção de avaliar se ele quiser o conteúdo e o catalogo. se ele quiser, ele vai simplesmente digitar as notas e dar um enter.

Vamos lidar com a leitura e escrita de arquivos do projeto. Aonde vamos guardar as filmes e suas informações. Onde vamos guardar as contas dos usuários. entre oustros?

Então no meu projeto, eu devo criar mais de um arquivo de texto.txt? cada um para armazenar uma coisa, tipo, um cuida do catalogo e o outro cuida dos usuários?

Posso colocar esses arquivo em uma pasta exclusiva para eles?

Eu gostaria de ter 3 arquivos .txt. um Catalogo.txt, Assinante.txt e um Adiministrador.txt

Quero também que não tenha como criar um Administrador no terminal. No terminal só será possível criar uma conta de usuário. A unica forma de criar um administrador será indo diretamente no arquivo .txt e adicionando manualmente.

Ok, agora vamos começar e trabalhar com o Catalogo, vamos inserir filmes nele, e o usuário responsável por isso será o Administrador.

Como fazer o Administrador adicionar no catalogo um filme ou uma serie?

como fazer para quando o usuário Administrador digitar para entrar na plataforma. ele de fato conseguir?

ok, está funcionando. Agora eu preciso que você me forneça alguns filmes para que eu possa colocar no catalogo

Quando eu tento cadastrar uma serie, percebi que a relação de composição entre a classe serie e a eposodio não esta correta

como eu faço para colocar as informações dos episódios

como ficara a classe administrador?

como salvar as informações referentes aos epesodios das series. e com que o  usuario consiga ver os detalhes da serie(como os epsodios, etc) e os detalhes dos filmes?

o que ajustar na main para conseguir ver os detalhes dos conteudos?

preciso de você faça o catalogo.txt com series e filmes. para que eu possa copiar e colar

eu quero tirar esse case 3 do código da main, e colocar nas classes assinante e administrador

eu quero tirar esse case 3 do código da main, e colocar nas classes assinante e administrador

preciso que o assinante e o adiministrador possam ver detalhes do filmes e das series(Juntamente com as informações do epsodios).

ok, agora está faltando a interface "Avaliável". Eu quero que somente o "Assinante" possa fazer as avaliações. E ele deve fazer duas avaliações: 1 avaliar um conteúdo do catalogo(Essa opção deve aparecer em "case 4:

ótimo, ficou muito legal. porem as informações não estão ficando salvas.

quando estou pedindo para listar o catalogo, ele esta mostrando apenas um titulo, sendo que tem muitos outros a serem mostrados.

================================================================================
HISTÓRICO DE CONVERSAS (CHAT / IA) - PROJETO POO (PLATAFORMA DE STREAMING)
================================================================================

Estou modelando esses projeto deo POO em java e queria ajuda em começar a escrever o código. Como e por onde podemos começar?
A idéia é ser um projeto de POO em java para um trabalho final da faculdade. Onde tenho que seguir as regras conforme essas duas imagens 

Podemos começar pelas etapas 1 e 2. Esqueci de avisar mas threads não vai ser necessario

Avança para a etapa 3

Podemos avançar para a etapa 4

Gostaria de testar a execucao

Algumas considerações, em usuario tem um getSenha, isso não está errado?

Aplica essas correções

Tem um Exception para se o arquivo for deletado?

Pode ser do jeito da opção 2, pois acrescentar coisas que esta fora da UML não é um problema. Desde que siga as instruções posso adicionar coisas

Esta sendo usado interface nesse projeto, que seria um consjunto de metodos publicos e conhecidos?

Acho que esta bom desse jeito na verdade, me parece que isso vai complicar mais ainda

Em plataformaStreaming, para que tem os metodos popularCatalogoPadrao e o popularUsuarioPadrão, sei que é porque esta no UML mas acho que pensando bem eles não fazem sentido, ja que essas informações deveriam estar no arquivo e não no código.

Eu consegui crirar um usuario sem senha, poderia tratar esse erro por favor ?

Ao buscar e apenas apertar enter ele mostra todos os filme, como da para resolver isso?

Nesse código tem alguns problemas um deles é que quando é cadastrado um novo usuario ele não salva dentro da pasta de assinante. Resolva essa situação

Quais campos seriam importantes tratar erros nesse código?

Por enquanto poderia implementar os que já estão ciados? "LoginInvalidoException" e "ConteudoNaoEncontradoException"

Tem algumas formar de tratar erro no Java né, pq que foi usado throws? ao invês de só usar um try catch e por exemplo quais seriam as diferenças para usar o Checked ou Unchecked?

Então por exemplo se eu quero tratar as entradas como por exemplo em "Escolher uma opção:" no menu eu posso usar somente try catch?

Sim, pode adicionar

Esse excepion é nativo do java né? E como eu altero a mensagem aparece se eu quiser. Outra dúvida esses tipos de try catch q foram feitos poderiam ter sido feitos um if else para verifica ao invés o try catch?

Se eu quiser tratar o fato da pessoa não escrever nada ao cadastrar usuario. Primeiro imagino que teria que ser criado outro Exception?

Pode ser a opção 1. Uma coisa que acho q é bom tomar cuidado é tratar antes de inserir o usuario dentro do arquivo texto, se nao ele vai inserir de qualquer forma

A verificação se o email esta com o mesmo que no arquivo é feito onde ?

Quando faço login ainda da erro se digito qualquer coisa ao escolher opção

Em administrador ainda é possivel deixar algumas informações sem nada, ai em ano ele pede independente se eu der o enter, e em minutos tbm. E ele mostra mensagem de que o filme foi adicionado.

O que é esses erros?

Me ajuda a descobrir o que tem de errado no @src/Usuarios/Administrador.java ? Não corrige só aponta o erro, deve ta faltando algum ; ou )

Pelo que vi adicionar ou remover o filme e serie tbm não esta alterando o arquivo. Poderia ajustar?

Eu apertei errado era para ter aceitado

Porque o existe uma instancia nova e pq foi usado private ?

Acho que a autenticação da senha esta sendo feita errada né. Pois ela é facilmente visivel por tem um get o que imagino esta errado.

Mesmo ela tendo protect outra classe conseguiria acessar a senha?

Atualmente essa "pataforma de streaming" esta mais parecendo só um catalogo de series e filmes. Como posso implementar algo ára ficar mais parecido? Não precisa necessariamente que um filme seja reproduzido.[]

Começando pela simulação de reprodução. Mas por enquanto não quero que faça mudanças. A princípio quero entender algumas coisas. A interface Reproduzivel seria uma classe com método reproduzir ? Me explique melhor como seria feito

Sim me de mais detalhes de como seria dentro do método reproduzir()?

Na verdade acho que isso tudo complicaria de mais e para a nossa apresentação não precisa mostrar toda essa interação. Como da para simplificar isso?

Pq não uma classe abstrata ao inves de uma interface na reprodução ?

Uma coisa que acho que usando só o caminho 1 é que não daria para implementar junto um histórico. Pq ele somaria +1 no contador de visualizações

Uma coisa que acho que usando só o caminho 1 é que não daria para implementar junto um histórico. Pq ele somaria +1 no contador de visualizações

Pode fazer essas correções

Poderia me ajudar a tratar os erros de inserção desse código? Não precisa tratar quando a pessoa apenas aperta o enter e fica travado no código. Quero tratar apenas quando inserir algum valor

EU tinha falando antes de colocar um método de reproduzir com você não sei se ainda tem no histórico. Poderia voltar nele?

Quero implementar o reproduzir como metodo da classe abstrata Conteudo, e fazer a parte de histórico apenas no assinate.

Tem algum lugar que consigo pegar o historio e salvar?

Estou falando do chat/ia exporta para um .txt e para um .md dentro da pasta desse projeto mesmo.

Tem como gerar um só com o que foi pedido?

Gere um somente com o texto que eu mandei