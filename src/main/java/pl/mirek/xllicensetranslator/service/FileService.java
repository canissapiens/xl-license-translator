package pl.mirek.xllicensetranslator.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    public void writeStringToFile(String fileName, String content) {
        File fileDir = new File(fileName);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF8"))) {
            writer.append(content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readStringFromFile(String filename) {
        StringBuffer sb = new StringBuffer();
        File fileDir = new File(filename);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"))) {
            String string;
            while ((string = in.readLine()) != null) {
                sb.append(string);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
