<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<header id="layout-header">
	<div class="clearfix container">
		<a href="/" id="logo"> </a>
		<div id="header-content">
			<nav id="main-nav">

				<ul class="clearfix">
					<li><a href="${s:mvcUrl('CCC#itens').build() }" rel="nofollow">Carrinho
							(${carrinhoCompras.quantidade })</a></li>
					<li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">Sobre
							Nós</a></li>
					<li><a href="/pages/perguntas-frequentes" rel="nofollow">Perguntas
							Frequentes</a></li>
				</ul>
			</nav>
		</div>
	</div>
</header>

<nav class="categories-nav">
	<ul class="container">
		<li class="category"><a href="/teste-spring-mvc">Home</a></li>
		<li class="category"><a href="#"> Agile </a></li>
		<li class="category"><a href="#"> Front End </a></li>
		<li class="category"><a href="#"> Games </a></li>
		<li class="category"><a href="#"> Java </a></li>
		<li class="category"><a href="#"> Mobile </a></li>
		<li class="category"><a href="#"> Web </a></li>
		<li class="category"><a href="#"> Outros </a></li>
	</ul>
</nav>
