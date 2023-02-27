package mystring;
import java.io.*;
import java.util.Scanner;


public class WorkWithFile {

  static String ReadFromFile () throws Exception  {
    String s=null;
    String vvod="";
    String FileName="";
    boolean FlagCreate=false;
    int i;
    
    Scanner LineReader = new Scanner (System.in);
    
    while (FlagCreate!=true) {
      System.out.print("\nВведите имя файла: ");
      FileName = LineReader.nextLine()+".txt";
    
      if (FileName.contains("+")||FileName.contains("-")||FileName.contains("\\")
        ||FileName.contains("|")||FileName.contains(",")||FileName.contains("*")
        ||FileName.contains("/")||FileName.contains("&")||FileName.contains("№")
        ||FileName.contains("`")||FileName.contains(">")||FileName.contains("|")
        ||FileName.contains("#")||FileName.contains("<")||FileName.contains("(")||FileName.contains(")")) {
          System.out.println("\nВ имени файла содержатся неприемлемые символы!"
                  + "\nПожалуйста введите еще раз."); 
          FlagCreate = false;  
      } else FlagCreate = true;
    }
    
    
    try {
      BufferedReader rff = new BufferedReader (new FileReader (new File (FileName)));
      s = rff.readLine();
      rff.close();
    } catch (Exception e) {
        
        System.out.println("\nФайл не существует, хотите создать ?");
        while (vvod!="2") {
          System.out.println("\n|------|Создание файла|------|");
          System.out.println("1) Да");
          System.out.println("2) Нет");
          System.out.print (">>");
          vvod = LineReader.nextLine();  

          switch (vvod) {
              case "1" : System.out.print("\nВведите строку, которую хотите записать в этот файл: ");
                         s = LineReader.nextLine();
                         WorkWithFile.FileCreation(FileName);
                         WorkWithFile.WriteToFile (FileName, s, false);
                         vvod="2";
                         System.out.println("Процесс прошел успешно, ваша строка: "+s);                          
                         break;
                      
              case "2" : vvod="2";
                         break;
                  
              default : System.out.println("\nОшибка ввода! \nВводите пожалуйста только предложенные варианты: {1,2}.");
          }
        }
      }  
    if (s!="") System.out.println("Процесс прошел успешно, ваша строка: "+s);
    return s; 
 }
  
  static char WriteToFile (String FileName, String s, boolean flag) throws Exception {
    char status='1';
    try {
      if ((new File(FileName)).exists()&&(flag==false)) status='2';
        else { 
          BufferedWriter wtf = new BufferedWriter (new FileWriter (new File (FileName)));
          wtf.write(s);
          wtf.close(); 
        }     
    } catch (Exception e){
        if ((new File(FileName)).exists()&&(flag==false)) status='2';
          else status='0';
      }
    return status;
  }
  
  static void FileCreation (String FileName) throws Exception {
    File NewFile = new File(FileName);
      try {
        NewFile.createNewFile();
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
}

// чтении системных файлов
// ввод имя файла контроль
// static и private