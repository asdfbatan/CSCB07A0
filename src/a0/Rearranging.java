package a0;
// **********************************************************
// Assignment0:
// UTORID:zhan5263
// UT Student #:1004424517
// Author:zhangti
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

/*
 * This class sorts the ints in the array such as 0 in the middle, negative on the left side,
 * positive on the right side.
 */
public class Rearranging {
  /*
   * This method takes in as input an array of int and returns back nothing (void).
   */
  public static void rearranging(int[] items) {
    // set the pointers and position values
    int start_pointer = 0;
    int mid = 0;
    int end_pointer = items.length - 1;
    // search from the end to the start
    while (mid <= end_pointer) {
      // if negative then put forward
      if (items[mid] < 0) {
        swap(start_pointer++, mid++, items);
      }
      // positive or zero
      else {
        // if zero then just move the last zero position
        if (items[mid] == 0) {
          mid++;
        }
        // if positive then put backwards
        else {
          swap(mid, end_pointer--, items);
        }
      }
    }



  }

  /*
   * This method takes in as input two ints and an array of ints. The int i and int j are the index
   * i and index j in the array items. This method swap the value at the index i in items with the
   * value at the index j.
   */
  private static void swap(int i, int j, int[] items) {
    // create a variable temp to store value
    int temp = items[i];
    // change the values
    items[i] = items[j];
    items[j] = temp;
  }

  public static void main(String[] args) {
    /*
     * You can modify the main function in any way you like. We will not mark your main function.
     */
    int[] items = {7, -5, 0, -3, 0, 5, 8, 0, -2, 11, -9};
    /*
     * printing the values in the items before calling the method rearranging
     */
    for (int item : items) {
      System.out.println(item);
    }
    System.out.println("\n");

    // calling the rearranging method
    Rearranging.rearranging(items);
    /*
     * printing the values in the items after calling the method rearranging
     */
    for (int item : items) {
      System.out.println(item);
    }
  }
}
