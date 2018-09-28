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
		partida.posicionarMeepleMosteiro();
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
		
		partida.posicionarTile(t30, SUL);
		verificarRelatorioTurno(partida, "PRETO", "48N", "Tile_Posicionado");
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		
		partida.finalizarTurno();
		
		girar(partida, 1);
		partida.posicionarTile(t48, LESTE);
		
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, LESTE);
		partida.finalizarTurno();
				
		partida.posicionarTile(t30, OESTE);
		partida.finalizarTurno();
		
		partida.posicionarTile(t27, SUL);
		partida.finalizarTurno();
				
		girar(partida, 3);
		partida.posicionarTile(t52, SUL);
		partida.finalizarTurno();
		
		partida.posicionarTile(t48, SUL);
		partida.finalizarTurno();
		
		girar(partida, 1);
		partida.posicionarTile(t61,LESTE);
		partida.finalizarTurno();
					
		verificarRelatorioPartida(partida, "Partida_Finalizada", "VERDE(0,7); PRETO(0,7)");
		
	}
	
	/**
	 * Caso de teste 03
	 * Ao final de um turno com a partida em andamento em que o o monastério esteja incompleto, 
	 * a pontuação do jogador não é alterada e o Meeple permanece no monastério.
	 */
	@Test
	public void MonasterioIncompletoComPartidaEmAndamento() {
		mockarTiles(tiles, t30, t48, t29, t60, t27, t52, t31, t61, t62);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		
		partida.finalizarTurno();
		partida.posicionarTile(t30, SUL);
		
		partida.posicionarMeepleMosteiro();
		verificarRelatorioTurno(partida, "PRETO", "48N", "Tile_Posicionado");
		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");
		
		partida.finalizarTurno();
		
		
		girar(partida, 1);
		partida.posicionarTile(t48, LESTE);
		
		partida.finalizarTurno();
		
		
		partida.posicionarTile(t30, LESTE);
		partida.finalizarTurno();
				
					
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");
		
	}
	
	/**
	 * Caso de teste 04
	 * Verifica se lança exceções para tentativas de adicionar Meeples em um Monastério já ocupado por um outro Meeple.
	 */
	@Test
	public void MonasterioComDoisMeeples() {
		mockarTiles(tiles, t30, t48);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleMosteiro();
		
		ocorreExcecaoJogo(() -> partida.posicionarMeepleMosteiro(),
				"Impossível posicionar: tile já contém meeple");
		verificarRelatorioTurno(partida, "PRETO", "48N", "Tile_Posicionado");
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		partida.finalizarTurno();
		
	}
	
	 
	
	/**
	 * Caso de teste 05
	 * Se a partida finaliza com o monastério incompleto e há um Meeple, a pontuação do jogador será 
	 * a quantidade de tiles ao redor do monastério.  
	 */
	@Test
	public void	PartidaFinalizadaComMonasterioIncompleto() {
		mockarTiles(tiles, t30, t48);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		
		partida.finalizarTurno();
		partida.posicionarTile(t30, SUL);
		
		partida.posicionarMeepleMosteiro();
		verificarRelatorioTurno(partida, "PRETO", "48N", "Tile_Posicionado");
		partida.finalizarTurno();
		verificarRelatorioPartida(partida, "Partida_Finalizada", "VERDE(0,7); PRETO(2,7)");
		
		
	}
	
	
	/**
	 * Caso de teste 06
	 * Quando o jardim está totalmente rodeado por tiles válidos (8 tiles + ele próprio), 
	 * o jogador soma 9 pontos e o Meeple é devolvido a ele. 	
	 */
	@Test
	public void JardimCompleto() {
		mockarTiles(tiles, t30, t72, t29, t31, t01, t03, t27, t08, t02);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		//T72
		partida.posicionarTile(t30, LESTE);	
		
		partida.posicionarMeepleJardim();
		
		verificarRelatorioTurno(partida, "PRETO", "72N", "Tile_Posicionado");		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");		
		partida.finalizarTurno();
		
		//T29
		girar(partida, 2);
		partida.posicionarTile(t30, NORTE);		
		partida.finalizarTurno();
		
		//T31
		girar(partida, 1);
		partida.posicionarTile(t72, NORTE);
		partida.finalizarTurno();
		
		//T01
		partida.posicionarTile(t31, LESTE);
		partida.finalizarTurno();
				
		//T03
		girar(partida, 2);
		partida.posicionarTile(t30, SUL);
		partida.finalizarTurno();
				
		//T27
		girar(partida, 2);
		partida.posicionarTile(t72, LESTE);
		partida.finalizarTurno();
	
		//T08
		girar(partida, 2);
		partida.posicionarTile(t72, SUL);
		partida.finalizarTurno();
		
		//T02
		partida.posicionarTile(t08,LESTE);
		partida.finalizarTurno();
					
		verificarRelatorioPartida(partida, "Partida_Finalizada", "VERDE(0,7); PRETO(9,7)");
		
	}
	
	/**
	 * Caso de teste 07
	 * Se o jardim é completado mas não tem nenhum Meeple, não deve somar pontuação a nenhum jogador.
	 */
	@Test
	public void JardimCompletoSemMeeple() {
		
		mockarTiles(tiles, t30, t72, t29, t31, t01, t03, t27, t08, t02);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		//T72
		partida.posicionarTile(t30, LESTE);	
				
		verificarRelatorioTurno(partida, "PRETO", "72N", "Tile_Posicionado");		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");		
		partida.finalizarTurno();
		
		//T29
		girar(partida, 2);
		partida.posicionarTile(t30, NORTE);		
		partida.finalizarTurno();
		
		//T31
		girar(partida, 1);
		partida.posicionarTile(t72, NORTE);
		partida.finalizarTurno();
		
		//T01
		partida.posicionarTile(t31, LESTE);
		partida.finalizarTurno();
				
		//T03
		girar(partida, 2);
		partida.posicionarTile(t30, SUL);
		partida.finalizarTurno();
				
		//T27
		girar(partida, 2);
		partida.posicionarTile(t72, LESTE);
		partida.finalizarTurno();
	
		//T08
		girar(partida, 2);
		partida.posicionarTile(t72, SUL);
		partida.finalizarTurno();
		
		//T02
		partida.posicionarTile(t08,LESTE);
		partida.finalizarTurno();
					
		verificarRelatorioPartida(partida, "Partida_Finalizada", "VERDE(0,7); PRETO(0,7)");
		
	}
	
			
	
	/**
	 * Caso de teste 08
	 * Ao final de um turno com a partida em andamento em que o jardim esteja incompleto, 
	 * a pontuação do jogador não é alterada e o Meeple permanece no jardim.
	 */
	@Test
	public void JardimIncompletoComPartidaEmAndamento() {
		
		mockarTiles(tiles, t30, t72, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		//T72
		partida.posicionarTile(t30, LESTE);	
		
		partida.posicionarMeepleJardim();
		
		verificarRelatorioTurno(partida, "PRETO", "72N", "Tile_Posicionado");		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");		
		partida.finalizarTurno();
		
		//T29
		girar(partida, 2);
		partida.posicionarTile(t30, NORTE);		
		verificarRelatorioTurno(partida, "VERDE", "29S", "Tile_Posicionado");		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");		
		partida.finalizarTurno();
	
		
		
	}
	
	/**
	 * Caso de teste 09
	 * Verifica se lança exceções para tentativas de adicionar Meeples em um jardim já ocupado por um outro Meeple.
	 */
	@Test
	public void JardimComDoisMeeples() {
		mockarTiles(tiles, t30, t72);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleJardim();
		
		ocorreExcecaoJogo(() -> partida.posicionarMeepleJardim(),
				"Impossível posicionar: tile já contém meeple");
		verificarRelatorioTurno(partida, "PRETO", "72N", "Tile_Posicionado");
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		partida.finalizarTurno();
		
	}
	
	
	/**
	 * Caso de teste 10
	 * Se a partida finaliza com o Jardim incompleto e há um Meeple, a pontuação do jogador será 
	 * a quantidade de tiles ao redor do jardim.  
	 */
	@Test
	public void	PartidaFinalizadaComJardimIncompleto() {
		mockarTiles(tiles, t30, t72);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		
		partida.finalizarTurno();
		partida.posicionarTile(t30, LESTE);
		
		partida.posicionarMeepleJardim();
		verificarRelatorioTurno(partida, "PRETO", "72N", "Tile_Posicionado");
		partida.finalizarTurno();
		verificarRelatorioPartida(partida, "Partida_Finalizada", "VERDE(0,7); PRETO(2,7)");
		
	}
	/**
	 * Caso de teste 11
	 * Quando a partida finzaliza e existem dois mosteiros com meeples juntos.
	 * Cadas jogador deve ganhar 9 pontos e receber o seu meeple de volta
	 */
	@Test
	public void PartidaFinalizadaComMosteirosJuntos() {
		
	}
	
	
	/**
	 * Caso de teste 12
	 * Quando a partida finzaliza e existem dois jardins com meeples juntos.
	 * Cadas jogador deve ganhar 9 pontos e receber o seu meeple de volta
	 */
	@Test
	public void PartidaFinalizadaComJardimJuntos() {
		
		
	}
	
	
	
	
	
	
	
	


	
	
	
	
}
