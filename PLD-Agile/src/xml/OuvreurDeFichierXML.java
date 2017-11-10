package xml;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

import modele.ExceptionPlanCo;

/**
 * Classe gerant l'ouverture d'une popup de choix de fichier xml dans un explorateur de fichier
 * @author 4104
 */
public class OuvreurDeFichierXML extends FileFilter {// Singleton
	
	private static OuvreurDeFichierXML instance = null;
	private OuvreurDeFichierXML(){}
	protected static OuvreurDeFichierXML getInstance(){
		if (instance == null) instance = new OuvreurDeFichierXML();
		return instance;
	}

	/**
	 * Ouvre une popup afin de recuperer le chemin vers un fichier
	 * @param lecture Ouvrir un fichier ou sauvegarder un fichier
	 * @return
	 * @throws ExceptionPlanCo
	 */
 	public File ouvre(boolean lecture) throws ExceptionPlanCo{
 		int returnVal;
 		JFileChooser jFileChooserXML = new JFileChooser(System.getProperty("user.dir"));
        jFileChooserXML.setFileFilter(this);
        jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (lecture)
         	returnVal = jFileChooserXML.showOpenDialog(null);
        else
         	returnVal = jFileChooserXML.showSaveDialog(null);
        if (returnVal != JFileChooser.APPROVE_OPTION)
        	if (returnVal == JFileChooser.CANCEL_OPTION)
        		throw new ExceptionPlanCo(ExceptionPlanCo.ANNULATION_OUVERTURE_FICHIER);
        	else
        		throw new ExceptionPlanCo(ExceptionPlanCo.PROBLEME_OUVERTURE_FICHIER);
        return new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
        
 	}
 	
 	/**
 	 * Accepte les fichiers xml uniquement
 	 * @param f 
 	 */
 	@Override
    public boolean accept(File f) {
	    	if (f == null) return false;
	    	if (f.isDirectory()) return true;
	    	String extension = getExtension(f);
	    	if (extension == null) return false;
	    	return extension.contentEquals("xml");
    }

 	/**
 	 * Retourne la description du fichier XML
 	 * @return description du fichier XML
 	 */
	@Override
	public String getDescription() {
		return "Fichier XML";
	}

	/**
	 * Retourne l'extension du fichier
	 * @param f fichier
	 * @return L'extension du fichier passe en parametre
	 */
    private String getExtension(File f) {
	    String filename = f.getName();
	    int i = filename.lastIndexOf('.');
	    if (i>0 && i<filename.length()-1) 
	    	return filename.substring(i+1).toLowerCase();
	    return null;
   }
}
