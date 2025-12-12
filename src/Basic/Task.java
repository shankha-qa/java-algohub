package Basic;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Task {

    public static class Posts implements Comparable<Posts> {
        int likes;
        public Posts(int likes) {
            this.likes = likes;
        }
        public int compareTo(Posts o){
            return this.likes-o.likes;
        }
    }

    public void selectPostPrecedence(ArrayList<String> posts) {
        PriorityQueue<Posts> pq = new PriorityQueue<>();
        for (int i = 0; i < posts.size(); i++) {
            Posts p = new Posts(i);
            pq.add(p);
        }
        System.out.println("Most liked post is : " + pq.peek());
    }


}
