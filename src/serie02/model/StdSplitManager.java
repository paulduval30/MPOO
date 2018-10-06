package serie02.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.SingleSelectionModel;

import serie02.util.FileState;
import util.Contract;

public class StdSplitManager implements SplitManager {
	
	private final int MAX_FRAGMENT_NB = 100;
    private final int MIN_FRAGMENT_SIZE = 1024;
	
	private File file;
	private FileState fileState;
	private ArrayList<Long> splitSizes;
	
	public StdSplitManager() {
		this.file = null;
		this.splitSizes = new ArrayList<Long>();
	}
	
	public StdSplitManager(File file) {
		Contract.checkCondition(file != null, "File is null");
		this.file = file;
		this.splitSizes = new ArrayList<Long>();
	}
	
	@Override
	public boolean canSplit() {
		return (new FileState(this.file).isSplittable());
	}

	@Override
	public File getFile() {
		return this.file;
	}

	@Override
	public int getMaxFragmentNb() {
		return this.MAX_FRAGMENT_NB;
	}

	@Override
	public String[] getSplitsNames() {
		Contract.checkCondition(canSplit(), "File not splitable");
		String[] tabNames = new String[splitSizes.size()];
		for(int i = 0; i < tabNames.length; i++) {
			tabNames[i] = this.file.getName().split(".")[0] + (i + 1) + "." + this.file.getName().split(".")[1];
		}
		return tabNames;
	}

	@Override
	public long[] getSplitsSizes() {
		Contract.checkCondition(canSplit(), "File not splitable");
		
		long[] tabSizes = new long[this.splitSizes.size()];
		for(int j = 0; j < tabSizes.length; j++) {
			tabSizes[j] = this.splitSizes.get(j);
		}
		return tabSizes;
	}

	@Override
	public void setFile(File f) {
		Contract.checkCondition(f != null, "File is null");
		this.file = f;
	}

	@Override
	public void setSplitsSizes(long fragSize) {
		Contract.checkCondition(canSplit(), "File not splitable");
		Contract.checkCondition(fragSize >= MIN_FRAGMENT_SIZE, "FragSize too short");
		Contract.checkCondition(fragSize >= Math.ceil(getFile().length() / getMaxFragmentNb()), "FragSize too short");

		int nbMorceau = (int) (this.file.length() / fragSize);
		for(int k = 0; k < nbMorceau; k++) {
			this.splitSizes.add(fragSize);
		}
	}

	@Override
	public void setSplitsSizes(long[] fragSizes) {
		Contract.checkCondition(canSplit(), "File not splitable");
		Contract.checkCondition(fragSizes != null, "Fragsize is null");
		Contract.checkCondition(getMaxFragmentNb() > fragSizes.length, "Le nombre de fragment pour la découpe du fichier est trop grand");
		Contract.checkCondition(fragSizes.length >= 1, "Not enought Split");
		for (int i= 0; i < fragSizes.length; i++) {
			Contract.checkCondition(fragSizes[i] >= MIN_FRAGMENT_SIZE, "At least one fragment is too short");
		}
		
		int total = 0;
		for(int j = 0; j < fragSizes.length; j++) {
			if ((fragSizes[j] + total) < getFile().length()) {
				total += fragSizes[j];
				this.splitSizes.add(fragSizes[j]);
			}
			else
				this.splitSizes.add(getFile().length() - total);
		}
	}

	
	@Override
	public void setSplitsNumber(int number) {
		Contract.checkCondition(canSplit(), "lFile not splitable");
		Contract.checkCondition(number >= 1, "Not enought split");
		Contract.checkCondition(getMaxFragmentNb() >= number, "tpp much split");
		
		long fileLenght;
		int quotient;
		long reste;
		fileLenght = getFile().length();
		quotient = (int) fileLenght / number;
		reste = (int) fileLenght % number;
		long total = 0;
		
		if(quotient < this.MIN_FRAGMENT_SIZE)
		{
			quotient = this.MIN_FRAGMENT_SIZE;
			reste = 0;
		}
		
		if (number == 1) 
			this.splitSizes.add(getFile().length());
		else {
			for(int z = 0; z < number; z++) {
				if(reste < z)
					this.splitSizes.add((long) quotient + 1);
				else 
					this.splitSizes.add((long) quotient);
				total += quotient;
			}
		}
	}
	
	@Override
	public void split() throws IOException {
		Contract.checkCondition(this.canSplit(), "File not splitable");
		InputStream input =	new FileInputStream(this.file);
		BufferedInputStream buf = new BufferedInputStream(input);
		byte[] tabByte = new byte[this.splitSizes.size()];
		for(int i = 0; i < this.splitSizes.size(); i++)
		{
			
			OutputStream out = new FileOutputStream(new File(this.getSplitsNames()[i]));
			BufferedOutputStream bufout = new BufferedOutputStream(out);
			buf.read(tabByte, i, Integer.parseInt(splitSizes.get(i) + ""));
			bufout.write(tabByte[i]);
		}
	}

	@Override
	public void unsetFile() {
		this.file = null;
	}

}
