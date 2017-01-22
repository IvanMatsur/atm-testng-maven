package com.epam.atm.testng.utilities;

/**
 * Created by Ivan_Matsur on 1/20/2017.
 */
public class Timeout {

  public static void waitFor(int milliseconds){
    try{
      Thread.sleep(milliseconds);
    } catch (InterruptedException e){
    }
  }
}
