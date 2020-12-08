package game.frontend;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.util.List;

public class BoardPanel extends TilePane {

	private StackPane[][] cells;

	public BoardPanel(final int rows, final int columns, final int cellSize) {
		setPrefRows(rows);
		setPrefColumns(columns);
		setPrefTileHeight(cellSize);
		setPrefTileWidth(cellSize);
		this.cells = new StackPane[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new StackPane();
				getChildren().add(cells[i][j]);
			}
		}
	}
	
	public void setImage(int row, int column, Image image) {
		cells[row][column].getChildren().add(new ImageView(image));
	}

	public void setEffect(int row, int column, Node n) {
		if ( n != null ) cells[row][column].getChildren().add(n);
	}

}