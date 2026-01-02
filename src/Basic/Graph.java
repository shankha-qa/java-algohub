package Basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {

    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt =  wt;
        }
    }

    public static void representGraph(){
        int vces = 7;
        ArrayList<Edge>[] graph =  new ArrayList[vces];

        for (int i = 0; i < vces; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,3,40));
        graph[0].add(new Edge(0,1,10));

        graph[1].add(new Edge(0,0,10));
        graph[1].add(new Edge(0,2,10));

        graph[2].add(new Edge(0,3,10));
        graph[2].add(new Edge(0,1,10));

        graph[3].add(new Edge(0,0,40));
        graph[3].add(new Edge(0,2,10));
        graph[3].add(new Edge(0,4,2));

        graph[4].add(new Edge(0,3,2));
        graph[4].add(new Edge(0,5,3));
        graph[4].add(new Edge(0,6,3));

        graph[5].add(new Edge(0,4,3));
        graph[5].add(new Edge(0,6,3));

        graph[6].add(new Edge(0,5,3));
        graph[6].add(new Edge(0,4,8));
    }

    // 2 vertices will be given. Check there is at least one path from src to destination
    public static boolean checkHasAtleastOnePath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        if(src == dest)
            return true;

        visited[src] = true;
        for(Edge edge: graph[src]) {
            if (!visited[edge.nbr]) {
                boolean doesNeighbourHasPath = checkHasAtleastOnePath(graph, edge.nbr, dest, visited);
                if (doesNeighbourHasPath) {
                    return true;
                }
            }
        }

        return false;
    }

    // 2 vertices will be given. Print all possible paths from src to destination
    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {
        if(src == dest) {
            System.out.println(psf);
            return;
        }
        visited[src] = true;
        for(Edge edge: graph[src]) {
            if (!visited[edge.nbr]) {
                printAllPaths(graph, edge.nbr, dest, visited, psf + " " + edge.nbr);
            }
        }
        visited[src] = false;
    }

    //Get all connected components of a graph in ArrayList
    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph, int vtces){
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        boolean[] visited = new boolean[vtces];

        for (int v = 0; v < vtces; v++) {
            if(!visited[v]) {
                ArrayList<Integer> comp = new ArrayList<>();
                drawTreeAndGenerateComponent(graph, v, comp, visited);
                comps.add(comp);
            }
        }
        System.out.println(comps);
        return comps;
    }

    public static void drawTreeAndGenerateComponent(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited) {
        visited[src] = true;
        comp.add(src);
        for (Edge edge : graph[src]){
            if(!visited[edge.nbr]) {
                drawTreeAndGenerateComponent(graph, edge.nbr, comp, visited);
            }
        }
    }

    //Check Is graph Connected ?
    public static boolean isGraphConnected(ArrayList<Edge>[] graph, int vtces){
        ArrayList<ArrayList<Integer>> comps = getConnectedComponents(graph, vtces);
        return (comps.size() == 1);
    }

    //Check in How many different combination can be found where pair of vertex can be created from groups which are separate
    public static int findUniqueCombination(ArrayList<Edge>[] graph, int vtces){
        ArrayList<ArrayList<Integer>> comps = getConnectedComponents(graph, vtces);
        int count = 0;
        for (int i = 0 ; i <= comps.size() - 1 ; i++){
            for (int j = i+1 ; j <= comps.size() - 1 ; j++){
                count += comps.get(i).size() * comps.get(j).size();
            }
        }
        return count;
    }

    //Count number of islands. Land is 0 and Water is 1
    public static int getTotalNumberOfIslands(int[][] ocean) {
        int count = 0;
        boolean[][] visited = new boolean[ocean.length][ocean[0].length];
        for (int r = 0; r <= ocean.length - 1; r++) {
            for (int c = 0; c <= ocean[0].length - 1; c++) {
                if (!visited[r][c] && ocean[r][c] == 0) {
                    getConnectedLand(ocean, r, c, visited);
                    count++;
                }
            }
        }
        return count;
    }

    public static void getConnectedLand(int[][] ocean, int row, int col, boolean[][] visited) {
        if( row < 0 || col < 0 || row > ocean.length - 1 || col > ocean[0].length - 1 ||
                visited[row][col] || ocean[row][col] == 1) {
            return;
        }

        visited[row][col] =  true;
        getConnectedLand(ocean, row - 1, col, visited);
        getConnectedLand(ocean, row , col + 1, visited);
        getConnectedLand(ocean, row , col - 1, visited);
        getConnectedLand(ocean, row + 1, col, visited);
    }

    //Check presence of Words in a matrix
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                // Start DFS only when first character matches
                if (board[r][c] == word.charAt(0)) {
                    if (search(board, r, c, 0, word, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean search(char[][] board, int r, int c,
                          int idx, String word, boolean[][] visited) {

        // boundary + visited + mismatch check
        if (r < 0 || c < 0 ||
                r >= board.length || c >= board[0].length ||
                visited[r][c] ||
                board[r][c] != word.charAt(idx)) {
            return false;
        }

        // word completely matched
        if (idx == word.length() - 1) {
            return true;
        }

        visited[r][c] = true;
        boolean found =
                search(board, r + 1, c, idx + 1, word, visited) ||
                search(board, r - 1, c, idx + 1, word, visited) ||
                search(board, r, c + 1, idx + 1, word, visited) ||
                search(board, r, c - 1, idx + 1, word, visited);
        visited[r][c] = false;

        return found;
    }

    //BFS  --- Concept
    static class Pair {
        int v;
        String psf;

        public Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }
    }

    public static void breadthFirstSearch(ArrayList<Edge>[] graph, int vertices, int src){

        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(src , src + ""));
        boolean[] visited = new boolean[vertices];

        //Remove Mark* Work Add*
        while(!queue.isEmpty()){
            //Remove
            Pair rem = queue.remove();

            //Mark*
            if(!visited[rem.v]){
                visited[rem.v] = true;
            }

            //Work
            System.out.println(rem.v + "@" + rem.psf);

            //Add*
            for(Edge e : graph[rem.v]){
                if(!visited[e.nbr]){
                    queue.add(new Pair(e.nbr , rem.psf + e.nbr));
                }
            }
        }
    }

    // Is graph cyclic ?
    public static boolean isCyclic(ArrayList<Edge>[] graph, int vertices){
        boolean[] visited = new boolean[vertices];
        for (int v = 0; v < vertices; v++) {
            if (!visited[v]) {
                boolean cycle = isGraphCyclic(graph, v, visited);
                if (cycle)
                    return true;
            }
        }
        return false;
    }

    public static boolean isGraphCyclic(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(src , src + ""));

        while(!queue.isEmpty()) {
            //Remove
            Pair rem = queue.remove();

            //Mark*
            if(!visited[rem.v]){
                visited[rem.v] = true;
            }
            else {
                return true;
            }

            //Add*
            for(Edge e : graph[rem.v]){
                if(!visited[e.nbr]){
                    queue.add(new Pair(e.nbr , rem.psf + e.nbr));
                }
            }
        }
        return false;
    }

    // Find time to sickness spread
    public static class SickPair{
        int people;
        int time;

        public SickPair(int people, int time) {
            this.people = people;
            this.time = time;
        }
    }

    public static void sicknessAnalysis(ArrayList<Edge>[] place, int people, int src, int providedTime) {
        int[] visited =  new int[people];

        ArrayDeque<SickPair> queue = new ArrayDeque<>();
        queue.add(new SickPair(src , 1));

        int count = 0;

        while (!queue.isEmpty()) {
            //Remove
            SickPair rem = queue.remove();

            //Mark *
            if (visited[rem.people] > 0){
                continue;
            }
            visited[rem.people] = rem.time;

            //Work
            if(rem.time > providedTime)
                break;
            count ++;

            //Add*
            for(Edge e : place[people]){
                if (visited[e.nbr] == 0) {
                    queue.add(new SickPair(e.nbr, rem.time + 1));
                }
            }
        }
        System.out.println("Sick people : " + count);

    }

    //O - None, 1- Fresh Orange. 2- Rotten Orange. Find time to rot orranges.
    static class OPair {
        int r;
        int c;

        public OPair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int findTimeToRotAllOranges(int[][] grid){
        ArrayDeque<OPair> queue = new ArrayDeque<>();
        int fresh = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if ( grid[i][j] == 2 )
                    queue.add(new OPair(i,j));
                else if ( grid[i][j] == 1 )
                    fresh ++;
            }
        }
        if(fresh == 0)
            return 0;

        int timeCount = 0;

        while(!queue.isEmpty()) {
            int size =queue.size();
            timeCount ++;
            while(size > 0){
                //remove
                OPair rem = queue.remove();

                //mark *
                if(rem.r - 1 >= 0 && rem.r - 1 < grid.length && grid[rem.r - 1][rem.c] == 1) {
                    grid[rem.r - 1][rem.c] = 2;
                    queue.add(new OPair(rem.r - 1, rem.c));
                    fresh--;
                }
                if(rem.c - 1 >= 0 && rem.c - 1 < grid[0].length && grid[rem.r][rem.c - 1] == 1) {
                    grid[rem.r][rem.c - 1] = 2;
                    queue.add(new OPair(rem.r, rem.c - 1));
                    fresh--;
                }
                if(rem.r + 1 >= 0 && rem.r + 1 < grid.length && grid[rem.r + 1][rem.c] == 1) {
                    grid[rem.r + 1][rem.c] = 2;
                    queue.add(new OPair(rem.r + 1, rem.c));
                    fresh--;
                }
                if(rem.c + 1 >= 0 && rem.c + 1 < grid[0].length && grid[rem.r][rem.c + 1] == 1) {
                    grid[rem.r][rem.c + 1] = 2;
                    queue.add(new OPair(rem.r, rem.c + 1));
                    fresh--;
                }

                size --;
            }
        }
        if (fresh != 0)
            return -1;

        return timeCount;
    }

    //BFS --Dijkstra's Algo --- Shortest weighted path  --- Concept
    static class DPair implements Comparable<DPair>{
        int v;
        String psf;
        int wsf;

        public DPair(int v, String psf, int wsf) {
            this.v = v;
            this.psf = psf;
            this.wsf =  wsf;
        }

        public int compareTo(DPair o) {
            return this.wsf - o.wsf;
        }
    }

    public static void dijkstrasAlgo(ArrayList<Edge>[] graph, int vertices, int src){

        PriorityQueue<DPair> queue = new PriorityQueue<>();
        queue.add(new DPair(src , src + "", 0));
        boolean[] visited = new boolean[vertices];

        //Remove Mark* Work Add*
        while(!queue.isEmpty()){
            //Remove
            DPair rem = queue.remove();

            //Mark*
            if(!visited[rem.v]){
                visited[rem.v] = true;
            }

            //Work
            System.out.println(rem.v + "@" + rem.psf + " via : " + rem.wsf);

            //Add*
            for(Edge e : graph[rem.v]){
                if(!visited[e.nbr]){
                    queue.add(new DPair(e.nbr , rem.psf + e.nbr, rem.wsf + e.wt));
                }
            }
        }
    }

    //BFS --Prim's Algo --- Minimum wire needed to connect all vertices  --- Concept
    static class PPair implements Comparable<PPair>{
        int v;
        int av;
        int wt;

        public PPair(int v, int av, int wt) {
            this.v = v;
            this.av = av;
            this.wt =  wt;
        }

        public int compareTo(PPair o) {
            return this.wt - o.wt;
        }
    }

    public static void primsAlgo(ArrayList<Edge>[] graph, int vertices){

        PriorityQueue<PPair> queue = new PriorityQueue<>();
        queue.add(new PPair(0 , -1, 0));
        boolean[] visited = new boolean[vertices];

        //Remove Mark* Work Add*
        while(!queue.isEmpty()){
            //Remove
            PPair rem = queue.remove();

            //Mark*
            if(!visited[rem.v]){
                visited[rem.v] = true;
            }

            //Work
            if(rem.av != -1)
                System.out.println(rem.v + "@" + rem.av + " via : " + rem.wt);

            //Add*
            for(Edge e : graph[rem.v]){
                if(!visited[e.nbr]){
                    queue.add(new PPair(e.nbr , rem.v, e.wt));
                }
            }
        }
    }
}
