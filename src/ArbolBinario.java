import java.util.Scanner;

public class ArbolBinario {
    // Clase interna Nodo representa cada elemento del árbol
    static class Nodo {
        int valor;
        Nodo izquierda, derecha;

        public Nodo(int valor) {
            this.valor = valor;
            izquierda = derecha = null;
        }

        // Inserta un nuevo valor siguiendo la lógica binaria
        public void insertar(int nuevoValor) {
            if (nuevoValor < this.valor) {
                if (izquierda == null)
                    izquierda = new Nodo(nuevoValor);
                else
                    izquierda.insertar(nuevoValor);
            } else {
                // Si es igual o mayor, va a la derecha
                if (derecha == null)
                    derecha = new Nodo(nuevoValor);
                else
                    derecha.insertar(nuevoValor);
            }
        }

        // Imprime los valores del árbol en una sola línea ordenada (inorden)
        public void imprimirEnLinea() {
            imprimirInorden(this);
            System.out.println(); // Salto de línea al final
        }

        private void imprimirInorden(Nodo nodo) {
            if (nodo == null) return;
            imprimirInorden(nodo.izquierda);
            System.out.print(nodo.valor + " ");
            imprimirInorden(nodo.derecha);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Nodo raiz = null;

        System.out.println("Ingresa números para construir el árbol. Escribe 'listo' para terminar:");

        while (true) {
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("listo")) break;

            try {
                int numero = Integer.parseInt(entrada);
                if (raiz == null) {
                    raiz = new Nodo(numero);
                } else {
                    raiz.insertar(numero);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número entero o 'listo' para terminar.");
            }
        }

        if (raiz != null) {
            System.out.println("\nValores en una sola línea (inorden):");
            raiz.imprimirEnLinea();
        } else {
            System.out.println("No se ingresaron números.");
        }

        scanner.close();
    }
}
