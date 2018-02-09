var app = angular.module("spotify", []);
app.controller("spotifyController", function($scope) {

	$scope.artistas = [];
	
	$scope.adicionarArtista = function (artista) {
		if (pesquisaArtista(artista)) {
			alert("artista já existente no sistema");
		}else {
			artista.isFavorito = false;
			artista.nota = "";
			artista.ultimaMusica = "";
			artista.coracao = "imagens/coracaoBranco.png";
			$scope.artistas.push(artista);
			}
			delete $scope.artista;
		};

});