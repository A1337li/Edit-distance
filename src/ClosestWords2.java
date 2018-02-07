/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig fÃ¶rfattare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords2 {
    LinkedList<String> closestWords = null;

    int closestDistance = -1;

    int[][] oldMatrix = new int[40][40];
    String oldWord = "q";

    int[][] partDist(String w1, String w2, int w1len, int w2len) {
        int compareVal = compareWords(oldWord,w2);

        for (int i = 0; i <= w1len; i++) {
            for (int j = compareVal; j <= w2len; j++) {
                if (i == 0 || j == 0)
                    oldMatrix[i][j] = i+j;
                else if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    oldMatrix[i][j] = oldMatrix[i - 1][j - 1];
                }
                else {
                    oldMatrix[i][j] = 1 + getmin(oldMatrix[i][j - 1],
                            oldMatrix[i - 1][j],
                            oldMatrix[i - 1][j - 1]);
                }
            }
        }

        return oldMatrix;
    }


    int Distance(String w1, String w2) {
        return partDist(w1, w2, w1.length(), w2.length())[w1.length()][w2.length()];
    }

    public ClosestWords2(String w, List<String> wordList) {
        for (String s : wordList) {
            if (closestDistance != -1 && Math.abs(s.length()-w.length()) > closestDistance){
                continue;
            }

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

    public int compareWords(String word1, String word2) {
        int x = 0;
        int y = Math.min(word1.length(), word2.length());
        while (x < y && word1.charAt(x) == word2.charAt(x)){
            x++;
        }
        return x;
    }

    int getmin(int x, int y, int z) {
        return Math.min(x, Math.min(y,z));
    }

}