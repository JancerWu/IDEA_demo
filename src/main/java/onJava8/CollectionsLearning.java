package onJava8;

import java.util.ArrayList;
import java.util.List;

public class CollectionsLearning {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1.1f);
        list.add(2);
        list.add(2.2d);
        for (Object o : list) {
            System.out.println((int) o);
        }
    }
}
