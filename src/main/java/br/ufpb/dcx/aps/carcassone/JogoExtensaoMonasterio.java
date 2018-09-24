package br.ufpb.dcx.aps.carcassone;

public class JogoExtensaoMonasterio extends Jogo{
	
	public PartidaExtensaoMonasterio criarPartida(BolsaDeTiles tiles, Cor... sequencia) {
		return new PartidaExtensaoMonasterio(tiles, sequencia);
	}
}
