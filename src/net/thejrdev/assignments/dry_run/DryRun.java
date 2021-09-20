package net.thejrdev.assignments.dry_run;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DryRun {

    File file;

    public DryRun(String filename){
        file = new File(filename);
    }

    public String readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while(scanner.hasNextLine()){
            builder.append("I like ").append(scanner.nextLine()).append("\n");
        }
        return builder.toString();
    }



}
