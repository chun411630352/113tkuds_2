import java.util.*;

public class RecursiveTreePreview {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder sub1 = new Folder("sub1");
        Folder sub2 = new Folder("sub2");
        sub1.files.add("a.txt");
        sub1.files.add("b.txt");
        sub2.files.add("c.txt");
        root.subFolders.add(sub1);
        root.subFolders.add(sub2);
        root.files.add("main.doc");
        System.out.println("總檔案數: " + countFiles(root));

        Menu menu = new Menu("主選單");
        Menu item1 = new Menu("項目 1");
        Menu item2 = new Menu("項目 2");
        Menu item21 = new Menu("項目 2-1");
        Menu item22 = new Menu("項目 2-2");
        item2.children.add(item21);
        item2.children.add(item22);
        menu.children.add(item1);
        menu.children.add(item2);
        System.out.println("選單結構：");
        printMenu(menu, 0);

        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, 4)), 5);
        List<Integer> flat = new ArrayList<>();
        flatten(nestedList, flat);
        System.out.println("展平結果：" + flat);

        int depth = maxDepth(nestedList);
        System.out.println("最大深度: " + depth);
    }

    static class Folder {
        String name;
        List<String> files = new ArrayList<>();
        List<Folder> subFolders = new ArrayList<>();
        Folder(String name) {
            this.name = name;
        }
    }

    public static int countFiles(Folder folder) {
        int count = folder.files.size();
        for (Folder f : folder.subFolders) {
            count += countFiles(f);
        }
        return count;
    }

    static class Menu {
        String title;
        List<Menu> children = new ArrayList<>();
        Menu(String title) {
            this.title = title;
        }
    }

    public static void printMenu(Menu menu, int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("- " + menu.title);
        for (Menu m : menu.children) {
            printMenu(m, level + 1);
        }
    }

    public static void flatten(List<?> nested, List<Integer> result) {
        for (Object o : nested) {
            if (o instanceof Integer) {
                result.add((Integer) o);
            } else if (o instanceof List) {
                flatten((List<?>) o, result);
            }
        }
    }

    public static int maxDepth(List<?> nested) {
        int max = 1;
        for (Object o : nested) {
            if (o instanceof List) {
                max = Math.max(max, 1 + maxDepth((List<?>) o));
            }
        }
        return max;
    }
}
