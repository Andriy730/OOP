package ua.lpnuai.oop.gordon01;

/**
 *  Лабораторна робота №1
 *  <p>Утилітарний клас для обробки даних користувача.</p>
 *
 *  Завдання:
 *  <p>З тексту видалити всі слова заданої довжини, що
 *  починаються на приголосну літеру.</p>
 *
 * @version 1.0 05-03-2019
 * @author Andriy Gordon
 */

class StringEditor {

    /**
     * Метод для обробки даних користувача
     * @param text текст для обробки
     * @param deleteLength довжина слів, що мають бути видалені
     * @param debug
     * @return Відредагований текст
     */
     static String edit(String text, int deleteLength, boolean debug){

        StringBuilder editedText = new StringBuilder();
        // відредагувати задану стрічку згідно з умовою
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == ' '){
                continue;
            }

            int counter = 0;
            // підрахунок довжини слова
            for(int j = i; j < text.length(); j++){
                if(text.charAt(j) == ' '){
                    break;
                }
                counter++;
            }

            String word = text.substring(i, i + counter);
            if(debug) System.out.println("Слово, що перевіряється: " + word);
            // додати слово, якщо воно не задовільняє умову видалення
            if(counter != deleteLength || f(word)){
                editedText.append(word);
                editedText.append(' ');
            }
            i += counter;
            if(debug) System.out.println(editedText.toString());
        }


        return editedText.toString();
    }

    // функція для перевірки чи починається слово з голосної букви
    private static boolean f(String  word){
        word = word.toLowerCase();
        char c = word.charAt(0);
        char[] chars = {'\u0430', '\u0435', '\u0454', '\u0438', '\u0456', '\u0457', '\u043E', '\u0443', '\u044E', '\u044F'};
        for(char a: chars){
            if(a == c){
                return true;
            }
        }
        return false;
    }
}
