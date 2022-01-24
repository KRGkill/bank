#API Rest para simular un pequeño banco:

- Registro usuario
- Creación de cuenta (wallet)
- Realización de depósito de dinero
- Visualización de cuenta (wallet) --> Balance y movimientos
- Transferencia de una cuenta A a una cuenta B


## Task Resolution
You should be able to start the example application by executing com.iobuilders.bank.BankApplication Which starts a
webserver on port 8080 (http://localhost:8080)
and serves SwaggerUI in the path http://localhost:8080/swagger-ui/ where can inspect and try the endpoints.

### Registro usuario
You can try this in the User Controller Endpoints

### Creación de cuenta (wallet)
You can try this in the Account Controller Endpoints

### Realización de depósito de dinero
You can try this in the Transaction Controller Endpoints

In http://localhost:8080/swagger-ui/#/transaction-controller/saveTransactionUsingPOST, you can add the data for the transaction, 
selecting DEPOSIT for TransactionType

### Visualización de cuenta (wallet) --> Balance y movimientos
You can try this in the Transaction Controller Endpoints
http://localhost:8080/swagger-ui/#/transaction-controller/getAllTransactionUsingGET


### Transferencia de una cuenta A a una cuenta B
You can try this in the Transaction Controller Endpoints

In http://localhost:8080/swagger-ui/#/transaction-controller/saveTransactionUsingPOST, you can add the data for the transaction, 
selecting TRANSFER for TransactionType, and setting 'withdrawalAccountFromId' with the id of the account where the money comes.


# Important
It is a simple application, just adding and subtracting transaction balances.
In a real application, it would be necessary to add other tables in the database, interfaces, DTOs, mappers, and other entities, in addition to security, audit and session.
I hope that with the development carried out you can appreciate my knowledge, in case of any doubt do not suppose, just ask me.
