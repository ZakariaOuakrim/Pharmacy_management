package com.example.test.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.w3c.dom.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
    @FXML private TextField nomc;
    @FXML
    private ChoiceBox nomm;
    @FXML private TextField prix;

    @FXML
    private void facture(ActionEvent event) throws IOException {
        Document doc = new Document() {
            @Override
            public String getNodeName() {
                return null;
            }

            @Override
            public String getNodeValue() throws DOMException {
                return null;
            }

            @Override
            public void setNodeValue(String nodeValue) throws DOMException {

            }

            @Override
            public short getNodeType() {
                return 0;
            }

            @Override
            public Node getParentNode() {
                return null;
            }

            @Override
            public NodeList getChildNodes() {
                return null;
            }

            @Override
            public Node getFirstChild() {
                return null;
            }

            @Override
            public Node getLastChild() {
                return null;
            }

            @Override
            public Node getPreviousSibling() {
                return null;
            }

            @Override
            public Node getNextSibling() {
                return null;
            }

            @Override
            public NamedNodeMap getAttributes() {
                return null;
            }

            @Override
            public Document getOwnerDocument() {
                return null;
            }

            @Override
            public Node insertBefore(Node newChild, Node refChild) throws DOMException {
                return null;
            }

            @Override
            public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
                return null;
            }

            @Override
            public Node removeChild(Node oldChild) throws DOMException {
                return null;
            }

            @Override
            public Node appendChild(Node newChild) throws DOMException {
                return null;
            }

            @Override
            public boolean hasChildNodes() {
                return false;
            }

            @Override
            public Node cloneNode(boolean deep) {
                return null;
            }

            @Override
            public void normalize() {

            }

            @Override
            public boolean isSupported(String feature, String version) {
                return false;
            }

            @Override
            public String getNamespaceURI() {
                return null;
            }

            @Override
            public String getPrefix() {
                return null;
            }

            @Override
            public void setPrefix(String prefix) throws DOMException {

            }

            @Override
            public String getLocalName() {
                return null;
            }

            @Override
            public boolean hasAttributes() {
                return false;
            }

            @Override
            public String getBaseURI() {
                return null;
            }

            @Override
            public short compareDocumentPosition(Node other) throws DOMException {
                return 0;
            }

            @Override
            public String getTextContent() throws DOMException {
                return null;
            }

            @Override
            public void setTextContent(String textContent) throws DOMException {

            }

            @Override
            public boolean isSameNode(Node other) {
                return false;
            }

            @Override
            public String lookupPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public boolean isDefaultNamespace(String namespaceURI) {
                return false;
            }

            @Override
            public String lookupNamespaceURI(String prefix) {
                return null;
            }

            @Override
            public boolean isEqualNode(Node arg) {
                return false;
            }

            @Override
            public Object getFeature(String feature, String version) {
                return null;
            }

            @Override
            public Object setUserData(String key, Object data, UserDataHandler handler) {
                return null;
            }

            @Override
            public Object getUserData(String key) {
                return null;
            }

            @Override
            public DocumentType getDoctype() {
                return null;
            }

            @Override
            public DOMImplementation getImplementation() {
                return null;
            }

            @Override
            public Element getDocumentElement() {
                return null;
            }

            @Override
            public Element createElement(String tagName) throws DOMException {
                return null;
            }

            @Override
            public DocumentFragment createDocumentFragment() {
                return null;
            }

            @Override
            public Text createTextNode(String data) {
                return null;
            }

            @Override
            public Comment createComment(String data) {
                return null;
            }

            @Override
            public CDATASection createCDATASection(String data) throws DOMException {
                return null;
            }

            @Override
            public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
                return null;
            }

            @Override
            public Attr createAttribute(String name) throws DOMException {
                return null;
            }

            @Override
            public EntityReference createEntityReference(String name) throws DOMException {
                return null;
            }

            @Override
            public NodeList getElementsByTagName(String tagname) {
                return null;
            }

            @Override
            public Node importNode(Node importedNode, boolean deep) throws DOMException {
                return null;
            }

            @Override
            public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }

            @Override
            public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }

            @Override
            public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
                return null;
            }

            @Override
            public Element getElementById(String elementId) {
                return null;
            }

            @Override
            public String getInputEncoding() {
                return null;
            }

            @Override
            public String getXmlEncoding() {
                return null;
            }

            @Override
            public boolean getXmlStandalone() {
                return false;
            }

            @Override
            public void setXmlStandalone(boolean xmlStandalone) throws DOMException {

            }

            @Override
            public String getXmlVersion() {
                return null;
            }

            @Override
            public void setXmlVersion(String xmlVersion) throws DOMException {

            }

            @Override
            public boolean getStrictErrorChecking() {
                return false;
            }

            @Override
            public void setStrictErrorChecking(boolean strictErrorChecking) {

            }

            @Override
            public String getDocumentURI() {
                return null;
            }

            @Override
            public void setDocumentURI(String documentURI) {

            }

            @Override
            public Node adoptNode(Node source) throws DOMException {
                return null;
            }

            @Override
            public DOMConfiguration getDomConfig() {
                return null;
            }

            @Override
            public void normalizeDocument() {

            }

            @Override
            public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }
        };
//        PdfWriter.getInstance(doc,new FileOutputStream("FACTURE.pdf"));
//        doc.open();
//        doc.add(new Paragraph("FACTURE ICI"));
//        doc.close();
//

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
