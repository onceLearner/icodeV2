package com.Emi.IcodeV2.service;

import com.Emi.IcodeV2.controller.SubmitCodeController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CodeHandlerV2 {


    public static String judgeWithoutSsh(String language, String clientCode,String input,String reference) throws IOException {


        //creation des fichier poour la lecture et l 'ecriture des erreures des resultats
        File fileInput = new File("in.txt");
        File fileOutput=new File ("out.txt");
        File fileError=new File ("error.txt");
        File fileRef=new File ("ref.txt");


        // write to input and reference files
        FileWriter writerIn=new FileWriter(fileInput);
        FileWriter writerRef=new FileWriter(fileRef);

        writerIn.write(input);
        writerRef.write(reference);
        writerIn.close();
        writerRef.close();




        // les commandes selons les languages
        String pyth=" echo " + clientCode + " > amir.py && python amir.py ";

        String c=" echo '" + clientCode + "' > file2.c && gcc -o reda2 file2.c && ./reda2";

        String jav=" echo '" + clientCode + "' > hamid.java && javac hamid.java  && java hamid"  ;
        
        



        ProcessBuilder processBuilder=new ProcessBuilder();

//          processBuilder.redirectOutput(ProcessBuilder.Redirect fileOutput);
        processBuilder.redirectInput(fileInput);
//        processBuilder.redirectErrorStream(true);
//        processBuilder.redirectError(fileError);





        switch (language) {

            case "c":  processBuilder.command("cmd.exe","/c",c); break;

            case "java":processBuilder.command("cmd.exe","/c",jav); break;

            case  "python":processBuilder.command("cmd.exe","/c",pyth); break;

        }

        Process process=processBuilder.start();
        File tryIt=new File("try.txt");

        String errorWithAppache=IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8);

        String InputWithAppache="33";
        String tryAppacheIou= IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);

















//        String errorFound = new Scanner(error_file).useDelimiter("\\Z").next();
        String errorFound="wrong code";

        //  Reading files into string text + Replace all new line and whitespace at the end with ""
        String text1= FileUtils.readFileToString(fileOutput);

        String text3= FileUtils.readFileToString(fileError);
//        Long size=fileOutput.length();



//return  "this the length of the files outputed: " +size + text1;
//        return text1 + text2 +text3;
        text1=text1.replaceAll("\\r|\\n|\\s+$", "");

        String text2=FileUtils.readFileToString(fileRef);
        text2=text2.replaceAll("\\r|\\n", "");

        //check if the files are equals
        boolean  issequals=text1==text2;
//
//        if (issequals) return ("correct solution "+issequals);
//        else return text1+" and the other file " + text2 ;


        return   "this appache thing"+tryAppacheIou + "\n here is the errros : "+ errorWithAppache;







    }

    public static String getOutputResult( Process process,File ktebhere) throws IOException {
        Writer writer=new FileWriter(ktebhere);

        InputStream inStream = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
        String line=null;
        String whatHaveBeenWrote="";
        try {
            while((line = reader.readLine())!=null)
            {
                writer.append(line);
                whatHaveBeenWrote+=line;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;


    }

}

// TODO: 04/05/2020 commands should be in an list of string