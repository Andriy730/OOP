package ua.lpnuai.oop.gordon02;

public class Editor {

    public static String edit(String text, int lengthToDelete){
        Sentence sentence = new Sentence(text);
        StringBuilder builder = new StringBuilder();
        sentence.forEach((s) -> {if(f(s) || s.length() != lengthToDelete) builder.append(s + " "); });
        return builder.toString();
    }

    private static boolean f(String  word){
        word = word.toLowerCase();
        char c = word.charAt(0);
        char[] chars = {'\u0430', '\u0435', '\u0454', '\u0438', '\u0456',
                '\u0457', '\u043E', '\u0443', '\u044E', '\u044F'};
        for(char a: chars){
            if(a == c){
                return true;
            }
        }
        return false;
    }
}
