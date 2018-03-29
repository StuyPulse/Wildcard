package org.usfirst.frc.team694.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class readfromCSV {
    ArrayList<double[]>m = new ArrayList<double[]>();
    public readfromCSV(File file){
        Scanner scanner;
        System.out.println("gfd");
        try {
            scanner = new Scanner(file);
            scanner.useDelimiter(",");
            int counter = 0;
            double[] test = new double[3];
            while(scanner.hasNext()){
                if (counter % 3 == 0){
                    m.add(test);
                    test = new double[3];
                    System.out.println("/");
                    for(int i = 0; i < test.length;i++){
                        System.out.print(test[i]);
                    }
                }
                System.out.println("gfd");
                test[counter % 3] = Double.parseDouble(scanner.next());
                counter++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("rip");
            e.printStackTrace();
        }
        
    }
    public void testing(){
        System.out.println(m.get(0));
    }
    
}
