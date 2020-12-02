package xadrez.pe�as;

import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Partida;
import xadrez.Pe�aXadrez;

public class Rei extends Pe�aXadrez {

	private Partida partida;

	public Rei(Tabuleiro tabuleiro, Cor cor, Partida partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public String toString() {
		if(getCor() == Cor.Brancas) {
			return "\u2654";
		} else {
			return "\u265A";
		}
	}

	private boolean podeMover(Posi��o posi��o) {
		Pe�aXadrez p = (Pe�aXadrez) getTabuleiro().pe�a(posi��o);
		return p == null || p.getCor() != getCor();
	}

	private boolean testarRookpossivel(Posi��o posi��o) {
		Pe�aXadrez p = (Pe�aXadrez) getTabuleiro().pe�a(posi��o);
		return p != null && p instanceof Torre && p.getCor() == getCor() && getcontagemMovimentos() == 0;
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posi��o p = new Posi��o(0, 0);

		// Acima
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna());
		if (getTabuleiro().posi��oExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Baixo
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna());
		if (getTabuleiro().posi��oExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Esquerda
		p.setValores(posi��o.getLinha(), posi��o.getColuna() - 1);
		if (getTabuleiro().posi��oExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Direita
		p.setValores(posi��o.getLinha(), posi��o.getColuna() + 1);
		if (getTabuleiro().posi��oExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Acima + Esquerda
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 1);
		if (getTabuleiro().posi��oExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Acima + Direita
		p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 1);
		if (getTabuleiro().posi��oExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Baixo + Esquerda
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 1);
		if (getTabuleiro().posi��oExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// Baixo + Direita
		p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 1);
		if (getTabuleiro().posi��oExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Roque
		if (getcontagemMovimentos() == 0 && !partida.getCheck()) {
			// Roque pequeno
			Posi��o posTorreRei = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 3);
			if (testarRookpossivel(posTorreRei)) {
				Posi��o p1 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 1);
				Posi��o p2 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() + 2);
				if (getTabuleiro().pe�a(p1) == null && getTabuleiro().pe�a(p2) == null) {
					mat[posi��o.getLinha()][posi��o.getColuna() + 2] = true;
				}
			}

			// Roque grande
			Posi��o posTorreDama = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 4);
			if (testarRookpossivel(posTorreDama)) {
				Posi��o p1 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 1);
				Posi��o p2 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 2);
				Posi��o p3 = new Posi��o(posi��o.getLinha(), posi��o.getColuna() - 3);
				if (getTabuleiro().pe�a(p1) == null && getTabuleiro().pe�a(p2) == null && getTabuleiro().pe�a(p3) == null) {
					mat[posi��o.getLinha()][posi��o.getColuna() - 2] = true;
				}
			}
		}

		return mat;
	}
}