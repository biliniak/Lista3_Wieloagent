import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.RandomStringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main extends JFrame {
    private static SwingUtilities EventQueue;
    private JButton startButton;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private JComboBox<String> comboBox4;
    private JComboBox<String> comboBox5;
    private JComboBox<String> comboBox6;
    private JComboBox<String> comboBox7;
    private JComboBox<String> comboBox8;
    private JComboBox<String> comboBox9;
    private JComboBox<String> comboBox10;
    private JComboBox<String> comboBox11;
    private JComboBox<String> comboBox12;
    private JLabel Typkuchni;
    private JLabel RodzajPosiłku;
    private JLabel RodzajPotrawy;
    private JLabel SmakPotrawy;
    private JLabel SposóbPrzyrządzania;
    private JLabel CzasPrzygotowania;
    private JPanel FormParams;
    private JFormattedTextField LagentowK;
    private JTextPane Wyniki;
    private JButton zapiszWyborButton;
    private JTextField textField1;

    private static final String INITIALIZATION_FILE = "ilosc_agentow.txt";
    private static final String NUM_AGENTS_KEY = "numAgents";
    private static final String JSON_FILE = "tablice.json";
    private static final String FLAG_FILE = "flaga.txt";
    private boolean initialized = false;

    public String typ1, rodzaj1, potrawa1, smak1, sposob1, czas1;
    public int int_agentowk;
    public String prio_typ1, prio_rodzaj1, prio_potrawa1, prio_smak1, prio_sposob1, prio_czas1;

    public String typ_kuchniii[] = {"Kuchnia Włoska",
            "Kuchnia Polska",
            "Kuchnia Indyjska",
            "Kuchnia Wietnamska",
            "Meksykańska"};

    public String r_poislkuuu[] = {"Śniadanie",
            "Lunch",
            "Obiad",
            "Kolacja",
            "Dodatek",
            "Deser"};
    public String r_potrawyyy[] = {
            "Sałatka",
            "Zupa",
            "Dania mięsne",
            "Makaron",
            "Pizza",
            "Deser"};

    public String smak_potrawyyy[] = {
            "Słodki",
            "Słony",
            "Kwaśny",
            "Gorzki",
            "Pikantny",
            "Słodko-kwaśny",
            "Bardzo pikantny"};

    public String sposob_przyrzadzeniaaa[] = {
            "Gotowanie", "Smażenie", "Pieczenie", "Grillowanie", "Bez obróbki termicznej"
    };
    public String czas_przygotowaniaaa[] = {
            "Poniżej 10 min",
            "10-15 min",
            "15-30 min",
            "30-1h",
            "1h-1,5h",
            "Powyżej 1,5h"
    };


    public Main() {
        super("Parametry wieloagentowe");
        this.setContentPane(this.FormParams);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        LagentowK.setText("Liczba agentów (k):");
        comboBox1.setModel(new DefaultComboBoxModel<>(typ_kuchniii));
        comboBox2.setModel(new DefaultComboBoxModel<>(r_poislkuuu));
        comboBox3.setModel(new DefaultComboBoxModel<>(r_potrawyyy));
        comboBox4.setModel(new DefaultComboBoxModel<>(smak_potrawyyy));
        comboBox5.setModel(new DefaultComboBoxModel<>(sposob_przyrzadzeniaaa));
        comboBox6.setModel(new DefaultComboBoxModel<>(czas_przygotowaniaaa));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!initialized) {
                    initialize();
                    initialized = true;
                }
                String typ = comboBox1.getSelectedItem().toString();
                String rodzaj = comboBox2.getSelectedItem().toString();
                String potrawa = comboBox3.getSelectedItem().toString();
                String smak = comboBox4.getSelectedItem().toString();
                String sposob = comboBox5.getSelectedItem().toString();
                String czas = comboBox6.getSelectedItem().toString();

                typ1 = typ;
                rodzaj1 = rodzaj;
                potrawa1 = potrawa;
                smak1 = smak;
                sposob1 = sposob;
                czas1 = czas;

                StringBuilder wynik = new StringBuilder();
                wynik.append("Wybrane parametry:").append("\n");
                wynik.append("Typ kuchni: ").append(typ1).append("\n");
                wynik.append("Rodzaj posiłku: ").append(rodzaj1).append("\n");
                wynik.append("Rodzaj potrawy: ").append(potrawa1).append("\n");
                wynik.append("Smak potrawy: ").append(smak1).append("\n");
                wynik.append("Sposób przyrządzania: ").append(sposob1).append("\n");
                wynik.append("Czas przygotowania: ").append(czas1).append("\n");

                Wyniki.setText(wynik.toString());
            }
        });

        zapiszWyborButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int_agentowk = Integer.parseInt(LagentowK.getText());
                System.out.println(int_agentowk);

                String jsonString = generateJsonString();
                writeJsonToFile(jsonString, JSON_FILE);

                writeFlagFile();

                JOptionPane.showMessageDialog(null, "Wybór zapisany pomyślnie!");
            }
        });
    }

    private void initialize() {
        String numAgentsString = readInitializationFile(INITIALIZATION_FILE);
        if (numAgentsString != null) {
            LagentowK.setText(numAgentsString);
            int_agentowk = Integer.parseInt(numAgentsString);
        }
    }

    private String readInitializationFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeJsonToFile(String jsonString, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFlagFile() {
        try (FileWriter writer = new FileWriter(FLAG_FILE)) {
            writer.write(RandomStringUtils.randomAlphabetic(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateJsonString() {
        List<String> paramsList = new ArrayList<>(Arrays.asList(typ1, rodzaj1, potrawa1, smak1, sposob1, czas1));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(paramsList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
