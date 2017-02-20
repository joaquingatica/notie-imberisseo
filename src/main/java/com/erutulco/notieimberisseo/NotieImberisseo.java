package com.erutulco.notieimberisseo;

import com.erutulco.notieimberisseo.config.Config;

import java.awt.EventQueue;

public class NotieImberisseo {

  /**
   * Main function.
   * @param args CLI arguments
   */
  public static void main(String[] args) {
    Config.initialize();
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UserInterfaceController uic = UserInterfaceController.getInstance();
          uic.initializeWindow(false);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
