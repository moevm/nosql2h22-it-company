# nosql2h22-it-company

## Записи работы приложения

Записи работы приложения на различных стадиях готовности представлены
на [YouTube](https://youtube.com/playlist?list=PLjMLyaIQ920moxvjiXnTMNEmCfav3ak-w).

## Аутентификация и авторизация

Для прохождения аутентификации и авторизации по умолчанию предусмотрены следующие роли:

* HR
    * Login: hr
    * Password: hr
* Developer
    * Login: developer
    * Password: developer

## При локальном использовании

Для попадания на стартовую страницу авторизации необходимо перейти по http://localhost:3000 после запуска всех сервисов
из [docker-compose.yaml][docker-compose-link].

Для смены используемого порта необходимо изменить его значение в [docker-compose.yaml][docker-compose-link] для
сервиса `react-client`.

## Сервис авторизации

Для поддержания безопасной проверки токенов доступа, получаемых сервисами серверной стороны, были использованы
сертификаты с подписанными открытыми ключами, которыми можно проверить подлинность полученного токена. Со временем
сертификаты истекают и их необходимо обновлять. Подробнее в [Обновление сертификата](backend/README.md).

[docker-compose-link]: ./environment/docker-compose.yaml
