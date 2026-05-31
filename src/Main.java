import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(System.in);
        int n = lerInt(in), m = lerInt(in), k = lerInt(in);

        List<long[]>[] adj = new List[n + 1];
        for (int v = 1; v <= n; v++) adj[v] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = lerInt(in), b = lerInt(in); long c = lerInt(in);
            adj[a].add(new long[]{b, c});
        }

        int[] count = new int[n + 1];
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[0], y[0]));
        pq.add(new long[]{0, 1});                       

        StringBuilder sb = new StringBuilder();
        int achados = 0;
        while (!pq.isEmpty() && achados < k) {
            long[] cur = pq.poll();                     
            long d = cur[0]; int u = (int) cur[1];
            if (count[u] >= k) continue;
            count[u]++;
            if (u == n) { sb.append(d).append(' '); achados++; }
            for (long[] e : adj[u]) {                   
                int v = (int) e[0];
                if (count[v] < k) pq.add(new long[]{d + e[1], v});
            }
        }
        System.out.println(sb.toString().trim());
    }

    private static int lerInt(DataInputStream in) throws IOException {
        int x = 0, b = in.read();
        while (b < '0') b = in.read();                  
        while (b >= '0') { x = x * 10 + b - '0'; b = in.read(); }
        return x;
    }
}
