/*
 * This file is part of picocash.
 *
 * picocash is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * picocash is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with picocash.  If not, see <http://www.gnu.org/licenses/>.
 * and open the template in the editor.
 *
 * Copyright 2008 Daniel Wasilew
 */
package picocash.export;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import picocash.model.NameAndIconable;
import picocash.model.impl.Account;
import picocash.model.impl.Category;
import picocash.model.impl.Payee;
import picocash.model.impl.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author wusel
 */
public class ModelExporter {

    private static final Log log = LogFactory.getLog(ModelExporter.class);
    private final String fileName;
    private List<Account> accounts = new ArrayList<Account>();
    private List<Category> categories = new ArrayList<Category>();
    private List<Payee> payees = new ArrayList<Payee>();
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public ModelExporter(String fileName) {
        this.fileName = fileName;
    }

    public void export() throws Exception {
        FileWriter fw = new FileWriter(fileName);

        log.info("start export");
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        Document document = docBuilder.newDocument();

        Element root = document.createElement("picocash_model_backup");
        document.appendChild(root);

        Element accountsElement = document.createElement(ImportExportConstants.GROUP_ACCOUNTS);
        Element payeesElement = document.createElement(ImportExportConstants.GROUP_PAYEES);
        Element categoriesElement = document.createElement(ImportExportConstants.GROUP_CATEGORIES);
        Element transactionsElement = document.createElement(ImportExportConstants.GROUP_TRANSACTIONS);

        root.appendChild(accountsElement);

        for (Account account : accounts) {
            accountsElement.appendChild(createAccountElement(account, document));
        }

        root.appendChild(payeesElement);
        for (Payee payee : payees) {
            payeesElement.appendChild(createPayeeElement(payee, document));
        }

        root.appendChild(categoriesElement);
        for (Category category : categories) {
            categoriesElement.appendChild(createCategoryElement(category, document));
        }

        root.appendChild(transactionsElement);
        for (Transaction transaction : transactions) {
            transactionsElement.appendChild(createTransactionElement(transaction, document));
        }

        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(fw);
        DOMSource source = new DOMSource(document);
        trans.transform(source, result);
    }

    private Element createAccountElement(Account account, Document doc) {
        Element element = createIDAndNameableElement(doc, ImportExportConstants.ELEMENT_ACCOUNT, account);
        element.setAttribute(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_ACCOUNT_NUMBER, account.getAccountNumber());
        element.setAttribute(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_BANK, account.getBank());
        element.setAttribute(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_WITHDRAW, account.getWithDraw() + "");
        element.setAttribute(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_BALANCE, account.getBalance() + "");
        element.setAttribute(ImportExportConstants.ACCOUNT_ATTRIBUTES.ATTRIBUTE_START_BALANCE, account.getStartbalance() + "");
        return element;
    }

    public void setAccounts(List<Account> allAccounts) {
        this.accounts = allAccounts;
    }

    public void setCategories(List<Category> allCategories) {
        this.categories = allCategories;
    }

    public void setPayees(List<Payee> allPayees) {
        this.payees = allPayees;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    private Node createCategoryElement(Category category, Document document) {
        Element element = createIDAndNameableElement(document, ImportExportConstants.ELEMENT_CATEGORY, category);
        return element;
    }

    private Element createIDAndNameableElement(Document document, String name, NameAndIconable source) throws DOMException {
        Element element = document.createElement(name);
        element.setAttribute(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ID, source.getId() + "");
        element.setAttribute(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_NAME, source.getName());
        element.setAttribute(ImportExportConstants.NAME_ICON_ATTRIBUTES.ATTRIBUTE_ICON, source.getIcon());
        return element;
    }

    private Node createPayeeElement(Payee payee, Document document) {
        Element element = createIDAndNameableElement(document, ImportExportConstants.ELEMENT_PAYEE, payee);
        return element;
    }

    private Node createTransactionElement(Transaction transaction, Document document) {
        Element element = createIDAndNameableElement(document, ImportExportConstants.ELEMENT_TRANSACTION, transaction);
        element.setAttribute(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_AMOUNT, transaction.getAmount() + "");
        element.setAttribute(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_COMMENT, transaction.getComment());
        element.setAttribute(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_CATEGORY, transaction.getCategory() != null ? transaction.getCategory().getId() + "" : "");
        element.setAttribute(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_PAYEE, transaction.getPayee() != null ? transaction.getPayee().getId() + "" : "");
        element.setAttribute(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_FROM_ACCOUNT, transaction.getFromAccount() != null ? transaction.getFromAccount().getId() + "" : "");
        element.setAttribute(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_TO_ACCOUNT, transaction.getToAccount() != null ? transaction.getToAccount().getId() + "" : "");
        element.setAttribute(ImportExportConstants.TRANSACTION_ATTRIBUTES.ATTRIBUTE_DATE, transaction.getTransactionDate() + "");
        return element;
    }
}
