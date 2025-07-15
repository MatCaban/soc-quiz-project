import utility.FileNames;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Questions {
    private final List<Question> questions;

    public Questions() {
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestions(){
        return this.questions;
    }

    public void setQuestions(String topic) {
        this.questions.clear(); // clearing arraylist before filling again
        String separator = File.separator;
        String path = String.join(separator, new String[]{FileNames.SRC.getFileName(), FileNames.QUESTION.getFileName()});
        try {
            FileReader file = new FileReader(path + separator + topic + ".txt");
            BufferedReader reader = new BufferedReader(file);
            Scanner scanner = new Scanner(reader);

            String line;
            String question = "";
            List<String> alternatives = new ArrayList<>();
            int answer = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("?")){
                    question = line;
                } else if (line.contains("-")) {
                    alternatives.add(line);
                }  else if (line.contains(".")){
                    List<String> copyAlternatives = new ArrayList<>(alternatives);
                    this.questions.add(new Question(question, copyAlternatives));
                    answer = 0;
                    alternatives.clear();
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
}
