import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ParametryWieloagent extends JFrame {
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
            "Dania miesne",
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
            "Powyżej 1,5h "
    };
    public int cena[] = {
            10,
            25,
            35,
            45,
            55,
            65,
    };
    Random random = new Random();
    List<List<List<String>>> ar = new ArrayList<List<List<String>>>();

    private void serializeToJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File(JSON_FILE), ar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class AgentKlient {
        String[] tab_wart;
        String[] tab_params;

        public AgentKlient(String[] tab_wart, String[] tab_params) {
            this.tab_wart = tab_wart;
            this.tab_params = tab_params;
        }

        public String[] ret1() {
            return tab_wart;
        }

        public String[] ret2() {
            return tab_params;
        }
    }

    public class AgentSeller extends AgentKlient {
        public AgentSeller(String[] tab_wart, String[] tab_params) {
            super(tab_wart, tab_params);
        }

        @Override
        public String[] ret1() {
            return tab_wart;
        }

        @Override
        public String[] ret2() {
            return tab_params;
        }
    }

    public void randomix() {
        ar.clear();
        List<List<String>> tempList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<String> innerList = new ArrayList<>();
            byte[] array = new byte[7]; // length is bounded by 7
            random.nextBytes(array);
            String generatedString = RandomStringUtils.randomAlphabetic(7);
            int index1 = random.nextInt(typ_kuchniii.length);
            int index2 = random.nextInt(r_poislkuuu.length);
            int index3 = random.nextInt(r_potrawyyy.length);
            int index4 = random.nextInt(smak_potrawyyy.length);
            int index5 = random.nextInt(sposob_przyrzadzeniaaa.length);
            int index6 = random.nextInt(czas_przygotowaniaaa.length);
            int index7 = random.nextInt(cena.length);

            innerList.add(generatedString);
            innerList.add(typ_kuchniii[index1]);
            innerList.add(r_poislkuuu[index2]);
            innerList.add(r_potrawyyy[index3]);
            innerList.add(smak_potrawyyy[index4]);
            innerList.add(sposob_przyrzadzeniaaa[index5]);
            innerList.add(czas_przygotowaniaaa[index6]);
            innerList.add(String.valueOf(cena[index7]));
            tempList.add(innerList);
        }

        Random random = new Random();
        for (int i = 0; i < int_agentowk; i++) {
            List<List<String>> innerList = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                int index = random.nextInt(tempList.size());
                List<String> agentInnerList = new ArrayList<>(tempList.get(index));

                int index7 = random.nextInt(cena.length);
                int modifiedCena = (int) (cena[index7] * (0.9 + random.nextDouble() * 0.2));
                agentInnerList.set(7, String.valueOf(modifiedCena));

                innerList.add(agentInnerList);
            }
            ar.add(innerList);
        }
        initialized = true;
        writeFlagFile(true);
        serializeToJsonFile();
    }

    private void initializeData() {
        if (!isFlagFileExists()) {
            randomix();
        }try {
            // Odczytaj zawartość pliku "tablice.json"
            ObjectMapper mapper = new ObjectMapper();
            List<List<List<String>>> jsonData = mapper.readValue(new File(JSON_FILE), new TypeReference<List<List<List<String>>>>() {});

            // Wyświetl zawartość pliku w konsoli
            System.out.println("Zawartość pliku tablice.json:");
            for (List<List<String>> innerList : jsonData) {
                for (List<String> agentInnerList : innerList) {
                    System.out.println(agentInnerList);
                }
                System.out.println();
            }

            // Przypisz odczytane dane do zmiennej "ar"
            ar = jsonData;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isFlagFileExists() {
        File file = new File(FLAG_FILE);
        return file.exists();
    }

    private void writeFlagFile(boolean value) {
        try {
            FileWriter writer = new FileWriter(FLAG_FILE);
            writer.write(String.valueOf(value));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean readFlagFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FLAG_FILE));
            String line = reader.readLine();
            reader.close();
            return Boolean.parseBoolean(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ParametryWieloagent(int int_agentowk) {

        this.int_agentowk = int_agentowk;
        initialized = readFlagFile();
        if (!initialized) {
            randomix();
        }
        initializeData();
        setContentPane(FormParams);
        setSize(700, 700);
        setTitle("System polecający dania");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typ1 = comboBox1.getSelectedItem().toString();
                rodzaj1 = comboBox2.getSelectedItem().toString();
                potrawa1 = comboBox3.getSelectedItem().toString();
                smak1 = comboBox4.getSelectedItem().toString();
                sposob1 = comboBox5.getSelectedItem().toString();
                czas1 = comboBox6.getSelectedItem().toString();
                String tab_wart[] = {typ1, rodzaj1, potrawa1, smak1, sposob1, czas1};
                System.out.println(Arrays.toString(tab_wart));

                prio_typ1 = comboBox7.getSelectedItem().toString();
                prio_rodzaj1 = comboBox8.getSelectedItem().toString();
                prio_potrawa1 = comboBox9.getSelectedItem().toString();
                prio_smak1 = comboBox10.getSelectedItem().toString();
                prio_sposob1 = comboBox11.getSelectedItem().toString();
                prio_czas1 = comboBox12.getSelectedItem().toString();
                String tab_prio[] = {prio_typ1, prio_rodzaj1, prio_potrawa1, prio_smak1, prio_sposob1, prio_czas1};
                System.out.println(Arrays.toString(tab_prio));

                AgentSeller classObj = new AgentSeller(tab_wart, tab_prio);

                ar.forEach(System.out::println);
                Wyniki.setText("");
                Wyniki.setText("" + ar);
            }
        });
    }

    public static void main(String[] args) {
        int int_agentowk = readInitializationValue();

        if (int_agentowk == -1) {
            String input = JOptionPane.showInputDialog("Podaj wartość int_agentowk:");
            int_agentowk = Integer.parseInt(input);
            saveInitializationValue(int_agentowk);
        }

        ParametryWieloagent FormParams = new ParametryWieloagent(int_agentowk);
    }

    private static int readInitializationValue() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(INITIALIZATION_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(NUM_AGENTS_KEY)) {
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        return Integer.parseInt(parts[1]);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static void saveInitializationValue(int value) {
        try {
            FileWriter writer = new FileWriter(INITIALIZATION_FILE);
            writer.write(NUM_AGENTS_KEY + "=" + value);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}