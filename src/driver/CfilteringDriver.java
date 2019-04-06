// **********************************************************
// Assignment0:
// UTORID:zhan5263
// UT Student #:1004424517
// Author: zhang ti
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check. 
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

public class CfilteringDriver {

  /*
   * it reads users' mobie rating from a given file
   * calculate and show similarity scores as well as score matrix
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // read number of users and number of movies from file
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());
      
      // create a new Cfiltering object for use
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      br.readLine();

      // fill the user-movie matrix
      
      // set the pointers and initial values
      String row;
      int rowNumber = 0;
      int columnNumber = 0;
      
      // loop through each row
      while ((row = br.readLine()) != null)
      {
        // all the Ratings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        
        //loop through each column
        for (String singleRating : allRatings) 
        {
          // call the method to fill numbers in a row's column one by one
          // change the string representation into integer
          cfObject.populateUserMovieMatrix(rowNumber, columnNumber, Integer.parseInt(singleRating));
          columnNumber++;
        }
        // go to next row
        rowNumber++;
        // reset the pointer for restarting next row
        columnNumber = 0;
      }
      // close the file
      fStream.close();

      // calculate the similarity score between two users
      int j = 0;
      for (int i = 0; i < numberOfUsers; i++) {
        for (j = i; j < numberOfUsers; j++) {
          cfObject.calculateSimilarityScore(i, j, numberOfMovies);
        }
      }
      
      // call the method to print the user-user matrix
      cfObject.printUserUserMatrix(numberOfUsers);
      
      // call the method to print the most similar pair of users
      cfObject.findAndprintMostSimilarPairOfUsers(numberOfUsers);
      
      // call the method to print the most dissimilar pair of users
      cfObject.findAndprintMostDissimilarPairOfUsers(numberOfUsers);


    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
