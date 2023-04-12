package hw;

import java.util.*;

public class Main {

    private static String phoneCaseInventoryManage(float demand, float[] inventory, float[] price) {
            int n = inventory.length;
            List<Integer> ids = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                ids.add(i);
            }
            ids.sort((a, b) -> {
                        float m1 = price[a] / inventory[a];
                        float m2 = price[b] / inventory[b];
                        if (m1 > m2) return 1;
                        else if (m1 < m2) return -1;
                        else return 0;
                    }
            );
        float ans = 0;
        for (int i = inventory.length - 1; i >= 0; i--) {
            int id = ids.get(i);
            if (inventory[id] <= demand) {
                demand -= inventory[id];
                ans += price[id];
            } else {
                ans += demand * price[id] / inventory[id];
                return String.format("%.2f", ans);
            }

        }
        return String.format("%.2f", ans);
    }


    public static void main(String[] args) {

        System.out.println("Hello world!");
        phoneCaseInventoryManage(20, new float[]{18,15,10}, new float[]{75,72,45});
    }
}