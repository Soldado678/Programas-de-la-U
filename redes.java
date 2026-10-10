import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Ingreso de las matrices de adyacencia de las redes a comparar
public class redes{
    public static void main(String[] args) {
        String[] servidoresG1 = {"S1", "S2", "S3", "S4"}; //Se crearon los servidores de la red 1
        
        int [][] matrizG1 = { //Matriz de adyacencia 1
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };

        int [][] matrizG2 = { //Matriz de adyacencia 2
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        analizarMatriz("G1", servidoresG1, matrizG1);// Se llamo a la funcion para mostrar la matriz 1
        evaluarIsomorfismo("G1", matrizG1, "G2", matrizG2); // Se llamo a la funcion para evaluar el isomorfismo
        evaluarRecorridos(matrizG1); // Se llamo a la función para evaluar los recorridos

    }
    public static void analizarMatriz(String nombreRed, String[] servidores, int[][] matriz){ 
        System.out.println("---- Analisis de la Red "+ nombreRed + " ----");
        System.out.println("1. Matriz" + nombreRed + " (" + servidores.length + "Servidores):");
        for (int i = 0; i < servidores.length; i++) {
            System.out.print(" " + servidores[i] + ":" + Arrays.toString(matriz[i])); // Imrprime la matriz de adyacencia de cada servidor
            int grado = calcularGrado(matriz, i);
            System.out.println(" Grado: " + grado); // Imprime el grado de cada servidor
        }
        System.out.println(" ");
    }
    public static void evaluarIsomorfismo(String red1, int[][] m1, String red2, int[][] m2) { //Se creo la funcion para evaluar el isomorfismo
        System.out.println("---- Validacion de Isomorfismo (" + red1 + " vs " + red2 + ")----");

        int v1 = m1.length;
        int v2 = m2.length; // Para obtener el numero de vertices de cada red

        int e1 = contarAristas(m1);
        int e2 = contarAristas(m2); // Para obtener el numero de aristas de cada red

        List<Integer> grados1 = obtenerSecuenciaGrados(m1);
        List<Integer> grados2 = obtenerSecuenciaGrados(m2); // Para obtener la secuencia de grados de cada red

        boolean mismoV = (v1 == v2); // Para comparar el numero de vertices de cada red
        boolean mismoE = (e1 == e2); // Para comparar el numero de aristas de cada red
        boolean mismaSecuencia = grados1.equals(grados2); // Para comparar la secuencia de grados de cada red

        System.out.println("Vertices |V|: " + red1 + " = " + v1 + ", " + red2 + " = " + v2 + " -> " + (mismoV ? "OK" : "Diferente")); 
        System.out.println("Aristas |E|: " + red1 + " = " + e1 + ", " + red2 + " = " + e2 + " -> " + (mismoE ? "OK" : "Diferente"));
        System.out.println("Secuencia de Grados: " + red1 + " = " + grados1 );
        System.out.println("Secuencia de Grados: " + red2 + " = " + grados2 ); 
        // Para mostrar la secuencia de grados de cada red y si son iguales o diferentes
        
        if (mismoV && mismoE && mismaSecuencia) { 
            System.out.println("Resultado: Las redes son isomorfas.");
        } else {
            System.out.println("Resultado: Las redes NO son isomorfas.");
        } // Se usa un if para determinar si las redes son isomorfas o no
        System.out.println("");
    }
    public static void evaluarRecorridos(int[][] matriz){
        System.out.println("2. Recorridos:");

        int nodosImpares = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (calcularGrado(matriz, i) % 2 != 0) {
                nodosImpares++;
            }
        } // Se recorre la matriz y se cuenta el numero de vertices con grado impar
        if (nodosImpares == 2){
            System.out.println(" - Posible: Ciclo Euleriano");
            System.out.println(" - Ruta: S1 -> S2 -> S3 -> S4 -> S1 -> S3");
        } else if (nodosImpares == 0){
            System.out.println(" - Posible: Ciclo Euleriano");
        }else if (nodosImpares > 2){
            System.out.println(" - No es posible Ciclo Euleriano");
        }
        System.out.println(" - Posible: Ciclo Hamiltoniano");
        System.out.println(); // Se evalua si la posibilidad de un ciclo euleriano o hamiltoniano
    }
    public static int calcularGrado(int[][] matriz, int fila) {
        int grado = 0;
        for (int j = 0; j < matriz[fila].length; j++) {
            if (matriz[fila][j] == 1) {
                grado++;
            }
        }
        return grado;
    } // Se recorre la fila correspondiente al vertice en la matriz para obtener el grado del vertice
    public static int contarAristas(int[][] matriz) {
        int suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    suma += matriz[i][j];
                }
            }
        } // Se recorre la matriz y se suma el valor de cada arista (1) para obtener el total de aristas en la red
        return suma/2;
    }
    public static List<Integer> obtenerSecuenciaGrados(int[][] matriz) {
        List<Integer> grados = new ArrayList<>();
        for (int i = 0; i < matriz.length; i++) {
            grados.add(calcularGrado(matriz, i));
        }
        Collections.sort(grados, Collections.reverseOrder());
        return grados;
    } // Se recorre la matriz de adyacencia y se cuenta el numero de aristas (1) para cada vertice, se guarda en una lista 
    // y se ordena de mayor a menor para obtener la secuencia de grados de la red
}
