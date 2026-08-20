import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'componentsInGraph' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY gb as parameter.
     */

    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {

    int[] parent = new int[100001];
    int[] size = new int[100001];

    // Initialize
    for (int i = 1; i <= 100000; i++) {
        parent[i] = i;
        size[i] = 1;
    }

    // Find function
    for (List<Integer> edge : gb) {
        int a = edge.get(0);
        int b = edge.get(1);

        int rootA = a;
        while (parent[rootA] != rootA) {
            rootA = parent[rootA];
        }

        int rootB = b;
        while (parent[rootB] != rootB) {
            rootB = parent[rootB];
        }

        // Union
        if (rootA != rootB) {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    // Find component sizes
    for (int i = 1; i <= 100000; i++) {
        if (parent[i] == i && size[i] > 1) {
            min = Math.min(min, size[i]);
            max = Math.max(max, size[i]);
        }
    }

    List<Integer> result = new ArrayList<>();
    result.add(min);
    result.add(max);

    return result;
}

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> gb = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] gbRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> gbRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int gbItem = Integer.parseInt(gbRowTempItems[j]);
                gbRowItems.add(gbItem);
            }

            gb.add(gbRowItems);
        }

        List<Integer> result = Result.componentsInGraph(gb);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
