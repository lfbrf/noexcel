/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.jobs;

import java.util.Timer;

/**
 *
 * @author Luiz
 */
public class Main {

    public static void main(String[] args) {

        Timer t = new Timer();
        MyTask mTask = new MyTask();
        // This task is scheduled to run every 10 seconds

        t.scheduleAtFixedRate(mTask, 0, 12000000);

    }

}
