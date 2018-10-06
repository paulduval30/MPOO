package serie02.model;

import java.io.File;
import java.io.IOException;

/**
 * Mod�lise le type abstrait SplitManager.
 * Un split manager est un objet capable de d�couper un gros fichier en fichiers
 *  plus petits.
 * Le principe est le suivant : on configure le split manager en lui appliquant
 *  autant de fois que l'on souhaite une des m�thodes setSplits* (en changeant
 *  la taille des morceaux � chaque fois).
 * Puis on commande au split manager de casser le fichier selon la derni�re
 *  configuration effectu�e (m�thode split()).
 * Le nom des petits fichiers produits est form� sur la base du nom du fichier
 *  source, augment� d'un num�ro de s�quence.
 * Enfin, un fichier est fragmentable si et seulement si il existe bien comme
 *  fichier dans le syst�me de fichiers et qu'il est accessible en lecture pour
 *  le programme.
 * 
 * @inv <pre>
 *     Soit f   ::= getFile()
 *          gSS ::= getSplitsSizes()
 *     !canSplit() <==> getMaxFragmentNb() == 0
 *     canSplit()
 *         ==> gSS != null
 *             1 <= gSS.length <= getMaxFragmentNb()
 *             getMaxFragmentNb() ==
 *                 min(MAX_FRAGMENT_NB, ceil(f.length() / MIN_FRAGMENT_SIZE))
 *             forall i:[0..gSS.length - 2] : gSS[i] >= MIN_FRAGMENT_SIZE
 *             gSS[gSS.length - 1] >= 1
 *             sum(i:[0..gSS.length - 1], gSS[i]) == f.length()
 *             getSplitsNames() != null
 *             getSplitsNames().length == gSS.length
 *             forall i:[0..getSplitsNames().length - 1] :
 *                 getSplitsNames()[i]
 *                     .equals(f.getAbsolutePath() + "." + (i + 1)) </pre>
 * 
 * @cons <pre>
 * $DESC$
 *     Un gestionnaire de scission sans fichier.
 * $ARGS$
 *     -
 * $POST$
 *     getFile() == null </pre>
 * 
 * @cons <pre>
 * $DESC$
 *     Un gestionnaire de scission bas� sur le fichier file.
 * $ARGS$
 *     File file
 * $PRE$
 *     file != null
 * $POST$
 *     file.equals(getFile())
 *     canSplit() ==> getSplitsSizes().length == 1 </pre>
 */
public interface SplitManager {
    
    // CONSTANTES
   
    /**
     * Borne sup�rieure pour le nombre maximal de fragments.
     */
    int MAX_FRAGMENT_NB = 100;
    
    /**
     * Taille minimale d'un fragment (en octets).
     */
    int MIN_FRAGMENT_SIZE = 1024;

    // REQUETES
    
    /**
     * Indique si le fichier sous-jacent peut �tre fragment�, c'est-�-dire si
     *  un chemin a bien �t� d�fini et qu'il repr�sente un fichier (pas un
     *  r�pertoire) accessible en lecture pour le programme.
     */
    boolean canSplit();
   
    /**
     * Le fichier � fragmenter.
     */
    File getFile();
    
    /**
     * Le nombre maximal de fragments que supporte la configuration courante de
     *  ce fragmenteur.
     */
    int getMaxFragmentNb();
    
    /**
     * Les noms des fragments du fichier sous-jacent.
     * @pre <pre>
     *     canSplit() </pre>
     */
    String[] getSplitsNames();
    
    /**
     * Les tailles des fragments du fichier sous-jacent.
     * @pre <pre>
     *     canSplit() </pre>
     */
    long[] getSplitsSizes();
   
    // COMMANDES
   
    /**
     * Fixe le fichier � fragmenter.
     * @pre <pre>
     *     f != null </pre>
     * @post <pre>
     *     getFile().equals(f)
     *     canSplit() ==> getSplitsSizes().length == 1 </pre>
     */
    void setFile(File f);
    
    /**
     * Fixe la taille des fragments de fichier.
     * Seul le dernier fragment peut �tre de taille inf�rieure �
     *  MIN_FRAGMENT_SIZE.
     * @pre <pre>
     *     canSplit()
     *     fragSize >= max(
     *          MIN_FRAGMENT_SIZE,
     *          ceil(getFile().length() / getMaxFragmentNb())) </pre>
     * @post <pre>
     *     Soit gSS ::= getSplitsSizes()
     *     gSS.length == ceil(getFile().length() / fragSize)
     *     forall i:[0..gSS.length - 2] : gSS[i] == fragSize
     *     1 <= gSS[gSS.length - 1] <= fragSize </pre>
     */
    void setSplitsSizes(long fragSize);
    
    /**
     * Fixe la taille des fragments de fichier.
     * Si la somme des tailles pass�es est inf�rieure � la taille du fichier �
     *  fragmenter on rajoute un dernier fragment qui contient ce qu'il reste.
     * Si la somme des tailles pass�es est sup�rieure � la taille du fichier �
     *  fragmenter on tronque l'argument.
     * @pre <pre>
     *     canSplit()
     *     fragSizes != null
     *     getMaxFragmentNb() > fragSizes.length >= 1
     *     forall i:[0..fragSizes.length - 1] :
     *         fragSizes[i] >= MIN_FRAGMENT_SIZE </pre>
     * @post <pre>
     *     Soit gSS ::= getSplitsSizes()
     *     getFile().length() <= sum(i:[0..fragSizes.length - 1], fragSizes[i])
     *         ==> gSS.length <= fragSizes.length
     *             forall i:[0..gSS.length - 2] : gSS[i] == fragSizes[i]
     *             1 <= gSS[gSS.length - 1] <= fragSizes[gSS.length - 1]
     *     getFile().length() > sum(i:[0..fragSizes.length - 1], fragSizes[i])
     *         ==> gSS.length == fragSizes.length + 1
     *             forall i:[0..fragSizes.length - 1] : gSS[i] == fragSizes[i]
     *             gSS[fragSizes.length] ==
     *                 getFile().length()
     *                 - sum(i:[0..fragSizes.length - 1], fragSizes[i]) </pre>
     */
    void setSplitsSizes(long[] fragSizes);
    
    /**
     * Fixe le nombre des fragments de fichier, qui sont alors tous � peu pr�s
     *  de la m�me taille (� un octet pr�s).
     * @pre <pre>
     *     canSplit()
     *     1 <= number <= getMaxFragmentNb() </pre>
     * @post <pre>
     *     Soit gSS ::= getSplitsSizes()
     *          n   ::= getFile().length()
     *          q   ::= n / number
     *          r   ::= n % number
     *     q < MIN_FRAGMENT_SIZE
     *         ==> gSS.length == ceil(n / MIN_FRAGMENT_SIZE)
     *             forall i:[0..gSS.length - 2] : gSS[i] == MIN_FRAGMENT_SIZE
     *             gSS[gSS.length - 1] == n - sum(i:0..gSS.length - 2, gSS[i])
     *     q >= MIN_FRAGMENT_SIZE
     *         ==> gSS.length == number
     *             forall i:[0..r - 1] : gSS[i] == q + 1
     *             forall i:[r..number - 1] : gSS[i] == q </pre>
     */
    void setSplitsNumber(int number);
    
    /**
     * Effectue sur le disque la scission du gros fichier en plus petits.
     * @pre <pre>
     *     canSplit() </pre>
     * @post <pre>
     *     forall i:[0..getSplitsSizes().length - 1] :
     *         getSplitsSizes()[i] == La taille du fichier sur disque de nom
     *                                getSplitsNames()[i]
     *     le fichier associ� � getFile() a m�me contenu que la
     *         concat�nation des fichiers de nom getSplitsNames() </pre>
     * @throws java.io.FileNotFoundException
     *     s'il n'est pas possible de cr�er ou d'ouvrir en �criture l'un des
     *         fichiers fragments
     * @throws IOException
     *     s'il s'est produit une erreur d'entr�e/sortie durant l'�criture dans
     *         les fichiers fragments
     */
    void split() throws IOException;
    
    /**
     * D�connecte le fichier de ce gestionnaire.
     * @post <pre>
     *     getFile() == null </pre>
     */
    void unsetFile();
}
