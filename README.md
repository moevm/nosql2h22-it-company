# nosql2h22-it-company

Для корректной работы необходим самоподписанный сертификат, который необходимо обновлять.

1. [Опционально] Перейти в:
    ```shell
    cd $JAVA_HOME/bin
    ```
2. Создать сертификат со следующими параметрами:
    1. `alias` - псевдоним сертификата
    2. `keyalg` - название алгоритма (сервис поддерживает `RSA`)
    3. `keypass` - закрытый ключ для выбранного алгоритма (2048 бит)
    4. `keystore` - название хранилища (файла, в который запишется сертификат)
    5. `storepass` - ключ хранилища (должен совпадать с `keypass`)
    6. `validity` - срок действия сертификата (в днях)

   ```shell
   keytool -genkeypair \
   -alias nosql-it-company-jwt \
   -keyalg RSA \
   -keypass <your-key-pass> \
   -keystore nosql-jwt.jks \
   -storepass <your-key-pass> \
   -validity 360
   ```
3. Ответить на вопросы следующим образом:
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
4. Поместить сгенерированный сертификат `nosql-jwt.jks` в  
   `backend/auth-service/src/main/resources/jwks/`
5. Приватный ключ, используемый на втором шаге для создания сертификата передать при запуске приложения в переменную
   среды `JWT_KEYSTORE_PASSWORD`.
