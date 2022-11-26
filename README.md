André R R Costa
# CodingChallenge

# Descrição
2) Eu não fiz as validações de entrada para as datas.
3) Os testes não estão levando em consideração a infalibilidade (pela lógica dos testes
mas obviamente o software está correto)
4) O algoritmo poderia perder vários aspectos de acoplamento. porém a modelagem das
tabelas não permite.
5) Eu fiz 3 interfaces. Uma que utiliza streams para separar os dados e é acessada em
localhost:8080/solutions/oop/{métodos de 1 a 6}. A segunda que só executa SQL e é
acessada em localhost:8080/solutions/sql/{métodos de 1 a 6} mas a terceira eu ia
implementar através de functions, views e procedures mas eu fiquei cansado. 
Depois eu posso fazer.

# How to run
1) Faça o mvn clean install para criar as implementações do mapstruct e rodar os testes.
2) rode 'mvn spring-boot:run'
