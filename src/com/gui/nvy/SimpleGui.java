package com.gui.nvy;

import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class SimpleGui extends JFrame {
    private final JTextField input = new JTextField("", 5);

    public SimpleGui() {
        super("Шар с ответами");
        JLabel label = new JLabel("Вопрос:");
        JButton button = new JButton("Задать вопрос");

        Container container = this.getContentPane();

        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        button.addActionListener(new ButtonEventListener());
        button.setEnabled(false);
        input.setColumns(10);
        container.add(label);
        container.add(input);
        container.add(button);

        DocumentListener documentListener = new DocumentListener() {
            public void check() {
                //
                button.setEnabled(input.getText().trim().length() != 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                //System.out.println("insertUpdate");
                check();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //System.out.println("removeUpdate");
                check();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //System.out.println("changedUpdate");
                check();
            }
        };
        input.getDocument().addDocumentListener(documentListener);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "";
            String[] texts = {"Да", "Нет", "Возможно", "Будущее неясно",
                    "А ты как думаешь", "Ни в коем случае", "Хорошие перспективы",
                    "Бесспорно", "Весьма сомнительно", "Перспективы не очень хорошие"};

            message += "Твой вопрос: " + input.getText() + "\n";
            Random random = new Random();
            int numInt = random.nextInt(10);
            message += "Мой ответ: ";
            message += texts[numInt];
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SimpleGui app = new SimpleGui();
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(350, 100);
        app.setVisible(true);
    }
}