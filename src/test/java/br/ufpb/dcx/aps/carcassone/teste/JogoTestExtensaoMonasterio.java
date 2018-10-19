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
	public void monasterioCompletoComMeeple() {
		mockarTiles(tiles, t30, t48, t29, t60, t27, t52, t31, t61, t62);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		//Turno 2- Tile: 48		
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleMosteiro();
		
		verificarRelatorioTurno(partida, "PRETO", "48N", "Tile_Posicionado");
		//Verificar se o jogador PRETO está com um Meeple a menos
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");
		
		partida.finalizarTurno();
		
		//Turno 3- Tile: 29
		girar(partida, 1);
		partida.posicionarTile(t48, LESTE);
		
		partida.finalizarTurno();
		
		//Turno 4- Tile: 60
		partida.posicionarTile(t30, LESTE);
		partida.finalizarTurno();
				
		//Turno 5- Tile: 27
		partida.posicionarTile(t30, OESTE);
		partida.finalizarTurno();
		
		//Turno 6- Tile: 52
		partida.posicionarTile(t27, SUL);
		partida.finalizarTurno();
				
		//Turno 7- Tile: 31
		girar(partida, 3);
		partida.posicionarTile(t52, SUL);
		partida.finalizarTurno();
		
		//Turno 8- Tile: 61
		partida.posicionarTile(t48, SUL);
		partida.finalizarTurno();
		
		//Turno 9- Tile: 62
		girar(partida, 1);
		partida.posicionarTile(t61,LESTE);
		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(9,7)");
		
	}
	
	
	/**
	 * Caso de teste 02
	 * Se o monastério é completado mas não tem nenhum Meeple, não deve somar pontuação a nenhum jogador.
	 */
	@Test
	public void monasterioCompletoSemMeeple() {
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
					
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		
	}
	
	
	/**
	 * Caso de teste 03
	 * Ao final de um turno com a partida em andamento em que o o monastério esteja incompleto, 
	 * a pontuação do jogador não é alterada e o Meeple permanece no monastério.
	 */
	@Test
	public void monasterioIncompletoComPartidaEmAndamento() {
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
	public void monasterioComDoisMeeples() {
		mockarTiles(tiles, t30, t48);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleMosteiro();
		
		ocorreExcecaoJogo(() -> partida.posicionarMeepleMosteiro(),
				"Impossível posicionar Meeple: tile já contém Meeple Posicionado");
		
		verificarRelatorioTurno(partida, "PRETO", "48N", "Tile_Posicionado");
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");	
	}
	
	 
	/**
	 * Caso de teste 05
	 * Se a partida finaliza com o monastério incompleto e há um Meeple, a pontuação do jogador será 
	 * a quantidade de tiles ao redor do monastério.  
	 */
	@Test
	public void	partidaFinalizadaComMonasterioIncompleto() {
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
	public void jardimCompleto() {
		mockarTiles(tiles, t30, t73, t29, t31, t01, t03, t27, t08, t02);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		//Tuno 2- Tile: 73
		partida.posicionarTile(t30, LESTE);	
		partida.posicionarMeepleJardim();
		
		verificarRelatorioTurno(partida, "PRETO", "73N", "Tile_Posicionado");		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");		
		
		partida.finalizarTurno();
		
		//Turno 3- Tile: 29
		girar(partida, 2);
		partida.posicionarTile(t30, NORTE);		
		partida.finalizarTurno();
		
		//Turno 4- Tile: 31
		girar(partida, 1);
		partida.posicionarTile(t73, NORTE);
		partida.finalizarTurno();
		
		//Turno 5- Tile: 01
		partida.posicionarTile(t31, LESTE);
		partida.finalizarTurno();
				
		//Turno 6- Tile: 03
		girar(partida, 2);
		partida.posicionarTile(t30, SUL);
		partida.finalizarTurno();
				
		//Turno 7- Tile: 27
		girar(partida, 2);
		partida.posicionarTile(t73, LESTE);
		partida.finalizarTurno();
	
		//Turno 8- Tile: 08
		girar(partida, 2);
		partida.posicionarTile(t73, SUL);
		partida.finalizarTurno();
		
		//Turno 9- Tile: 02
		partida.posicionarTile(t08,LESTE);
					
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(9,7)");
		
	}
	
	
	/**
	 * Caso de teste 07
	 * Se o jardim é completado mas não tem nenhum Meeple, não deve somar pontuação a nenhum jogador.
	 */
	@Test
	public void jardimCompletoSemMeeple() {
		mockarTiles(tiles, t30, t73, t29, t31, t01, t03, t27, t08, t02);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, LESTE);	
				
		verificarRelatorioTurno(partida, "PRETO", "73N", "Tile_Posicionado");		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");		
		
		partida.finalizarTurno();
		
		girar(partida, 2);
		partida.posicionarTile(t30, NORTE);		
		partida.finalizarTurno();
		
		girar(partida, 1);
		partida.posicionarTile(t73, NORTE);
		partida.finalizarTurno();
		
		partida.posicionarTile(t31, LESTE);
		partida.finalizarTurno();
		
		girar(partida, 2);
		partida.posicionarTile(t30, SUL);
		partida.finalizarTurno();
		
		girar(partida, 2);
		partida.posicionarTile(t73, LESTE);
		partida.finalizarTurno();

		girar(partida, 2);
		partida.posicionarTile(t73, SUL);
		partida.finalizarTurno();
		
		partida.posicionarTile(t08,LESTE);
					
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
	}
	
	
	/**
	 * Caso de teste 08
	 * Ao final de um turno com a partida em andamento em que o jardim esteja incompleto, 
	 * a pontuação do jogador não é alterada e o Meeple permanece no jardim.
	 */
	@Test
	public void jardimIncompletoComPartidaEmAndamento() {	
		mockarTiles(tiles, t30, t73, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();

		partida.posicionarTile(t30, LESTE);	
		partida.posicionarMeepleJardim();
		
		verificarRelatorioTurno(partida, "PRETO", "73N", "Tile_Posicionado");		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");		
		partida.finalizarTurno();
		
		girar(partida, 2);
		partida.posicionarTile(t30, NORTE);		
	
		verificarRelatorioTurno(partida, "VERDE", "29S", "Tile_Posicionado");		
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,6)");		
	}
	
	
	/**
	 * Caso de teste 09
	 * Verifica se lança exceções para tentativas de adicionar Meeples em um jardim já ocupado por um outro Meeple.
	 */
	@Test
	public void jardimComDoisMeeples() {
		mockarTiles(tiles, t30, t73);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleJardim();
		
		ocorreExcecaoJogo(() -> partida.posicionarMeepleJardim(),
				"Impossível posicionar Meeple: tile já contém Meeple Posicionado");
		verificarRelatorioTurno(partida, "PRETO", "73N", "Tile_Posicionado");
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
	}
	
	
	/**
	 * Caso de teste 10
	 * Se a partida finaliza com o Jardim incompleto e há um Meeple, a pontuação do jogador será 
	 * a quantidade de tiles ao redor do jardim.  
	 */
	@Test
	public void	partidaFinalizadaComJardimIncompleto() {
		mockarTiles(tiles, t30, t73);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);	
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, LESTE);
		partida.posicionarMeepleJardim();
		
		verificarRelatorioTurno(partida, "PRETO", "73N", "Tile_Posicionado");
		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(2,7)");
	}
}
