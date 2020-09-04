# Test DOCS

Esse software é uma iniciativa para criar uma ferramenta de documentação. Note que, ele não serve para rodar seus testes `automáticos`
, mas sim como uma forma de documentar casos de testes feitos em **caixa preta**. Com o objetivo de transformar a fase de teste 
em um sistema colaborativo e de fácil usabilidade.


## Documentação

- [Documento de Requisitos](./project/requirements.pdf)
- [Diagramas](./project/diagrams)
  - [Use Case](./project/diagrams/useCase.pdf)
  - [Entity relationship model](./project/diagrams/er.pdf)

## Guias de estilos

Neste projeto há uma série de guia de estilos que devem ser seguidas, como:

- [X] Criar issues (template de como criar a issue);
- [X] Criar pull request (template para criar pull request);
- [X] Como assinar PR e issues com labels e pessoas;
- [X] Mensagens de commit;
- [X] Nomes de branches;
- [X] Fluxo de git e github.

Você pode encontrar mais informações no [Guias de Estilos](./.github/CONTRIBUTING.md) do _Test Docs_

## Sobre o projeto

### Requisitos Funcionais
* [ ] O sistema deve prover as operação básicas para os **users**:
	* [ ]  Cadastro de usuário;
	* [ ]  Edição de usuário logado;
	* [ ]  Remoção do usuário logado.
* [ ] Cada **user** irá ter: 
	- `email*`;
	- `password*`;
	-  `name*`;
	- `office*`;
	- `avatar_uri`;
	-  `description`;
	-  `github_uri`.
* [ ] **Users** poderão criar, editar e deletar **projects**;
* [ ] Cada **project** pode ter _n_ **users**, porém apenas um é o dono do projeto, sendo assim o dono pode adicionar e remover **users** de um **project**;
* [ ] Cada **project** irá ter:
	- `name*`;
	- `description`;
	- `avatar_uri`;
	- `owner_id*`;
	- `github_repo_uri`.
* [ ] Cada **project** pode ter _n_ **test_request**, e cada **test_request** tem:
	- `target_version*`;
	- `title`;
	- `description`;
	- `request_user_id`;
	- `tester_user_id`;
	- `status`.
* [ ] Cada **release_tests** pode ter _n_ **test_suites**, e cada **test_suites** tem:
	- `title`;
	- `description`;
	- `report`;
	- `image_report`.
* [ ] Cada **test_suites** pode ter _n_ **test_cases**, e cada **test_cases** tem:
	- `title`;
	- `description`;
	- `status`;
	- `image_description`;
	- `report`;
	- `image_report`;
	- `tasks_id`.
* [ ] Cada **release_tests** pode ter _n_ **tasks**, e cada **tasks** tem:
	- `title`;
	- `type`;
	- `status`.

### Requisitos de Negócio

* [ ] Ao criar uma **release_tests** tem que informar qual o **user** que irá fazer os teste;
* [ ] O **user** que irá fazer os testes é o único que pode alterar informações dentro de uma **release_tests**.
* [ ] Ao selecionar o **user** este deve ser notificado tanto ao entrar no tela principal do sistema como no e-mail.
* [ ] Caso exista um repositório do github associado deve carregar as informações da tag para a **release_tests**.
* [ ] O sistema deve informar se a versão foi aprovada, indicando os pontos reprovados (caso haja) em acordo com as entradas fornecidas pelo **user** que está testando.