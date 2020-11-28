import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteXML {

    public static List<String> listout = new ArrayList<>();

    public static void main(String[] args) {
        try {
            readFile("InputXML");
            writeFile();
        } catch (IOException ex) {
            System.out.printf("SOMETHIG WRONG!!!");
            ex.printStackTrace();
        }
    }

    public static void readFile(String nameFileIn) throws IOException {
        ReadWriteXML currentObject = new ReadWriteXML();
        File fileCurrent = new File(currentObject.getClass().getClassLoader().getResource(nameFileIn).getFile());
        BufferedReader inFile = new BufferedReader(new FileReader(fileCurrent));
        String strokaIn;
        int number = 0;
        while (inFile.ready()) {
            strokaIn = inFile.readLine();
            if (strokaIn.matches(".*<.*li.*>.*</.*li.*>")) {
                strokaIn = strokaIn.replaceAll(">.*</",">IT'S MY OPTION OF THIS TAG NUMBER №" + number + " </");
                number++;
            }
            listout.add(strokaIn);
        }
        inFile.close();
    }

    public static void writeFile() throws FileNotFoundException {
        if (listout.isEmpty()) {
            return;
        } else {
            File outFile = new File("src/main/resources/OutputXML.xml");
            PrintWriter outPrint = new PrintWriter(outFile);
            for (String stroka : listout){
                outPrint.println(stroka);
            }
            outPrint.close();
        }
    }
}
