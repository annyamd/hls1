# hls1
Написать монолит с использованием Spring Boot.
Разрешены языки: Java, Kotlin.
Разрешены системы сборки: Maven, Gradle.
Использовать стабильные версии фреймворков, библиотек, языков. При написании придерживаться языковым конвенциям Java, Kotlin.
Следовать принципам разработки ПО, таким как SOLID, DRY, KISS и др.
При разработке использовать git feature branching strategy. При выборе имен для коммитов необходимо придерживаться git conventional commits.
Код должен быть покрыт модульными и интеграционными тестами с помощью testcontainers и junit-jupiter-api, берём лучше отсюда, ещё можно зайти сюда и делаем красоту. Минимальный общий процент покрытия 70%.

Требования общие

Основные сущности должны иметь осмысленный CRUD интерфейс с использованием REST API.
Использовать правильные http статусы для ответов.
Для запросов к БД использовать spring-data-jpa.
Должна быть реализована валидация полей на уровне контроллера и на уровне Entity.
Структура БД должна создаваться через Liquibase/Flyway миграции.
Для каждого контроллера должны быть написаны интеграционные тесты с использованием testcontainers и junit-jupiter-api.
Использовать переменные среды для конфигурации приложения. При развертывании их можно задавать через поле enviroments: в docker-compose.yml.
Приложение должно собираться с использованием docker и запускаться через docker-compose.
Использовать любую реляционную БД, запущенную через docker.
Каждый findAll должен иметь пагинацию. Нельзя отдавать больше 50 записей за один запрос.
Должен быть минимум один запрос, который вернет findAll в виде бесконечной прокрутки без указания общего количества записей.
Должен быть минимум один запрос, который вернет findAll с пагинацией и с указанием общего количества записей в http хедере.
На сложных запросах должны использоваться транзакции. Должно быть не меньше двух подобных запросов. Обосновать почему там нужны тразакции.
Разделять модели Entity и Dto.
Приложение должно иметь чистый код и архитектуру с разделением по сервисам, репозиториям, контроллерам, моделям и т.д.
Все enums в БД должны сериализоваться как строки.
Ошибки (Exception) из контроллеров нужно хендлить и отдавать человеко читаемую ошибку в теле ответа.
Продумать и согласовать архитектуру БД с преподавателем.
Должны быть реализованы связи между сущностями каждого типа: Many to Many, One to Many/Many to One, Many to Many с дополнительным полем.


Требования к авторизации

Для авторизации использовать spring-security в комбинации с JWT токеном в хедере Authorization.
Реализовать ролевую модель.
Должны быть методы, логика которых меняется в зависимости от текущего пользователя. Пример: Повар не может загружать товары на склад, а логист не может принимать заказы у пользователей.
Не отдавать пароль в запросе, где участвуют модели пользователя.
Все пароли должны храниться в хэшированном виде.
Пользователей могут создавать только супервайзеры.
