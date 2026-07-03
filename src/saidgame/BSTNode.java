package saidgame;

public class BSTNode {
    int score;         // درجة اللاعب
    String username;   // اسم المستخدم
    int level;         // المستوى الذي تم الوصول إليه
    BSTNode left, right; 

    // المُنشئ (constructor) لتعيين القيم الابتدائية للعقدة
    public BSTNode(int score, String username, int level) {
        this.score = score;           // تعيين درجة اللاعب
        this.username = username;     // تعيين اسم المستخدم
        this.level = level;           // تعيين المستوى
        this.left = this.right = null; // الإشارة إلى أن لا توجد عقد فرعية بعد (عقدة جديدة)
    }
}
