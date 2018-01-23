/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tls;

/**
 *
 * @author Luiz
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class DescarregadorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private StreamedContent streamedContent;
    private StreamedContent image;

    public StreamedContent getGraphicImage() throws FileNotFoundException {
        return new org.primefaces.model.DefaultStreamedContent(
                new java.io.FileInputStream("C:\\apache-tomcat-8.0.45\\temp\\utfpr\\arquivos\\Koala.jpeg"),
                "image/jpeg",
                "Koala.jpeg"
        );
    }

    public void descarregar(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);

        streamedContent = new DefaultStreamedContent(inputStream,
                Files.probeContentType(file.toPath()), file.getName());
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }
}
