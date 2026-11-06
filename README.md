# Projeto AC4 Poo 
Luã Japiassu Macedo Maia: 202607092245

Pietro



## Descrição do projeto

Aplicação Spring Boot (Maven, Java) para uma empresa de brinquedos esportivos para crianças de até 15 anos. Os brinquedos são cadastrados, consultados, atualizados e excluídos (CRUD) por endpoints REST em `localhost:8080/brinquedos`, testados no Postman. Os dados ficam na tabela `TDS_TB_BRINQUEDOS` do banco H2, com as colunas Id, Nome, Tipo, Classificacao, Tamanho e Preco.

O Postman envia a requisição para o `BrinquedoController`, que chama o `BrinquedoService`. O Service usa o `BrinquedoRepository` para gravar e buscar os dados no banco H2.

## Repository

```java
@Repository
public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}
```

Métodos do `JpaRepository` usados no projeto:

| Método | O que faz | Usado em |
|---|---|---|
| `findAll()` | busca todos os registros | GET (listar) |
| `findById(id)` | busca um registro pelo id | GET (individual) |
| `save(brinquedo)` | insere ou atualiza e faz o commit no banco | POST e PUT |
| `existsById(id)` | verifica se o id existe | PUT e DELETE |
| `deleteById(id)` | exclui o registro pelo id | DELETE |

## Testes do CRUD (Postman)

Base: `http://localhost:8080/brinquedos`

### Create: POST `/brinquedos/criar`

Cadastra um brinquedo. O `id` é gerado automaticamente.

### Read: GET `/brinquedos`

Lista todos os brinquedos cadastrados.

### Read: GET `/brinquedos/individual/1`

Busca o brinquedo pelo id.


### Update: PUT `/brinquedos/atualizacao/1`

Atualiza os dados do brinquedo de id 1.


### Delete: DELETE `/brinquedos/deletar/1`

Exclui o brinquedo do banco pelo id. Retorna `200` com `"Brinquedo deletado com sucesso!"`, ou `404` com `"Brinquedo não encontrado."` se o id não existir.


  
