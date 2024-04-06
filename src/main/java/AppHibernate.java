import entity.Livres;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

public class AppHibernate extends JPanel {
    private static SessionFactory sessionFactory;
    private JTextArea infoTextArea = new JTextArea("");

    // placeholder queries
    private static final String Q1QUERY = "SELECT * FROM biblio.livres;";
    private static final String Q2QUERY = "SELECT * FROM biblio.adherents;";
    private static final String Q3QUERY = "SELECT * FROM biblio.commandes;";
    private static final String Q4QUERY = "SELECT * FROM biblio.emprunts";


    public AppHibernate() {
        // Initialize Hibernate
        initSessionFactory();

        // Set the layout
        setLayout(new BorderLayout());

        // Create the title
        JLabel titleLabel = new JLabel("Retour de la requete: ", SwingConstants.CENTER);

        // Create the text area for displaying information
        infoTextArea.setText("");
        infoTextArea.setEditable(false); // if you don't want it to be editable

        // Create the panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1)); // 4 rows, 1 column


        JButton b1 = new JButton("Question 1");
        JButton b2 = new JButton("Question 2");
        JButton b3 = new JButton("Question 3");
        JButton b4 = new JButton("Question 4");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText(processQueryAndFormatToString(Q1QUERY));

            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText(processQueryAndFormatToString(Q2QUERY));

            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText(processQueryAndFormatToString(Q3QUERY));

            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText(processQueryAndFormatToString(Q4QUERY));

            }
        });

        // Setup other buttons similarly...

        // Add components to the main panel...
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);

        // Add components to the main panel
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(infoTextArea), BorderLayout.CENTER); // Wrap the text area in a scroll pane
        add(buttonPanel, BorderLayout.WEST);
    }

    private static void initSessionFactory() {
        // Configuration can also be done programmatically
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction(); // Begin transaction

            // Assuming Q1QUERY is a native SQL query. If it's HQL, use session.createQuery()
            Query query = session.createNativeQuery(hql);

            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

            List<Map<String, Object>> results = query.getResultList(); // Adjusted for native queries usually returning Object[] for each row


            for (Object row : results) {

                sb.append(row.toString());
                sb.append("\n");
            }

            transaction.commit(); // Commit transaction if all operations were successful
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback in case of an exception
            e.printStackTrace();
        } finally {
            if (session != null) session.close(); // Always close the session to free up resources
        }

        return sb.toString();
    }

    public static void run() {
        SwingUtilities.invokeLater(() -> {
            // Create the frame to hold the panel
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("IFT2935 - Projet");

            // Add the panel to the frame

            AppHibernate appHibernate = new AppHibernate();
            frame.add(appHibernate);
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

        });
    }

    public static void main(String[] args) {
        run();
    }
}
