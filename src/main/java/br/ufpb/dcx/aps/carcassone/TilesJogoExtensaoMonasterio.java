package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoLado;

public class TilesJogoExtensaoMonasterio extends TipoTileCarcassonne {
	
	private boolean jardim;
	
	public TilesJogoExtensaoMonasterio(Origem origem, TipoLado ladoNorte, TipoLado ladoLeste, TipoLado ladoSul, TipoLado ladoOeste,
			boolean escudo, boolean mosteiro, boolean cidadeContinua, boolean jardim) {
		super(origem, ladoNorte, ladoLeste, ladoSul, ladoOeste, escudo, mosteiro, cidadeContinua);
		this.jardim = jardim;
	}
	
	public boolean isJardim() {
		return jardim;
	}

	public void setJardim(boolean jardim) {
		this.jardim = jardim;
	}

}
