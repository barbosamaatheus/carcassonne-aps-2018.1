package br.ufpb.dcx.aps.carcassone;

import java.util.ArrayList;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class Partida {

	private BolsaDeTiles tiles;
	private Tile proximoTile;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("  ");
	
	private ArrayList<Jogador> jogadores; 
	private EstadoTurno estadoTurno = EstadoTurno.Tile_Posicionado;
	private boolean partidaEmAndamento = true;
	private Tile tileTurnoAtual;
	int jogadorAtual = 0;
	private boolean estadoMeeple = false;

	public Partida (BolsaDeTiles tiles, Cor ...sequencia) {
		this.tiles = tiles;
		pegarProximoTile();
		tileTurnoAtual = proximoTile;
		jogadores = carregaJogadores(sequencia);
		tabuleiro.adicionarPrimeiroTile(tileTurnoAtual);
	}
	
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
		return relatorio;
	}

	private String getEstadoPartida() {
		return (isPartidaEmAndamento() ? "Em_Andamento" : "Partida_Finalizada" );
	}
	
	public String montaRelatorioTurno() {
		Jogador proximoJogador = jogadores.get(jogadorAtual % jogadores.size());
		return "Jogador: " + proximoJogador.getCor() +"\nTile: " + tileTurnoAtual.toString() + "\nStatus: "+ getEstadoTurno();
	}
	
	public void verificaPartidaFinalizou() {
		if(!isPartidaEmAndamento()){
			throw new ExcecaoJogo("Partida finalizada");
		}
	}
	
	public String relatorioTurno() {
		verificaPartidaFinalizou();
		return montaRelatorioTurno();
	}
	
	public boolean isTilePosicionado() {
		return getEstadoTurno() == EstadoTurno.Tile_Posicionado;
	}
	
	public Partida girarTile() {
		if(isTilePosicionado())
			throw new ExcecaoJogo("Não pode girar tile já posicionado");
		if(!isPartidaEmAndamento()){
			throw new ExcecaoJogo("Não pode girar tiles com a partida finalizada");
		}
		proximoTile.girar();
		return this;
	}

	private void pegarProximoTile() {
		proximoTile = tiles.pegar();
		if(proximoTile == null) {
			setPartidaEmAndamento(false);
		}else{
			
			proximoTile.reset();
		}
		tileTurnoAtual = proximoTile;
	}

	public Partida finalizarTurno() {
		pegarProximoTile();
		jogadorAtual++;		
		setEstadoTurno(EstadoTurno.Início_Turno);
		return this;
	}

	public Partida posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, proximoTile);
		setEstadoTurno(EstadoTurno.Tile_Posicionado);
		
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
		System.out.println("-----"+tileTurnoAtual.toString());
		System.out.println("---"+tileTurnoAtual.isMeeple());
		if(tileTurnoAtual.isMeeple()) {
			throw new ExcecaoJogo("Impossível posicionar Meeple: tile já contém Meeple Posicionado");
		}
		else {
			this.tileTurnoAtual.setMeeple(true);
			this.jogadores.get(jogadorAtual).setPontuacao(2);
		}
		
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
		return tabuleiro.toString();
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

	public void posicionarMeepleJardim() {
		// TODO Auto-generated method stub
		
	}
	
}
