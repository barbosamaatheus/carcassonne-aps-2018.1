package br.ufpb.dcx.aps.carcassone;

public class Jogo {
	
	public void verificaBolsaTilesNula(BolsaDeTiles tiles) {
		if(tiles == null)
			throw new ExcecaoJogo("A bolsa de tiles deve ser fornecida na criação de uma partida");
	}
	
	public void verificaSequenciaValida(Cor...sequencia) {
		if(sequencia.length < 2)
			throw new ExcecaoJogo("Cada partida deve ter uma sequência de pelo menos dois jogadores");
	}
	
	public void verificaRepeticoes(Cor... sequencia) {
		for (int i = 0; i < sequencia.length - 1 ; i++){
			for(int j = i+1; j < sequencia.length; j++){
				if(sequencia[i]==sequencia[j]){
					throw new ExcecaoJogo("Não pode haver repetição de cores na sequência de jogadores");
				}
	        }
		}
	}
	
	
	public void executaVerificacoes(BolsaDeTiles tiles, Cor... sequencia) {
		verificaBolsaTilesNula(tiles);
		verificaSequenciaValida(sequencia);
		verificaRepeticoes(sequencia);
	}
	
	public Partida criarPartida(BolsaDeTiles tiles, Cor... sequencia) {
		return new Partida(tiles, sequencia);
	}



}
