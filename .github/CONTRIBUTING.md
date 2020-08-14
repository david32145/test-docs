# Guias de estilo do Test Docs

## Sumário
- [Como criar sua issue](#como-criar-sua-issue);
- [Como enviar seu código para o github](#como_enviar_seu_código_para_o_github)
  - [Fluxo git e github](#fluxo_git_e_github);
  - [Nome das branch](#nome_das_branch);
  - [Messagem de commit](#messagem_de_commit).
- [Liberando versão para a stage](#liberando_versão_para_a_stage);
- [Liberando versão para master](#liberando_versão_para_master).

### Como criar sua issue

Primeiro entre na aba de issues e escolha um template, dentre os quais estão disponíveis:

- Nova Funcionalidade (Para inserção de nova funcionalidade);
- Documentação (Para adição ou melhora de documentação);
- Melhora de funcionalidade (Para melhora de uma funcionalidade existente);
- Bug encontrado (Para reportar um novo problema).

Depois de escolher o template preencha com as informações relacionada com aquela issue.

Depois disso relacione essa issue com os labels do github, para que o escopo da issue fique bem definido.

Associe também com o milestone atual.

Lembrando que a pessoa que cria a issue não necessariamente deve resolve-la.

Porém, se você escolheu uma issue para resolver deve assina-la como sua tarefa, para que os outros saibam que você está fazendo a issue _X_ e assim não ter duas pessoas fazendo a mesma coisa sem saber.

### Como enviar seu código para o github

Antes de enviar o código é necessário explicar como funciona o fluxo git e github.

#### Fluxo git e github

Neste projeto existem 3 branches principais e que não podem receber commit diretamente pelo terminal, elas são:

**master**: Na _master_ existe o código principal que já foi testado e está pronto para ser apresentado sem bugs ou qualquer outro tipo de problema.

**stage**: A branch _stage_ é puxada a partir da _master_ e é nela que está o código que está pronto para ir para a _master_ mas não foi testado ou não há garantia que vai funcionar tudo bem, toda vez que uma versão é gerada o código vai para a _stage_ para só depois ser para a _master_.

**development**: A branch _development_ é puxada a partir da _stage_ e é nessa branch que as novas funcionalidades são inseridas, como próprio nome sugere é a branch de desenvolvimento.

Como dito acima, essas branches não podem receber commit diretamente, para isso é necessário criar branches paralelas a _development_, basta entrar na _development_ e criar suas branches a partir dela.

#### Nome das branch

Existe uma regra para criar o nome das branches, as regras são as senguinte:

_git checkout -b x_ cria a branch _x_ e entra nela a partir da atual.

- **docs/diagram_use_case**: Para branch onde vai alterar coisas relacionados a documentação;
- **feat/login_jwt**: Para branch onde vai criar novas funcionalidades;
- **chore/login_with_email_passoword**: Para melhora de funcionalidade;
- **fix/browser_explode**: Para conserto de bugs.

Lembrando que pelo menos o nome das branches deve ser em inglês.

#### Messagem de commit

Depois que você terminou sua tarefa é necessário fazer o commit, a messagem de commit deve seguir o padrão abaixo.

```md
nome_da_branch: descrição da tarefa que você fez [30 caracteres no máximo]

[Optional] descrição mais detalhada e opicional.

link das issues que estão relacionado com essa tarefa
```

Exemplo

```md
docs/diagram_use_case: Adicionado diagrama de caso de uso

closes #1, closes #2.
```

Depois de fazer o commit é necessário fazer o pull request, para fazer a pull request seleciona a branch de origin a sua branch e a de destino a _development_. 

Agora basta prencher o template e marcar os demais integrantes da equipe para fazer a revisão, se tudo ocorrer bem o código vai para a _development_.

### Liberando versão para stage

Quando a nova versão estiver pronta alguém vai criar uma pull request da _development_ para a _stage_ e depois alguém vai se encarregar de testar todas as funcionalidades.

### Liberando versão para master

Quando a versão da _stage_ for aprovada uma pull request será feita para a _master_ e assim temos uma versão que pode ser entregue.