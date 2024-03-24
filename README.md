# streaming-api
API REST para uma plataforma de streaming de música

## Introdução

A API foi desenvolvida para atender aos requisitos de uma plataforma de streaming de música em crescimento, oferecendo endpoints para adicionar, listar, atualizar e excluir músicas da biblioteca.

## Base URL

A URL base da API é: `http://localhost:8080` (ou substitua por sua URL de produção).

## Endpoints

A seguir estão os endpoints disponíveis na API:

### Listar Todas as Músicas

GET /musics

Retorna uma lista de todas as músicas na biblioteca.

### Obter uma Música por ID

GET /musics/{id}


Retorna os detalhes de uma música específica com o ID fornecido.

### Adicionar uma Nova Música

POST /musics


Adiciona uma nova música à biblioteca.

### Atualizar uma Música Existente

PUT /musics/{id}


Atualiza os detalhes de uma música existente com o ID fornecido.

### Excluir uma Música

DELETE /musics/{id}


Exclui uma música da biblioteca com o ID fornecido.

## Parâmetros

### Música (JSON)

- `title` (string): Título da música.
- `artist` (string): Artista da música.
- `genre` (string): Gênero da música.
- `duration` (integer): Duração da música em segundos.

## Exemplos de Requisições

Aqui estão alguns exemplos de como fazer requisições usando a API:

### Exemplo de Requisição para Adicionar uma Nova Música

## O que o sistema retorna no json
POST /musics

{
    "title": "Nome da Música",
    "artist": "Nome do Artista",
    "genre": "Gênero Musical",
    "duration": 240
}

## Status Codes
A API retorna os seguintes códigos de status HTTP:

200 OK: Requisição bem-sucedida.
201 Created: Ação concluída com sucesso.
204 No Content: Ação concluída com sucesso, sem conteúdo retornado.
400 Bad Request: Requisição inválida ou malformada.
404 Not Found: Recurso não encontrado.
500 Internal Server Error: Erro interno do servidor.