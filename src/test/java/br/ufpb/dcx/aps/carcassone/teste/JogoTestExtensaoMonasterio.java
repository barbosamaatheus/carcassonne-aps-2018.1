package br.ufpb.dcx.aps.carcassone.teste;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import br.ufpb.dcx.aps.carcassone.BolsaDeTiles;
import br.ufpb.dcx.aps.carcassone.JogoExtensaoMonasterio;

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
	public void MonasterioCompleto() {
		
	}
	
	/**
	 * Caso de teste 02
	 * Se o monastério é completado mas não tem nenhum Meeple, não deve somar pontuação a nenhum jogador.
	 */
	@Test
	public void MonasterioCompletoSemMeeple() {
		
	}
	
	/**
	 * Caso de teste 03
	 * Ao final de um turno com a partida em andamento em que o o monastério esteja incompleto, 
	 * a pontuação do jogador não é alterada e o Meeple permanece no monastério.
	 */
	@Test
	public void MonasterioIncompletoComPartidaEmAndamento() {
		
	}
	
	/**
	 * Caso de teste 04
	 * Verifica se lança exceções para tentativas de adicionar Meeples em um Monastério já ocupado por um outro Meeple.
	 */
	@Test
	public void MonasterioComDoisMeeples() {
		
	}
	
	
	/**
	 * Caso de teste 05
	 * Se a partida finaliza com o monastério incompleto e há um Meeple, a pontuação do jogador será 
	 * a quantidade de tiles ao redor do monastério.  
	 */
	@Test
	public void	PartidaFinalizadaComMonasterioIncompleto() {
		
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
