/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig fÃ¶rfattare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
    LinkedList<String> closestWords = null;

    int closestDistance = -1;



    static int getmin(int x,int y,int z)
    {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }

    int partDist(String w1, String w2, int w1len, int w2len) {


        int matrix[][] = new int[w1len + 1][w2len + 1];

        for (int i = 0; i <= w1len; i++) {
            for (int j = 0; j <= w2len; j++) {
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

        return matrix[w1len][w2len];
    }


    int Distance(String w1, String w2) {
        return partDist(w1, w2, w1.length(), w2.length());
    }

    public ClosestWords(String w, List<String> wordList) {
        for (String s : wordList) {
            int dist = Distance(w, s);
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