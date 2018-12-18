package kata5p1;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MailListReader {
    
    public MailListReader(){}
    
    public List<String> read(String fileName) throws IOException{
        List<String> mails = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String token;
            while((token = input.readLine()) != null) {
                if(token.matches(".+@(.+[.].+)")){
                    mails.add(new String(token));
                }
            }
            input.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return mails;
    }
    
}
