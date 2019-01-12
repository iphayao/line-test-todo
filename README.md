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