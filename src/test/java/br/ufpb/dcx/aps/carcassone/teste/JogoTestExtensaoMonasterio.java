package br.ufpb.dcx.aps.carcassone.teste;

import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import br.ufpb.dcx.aps.carcassone.BolsaDeTiles;
import br.ufpb.dcx.aps.carcassone.JogoExtensaoMonasterio;
import br.ufpb.dcx.aps.carcassone.Partida;

public class JogoTestExtensaoMonasterio extends JogoTest{

	@Before
	public void novoJogo() {
		tiles = mock(BolsaDeTiles.class);
		jogo = new JogoExtensaoMonasterio();
	}
	
	/**
	 * Caso de teste 01
	 * Quando o monastério está totalmente rodeado por tiles válidos (8 tiles + ele próprio), 
	 * o jogador soma 9 pontos e o Meeple é devolvido a ele. 	
	 */
	@Test
	public void MonasterioCompletoComMeeple() {
		mockarTiles(tiles, t30, t48, t29, t60, t27, t52, t31, t61, t62);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		//segundo turno t48
		//posicionar o t48 no sul de t30
		partida.posicionarTile(t30, SUL);
		//o norte do t48 ta apontando para o norte
		verificarRelatorioTurno(partida, "PRETO", "48N", "Tile_Posicionado");
		//o jogador PRETO está com um Meeple a menos
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");
		
		partida.finalizarTurno();
		
		//terceiro turno t29
		girar(partida, 1);
		partida.posicionarTile(t48, LESTE);
		
		partida.finalizarTurno();
		
		//quarto turno t60
		partida.posicionarTile(t30, LESTE);
		partida.finalizarTurno();
				
		//quinto turno t27
		partida.posicionarTile(t30, OESTE);
		partida.finalizarTurno();
		
		//sexto turno t52
		partida.posicionarTile(t27, SUL);
		partida.finalizarTurno();
				
		//setimo turno t31
		girar(partida, 3);
		partida.posicionarTile(t52, SUL);
		partida.finalizarTurno();
		
		//oitavo turno t61
		partida.posicionarTile(t30, SUL);
		partida.finalizarTurno();
		
		//nono turno t62
		girar(partida, 1);
		partida.posicionarTile(t61,LESTE);
		partida.finalizarTurno();
					
		verificarRelatorioPartida(partida, "Partida_Finalizada", "VERDE(0,7); PRETO(9,7)");
		
	}
	
	/**
	 * Caso de teste 02
	 * Se o monastério é completado mas não tem nenhum Meeple, não deve somar pontuação a nenhum jogador.
	 */
	@Test
	public void MonasterioCompletoSemMeeple() {
		mockarTiles(tiles, t30, t48, t29, t60, t27, t52, t31, t61, t62);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		//segundo turno
		partida.posicionarTile(t30, SUL);
		
		verificarRelatorioTurno(partida, "VERDE", "49S", "Tile_Posicionado"); //ver como funciona
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		partida.finalizarTurno();
		
		//terceiro turno
		girar(partida, 1);
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.finalizarTurno();
		
		//terceiro turno
		girar(partida, 1); //rever
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.finalizarTurno();
				
		//quarto turno
		girar(partida, 1); //rever
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.finalizarTurno();
		
		//quinto turno
		girar(partida, 1); //rever
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.finalizarTurno();
				
		//sexto turno
		girar(partida, 1); //rever
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.finalizarTurno();
		
		//sétimo turno
		girar(partida, 1); //rever
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.finalizarTurno();
		
		//oitavo turno
		girar(partida, 1); //rever
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.finalizarTurno();
				
		//nono turno
		girar(partida, 1); //rever
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.finalizarTurno();
			
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
	}
	
	/**
	 * Caso de teste 03
	 * Ao final de um turno com a partida em andamento em que o o monastério esteja incompleto, 
	 * a pontuação do jogador não é alterada e o Meeple permanece no monastério.
	 */
	@Test
	public void MonasterioIncompletoComPartidaEmAndamento() {
		mockarTiles(tiles, t30, t49, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		//segundo turno
		girar(partida, 1); //rever
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleMosteiro();
		verificarRelatorioTurno(partida, "VERDE", "49S", "Tile_Posicionado");
		partida.finalizarTurno();
		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,6); PRETO(0,7)");
	}
	
	/**
	 * Caso de teste 04
	 * Verifica se lança exceções para tentativas de adicionar Meeples em um Monastério já ocupado por um outro Meeple.
	 */
	@Test
	public void MonasterioComDoisMeeples() {
		mockarTiles(tiles, t30, t49);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		//segundo turno
		girar(partida, 1); //rever
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleMosteiro();
		verificarRelatorioTurno(partida, "VERDE", "49S", "Tile_Posicionado");
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,6); PRETO(0,7)");
		partida.finalizarTurno();
		
		//tereciro turno
		girar(partida, 1); //rever
		partida.posicionarTile(tileReferencia, ladoTileReferencia);
		partida.posicionarMeepleMosteiro();
		partida.finalizarTurno();
	}
	
	 
	
	/**
	 * Caso de teste 05
	 * Se a partida finaliza com o monastério incompleto e há um Meeple, a pontuação do jogador será 
	 * a quantidade de tiles ao redor do monastério.  
	 */
	@Test
	public void	PartidaFinalizadaComMonasterioIncompleto() {
		mockarTiles(tiles, t30, t49);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		//segundo turno
		girar(partida, 1); //rever
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleMosteiro();
		verificarRelatorioTurno(partida, "VERDE", "49S", "Tile_Posicionado");
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,6); PRETO(0,7)");
		partida.finalizarTurno();
		verificarRelatorioPartida(partida, "Partida_Finalizada", "VERDE(2,7); PRETO(0,7)");
		
		
	}
	
	
	/**
	 * Caso de teste 06
	 * Quando o jardim está totalmente rodeado por tiles válidos (8 tiles + ele próprio), 
	 * o jogador soma 9 pontos e o Meeple é devolvido a ele. 	
	 */
	@Test
	public void JardimCompleto() {
		
	}
	
	/**
	 * Caso de teste 07
	 * Se o jardim é completado mas não tem nenhum Meeple, não deve somar pontuação a nenhum jogador.
	 */
	@Test
	public void JardimCompletoSemMeeple() {
		
	}
	
	/**
	 * Caso de teste 08
	 * Ao final de um turno com a partida em andamento em que o o monastério esteja incompleto, 
	 * a pontuação do jogador não é alterada e o Meeple permanece no monastério.
	 */
	@Test
	public void JardimIncompletoComPartidaEmAndamento() {
		
	}
	
	/**
	 * Caso de teste 09
	 * Verifica se lança exceções para tentativas de adicionar Meeples em um Monastério já ocupado por um outro Meeple.
	 */
	@Test
	public void JardimComDoisMeeples() {
		
	}
	
	
	/**
	 * Caso de teste 10
	 * Se a partida finaliza com o Jardim incompleto e há um Meeple, a pontuação do jogador será 
	 * a quantidade de tiles ao redor do jardim.  
	 */
	@Test
	public void	PartidaFinalizadaComJardimIncompleto() {
		
	}
	
	
	
	
	
	
	
	


	
	
	
	
}
