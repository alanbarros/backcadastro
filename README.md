# backcadastro - API de Cadastro de Vendas
Este projeto é uma API Spring Boot para cadastro de vendas, com endpoints para validação de CPF/CNPJ.

## Pré-requisitos
Para executar este projeto com Docker, você precisará ter o Docker e o Docker Compose instalados em sua máquina:

- [Docker](https://docs.docker.com/get-docker/)

- [Docker Compose](https://docs.docker.com/compose/install/)

## Como Executar com Docker Compose
Siga os passos abaixo para construir a imagem Docker e executar o container:

1. **Navegue até o diretório raiz do projeto:**
Certifique-se de que você está no mesmo diretório onde os arquivos pom.xml, src, Dockerfile e docker-compose.yml estão localizados.

2. **Construa a imagem Docker e inicie o container:**
Abra seu terminal ou prompt de comando e execute o seguinte comando:

```shell
docker-compose up --build
```

`--build`: Este comando irá construir a imagem Docker a partir do `Dockerfile`. Se a imagem já existir e você não tiver feito alterações no código ou no `Dockerfile`, você pode omitir `--build` nas próximas vezes para iniciar o container mais rapidamente.

- O Spring Boot pode levar alguns segundos para iniciar completamente. Observe os logs no terminal até ver uma mensagem indicando que a aplicação foi iniciada (ex: "Started BackcadastroApplication in X.X seconds").

3. **Parar o container:**
Para parar o container, pressione `Ctrl+C no terminal onde o `docker-compose up` está sendo executado. Se você quiser parar e remover o container (e a rede criada pelo compose), execute:

```shell
docker-compose down
```

## Testando a API com cURL
Com a aplicação em execução, você pode testar os endpoints da API usando cURL.

### 1. Endpoint `/vendas/` (GET)
Este endpoint retorna uma mensagem simples.

```shell
curl http://localhost:8080/vendas/
```

**Saída esperada:**

```
Oi mundo!
```

### 2. Endpoint /vendas/from/cpf_cnpj (GET)
Este endpoint valida a entrada de CPF ou CNPJ.

**Teste com CPF válido (apenas números)**
```
curl "http://localhost:8080/vendas/from/cpf_cnpj?cpf=12345678900"
```

**Saída esperada:**

```
O cpf: 12345678900 foi validado com sucesso
```

**Teste com CNPJ válido (apenas números)**
```
curl "http://localhost:8080/vendas/from/cpf_cnpj?cnpj=12345678000199"
```

**Saída esperada:**

```
O cnpj: 12345678000199 foi validado com sucesso
```

**Teste com CPF inválido (contém letras)**
```
curl "http://localhost:8080/vendas/from/cpf_cnpj?cpf=123abc78900"
```

**Saída esperada (erro de validação):**
```
[
"cpf: O `cpf` deve conter apenas números."
]
```

**Teste com CNPJ inválido (contém caracteres especiais)**
```
curl "http://localhost:8080/vendas/from/cpf_cnpj?cnpj=12.345.678/0001-99"
```

**Saída esperada (erro de validação):**

```
[
"cnpj: O `cnpj` deve conter apenas números."
]
```

**Teste com CPF e CNPJ informados (erro de validação)**
```
curl "http://localhost:8080/vendas/from/cpf_cnpj?cpf=12345678900&cnpj=12345678000199"
```

**Saída esperada (erro de validação):**

```
[
"isOnlyCpfOrCnpjProvided: Deve ser informado apenas o CPF ou apenas o CNPJ."
]
```

**Teste sem CPF nem CNPJ informados (erro de validação)**
```
curl "http://localhost:8080/vendas/from/cpf_cnpj"
```

**Saída esperada (erro de validação):**

```
[
"isOnlyCpfOrCnpjProvided: Deve ser informado apenas o CPF ou apenas o CNPJ."
]
```
