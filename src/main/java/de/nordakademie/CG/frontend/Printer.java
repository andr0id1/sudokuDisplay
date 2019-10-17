package de.nordakademie.CG.frontend;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by andreclausen on 16.10.19.
 */
public class Printer {

    Scanner scanner = new Scanner(System.in);
    ArrayList<String> sudolus = new ArrayList<>();

    public void menuLoop() {
        int selection;

        do {
            this.printDialog();
            selection = this.scanner.nextInt();

            if (selection == 0) {
                break;
            } else if (selection == 1) {
                readSudoku();
            } else if (selection > 1 && selection <= this.sudolus.size() + 1) {
                printAll(sudolus.get(selection - 2));
            } else {
                System.out.println("ungültig");
            }






        } while (true);

    }

    private void printDialog() {
        System.out.println("(0) Stop");
        System.out.println("(1) Load file");

        int i = 2;
        for (String sudoku : this.sudolus) {
            System.out.println("(" + i++ + ") Print sudoku nr. " + sudoku.substring(0,4));
        }
    }


    public void printLine(){
        System.out.print(ConsoleColors.RED + "+");
        for(int i = 0; i < 3 ; i++){
            System.out.print(ConsoleColors.GREEN + "---+---+---" +
                    ConsoleColors.RED + "+");
        }
        System.out.println(ConsoleColors.RESET);
    }

    public void printRedLine(){
        System.out.println(ConsoleColors.RED + "+---+---+---+---+---+---+---+---+---+" + ConsoleColors.RESET);
    }

    private void prindNumberLine(String line){
        String[] numbers = line.split("");
        System.out.print(ConsoleColors.RED +"|" + ConsoleColors.RESET);
        for (int i = 0; i < 9; i++){
            if (numbers[i].equals("0")){
                numbers[i] = " ";
            }
            System.out.print(ConsoleColors.GREEN +" "+ numbers[i]);
            if((i+1)%3 == 0){
                System.out.print(ConsoleColors.RED + " |");
            }else {
                System.out.print(ConsoleColors.GREEN + " |");
            }
        }
        System.out.println(ConsoleColors.RESET);
    }

    public void printAll(String sudoku){
        System.out.println("Sudoku Nr.: " + sudoku.substring(0,4));
        printRedLine();
        for( int i = 0; i < 9; i++){
            prindNumberLine(sudoku.substring(4 + (9*i),13 + (9*i)));
            if ((i+1)%3 == 0){
                printRedLine();
            }else{
                printLine();
            }
        }

    }

    public  void readSudoku(){


        System.out.println("Dateiname (absoluter Pfad):");
        System.out.println("Press enter for default file.");
        scanner.nextLine();
        String fileName = scanner.nextLine();
        if(fileName.equals("")){
            fileName = "/Users/andreclausen/IdeaProjects/sudokuDisplay/src/main/resources/sudokus.txt";
        }

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String line;
            int lineNumber = 0;
            while((line = bufferedReader.readLine()) != null){
                lineNumber++;
                if(line.length()%85 == 0){
                    while (line.length() > 0){
                        sudolus.add(line.substring(0,85));
                        line = line.substring(85, line.length());
                    }
                }else{
                    System.out.println("Line invalid, wrong character count in Line " + lineNumber);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
