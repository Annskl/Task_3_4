package ru.vsu.cs.uskova_anna_task3;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static final String[] REVERSE_METHODS = {"MyStack", "BuiltInStack", "Recursive"};

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        final String[] method = new String[1];
        LinkedList<Integer> list = new LinkedList<>();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBounds(100, 100, 600, 300);
        jFrame.setResizable(false);

        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Ручной ввод: ");
        JTextField textField = new JTextField(30);
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            try {
                String input = textField.getText();
                String[] elements = input.split("[ ,]+");

                getResult(list, panel, elements, method[0]);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
            }
        });

        JLabel label1 = new JLabel("Ввод из файла: ");
        JTextField textField1 = new JTextField(30);
        JButton button1 = new JButton("OK");
        button1.addActionListener(e -> {
            try {
                String input = textField1.getText();
                Path path = Path.of(input);

                String[] elements = Files.readAllLines(path).get(0).split("[ ,]+");

                getResult(list, panel, elements, method[0]);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Ошибка ввода!");
            }
        });


        JButton[] jButtons = new JButton[3];
        for(int i=0; i<jButtons.length; i++){
            jButtons[i] = new JButton(REVERSE_METHODS[i]);
        }

        jButtons[0].addActionListener(e -> {
            method[0] = REVERSE_METHODS[0];
        });

        jButtons[1].addActionListener(e -> {
            method[0] = REVERSE_METHODS[1];
        });

        jButtons[2].addActionListener(e -> {
            method[0] = REVERSE_METHODS[2];
        });

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(label1);
        panel.add(textField1);
        panel.add(button1);

        for (JButton jButton : jButtons) {
            panel.add(jButton);
        }

        jFrame.add(panel);
        jFrame.setVisible(true);
    }

    private static void getResult(LinkedList<Integer> list, JPanel panel, String[] elements, String method) {
        for(String element: elements){
            list.addLast(Integer.parseInt(element));
        }

        String before = list.toString();

        try{
            switch (method){
                case "MyStack" -> list.reverseWithMyStack();
                case "BuiltInStack" -> list.reverseWithBuiltInStack();
                case "Recursive" -> list.recursiveReverse();
            }
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(panel, "Не выбран метод разворота списка!");
            list.clear();
            return;
        }

        String after = list.toString();

        String answer = "Список до: " + before + "\nСписок после: " + after + "\nМетод: " + method;

        JOptionPane.showMessageDialog(panel, answer);
        list.clear();
    }
}
