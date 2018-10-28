package br.ufpb.dcx.aps.carcassone;

public class JogoExtensaoMosteiro extends Jogo{
	
	public PartidaExtensaoMosteiro criarPartida(BolsaDeTiles tiles, Cor... sequencia) {
		executaVerificacoes(tiles, sequencia);
		return new PartidaExtensaoMosteiro(tiles, sequencia);
	}
}
