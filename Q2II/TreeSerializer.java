package Q2II;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import javax.management.RuntimeErrorException;

// Assumption: Deserializer only works with distinct values to detect cyclic tree

public class TreeSerializer {

    final static private String DIVIDER = " ";
    final static private String NULL = "*";

    public static String serialize(Node root){
        if(root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        HashSet<Node> visited = new HashSet<>();
        helper(root, visited, res);
        return res.toString();
    }

    private static void helper(Node curr, HashSet<Node> visited, StringBuilder res){
        if(curr == null){
            res.append(NULL+DIVIDER);
            return;
        }

        // If there is cycle in tree
        if(visited.contains(curr)){
            throw new RuntimeException();
        }

        visited.add(curr);
        res.append(curr.num+DIVIDER);
        helper(curr.left,visited, res);
        helper(curr.right,visited, res);
    }

    public static Node deserialize(String str){
        if(str.equals("")){
            return null;
        }
        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        q.addAll(Arrays.asList(str.split(DIVIDER)));
        return helper(q,visited);
    }

    private static Node helper(Queue<String> q, HashSet<String> visited){
        if(q.isEmpty() || q.peek().equals(NULL)){
            q.poll();
            return null;
        }
        String val = q.poll();
        if(visited.contains(val)){
            throw new RuntimeException();
        }
        visited.add(val);
        Node n = new Node(Integer.parseInt(val));
        n.left = helper(q,visited);
        n.right = helper(q,visited);
        return n;
    }
}