package br.ufpb.dcx.aps.carcassone;

public class JogoExtensaoMonasterio extends Jogo{
	
	public PartidaExtensaoMonasterio criarPartida(BolsaDeTiles tiles, Cor... sequencia) {
		executaVerificacoes(tiles, sequencia);
		return new PartidaExtensaoMonasterio(tiles, sequencia);
	}
}
