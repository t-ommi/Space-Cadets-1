import java.util.Scanner;
import java.net.*;
import java.net.MalformedURLException;
import java.io.*;


public class Search {

  public static void main(String[] args) {

    Toolbox myToolbox = new Toolbox(); //Adding the toolbox into the class
    System.out.println("Welcome! to the People Search");
    System.out.println("Input the email code");

    Scanner scanner = new Scanner(System.in); //Scanner Object called scanner is created
    String emailID = scanner.nextLine(); //nextLine method is used to read Strings from user input

    String query = "https://www.ecs.soton.ac.uk/people/";

    try { //forces program to execute the block of code
      URL url = new URL(query + emailID); //Concatenation of url and emailID
      BufferedReader content = new BufferedReader(new InputStreamReader(url.openStream()));
      //BufferedReader Object 'content' -> hold the URL content in Buffer from InputStreamReader
      //InputStreamReader -> reads the content, one character at a time
      //url.openStream() -> Method which opens the URL (url)
      String inputLine; //Decleration of the current line to be read
      boolean found = false;
      while (((inputLine = content.readLine()) != null) & (!found)) {
        //'While' loop to keep going as long as there is a line to be read in URL content and the line is not found
        if (inputLine.indexOf("title") > 0) { //If the current line has the word "title"
          int start = (inputLine.indexOf("title")) + 16; //Assigns the start of the name
          int end = (inputLine.length() - 4); //Assigns the end of the name
          String name = inputLine.substring(start, end); //Assigns the 'name' to a variable
          if (name.equals("lectronics and Computer Science | University of Southampton</ti")) {
            System.out.println("Could not find"); //Prints an error if 'name' matches condition
          } else {
            System.out.println(name); //Prints the name if not
          }
          found = true; //Sets found to true to end while loop
        }
      }

      content.close();
    } catch (MalformedURLException e) { //code is executed if error occurs in try{}
      e.printStackTrace();
      System.err.println("Unable to find URL");
    } catch (IOException e) {
      System.err.println("There was a problem with reading");
    }


  }
}
