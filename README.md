# Considerações do projeto

Por se tratar de uma arquitetura baseada em microserviços optei por utilizar as seguintes tecnologias; **Java 11 + Spring Boot + Postgres (RDS AWS) + Spring Cloud ( Zuul e Eureka)** alem disto incorporei algumas outras coisas que julgo como importantes em um projeto de arquitetura distribuidas como:  **Autententicação e autorização  baseada em JWT,  Documentação de API utilizando o Swagger 2 , testes unitarios** além da utilização do **Docker** e **DockerCompose** para facilitar a implantação e o escalonamento do sistema.
    
### Estrutura do projeto ###

	├── devchalenge	 #Maven pom project
	│   ├── discovery	   #Maven module project de discovery do projeto e HealhCheeck
	│   ├── gateway		 #Maven module microservice load balancing
	│   ├── auth		       #Maven module microservice project AUth
	│   ├── cliente	#      #Maven module microservice project Cliente

	└── docker-compose.yml	#Docker compose

### Installation - DOCKER

Faça o clone do projeto para seu computador acesse a pasta raiz do projeto via CMD e execute o seguinte comando.

Obs:

Você deve possuir o maven e o Docker instalados.

```sh
 mvn clean install
 docker-compose up
```
Após a conclusão do processo o  sistema podera ser acessado em:

**DISCOVERY**
- http://localhost:8761/

**AUTH**

- http://localhost:8762/auth/swagger-ui.html

		ADMIN - usuário: admin  senha: 123456
		USER     - usuário: user     senha: 123456

**CLIENTE**
- http://localhost:8762/cliente/swagger-ui.html

**ACESSOS VIA GATEWAY ZULL**

http://localhost:8762/auth-api/swagger-ui.html

http://localhost:8762/cliente-api/swagger-ui.html

### Installation - DESENV

Faça o clone do projeto para seu computador 

		mvn clean install

Você já pode utilizar as mesmas url apontadas acima conforme for subindo cada modulo.


### Collection POSTMAN

	├── devchalenge	 #Raiz do projeto
	│   ├── collections_postman
				│   ├── Builders Challenge - GATEWAY.postman_collection.json 
				│   ├── Builders Challenge - NO GATEWAY.postman_collection.json

**Obs.** 
Uma collection faz acesso direto as  e a outra passa pelo Gateway ZULL.
Uma vez autenticado o token é armazenado em uma variavel.

# BUILDERS
