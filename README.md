# Sistema de Trânsito - Gestão e Qualidade de Software

## Descrição

O projeto consiste em uma aplicação voltada para auxiliar o serviço de funcionários do DETRAN, que poderão gerenciar multas associadas a motoristas. Enquanto os motoristas por sua vez, podem checar a existência de multas sobre seu nome/veículo.

## Requisitos

Certifique-se de ter o Docker instalado para a execução da aplicação.

## Instruções de Uso

1. Clone o repositório com o Git.
```bash
git clone https://github.com/Diego-Pimenta/transit-system.git
```

2. Entre na pasta raiz do projeto e inicie os containers definidos no "compose.yaml".
```bash
cd transit-system
docker compose up
```

3. Acesse o frontend da aplicação pelo navegador com a URL:
```bash
http://localhost:3000
```

4. Interaja com a API no Postman ou Insomnia com a seguinte URL:
```bash
http://localhost:8081/api
```

5. Para acessar a plataforma com contas já cadastradas no banco de dados, utilize os seguintes dados de login:

- Funcionário - CPF: 12345678901, Senha: admin123.
- Motorista - CPF: 12345678902, Senha: password1.
- Motorista - CPF: 12345678903, Senha: password2.
- Motorista - CPF: 12345678904, Senha: password3.
- Motorista - CPF: 12345678905, Senha: password4.

Observação: Qualquer novo usuário apenas possuirá autoridade de motorista.
