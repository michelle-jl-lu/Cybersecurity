/******************************************************************************
 *  Michelle Lu    Cybersecurity PD 5
 *
 *  Compilation:  javac PlayfairCipher.java
 *  Execution:    java PlayfairCipher encode code playfaircipherkey
 *                java PlayfairCipher decode code playfaircipherkey
 *
 *  Dependencies: none
 *
 *  Encodes a plain text given a playfair cipher key, decodes a given encoded text using
 *  the given playfair cipher key.
 *
 *  % java PlayfairCipher encode Dragonball ABCDEFGHIKLMNOPQRSTUVWXYZ
 *  Encoded Text: BTBFTSGFNVPV
 *
 *  % java PlayfairCipher decode BTBFTSGFNVPV ABCDEFGHIKLMNOPQRSTUVWXYZ
 *  Decoded Text: DRAGONBALXLZ
 *
 *  % java PlayfairCipher encode JIMJAMESJACK QWERTYUIOPASDFGHKLZXCVBNM
 *  Encoded Text: PLPBYDBTDUSVVN
 *
 *  % java PlayfairCipher decode PLPBYDBTDUSVVN QWERTYUIOPASDFGHKLZXCVBNM
 *  Decoded Text: IXIMIAMESIACKZ
 *
 ******************************************************************************/

public class PlayfairCipher {

    public static int[] locate(String letter, String[][] playfairKey) {
        int[] coords = new int[2];
        for (int i = 0; i < playfairKey.length; i++) {
            for (int j = 0; j < playfairKey[i].length; j++) {
                if (playfairKey[i][j].equals(letter)) {
                    coords[0] = i;
                    coords[1] = j;
                }
            }
        }
        return coords;
    }

    public static String changeJ (String code){
        String newCode = "";
        for (int i = 0; i < code.length()-1; i++){
            String testcode = code.substring(i,i+1);
            if (testcode.equals("J")) newCode += "I";
            else newCode += testcode;
        }
        if (code.substring(code.length()-1).equals("J")) newCode+= "I";
        else newCode += code.substring(code.length()-1);
        return newCode;
    }

    // ENCODING CODE
    public static void verticalEncode(int[] firstLetter, int[] secondLetter, String[][] playfairKey) {
        int limit = playfairKey.length - 1;
        if (firstLetter[1] < limit) {
            System.out.print(playfairKey[firstLetter[0]][firstLetter[1] + 1]);
        }
        if (firstLetter[1] == limit) {
            System.out.print(playfairKey[firstLetter[0]][0]);
        }
        if (secondLetter[1] < limit) {
            System.out.print(playfairKey[secondLetter[0]][secondLetter[1] + 1]);
        }
        if (secondLetter[1] == limit) {
            System.out.print(playfairKey[secondLetter[0]][0]);
        }
    }

    public static void horizontalEncode(int[] firstLetter, int[] secondLetter, String[][] playfairKey) {
        int limit = playfairKey[0].length - 1;
        if (firstLetter[0] == limit) {
            System.out.print(playfairKey[0][firstLetter[1]]);}
        else if (firstLetter[0] < limit) {
            System.out.print(playfairKey[firstLetter[0] + 1][firstLetter[1]]);
        }
        if (secondLetter[0] == limit) {
            System.out.print(playfairKey[0][secondLetter[1]]);
        }
        else if (secondLetter[0] < limit) {
            System.out.print(playfairKey[secondLetter[0] + 1][secondLetter[1]]);
        }

    }

    public static void regularEncode(int[] firstLetter, int[] secondLetter, String[][] playfairKey) {
        int firstLetterrow = firstLetter[0];
        int firstLettercol = firstLetter[1];
        int secondLetterrow = secondLetter[0];
        int secondLettercol = secondLetter[1];
        System.out.print(playfairKey[firstLetterrow][secondLettercol]);
        System.out.print(playfairKey[secondLetterrow][firstLettercol]);
    }

    public static void encode(String code, String[][] playfairKey) {
        System.out.print("Encoded Code: ");
        for (int i = 0; i <= code.length() - 2; i++) {
            String currentPair = code.substring(i, i + 2);
            String firstLetter = currentPair.substring(0, 1);
            String secondLetter = currentPair.substring(1);
            int[] firstLetterCoords = locate(firstLetter, playfairKey);
            int[] secondLetterCoords = locate(secondLetter, playfairKey);
            if (firstLetterCoords[1] == secondLetterCoords[1])
                verticalEncode(firstLetterCoords, secondLetterCoords, playfairKey);
            else if (firstLetterCoords[0] == secondLetterCoords[0])
                horizontalEncode(firstLetterCoords, secondLetterCoords, playfairKey);
            else regularEncode(firstLetterCoords, secondLetterCoords, playfairKey);
            i++;
        }
        System.out.println("");
    }

    // DECODING CODE
    public static void verticalDecode(int[] firstLetter, int[] secondLetter, String[][] playfairKey) {
        int limit = playfairKey.length - 1;
        if (firstLetter[1] > 0) {
            System.out.print(playfairKey[firstLetter[0]][firstLetter[1]-1]);
        }
        if (firstLetter[1] == 0) {
            System.out.print(playfairKey[firstLetter[0]][limit]);
        }
        if (secondLetter[1] > 0) {
            System.out.print(playfairKey[secondLetter[0]][secondLetter[1]-1]);
        }
        if (secondLetter[1] == 0) {
            System.out.print(playfairKey[secondLetter[0]][limit]);
        }
    }

    public static void horizontalDecode(int[] firstLetter, int[] secondLetter, String[][] playfairKey) {
        int limit = playfairKey[0].length - 1;
        if (firstLetter[0] > 0) {
            System.out.print(playfairKey[firstLetter[0]-1][firstLetter[1]]);
        }
        if (firstLetter[0] == 0) {
            System.out.print(playfairKey[limit][firstLetter[1]]);
        }
        if (secondLetter[0] > 0) {
            System.out.print(playfairKey[secondLetter[0]-1][secondLetter[1]]);
        }
        if (secondLetter[0] == 0) {
            System.out.print(playfairKey[limit][1]);
        }
    }

    public static void regularDecode(int[] firstLetter, int[] secondLetter, String[][] playfairKey) {
        int firstLetterrow = firstLetter[0];
        int firstLettercol = firstLetter[1];
        int secondLetterrow = secondLetter[0];
        int secondLettercol = secondLetter[1];
        System.out.print(playfairKey[firstLetterrow][secondLettercol]);
        System.out.print(playfairKey[secondLetterrow][firstLettercol]);
    }

    public static void decode(String code, String[][] playfairKey) {
        System.out.print("Decoded Code: ");
        for (int i = 0; i <= code.length() - 2; i++) {
            String currentPair = code.substring(i, i + 2);
            String firstLetter = currentPair.substring(0, 1);
            String secondLetter = currentPair.substring(1);
            int[] firstLetterCoords = locate(firstLetter, playfairKey);
            int[] secondLetterCoords = locate(secondLetter, playfairKey);
            if (firstLetterCoords[1] == secondLetterCoords[1])
                verticalDecode(firstLetterCoords, secondLetterCoords, playfairKey);
            else if (firstLetterCoords[0] == secondLetterCoords[0])
                horizontalDecode(firstLetterCoords, secondLetterCoords, playfairKey);
            else regularDecode(firstLetterCoords, secondLetterCoords, playfairKey);
            i++;
        }
        System.out.println("");
    }

    public static String[][] playfairKey(String key) {
        String[][] playfairKey = new String[5][5];
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 4 && j == 5) {
                    playfairKey[i][j] = key.substring(counter);
                    break;
                } else {
                    playfairKey[i][j] = key.substring(counter, counter + 1);
                }
                counter++;
            }
        }
        return playfairKey;
    }

    public static String insertDouble(String code) {
        for (int i = 0; i < code.length() - 1; i++) {
            String firstLetter = code.substring(i, i + 1);
            String secondLetter = code.substring(i + 1, i + 2);
            if (firstLetter.equals(secondLetter)) {
                code = code.substring(0, i + 1) + "X" + code.substring(i + 1);
            }
            i++;
        }
        return code;
    }

    public static void main(String[] args) {
        String choice = (args[0]).toUpperCase();
        String code = (args[1]).toUpperCase();
        code = changeJ(code);
        code = insertDouble(code);
        String key = (args[2]).toUpperCase();
        String[][] playfairKey = playfairKey(key);
        if ((code.length() % 2) != 0) code += "Z";
        if (choice.equals("ENCODE")){
            encode(code, playfairKey);
        }
        if (choice.equals("DECODE")){
            decode(code, playfairKey);
        }

    }
}
