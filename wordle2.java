
import java.util.Arrays;
import java.util.Scanner;

public class wordle2 {
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[47m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    public static final String ANSI_RESET = "\u001B[0m";

 
    public static void main(String[] args) {
    	
    	//Ernesto Villar Perez y Ana Suárez Villaescusa
    	
        //Explicación del juego
        System.out.println();
        
        System.out.println("El objetivo del juego es simple, adivinar la palabra oculta.");
        System.out.println("La palabra tiene 5 letras y tienes 6 intentos para adivinarla.");
        System.out.println("La palabra es la misma para todas las personas en ese día.");
        
        System.out.println();
        
        System.out.println("Cada intento debe ser una palabra válida.");
        System.out.println("En cada ronda el juego pinta cada letra de un");
        System.out.println("color indicando si esa letra se encuentra o no en");
        System.out.println("la palabra y si se encuentra en la posición correcta.");

        System.out.println();
        
        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "┌───┐" + ANSI_RESET);
        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "│ D │" + ANSI_RESET + " VERDE significa que la letra está en la palabra y en la posición CORRECTA.");
        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "└───┘" + ANSI_RESET);
        
        System.out.println();
        
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "┌───┐" + ANSI_RESET);
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "│ A │" + ANSI_RESET + " AMARILLO significa que la letra letra está presente en la palabra pero en la posición INCORRECTA.");
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "└───┘" + ANSI_RESET);
        
        System.out.println();
        
        System.out.println(ANSI_GREY_BACKGROUND + ANSI_BLACK + "┌───┐" + ANSI_RESET);
        System.out.println(ANSI_GREY_BACKGROUND + ANSI_BLACK + "│ N │" + ANSI_RESET + " GRIS significa que la letra letra NO está presente en la palabra.");
        System.out.println(ANSI_GREY_BACKGROUND + ANSI_BLACK + "└───┘" + ANSI_RESET);
        //Fin de la explicacion del juego
        
        Scanner lecScanner = new Scanner(System.in);
        String secreta;

        char abecedarioChar = 65;
        String[] palabra = { "│   │", "│   │", "│   │", "│   │", "│   │" };
        String[] almacenPalabra = new String[6];

        String[] abecedario = new String[27];

        int contador = 0;

        String intentoPalabra = "";
        String lista[] = new String[27]; 

        lista[0] = "P";
        lista[1] = "A";
        lista[2] = "N";
        lista[3] = "E";
        lista[4] = "L";

        int correcto = 0;
        char palabraAnalizar = '\0';

        secreta = "PANEL";

        for (int i = 0; i < 6; i++) {
            almacenPalabra[i] = "│   │ │   │ │   │ │   │ │   │";
        }

        for (int i = 0; i < 27; i++) {
            if (i == 14) {
                abecedario[i] = ("Ñ");
            } else {
                abecedario[i] = Character.toString(abecedarioChar);
                abecedarioChar++;
            }
        }

        System.out.println();
        
        System.out.println("---------------------------------------------");
        imprimirLinea(almacenPalabra, palabra, contador);
        System.out.println("---------------------------------------------");
        
        imprimirAbecedario(palabraAnalizar, palabra, abecedario);

        do {
            System.out.println("---------------------------------------------");
            System.out.print("");
            do {
                System.out.print("Introduce palabra: ");
                intentoPalabra = lecScanner.nextLine();
            } while (intentoPalabra.length() != secreta.length());
            intentoPalabra = intentoPalabra.toUpperCase();
            almacenPalabra[contador] = intentoPalabra;

            if (secreta.equals(intentoPalabra)) {
                correcto = 1;
            } else {
                for (int i = 0; i < 5; i++) {
                    palabraAnalizar = intentoPalabra.charAt(i);
                    returnAbecedario(secreta, palabraAnalizar, abecedario, lista);

                    if (secreta.charAt(i) == intentoPalabra.charAt(i)) {
                        palabra[i] = ANSI_GREEN_BACKGROUND + ANSI_BLACK + "│ "+ intentoPalabra.charAt(i) + " │" + ANSI_RESET;
                    } else if (secreta.contains("" + palabraAnalizar)) {
                        palabra[i] = ANSI_YELLOW_BACKGROUND + "│ " + ANSI_BLACK + intentoPalabra.charAt(i) + " │" + ANSI_RESET;
                    } else {
                        palabra[i] = ANSI_GREY_BACKGROUND + "│ " + ANSI_BLACK + intentoPalabra.charAt(i) + " │" + ANSI_RESET;
                    }
                }
                System.out.println("---------------------------------------------");
                imprimirLinea(almacenPalabra, palabra, contador);
                System.out.println("---------------------------------------------");
                imprimirAbecedario(palabraAnalizar, palabra, abecedario);
            }
            contador++;
        } while (correcto != 1 && contador <= 5);

        if (correcto == 1 && contador <= 5) {
            System.out.print("Enhorabuena!!! Adivinaste la palabra");
        } else {
            System.out.print("Vaya!!! Te quedaste sin intentos, la palabra correcta era: " + secreta);
        }
        lecScanner.close();
    }

    private static String[] returnAbecedario(String secreta, char palabraAnalizar, String[] abecedario,String lista[]) {
        for (int i = 0; i < 27; i++) {
            if (abecedario[i].equals(Character.toString(palabraAnalizar))) {
            	if (Arrays.asList(lista).contains(Character.toString(palabraAnalizar))) {
                    abecedario[i] =ANSI_RED + abecedario[i] + ANSI_RESET;
                }else {
                    abecedario[i] = ANSI_RED + abecedario[i] + ANSI_RESET;
                }
            }
        }
        return abecedario;
    }

    private static void imprimirAbecedario(char palabraAnalizar, String[] palabra, String[] abecedario) {
        for (int i = 0; i < 27; i++) {
            if (i == 8 || i == 17) {
                System.out.print("│ "+abecedario[i]+" │");
                System.out.println("");
            } else {
                System.out.print("│ "+abecedario[i]+" │");
            }
        }
        System.out.println("");
    }

    private static void imprimirLinea(String[] almacenPalabra, String[] palabra, int contador) {
        String rellenar = "";
        for (int i = 0; i < 5; i++) {
            rellenar += (palabra[i] + " ");
        }
        rellenar += " ";
        System.out.println("");
        almacenPalabra[contador] = rellenar;

        for (int i = 0; i < 6; i++) {
            System.out.println("  " + almacenPalabra[i]);
        }

        System.out.println("");

    }
}
