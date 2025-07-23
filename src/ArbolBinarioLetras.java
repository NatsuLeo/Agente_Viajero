import java.util.Scanner;

public class ArbolBinarioLetras {
    // Clase interna Nodo representa cada letra del árbol
    static class Nodo {
        char letra;
        Nodo izquierda, derecha;

        public Nodo(char letra) {
            this.letra = letra;
            izquierda = derecha = null;
        }

        // Inserta una nueva letra siguiendo la lógica personalizada
        public void insertar(char nuevaLetra) {
            if (nuevaLetra > this.letra) {
                // Si la letra es "menor" según tu orden (ej. 'z' < 'a'), va a la izquierda
                if (izquierda == null)
                    izquierda = new Nodo(nuevaLetra);
                else
                    izquierda.insertar(nuevaLetra);
            } else {
                // Igual o "mayor" va a la derecha
                if (derecha == null)
                    derecha = new Nodo(nuevaLetra);
                else
                    derecha.insertar(nuevaLetra);
            }
        }

        // Imprime las letras en una sola línea (inorden)
        public void imprimirEnLinea() {
            imprimirInorden(this);
            System.out.println(); // Salto de línea final
        }

        private void imprimirInorden(Nodo nodo) {
            if (nodo == null) return;
            imprimirInorden(nodo.izquierda);
            System.out.print(nodo.letra + " ");
            imprimirInorden(nodo.derecha);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Nodo raiz = null;

        System.out.println("Ingresa letras para construir el árbol. Escribe 'listo' para terminar:");

        while (true) {
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("listo")) break;

            if (entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
                char letra = entrada.charAt(0);
                if (raiz == null) {
                    raiz = new Nodo(letra);
                } else {
                    raiz.insertar(letra);
                }
            } else {
                System.out.println("Entrada inválida. Ingresa una sola letra o 'listo'.");
            }
        }

        if (raiz != null) {
            System.out.println("\nLetras en una sola línea (inorden):");
            raiz.imprimirEnLinea();
        } else {
            System.out.println("No se ingresaron letras.");
        }

        scanner.close();
    }
}
