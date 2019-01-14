# LINE Test TODO
LINE Test Todo project

## How to run
After cloned this repo into your machine, follow this step

### Configuration .yml file
Open application.yml file in `src/main/resource/application.yml` replace `channel-token` and `channel-secret` as value from `secrete.txt`

```
line.bot:
  channel-token: 'Put Channel Token Here.'
  channel-secret: 'Put Channel Secret Here.'
  handle-path: /callback
```

Replace `client_id`, `client_secret` and `redirect_uri` as host name of Bot's callback path or host that running this application.

```
line.login:
  client_id: 'Put Channel Client ID Here.'
  client_secret: 'Put Channel Client Secret Here.'
  redirect_uri: 'Put Host Name Here'/auth
```

Replace `edit-pat` as host name of Bot's callback path or host that running this application.

```
app:
  edit-path: 'Put Host Name Here'/todos/edit
```

### Run Spring Boot
To run application in your local machine use this command, it will compile, test and start this application.

```
$ mvn spring-boot:run

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.2.RELEASE)
 
INFO 2788 --- [           main] com.iphayao.linetest.Application         : Starting Application on

```

This project also deployed into **Heroku** `https://line-test-todo.herokuapp.com/`


#Done! 完了
