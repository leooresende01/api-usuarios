
# Usuarios

- [Usuarios](#usuarios)
	- [Endpoints](#endpoints)
		- [***1. Buscar todos os usuários registrados***](#1-buscar-todos-os-usuários-registrados)
		- [***2. Registrar um usuario***](#2-registrar-um-usuario)
		- [***3. Atualizar informações de um usuário***](#3-atualizar-informações-de-um-usuário)
		- [***4. Deletar um usuário***](#4-deletar-um-usuário)
		- [***5. Buscar um unico usuário registrado***](#5-buscar-um-unico-usuário-registrado)
		- [***6. Validar e-mail usando o código***](#6-validar-e-mail-usando-o-código)



## Endpoints


--------



### ***1. Buscar todos os usuários registrados***


Busca todos os usuários registrados.


***Endpoint:***

```bash
Method: GET
Auth Type: none
URL: {{host}}/api/v1/usuarios
```

### ***2. Registrar um usuario***


Registra um novo usuário caso o nome de usuário ou e-mail não esteja sendo utilizado, enviando um código de verificação pelo e-mail para verificar se ele é valido.


***Endpoint:***

```bash
Method: POST
Type: RAW/JSON
Auth Type: none
URL: {{host}}/api/v1/usuarios
```



***Body:***

```js        
{
    "username": "{{username}}",
    "password": "{{password}}",
    "nomeCompleto": "{{nomeCompleto}}",
    "email": "{{email}}"
}
```




### ***3. Atualizar informações de um usuário*** 


Atualiza as informações de um usuário, caso o novo e-mail ou nome de usuário novo não esteja sendo utilizado, enviando um código de verificação pelo novo e-mail para verificar se ele é valido.


***Endpoint:***

```bash
Method: PUT
Type: RAW/JSON
Auth Type: none
URL: {{host}}/api/v1/usuarios/:usernameEmailOuId
```



***URL variables:***

| Key               | Value        |
| ----------------- | ------------ |
| usernameEmailOuId | {{username}} |



***Body:***

```js        
{   
    "username": "{{username}}",
    "nomeCompleto": "{{nomeCompleto}}",
    "email": "{{email}}"
}
```

### ***4. Deletar um usuário***


Deleta um usuário caso a senha enviada for valida.


***Endpoint:***

```bash
Method: DELETE
Type: RAW/JSON
Auth Type: none
URL: {{host}}/api/v1/usuarios/:usernameEmailOrId
```



***URL variables:***

| Key               | Value        |
| ----------------- | ------------ |
| usernameEmailOrId | {{username}} |



***Body:***

```js        
{
    "password": "{{password}}"
}
```



### ***5. Buscar um unico usuário registrado***


Busca um único usuário registrado pelo e-mail, nome de usuário ou id.


***Endpoint:***

```bash
Method: GET
Auth Type: none
URL: {{host}}/api/v1/usuarios/:usernameEmailOuId
```



***URL variables:***

| Key               | Value     |
| ----------------- | --------- |
| usernameEmailOuId | {{email}} |



### ***6. Validar e-mail usando o código***

Valida o e-mail do usuário usando o código de verificação enviado por e-mail.


***Endpoint:***

```bash
Method: GET
Auth Type: none
URL: {{host}}/api/v1/usuarios/:userId/validarEmail
```



***Query params:***

| Key    | Value      |
| ------ | ---------- |
| codigo | {{codigo}} |



***URL variables:***

| Key    | Value      |
| ------ | ---------- |
| userId | {{userId}} |




---

[Ir para o topo](#usuarios)