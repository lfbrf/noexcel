/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treenode;

/**
 *
 * @author Luiz
 */
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "treeBasicView")
@ViewScoped
public class BasicView implements Serializable {

    private TreeNode root;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);

        TreeNode node1 = new DefaultTreeNode("Node 1", root);

        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));

    }

    public TreeNode getRoot() {
        return root;
    }
}
