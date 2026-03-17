package ed.lab;

public class Main {
    public static void main(String[] args) {
        //ejercicio
        System.out.println("EJERCICIO 1");
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(1);
        root.right = new TreeNode<>(4);
        root.left.right = new TreeNode<>(2);

        E01KthSmallest solver = new E01KthSmallest();

        System.out.println("k=1 -> " + solver.kthSmallest(root, 1)); // 1
        System.out.println("k=2 -> " + solver.kthSmallest(root, 2)); // 2
        System.out.println("k=3 -> " + solver.kthSmallest(root, 3)); // 3
        System.out.println("k=4 -> " + solver.kthSmallest(root, 4)); // 4
        // EJERCICIO 2
        System.out.println("EJERCICO 2");
        E02AVLTree<Integer> avl = new E02AVLTree<>(Integer::compare);

        System.out.println("Altura inicial: " + avl.height()); //  0
        System.out.println("Tamaño inicial: " + avl.size());   //  0

        avl.insert(50);
        avl.insert(100);
        avl.insert(25);
        avl.insert(75);
        avl.insert(150);
        avl.insert(90);

        System.out.println("Altura después de inserts: " + avl.height()); // 3
        System.out.println("Tamaño después de inserts: " + avl.size());   //  6

        // Probamos búsqueda
        System.out.println("Buscar 75: " + avl.search(75));   //  75
        System.out.println("Buscar 200: " + avl.search(200)); // null

        // Eliminamos un valor
        avl.delete(75);

        System.out.println("Tamaño después de delete(75): " + avl.size()); // 5
        System.out.println("Buscar 75: " + avl.search(75)); //  null
    }

}