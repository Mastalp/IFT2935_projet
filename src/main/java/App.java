import org.xml.sax.ext.Attributes2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class App extends JPanel {

    private Connection conn;

    public JTextArea infoTextArea = new JTextArea("");

    public static final String Q1QUERY = "SELECT * FROM parcinfo.installer;";
    public static final String Q2QUERY = "SELECT * FROM parcinfo.installer;";
    public static final String Q3QUERY = "SELECT * FROM parcinfo.installer;";
    public static final String Q4QUERY = "SELECT * FROM parcinfo.installer;";

    public App() throws SQLException {

        // Initialize connection
        initConnection();


        // Set the layout
        setLayout(new BorderLayout());

        // Create the title
        JLabel titleLabel = new JLabel("Retour de la requete: ", SwingConstants.CENTER);

        // Create the text area for displaying information
        //JTextArea infoTextArea = new JTextArea();
        infoTextArea.setText("TO DO ");
        infoTextArea.setEditable(false); // if you don't want it to be editable

        // Create the panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1)); // 4 rows, 1 column

        JButton b1 = new JButton("Question 1");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    infoTextArea.setText(processQueryAndFormatToString(Q1QUERY));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        JButton b2 = new JButton("Question 2");

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    infoTextArea.setText(processQueryAndFormatToString(Q2QUERY));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        JButton b3 = new JButton("Question 3");

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    infoTextArea.setText(processQueryAndFormatToString(Q3QUERY));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        JButton b4 = new JButton("Question 4");

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    infoTextArea.setText(processQueryAndFormatToString(Q4QUERY));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });



        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);

        // Add components to the main panel
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(infoTextArea), BorderLayout.CENTER); // Wrap the text area in a scroll pane
        add(buttonPanel, BorderLayout.WEST);
    }

    private void initConnection() throws SQLException {
        conn = DB.connect();
        System.out.println("CONNECTION TO DB SUCCESSFUL");
    }

    private static void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
            System.out.println("CONNECTION SUCCESSFULLY CLOSED");
        }
    }


    public String processQueryAndFormatToString(String query) throws SQLException {
        StringBuilder sb = new StringBuilder();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData metadata = rs.getMetaData();
        int columnCount = metadata.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            sb.append(metadata.getColumnName(i) + ", ");
        }
        while (rs.next()) {
            String row = "";
            sb.append("\n");
            for (int i = 1; i <= columnCount; i++) {
                row += rs.getString(i) + ", ";
            }
            sb.append(row);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the frame to hold the panel
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("IFT2935 - Projet");

            // Add the panel to the frame

            try {
                App app = new App();
                frame.add(app);
                // Set the size and make the frame visible
                frame.setSize(600, 400);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            closeConnection(app.conn);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        super.windowClosed(e);
                    }
                });

                frame.setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }




        });
    }
}
