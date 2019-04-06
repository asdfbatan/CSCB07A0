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
package a0;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cfiltering {
  // 2d matrix for user-movie
  private int userMovieMatrix[][];
  // 2d matrix for user-user
  private float userUserMatrix[][];


  // constructor
  public Cfiltering() {
    // matrix with dimension 1*1
    userMovieMatrix = new int[1][1];
    userUserMatrix = new float[1][1];
  }

  
  // create matrix with size from given values
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    userUserMatrix = new float[numberOfUsers][numberOfUsers];

  }


  // fill in the matrix
  public void populateUserMovieMatrix(int rowNumber, int columnNumber, int ratingValue) {
    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

 
  // calculate the similarity of given users
  public void calculateSimilarityScore(int user1, int user2, int numberOfMovies) {
    double distance = 0;
    // calculate the distance
    // here use the Euclidean Distance Formula as given
    for (int i = 0; i < numberOfMovies; i++) {
      distance = distance + Math.pow(((userMovieMatrix[user1][i] - userMovieMatrix[user2][i])), 2);
    }
    // calculate the similarity score
    double similarityScore = (1 / (1 + Math.sqrt(distance)));
    // Store similarity score in both upper and lower triangle in Matrix
    userUserMatrix[user1][user2] = (float) similarityScore;
    userUserMatrix[user2][user1] = (float) similarityScore;
  }


  // print the user-user matrix
  public void printUserUserMatrix(int numberOfUsers) {
    // below for format requirements
    // add this line to change to 4 decimal places as asked
    DecimalFormat decimal = new DecimalFormat("0.0000");

    System.out.println();
    System.out.println();

    System.out.println("userUserMatrix is:");
    List<String> userScore = new ArrayList<String>();

    // print out a list array of the matrix
    // loop through the rows
    for (int i = 0; i < numberOfUsers; i++) {
      // loop through the columns
      for (int j = 0; j < numberOfUsers; j++) {
        userScore.add(decimal.format(userUserMatrix[i][j]));
      }
      System.out.println(userScore);
      // reset for use
      userScore.clear();
    }
  }

 
  // print the most similar pair of users and their similarity score
  public void findAndprintMostSimilarPairOfUsers(int numberOfUsers) {
    // below for format requirements
    // add this line to change to 4 decimal places as asked
    DecimalFormat decimal = new DecimalFormat("0.0000");
    System.out.println();
    System.out.println();

    float maxScore = 0;
    List<String> similarUser = new ArrayList<String>();

    // loop through matrix and finds users with highest similarity score
    // for each row
    for (int i = 0; i < (numberOfUsers - 1); i++) {
      // for each column
      for (int j = i + 1; j < (numberOfUsers); j++) {
        if (userUserMatrix[i][j] > maxScore) {
          // if found more similar one, replace it and reset the associations
          maxScore = userUserMatrix[i][j];
          similarUser.clear();
          similarUser.add("User" + (i + 1) + " and User" + (j + 1));
        } else if (userUserMatrix[i][j] == maxScore) {
          // find anther similar one
          similarUser.add("User" + (i + 1) + " and User" + (j + 1));
        }
      }
    }

    System.out.println("The most similar pairs of users from above userUserMatrix are:");
    // set the pointers
    int num = similarUser.size();
    int num2 = 0;
    // match each user together without duplication
    while (num != 1) {
      System.out.println(similarUser.get(num2) + ", ");
      num2++;
      num--;
    }
    System.out.println(similarUser.get(num2));
    System.out.println("with similarity score of " + decimal.format(maxScore));
  }

 
  public void findAndprintMostDissimilarPairOfUsers(int numberOfUsers) {
    // below for format requirements
    // add this line to change to 4 decimal places as asked
    DecimalFormat decimal = new DecimalFormat("0.0000");
    System.out.println();
    System.out.println();

    float minScore = 1;
    List<String> dissimilarUser = new ArrayList<String>();

    // loop through matrix and finds users with lowest similarity score
    // for each row
    for (int x = 0; x < (numberOfUsers - 1); x++) {
      // for each column
      for (int y = x + 1; y < (numberOfUsers); y++) {
        if (userUserMatrix[x][y] < minScore) {
          // if found more similar one, replace it and reset the associations
          minScore = userUserMatrix[x][y];
          dissimilarUser.clear();
          dissimilarUser.add("User" + (x + 1) + " and User" + (y + 1));
        } else if (userUserMatrix[x][y] == minScore) {
          // find anther similar one
          dissimilarUser.add("User" + (x + 1) + " and User" + (y + 1));
        }
      }
    }

    System.out.println("The most dissimilar pairs of users from above " + "userUserMatrix are:");
    // set the pointers
    int num = dissimilarUser.size();
    int num2 = 0;
    // match each user together without duplication
    while (num != 1) {
      System.out.println(dissimilarUser.get(num2) + ", ");
      num2++;
      num--;
    }
    System.out.println(dissimilarUser.get(num2));
    System.out.println("with similarity score of " + decimal.format(minScore));
  }

}
