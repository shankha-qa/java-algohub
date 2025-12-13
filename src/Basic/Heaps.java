package Basic;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Heaps {

    // In an Array print 'K' number of largest elements without sorting the Array
    public PriorityQueue<Integer> printKLargestElements(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i <= arr.length - 1; i++) {
            if (i < k) {
                pq.add(arr[i]);
            }
           else if (arr[i] > pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        return pq;
    }

    // Sort Print an Array which is already sorted by K places
    public void sortKSortedArray(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i <= k ; i++) {
            pq.add(arr[i]);
        }

        for (int i = k + 1; i < arr.length; i++) {
            System.out.println(pq.remove());
            pq.add(arr[i]);
        }
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }

    // Merge K number of Sorted Lists into a single one
    public static class Pair implements Comparable<Pair> {
        int li;
        int di;
        int val;
        public Pair(int li, int di, int val) {
            this.li =  li;
            this.di =  di;
            this.val =  val;
        }
        public int compareTo(Pair o){
            return this.val - o.val;
        }
    }

    public ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for(int i = 0; i < lists.size(); i++) {
            Pair p = new Pair(i,0,lists.get(i).get(0));
            pq.add(p);
        }

        while(! pq.isEmpty()) {
            Pair p = pq.remove();
            result.add(p.val);

            p.di = p.di + 1;
            if(lists.get(p.li).size() > p.di) {
                p.val = lists.get(p.li).get(p.di);
                pq.add(p);
            }
        }
        return result;
    }

    //    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
    //    return the k closest points to the origin (0, 0).
    //    The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
    public int[][] kClosest(int[][] points, int k) {
        // Max-heap based on distance from origin
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );

        for(int[] point : points) {
            pq.offer(point);
            if (pq.size() > k){
                pq.poll();
            }
        }

        int[][] result = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            result[i][0] = temp[0];
            result[i][1] = temp[1];
            i++;
        }

        return result;
    }

    //Implement Hashmap and Implement Priority Queue
    //Will be done once Tree is Completed

}
