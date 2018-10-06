package serie02.util;

import java.io.File;

/**
 * Mod�lise l'�tat de "fragmentabilit�" d'un fichier.
 * Une instance de <code>FileState</code> enveloppe une instance de
 *  <code>File</code> et indique � chaque instant si ce fichier est fragmentable
 *  ou pas (<code>isSplittable</code>).
 * Un fichier est fragmentable ssi il existe comme fichier
 *  (<code>File.canRead</code> retourne <code>true</code>), est accessible en
 *  lecture (<code>File.canRead</code> retourne <code>true</code>) et s'il est
 *  de taille non nulle (<code>File.length</code> retourne une valeur
 *  strictement positive).
 * Il se peut qu'une instance de <code>FileState</code> enveloppe la r�f�rence
 *  <code>null</code>, auquel cas elle g�re l'absence de fichier.
 * Bien entendu, l'�tat du fichier peut �voluer au cours du temps et
 *  l'information le concernant n'est donc valable qu'au moment o� elle est
 *  demand�e.
 */
public class FileState {
    
    // ATTRIBUTS
    
    // Le fichier dont on g�re l'�tat de fragmentabilit�
    private final File file;
    
    // CONSTRUCTEURS

    public FileState(File f) {
        file = f;
    }

    // REQUETES
    
    /**
     * Un texte d�crivant l'�tat de fragmentabilit� du fichier g�r�.
     * @post <pre>
     *     Aucun fichier n'a �t� d�fini
     *         ==> result.equals("Aucun fichier n'a �t� d�fini")
     *     Le fichier n'existe pas ou n'est pas lisible
     *         ==> result.equals(
     *             "Le fichier X n'existe pas ou n'est pas lisible")
     *     Le fichier est bien d�fini mais de taille nulle
     *         ==> result.equals("Le fichier X est vide")
     *     Dans tous les autres cas
     *         ==> result.equals("Le fichier X est fragmentable") </pre>
     */
    public String describe() {
        State s = State.state(file);
        if (file == null) {
            return s.description;
        } else {
            return String.format(s.description, file.getAbsolutePath());
        }
    }
    
    /**
     * Indique l'�tat de fragmentabilit� de file au moment de l'appel.
     * Deux appels effectu�s � deux instants diff�rents peuvent retourner des
     *  valeurs diff�rentes (en fonction de l'�volution de l'�tat interne du
     *  fichier).
     * @post <pre>
     *     result == true ssi le fichier est fragmentable </pre>
     */
    public boolean isSplittable() {
        return State.state(file) == State.SPLITTABLE;
    }

    // TYPES IMBRIQUES
    
    private enum State {
        UNDEFINED("Aucun fichier n'a �t� d�fini"),
        NOT_VALID("Le fichier \"%1$s\" n'existe pas ou n'est pas lisible"),
        EMPTY("Le fichier \"%1$s\" est vide"),
        SPLITTABLE("Le fichier \"%1$s\" est fragmentable");
        
        private String description;
        State(String d) {
            description = d;
        }

        private static State state(File f) {
            State result = SPLITTABLE;
            if (f == null) {
                result = UNDEFINED;
            } else if (!f.isFile() || !f.canRead()) {
                result = NOT_VALID;
            } else if (f.length() == 0) {
                result = EMPTY;
            }
            return result;
        }
    }
}
