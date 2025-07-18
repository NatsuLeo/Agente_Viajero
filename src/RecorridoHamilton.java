import java.util.*;

public class RecorridoHamilton {

    static String[] recorrido = {
            "Tonala", "Juanacatlan", "Zapotlanejo", "Ixtlahuacan", "Guadalajara",
            "Tlaquepaque", "Zapopan", "El arenal", "Tala", "Villa corona",
            "Zacoalco de torres", "Acatlan de juarez", "Jocotepec", "Tlajomulco", "El salto", "Tonala"
    };

    static Map<String, Map<String, Integer>> distancias = new HashMap<>();

    public static void main(String[] args) {
        inicializarDistancias();

        int total = 0;
        System.out.println("Recorrido de Hamilton:");
        for (int i = 0; i < recorrido.length - 1; i++) {
            String desde = recorrido[i];
            String hacia = recorrido[i + 1];
            int distancia = obtenerDistancia(desde, hacia);
            System.out.println("Paso " + (i + 1) + ": " + desde + " → " + hacia + " = " + distancia + " km");
            total += distancia;
        }

        System.out.println("\nDistancia total recorrida: " + total + " km");
    }

    static void inicializarDistancias() {
        // Define distancias entre pares conectados
        agregar("Tonala", "Juanacatlan", 20);
        agregar("Juanacatlan", "Zapotlanejo", 19);
        agregar("Zapotlanejo", "Ixtlahuacan", 84);
        agregar("Ixtlahuacan", "Guadalajara", 51);
        agregar("Guadalajara", "Tlaquepaque", 10);
        agregar("Tlaquepaque", "Zapopan", 15);
        agregar("Zapopan", "El arenal", 36);
        agregar("El arenal", "Tala", 22);
        agregar("Tala", "Villa corona", 54);
        agregar("Villa corona", "Zacoalco de torres", 37);
        agregar("Zacoalco de torres", "Acatlan de juarez", 24);
        agregar("Acatlan de juarez", "Jocotepec", 31);
        agregar("Jocotepec", "Tlajomulco", 40);
        agregar("Tlajomulco", "El salto", 43);
        agregar("El salto", "Tonala", 18); // regreso al inicio
    }

    static void agregar(String a, String b, int d) {
        distancias.putIfAbsent(a, new HashMap<>());
        distancias.putIfAbsent(b, new HashMap<>());
        distancias.get(a).put(b, d);
        distancias.get(b).put(a, d);
    }

    static int obtenerDistancia(String a, String b) {
        return distancias.getOrDefault(a, Map.of()).getOrDefault(b,
                distancias.getOrDefault(b, Map.of()).getOrDefault(a, 0));
    }
}
