package milestone1;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class MileStone_One {
    public static void headers(String[] headers) {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int k = 0; k < headers.length; k++) {
            System.out.print(headers[k]);
            System.out.print("\t");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public static void printColumnData(String[] headers, String[] dataToArray) {
        for (int k = 0; k < headers.length; k++) {
            System.out.print(dataToArray[k]);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static Date convertDate(String dateString) {
        System.out.println(dateString);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yy");  
        try {
            // String formattedDate = formatter.format(dateString);
            LocalDate date = LocalDate.parse("2016-07-05");
            System.out.print(date);
            // return formatter.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  
    }

//    public static boolean isWithinRange(String dateToBeTested, String startDate, String endDate) {
//        return convertDate(dateToBeTested).after(convertDate(startDate)) && convertDate(dateToBeTested).before(convertDate(endDate));
//    }

    

    public static void resultantData(List<String> dataSet, String filterType, int numberOfRecords, String[] headers, int headerIndex, String countryType) {
        int numberOfRecordsFound = 0;
        Optional<String> countryValue = Optional.ofNullable(countryType);
        for (int j = 1; j < dataSet.size(); j++) {
            String[] dataToArray = dataSet.get(j).split(",");
            for (int i = 0; i < dataToArray.length; i++) {
                if(i==headerIndex && dataToArray[i].toLowerCase().indexOf(filterType.toLowerCase()) > -1) {
                    if(countryValue.isPresent()) {
                        if(dataToArray[5].indexOf(countryType.toLowerCase()) > -1) {
                            printColumnData(headers, dataToArray);
                            numberOfRecordsFound = numberOfRecordsFound + 1;
                            j+=1;
                            break;
                        } 
                    }else {
                        printColumnData(headers, dataToArray);
                        numberOfRecordsFound = numberOfRecordsFound + 1;
                        j+=1;
                        break;
                    }
                }
            }
            if(numberOfRecordsFound == numberOfRecords) {
                break;
            }
            if(j == dataSet.size() - 1) {
                if(numberOfRecordsFound == 0) {
                    System.out.println("No Record Found");
                }
            }
        }
    }


    public static void  main(String[] args) throws IOException {
        try {
             // starting time
            long startTime = System.currentTimeMillis();
            String filePath = "/Users/iparashar/Downloads/netflix_titles.csv";
            List<String> dataSet = Files.readAllLines(Paths.get(filePath));
            Scanner userInput = new Scanner(System.in);
            System.out.println("-----------------------------------------------------Let's Binge Watch The Movies------------------------------------------------------");
            System.out.println("Select the action type you want to perform");
            System.out.println("Select 1 for : List first n records Based on type");
            System.out.println("Select 2 for : List first n records Based on genre");
            System.out.println("Select 3 for : Filter first n records based on type and country");
            int actionType = userInput.nextInt();
            System.out.println("Enter the number of records you want to see ?");
            int numberOfRecords = userInput.nextInt();
            String[] headers = dataSet.get(0).split(",");
//            System.out.println("Enter the start date from which you want to see the data ? (in : dd-mm-yyyy)");
//            userInput.nextLine();
//            String startDate = userInput.nextLine();
//            System.out.println("Enter the end date till which you want to see the data ? (in : dd-mm-yyyy)");
//            String endDate = userInput.nextLine();
            switch(actionType) {
                case 1: 
                    System.out.println("Enter the type you want to see ?");
                    String filterType = userInput.nextLine();
                    headers(headers);
                    resultantData(dataSet, filterType, numberOfRecords, headers, 1, null);
                break;
                case 2: 
                    System.out.println("Enter the genre you want to see ?");
                    filterType = userInput.nextLine();
                    headers(headers);
                    resultantData(dataSet, filterType, numberOfRecords, headers, 10, null);
                break;
                case 3: 
                    System.out.println("Enter the type you want to see ?");
                    filterType = userInput.nextLine();
                    System.out.println("Enter the country you want to see ?");
                    String countryType = userInput.nextLine();
                    headers(headers);
                    resultantData(dataSet, filterType, numberOfRecords, headers, 1, countryType);
                break;
                default:
                break;
            }
              // closing scanner
              userInput.close();
              long endTime = System.currentTimeMillis();
              System.out.println();
              System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
              System.out.println("Time Taken To Execute Query " + (endTime - startTime) + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
}
