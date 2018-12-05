package com.neet.MapViewer.Main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.scene.canvas.Canvas;

public class TileMapViewer {

    public int numCols;
    public int numRows;

    private int[][] mapMatrix;
    private int[][] tileType;

    private Image tileset;
    private int numTilesAcross;
    private int tileSize = 16;
    private Image items;

    public Canvas mainCanvas;

    public void loadMap(String mapFile)
    {
        try
        {
            InputStream in = getClass().getResourceAsStream(mapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());

            mapMatrix = new int[numRows][numCols];

            String delims = "\\s+";
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++) {
                    mapMatrix[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void loadImages(String tilesImage)
    {
        try
        {
            tileset = new Image(TileMapViewer.class.getResourceAsStream(tilesImage));
            numTilesAcross = (int)tileset.getWidth() / tileSize;

            System.out.println(numTilesAcross);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initMapCanvas()
    {
        mainCanvas = new Canvas(640, 640);
        tileType = new int[numRows][numCols];

        for(int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numCols; col++)
            {
                if(mapMatrix[row][col] == 0)
                {
                    continue;
                }

                int rowCol = mapMatrix[row][col];
                System.out.println(rowCol);
                int R = rowCol / numTilesAcross;
                int C = rowCol % numTilesAcross;

                if(R == 0)
                {
                    mainCanvas.getGraphicsContext2D().drawImage(
                            tileset, C * tileSize, 0, tileSize, tileSize,
                            col * tileSize, row * tileSize, tileSize, tileSize);
                    tileType[row][col] = 0;
                }
                else
                {
                    mainCanvas.getGraphicsContext2D().drawImage(
                            tileset, C * tileSize, tileSize, tileSize, tileSize,
                            col * tileSize, row * tileSize, tileSize, tileSize);
                    tileType[row][col] = 1;
                }
            }
        }

    }
}
