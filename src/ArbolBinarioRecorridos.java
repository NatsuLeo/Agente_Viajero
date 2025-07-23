import java.util.Scanner;

public class ArbolBinarioRecorridos {
    // Nodo numérico
    static class NodoNumero {
        int valor;
        NodoNumero izquierda, derecha;

        public NodoNumero(int valor) {
            this.valor = valor;
        }

        public void insertar(int nuevoValor) {
            if (nuevoValor < this.valor) {
                if (izquierda == null) izquierda = new NodoNumero(nuevoValor);
                else izquierda.insertar(nuevoValor);
            } else {
                if (derecha == null) derecha = new NodoNumero(nuevoValor);
                else derecha.insertar(nuevoValor);
            }
        }

        public void imprimirPreorden() {
            System.out.print(valor + " ");
            if (izquierda != null) izquierda.imprimirPreorden();
            if (derecha != null) derecha.imprimirPreorden();
        }

        public void imprimirInorden() {
            if (izquierda != null) izquierda.imprimirInorden();
            System.out.print(valor + " ");
            if (derecha != null) derecha.imprimirInorden();
        }

        public void imprimirPostorden() {
            if (izquierda != null) izquierda.imprimirPostorden();
            if (derecha != null) derecha.imprimirPostorden();
            System.out.print(valor + " ");
        }
    }

    // Nodo de letra
    static class NodoLetra {
        char letra;
        NodoLetra izquierda, derecha;

        public NodoLetra(char letra) {
            this.letra = letra;
        }

        public void insertar(char nuevaLetra) {
            if (nuevaLetra > this.letra) {
                if (izquierda == null) izquierda = new NodoLetra(nuevaLetra);
                else izquierda.insertar(nuevaLetra);
            } else {
                if (derecha == null) derecha = new NodoLetra(nuevaLetra);
                else derecha.insertar(nuevaLetra);
            }
        }

        public void imprimirPreorden() {
            System.out.print(letra + " ");
            if (izquierda != null) izquierda.imprimirPreorden();
            if (derecha != null) derecha.imprimirPreorden();
        }

        public void imprimirInorden() {
            if (izquierda != null) izquierda.imprimirInorden();
            System.out.print(letra + " ");
            if (derecha != null) derecha.imprimirInorden();
        }

        public void imprimirPostorden() {
            if (izquierda != null) izquierda.imprimirPostorden();
            if (derecha != null) derecha.imprimirPostorden();
            System.out.print(letra + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NodoNumero raizNumero = null;
        NodoLetra raizLetra = null;
        boolean usandoNumeros = false;
        boolean usandoLetras = false;

        System.out.println("Ingresa letras o números para construir el árbol. Escribe 'listo' para terminar:");

        while (true) {
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("listo")) break;

            if (entrada.matches("-?\\d+")) {
                if (usandoLetras) {
                    System.out.println("Solo se pueden ingresar letras.");
                    return;
                }
                int num = Integer.parseInt(entrada);
                if (raizNumero == null) {
                    raizNumero = new NodoNumero(num);
                    usandoNumeros = true;
                } else {
                    raizNumero.insertar(num);
                }
            } else if (entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
                if (usandoNumeros) {
                    System.out.println("Solo se pueden ingresar números.");
                    return;
                }
                char letra = entrada.charAt(0);
                if (raizLetra == null) {
                    raizLetra = new NodoLetra(letra);
                    usandoLetras = true;
                } else {
                    raizLetra.insertar(letra);
                }
            } else {
                System.out.println("Entrada inválida. Ingresa solo letras o números enteros.");
            }
        }

        // Mostrar recorridos
        if (raizNumero != null) {
            System.out.println("\nPreorden:");
            raizNumero.imprimirPreorden();
            System.out.println("\nPostorden:");
            raizNumero.imprimirPostorden();
            System.out.println("\nInorden:");
            raizNumero.imprimirInorden();
            System.out.println();
        } else if (raizLetra != null) {
            System.out.println("\nPreorden:");
            raizLetra.imprimirPreorden();
            System.out.println("\nPostorden:");
            raizLetra.imprimirPostorden();
            System.out.println("\nInorden:");
            raizLetra.imprimirInorden();
            System.out.println();
        } else {
            System.out.println("No se ingresaron datos válidos.");
        }

        scanner.close();
    }
}
