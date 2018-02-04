/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig fÃ¶rfattare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords2 {
    LinkedList<String> closestWords = null;

    int closestDistance = -1;

    int[][] oldMatrix;
    String oldWord;

    public int compareWords(String word1, String word2) {
        int x = 0;
        while (word1.charAt(x) == word2.charAt(x)){
            x++;

        }
        return x;
    }

    static int getmin(int x, int y, int z)
    {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }

    int[][] partDist(String w1, String w2, int w1len, int w2len) {
        int compareVal = compareWords(oldWord,w2);

        int matrix[][] = new int[w1len + 1][w2len + 1];
        for (int i = 0; i <= w1len; i++){
            for (int j=0; j < compareVal; j++){
                matrix[i][j] = oldMatrix[i][j];
            }
        }
        for (int i = 0; i <= w1len; i++) {

            for (int j = compareVal; j <= w2len; j++) {
                if (i == 0)
                    matrix[i][j] = j;

                else if (j == 0)
                    matrix[i][j] = i;
                else if (w1.charAt(i - 1) == w2.charAt(j - 1))
                    matrix[i][j] = matrix[i - 1][j - 1];
                else
                    matrix[i][j] = 1 + getmin(matrix[i][j - 1],
                            matrix[i - 1][j],
                            matrix[i - 1][j - 1]);



            }
        }
        oldMatrix = matrix;
        return matrix;
    }


    int Distance(String w1, String w2) {
        return partDist(w1, w2, w1.length(), w2.length())[w1.length()][w2.length()];
    }

    public ClosestWords2(String w, List<String> wordList) {
        //oldMatrix = new int[10][10];
        for (String s : wordList) {
            int dist = Distance(w, s);
            oldWord = s;
            // System.out.println("d(" + w + "," + s + ")=" + dist);
            if (dist < closestDistance || closestDistance == -1) {
                closestDistance = dist;
                closestWords = new LinkedList<String>();
                closestWords.add(s);
            }
            else if (dist == closestDistance)
                closestWords.add(s);
        }
    }

    int getMinDistance() {
        return closestDistance;
    }

    List<String> getClosestWords() {
        return closestWords;
    }
}