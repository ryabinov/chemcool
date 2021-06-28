# Инструкция по работе с серисом **Spring Cloud Config Server**

**Spring Cloud Config Server**, далее **конфиг сервер** - это хранилище конфигураций для распределенной системы микросервисов. В качестве источника данных на данный момент используется Git и простые файлы, хранящиеся локально. По соглашению нашего проекта Spring Cloud Config отдает файлы, соответствующие имени запрашивающего Spring приложения (параметр *Spring.cloud.config.name*).

Приложение конфиг сервер имеет имя **config-server**

## Первичная настройка конфиг сервера до перового запуска проекта
Дла того чтобы конфиг сервер мог отдавать конфигурационные файлы своим клиентам в профиле локального тестирования (без использования Docker) необходимо провести настройку git для папки **config-repo**.

Для этого открываем терминал и переходим в папку **configuration/config-repo**

        cd configuration/config-repo
Далее необходимо последовательно выполнить следующие команды:

        git init
        git add -A
        git commit -m "init commit"

Далее необходимо указать переменные окружения для сервиса конфиг сервера:
для указания локального git репозитория:
        
        SPRING_CLOUD_CONFIG_SERVER_GIT_URL=file:///абсолютный_путь_к_папке_config-repo

Пример для Linux:

        SPRING_CLOUD_CONFIG_SERVER_GIT_URL=file:////media/имя_пользователя/Java/Project/chemcool/configuration/config-repo

Пример для Windows:

        SPRING_CLOUD_CONFIG_SERVER_GIT_URL=file:///C:\Users\имя_пользователя\IdeaProjects\chemcool\configurastion\config-repo


Так же необходимо определить 
SPRING_CLOUD_CONFIG_SERVER_GIT_SEARCHPATHS=? 
SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCHLOCATIONS=file:////opt/config-repo

После этого конфиг сервер готов к запуску. Этого достаточно, для того чтобы запускать приложение.
Если необходимо настроить свой новый микросервис и включить его в работу через конфиг сервер, то читаем далее.

  
## Настройка разрабатываемого микросервиса в роли клиента конфиг сервера
Для настройки взаимодействия разрабатываемого вами микросервиса с сервисом конфиг сервера необходимо выполнить следующие шаги:
- добавить мавен зависимости в pom файл сервиса, чтобы включить поддержку клиента конфиг сервера 
- в ресурсы добавить файл bootstrap.yml, в котором указаны необходимая настройка для взаимодействия

### Добавляем зависимость 
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
Если нет описанной версии в блоке **dependencyManagement**, то тогда необходимо указать ещё и версию org.springframework.cloud.
Для Spring Boot версии 2.3.5.RELEASE, версия Spring Cloud будет Hoxton.SR
        
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
            <version>Hoxton.SR8</version>
        </dependency>

### bootstrap.yml
Теперь необходимо в ресурсах разрабатываемого микросервиса разместить файл bootstrap.yml со следующим содержимым:

    spring:
      application:
       name: название_микросервиса
      cloud:
        config:
          name: название_файла_конфигурации
      discovery:
        enabled: true
        service-id: config-service
      username: ${SPRING_CLOUD_CONFIG_USERNAME}
      password: ${SPRING_CLOUD_CONFIG_PASSWORD}
      fail-fast: true
      retry:
        initial-interval: 2000
        multiplier: 1.1
        max-attempts: 50
        max-interval: 1000

Ключевые параметры в этом файле:

        spring.cloud.config.name: название_файла_конфигурации
этот параметр указывает на название файла yml в папке config-repo

        spring.cloud.discovery: config-service
этот параметр указывает на название нашего конфиг сервера, к которому будет обращаться с запросом файла конфигурации разрабатываемый микросервис 

### Размещаем файл в configuration/config-repo
Размещаем файл название_файла_конфигурации.yml в папке configuration/config-repo.
Затем открываем терминал и последовательно выполняем команды:

        git add -A
        git commit -m "add new file_name.yml"
