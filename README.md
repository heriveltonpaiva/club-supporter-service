# club-supporter-service


Serviço responsável pelo cadastro de sócio torcedor e adesão as campanhas do seu time do coração.

#### Tecnologias Utilizadas 

Segue abaixo as principais tecnologias utilizadas nesse projeto:

* Java 11
* SpringBoot (Web, Data, Cloud e AMQP)
* Spring Cloud Netflix
* Spring Cloud OpenFeign
* JUnit5 e Mockito
* Lombok
* Swagger2

#### Pré-requisitos antes de executar a aplicação 

Esse serviço possui a dependência com o rabbitMQ, foi implementado um consumidor
para simular o recebimento das notificações de atualização de campanhas. 

Uma segunda dependência foi adicionada, sendo necessário o container da base de dados
está rodando, a justificativa dessa implementação é garantir autonomia do sistema
ao cadastrar o sócio torcedor, sem dependência de um único serviço para cadastrar,
além da necessidade de consultar os times cadastrados.

Antes de executar essa aplicação, subir o container do rabbitMQ e da base de dados
postgresql conforme descrito no serviço abaixo:

https://github.com/heriveltonpaiva/campaign-service

#### Executar a aplicação 


Para rodar a aplicação execute:

`./gradlew bootRun` 

Você pode acessar a documentação da api através do swagger:

`http://localhost:8090/swagger-ui.html`

#### Arquitetura

* A organização das classes e pacotes se aplicam a algumas práticas e conceitos DDD, Campaign e CampaignSubscription isolado em um único
contexto de negócio, assim como team e clubsupporter em um contexto isolado. 
* Domínios externos não conhecem as entidades, o trafégo de 
informações entre serviços ocorre através dos DTO's. 

* Utilização do Spring Cloud para comunicação entre serviços com Fallbacks lançando
exceções caso algo de errado possa ocorrer. 

#### Como testar 

* Ao iniciar esse serviço e executar o curl abaixo, será criado um novo sócio torcedor, se existir campanha para o
idHeartTeam=1 será associado automaticamente, senão criará apenas o sócio torcedor.
 
`curl -X POST "http://localhost:8090/club-supporter" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"birthDate\": \"2020-02-05\", \"email\": \"teste@gmail.com\", \"idHeartTeam\": 1, \"name\": \"Herivelton\"}"`

Na applicação: campaign-service 

* Vamos criar uma campanha para o idHeartTeam=1, execute o comando abaixo:

`curl -X POST "http://localhost:8089/campaigns" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"endEffectiveDate\": \"2021-01-01\", \"idHeartTeam\": 1, \"name\": \"Campanha do Flamengo\", \"startEffectiveDate\": \"2020-01-01\"}"`

Em seguida ao executar novamente a primeira chamada para adicionar o sócio torcedor, o registro será atualizado e vinculado a nova
nova campanha existente.

* Vamos criar mais duas campanhas idênticas a primeira, alterando apenas a data final, assim ficando respectivamente as
datas: `2021-01-01, 2021-01-02, 2021-01-02` em seguida vamos buscar o id do registro que possui a data `2021-01-01` e altera-la
para `2021-01-02`, nesse momento haverá conflito de datas, esse serviço deverá efetuar atualização nas datas seguintes.

Segue o curl executado para update, substitua {id_campaign} pelo id da campanha referente a data `2021-01-02`
`curl -X PUT "http://localhost:8089/campaigns/{id_campaign}" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"endEffectiveDate\": \"2021-01-02\", \"idHeartTeam\": 1, \"name\": \"Campanha do Flamengo 2\", \"startEffectiveDate\": \"2020-01-01\"}"`

Após execução as datas ficaram respectivamente `2021-01-02, 2021-01-03, 2021-01-04`, nesse momento as campanhas que sofreram
atualizações foram postados na fila para notificar outras aplicações, no nosso cenário foi enviado a mensagem abaixo conforme log:

`m=sendMessage message=Notification(data=CampaignDTO(id=138, name=Campanha do Flamengo, startEffectiveDate=2020-01-01, endEffectiveDate=2021-01-02, idHeartTeam=1))`

`sendMessage message=Notification(data=CampaignDTO(id=141, name=Campanha do Flamengo 2, startEffectiveDate=2020-01-01, endEffectiveDate=2021-01-03, idHeartTeam=1))`

`sendMessage message=Notification(data=CampaignDTO(id=142, name=Campanha do Flamengo 2, startEffectiveDate=2020-01-01, endEffectiveDate=2021-01-04, idHeartTeam=1))`

Na aplicação club-supporter-service será recebido a notificação das campanhas que foram atualizadas, como demostrado no log abaixo: 

`Notification(data={id=142.0, name=Campanha do Flamengo 2, startEffectiveDate={year=2020.0, month=JANUARY, monthValue=1.0, dayOfMonth=1.0, chronology={calendarType=iso8601, id=ISO}, 
dayOfWeek=WEDNESDAY, dayOfYear=1.0, era=CE, leapYear=true}, endEffectiveDate={year=2021.0, month=JANUARY, monthValue=1.0, dayOfMonth=4.0, chronology={calendarType=iso8601, id=ISO}, dayOfWeek=MONDAY, dayOfYear=4.0, era=CE, leapYear=false}, idHeartTeam=1.0})
`

Mais detalhes podem ser consultados no swagger das aplicações. 

Isso é tudo, até a próxima! 