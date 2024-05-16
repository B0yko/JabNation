package JabNation.Utils;

import java.io.*;
import java.util.*;

public class BoxerSorter {

    public static void main(String[] args) {
        String filePath = "boxers.txt";
        sortBoxers(filePath);
        System.out.println("Boxers sorted successfully!");
    }

    public static void sortBoxers(String filePath) {
        List<String> boxers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                boxers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        quicksort(boxers, 0, boxers.size() - 1);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String boxer : boxers) {
                bw.write(boxer);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void quicksort(List<String> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
    }

    public static int partition(List<String> arr, int low, int high) {
        String pivot = arr.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(arr, i, j);
            }
        }

        Collections.swap(arr, i + 1, high);
        return i + 1;
    }
}
