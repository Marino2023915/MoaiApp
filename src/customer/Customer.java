/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package customer;

/**
 * 顧客情報を保持します。
 * <br><br>
 * 【Information】<br>
 *  機能１ すべてのメンバ変数，メソッドを作成します。<br>
 *
 * @author nus Academy Co., Ltd
 */
public class Customer {
    /** 顧客番号 */
    private String no;

    /** 顧客姓 */
    private String nameLast;

    /** 顧客名 */
    private String nameFirst;

    /** 顧客パスワード */
    private String password;

    /** 顧客郵便番号 */
    private String postalNo;

    /** 顧客住所 */
    private String address;

    /** 顧客電話番号 */
    private String phone;

    /** 顧客e-mail */
    private String email;

    /**
     * getterです。
     * @return String 顧客番号です。
     */
    public String getNo() {
        return no;
    }

    /**
     * setterです。
     * @param no 設定する顧客番号です。
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * getterです。
     * @return String 顧客姓です。
     */
    public String getNameLast() {
        return nameLast;
    }

    /**
     * setterです。
     * @param nameLast 設定する顧客姓です。
     */
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    /**
     * getterです。
     * @return String 顧客名です。
     */
    public String getNameFirst() {
        return nameFirst;
    }

    /**
     * setterです。
     * @param nameFirst 設定する顧客名です。
     */
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    /**
     * getterです。
     * @return String 顧客パスワードです。
     */
    public String getPassword() {
        return password;
    }

    /**
     * setterです。
     * @param password 設定する顧客パスワードです。
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getterです。
     * @return String 顧客郵便番号です。
     */
    public String getPostalNo() {
        return postalNo;
    }

    /**
     * setterです。
     * @param postalNo 設定する顧客郵便番号です。
     */
    public void setPostalNo(String postalNo) {
        this.postalNo = postalNo;
    }

    /**
     * getterです。
     * @return String 顧客住所です。
     */
    public String getAddress() {
        return address;
    }

    /**
     * setterです。
     * @param address 設定する顧客住所です。
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getterです。
     * @return String 顧客電話番号です。
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setterです。
     * @param phone 設定する顧客電話番号です。
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getterです。
     * @return String 顧客e-mailです。
     */
    public String getEmail() {
        return email;
    }

    /**
     * setterです。
     * @param email 設定する顧客e-mailです。
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
