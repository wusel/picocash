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

/**
 *
 * @author wusel
 */
public class ImportExportConstants {

    public static final String ELEMENT_ACCOUNT = "account";
    public static final String ELEMENT_PAYEE = "payee";
    public static final String ELEMENT_CATEGORY = "category";
    public static final String ELEMENT_TRANSACTION = "transaction";
    public static final String GROUP_ACCOUNTS = "accounts";
    public static final String GROUP_TRANSACTIONS = "transactions";
    public static final String GROUP_CATEGORIES = "categories";
    public static final String GROUP_PAYEES = "payees";

    public static class NAME_ICON_ATTRIBUTES {

        public static final String ATTRIBUTE_ID = "id";
        public static final String ATTRIBUTE_NAME = "name";
        public static final String ATTRIBUTE_ICON = "icon";
    }

    public static class ACCOUNT_ATTRIBUTES {

        public static final String ATTRIBUTE_ACCOUNT_NUMBER = "accountNumber";
        public static final String ATTRIBUTE_BANK = "bank";
        public static final String ATTRIBUTE_WITHDRAW = "withDraw";
        public static final String ATTRIBUTE_BALANCE = "balance";
        public static final String ATTRIBUTE_START_BALANCE = "startBalance";
    }

    public static class TRANSACTION_ATTRIBUTES {

        public static final String ATTRIBUTE_AMOUNT = "amount";
        public static final String ATTRIBUTE_COMMENT = "comment";
        public static final String ATTRIBUTE_CATEGORY = "category";
        public static final String ATTRIBUTE_PAYEE = "payee";
        public static final String ATTRIBUTE_FROM_ACCOUNT = "fromAccount";
        public static final String ATTRIBUTE_TO_ACCOUNT = "toAccount";
        public static final String ATTRIBUTE_DATE = "date";
    }


}
