import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class ChessState {

	private JFrame Frame;
	public String[][] board; // represents the 2d board
	public int[][] Queens; // Queens[i][0] maps x for i-th Queen, and Queens[i][1] maps y for i-th Queen
	public int[][] Knights; // Knights[i][0] maps x for i-th Knight, and Knight[i][1] maps y for i-th
							// Knight[i][2] represents stamina.
	// public int[] Stamina; // keeps tack of the knight's stamina.
	public int queencount;

	public ChessState(String[][] board, int[][] Queens, int[][] Knights) {

		this.board = board;
		this.Queens = Queens;
		this.Knights = Knights;
		// this.Stamina = Stamina;
		this.queencount = Queens.length;

	}

	public void view() {
		this.Frame = new JFrame("Chess");
		final JMenuBar tableMenueBar = new JMenuBar();
		populateMenueBar(tableMenueBar);
		this.Frame.setJMenuBar(tableMenueBar);
		this.Frame.setSize(400, 400);
		this.Frame.setVisible(true);
	}

	private void populateMenueBar(final JMenuBar tableMenueBar) {
		tableMenueBar.add(createFileMenu());
	}

	private JMenu createFileMenu() {
		final JMenu fileMenu = new JMenu("Options");
		final JMenuItem openPGN = new JMenuItem("Load PGN");
		openPGN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("open up that pgn :D");

			}
		});
		fileMenu.add(openPGN);
		return fileMenu;
	}

	public ChessState inspire() {

		for (int i = 0; i < this.Knights.length; i++) {
			this.Knights[i][2] = this.Knights[i][2] + 1;
		}

		return new ChessState(this.board, this.Queens, this.Knights);

	}

	public ChessState move(int knight, int direction) {

		boolean m = false;
		//int[][] knight_copy = DeepCopy(this.Knights);
		int[][] knight_copy = this.Knights.clone();
		String[][] newboard = new String[this.board.length][this.board.length];
		int[][] newqueens;

		if (Knights[knight][2] > 0)
			switch (direction) {

			case 1:
				if ((knight_copy[knight][0] + 1 < this.board[1].length) && (knight_copy[knight][0] + 1 >= 0)) {
					if ((knight_copy[knight][1] - 2 < this.board.length) && (knight_copy[knight][1] - 2 >= 0)) {
					

								knight_copy[knight][0] += 1;
								knight_copy[knight][1] -= 2;
								knight_copy[knight][2]--;
								
								newqueens=this.Queens.clone();

								ChessState c = new ChessState(newboard, newqueens, knight_copy);
								c.killqueens(knight);
								return c;
						
					}else break;
				} else
					break;

			case 2:
				if ((knight_copy[knight][0] + 2 < this.board[1].length) && (knight_copy[knight][0] + 2 >= 0)) {
					if ((knight_copy[knight][1] - 1 < this.board[1].length) && (knight_copy[knight][1] - 1 >= 0)) {

						knight_copy[knight][0] += 2;
						knight_copy[knight][1] -= 1;
						knight_copy[knight][2]--;
						ChessState c = new ChessState(this.board, this.Queens, knight_copy);
						c.killqueens(knight);
						return c;
					} else
						return null;
				} else
					return null;

			case 3:
				if ((knight_copy[knight][0] + 2 < this.board[1].length) && (knight_copy[knight][0] + 2 >= 0)) {
					if ((knight_copy[knight][1] + 1 < this.board.length) && (knight_copy[knight][1] + 1 >= 0)) {

						knight_copy[knight][0] += 2;
						knight_copy[knight][1] += 1;
						knight_copy[knight][2]--;
						ChessState c = new ChessState(this.board, this.Queens, knight_copy);
						c.killqueens(knight);
						return c;
					} else
						return null;
				} else
					return null;
			case 4:
				if ((knight_copy[knight][0] + 1 < this.board[1].length) && (knight_copy[knight][0] + 1 >= 0)) {
					if ((knight_copy[knight][1] + 2 < this.board.length) && (knight_copy[knight][1] + 2 >= 0)) {

						knight_copy[knight][0] += 1;
						knight_copy[knight][1] += 2;
						knight_copy[knight][2]--;
						ChessState c = new ChessState(this.board, this.Queens, knight_copy);
						c.killqueens(knight);
						return c;
					} else
						return null;
				} else
					return null;
			case 5:
				if ((knight_copy[knight][0] - 1 < this.board[1].length) && (knight_copy[knight][0] - 1 >= 0)) {
					if ((knight_copy[knight][1] + 2 < this.board.length) && (knight_copy[knight][1] + 2 >= 0)) {

						knight_copy[knight][0] -= 1;
						knight_copy[knight][1] += 2;
						knight_copy[knight][2]--;
						ChessState c = new ChessState(this.board, this.Queens, knight_copy);
						c.killqueens(knight);
						return c;
					} else
						return null;
				} else
					return null;
			case 6:
				if ((knight_copy[knight][0] - 2 < this.board[1].length) && (knight_copy[knight][0] - 2 >= 0)) {
					if ((knight_copy[knight][1] + 1 < this.board.length) && (knight_copy[knight][1] + 1 >= 0)) {

						knight_copy[knight][0] -= 2;
						knight_copy[knight][1] += 1;
						knight_copy[knight][2]--;
						ChessState c = new ChessState(this.board, this.Queens, knight_copy);
						c.killqueens(knight);
						return c;
					} else
						return null;
				} else
					return null;
			case 7:
				if ((knight_copy[knight][0] - 2 < this.board[1].length) && (knight_copy[knight][0] - 2 >= 0)) {
					if ((knight_copy[knight][1] - 1 < this.board.length) && (knight_copy[knight][1] - 1 >= 0)) {

						knight_copy[knight][0] -= 2;
						knight_copy[knight][1] -= 1;
						knight_copy[knight][2]--;
						ChessState c = new ChessState(this.board, this.Queens, knight_copy);
						c.killqueens(knight);
						return c;
					} else
						return null;
				} else
					return null;
			case 8:
				if ((knight_copy[knight][0] - 1 < this.board[1].length) && (knight_copy[knight][0] - 1 >= 0)) {
					if ((knight_copy[knight][1] - 2 < this.board.length) && (knight_copy[knight][1] - 2 >= 0)) {

						knight_copy[knight][0] -= 1;
						knight_copy[knight][1] -= 2;
						knight_copy[knight][2]--;
						ChessState c = new ChessState(this.board, this.Queens, knight_copy);
						c.killqueens(knight);
						return c;
					} else
						return null;
				} else
					return null;

			default:
				return null;

			}

		return null;
	}

	public void reduceStamina() {
		for (int i = 0; i < Knights.length; i++) {
			Knights[i][2]--;
		}

	}

	public void killqueens(int knight) {
		for (int i = 0; i < queencount; i++) {
			if ((this.Knights[knight][0] == this.Queens[i][0]) && (this.Knights[knight][1] == this.Queens[i][1])) {

				int[][] newqueens = new int[queencount - 1][2];
				for (int j = 0; j < queencount; j++) {
					if (j == i) {
					} else if (j < i)
						newqueens[j] = Queens[i];
					else
						newqueens[j] = Queens[i + 1];
					Queens = newqueens;
					queencount--;

				}
				return;
			}

		}

	}

	public static int[][] DeepCopy(int[][] arrIN) {
		int[][] arrOUT = new int[arrIN.length][arrIN[0].length];
		if (arrIN != null) {
			for (int i = 0; i < arrIN.length; i++) {
				arrOUT[i] = arrIN[i];
			}
		}
		return arrOUT;

	}
	public static int[] DeepCopy(int[] arrIN) {
		int[] arrOUT = new int[arrIN.length];
		if (arrIN != null) {
			for (int i = 0; i < arrIN.length; i++) {
				arrOUT[i] = arrIN[i];
			}
		}
		return arrOUT;

	}

	public void PopulateGrid() {
		this.board = new String[this.board.length][this.board.length];
		for (int i = 0; i < this.Queens.length; i++) {

			this.board[Queens[i][1]][Queens[i][0]] = "  Q  ";
		}

		for (int i = 0; i < this.Knights.length; i++) {

			this.board[Knights[i][1]][Knights[i][0]] = "K" + "(" + Knights[i][2] + ")";
		}

	}

	public String toString() {
		this.PopulateGrid();
		String r = "";

		r += this.getID() + "\n";

		r += "-----------------Field----------------\n";
		for (int i = 0; i < this.board.length; i++) {
			r += "||";
			for (int j = 0; j < this.board[0].length; j++) {

				if (this.board[i][j] != null) {
					r += this.board[i][j] + "|";
				}

				else {
					r += " " + this.board[i][j] + " |";
				}
			}

			r += "|\n";
		}
		r += "**************************************";
		return r;
	}

	public String getID() {
		this.PopulateGrid();
		String ret = "";
		for (String[] sr : board) {
			for (String s : sr) {
				if (s == null) {
					ret += "00";
				} else {

					ret += s.strip();
				}
				ret += ".";
			}
		}

		return ret;
	}

}
