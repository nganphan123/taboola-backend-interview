package Q2II;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // construct tree
        Node root = new Node(1);
        Node s  = root.left = new Node(2);
        root.right = new Node(1);
        root.left.left = new Node(7);
        root.left.right = new Node(5);
        root.right.right = new Node(28);
        root.left.left.left = new Node(4);
        
        String serialString = TreeSerializer.serialize(root);
        System.out.println(serialString);

        System.out.println(TreeSerializer.serialize(TreeSerializer.deserialize(serialString)));
        
    }
}
