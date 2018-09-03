package br.ufpb.dcx.aps.carcassone;

public class Jogador {
	private String cor;
	private int pontuacao;
	private int meeples;
	
	Jogador(String cor){
		this.cor = cor;
		this.setPontuacao(0);
		this.setMeeples(7);		
	}
	
	@Override
	public String toString() {
		return cor +"(" + getPontuacao() + "," + getMeeples() + ")";
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	public int getMeeples() {
		return meeples;
	}
	public void setMeeples(int meeples) {
		this.meeples = meeples;
	}
	
	
}
