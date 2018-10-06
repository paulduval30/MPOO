package serie02.util;

import java.io.File;

/**
 * Modélise l'état de "fragmentabilité" d'un fichier.
 * Une instance de <code>FileState</code> enveloppe une instance de
 *  <code>File</code> et indique à chaque instant si ce fichier est fragmentable
 *  ou pas (<code>isSplittable</code>).
 * Un fichier est fragmentable ssi il existe comme fichier
 *  (<code>File.canRead</code> retourne <code>true</code>), est accessible en
 *  lecture (<code>File.canRead</code> retourne <code>true</code>) et s'il est
 *  de taille non nulle (<code>File.length</code> retourne une valeur
 *  strictement positive).
 * Il se peut qu'une instance de <code>FileState</code> enveloppe la référence
 *  <code>null</code>, auquel cas elle gère l'absence de fichier.
 * Bien entendu, l'état du fichier peut évoluer au cours du temps et
 *  l'information le concernant n'est donc valable qu'au moment où elle est
 *  demandée.
 */
public class FileState {
    
    // ATTRIBUTS
    
    // Le fichier dont on gère l'état de fragmentabilité
    private final File file;
    
    // CONSTRUCTEURS

    public FileState(File f) {
        file = f;
    }

    // REQUETES
    
    /**
     * Un texte décrivant l'état de fragmentabilité du fichier géré.
     * @post <pre>
     *     Aucun fichier n'a été défini
     *         ==> result.equals("Aucun fichier n'a été défini")
     *     Le fichier n'existe pas ou n'est pas lisible
     *         ==> result.equals(
     *             "Le fichier X n'existe pas ou n'est pas lisible")
     *     Le fichier est bien défini mais de taille nulle
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
     * Indique l'état de fragmentabilité de file au moment de l'appel.
     * Deux appels effectués à deux instants différents peuvent retourner des
     *  valeurs différentes (en fonction de l'évolution de l'état interne du
     *  fichier).
     * @post <pre>
     *     result == true ssi le fichier est fragmentable </pre>
     */
    public boolean isSplittable() {
        return State.state(file) == State.SPLITTABLE;
    }

    // TYPES IMBRIQUES
    
    private enum State {
        UNDEFINED("Aucun fichier n'a été défini"),
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
