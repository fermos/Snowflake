
import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author Mosè Ferrazzini
 * @version 20.12.2019
 */
public class Saver {
    private Path origine;
    /**
     * Costruttore per importare i punti.
     * @param cutPoints Punti di taglio.
     * @param trianglePoints Punti del triangolo modificato.
     * @param destinazione Dove salvare i punti.
     * @throws IOException 
     */
    public Saver(Point[] cutPoints, Point[] trianglePoints, Path destinazione) throws IOException {
        String cutPointsString = arrayToCSV(cutPoints);
        String trianglePointsString = arrayToCSV(trianglePoints);
        Files.write(destinazione, cutPointsString.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(destinazione, "\r\n".getBytes(), StandardOpenOption.APPEND);
        Files.write(destinazione, trianglePointsString.getBytes(), StandardOpenOption.APPEND);
    }
    
    /**
     * Costruttore con solo l'origine da impostare.
     * @param origine 
     */
    public Saver(Path origine) {
        this.origine = origine;
    }
    
    /**
     * Trasforma da array a csv.
     * @param array
     * @return Csv risultante dall'array.
     */
    public String arrayToCSV(Point[] array) {
        String tempString = "";
        for(int i = 0; i< array.length; i++) {
            tempString += array[i].x + "," + array[i].y;
            if(i != array.length-1) {
                tempString += ",";
            }
        }
        return tempString;
    }
    
    /**
     * Trasforma da csv a array.
     * @param str
     * @return Array risultante dal csv.
     */
    public Point[] csvToArray(String str) {
        String[] s = str.split(",");
        Point[] result = new Point[s.length/2];
        System.out.println(str);
        System.out.println(result.length);
        for (int i = 0; i < s.length; i+=2) {
            if(i != 0) {
                result[i/2] = new Point(Integer.parseInt(s[i]), Integer.parseInt(s[i+1]));
            }else{
                result[i] = new Point(Integer.parseInt(s[i]), Integer.parseInt(s[i+1]));
            }
        }
        return result;
    }
    
    /**
     * Getter per i punti di taglio.
     * @return Punti di taglio.
     * @throws IOException 
     */
    public Point[] getCutPoints() throws IOException{
        List<String> lines = Files.readAllLines(origine);
        return csvToArray(lines.get(0));
    }

    /**
     * Getter per i punti del triangolo modificato.
     * @return Punti del triangolo modificato.
     * @throws IOException 
     */
    public Point[] getTrianglePoints() throws IOException{
        List<String> lines = Files.readAllLines(origine);
        return csvToArray(lines.get(1));
    }    
}
