package mystring;

import WorkWithString.MyString;
import java.util.Scanner;
import java.io.*;

/*ввод пустой строки в консоле.
   запись файла с недопустимыми символами в имени.
   перезапись файла с атрибутом "только чтение".
 */
public class Main {

    public static void main(String[] args) {

        //объявление переменных
        String vvod = "";
        String vvod1 = "";
        String s = null;
        boolean flagstart = false;

        Scanner scan = new Scanner(System.in);

        System.out.println("Вас приветствует программа для обработки строки и последующего вывода желаемых результатов");
        System.out.println("Для начала выберите откуда вы хотите считать строку :");

        while (vvod != "3") {

            /*Изменить меню: первое меню это спосо ввода данных*/
            if (flagstart) {
                System.out.println("\n|------|МЕНЮ|------|");
                System.out.println("1) Выбрать способ ввода даных");
                System.out.println("2) Выполнить задания и вывести результат");
                System.out.println("3) Выход");

                System.out.print(">>");
                vvod = scan.nextLine();
            } else {
                vvod = "1";
                flagstart = true;
            }

            switch (vvod) {
                case "1":
                    s = WayOfReading();
                    break;

                case "2":
                    try {
                        DoTasks(s);
                    } catch (Exception e) {
                        System.out.println("\nОшибка, данные не введены.");
                        s = WayOfReading();
                    }
                    break;

                case "3":
                    vvod = "3";
                    break;

                default:
                    System.out.println("\nОшибка ввода! \nВводите пожалуйста только предложенные варианты: {1,2,3}.");
            }
        }
        System.out.println("\nДо свидания, удачного дня!");

    }

    static String WayOfReading() {
        String ss = "";
        String vvod = "";

        //BufferedReader LineReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);

        while (vvod != "3") {

            System.out.println("\n|------|Выбор способа ввода данных|------|");
            System.out.println("1) Считать из файла");
            System.out.println("2) Ввести на клавиатуре");
            System.out.println("3) Назад в главное меню");

            System.out.print(">>");
            vvod = scan.nextLine();

            switch (vvod) {

                case "1":
                    try {
                        ss = WorkWithFile.ReadFromFile();
                    } catch (Exception e) {
                        System.out.println("\nВвод данных не осуществлен, попробуйте снова");
                    }

                    vvod = "3";
                    break;

                case "2":
                    System.out.print("\nВведите строку: ");
                    ss = scan.nextLine();
                    if (ss.length() == 0) {
                        System.out.println("Ввод данных не осуществлен, пожалуйста повторите попытку.");
                        vvod = "2";
                    } else {
                        System.out.println("Ввод данных осуществлен, ваша строка: " + ss);
                        vvod = "3";
                    }
                    break;

                case "3":
                    vvod = "3";
                    break;

                default:
                    System.out.println("\nОшибка ввода! \nВводите пожалуйста только предложенные варианты: {1,2,3}.");

            }
        }
        return (ss);
    }

    static void DoTasks(String ss) {

        String[] out1 = new String[2]; //массив для вывода
        String[] out2 = new String[3]; //массив для вывода
        int kol; // количество знаков препинания  
        String FileName = null;
        String result = "";
        char FlagWrite = '0';
        char FlagWrite1 = '0';

        MyString s1 = new MyString(ss);
        out1 = s1.CentralWord();
        out2 = s1.WordsHowFirstWord();
        kol = s1.KolvoZnak();

        String vvod = "";
        String vvod1 = "";
        Scanner scan = new Scanner(System.in);

        result = "\nВаша строка: " + ss
                + "\r\nРезультат выполнения заданий:"
                + "\r\nЗадача a) В середине строки находится " + out1[0] + ' ' + out1[1]
                + "\r\nЗадача b) Знаков препинания в данной строке: " + kol
                + "\r\nЗадача с) Первое слово: " + out2[0] + ", его длина: " + out2[1] + ", слова такой же длины: " + out2[2];

        while (vvod != "3") {
            System.out.println("\n|------|Вывод результата|------|");
            System.out.println("1) Записать в файл");
            System.out.println("2) Вывести на экране");
            System.out.println("3) Назад в главное меню");
            System.out.print(">>");

            vvod = scan.nextLine();

            switch (vvod) {
                case "1":
                    System.out.print("\nВведите имя файла: ");
                    FileName = scan.nextLine();
                    FileName += ".txt";

                    try {
                        FlagWrite = WorkWithFile.WriteToFile(FileName, result, false);
                        switch (FlagWrite) {
                            case '0':
                                System.out.println("\nПроизошла ошибка записи в файл! "
                                        + "\nПопробуйте ввести другое имя или выберите вывод на экран.");
                                break;

                            case '1':
                                System.out.println("\nЗапись в файл успешно выполнена");
                                break;

                            case '2':
                                while (vvod1 != "2") {
                                    System.out.println("\nФайл уже существует, хотите перезаписать ?");
                                    System.out.println("1) Да");
                                    System.out.println("2) Нет");
                                    System.out.print(">>");

                                    vvod1 = scan.nextLine();
                                    switch (vvod1) {
                                        case "1":
                                            FlagWrite1 = WorkWithFile.WriteToFile(FileName, result, true);
                                            if (FlagWrite1 == '1') {
                                                System.out.println("\nФайл успешено перезаписан.");
                                            }
                                            if (FlagWrite1 == '0') {
                                                System.out.println("\nПроизошла ошибка при перезаписи!"
                                                        + "\nВозможно файл обладает несовместимыми с данными действиями атрибутами, попробуйте ввести другое имя файла.");
                                            }
                                            vvod1 = "2";
                                            break;

                                        case "2":
                                            vvod1 = "2";
                                            break;
                                    }
                                }

                        }
                    } catch (Exception ex) {
                        System.out.println("\nПроизошла ошибка записи в файл!");
                    }

                    if (FlagWrite == '1') {
                        vvod = "3";
                    }
                    break;

                case "2":
                    System.out.println(result);
                    vvod = "3";
                    break;

                case "3":
                    vvod = "3";
                    break;

                default:
                    System.out.println("\nОшибка ввода! \nВводите пожалуйста только предложенные варианты: {1,2,3}.");

            }
        }
    }

}

/*
        String ss1="abc, bca";
        String ss2="a, a, bfjkbifngkghk.";
        String ss3="abdrt, yturnf.";
        String ss4="a, aa: aaa aaaa, aaaaa."; 
        
        MyString s1 = new MyString ();
        out1 = s1.CentralWord();
        out2 = s1.WordsHowFirstWord();
        kol = s1.KolvoZnak();
        System.out.println("Тест для первого предложения:");
        System.out.println("Задача a) В середине строки находится "+out1[0]+' '+out1[1]);
        System.out.println("Задача b) Знаков препинания в данной строке: "+kol);
        System.out.println("Задача с) Первое слово: "+out2[0]+", его длина: "+out2[1]+", слова такой же длины: "+out2[2]+"\n");
        
      //Тест 2
        MyString s2 = new MyString (ss2);
        out1 = s2.CentralWord();
        out2 = s2.WordsHowFirstWord();
        kol = s2.KolvoZnak();
        System.out.println("Тест для второго предложения:");
        System.out.println("Задача a) В середине строки находится "+out1[0]+' '+out1[1]);
        System.out.println("Задача b) Знаков препинания в данной строке: "+kol);
        System.out.println("Задача с) Первое слово: "+out2[0]+", его длина: "+out2[1]+", слова такой же длины: "+out2[2]+"\n");
      
      //Тест 3
        MyString s3 = new MyString (ss3);
        
        //клонирование s3
        MyString MyClon = new MyString(s3);
        
        out1 = MyClon.CentralWord();
        out2 = MyClon.WordsHowFirstWord();
        kol = MyClon.KolvoZnak();
        System.out.println("Тест для третьего предложения:");
        System.out.println("Задача a) В середине строки находится "+out1[0]+' '+out1[1]);
        System.out.println("Задача b) Знаков препинания в данной строке: "+kol);
        System.out.println("Задача с) Первое слово: "+out2[0]+", его длина: "+out2[1]+", слова такой же длины: "+out2[2]+"\n");
        
      //Тест 4
        MyString s4 = new MyString (ss4);        
        out1 = s4.CentralWord();
        out2 = s4.WordsHowFirstWord();
        kol = s4.KolvoZnak();
        System.out.println("Тест для четвертого предложения:");
        System.out.println("Задача a) В середине строки находится "+out1[0]+' '+out1[1]);
        System.out.println("Задача b) Знаков препинания в данной строке: "+kol);
        System.out.println("Задача с) Первое слово: "+out2[0]+", его длина: "+out2[1]+", слова такой же длины: "+out2[2]+"\n");
        
 */
