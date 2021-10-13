package net.iterart.chingolo.util;

public class TextoUtil {
	
	public static boolean contieneSoloLetras(String texto) {
        for (char c : texto.toCharArray()) {
            //Si no está entre a y z o A y Z...
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    // Palabras que empiecen con mayúscula
    public static String primerLetraMayuscula(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        } else {
            return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
        }
    }

}
