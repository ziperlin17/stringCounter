
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static List<List<File>> mainList = new ArrayList<>();

    public static List<File> ifDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        List<File> tempList = new ArrayList<>();
        for ( File file : dir.listFiles() ){
            if ( file.isFile()  && file.getName().contains(".java")) {
                tempList.add(file);
            }
            if (file.isDirectory()) {
                if (tempList.size() != 0) {
                    mainList.add(tempList);
                }
                if (ifDirectory(file.getPath()).size() != 0) {
                    mainList.add(ifDirectory(file.getPath()));
                }

            }
        }
        return tempList;
    }

    public static int countByPath(String FilePath) throws Exception {
        File file = new File(FilePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[(int)file.length()];
        fis.read(byteArray);
        String data = new String(byteArray);
        String[] stringArray = data.split("\r\n");
        return stringArray.length;
    }



    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к папке в виде C:\\DirectoryPathExample ");
//        String path = "C:\\java";
        String path = scanner.next();
        File dir = new File(path);
        List<File> lst = new ArrayList<>();
        for ( File file : dir.listFiles() ){
            if ( file.isFile()  && file.getName().contains(".java")) {
                lst.add(file);
            }
            if (file.isDirectory()) {
                ifDirectory(path);
            }
        }
        mainList.add(lst);
        int quantity = 0;

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < mainList.size(); i++) {
            for (int j = 0; j < mainList.get(i).size(); j++) {
                if (stringList.contains(mainList.get(i).get(j).getPath()) == false) {
                    stringList.add((mainList.get(i).get(j).getPath()));
                }
            }
        }

        System.out.println(stringList.toString());
        for (int i = 0; i < stringList.size(); i++) {
            quantity += countByPath(stringList.get(i));
        }
        System.out.println(quantity);
    }
}
