
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter git command: ");

        String str1 = scanner.nextLine();

        String closestGitCommand = "";
        int minDistance = Integer.MAX_VALUE;

        String filePath = "C:\\learnings\\Levenshtein_distance\\git_commands.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String command;
            while ((command = br.readLine()) != null) {
                int distance = levenshteinDistance(str1, command);
                if(distance < minDistance){
                    minDistance = distance;
                    closestGitCommand = command;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("Did you mean to type \"" + closestGitCommand + "\"?");
    }

    public static int levenshteinDistance(String str1, String str2) {
        int[][] matrixArray = new int[str1.length() + 1][str2.length() + 1];

        int cost = 0;

        for(int i=0; i<=str1.length(); i++) {
            for(int j=0; j<=str2.length(); j++){
                if(i==0 || j==0){
                    cost = i + j;
                }
                else {
                    if(str1.charAt(i-1) == str2.charAt(j-1)){
                        cost = 0;
                    }else{
                    cost = 1;
                    }
                    cost = Math.min(matrixArray[i][j-1] + 1, Math.min(matrixArray[i-1][j] + 1, matrixArray[i-1][j-1] + cost));
                }
                matrixArray[i][j] = cost;
            }
        
        }
        return matrixArray[str1.length()][str2.length()];
    }

}