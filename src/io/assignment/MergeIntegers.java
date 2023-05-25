package io.assignment;
import java.io.*;

public class MergeIntegers {

        public static void main(String[] args) {
            String input1FileName = "C:\\Users\\admin\\IdeaProjects\\Java Input Output\\src\\io\\assignment\\input1.txt";
            String input2FileName = "C:\\Users\\admin\\IdeaProjects\\Java Input Output\\src\\io\\assignment\\input2.txt";
            String mergedFileName = "merged.txt";
            String commonFileName = "common.txt";
                
            // once file is ran the common.txt and merged.txt file will be created
                
            try {
                // Reads integers from input1.txt and input2.txt
                int[] input1Integers = readIntegersFromFile(input1FileName);
                int[] input2Integers = readIntegersFromFile(input2FileName);

                // Merges the contents of input1.txt and input2.txt
                int[] mergedIntegers = mergeArrays(input1Integers, input2Integers);

                // Writes merged integers to merged.txt
                writeIntegersToFile(mergedIntegers, mergedFileName);

                // Finds integers present in both input1.txt and input2.txt
                int[] commonIntegers = findCommonIntegers(input1Integers, input2Integers);

                // Writes common integers to common.txt
                writeIntegersToFile(commonIntegers, commonFileName);

                System.out.println("Merging and common integers process completed successfully.");
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("An error occurred while reading or writing files: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer format in the input files: " + e.getMessage());
            }
        }

        private static int[] readIntegersFromFile(String fileName) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                int[] integers = new int[0];

                while ((line = reader.readLine()) != null) {
                    // Convert the line to an integer and append it to the array
                    int number = Integer.parseInt(line);
                    integers = appendToArray(integers, number);
                }

                return integers;
            }
        }

        private static int[] mergeArrays(int[] array1, int[] array2) {
            int[] mergedArray = new int[array1.length + array2.length];
            System.arraycopy(array1, 0, mergedArray, 0, array1.length);
            System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);
            return mergedArray;
        }

        private static int[] findCommonIntegers(int[] array1, int[] array2) {
            int[] commonArray = new int[Math.min(array1.length, array2.length)];
            int index = 0;

            for (int num1 : array1) {
                for (int num2 : array2) {
                    if (num1 == num2) {
                        commonArray[index] = num1;
                        index++;
                        break; // Found a common integer, moved to the next number in array1
                    }
                }
            }

            // Trims the commonArray to remove unused elements
            return trimArray(commonArray, index);
        }

        private static void writeIntegersToFile(int[] integers, String fileName) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (int number : integers) {
                    writer.write(String.valueOf(number));
                    writer.newLine();
                }
            }
        }

        private static int[] appendToArray(int[] array, int number) {
            int[] newArray = new int[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[array.length] = number;
            return newArray;
        }

        private static int[] trimArray(int[] array, int length) {
            int[] trimmedArray = new int[length];
            System.arraycopy(array, 0, trimmedArray, 0, length);
            return trimmedArray;
        }
}
