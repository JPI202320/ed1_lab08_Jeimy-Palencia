package ed.lab;

public class E01KthSmallest {

    //elementos vistos
    private int count =0;
    //guarda el valor al llegar al k-esimo
    private int result=-1;
    public int kthSmallest(TreeNode<Integer> root, int k) {
        inorder(root,k);
        return result;
    }

    private void inorder(TreeNode<Integer> node, int k) {
        if (node == null) return;

        inorder(node.left, k);

        count++;
        if (count == k) {
            result = node.value;
            return;
        }

        inorder(node.right, k);
    }
    /*
    Análisis:
            1. ¿Cuál es la complejidad de tiempo en el mejor, peor y caso
            promedio? Justifique su respuesta y expliqué de qué depende
            la complejidad.
            mejor: O
            2. ¿Cuál es la complejidad de espacio en el mejor, peor y caso
            promedio? Justifique su respuesta y expliqué de qué depende
            la complejidad.
            3. Si el árbol binario de búsqueda fuera modificado con mucha
            frecuencia (es decir, que se pudieran agregar y eliminar
            valores), ¿qué optimización realizaría para obtener el k-ésimo
            elemento más pequeño frecuentemente? Explique los
            beneficios de su solución y la complejidad de tiempo y
            espacio que esta tendría.

     */

}