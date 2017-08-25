/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.IOException;
import jdk.dio.DeviceConfig;
import jdk.dio.DeviceManager;
import jdk.dio.gpio.GPIOPin;
import jdk.dio.gpio.GPIOPinConfig;
import jdk.dio.gpio.PinEvent;
import jdk.dio.gpio.PinListener;

/**
 *
 * @author nabil
 */
public class GPIOPinTest implements PinListener, AutoCloseable {
    // Emulator Port names
  //private static final int LED1_ID = 1;
  //private static final int BUTTON_PIN = 0;
       // Raspberry Pi Pin values
    private final String LED1_ID = "GPIO23";
    private final int BUTTON_PIN = 17;
    
    private GPIOPin led1;
    private GPIOPin button1;
    
        public void start() throws IOException {
        // Open the LED pin (Output)
       led1 = DeviceManager.open(LED1_ID, GPIOPin.class);
      // led1 = DeviceManager.open(LED1_ID);

        // Config file for the button - trigger on a rising edge (from low to high)
        GPIOPinConfig config1 = new GPIOPinConfig(DeviceConfig.DEFAULT,
                BUTTON_PIN,
                GPIOPinConfig.DIR_INPUT_ONLY,
                DeviceConfig.DEFAULT,
                GPIOPinConfig.TRIGGER_RISING_EDGE,
                false);

        // Open the BUTTON pin (Input)
        button1 = DeviceManager.open(config1);

        // Add this class as a pin listener to the buttons
        button1.setInputListener(this);

        // Turn the LED on, then off - this tests the LED
        led1.setValue(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }

        // Start with the LED off (false)
        led1.setValue(false);
    }
        
        
            @Override
    public void close() {
        try {
            if (led1 != null) {
                led1.setValue(false);
                led1.close();
            }
            if (button1 != null) {
                button1.close();
            }
        } catch (IOException ex) {
            System.out.println("Exception closing resources: " + ex);
        }
    }
    
        @Override
    public void valueChanged(PinEvent event) {

        // Simple one button = one LED        
        try {
            System.out.println("setting led1");
            led1.setValue(!led1.getValue()); // Toggle the value of the led
        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
        }
    }

}
