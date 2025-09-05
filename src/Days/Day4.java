package Days;

import Interfaces.IAOCPuzzle;

import java.io.*;

public class Day4 implements IAOCPuzzle<String[][], Integer> {

    private int MAX_X = 0;
    private int MAX_Y = 0;

    public Day4() {
    }

    public Integer part1() {
        String[][] input = getPuzzleInput(getClass().getSimpleName());
        return part1(input);
    }

    public Integer part2() {
        String[][] input = getPuzzleInput(getClass().getSimpleName());
        return part2(input);
    }


    public String[][] getPuzzleInput(String filename) {

        String[][] input = null;

        String inputFile = "C:\\Users\\morle\\IdeaProjects\\AdventOfCode\\Input\\" + filename + ".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputFile)));
            int r = 0;
            String line;
            while((line = reader.readLine()) != null) {

                if(input == null) {
                    input = new String[line.length()][line.length()];
                }

                String[] row = line.split("");

                for(int c = 0; c < row.length; c++) {
                    input[r][c] = row[c];
                }
                r++;
            }

        } catch(IOException ioe) {
            throw new RuntimeException(ioe);
        }

        MAX_X = input.length;
        MAX_Y = input[0].length;

        return input;
    }

    private int part1(String[][] input) {

        int count = 0;
        final int[][] dirs = {{0,1}, {0,-1}, {-1,0}, {1,0},{-1,-1}, {-1,1}, {1,-1}, {1,1}};
        final String XMAS = "XMAS";

        for(int r = 0; r < input.length; r++) {
            for(int c = 0; c < input[0].length; c++) {

                //ITERATE FOR EACH DIRECTION FROM THE CURRENT POSITION
                for(int d = 0; d < dirs.length; d++) {

                    int x = dirs[d][0];
                    int y = dirs[d][1];
                    boolean ok = true;

                    for(int i = 0; i < XMAS.length(); i++) {

                        int posX = r + (x * i);
                        int posY = c + (y * i);

                        if(!inBounds(posX,posY) || !input[posX][posY].equals(Character.toString(XMAS.charAt(i)))) {
                            ok = false;
                            break;
                        }
                    }
                    count += (ok) ? 1 : 0;
                }
            }
        }
        return count;
    }

    private int part2(String[][] input) {

        int count = 0;
        for(int r = 0; r < input.length; r++) {
            for(int c = 0; c < input[0].length; c++) {

                //CENTRE POINT OF AN X-MAS is an "A"
                if(input[r][c].equals("A")) {

                    //THESE IS THE X-MAS FILTER ARRAY ROTATED
                    String[] X_MAS = {"SSMM", "MSSM", "MMSS", "SMMS"};

                    //CHECK EACH OF THE DIAGONAL POSITIONS ARE VALID
                    if(inBounds(r+1,c+1) && inBounds(r-1, c+1) &&
                            inBounds(r-1, c-1) && inBounds(r+1, c-1))
                    {

                        for(int x = 0; x < X_MAS.length; x++) {
                            String toCheck = X_MAS[x];

                            if(input[r+1][c+1].equals(Character.toString(toCheck.charAt(0))) &&
                                input[r-1][c+1].equals(Character.toString(toCheck.charAt(1))) &&
                                input[r-1][c-1].equals(Character.toString(toCheck.charAt(2))) &&
                                input[r+1][c-1].equals(Character.toString(toCheck.charAt(3))))
                            {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < MAX_X && y < MAX_Y;
    }

}
