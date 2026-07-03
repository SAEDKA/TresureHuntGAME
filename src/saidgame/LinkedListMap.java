package saidgame; 

import java.util.ArrayList;     
import java.util.Collections;   
import java.util.List;         
import java.util.Random;      

public class LinkedListMap {

    private Node head;          // رأس القائمة المرتبطة (أول خانة في الخريطة)
    private int size;           // عدد الخانات في الخريطة

    public LinkedListMap(int size, int level) {
        this.size = 30;         // تثبيت عدد الخانات على 30، بغض النظر عن القيمة المُمررة
        generateMap(level);     // استدعاء دالة توليد الخريطة حسب المستوى
    }

    // دالة توليد الخريطة حسب المستوى
    private void generateMap(int level) {
        head = null;            // تفريغ القائمة الحالية (بداية جديدة)
        Random rand = new Random(); // كائن لتوليد الأرقام العشوائية

        if (level == 1) {
            // المستوى الأول: 9 كنوز، 5 أفخاخ، 15 خانة فارغة
            List<String> tileTypes = new ArrayList<>();
            tileTypes.add("Start"); // أول خانة تكون نقطة البداية

            for (int i = 0; i < 9; i++) tileTypes.add("Treasure"); // إضافة 9 خانات كنز
            for (int i = 0; i < 5; i++) tileTypes.add("Trap");     // إضافة 5 خانات فخ
            for (int i = 0; i < 15; i++) tileTypes.add("Empty");   // إضافة 15 خانة فارغة

            Collections.shuffle(tileTypes.subList(1, tileTypes.size())); // خلط جميع الخانات ما عدا البداية

            for (int i = 0; i < tileTypes.size(); i++) {
                addNode(new Node(i + 1, tileTypes.get(i))); // إنشاء وإضافة كل خانة إلى القائمة
            }

        } else if (level == 2) {
            // المستوى الثاني: أنواع إضافية (Forward و Backward)
            List<String> tileTypes = new ArrayList<>();
            tileTypes.add("Start"); // أول خانة

            for (int i = 0; i < 5; i++) tileTypes.add("Treasure"); // 5 كنوز
            for (int i = 0; i < 5; i++) tileTypes.add("Trap");     // 5 أفخاخ
            for (int i = 0; i < 5; i++) tileTypes.add("Forward " + (1 + rand.nextInt(5)));   // 5 خانات تقدم عشوائي (من 1 إلى 5)
            for (int i = 0; i < 4; i++) tileTypes.add("B"
                    + "ackward " + (1 + rand.nextInt(5)));  // 4 خانات تراجع عشوائي (من 1 إلى 5)
            for (int i = 0; i < 10; i++) tileTypes.add("Empty");    // 10 خانات فارغة

            Collections.shuffle(tileTypes.subList(1, tileTypes.size())); // خلط الخانات عدا البداية

            for (int i = 0; i < tileTypes.size(); i++) {
                addNode(new Node(i + 1, tileTypes.get(i))); // إنشاء وإضافة كل خانة
            }

        } else if (level == 3) {
            // المستوى الثالث: ميزات متقدمة مثل الزلزال والقنابل
            int treasureCount = 0;                          // عداد الكنوز
            int bombCount = 0;                              // عداد القنابل
            int maxTreasures = 3 + rand.nextInt(4);         // عدد الكنوز من 3 إلى 6
            int maxBombs = 1 + rand.nextInt(2);             // عدد القنابل من 1 إلى 2
            boolean earthquakePlaced = false;               // للتأكد من أن الزلزال لا يتكرر

            for (int i = 1; i <= size; i++) {
                String type;
                int chance = rand.nextInt(100);             // رقم عشوائي من 0 إلى 99

                if (i == 1) {
                    type = "Start";                         // أول خانة هي البداية
                } else if (!earthquakePlaced && chance < 10) {
                    type = "Earthquake";                   // خانة زلزال (نسبة 10%) لمرة واحدة فقط
                    earthquakePlaced = true;
                } else if (bombCount < maxBombs && chance < 20) {
                    type = "Bomb";                         // خانة قنبلة إذا لم نصل للحد
                    bombCount++;
                } else if (treasureCount < maxTreasures && chance < 35) {
                    type = "Treasure";                     // كنز إذا لم نصل للحد
                    treasureCount++;
                } else if (chance < 35) {
                    type = "Trap";                         // فخ بنسبة 35% تقريبًا
                } else {
                    type = "Empty";                        // غير ذلك تكون خانة فارغة
                }

                addNode(new Node(i, type)); // إنشاء وإضافة الخانة
            }
        }
    }   

    // دالة لإضافة خانة جديدة إلى نهاية القائمة المرتبطة
   private void addNode(Node newNode) {
    if (head == null) {
        head = newNode;
    } else {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp; // هذا هو الجزء المهم للدوبل لينكد لست
    }
}


    public int getCount() {
        return size; // إرجاع عدد الخانات
    }

    public Node getHead() {
        return head; // إرجاع الرأس (أول خانة)
    }

    // دالة لإرجاع خانة معينة حسب موقعها (id)
    public Node getNodeAt(int position) {
        Node current = head; // ابدأ من الرأس
        while (current != null) {
            if (current.id == position) { // إذا وصلنا إلى الموقع المطلوب
                return current;
            }
            current = current.next; // انتقل إلى الخانة التالية
        }
        return null; // لم يتم العثور على الخانة
    }
}
