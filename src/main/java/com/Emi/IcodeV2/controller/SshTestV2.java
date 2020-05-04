package com.Emi.IcodeV2.controller;
import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@RestController
@CrossOrigin("*")
public class SshTestV2 {

    String clientCode1 = "#include<stdio.h>\n" +
            "int main() {\n" +
            "int var;\n" +
            "scanf(\"%d\",&var);\n" +
            "printf(\"%d\",var);\n" +
            "}";
//    @RequestMapping(value="v1",method = RequestMethod.GET)
//    public  String getClientCode()
//







    @PostMapping("v1")// you can use just @GetMapping("v1") as shortcut to that
    public String  judge(@RequestBody String clientCode) throws IOException {


        //connection
        SSHByPassword shel = new SSHByPassword("yasmina.emi.ac.ma", 22, "melissati", "melissati ");
        Shell.Plain plain = new Shell.Plain(shel);

        // create files for input and output stream
        //paths to the 3 files : input file , user outputted file and compare file
        Path Path_to_input_file = Paths.get("src/main/resources/Problems/p1/in.txt");
        Path Path_to_ouput_file = Paths.get("src/main/resources/Problems/p1/out.txt");
        Path Path_to_compare_file = Paths.get("src/main/resources/Problems/p1/reference.txt");
        Path Path_to_error_file = Paths.get("src/main/resources/Problems/p1/error.txt");


        //create file to read input from it
        File In_file = new File(Path_to_input_file.toString()).getAbsoluteFile(); //add getAbsoluteFile because  it recognize files in same dir can't go up
        Scanner fileScanner = new Scanner(In_file);
        String input_file_conent = fileScanner.nextLine();


        // create file to pass User output to it and also potentiel erros
        File Out_file = new File(Path_to_ouput_file.toString()).getAbsoluteFile(); // toString because the argument is string not a path object
        File compare_file = new File(Path_to_compare_file.toString()).getAbsoluteFile();
        File error_file = new File(Path_to_error_file.toString()).getAbsoluteFile();





//        String text = plain.exec(" cd forTestPurpose && gcc -o reda file1.c && ./reda");

        //create streams for the exec methods  in out error
        InputStream passeur = new FileInputStream(In_file);

        OutputStream ktebFih = new FileOutputStream(Out_file);

        OutputStream eroors = new FileOutputStream(error_file);


        // command to execute the thing on the ssh server
        int text2 = shel.exec(" echo '" + clientCode + "' > file2.c && gcc -o reda2 file2.c && ./reda2", passeur, ktebFih, eroors);

//        String errorFound = new Scanner(error_file).useDelimiter("\\Z").next();
        String errorFound="wrong code";

        //check if the files are equals
        boolean issequals = FileUtils.contentEquals(Out_file, compare_file);

        if (issequals) return ("correct solution");
        else return errorFound;
    }



}
