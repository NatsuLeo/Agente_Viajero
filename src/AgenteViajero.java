import java.util.*;

public class AgenteViajero {
    static final int INF = Integer.MAX_VALUE;

    static String[] ciudades = {
            "Villa corona", "Zacoalco de torres", "Jocotepec", "Acatlan de juarez", "Tala",
            "Tlajomulco", "El arenal", "Zapopan", "Tlaquepaque", "El salto",
            "Juanacatlan", "Zapotlanejo", "Ixtlahuacan", "Guadalajara", "Tonala"
    };

    // Matriz de distancias (los valores INF representan ausencia de conexión directa)
    static int[][] distancias = new int[15][15];

    public static void main(String[] args) {
        inicializarMatriz();

        int inicio = 0; // Villa corona
        boolean[] visitado = new boolean[ciudades.length];
        List<Integer> recorrido = new ArrayList<>();
        int totalDistancia = 0;

        int actual = inicio;
        recorrido.add(actual);
        visitado[actual] = true;

        for (int i = 0; i < ciudades.length - 1; i++) {
            int siguiente = -1;
            int distanciaMinima = INF;

            for (int j = 0; j < ciudades.length; j++) {
                if (!visitado[j] && distancias[actual][j] < distanciaMinima) {
                    distanciaMinima = distancias[actual][j];
                    siguiente = j;
                }
            }

            if (siguiente == -1) {
                System.out.println("No se pudo completar el recorrido (grafo no conexo).");
                return;
            }

            recorrido.add(siguiente);
            visitado[siguiente] = true;
            totalDistancia += distanciaMinima;
            actual = siguiente;
        }

        // Volver al punto de inicio
        if (distancias[actual][inicio] != INF) {
            recorrido.add(inicio);
            totalDistancia += distancias[actual][inicio];
        } else {
            System.out.println("No hay ruta de regreso al inicio.");
        }

        // Mostrar resultados
        System.out.println("\nRuta del agente viajero:");
        for (int i : recorrido) {
            System.out.println("- " + ciudades[i]);
        }
        System.out.println("\nDistancia total: " + totalDistancia + " km");
    }

    // Llenar matriz de distancias
    private static void inicializarMatriz() {
        for (int[] fila : distancias)
            Arrays.fill(fila, INF);

        for (int i = 0; i < ciudades.length; i++)
            distancias[i][i] = 0;

        // Distancias bidireccionales
        setDist("Tonala", "Tlaquepaque", 13);
        setDist("Tonala", "El salto", 18);
        setDist("Tonala", "Juanacatlan", 20);
        setDist("Tonala", "Zapotlanejo", 22);
        setDist("Tonala", "Guadalajara", 16);
        setDist("Guadalajara", "Zapopan", 8);
        setDist("Guadalajara", "Tlaquepaque", 10);
        setDist("Guadalajara", "Zapotlanejo", 38);
        setDist("Guadalajara", "Ixtlahuacan", 51);
        setDist("Ixtlahuacan", "Zapopan", 57);
        setDist("Ixtlahuacan", "Zapotlanejo", 84);
        setDist("Zapotlanejo", "Juanacatlan", 19);
        setDist("Juanacatlan", "El salto", 2);
        setDist("El salto", "Tlajomulco", 43);
        setDist("El salto", "Tlaquepaque", 27);
        setDist("Tlaquepaque", "Zapopan", 15);
        setDist("Zapopan", "El arenal", 36);
        setDist("El arenal", "Tala", 22);
        setDist("Tlajomulco", "Jocotepec", 40);
        setDist("Tlajomulco", "Tala", 42);
        setDist("Tala", "Villa corona", 54);
        setDist("Tala", "Acatlan de juarez", 53);
        setDist("Acatlan de juarez", "Villa corona", 9);
        setDist("Acatlan de juarez", "Zacoalco de torres", 24);
        setDist("Acatlan de juarez", "Jocotepec", 31);
        setDist("Jocotepec", "Zacoalco de torres", 54);
        setDist("Zacoalco de torres", "Villa corona", 37);
    }

    // Función auxiliar para asignar distancias por nombre de ciudad
    private static void setDist(String origen, String destino, int distancia) {
        int i = getIndex(origen);
        int j = getIndex(destino);
        distancias[i][j] = distancia;
        distancias[j][i] = distancia;
    }

    // Obtener índice en el arreglo de ciudades
    private static int getIndex(String ciudad) {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i].equalsIgnoreCase(ciudad)) return i;
        }
        throw new IllegalArgumentException("Ciudad no encontrada: " + ciudad);
    }
}