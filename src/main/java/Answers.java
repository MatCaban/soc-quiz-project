import utility.FileNames;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Answers {
    private List<Answer> answers;

    public Answers(){
        this.answers = new ArrayList<>();
    }

    public void setAnswers(String topic) {
        this.answers.clear(); // clearing arraylist before filling again
        String separator = File.separator;
        String path = String.join(separator, new String[]{FileNames.SRC.getFileName(), FileNames.QUESTION.getFileName()});
        try {
            FileReader file = new FileReader(path + separator + topic + ".txt");
            BufferedReader reader = new BufferedReader(file);
            Scanner scanner = new Scanner(reader);

            String line;
            int answer = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                    answer = Integer.parseInt(line);
                } else if (line.contains(".")){
                    this.answers.add(new Answer(answer));
                    answer = 0;
                }
            }
            file.close();
            reader.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("Cannot read file");
            e.getStackTrace();
        }
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }
}
