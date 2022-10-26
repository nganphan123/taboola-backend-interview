package Q2;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreeSerializer {

    final static private String DIVIDER = " ";
    final static private String NULL = "*";

    public static String serialize(Node root){
        if(root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        helper(root, res);
        return res.toString();
    }

    private static void helper(Node curr, StringBuilder res){
        if(curr == null){
            res.append(NULL+DIVIDER);
            return;
        }
        res.append(curr.num+DIVIDER);
        helper(curr.left,res);
        helper(curr.right,res);
    }

    public static Node deserialize(String str){
        if(str.equals("")){
            return null;
        }
        Queue<String> q = new LinkedList<>();
        q.addAll(Arrays.asList(str.split(DIVIDER)));
        return helper(q);
    }

    private static Node helper(Queue<String> q){
        if(q.isEmpty() || q.peek().equals(NULL)){
            q.poll();
            return null;
        }
        String val = q.poll();
        Node n = new Node(Integer.parseInt(val));
        n.left = helper(q);
        n.right = helper(q);
        return n;
    }
}