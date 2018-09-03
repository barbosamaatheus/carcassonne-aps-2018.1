package br.ufpb.dcx.aps.carcassone;

import java.util.ArrayList;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class Partida {

	private BolsaDeTiles tiles;
	private Tile proximoTile;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("  ");
	
	//TESTE2
	private ArrayList<Jogador> jogadores; 
	private EstadoTurno estadoTurno = EstadoTurno.Tile_Posicionado;
	private boolean partidaEmAndamento = true;
	private Tile tileTurnoAtual;
	private int jogadorAtual = 0;

	Partida(BolsaDeTiles tiles, Cor ...sequencia) {
		this.tiles = tiles;
		pegarProximoTile();
		tileTurnoAtual = proximoTile;
		jogadores = carregaJogadores(sequencia);
	}
	
	//TESTE 2
	public ArrayList<Jogador> carregaJogadores(Cor... sequencia){
		ArrayList<Jogador> temp = new ArrayList<Jogador>();
		for(Cor cor: sequencia) {
			temp.add(new Jogador(cor.name())); 
		}
		return temp;
	}

	
	public String relatorioPartida() {
		String relatorio = "Status: " + this.getEstadoPartida() + "\nJogadores: ";
		for(Jogador j :jogadores) { 
			if(j == jogadores.get(jogadores.size()-1)){
				relatorio +=  j.toString();
			}else relatorio +=  j.toString() + "; ";
		}
		System.out.println(jogadores.toString());
		System.out.println(relatorio);
		return relatorio;
	}

	private String getEstadoPartida() {
		return (isPartidaEmAndamento() ? "Em_Andamento" : "Partida_Finalizada" );
	}
	
	public String montaRelatorioTurno() {
		return "Jogador: " + jogadores.get(jogadorAtual).getCor() +"\nTile: " + tileTurnoAtual.toString() + "\nStatus: "+ getEstadoTurno();
	}
	
	public String relatorioTurno() {
		/* TESTE 3
		 * if(!isPartidaEmAndamento())
			throw new ExcecaoJogo("Partida finalizada");
			*/
		return montaRelatorioTurno();
	}
	//// FIM TESTE 2
	
	public Partida girarTile() {
		proximoTile.girar();
		return this;
	}

	private void pegarProximoTile() {
		proximoTile = tiles.pegar();
		/*TESTE 3
		if(proximoTile == null) {
			setPartidaEmAndamento(false);
		}else*/
			proximoTile.reset();
	}

	public Partida finalizarTurno() {
		pegarProximoTile();
		return this;
	}

	public Partida posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, proximoTile);
		return this;
	}

	public Partida posicionarMeepleEstrada(Lado lado) {
		return this;
	}

	public Partida posicionarMeepleCampo(Vertice vertice) {
		return this;
	}

	public Partida posicionarMeepleCidade(Lado lado) {
		return this;
	}

	public Partida posicionarMeepleMosteiro() {
		return this;
	}

	public String getEstradas() {
		return null;
	}

	public String getCampos() {
		return null;
	}

	public String getCidades() {
		return null;
	}

	public String getMosteiros() {
		return null;
	}

	public String relatorioTabuleiro() {
		return tileTurnoAtual.toString();
	}
	
	public boolean isPartidaEmAndamento() {
		return partidaEmAndamento;
	}

	public void setPartidaEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}
	public EstadoTurno getEstadoTurno() {
		return estadoTurno;
	}
	public void setEstadoTurno(EstadoTurno estadoTurno) {
		this.estadoTurno = estadoTurno;
	}
	
}
