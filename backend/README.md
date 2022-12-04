# nosql2h22-it-company

# Auth Service

Для корректной работы сервисов (аутентификация и авторизация пользователей) необходим самоподписанный сертификат,
который необходимо обновлять. Для каждого выдаваемого типа ключа (ACCESS, REFRESH) необходимо создать отдельный
сертификат с собственным закрытым ключом. По умолчанию было создано два сертификата для каждого из токенов со сроком
истечения в 2 года.

## Унифицированное создание сертификата

1. [Опционально] Перейти в:
    ```shell
    cd $JAVA_HOME/bin
    ```
2. Создать закрытый ключ (например, 2048 бит) для выбранного алгоритма.
3. Создать сертификат со следующими параметрами:
    1. `alias` - псевдоним сертификата
    2. `keyalg` - название алгоритма (сервис поддерживает `RSA`)
    3. `keypass` - закрытый ключ для выбранного алгоритма (2048 бит)
    4. `keystore` - название хранилища (файла, в который запишется сертификат)
    5. `storepass` - ключ хранилища (должен совпадать с `keypass`)
    6. `validity` - срок действия сертификата (в днях)
    7. `token-type` - тип используемого токена (access, refresh)

   ```shell
   keytool -genkeypair \
   -alias nosql-<token-type>-token \
   -keyalg RSA \
   -keypass <your-key-pass> \
   -keystore nosql-<token-type>-token.jks \
   -storepass <your-key-pass> \
   -validity 720
   ```
4. Ответить на вопросы следующим образом:
   ```shell
   What is your first and last name?
   [Unknown]:  nosql-it-company
   What is the name of your organizational unit?
   [Unknown]:
   What is the name of your organization?
   [Unknown]:  ETU
   What is the name of your City or Locality?
   [Unknown]:  Saint Petersburg
   What is the name of your State or Province?
   [Unknown]:  Saint Petersburg
   What is the two-letter country code for this unit?
   [Unknown]:  RU
   Is CN=nosql-it-company, OU=Unknown, O=ETU, L=Saint Petersburg, ST=Saint Petersburg, C=RU correct?
   [no]:  yes
   ```
5. Поместить сгенерированные сертификаты `nosql-access-token.jks` и `nosql-refresh-token.jks` в  
   `backend/auth-service/src/main/resources/jwks/`
6. Приватные ключи, используемые на 3 шаге для создания сертификата, передать при запуске приложения в переменные
   среды `JWKS_ACCESS_TOKEN_PASSWORD` и `JWKS_REFRESH_TOKEN_PASSWORD`.
