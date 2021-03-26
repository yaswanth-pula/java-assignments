package imageSaver;
import edu.duke.*;
import java.io.File;

public class ImageSaver {
	public void doSave() {
		DirectoryResource dr = new DirectoryResource();
		for (File currentFile : dr.selectedFiles()) {
			ImageResource image = new ImageResource(currentFile);
			String currentFilename = image.getFileName();
			String newFileName = "copy-" + currentFilename;
			image.setFileName(newFileName);
			image.draw();
			image.save();
		}
	}
}
