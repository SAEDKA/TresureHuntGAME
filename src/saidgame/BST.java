package saidgame;

public class BST {
    private BSTNode root;

    public void insert(int score, String username, int level) {
        root = insertRec(root, score, username, level);
    }

    private BSTNode insertRec(BSTNode root, int score, String username, int level) {
        if (root == null) {
            root = new BSTNode(score, username, level);
            return root;
        }
        if (score < root.score) {  //يسار
            root.left = insertRec(root.left, score, username, level);
        } else {
            root.right = insertRec(root.right, score, username, level);
        }
        return root;
    }

    public String getAllScores() {
        StringBuilder sb = new StringBuilder();
        inorder(root, sb); //يسار → الجذر → يمين

        return sb.toString();
    }

    private void inorder(BSTNode root, StringBuilder sb) {
        if (root != null) {
            inorder(root.left, sb);
            sb.append(root.score).append(" (level ").append(root.level).append(")\n");
            inorder(root.right, sb);
        }
    }

    public String getBestScore() { //من اليمين لاخر نود
        if (root == null) {
            return "No scores available.";
        }
        BSTNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.score + " (level " + current.level + ")";
    }

    public String getWorstScore() {
        if (root == null) {
            return "No scores available.";
        }
        BSTNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.score + " (level " + current.level + ")";
    }
}
