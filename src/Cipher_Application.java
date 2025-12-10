import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

public class cipher_application extends javax.swing.JFrame{

    public static void main(String[] args){

        // Create the main frame
        JFrame frame = new JFrame("File Cipher Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setResizable(false);
        
        // Use a layered pane to allow overlaying the buttons on the background
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));
        frame.setContentPane(layeredPane);
        
        // Load the background image
        // System.out.println(System.getProperty("user.dir"));
        ImageIcon img = new ImageIcon("../cipher_application/assets/background.png");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 700, 500);
        layeredPane.add(background, Integer.valueOf(1));

        // Title label
        JLabel titleLabel = new JLabel("FILE  CIPHER  APPLICATION");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(lasticaFont(30));
        titleLabel.setForeground(Color.WHITE);
        // Position the label
        titleLabel.setBounds(57, 27, 700, 100);
        layeredPane.add(titleLabel, Integer.valueOf(2));

        // Title label
        JLabel titleLabel1 = new JLabel("SELECT BUTTONS");
        titleLabel1.setForeground(Color.black);
        titleLabel1.setFont(lasticaFont(13));
        // Position the label
        titleLabel1.setBounds(77, 180, 200, 30);
        layeredPane.add(titleLabel1, Integer.valueOf(2));
        
        // Title label
        JLabel titleLabel2 = new JLabel("MENU");
        titleLabel2.setForeground(Color.black);
        titleLabel2.setFont(lasticaFont(13));
        // Position the label
        titleLabel2.setBounds(490, 180, 200, 30);
        layeredPane.add(titleLabel2, Integer.valueOf(2));

        JPanel rotatedLabel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                String text = "OR";
                g2.setFont(new Font("Microsoft JhengHei", Font.BOLD, 13));
                g2.setColor(Color.BLACK);

                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();

                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2;

                g2.rotate(Math.toRadians(-90), getWidth() / 2.0, getHeight() / 2.0);
                g2.drawString(text, x, y);
            }
        };

        rotatedLabel.setOpaque(false);
        rotatedLabel.setBounds(333, 274, 30, 100);
        layeredPane.add(rotatedLabel, Integer.valueOf(2));
                
        // Create and set up the text field
        JTextArea jTextArea = new JTextArea();
        jTextArea.setText("\n   1- Encrypt Files   \n\n   2- Decrypt Files   \n\n   3- Exit ");
        jTextArea.setFont(new Font("Arial", Font.PLAIN, 13));
        jTextArea.setEditable(false);
        jTextArea.setOpaque(false); 
        jTextArea.setForeground(Color.WHITE);
        jTextArea.setBackground(new Color(0, 0, 0, 0));
        int panelWidth = 170;
        int panelHeight = 107;
        jTextArea.setBounds(5, 5, panelWidth - 10, panelHeight - 10);
        jTextArea.setBorder(null);
        jTextArea.setMargin(new Insets(0 , 0, 0, 0));
        RoundedPanel panel = new RoundedPanel(25);
        panel.setBounds(407, 230, 230, 120);
        panel.setLayout(null);
        panel.add(jTextArea);
        layeredPane.add(panel, Integer.valueOf(2));


        JLabel inputLabel = new JLabel("Please select your choice:");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(inputLabel);
        inputLabel.setBounds(397, 317, 230, 117);
        layeredPane.add(inputLabel, Integer.valueOf(3));

        
        RoundedTextField inputField = new RoundedTextField(7);
        inputField.setBounds(407, 391, 230, 30);
        layeredPane.add(inputField, Integer.valueOf(3));

        // Add action listener to the input field
        inputField.addActionListener((ActionEvent e) -> {
            String text = inputField.getText();
            switch (text) {
                case "1":

                {
                    try {
                        encryptFiles(frame);
                    } catch (IOException ex) {
                        Logger.getLogger(cipher_application.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case "2":
                {
                    try {
                        decryptFiles(frame);
                    } catch (IOException ex) {
                        Logger.getLogger(cipher_application.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case "3":
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(layeredPane, "Invalid input. Please enter 1, 2, or 3.");
                    break;
            }
        });

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); 
        buttonPanel.setBounds(90, 240, 160, 200); 

        // Create buttons
        RoundedButton encryptButton = new RoundedButton("Encrypt Files", 20);
        RoundedButton decryptButton = new RoundedButton("Decrypt Files", 20);
        RoundedButton exitButton = new RoundedButton("Exit", 20);

        // Set up buttons
        encryptButton = setupButton(encryptButton);
        decryptButton = setupButton(decryptButton);
        exitButton = setupButton(exitButton);

        // Add action listeners for buttons
        encryptButton.addActionListener(e -> {
            try {
                encryptFiles(frame);
            } catch (IOException ex) {
                Logger.getLogger(cipher_application.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        decryptButton.addActionListener(e -> {
            try {
                decryptFiles(frame);
            } catch (IOException ex) {
                Logger.getLogger(cipher_application.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to panel
        buttonPanel.add(encryptButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 27)));
        buttonPanel.add(decryptButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 27)));
        buttonPanel.add(exitButton);

        // Add panel to the layered pane
        layeredPane.add(buttonPanel, Integer.valueOf(2));

        // Center and show the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
    }

    public static Font lasticaFont(int size){
        Font lasticaFont;
        try {
            lasticaFont = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + "/cipher_application/assets/fontsfree-net-lastica.ttf")).deriveFont(Font.BOLD, size);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(lasticaFont);
            return lasticaFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return lasticaFont = new Font("Microsoft JhengHei", Font.BOLD, 17);
        }
    }

    private static RoundedButton setupButton(RoundedButton button) {
        button.setBackground(new Color(50, 35, 84));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(150, 40));
        button.setMinimumSize(new Dimension(150, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }

    public static class RoundedButton extends JButton {
        private int radius;

        public RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(Color.WHITE);
            setBorderPainted(false);
            setBorder(null);
            setBackground(new Color(50, 35, 84));
            setPreferredSize(new Dimension(150, 40));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(50, 35, 84));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
        }
    }

    public static class RoundedTextField extends JTextField {
        private int radius;

        public RoundedTextField(int radius) {
            super();
            this.radius = radius;
            setOpaque(false); 
            setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.decode("#b8b9bb"));
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() -3, radius, radius);
            g2.dispose();
        }
    }
    
    public static void encryptFiles(JFrame frame) throws IOException{
        
        JFileChooser fileChooser = new JFileChooser();
        
        // Input file
        fileChooser.setDialogTitle("Select the file");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int value1 = fileChooser.showOpenDialog(frame);
        File input;
        if (value1 == JFileChooser.APPROVE_OPTION) {
            input = fileChooser.getSelectedFile();
        }else{
            input = null;
        }
        
        // Output file
        if (input != null) {
            fileChooser.setDialogTitle("Select the destination");
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int value2 = fileChooser.showSaveDialog(frame);
            File output;
            if (value2 == JFileChooser.APPROVE_OPTION) {
                output = fileChooser.getSelectedFile();
            } else {
                output = null;
            }
            if (output != null) {
                try {
                    encrypt(input, output);
                    JOptionPane.showMessageDialog(frame, "The file was encrypted successfully.");
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(frame, "Error during encryption: " + ex.getMessage());
                }
            }
        }
    }
    
    public static void encrypt(File input, File output) throws IOException {
        StringBuilder encryptedText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String text = encryptText(line);
                
                // Write the encrypted text to the output file
                writer.write(text);
                writer.newLine(); // Add new line after each encrypted line
                
                // Append to the string builder for the method return
                encryptedText.append(text).append(System.lineSeparator());
            }
        }
    }
    
    public static String encryptText(String text){
        
        // Move the first half of the string to the last half
        int midIndex = (int) Math.ceil(text.length() / 2.0);
        String firstPart = text.substring(0, midIndex);
        String secondPart = text.substring(midIndex);
        text = secondPart + firstPart;
        
        // Swap the first 2 characters of the line with the last two characters
        if (text.length() >= 4) {
            String firstTwo = text.substring(0, 2);
            String lastTwo = text.substring(text.length() - 2);
            String middle = text.substring(2, text.length() - 2);
            text = lastTwo + middle + firstTwo;
        }
        
        // Swap the two characters immediately to the left of the middle of the string
        int len = text.length();
        int center = len / 2;

        if (center - 2 >= 0 && center + 2 <= len) {
            String leftPart = text.substring(0, center - 2);
            String leftMid = text.substring(center - 2, center); 
            String rightMid = text.substring(center, center + 2); 
            String rightPart = text.substring(center + 2);

            text = leftPart + rightMid + leftMid + rightPart;
        }

        // Mapping of original characters to their substitutions
        Map<Character, Character> substitutions = new HashMap<>();
        substitutions.put('أ', '₪');
        substitutions.put('إ', '₰');
        substitutions.put('آ', '₯');
        substitutions.put('ا', '₱');
        substitutions.put('ب', '₲');
        substitutions.put('ت', '₵');
        substitutions.put('ث', '₶');
        substitutions.put('ج', '₷');
        substitutions.put('ح', '₸');
        substitutions.put('خ', '₹');
        substitutions.put('د', '₺');
        substitutions.put('ذ', '₻');
        substitutions.put('ر', '₼');
        substitutions.put('ز', '₽');
        substitutions.put('س', '₾');
        substitutions.put('ش', '₿');
        substitutions.put('ص', '℁');
        substitutions.put('ض', '℆');
        substitutions.put('ط', '℅');
        substitutions.put('ظ', 'ℓ');
        substitutions.put('ع', '℮');
        substitutions.put('غ', 'ℳ');
        substitutions.put('ف', '℘');
        substitutions.put('ق', '℧');
        substitutions.put('ك', 'ℨ');
        substitutions.put('ل', '℩');
        substitutions.put('م', 'ℯ');
        substitutions.put('ن', 'ℭ');
        substitutions.put('ه', 'K');
        substitutions.put('و', 'ℤ');
        substitutions.put('ي', 'ℬ');
        substitutions.put('ء', 'ℼ');
        substitutions.put('ة', 'ℽ');
        substitutions.put('ى', 'ℾ');
        substitutions.put('ئ', 'ℿ');
        substitutions.put('ؤ', '⅋');
        substitutions.put('A', '∆');
        substitutions.put('B', '∑');
        substitutions.put('C', '∏');
        substitutions.put('D', '√');
        substitutions.put('E', '∞');
        substitutions.put('F', '∫');
        substitutions.put('G', '≈');
        substitutions.put('H', '≠');
        substitutions.put('I', '≥');
        substitutions.put('J', '≤');
        substitutions.put('K', '∂');
        substitutions.put('L', '∇');
        substitutions.put('M', '∈');
        substitutions.put('N', '∉');
        substitutions.put('O', '∃');
        substitutions.put('P', '∀');
        substitutions.put('Q', '⊂');
        substitutions.put('R', '⊃');
        substitutions.put('S', '⊆');
        substitutions.put('T', '⊇');
        substitutions.put('U', '⊕');
        substitutions.put('V', '⊗');
        substitutions.put('W', '⊥');
        substitutions.put('X', '⋂');
        substitutions.put('Y', '⋃');
        substitutions.put('Z', '∠');
        substitutions.put('؟', '¿');
        substitutions.put('.', 'W');
        substitutions.put(',', 'X');
        substitutions.put('!', 'Š');
        substitutions.put('?', '…');
        substitutions.put(':', 'y');
        substitutions.put(';', 'Z');
        substitutions.put('\'', 'f');
        substitutions.put('"', 'Ž');
        substitutions.put('(', '|');
        substitutions.put(')', '~');
        substitutions.put('-', '_');
        substitutions.put('_', '`');
        substitutions.put('+', '‰');
        substitutions.put('=', 'Ÿ');
        substitutions.put('@', '¡');
        substitutions.put('#', '«');
        substitutions.put('$', '±');
        substitutions.put('&', 'V');
        substitutions.put('{', '¬');
        substitutions.put('}', '£');
        substitutions.put('0', '¢');
        substitutions.put('1', '§');
        substitutions.put('2', '¥');
        substitutions.put('3', 'U');
        substitutions.put('4', '¶');
        substitutions.put('5', 'ß');
        substitutions.put('6', '©');
        substitutions.put('7', '®');
        substitutions.put('8', '™');
        substitutions.put('9', '¤');
        substitutions.put(' ', '€');
        
        StringBuilder sb = new StringBuilder();

        // Iterate over each character in the string
        for (char c : text.toCharArray()) {
            char upperC = Character.toUpperCase(c);
            // Substitute if the character is in the map, else keep the original character
            sb.append(substitutions.getOrDefault(upperC, c));
        }

        text = sb.toString();
        
        return text;
    }
    
    public static void decryptFiles(JFrame frame) throws IOException{
        
        JFileChooser fileChooser = new JFileChooser();
        
        // Input file
        fileChooser.setDialogTitle("Select the file");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int value1 = fileChooser.showOpenDialog(frame);
        File input;
        if (value1 == JFileChooser.APPROVE_OPTION) {
            input = fileChooser.getSelectedFile();
        }else{
            input = null;
        }
        
        // Output file
        if (input != null) {
            fileChooser.setDialogTitle("Select the destination");
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int value2 = fileChooser.showSaveDialog(frame);
            File output;
            if (value2 == JFileChooser.APPROVE_OPTION) {
                output = fileChooser.getSelectedFile();
            } else {
                output = null;
            }
            if (output != null) {
                try {
                    decrypt(input, output);
                    JOptionPane.showMessageDialog(frame, "The file was decrypted successfully.");
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(frame, "Error during encryption: " + ex.getMessage());
                }
            }
        }
    }
    
    public static void decrypt(File input, File output) throws IOException {
        StringBuilder decryptedText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String text = decryptText(line);

                // Write the decrypted text to the output file
                writer.write(text);
                writer.newLine(); // Add new line after each decrypted line

                // Append to the string builder for the method return
                decryptedText.append(text).append(System.lineSeparator());
            }
        }
    }
    
    public static String decryptText(String text){
        
        // Mapping of substitutions to their original characters
        Map<Character, Character> reverseSubstitutions = new HashMap<>();
        reverseSubstitutions.put('₪', 'أ');
        reverseSubstitutions.put('₰', 'إ');
        reverseSubstitutions.put('₯', 'آ');
        reverseSubstitutions.put('₱', 'ا');
        reverseSubstitutions.put('₲', 'ب');
        reverseSubstitutions.put('₵', 'ت');
        reverseSubstitutions.put('₶', 'ث');
        reverseSubstitutions.put('₷', 'ج');
        reverseSubstitutions.put('₸', 'ح');
        reverseSubstitutions.put('₹', 'خ');
        reverseSubstitutions.put('₺', 'د');
        reverseSubstitutions.put('₻', 'ذ');
        reverseSubstitutions.put('₼', 'ر');
        reverseSubstitutions.put('₽', 'ز');
        reverseSubstitutions.put('₾', 'س');
        reverseSubstitutions.put('₿', 'ش');
        reverseSubstitutions.put('℁', 'ص');
        reverseSubstitutions.put('℆', 'ض');
        reverseSubstitutions.put('℅', 'ط');
        reverseSubstitutions.put('ℓ', 'ظ');
        reverseSubstitutions.put('℮', 'ع');
        reverseSubstitutions.put('ℳ', 'غ');
        reverseSubstitutions.put('℘', 'ف');
        reverseSubstitutions.put('℧', 'ق');
        reverseSubstitutions.put('ℨ', 'ك');
        reverseSubstitutions.put('℩', 'ل');
        reverseSubstitutions.put('ℯ', 'م');
        reverseSubstitutions.put('ℭ', 'ن');
        reverseSubstitutions.put('K', 'ه');
        reverseSubstitutions.put('ℤ', 'و');
        reverseSubstitutions.put('ℬ', 'ي');
        reverseSubstitutions.put('ℼ', 'ء');
        reverseSubstitutions.put('ℽ', 'ة');
        reverseSubstitutions.put('ℾ', 'ى');
        reverseSubstitutions.put('ℿ', 'ئ');
        reverseSubstitutions.put('⅋', 'ؤ');
        reverseSubstitutions.put('∆', 'A');
        reverseSubstitutions.put('∑', 'B');
        reverseSubstitutions.put('∏', 'C');
        reverseSubstitutions.put('√', 'D');
        reverseSubstitutions.put('∞', 'E');
        reverseSubstitutions.put('∫', 'F');
        reverseSubstitutions.put('≈', 'G');
        reverseSubstitutions.put('≠', 'H');
        reverseSubstitutions.put('≥', 'I');
        reverseSubstitutions.put('≤', 'J');
        reverseSubstitutions.put('∂', 'K');
        reverseSubstitutions.put('∇', 'L');
        reverseSubstitutions.put('∈', 'M');
        reverseSubstitutions.put('∉', 'N');
        reverseSubstitutions.put('∃', 'O');
        reverseSubstitutions.put('∀', 'P');
        reverseSubstitutions.put('⊂', 'Q');
        reverseSubstitutions.put('⊃', 'R');
        reverseSubstitutions.put('⊆', 'S');
        reverseSubstitutions.put('⊇', 'T');
        reverseSubstitutions.put('⊕', 'U');
        reverseSubstitutions.put('⊗', 'V');
        reverseSubstitutions.put('⊥', 'W');
        reverseSubstitutions.put('⋂', 'X');
        reverseSubstitutions.put('⋃', 'Y');
        reverseSubstitutions.put('∠', 'Z');
        reverseSubstitutions.put('¿', '؟');
        reverseSubstitutions.put('W', '.');
        reverseSubstitutions.put('X', ',');
        reverseSubstitutions.put('Š', '!');
        reverseSubstitutions.put('…', '?');
        reverseSubstitutions.put('y', ':');
        reverseSubstitutions.put('Z', ';');
        reverseSubstitutions.put('f', '\'');
        reverseSubstitutions.put('Ž', '"');
        reverseSubstitutions.put('|', '(');
        reverseSubstitutions.put('~', ')');
        reverseSubstitutions.put('_', '-');
        reverseSubstitutions.put('`', '_');
        reverseSubstitutions.put('‰', '+');
        reverseSubstitutions.put('Ÿ', '=');
        reverseSubstitutions.put('¡', '@');
        reverseSubstitutions.put('«', '#');
        reverseSubstitutions.put('±', '$');
        reverseSubstitutions.put('V', '&');
        reverseSubstitutions.put('¬', '{');
        reverseSubstitutions.put('£', '}');
        reverseSubstitutions.put('¢', '0');
        reverseSubstitutions.put('§', '1');
        reverseSubstitutions.put('¥', '2');
        reverseSubstitutions.put('U', '3');
        reverseSubstitutions.put('¶', '4');
        reverseSubstitutions.put('ß', '5');
        reverseSubstitutions.put('©', '6');
        reverseSubstitutions.put('®', '7');
        reverseSubstitutions.put('™', '8');
        reverseSubstitutions.put('¤', '9');
        reverseSubstitutions.put('€', ' ');

        StringBuilder sb = new StringBuilder();

        // Iterate over each character in the string
        for (char c : text.toCharArray()) {
            char upperC = Character.toUpperCase(c);
            // Substitute if the character is in the map, else keep the original character
            sb.append(reverseSubstitutions.getOrDefault(upperC, c));
        }

        text = sb.toString();
        
        // Swap the two characters immediately to the right of the middle of the string with the two characters that immediately precede them. 
        int len = text.length();
        int center = len / 2;

        if (center - 2 >= 0 && center + 2 <= len) {
            String leftPart = text.substring(0, center - 2);
            String leftMid = text.substring(center - 2, center);    // two left of center
            String rightMid = text.substring(center, center + 2);   // two right of center
            String rightPart = text.substring(center + 2);

            text = leftPart + rightMid + leftMid + rightPart;
        }
        
        // Swap the first 2 characters of the line with the last two characters
        if (text.length() >= 4) {
            String firstTwo = text.substring(0, 2);
            String lastTwo = text.substring(text.length() - 2);
            String middle = text.substring(2, text.length() - 2);
            text = lastTwo + middle + firstTwo;
        }
        
        // Move the first half of the string to be the last half
        int newMid = text.length() / 2;
        String firstHalf = text.substring(0, newMid);
        String secondHalf = text.substring(newMid);
        text = secondHalf + firstHalf;
        
        return text;
    }
}