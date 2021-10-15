//script para criar o banco, collections e documents
//para executar:
// mongo -u <user> -p <password> mongodb01d.mydomain.com:27017/testeDB <yourFile.js> 
//use testeDB

db.Teste.drop();
db.Teste.insertMany( [
      { codigo: 1, nome: "Teste 1" },
	{ codigo: 2, nome: "Teste 2 abc" },
	{ codigo: 3, nome: "Teste 3 acdda" },
	{ codigo: 4, nome: "Teste 4 tererer" },
	{ codigo: 5, nome: "Teste 5 aaadfdf" },
   ] );