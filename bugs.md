# BUGS
<p>Uma página que serve para documentar os bugs e como foram resolvidos.</p>

## Bug do heap Space:
<p>O bug ocorre por causa do mecanismo para separar as mensagens e este deve ser a causa do erro de não aparecer a primeira mensagem para o utilizador</p>
<h3>O que acontence:</h3>
<p>Quando só tem uma mensagem (em que não haja virgula) ele entra em loop. Aqui ocorre o erro que heap space, porque ele vai estar semrpe a adicionar à arraylist de mensagens.</p>
<h3>Como resolver</h3>
<p>Refazer o sistema de separar as mensagens.</p>