import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ParametryWieloagent extends JFrame {
    private JButton startButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JComboBox comboBox9;
    private JComboBox comboBox10;
    private JComboBox comboBox11;
    private JComboBox comboBox12;
    private JLabel Typkuchni;
    private JLabel RodzajPosiłku;
    private JLabel RodzajPotrawy;
    private JLabel SmakPotrawy;
    private JLabel SposóbPrzyrządzania;
    private JLabel CzasPrzygotowania;
    private JPanel FormParams;
    private JFormattedTextField LagentowK;
    private JFormattedTextField LagentowS;
    private JTextPane Wyniki;
    private JButton zapiszWyborButton;
    private JTextField textField1;


    public String typ1, rodzaj1, potrawa1, smak1, sposob1, czas1;

    public int int_agentowk, int_agentows;
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
                "Gotowanie","Smażenie","Pieczenie","Grillowanie","Bez obróbki termicznej"
        };
        public String czas_przygotowaniaaa[] = {
                "Poniżej 10 min",
                "10-15 min",
                "15-30 min",
                "30-1h",
                "1h-1,5h",
                "Powyżej 1,5h "
        };
    Random random = new Random();
    List<List<String>> ar = new ArrayList<List<String>>();





    public class AgentKlient {

        String[] tab_wart;

        String[] tab_params;

        public AgentKlient(String[] tab_wart, String[] tab_params) {
            this.tab_wart = tab_wart;
            this.tab_params = tab_params;
        }

        public String[] ret1(){
            return tab_wart;
        }
        public String[] ret2(){
            return tab_params;
        }
    }

    public class AgentSeller  extends AgentKlient{
        public AgentSeller(String[] tab_wart, String[] tab_params) {
                super(tab_wart,tab_params);
        }

        @Override
        public String[] ret1() {
            return tab_wart;
        }

        @Override
        public String[] ret2() {
            return tab_params;
        }

        public void randomix(){
            ar.clear();
            for(int i = 0; i <= int_agentowk; i++) {
                List<String> tempList = new ArrayList<String>();
                ar.add(tempList);
                byte[] array = new byte[7]; // length is bounded by 7
                random.nextBytes(array);
                String generatedString = new String(array, Charset.forName("UTF-8"));
                int index1 = random.nextInt(typ_kuchniii.length);
                int index2 = random.nextInt(r_poislkuuu.length);
                int index3 = random.nextInt(r_potrawyyy.length);
                int index4 = random.nextInt(smak_potrawyyy.length);
                int index5 = random.nextInt(sposob_przyrzadzeniaaa.length);
                int index6 = random.nextInt(czas_przygotowaniaaa.length);

                tempList.add(generatedString);
                tempList.add(typ_kuchniii[index1]);
                tempList.add(r_poislkuuu[index2]);
                tempList.add(r_potrawyyy[index3]);
                tempList.add(smak_potrawyyy[index4]);
                tempList.add(sposob_przyrzadzeniaaa[index5]);
                tempList.add(czas_przygotowaniaaa[index6]);

            }
        }
    }

    public ParametryWieloagent() {
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
                classObj.randomix();
                AgentKlient kliencik = new AgentKlient(tab_wart, tab_prio);


                int_agentows = Integer.parseInt(LagentowS.getText());
                System.out.println(int_agentows);

                int_agentowk = Integer.parseInt(LagentowK.getText());
                System.out.println(int_agentowk);

                ar.forEach(System.out::println);
                Wyniki.setText("");
                Wyniki.setText("" + ar);

            }
        });
    }

    public static void main(String[] args) {
        ParametryWieloagent FormParams = new ParametryWieloagent();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
