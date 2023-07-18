package com.enviro.assessment.grad001.karabomaila;

import java.io.File;
import java.net.URI;

public interface FileParser {
    public void parseCSV(File csvFile);
    public File convertCSVDataToImage(String imageFormat, String base64ImageData);
    public URI createImageLink(File fileImage);
}
