package serie01;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import serie01.gui.Converter;
import serie01.util.Currency;
import serie01.util.CurrencyDB;
import serie01.util.DBFactory;

public final class Root {

    private Root() {
        // rien ici
    }
    
    private static void initDBType() {
        boolean done = false;
        while (!done) {
            DBTypes selection = (DBTypes) JOptionPane.showInputDialog(
                    null,
                    "Choissisez la source des données :",
                    "Création de la base de données",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    DBTypes.values(),
                    DBTypes.INTERNAL);
            if (selection != null) {
                try {
                    selection.createDB();
                } catch (UnsupportedOperationException e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "L'accès à ce type de bd n'est pas implémenté",
                            "Connexion à une base de données",
                            JOptionPane.WARNING_MESSAGE
                    );
                } finally {
                    done = selection.created;
                }
            } else {
                System.out.println("pas de base de données, pas de jouet !");
                done = true;
            }
        }
    }
    
    private enum DBTypes {
        INTERNAL("Tableau mémoire") {
            @Override void createDB() {
                CurrencyDB db = DBFactory.createInternalDB();
                Currency.setDB(db);
                setCreated();
            }
        },
        LOCAL("Fichier local") {
//            @Override void createDB() {
//                JFileChooser jfc = new JFileChooser();
//                int returnVal = jfc.showOpenDialog(null);
//                if (returnVal == JFileChooser.APPROVE_OPTION) {
//                    File f = jfc.getSelectedFile();
//                    CurrencyDB db = DBFactory.createLocalDB(f);
//                    Currency.setDB(db);
//                    setCreated();
//                }
//            }
        },
        REMOTE("SGBD distant");
        
        private final String name;
        private boolean created;
        DBTypes(String name) {
            this.name = name;
        }
        @Override public String toString() {
            return name;
        }
        void createDB() {
            throw new UnsupportedOperationException();
        }
        void setCreated() {
            created = true;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initDBType();
                new Converter(5).display();
            }
        });
    }
}
