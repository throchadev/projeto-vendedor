# Documentação da API

Para subir o projeto precisa subir o compose da aplicação:

    Dentro da pasta executar o comando: docker-compose up -d

Após subir a aplicação chamar o end point abaixo:

    http://localhost:8080/api/payment

Payload para teste:

    {
        "seller_code": "1",
        "payment_items": [
            {
            "payment_id":"PAY001",
            "payment_value":"10.00",
            "payment_status":"PAYED"
            },
            {
            "payment_id":"PAY002",
            "payment_value":"10.00",
            "payment_status":"PAYED"
            }
        ]
    }

