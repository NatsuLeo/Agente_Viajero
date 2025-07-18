import java.util.*;

public class CircuitoEuler {

    static Map<String, List<String>> grafo = new HashMap<>();
    static Map<String, Map<String, Integer>> distancias = new HashMap<>();
    static List<String> recorrido = new ArrayList<>();

    public static void main(String[] args) {
        construirGrafo();
        setDistancias();

        String inicio = "Tonala";
        recorrer(inicio);

        System.out.println("Recorrido:");
        int total = 0;
        int paso = 1;

        for (int i = 0; i < recorrido.size() - 1; i++) {
            String desde = recorrido.get(i);
            String hacia = recorrido.get(i + 1);
            if (distanciaValida(desde, hacia)) {
                int d = obtenerDistancia(desde, hacia);
                System.out.println("Paso " + (paso++) + ": " + desde + " → " + hacia + " = " + d + " km");
                total += d;
            }
        }

        System.out.println("\nDistancia total recorrida: " + total + " km");
    }

    static void construirGrafo() {
        agregar("Tonala", "Tlaquepaque");
        agregar("Tonala", "El salto");
        agregar("Tonala", "Juanacatlan");
        agregar("Tonala", "Zapotlanejo");
        agregar("Tonala", "Guadalajara");
        agregar("Guadalajara", "Zapopan");
        agregar("Guadalajara", "Tlaquepaque");
        agregar("Guadalajara", "Zapotlanejo");
        agregar("Guadalajara", "Ixtlahuacan");
        agregar("Ixtlahuacan", "Zapopan");
        agregar("Ixtlahuacan", "Zapotlanejo");
        agregar("Zapotlanejo", "Juanacatlan");
        agregar("Juanacatlan", "El salto");
        agregar("El salto", "Tlajomulco");
        agregar("El salto", "Tlaquepaque");
        agregar("Tlaquepaque", "Zapopan");
        agregar("Zapopan", "El arenal");
        agregar("El arenal", "Tala");
        agregar("Tlajomulco", "Jocotepec");
        agregar("Tlajomulco", "Tala");
        agregar("Tala", "Villa corona");
        agregar("Tala", "Acatlan de juarez");
        agregar("Acatlan de juarez", "Villa corona");
        agregar("Acatlan de juarez", "Zacoalco de torres");
        agregar("Acatlan de juarez", "Jocotepec");
        agregar("Jocotepec", "Zacoalco de torres");
        agregar("Zacoalco de torres", "Villa corona");
    }

    static void setDistancias() {
        set("Tonala", "Tlaquepaque", 13);
        set("Tonala", "El salto", 18);
        set("Tonala", "Juanacatlan", 20);
        set("Tonala", "Zapotlanejo", 22);
        set("Tonala", "Guadalajara", 16);
        set("Guadalajara", "Zapopan", 8);
        set("Guadalajara", "Tlaquepaque", 10);
        set("Guadalajara", "Zapotlanejo", 38);
        set("Guadalajara", "Ixtlahuacan", 51);
        set("Ixtlahuacan", "Zapopan", 57);
        set("Ixtlahuacan", "Zapotlanejo", 84);
        set("Zapotlanejo", "Juanacatlan", 19);
        set("Juanacatlan", "El salto", 2);
        set("El salto", "Tlajomulco", 43);
        set("El salto", "Tlaquepaque", 27);
        set("Tlaquepaque", "Zapopan", 15);
        set("Zapopan", "El arenal", 36);
        set("El arenal", "Tala", 22);
        set("Tlajomulco", "Jocotepec", 40);
        set("Tlajomulco", "Tala", 42);
        set("Tala", "Villa corona", 54);
        set("Tala", "Acatlan de juarez", 53);
        set("Acatlan de juarez", "Villa corona", 9);
        set("Acatlan de juarez", "Zacoalco de torres", 24);
        set("Acatlan de juarez", "Jocotepec", 31);
        set("Jocotepec", "Zacoalco de torres", 54);
        set("Zacoalco de torres", "Villa corona", 37);
    }

    static void agregar(String a, String b) {
        grafo.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        grafo.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
    }

    static void set(String c1, String c2, int d) {
        distancias.computeIfAbsent(c1, k -> new HashMap<>()).put(c2, d);
        distancias.computeIfAbsent(c2, k -> new HashMap<>()).put(c1, d);
    }

    static boolean distanciaValida(String a, String b) {
        return distancias.containsKey(a) && distancias.get(a).containsKey(b);
    }

    static int obtenerDistancia(String a, String b) {
        return distancias.get(a).get(b);
    }

    static void recorrer(String inicio) {
        Map<String, LinkedList<String>> copia = new HashMap<>();
        for (String ciudad : grafo.keySet()) {
            copia.put(ciudad, new LinkedList<>(grafo.get(ciudad)));
        }

        Stack<String> pila = new Stack<>();
        pila.push(inicio);

        while (!pila.isEmpty()) {
            String actual = pila.peek();
            if (!copia.get(actual).isEmpty()) {
                String siguiente = copia.get(actual).poll();
                copia.get(siguiente).remove(actual);
                pila.push(siguiente);
            } else {
                recorrido.add(pila.pop());
            }
        }

        Collections.reverse(recorrido);
    }

    static int calcularDistancia() {
        int total = 0;
        for (int i = 0; i < recorrido.size() - 1; i++) {
            String desde = recorrido.get(i);
            String hacia = recorrido.get(i + 1);
            if (distanciaValida(desde, hacia)) {
                total += obtenerDistancia(desde, hacia);
            }
        }
        return total;
    }
}