# ProjetoPointSistemas
Projeto e cadastramento de clientes para a Point Sistemas

### Softwares utilizados
* Java 11
* Apache tomcat 8.5.93
* PostgreSQL 16

--------------------------------------------------------------------------------------------------------------------
### Configurações do database PostgreSQL 16

| Info  | Dados |
| ------------- | ------------- |
| Database Name  | pointSistemas |
| Root  | localhost  |
| Port  | 5432  |
| Owner  | postgres  |
| Password  | admin  |

> **OBS: Não é preciso criar tabela pois toda vez que o projeto é executado o persistence JPA verifica se existe 
a tabela, se existir ele dropa e cria novamente uma tabela sem dados nas colunas, se não existir ele cria 
automaticamente com as colunas desejadas da plataforma.**

--------------------------------------------------------------------------------------------------------------------
### Execução do projeto

 - Configurar na IDE o apache tomcat 8.5
 - Inicializar o projeto utilizando o server apache tomcat 8.5
 - url para teste http://localhost:{port}/projetoUsuarioPointSistemas/
