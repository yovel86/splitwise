package com.projects.splitwise;

import com.projects.splitwise.commands.Command;
import com.projects.splitwise.commands.CommandRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Awaiting input...");
            String input = in.nextLine();
            try {
                Command command = CommandRegistry.getInstance().getCommand(input);
                command.execute(input);
            } catch (Exception e) {
                System.out.println("Please enter a valid command, Exception: " + e.getMessage());
            }
        }
    }

}
