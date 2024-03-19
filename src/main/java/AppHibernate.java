import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

public class AppHibernate extends JPanel {
    private static SessionFactory sessionFactory;

    public JTextArea infoTextArea = new JTextArea("");

    public AppHibernate() {
        // Initialize Hibernate
        initSessionFactory();

        // Set the layout
        setLayout(new BorderLayout());

        // Create the title
        JLabel titleLabel = new JLabel("Retour de la requete: ", SwingConstants.CENTER);

        // Create the text area for displaying information
        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setText("TO DO ");
        infoTextArea.setEditable(false); // if you don't want it to be editable

        // Create the panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1)); // 4 rows, 1 column


        JButton b1 = new JButton("Question 1");
        b1.addActionListener(e -> infoTextArea.setText(processQueryAndFormatToString("FROM Installer")));

        // Setup other buttons similarly...

        // Add components to the main panel...
        buttonPanel.add(b1);
        //buttonPanel.add(b2);
        //buttonPanel.add(b3);
        //buttonPanel.add(b4);

        // Add components to the main panel
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(infoTextArea), BorderLayout.CENTER); // Wrap the text area in a scroll pane
        add(buttonPanel, BorderLayout.WEST);
    }

    private static void initSessionFactory() {
        // Configuration can also be done programmatically
        sessionFactory = new Configuration().configure().buildSessionFactory();
        System.out.println("Hibernate SessionFactory Configured.");
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("SessionFactory closed");
        }
    }

    public String processQueryAndFormatToString(String hql) {
        StringBuilder sb = new StringBuilder();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery(hql);
            List results = query.list(); // Cast and process according to the entity

            for (Object result : results) {
                //Installer installer = (Installer) result; // Assuming the result is of type Installer
                // Build your string here...
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
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
                AppJDBC appJDBC = new AppJDBC();
                frame.add(appJDBC);
                // Set the size and make the frame visible
                frame.setSize(600, 400);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        shutdown();
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
