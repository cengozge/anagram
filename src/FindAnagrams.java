
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindAnagrams {
    private static String PATH = "E://dev//workspaces//eclipseLuna//Anagram";
    private static String INPUT_FILE_NAME = "sample.txt";
    private static final String OUTPUT_FILE_NAME = "output.txt";
    private static final String SAMPLE_TEMP_OUT_FILE = "tempOut.txt";
    private static final List<Word> LIST_ORIGINAL = new ArrayList<Word>();
    private static final List<Word> LIST_SORTED = new ArrayList<Word>();

    public static void main(String[] args) throws IOException {
        PATH = args[0];
        INPUT_FILE_NAME = args[1];
        FindAnagrams.readFromFile(PATH, INPUT_FILE_NAME);
    }

    public static void readFromFile(String path, String fileName) throws IOException {
        FileReader fr = new FileReader(String.valueOf(path) + "//" + fileName);
        BufferedReader br = new BufferedReader(fr);
        File fout = new File("tempOut.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        String line = "";
        int lineNumber = 0;
        while ((line = br.readLine()) != null) {
            Word wordOriginal = new Word(line, Integer.valueOf(lineNumber));
            LIST_ORIGINAL.add(wordOriginal);
            String sortedWord = FindAnagrams.sortWord(line);
            Word wordSorted = new Word(sortedWord, Integer.valueOf(lineNumber));
            LIST_SORTED.add(wordSorted);
            bw.write(String.valueOf(line) + "," + sortedWord);
            bw.newLine();
            bw.flush();
            ++lineNumber;
        }
        fr.close();
        br.close();
        fos.close();
        bw.close();
        FindAnagrams.readFromOutFile(PATH);
    }

    public static void readFromOutFile(String path) throws IOException {
        File fout = new File("output.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        Map<String, List<Word>> anagrams = LIST_SORTED.stream().collect(Collectors.groupingBy(Word::getName));
        for (Map.Entry<String, List<Word>> entry : anagrams.entrySet()) {
            if (entry.getValue().size() <= 1) continue;
            String writeOutput = "";
            System.out.printf("Key : %s %n", entry.getKey());
            int i = 0;
            while (i < entry.getValue().size()) {
                Integer index = entry.getValue().get(i).getIndex();
                System.out.println(LIST_ORIGINAL.get(index).getName());
                writeOutput = writeOutput.concat(LIST_ORIGINAL.get(index).getName());
                writeOutput = writeOutput.concat(" ");
                ++i;
            }
            bw.write(writeOutput);
            bw.newLine();
        }
        bw.close();
    }

    public static String sortWord(String word) {
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }
}