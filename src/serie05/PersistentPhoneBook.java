package serie05;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import serie04.Civ;
import serie04.PhoneBook;

/**
 * Mod�lise un annuaire t�l�phonique permettant de sauvegarder dans et
 *  charger � partir d'un fichier de texte.
 * Les fichiers textes associ�s aux annuaires doivent imp�rativement
 *  respecter le format suivant :
 * <ul>
 *    <li>Une entr�e par ligne</li>
 *    <li>Une ligne est constitu�e, dans cet ordre,
 *       <ol>
 *          <li>d'un entier entre 0 et le nombre de civilit�s moins une
 *              suivi du caract�re ':'</li>
 *          <li>d'un nom suivi du caract�re ':'</li>
 *          <li>d'un pr�nom suivi du caract�re ':'</li>
 *          <li>d'un num�ro de t�l�phone</li>
 *          <li>[d'une virgule suivie d'un num�ro de t�l�phone]*</li>
 *       </ol>
 *    </li>
 * </ul>
 * @cons
 * $DESC$
 *     Un annuaire �ventuellement associ� � un fichier de chemin 'file'
 * $ARGS$
 *     File file
 * $POST$
 *     getFile() == file
 * @cons
 * $DESC$
 *     Un annuaire sans fichier associ�
 * $ARGS$ -
 * $POST$
 *     getFile() == null
 */

public interface PersistentPhoneBook extends PhoneBook {
    
    // CONSTANTES
    
    // Le pattern qui permet de tester la validit� d'une ligne du fichier :
    //  des blancs (0, 1 ou plusieurs)
    //  un chiffre repr�sentant la civilit� (0..3)
    //  des blancs (0, 1 ou plusieurs)
    //  :
    //  une cha�ne (qui ne contient pas ":")
    //  :
    //  une cha�ne (qui ne contient pas ":")
    //  :
    //  des blancs (0, 1 ou plusieurs)
    //  un no de t�l�phone (dd.dd.dd.dd.dd o� d repr�sente un chiffre)
    //  des blancs (0, 1 ou plusieurs)
    //  {
    //    une virgule (",")
    //    des blancs (0, 1 ou plusieurs)
    //    un num�ro de t�l�phone
    //    des blancs (0, 1 ou plusieurs)
    //  } 0, 1 ou plusieurs fois
    Pattern LINE_RECOGNIZER = Pattern.compile(
            "^\\s*[0-" + (Civ.values().length - 1) // civ
            + "]\\s*:[^:]+:[^:]+:"                 // :nom:pr�nom:
            + "\\s*(\\d\\d\\.){4}\\d\\d\\s*"       // dd.dd.dd.dd.dd
            + "(,\\s*(\\d\\d\\.){4}\\d\\d\\s*)*$"  // , no, no, ...
    );

    // REQUETES
    
    /**
     * Retourne le chemin du fichier de sauvegarde associ� � cet annuaire.
     * Retourne null s'il n'y a pas de fichier associ�.
     */
    File getFile();
    
    // COMMANDES
    
    /**
     * Change de chemin de fichier de sauvegarde associ� � cet annuaire.
     * @pre
     *     file != null
     * @post
     *     getFile() == file
     */
    void setFile(File file);
    
    /**
     * Remplace le contenu de cet annuaire par celui du fichier texte.
     * Si le chargement est impossible, une BadSyntaxException est lev�e et
     *  l'annuaire est alors vide.
     * @pre
     *     getFile() != null
     * @post
     *     L'annuaire a �t� r�initialis� avec le contenu du fichier associ�
     *      si tout s'est bien pass�
     *     L'annuaire est vide si une exception a �t� lev�e
     * @throws BadSyntaxException
     *     si le fichier associ� est mal form�
     * @throws java.io.FileNotFoundException
     *     si le fichier associ� n'existe pas ou n'est pas accessible en lecture
     * @throws IOException
     *     s'il se produit une erreur d'entr�e/sortie lors de la lecture
     *      du fichier associ�
     */
    void load() throws IOException, BadSyntaxException;
    
    /**
     * Sauvegarde le contenu de l'annuaire dans son fichier associ�.
     * @pre
     *     getFile() != null
     * @post
     *     Le fichier associ� est un fichier de texte dont le contenu
     *      (correctement form�) est celui de l'annuaire si tout
     *      s'est bien pass�.
     *     Il ne doit y avoir aucun blanc superflu.
     * @throws java.io.FileNotFoundException
     *     si le fichier associ� ne peut pas �tre cr�� ou n'est pas accessible
     *      en �criture
     * @throws IOException
     *     s'il se produit une erreur d'entr�e/sortie lors de l'�criture
     *      dans le fichier associ�
     */
    void save() throws IOException;
}
