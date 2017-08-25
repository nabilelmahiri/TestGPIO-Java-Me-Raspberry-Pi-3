/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.IOException;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author nabil
 */
public class GPIOIMlet extends MIDlet {
    
    private GPIOPinTest pinTest;
    
    @Override
    public void startApp() {
        pinTest = new GPIOPinTest();
        try {
            pinTest.start();
        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
            notifyDestroyed();
        }
    }
    
      @Override
  public void destroyApp(boolean unconditional) {
      if (pinTest != null) {
          pinTest.close();
      }
  }
}
