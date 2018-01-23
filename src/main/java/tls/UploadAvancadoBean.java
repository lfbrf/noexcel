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
import br.edu.utfpr.model.User;
import br.edu.utfpr.model.service.UserService;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.edu.utfpr.util.ArquivoUtil;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class UploadAvancadoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<File> arquivos = new ArrayList<>();

    @PostConstruct
    public void postConstruct() {
        arquivos = new ArrayList<>(ArquivoUtil.listar());
        userService = new UserService();

    }

    public StreamedContent getImagem() {
        return imagem;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }
    private StreamedContent imagem;

    public void upload(FileUploadEvent event) {
        String login = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("login");
        System.out.println("MEU LOGIN" + login);

        UploadedFile uploadedFile = event.getFile();
        String remoteUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        String tipo;

        try {
            tipo = uploadedFile.getFileName().substring(uploadedFile.getFileName().length() - 4);
            if (tipo.equals("jpeg")) {
                tipo = ".jpeg";
            }
            if (tipo.equals("png")) {
                tipo = ".png";
            }
            if (tipo.equals("gif")) {
                tipo = ".gif";
            }

            System.out.println(uploadedFile.getContentType() + "----" + uploadedFile.getFileName() + "-----" + "----" + tipo);
            File arquivo = ArquivoUtil.escrever(login + tipo, uploadedFile.getContents());
            imagem = new DefaultStreamedContent(event.getFile().getInputstream());
            arquivos.add(arquivo);
            User user = userService.getByProperty("login", login);
            user.setAvatar(login + tipo);
            userService.update(user);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Upload completo", "O arquivo " + arquivo.getName() + " foi salvo!"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
        }
    }

    public List<File> getArquivos() {
        return arquivos;
    }

}
